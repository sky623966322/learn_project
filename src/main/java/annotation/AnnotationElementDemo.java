package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 未指定@Target时，默认值可以此注解可以作用在任何元素上
 * 未指定@Retention时，默认值是@Retention(RetentionPolicy.CLASS)，jvm运行时会被抛弃字节码，不参与运行
 */
@Retention(RetentionPolicy.SOURCE)
public @interface AnnotationElementDemo {
    //枚举类型
    enum Status {FIXED,NORMAL};

    //声明枚举
    Status status() default Status.FIXED;

    //布尔类型
    boolean showSupport() default false;

    //String类型
    String name()default "";

    //class类型
    Class<?> testCase() default Void.class;

    //注解嵌套
    Reference reference() default @Reference(next=true);

    //数组类型
    long[] value();
}


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
@interface Reference{
    boolean next() default false;
}

