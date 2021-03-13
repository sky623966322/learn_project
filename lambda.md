>原文链接 http://zh.lucida.me/blog/java-8-lambdas-insideout-language-features/

#### 1.Lambda使用的背景

java8之前通过匿名内部类来进行回调模式和函数式风格编程，匿名内部类会存在以下问题：

- 语法冗余
- 匿名类中this是表示内部类，而非外部类
- 类型载入和实例创建语义不够灵活
- 无法使用非final的局部变量
- 无法对控制流进行抽象

上面的多数问题均在Java SE 8中得以解决：

- 通过提供更简洁的语法和局部作用域规则，Java SE 8 彻底解决了问题 1 和问题 2
- 通过提供更加灵活而且便于优化的表达式语义，Java SE 8 绕开了问题 3
- 通过允许编译器推断变量的“常量性”（finality），Java SE 8 减轻了问题 4 带来的困扰

#### 2.函数式接口（Functional interfaces）

尽管匿名内部类有着种种限制和问题，每一个函数对象都对应一个接口类型。大多数回调接口都拥有这个特征：
比如 Runnable 接口和 Comparator 接口。我们把这些只拥有一个方法的接口称为 **函数式接口**。

为什么使用函数式接口，而不使用引入一个全新的 结构化 函数类型，例如(String, Object) -> int。<br/>
- 它会导致类库风格不兼容，一些类库会继续使用回调接口，例如Java7中使用的Runnable等类库，而另一些类库会使用结构化函数类型。
- 在处理exception时，结构化类型的语法会非常笨拙，而使用函数式接口只需要接口声明exception。
- 无法对方法 m(T->U) 和 m(X->Y) 进行重载（Overload），而函数式接口在有多个接口时可以按照上下文类型进行推导。

@FunctionalInterface 注解来显式指定一个接口是函数式接口（以避免无意声明了一个符合函数式标准的接口），加上这个注解之后，编译器就会验证该接口是否满足函数式接口的要求。

Java SE 7 中已经存在的函数式接口：
- java.lang.Runnable
- java.util.concurrent.Callable
- java.security.PrivilegedAction
- java.util.Comparator
- java.io.FileFilter
- java.beans.PropertyChangeListener

Java SE 8中增加了一个新的包：java.util.function，它里面包含了常用的函数式接口，例如：

- Predicate<T>——接收 T 并返回 boolean
- Consumer<T>——接收 T，不返回值
- Function<T, R>——接收 T，返回 R
- Supplier<T>——提供 T 对象（例如工厂），不接收值
- UnaryOperator<T>——接收 T 对象，返回 T
- BinaryOperator<T>——接收两个 T，返回 T

#### 3.lambda表达式（lambda expressions）
lambda 表达式的语法由参数列表、箭头符号 -> 和函数体组成。函数体既可以是一个表达式，也可以是一个语句块。
表达式函数体适合小型 lambda 表达式，它消除了 return 关键字，使得语法更加简洁。

```
(int x, int y) -> x + y
() -> 42
(String s) -> { System.out.println(s); }
```

#### 4. 目标类型（Target typing）和上下文

需要注意的是，函数式接口的名称并不是 lambda 表达式的一部分。那么问题来了，
对于给定的 lambda 表达式，它的类型是什么？答案是：它的类型是由其上下文推导而来

这就意味着同样的 lambda 表达式在不同上下文里可以拥有不同的类型：
```
Callable<String> c = () -> "done";
PrivilegedAction<String> a = () -> "done";
```
编译器负责推导 lambda 表达式类型。它利用 lambda 表达式所在上下文 所期待的类型 进行推导，
这个被期待的类型被称为 **目标类型**。lambda 表达式只能出现在目标类型为函数式接口的上下文中。

当且仅当下面所有条件均满足时，lambda 表达式才可以被赋给目标类型 T：
- T为函数式接口
- lambda表达式的参数和函数式接口的参数在数量上一一对应。
- lambda表达式的返回值和函数式接口的返回时兼容（相同或者继承关系）。
- lambda表达式所抛出的exception和函数式接口的方法抛出的exception类型兼容（相同或者继承关系）。

由于函数式接口已经知道了lambda表达式的形式参数类型，因此没必要在lambda表达式中重新写一遍参数类型。
```
(int x, int y) -> x + y  可以写成  （x, y） -> x + y
```
此外，当 lambda 的参数只有一个而且它的类型可以被推导得知时，该参数列表外面的括号可以被省略：
```
(String s) -> { System.out.println(s); } 可以写成 s -> System.out.println(s)
```

如果编译器无法解析出lambda表达式对应的函数接口类型，那么有以下三种方法
```
String[] names = {"Bob", "Alice", "Tim"};
//1.显示的指定参数类型
Arrays.stream(names).map((String s) -> new Person(s));

//2.创建一个指定类型的的函数式接口，例如Function<String, Person>

//3.为函数式接口Function R泛型指定类型
Arrays.stream(names).<Person>map(s -> new Person(s));
```

转型表达式（Cast expression）可以显式提供 lambda 表达式的类型，
这个特性在无法确认目标类型时非常有用。当重载的方法都拥有函数式接口时，
转型可以帮助解决重载解析时出现的二义性。
```
//这段代码是非法的, 无法根据上下文推测lambda表达式对应的函数式接口类型
//Object o = () -> { System.out.println("hi"); };
Object o = (Runnable) () -> { System.out.println("hi"); };
```

#### 6. 词法作用域（Lexical scoping）
lambda表达式函数体中引用外部变量和它在外部具体具有相同的语义。

- this关键词，lambda内部的this就是外部的this。与其相比较的内部类的this就不是外部的this。
- lambda 表达式不可以掩盖任何其所在上下文中的局部变量。以下示例中lambda中不可重新定义i。

注意：lambda表达式内部，如果未使用this，lambda函数式接口对象不会保存对外部对象this的引用。
   而内部类实例一直保留一个对外部类实例的强引用，内部类这个特性往往会造成内存泄露。
   
```
public class LambdaExample {
    @Test
    public void thisRef(){
        int i = 1;
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("匿名内部内 this -> " + this);

                int i = 2;
                System.out.println("匿名内部内 i -> " + i);
            }
        }).start();

        new Thread(() -> {
            System.out.println("lambda this -> " + this);

            //int i = 2;
            //System.out.println("lambda i -> " + i);
        }).start();
    }
}
```

#### 7. 变量捕获（Variable capture）
**有效只读变量**：如果一个局部变量在初始化后从未被修改过, 加上final 后也不会导致编译错误的
局部变量就是有效只读变量。

Java7编译器对内部类引用外部变量（即捕获变量）要求非常严格，必须声明为final。
Java8放宽了限制，内部类和lambda表达式允许引用有效只读变量。

##### lambda 表达式对值封闭，变量开放, 
```
int sum = 0;
list.forEach(e -> { sum += e.size(); }); // Illegal, close over values

List<Integer> aList = new List<>();
list.forEach(e -> { aList.add(e); }); // Legal, open over variables
```

>final基本数据类型变量一旦初始化之后，就不能被修改；final的引用类型的变量，
则在对其初始化之后便不能再让其指向另一个对象。

aList可以加上final，也代表aList是有效只读变量，只是不允许重新指向新的对象，对本身元素的操作是可以的。

如果想改变基本数据类型变量，则可以使用sum，min，max，reduce等归纳操作。
```
int[] arr = {1, 2, 3, 4};
IntStream.of(arr).sum();
IntStream.of(arr).reduce(0, (left, right) -> left + right);
```

#### 8.方法引用 

见代码Lambda::methodRef

#### 10. 默认方法和静态接口方法
Java8中，接口可以增加默认方法和静态方法

默认方法：
- 默认方法拥有其默认实现，接口的实现类通过继承得到该默认实现（如果类型没有覆盖该默认实现）。
- 此外，默认方法不是抽象方法，所以我们可以放心的向函数式接口里增加默认方法，而不用担心函数式接口的单抽象方法限制。
















