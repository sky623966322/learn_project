package grammar;


public class TryDemo {
    public static void main(String[] args) {
        System.out.println(test1());
    }

    public static int test1() {
        int i = 0;
        try {
            i = 2;
            return i;
        } finally {
            i = 3;
        }
    }
}
