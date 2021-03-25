import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HelloWorld {

    static int i = 10;

    static {
        i += 5;
    }

    public HelloWorld() {
        super();
    }

    /**
     * 1.static变量可以被修改
     * 2.static变量在多个实例对象中共享
     */
    @Test
    public void testStatic() {
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.test();

        HelloWorld helloWorld2 = new HelloWorld();
        helloWorld2.test2();
    }

    /**
     * 测试static变量的线程安全性: 不安全
     */
    @Test
    public void testAtomic() throws InterruptedException {
        ArrayList<Thread> threads = new ArrayList<>();
        for (int j = 0; j < 10; j++) {
            Thread thread = new Thread(() -> {
                for (int k = 0; k < 10000; k++) {
                    i++;
                }
            });
            thread.start();

            threads.add(thread);
        }

        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(i);
    }

    @Test
    public void test() {
        float f1 = 3.9f;
        int i1 = (int) f1; //强转丢失精度

        int ia[][] = {{4, 5, 6}, {1, 2, 3}};

        System.out.println(i1);
    }

    public void test2() {
        System.out.println(i);
    }

    public static int getLastLen(String inputStr) {
        if (inputStr == null || inputStr.length() == 0) {
            return 0;
        }
        String[] strArr = inputStr.split(" ");
        return strArr[strArr.length - 1].length();
    }

    public static void main(String[] args) {
        /*int x=4;
        double v = (x > 4) ? 99.9 : 9;
        System.out.println("value is " + v);*/

        System.out.println(i);

        int a, b = 1;


        float f1 ,f2;
        f1 = f2 = 8.0f;
        System.out.println(f1 + f2);
    }

    public void testSwitch(){
        short s = 1;
        int a = 1;
        long l = 1;


        switch (a){
            case 1:
                break;
            default:
                return;
        }

    }

   /* public void modify() {
        int I, j, k;
        I = 100;
        while (I > 0) {
            j = I * 2;
            System.out.println(" The value of j is " + j);
            k = k + 1;//这里编译不通过，k未被初始化
            I--;
        }
    }*/

    static {
        i /= 3;
    }
}
