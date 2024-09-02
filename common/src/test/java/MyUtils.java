import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @className MyUtils 
 * @description  
 * @author c.w
 * @date 2024/08/30
**/
public class MyUtils {
    @Test
    public void testMergeFile(){
        String fsPath = "E:\\Learnspace\\bilibiliLearn\\easypan\\temp\\9080545509_0829460178";
        String targetPath = "E:\\Learnspace\\bilibiliLearn\\easypan\\temp\\target\\test.jpg";
        File f = new File(fsPath);
        try {
            utils.MyUtils.mergeFile(f.listFiles(),targetPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


