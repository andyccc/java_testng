import org.junit.Assert;
import org.junit.Test;

public class TestDemo {
    @Test
    public void testcase2(){
        Assert.assertTrue(true);
        System.out.println("testcase1");

        int a =1;
        int b =2;
        int c =1;
        int d = a&c;
        int e = a|c;

        if (a == b && a == c) {
            System.out.println("进来了");
        }

        if (a == b || a == c) {
            System.out.println("又进来了");
        }
        System.out.println("d = "+d);


    }
}
