package lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

public class StreamExample {
    static String[] strArr = {"I", "love", "you", "too"};

    @Test
    public void forEach(){
        Stream<String> stream = Stream.of(strArr);
        stream.forEach(s -> System.out.println(s));

        Stream.of(strArr)
                .filter(s -> s.length() == 3)
                .forEach(s -> System.out.println(s));

        /**
         * map将原有的元素按照给点的函数做一一对应
         */
        Stream.of(strArr).map(s -> s.toUpperCase()).forEach(s -> System.out.println(s));

        /**
         * 中间操作	filter() flatMap() map() sorted() distinct()
         * concat() limit()  peek() skip()  parallel() sequential() unordered()
         *
         * 结束操作	forEach() allMatch() anyMatch() collect() count() findAny() findFirst()
         *  forEachOrdered() max() min() noneMatch() reduce() toArray()
         */
    }

    @Test
    public void map(){
        String[] array = new String[] { " 2019-12-31 ", "2020 - 01-09 ", "2020- 05 - 01 ", "2022 - 02 - 01",
                " 2025-01 -01" };
        // 请使用map把String[]转换为LocalDate并打印:
        Arrays.stream(array)
                .map(String::trim)
                .forEach(localDate -> System.out.println(localDate));
    }

    @Test
    public void reduce(){

        /**
         * reduce 压缩
         *
         * 求最长的单词
         */
        Optional<String> ret1 =  Stream.of(StreamExample.strArr)
                .reduce((s, s2) -> s.length() >= s2.length() ? s : s2);
        System.out.println(ret1.get());


        /**
         * 求所有单词长度的总和
         */
        Integer ret2 = Stream.of(strArr).reduce(0,
                (integer, s) -> integer + s.length(),
                (integer, integer2) -> integer + integer2);
        System.out.println(ret2);
    }


}


