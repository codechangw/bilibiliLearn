package com.easypan;

//import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Mybatis的mapper文件中的sql语句被修改后, 只能重启服务器才能被加载, 非常耗时,所以就写了一个自动加载的类,
 * 配置后检查xml文件更改,如果发生变化,重新加载xml里面的内容.
 */
//@Slf4j
@Component
public class MapperAutoRefresh implements ApplicationContextAware, SmartInitializingSingleton {
    /*容器上下文, 通过 Aware 填充*/
    private ApplicationContext applicationContext;
    /*是否启用 Mapper 刷新线程功能*/
    private static boolean enabled = true;
    /* Mapper.xml 实际资源路径集合 默认去 MyBatis 中已有的配置*/
    private Set<String> locationSet = new HashSet<>();
    /* MyBatis 中 xml 文件的路径列表, 用 File[] 包裹, 没法直接使用*/
    private Set<String> loadedResourcesSet;
    /*MyBatis配置对象*/
    private Configuration configuration;
    /*上一次刷新时间*/
    private Long beforeTime = 0L;
    /*延迟刷新秒数*/
    private static int delaySeconds = 10;
    /*休眠时间*/
    private static int sleepSeconds = 30;

    /**
     * 根据配置的 SqlSessionFactoryBean 的 mapperLocations 属性, 获取所有的 mapper.xml 的资源路径
     *
     * @see SqlSessionFactoryBean
     */
    @SuppressWarnings("unchecked")
    public void setLocation() {
        try {
            Field loadedResourcesField = Configuration.class.getDeclaredField("loadedResources");
            loadedResourcesField.setAccessible(true);
            this.loadedResourcesSet = ((HashSet<String>) loadedResourcesField.get(configuration));
            for (String locationPath : loadedResourcesSet) {
                if (locationPath.startsWith("file [")) {
                    String s = locationPath.substring("file [".length(), locationPath.lastIndexOf("]"));
                    locationSet.add(s);
                    System.out.println("Location:" + s);
                }
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 执行资源刷新任务
     */
    public void exeTask() {
        if (CollectionUtils.isEmpty(locationSet)) {
            return;
        }
        beforeTime = System.currentTimeMillis();
        if (enabled) {
            new Thread(runnable).start();
        }
    }

    private Runnable runnable = () -> {
        try {
            // 暂定时间
            TimeUnit.SECONDS.sleep(delaySeconds);
            System.out.println("==================================================");
            System.out.println("========= Enabled refresh mybatis mapper =========");
            System.out.println("==================================================");
            // 开始执行刷新操作
            while (true) {
                for (String path : locationSet) {
                    this.refresh(path, beforeTime);
                }
                TimeUnit.SECONDS.sleep(sleepSeconds);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    };

    /**
     * 刷新资源的操作
     *
     * @param filePath   xml 文件路径
     * @param beforeTime 上次刷新事件
     */
    public void refresh(String filePath, long beforeTime) {
        // 本次刷新时间
        long refreshTime = System.currentTimeMillis();
        File file = new File(filePath);
        if (!checkFile(file, beforeTime)) {
            return;
        }
        try {
            InputStream inputStream = new FileInputStream(file);
            // 清理原有资源，更新为自己的StrictMap方便增量重新加载
            String[] mapFieldNames = new String[]{
                    "mappedStatements", "caches",
                    "resultMaps", "parameterMaps",
                    "keyGenerators", "sqlFragments"
            };
            for (String fieldName : mapFieldNames) {
                Field field = Configuration.class.getDeclaredField(fieldName);
                field.setAccessible(true);
                Map map = ((Map) field.get(configuration));
                if (!(map instanceof StrictMap)) {
                    Map newMap = new StrictMap(StringUtils.capitalize(fieldName) + "collection");
                    for (Object key : map.keySet()) {
                        try {
                            newMap.put(key, map.get(key));
                        } catch (IllegalArgumentException ex) {
                            newMap.put(key, ex.getMessage());
                        }
                    }
                    field.set(configuration, newMap);
                }
            }
            // 清理已加载的资源标识，方便让它重新加载。
            this.loadedResourcesSet.remove(filePath);
            //重新编译加载资源文件。
            XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(inputStream, configuration,
                    filePath, configuration.getSqlFragments());
            xmlMapperBuilder.parse();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        } finally {
            ErrorContext.instance().reset();
        }
        //if (log.isDebugEnabled()) {
            System.out.println("Refresh file: " + file.getAbsolutePath());
            System.out.println("Refresh filename: " + file.getName());
        //}
        this.beforeTime = refreshTime;
    }

    /**
     * 判断文件是否需要刷新,需要刷新返回true，否则返回false
     *
     * @param file       xml 文件
     * @param beforeTime 上次更新事件
     * @return 是否需要重新加载
     */
    private boolean checkFile(File file, Long beforeTime) {
        return file.lastModified() > beforeTime;
    }


    /**
     * 重写 org.apache.ibatis.session.Configuration.StrictMap 类
     * 来自 MyBatis3.4.0版本，修改 put 方法，允许反复 put更新。
     *
     * @param <V>
     */
    public static class StrictMap<V> extends HashMap<String, V> {

        private static final long serialVersionUID = -4950446264854982944L;
        private final String name;

        public StrictMap(String name, int initialCapacity, float loadFactor) {
            super(initialCapacity, loadFactor);
            this.name = name;
        }

        public StrictMap(String name, int initialCapacity) {
            super(initialCapacity);
            this.name = name;
        }

        public StrictMap(String name) {
            super();
            this.name = name;
        }

        public StrictMap(String name, Map<String, ? extends V> m) {
            super(m);
            this.name = name;
        }

        @SuppressWarnings("unchecked")
        @Override
        public V put(String key, V value) {
            // 核心逻辑, 先删除后添加
            if (enabled) {
                remove(key);
            }
            if (containsKey(key)) {
                throw new IllegalArgumentException(name + " already contains value for " + key);
            }
            if (key.contains(".")) {
                final String shortKey = getShortName(key);
                if (super.get(shortKey) == null) {
                    super.put(shortKey, value);
                } else {
                    super.put(shortKey, (V) new Ambiguity(shortKey));
                }
            }
            return super.put(key, value);
        }

        @Override
        public V get(Object key) {
            V value = super.get(key);
            if (value == null) {
                throw new IllegalArgumentException(name + " does not contain value for " + key);
            }
            if (value instanceof Ambiguity) {
                throw new IllegalArgumentException(((Ambiguity) value).getSubject() + " is ambiguous in " + name
                        + " (try using the full name including the namespace, or rename one of the entries)");
            }
            return value;
        }

        private String getShortName(String key) {
            final String[] keyParts = key.split("\\.");
            return keyParts[keyParts.length - 1];
        }

        protected static class Ambiguity {
            private String subject;

            public Ambiguity(String subject) {
                this.subject = subject;
            }

            public String getSubject() {
                return subject;
            }
        }

    }

    /**
     * 单例实例化完成后执行
     */
    @Override
    public void afterSingletonsInstantiated() {
        SqlSessionFactory sessionFactory = applicationContext.getBean(SqlSessionFactory.class);
        this.configuration = sessionFactory.getConfiguration();
        setLocation();
        exeTask();
    }

    /**
     * 赋值 applicationContext
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}


