package lambda;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

public class CollectionExample {
    static String[] strArr = {"I", "love", "you", "too"};

    public void listForEach(){
        List<String> list = Lists.newArrayList(strArr);
        list.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });

        /**
         * Java8 Collection新增forEach方法，实际上还是一个语法糖，Consumer提供接口就是为了使用lambda表达式，
         * 内部实际上还是用for迭代
         */
        list.forEach(s -> {
            System.out.println(s);
        });

        /**
         * 类似forEach的方法还有 removeIf， replaceAll， sort
         */
    }

    @Test
    public void mapForEach(){
        HashMap<Integer, String> map = Maps.newHashMap();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");

        map.forEach((key, value) -> {
            System.out.println("" + key + "->" + value);
        });

        map.replaceAll((key, value) -> value.toUpperCase());
        System.out.println(map);

        /**
         * merge：将boy合并到3对应的值上
         */
        map.merge(3, "boy", (orgin, newValue) -> orgin + " " + newValue);
        System.out.println(map);

        /**
         * compute：对key对应的value进行操作，等到的新值进行替换
         */
        map.compute(3, (key, value) -> {
            String newMsg = " and girl";
            return  value==null ? newMsg : value.concat(newMsg);
        });
        System.out.println(map);


        /**
         * 还有类似的操作 computeIfAbsent， computeIfPresent
         */
    }

}
