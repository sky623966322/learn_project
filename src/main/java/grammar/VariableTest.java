package grammar;

public class VariableTest {

    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        short s1 = 1;
        // s1 = s1 + 1; 会报错，因为s1 + 1已经向上转型为int，无法赋值给short
        s1 += 1; // 相当于下面的强转
        s1 = (short) (s1 + 1);

        /**
         * Integer.valueOf()自动封装箱，IntegerCache保存了-128~127的数字
         * 超过了就是new Integer()
         */
        Integer a = 128, b = 128;
        Integer c = 127, d = 127;
        System.out.println(a == b);// 比较对象地址
        System.out.println(c == d);

        int e = 2 * 8;
        System.out.println(e);
    }

}
