import org.junit.Test;

/**
 * @className ImageCodeTest 
 * @description 验证码方法test 
 * @author c.w
 * @date 2024/04/29
**/
public class ImageCodeTest {

    @Test
    public void randomStrTest(){
        String s = randomStr(4);
        System.out.println(s);
    }

    private String randomStr(int n){
        String str1 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String str2 = "";
        int len = str1.length()-1;
        double r;
        for (int i = 0; i < n; i++) {
            r = Math.random()* len;
            str2 += str1.charAt((int)r);
        }
        return str2;
    }
}


