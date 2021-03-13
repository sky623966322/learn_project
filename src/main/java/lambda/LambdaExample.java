package lambda;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LambdaExample {
    static String[] names = {"Bob", "Alice", "Tim"};

    public void test(){
        /**
         * 如果编译器无法解析出lambda表达式对应的函数接口类型，那么有以下三种方法
         */

        //1.显示的指定参数类型
        Arrays.stream(names).map((String s) -> new Person(s));

        //2.创建一个指定类型的的函数式接口，例如Function<String, Person>

        //3.为函数式接口Function R泛型指定类型
        Arrays.stream(names).<Person>map(s -> new Person(s));

        //这段代码是非法的, 无法根据上下文推测lambda表达式对应的函数式接口类型
        //Object o = () -> { System.out.println("hi"); };
        Object o = (Runnable) () -> { System.out.println("hi"); };
    }



    @Test
    public void thisRef(){
        int i = 1;
        int j = 2;
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("匿名内部内 this -> " + this);

                int i = 2;
                System.out.println("匿名内部内 i -> " + i);

                //Java8之后，只要变量为有效只读变量，可以不用final修饰内部类也可以使用
                System.out.println("匿名内部内 j -> " + j);

            }
        }).start();

        new Thread(() -> {
            System.out.println("lambda this -> " + this);

            //int i = 2;
            //System.out.println("lambda i -> " + i);
        }).start();
    }


    /**
     * 值封闭，变量开放
     *
     * final基本数据类型变量一旦初始化之后，就不能被修改；
     * final的引用类型的变量，则在对其初始化之后便不能再让其指向另一个对象
     */
    public void closeToValue(){
        int[] arr = {1, 2, 3, 4};
        int sum = 0;
        //Arrays.stream(arr).forEach(value -> sum += value);

        final ArrayList<String> list = Lists.newArrayList();
        Arrays.stream(names).forEach(name -> list.add(name));

        //使用归纳操作
        IntStream.of(arr).sum();
        IntStream.of(arr).reduce(0, (left, right) -> left + right);
    }

    public void methodRef(){

        /**
         * 1.构造方法引用
         *
         * Stream.map要求函数式接口 Function<String, Person>要求入参为String，返回结果为Person,
         * 而Person构造方法形参为String， 并且隐式的返回this对象，则刚好对应，所以可以用方法引用代替
         */
        Function<String, Person> function = Person::new;

        /**
         * 2.1 特定类型的任意对象的实例方法：
         * 如果一个实例方法和一个函数式接口方法想对应，那么可以用ClassName::Method()方法引用
         *
         * Comparator.comparing要求函数式接口 Function泛型<Person, String>：入参是Person类型，返回的是String。
         * person.getName()入参隐式传入this，返回String
         */
        Function<Person, String> function2 = Person::getName;
        Comparator<Person> comparator1 = Comparator.comparing(function2);

        //排序
        List<Person> list = Arrays.stream(names).map(function).sorted(comparator1).collect(Collectors.toList());

        /**
         * 2.2 特定类型的任意对象的实例方法
         *
         * Compartor.compare()要求传入(String, String)， 返回int，
         * String.compareTo(String), 隐式的传入this，实际上参数就是(this, String)，返回的也是int， 所以这里可以使用方法引用
         */
        Comparator<String> comparator2 = String::compareTo;
        Arrays.sort(names, comparator2);

        /**
         * 3.静态方法引用, 与函数式接口 Compartor.compare(String, String)对应
         *
         * 与2.2中不同的是，names的泛型是String，所以才能使用String::compareTo,
         * 而LambdaExample::compare并不是
         */
        Arrays.sort(names, LambdaExample::compare);

        /**
         * 4. 特定对象的实例方法
         */
        Person ray = new Person("Ray");
        list.sort(ray::compare);

        /**
         * 5.数组构造方法引用 (奇怪的用法)
         */
        IntFunction<int[]> arrayMaker = int[]::new;
        int[] array = arrayMaker.apply(10); // 创建数组 int[10]
    }

    public void lambdaUpgrade(){
        List<Person> list = Lists.newArrayList();

        //最原始
        list.sort(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        //1.lambda表达式替换
        list.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));

        //2.使用Comparetor::comparing
        list.sort(Comparator.comparing(person -> person.getName()));

        //3.使用方法引用
        list.sort(Comparator.comparing(Person::getName));

        //使用降序比较器
        list.sort(Comparator.comparing(Person::getName).reversed());

    }

    public static int compare(String s1, String s2) {
        return s1.compareTo(s2);
    }

    @Override
    public String toString() {
        return "LambdaExample{}";
    }


}
