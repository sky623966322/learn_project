###Java基础
1.java数据类型。
    1.1 分为基础类型和引用类型。基础类型有哪些（8种）？引用类型有哪些（数组、对象）。
    1.2 包装类有哪些？
    1.3 Integer, Double能用双等号比较吗？要用什么方法比较。

2.java 三大特性。继承、封装、多态。
    1.1 java为什么不能多继承？（会产生二义性问题，两个父类中有相同的方法，不知道该继承哪个）
    1.2 多态有哪几种。（编译时多态 - 重载， 运行时多态 - 重写）
    1.3 什么是重载？决定重载的方法有哪些。
    1.4 重写方法， 是编译的时候还是运行时候决定调用哪个方法？（B 继承 A， C 继承 A， a.func()）

3.new String("abc")和String str="abc"是一样的吗？为什么String是final修饰？String是用什么数据结构实现的。

4.String, StringBuilder， StringBuffer区别。为什么StringBuffer线程安全？

5.常用的集合有哪些？List, Set, Map
    5.1 ArrayList, LinkedList分别是用什么数据结构实现的。它们的区别。
    5.2 HashSet 为什么能去除重复值？（HashMap实现的）

6.HashMap与LinkedHashMap区别。他们的底层数据结构。
    >HashMap： 数组 + 链表， jdk1.8当链表长度大于8链表转为红黑树）。说说HashMap.put()逻辑。
    > 1.7与1.8相比做了改进: 
        > 1.8 resize()扩容后存储索引位置优化：
            >1.8 resize()之后不再hash运算：扩容后索引 = 原索引 or 原索引 + oldCap；
            >1.7 resize()之后需要再次调用hash()方法做hashCode运算和扰动处理，再确定索引位置；
        >扩容时转移数据方式不同
            >1.8 尾插法，并且引入红黑树的数据结构，将数据插入到链表尾部或者当链表数据数量大于8时，转换为红黑树结构
            >1.7 头插法，与扩容前的数据位置发生颠倒，出现逆序，并发条件下会出现死循环
        >扩容时插入的数据时机不同
            >1.8 先插入再扩容
            >1.7 先扩容再插入
    >LinkedHashMap：有序，遍历慢。 散列表 + 双向链表。

7.HashMap线程安全吗？了解或者使用过ConcurrentHashMap吗？为什么ConCurrentHashMap能保证线程安全？
    >1.7 采用segment数组 + 链表结构, segment继承自ReentrantLock。
        >1）寻址方式：对key作hash运算获得segment位置
        >2）同步方式：put方法中，先通过索引定位segment，segment通过自旋tryLock()尝试获得锁，如果超过retry次数，则调用lock()申请锁，如果还是未获得锁，lock()方法将线程park()不再尝试。
    >1.8采用 哈希桶数组 + 链表或红黑树, 采用CAS + 自旋 + synchronize保证线程安全
        >1）寻址方式：key的hashcode与数组长度取模 h & (length -1), 与1.8HashMap相同
        >2）同步方式：对于put操作:
            >如果哈希桶为null，则用CAS + 自旋方式添加元素
            >如果哈希桶不为null，则用synchronize关键字同步
          
8.说说反射相关的类。Class, Field, Method, Constructor。
    8.1 利用反射创建对象的方法： 
        >Class clazz = Class.forName("xx");  clazz.newInstance() (获取默认无参的Constructor)
        >Constructor constructor = clazz.getDeclaredConstructor(); construtor.newInstance();
        
9.说说常见的Error与Exception， 已经他们之间的区别。
    9.1> Error， 系统内部错误和资源耗尽错误，程序内部不应该抛出。StackOverflowError, OutOfMemoryError
    9.2> Exception
        >非RuntimeException： IOException， FileNotFoundExpcetpion， ClassNotFoundException
        >RuntimeException：如果出现，就一定是你的问题。ArrayIndexOutOfBoundsExcetpion, NullPointExcetpion
    9.3>自定义异常应该继承哪个类？

###多线程

10. 线程几种状态。它们之间是怎么装换的。Thread.Sleep与Object.wait可以使线程进入sleep状态。怎么唤醒sleep状态的线程？
11. synchronized关键字的使用。（同步Class， 同步代码块， 同步方法）它们之间的区别。
    11.1 试着写单例的双锁模式
12. synchronized与ReentrantLock区别。
13. 线程池的核心参数，及创建线程的过程。
14. 原子类AtomicInteger为什么说线程安全？(volatile, CAS)
    14.1 AtomicInteger++ 线程安全吗？
15. ThreadLocal变量怎么使用，使用场景。连接池, 事务控制
    
###JVM
15. 1.8版本JVM内存区域划分。
16. 1.8常用的垃圾收集器。什么时候回触发GC
17. 了解双亲委派吗？为什么使用双亲委派？java有哪些地方破坏了双亲委派（加载第三方驱动， 比如com.mysql.cj.jdbc.Driver驱动）， 为什么要这样做？
18. lambda表达式对程序性能有提升吗？还知道哪些语法糖（包装类、泛型）
19. 与java相关的linux命令， 关注过GC状态吗？
20. 线上出问题，是怎么定位的？

###Spring
21. spring bean的加载过程。
22. Spring AOP与IOC的实现原理。
23. Spring Bean的作用域。


###Mysql
https://blog.csdn.net/weixin_45132238/article/details/107251285

24. 索引类型， 索引优化。
25. MySQL 引擎InnoDB与MyIsam区别
26. unoin 与 unoin all区别
27. 数据表死锁场景
28. 数据库id生成方法

###Redis
29. 数据接口有哪些。
30. Redis持久化的几种方式，优缺点是什么，怎么实现的
31. 缓存穿透的解决办法

###网络相关
1.Http请求get和post的区别以及数据包格式
2.说说你知道的几种HTTP响应码

###其他
1.maven解决依赖冲突,快照版和发行版的区别




