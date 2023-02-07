package exception;

import java.io.IOException;

public class ExceptionTest {

    public static void main(String[] args) {
        /**
         * unchecked exception 不受检查异常可以不处理
         *
         * 常见的例如：NullPointException， ArrayIndexOutOfBoundException，ClassCastException， ArithmeticException（算数异常）
         */
        test1();

        /**
         * checked exception 受检查异常必须被处理
         *
         * 常见的列如：IOException， SQLException，ClassNotFoundException
         */
        try {
            test2();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void test1() throws BaseException{

    }

    public static void test2() throws IOException {

    }

}
