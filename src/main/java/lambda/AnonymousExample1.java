package lambda;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

public class AnonymousExample1 {

    /**
     * #1.接口的lambda表达式，比如Runnable，Comparetor接口
     */
    @Test
    public void case1(){
        new Thread(() -> {
            System.out.println("test");
        }).start();

        List<String> list = Lists.newArrayList("c", "b", "a");
        Collections.sort(list, (o1, o2) ->{
            return o1.compareTo(o2);
        });
        System.out.println(list);

        /**
         * #2.自定义接口
         */
        CustomInterface customInterface = () -> {
            /**
             * #3. Lambda表达式不是匿名内部内，不会产生额外的class文件。
             * 所以这里的this是指AnonymousExample1
             */
            System.out.println(this.toString());
        };

        customInterface.test();
    }

    @Override
    public String toString() {
        return "AnonymousExample1{}";
    }

    interface CustomInterface{
        void test();

        /**
         * #4. 可以有多个default方法， Java8新增的关键字
         */
        default void test2(){
            System.out.println("test2");
        };

        default void test3(){
            System.out.println("test3");
        };

        String toString();
    }

}
