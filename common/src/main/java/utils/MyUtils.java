package utils;


import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;

/**
 * @className MyUtils
 * @description
 **/
public class MyUtils {
    public static <T> boolean ListObjectIsEmpty(List<T> list) {
        if (list == null || list.isEmpty()) {
            return true;
        }
        return false;
    }

    public static <T> boolean ListObjectIsEmpty(T[] list) {
        if (list == null || list.length == 0) {
            return true;
        }
        return false;
    }

    public static void mergeFile(File[] files, String targetFilePath) throws IOException {
        File targetFile = new File(targetFilePath);
        if (!targetFile.exists()) {
            try {
                targetFile.createNewFile();
            } catch (IOException e) {
                System.err.println("创建文件失败:" + targetFilePath);
                throw e;
            }
        }
        if (!ListObjectIsEmpty(files)) {
            FileChannel outChannel = new FileOutputStream(targetFile).getChannel();
            //  合并
            for (int i = 0; i < files.length; i++) {
                FileChannel inChannel = new FileInputStream(files[i]).getChannel();
                ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024);
                while (inChannel.read(buffer) != -1) {
                    buffer.flip();
                    outChannel.write(buffer);
                    buffer.clear();
                }
                inChannel.close();
            }
            outChannel.close();
            System.out.println("合并文件完成:"+targetFilePath);
        }
    }
}


