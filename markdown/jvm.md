# 第一章 Java概述

#### Java技术体系

- Java技术体系包括：JCP官方定义，Java程序设计语言、JVM、Class格式规范、Java Core类库API、第三方类库

- JDK: Java程序设计语言、JVM、Java类库统称为JDK(Java Development Kit)

- jre：Java SE的API和JVM两部分统称为JRE（Java Runtime Environment），JRE是支持Java程序运行的标准环境。

- 按照技术所服务的领域来划分：

    1）Java Card：支持Java小程序（Applets）运行在小内存设备（如智能卡）上的平台。

    2）Java ME（Micro Edition）：是为机顶盒、移动电话和PDA之类嵌入式消费电子设备提供的Java语言平台，包括虚拟机和一系列标准化的Java API。注意不是Android。

    3）Java SE（Standard Edition）：提供完整的Java核心API，同时支持Client桌面应用和Server应用。

    4)Java EE(EnterpriseEdition):企业级应用，在JDK10以后被Oracle放弃，捐献给Eclipse基金会管理，此后被称为Jakarta EE。
    
- JCP 是Java规范的制定组织，由Oracle、IBM、RedHat等成员组成。

- Oracle JDK 与 Open JDK

    Oracle JDK收费 https://zhuanlan.zhihu.com/p/64731331

    1）Oracle JDK： 由Oracle开发维护，个人免费，商业收费。
    
    2）OpenJDK： 由OpenJDK社区维护，开源，对个人和企业都免费。Oracle将JDK11所有的代码都开源给Open JDK，所以JDK11之前所有代码和Oracle JDK完全一样。
    
#### JDK各版本特性
http://wiki.xuetang9.com/?p=5010
https://blog.csdn.net/qq_22194659/article/details/86134443
https://en.wikipedia.org/wiki/Java_version_history

- JDK1.5： 泛型，自动装箱拆箱，enum（枚举）， foreach，JMM（内存模型），concurrent（并发包）；
- JDK6: synchronize锁优化（锁的升级）；
- JDK7：switch语句块中允许以字符串作为分支条件 ，创建泛型对象时应用类型推断， try-with-resources，数值型可以用二进制（0B）、可以用下划线分割表示数值（2_3456），引入支持动态类型包java.lang.invoke;
- JDK8：Lambda表达式；接口default方法，HashMap、ConcurrentHashMap重构；
- JDK9：Jigsaw模块化，G1成为默认垃圾收集器；
- JDK10：优化G1, var本地类型推断；增加Graal即时编译器（实验性）；
- JDK11：调整授权协议，12开始OracleJDK收费，11之前的OpenJDK与11的OracleJDK共用代码并且继续免费，增加实验性的收集器ZGC；
- JDK12：新增Shenandoah垃圾收集器，但是OracleJDK没有使用，继续优化ZGC， switch语句进行扩展；
- JDK13：优化ZGC，改进swithc表达式；
- JDK14：switch表达式正式版本，ZGC（实验性）支持Mac、Windows；
- JDK15：ZGC正式版，Shenandoah正式版

#### OpenJDK如何升级
https://www.redhat.com/en/resources/build-of-openjdk-datasheet
https://access.redhat.com/articles/1299013

OpenJDK一直是免费的，OracleJDK从12开始需要收费。2019年2月RedHat同时从Oracle手上接过OpenJDK 8和OpenJDK 11的管理权利和维护职责，RedHat代替Oracle成为JDK历史版本的维护者。

第一种，手动升级。从可以去RedHat官网手动下载（https://developers.redhat.com/products/openjdk/download）
对应的版本进行手动升级。

第二种，订阅升级。订阅购买RedHat企业Linux、中间件（比如JBoss）、OpenJDK Window订阅服务等。这些可以在线自动升级。
    
# 第三章 Java内存模型

参考 https://blog.csdn.net/javazejian/article/details/72772461

### 1.JVM内存模型

![image](5259016F70F348F69EA1E0DBAAFEC166)

- **方法区（Method Area）**:线程共享的内存区域。 存储被JVM加载的类信息，常量， 静态变量（全局的），即时编译器优化后的代码（1.7 HotSpot 已经将字符串常量池移到堆区）;
- **堆区（heap area）**：线程共享的内存区域。存放对象实例。
- **虚拟机栈（JVM Stacks）**：线程私有区域, 随着线程一起创建。每个方法被执行时，会创建一个栈帧，栈帧用来存放局部变量表、操作数栈、动态链接、方法出口。
- **本地方法栈（native method stack）**：线程私有区。不同于VM Stack，执行native方法使用本地方法栈
- **程序计数器**：线程私有区。保存当前线程执行字节码位置，字节码执行到哪一个分支、循环、跳转、异常处理、线程恢复都是通过程序计数器完成的。每个线程都有一个独立的程序计数器，这样CPU内核为每个线程分配时间片后，都能恢复到正确的执行位置。只为Java方法服务，执行native方法，程序计数器为空（Undefined）。

#### 虚拟机栈（JVM Stacks）

参考资料：

https://zhuanlan.zhihu.com/p/45354152

https://juejin.im/post/6844903793604182030

《深入理解Java虚拟机》2.3.3节

##### 基础知识

###### Java数据类型

![image](E1F96BC3CD9D4BE783CAAF0F809EB6AA)

###### Java基础变量长度
类型 | 长度 | 范围
---|---|---
byte | 1字节 | -2^7 ~ 2^7
boolean | 1字节 | true or false
char | 2字节 | 2^16
short(短整形) | 2字节 | -2^15 ~ 2^15
int | 4字节 | -2^31 ~ 2^31
float | 4字节 | 
long | 8字节 | -2^63 ~ 2^63
double | 8字节 | 

float、double取值范围见 https://blog.csdn.net/a327369238/article/details/52354811

###### MB，KB, B关系
B与b不同，注意区分， 1MB（兆字节） = 1024KB（千字节）= 1024*1024B(字节) = 1048576B(字节)； 8bit（比特位）= 1Byte（字节）； 1024Byte（字节）= 1KB(千字节)； 1024KB（千字节）.

##### 栈帧(Stack Frame)：
栈帧(Stack Frame)是用于支持虚拟机进行方法调用和方法执行的数据结构。每个方法被执行的时候，Java虚拟机都
会同步创建一个栈帧，栈帧存储了方法的局部变量表、操作数栈、动态连接和方法返回地址等信息。每一个方法从调用至执行完成的过程，都对应着一个栈帧在虚拟机栈里从入栈到出栈的过程。

对于执行引擎来讲，在活动线程中，只有位于栈顶的方法才是在运行的，只有位于栈顶的栈帧才是生效的，其被称为“当前栈帧”（Current Stack Frame），与这个栈帧所关联的方法被称为“当前方法”（Current Method）

![image](C20A3CCA7FD44B4185B87EAD063EAD03)

1）**局部变量表(Local Variable Table)**，包括基本数据类型（byte、boolean、char、short、int、float、long、double）、对象的引用类型reference（不包含对象本身，对象存放在堆区域、retureAddress类型。

*栈帧大小在线程执行时会发生变化吗？*<br>
解释：编译Java程序源码的时候，栈帧中需要多大的局部变量表，需要多深的操作数栈就已经被分析计算出来，并且写入到方法表的Code属性之中。换言之，一个栈帧需要分配多少内存，**并不会受到程序运行期变量数据的影响**，而仅仅取决于程序源码和具体的虚拟机实现的栈内存布局形式。

###### 变量槽（Variable Slot）
**变量槽（Variable Slot）是局部变量表的容量最小单位**。《Java虚拟机规范》规定一个变量槽可以存放boolean、
byte、char、short、int、float、reference或returnAddress类型的数据，使用32位的物理空间来存储，也就是这8种类型使用一个变量槽存储。对于64位的数据类型long和double，Java虚拟机会以高位对齐的方式为其分配两个连续的变量槽空间，把一次long和double数据类型读写分割为两次32位读写。

对于是64位操作系统，按照《Java虚拟机规范》，byte、char、short、int、float、reference或returnAddress采用高位补0对齐的方式使用一个变量槽空间，而long和double仍然使用两个变量槽空间，也就是一个64位的数据类型占128位的内存空间。

自己的理解： 
- long，double拆分成高32位和低32位，高32位后补齐32个0，低32位前补齐32个0，读取数据时，两部分进行与或运算得到真正的数据，
- 32位虚拟机中，一个变量槽占32位；64位虚拟机中，一个变量槽占64位，64位内存存储时高32位用对齐和补白手段填充。

###### reference类型
**reference长度：**《Java虚拟机规范》只规定reference变量使用一个变量槽，所以它的长度与实际使用32位还是64位虚拟机有关，如果是64位虚拟机，还与是否开启某些对象指针压缩的优化有关。

**refrence类型访问对象方式：** reference类型《Java虚拟机规范》既没有说明它的长度，也没有明确指出这种引用应有怎样的结构。但是一般来说，虚拟机实现至少都应当能通过这个引用做到两件事情，一是从根据引用直接或间接地查找到对象在Java堆中的数据存放的起始地址或索引，二是根据引用直接或间接地查找到对象所属数据类型在方法区中的存储的类型信息

参考 https://juejin.cn/post/6844903793604182030

- 指针：对象在内存中的物理地址。
- 引用（reference）：引用是对象的别名，其实质就是功能受限但是安全性更高的指针。
- 句柄：指针的指针，句柄实际上是一个数据，是一个Long (长整型)的数据，记载着对象在内存中的地址。例如windows内存管理器移动对象在内存中的位置后，把对象新的地址告知这个句柄地址来保存。

**1）使用句柄间接访问**：对于一些虚拟机来说，reference指向的是堆中的句柄池中的句柄，句柄保存了对象实例的指针和对象类型(类信息)的指针，这种是间接方式。

![image](D370E3B9D5C24C8FA35456C6AA2F3AED)

**2）使用指针直接访问**：对于HotSpot来说，reference保存的是对象的地址，这种是直接方式。

![image](5D92EC32329C462F8F301314523E3E41)

###### returnAddress类型：
目前已经很少见了，它是为字节
码指令jsr、jsr_w和ret服务的，指向了一条字节码指令的地址，某些很古老的Java虚拟机曾经使用这几
条指令来实现异常处理时的跳转，但现在也已经全部改为采用异常表来代替了。

###### 思考

*1.局部变量不在使用，设置为null是否有意义？<br/>
解释： 在实际情况中，即时编译才是虚拟机执行代码的主要方式，赋null值的操作在经过即时编译优化后几乎是一定会被当作无效操作消除掉的，这时候将变量设置为null就是毫无意义的行为*

*2.为什么类变量可以不赋值而直接使用，而局部变量不行呢？<br/>
解释：类变量有两次赋值的机会，一次是在类加载的“准备”阶段，类变量会被赋值为0、false或者null，另外一次是初始化阶段由用户代码赋值。所以类变量没有赋值，也可以直接使用。而局部变量没有用户代码赋值，那完全是没法使用的，在编译的时候就会报错。*

##### 2.操作数栈(Operand Stack)
也可以叫做操作栈，随着方法的执行，
**会从局部变量表中复制基本数据类型变量，或者对象实例中复制常量进入操作数栈**，再随着计算的进行将栈中元素出栈到局部变量表或者返回给方法调用者，也就是出栈/入栈操作。

3）**动态连接(Dynamic Linking)**，指向方法在常量池中的符号。class的方法在调用其他方法时候，会将其他方法的引用存储在常量池中。JVM会在虚拟机栈中为这个引用分配内存空间。

4）**方法返回地址（Return Address）**, 包括正常返回地址和异常返回地址。一般来说，方法正常退出时，主调方法的PC计数器的值就可以作为返回地址，栈帧中很可能会保存这个计数器值。而方法异常退出时，返回地址是要通过异常处理器表来确定的，栈帧中就一般不会保存这部分信息。方法退出的过程实际上等同于把当前栈帧出栈。

#### 方法区（Method Area）
方法区用于存储每一个Clas结构信息，例如，Class一般信息（版本号、类名、访问标志等），还包括Class的字段、方法、接口，常量池等。
![image](EC37F7DB0D01447589EB284ADAA0F0EC)

方法区在JVVM启动时创建，《Java虚拟机规范》不限定实现方法区的内存位置和内存管理策略。在JDK8之前，HotSpot开发者将垃圾收集的分代设置扩展至方法区，也就是用永久代实现了方法区，方便了方法区的管理，省去专门为方法区编写内存管理代码的工作。但是对其他JVM，譬如BEA JRockit、IBM J9等来说，是不存在永久代的概念的。

到了JDK 7的HotSpot，已经把原本放在永久代的字符串常量池、静态变量等移出，而到了JDK 8，终于完全废弃了永久代的概念，改用与JRockit、J9一样在本地内存中实现的元空间（Meta-space）来代替，把JDK7中永久代还剩余的内容（主要是类型信息）全部移到元空间中。

#### 运行时常量池
运行时常量池（Runtime Constant Pool）是class文件中每一个Class的常量池的运行时的表示形式，包括编译期可知的数值字面量、符号引用和必须在后期运行期解析后才能获得的方法或字段的引用。

JDK7之前运行时常量池是方法区的一部分，对于HotSpot来说使用永久代实现的，可以通过-XX：PermSize和-XX：MaxPermSize限制永久代的大小，即可间接限制其中常量池的容量。JDK8以后HotSpot使用元空间实现方法区，JDK8及以上版本使用-XX：MaxMeta-spaceSize参数限制元空间的大小。需要注意的是，从JDK7开始，就已经将字符串常量池移动到堆区域中。

### HotSpot中的对象

####  对象的创建

https://crowhawk.github.io/2017/08/09/jvm_1/

##### 对象创建的4中方式：

- new关键字创建
- 使用反射，Class.forName().newInstance()和Constructor.newInstance, 前者最终也是调用后者
- 实现Cloneable接口，覆盖Object.clone()方法，使用clone()方法实现浅拷贝；
- 实现Serializable接口，使用IO序列化与反序列化方式实现深拷贝；


##### new关键字对象创建的过程

1>类加载检查：new关键字检查类是否已经被加载、解析和初始化过的;

2>分配内存：JVM在heap堆中为对象划分内存空间；

3>初始化：将分配到的内存空间初始化；

4>设置对象头：设置MarkWord（包含HashCode、GC年龄、锁信息等）和类型指针（对象属于哪个类）；

5>执行<init>方法;

##### 对象的内存布局
在HotSpot虚拟机里，对象在堆内存中的存储布局可以划分为三个部分：对象头（Header）、实例数据（Instance Data）和对齐填充（Padding）。

###### 对象头
对象头在将synchronize同步时会详细分析。

###### 实例数据
1）父类和子类的数据都记录在内存中；

2）存储顺序受分配策略和字段在代码中的顺序影响；

###### 对齐填充
由于HotSpot虚拟机的自动内存管理系统要求对象起始地址必须是8字节的整数倍，换句话说就是**任何对象的大小都必须是8字节的整数倍**。对象头部分已经被精心设计成正好是8字节的倍数（1倍或者2倍），因此，如果对象实例数据部分没有对齐的话，就需要通过对齐填充来补全。

### JVM内存管理

https://tech.meituan.com/2017/12/29/jvm-optimize.html

#### 堆（Heap）内存结构：

在G1收集器出现之前，经典收集器将Hotspot VM内存划分为不同的物理区，采用“分代回收”算法。JVM内存主要由新生代、老年代、永久代构成。

![image](BCE4A197D0714393912488D749626BB6)

##### 1）新生代：
大部分对象都是在Eden区域创建，当Eden区空间不够，会发生Minor GC，垃圾回收器将还存活的对象复制到 S0，S1其中的一个。如果对象超过了GC“晋升年龄阈值”限制，会被复制到老年代。

##### 2）老年代：

进入老年代的条件：
- 经历过N次GC的还存活的对象（包括Minor GC和Major GC）
- 有些大对象也会被直接存放在老年代。HotSpot虚拟机提供了-XX：PretenureSizeThreshold参数，指定大于该设置值的对象直接在老年代分配，这样做的目的就是避免在Eden区及两个Survivor区之间来回复制，产生大量的内存复制操作。例如：-XX:PretenureSizeThreshold=3145728（不能直接写成3M）。

3）永久代：也叫做非堆区，逻辑分区中的方法区的数据，包括Class、Method信息会存储在这里。对于Hot Spot虚拟机，是用永久代实现了方法区。到了JDK 7的HotSpot，已经把原本放在永久代的字符串常量池、静态变量等移出，而到了
JDK 8，终于完全废弃了永久代的概念，改用与JRockit、J9一样在本地内存中实现的元空间（Meta-
space）来代替，把JDK 7中永久代还剩余的内容（主要是类型信息）全部移到元空间中 https://crowhawk.github.io/2017/08/10/jvm_2/）。

##### 分代收集
- 新生代收集（Minor GC/Young GC）：指目标只是新生代的垃圾收集。
- 老年代收集（Major GC/Old GC）：针对老年代垃圾收集。目前只有CMS收集器会有单独的老年代收集行为，换句话说，Serial+Serial Old，Parallel Scavenge收集器是触发Full GC时才会发生Major GC。
- Full GC：收集整个Java堆和方法区的垃圾收集。JDK 6 Update 24之
后的规则变为只要老年代的连续空间大于新生代对象总大小或者历次晋升的平均大小，就会进行Minor GC，否则将进行Full GC。

CMS收集器Full GC触发条件：
- Perm永久代已满（JDK8开始，Perm区完全消失，转而使用元空间。而元空间是直接存在内存中，不在JVM中）。
- 老年代剩余连续空间不足，担保失败、担保成功进行Monitor GC仍然没有成功，这两种情况都可能触发Full GC。
- CMS运行期间必须预留一部分空间供并发收集时的程序运作使用，当预留空间无法满足程序分配新对象的需要，就会出现一次“并发失败”（Concurrent Mode Failure），这时候虚拟机将不得不启动后备预案：冻结用户线程的执行，临时启用Serial Old收集器来重新进行老年代的垃圾收集，这样就产生“Stop The World”的Full GC。
- 主动触发Full GC（执行jmap -histo:live [pid]）来避免碎片问题。

##### 空间分配担保
JDK6.24之后担保开关HandlePromotionFailure被去掉，默认开启。

逻辑：当进行Minor GC之前，JVM检查Old Gen剩余连续空间大于Young Gen对象总空间：

1>如果Old Gen剩余空间 > Young Gen，则直接进行Minor GC；

2>如果Old Gen剩余空间 ≤ Young Gen，则判断Old Gen剩余空间是否大于Minor GC每次晋升对象大小的平均值。

    2.1> Old Gen剩余空间 ≤ 平均值，担保失败，进行Full GC；
    2.1> Old Gen剩余空间 > 平均值，则进行Minor GC。这次Minor GC仍然可能失败。如果失败则还是进行Full GC；
    
#### GC算法

https://crowhawk.github.io/2017/08/10/jvm_2/

##### 1）对象存活判断：

- 引用计数法：给对象添加了一个引用计数器，被引用加1，引用失效减1，引用为0则表示失效。但是由于存在循环引用的问题，未被JVM采用；

- 可达性分析法：被作为GC Root的引用，如果没有对象与之关联，则表示这个对象不可达；
![image](848255A2F52E49A387F315ABA2B5B426)
 
##### 2）垃圾收集算法：

- 标记-清除（Mark-Sweep）算法：标记要回收的对象，标记完成后回收被标记的对象。存在 空间碎片化、查找空闲空间效率的问题。
![image](1D5EF7D2B01B43909698465B50858ED4)

- 标记-复制（Mark-Copy）算法常被简称为复制（Copy）算法：将容量划分成两个相同大小的空间，每次使用一块，当这一块空间不足时，将存活的对象复制到另外一块空间，然后把使用过的空间清理掉。现在的商业虚拟机都使用复制算法来回收新生代。Minor GC采用此算法。
![image](EBC838FBBEB34FA2A4E9FE38B48D2106)

- 标记-整理（Mark-Compact）：先标记可回收的对象，让存活的对象向一端移动，然后直接清理掉边界以外的内存。可以理解为 先标记 -> 再清除 -> 最后整理
![image](59AA3D63FBA845B8BC99B961B277A927)

### HotSpot细节实现相关概念

#####  GC Root：
 根对象作为起始节点集，从这些节点开始，根据引用关系向下搜索，搜索过程所走过的路径称为“引用链”（Reference Chain），如果某个对象到GC  Roots间没有任何引用链相连，或者用图论的话来说就是从GC Roots到这个对象不可达时，则证明此对象是不可能再被使用的。在Java技术体系里面，固定可作为GC Roots的对象包括以下几种：
 
 - 在虚拟机栈（栈帧中的本地变量表）中引用的对象，比如参数、局部变量；
 - 在方法区中Java类静态属性引用的对象；
 - 常量引用的对象，比如字符串常量池（String Table）；
 - 在本地方法栈中JNI（即通常所说的Native方法）引用的对象；
 - Java虚拟机内部的引用，如基本数据类型对应的Class对象，一些常驻的异常对象（比如
NullPointExcepiton、OutOfMemoryError）等，还有系统类加载器。
- 所有被同步锁（synchronized关键字）持有的对象。
- 反映Java虚拟机内部情况的JMXBean、JVMTI中注册的回调、本地代码缓存等。
- 还可以有其他对象“临时性”地加入的对象；

##### GC Root根节点枚举
固定可作为GC Roots的节点主要在全局性的引用（例如常量或类静态属性）与执行上下文（例如栈帧中的本地变量表）中。

所有收集器在根节点枚举这一步骤时都是必须暂停用户线程的，**因为根节点枚举始终还是必须在一个能保障一致性的快照中才得以进行**。

由于目前主流Java虚拟机使用的都是准确式垃圾收集，所以当用户线程停顿下来之后，其实并不需要一个不漏地检查完所有
执行上下文和全局的引用位置。HotSpot使用一组成为OopMap数据结构来存储对象内的各类型数据，在即时编译的过程中，会在内存中中记录哪些位置是引用类型数据，这样就不用对所有的GC Roots进行查找。

##### SafePoint： 
安全点，虚拟机决定在特殊的时间点暂停用户线程，开始垃圾收集。例如方法调用、循环跳转、异常跳转
等都属于指令序列复用，所以只有具有这些功能的指令才会产生安全点

##### Remembered Set：
为解决对象跨代引用所带来的问题，垃圾收集器在新生代中建
立了名为记忆集（Remembered Set）的数据结构，用以避免把整个老年代加进GC Roots扫描范围。

卡表（Card-Table）：

记忆集是一种抽象逻辑数据结构，有多种选择的记录精度，例如字节精度、对象精度、卡精度，目前卡表（Card-Table）是最常用的一种实现。卡表中定义了卡精度与堆内存的映射关系等，卡表最简单的形式可以只是一个字节数组，字节数组CARD_TABLE的每一个元素都对应着其标识的内存区域中一块特定大小的内存块，每个字节数组中的元素就是“卡页”（Card Page）。

一个卡页的内存中通常包含不止一个对象，只要卡页内有一个（或更多）对象的字段存在着跨代指针，那就将对应卡表的数组元素的值标识为1，称为这个元素变脏（Dirty），没有则标识为0。在垃圾收集发生时，只要筛选出卡表中变脏的元素，就能轻易得出哪些卡页内存块中包含跨代指针，把它们加入GC Roots中一并扫描。

![image](9310ECE5049742D6B3EF1FFEBB12B73B)

##### 写屏障
###### 1）目的
写屏障用来解决卡表元素如何维护的问题，例如它们何时变脏、谁来把它们变脏等。

###### 2）如何使标记引用对象使卡表“变脏”
卡表元素何时变脏的答案是很明确的——有其他分代区域中对象引用了本区域对象时，其对应的卡表元素就应该变脏，变脏时间点原则上应该发生在引用类型字段赋值的那一刻。

写屏障可以看作在虚拟机层面对“引用类型字段赋值”这个动作的AOP切
面 [2] ，在引用对象赋值时会产生一个环形（Around）通知，供程序执行额外的动作，也就是说赋值的前后都在写屏障的覆盖范畴内。

###### 3）写屏障使用情况
在赋值前的部分的写屏障叫作写前屏障（Pre-Write Barrier），在赋值后的则叫作写后屏障（Post-Write Barrier）。HotSpot虚拟机的许多收集器中都有使用到写屏障，但直至G1收集器出现之前，其他收集器都只用到了写后屏障。

##### 并发的可达性分析
由于并发标记与用户线程同时进行，遇到以下这两种情况如何解决呢？

1）已经扫描过的对象突然又有新的对象引用，此对象不应该被回收，但是按照“三色标记”，会被回收。

增量更新（Incremental Update）：在新增对象引用时，记录新增的对象，再次扫描是否需要回收。CMS收集器使用此方法。


2） 扫描引用时，将要被标记的对象断开了引用，导致需要被回收的对象没有被回收。

原始快照（Snapshot At The Beginning，SATB）：在断开时，记录当时的快照，第一次扫描结束后，再次按照原来的结构扫描快照。G1收集器使用此方法。


### 可以搭配使用的垃圾收集器
![image](5DB25A68A63E40069E072E8F6BA30104)

这个关系不是一成不变的，由于维护和兼容性测试的成本，在JDK  8时将Serial+CMS、
ParNew+Serial Old这两个组合声明为废弃（JEP 173），并在JDK 9中完全取消了这些组合的支持（JEP214）

### 新生代垃圾回收器

https://crowhawk.github.io/2017/08/15/jvm_3/

##### 1）串行收集器（Serial）：
新生代收集器，使用复制算法。单线程收集同时必须暂停其他所有工作线程，直到它收集结束（Stop The World）。但是由于简单额外内存消耗最小，单线程没有额外的线程交互开销（核心线程和用户线程交互），所以对于客户端应用程序来说，Serial收集器还是一个很好的选择。HotSpot在客户端模式下默认的收集器就是Serial。
![image](6F6FC1D7D3B04FC08AFDBE8CA4EFC98F)

##### 2）并行收集器（ParNew）：
新生代收集器，采用复制算法，与Serial公用大部分代码，功能类似，Serial收集器的多线程并行版本。

在JDK9之前，ParNew收集器是激活CMS后（使用-XX：+UseConcMarkSweepGC选项）的默认新生代收集器，也可以使用-XX：+/-UseParNewGC选项来强制指定或者禁用它。

G1是一个面向全堆的收集器，不再需要其他新生代收集器的配合工作。所以自JDK9开始，ParNew加CMS收集器的组合就不再是官方推荐的服务端模式下的收集器解决方案了
![image](BE0D4B1A048A42F1972E47BDA0C21AA3)

##### 3）吞吐量优先（Parallel Scavenge）：
新生代收集器，采复制算法。只能与Serial  Old配合使用。侧重于吞吐量的控制，可以设置最大垃圾收集停顿时间（-XX:MaxGCPauseMillis）、吞吐量大小（-XX:GCTimeRatio）。还可以打开智能开关（-XX:UseAdptiveSizePolicy），这样无需设置新生代大小和比例、晋升老年代对象年龄等参数，动态调节停顿时间和最大吞吐量。jdk1.7和 1.8默认垃圾回收器；

*问：Parallel Scavenge与ParNew都是并行多线程新生代收集器，为什么ParNew可以与CMS搭配使用，Parallel Scavenge就不行呢？*

*答：除了一个面向低延迟一个面向高吞吐量的目标不一致外，技术上的原因是Parallel Scavenge收集器及后面提到的G1收集器等都没有使用HotSpot中原本设计的垃圾收集器的分代框架，
**Parallel Scavenge收集器架构中本身有PS MarkSweep收集器来进行老年代收集**，
并非
直接调用Serial Old收集器，但是这个PS MarkSweep收集器与Serial Old的实现几乎是一样的，所以PS MarkSweep也可以当做Serial Old*

### 老年代垃圾回收器

##### 1）Serial Old收集器：
Serial老年代版本，单线程，使用“标记-整理”算法，供客户端模式下的HotSpot虚拟机使用；

##### 2）Parallel Old收集器：
Parallel Scavenge老年代版本，使用“标记-整理”算法，这个收集器是直到JDK 6时才开始提供的；

Parallel Scavenge/Parallel Old收集器配合使用的流程图：
![image](7985599CE56D4EA697C347827B2A9955)

##### 3）CMS收集器：
Concurrent Mark Sweep，使用标记-清除算法，JDK5发布，以获取最短回收停顿时间为目标的回收器。常用在B/S互联网服务端Java应用中，非常重视服务的响应速度。CMS垃圾回收线程与用户线程一起工作。

![image](D3CD896F59BB454C9A2770E7A0A759CE)

*问题：CMS只有标记和和清除的过程，没有整理的过程，那产生碎片化的内存空间怎么处理呢？*

*回答：做法是让虚拟机平时多数时间都采用标记-清除算法，暂时容忍内存碎片的存在，直到内存空间的碎片化程度已经大到影响对象分配时，再采用标记-整理算法收集一次，以获得规整的内存空间。（《深入理解JVM》-3.3.4 标记-整理算法）*

#### G1收集器：
面向服务端应用的垃圾收集器：将整个Java堆划分为多个大小相等的独立区域（Region），但新生代和老年代不再是物理隔离的了，而都是一部分Region（不需要连续）的集合，每一个Region都可以根据需要，扮演新生代的Eden空间、Survivor空间，或者老年代空间。

###### G1收集器发展史
- G1从JDK 6 Update 14开始就有EarlyAccess版本的G1收集器供开发人员实验和试用“实验状态”（Experimental）;
- 直至JDK 7 Update 4，Oracle才认为它达到足够成熟的商用程度，移除了“Experimental”的标识；
- 到了JDK 8 Update 40的时候，G1提供并发的类卸载的支持，补全了其计划功能的最后一块拼图;
- JDK 9发布之日，G1宣告取代Parallel Scavenge加Parallel Old组合，成为服务端模式下的默认垃圾收集器，而CMS则
沦落至被声明为不推荐使用（Deprecate）的收集器;

###### G1收集器设计思想
不在针对堆的收集其中的某一个区域（新生代Minor GC或者老年代Major GC或者整个堆Full GC）,它面向内存任何区域组成需要回收的回收集（Collection Set，简称CSet），衡量标准不在是垃圾对象属于某个分代，而是哪块内存中存放的垃圾数量最多，回收受益最大，这就是G1收集器的Mixed GC模式。

###### G1收集器运行示意图
![image](A83B67D78A3443FAA86B0782859F4E22)

###### 为G1收集器指定合理的期望停顿时间
使用参数-XX：MaxGCPauseMillis指定，默认值是200毫秒。一般来说，回收阶段占到几十到一百甚至接近两百毫秒都很正常，但如果我们把停顿时间调得非常低，譬如设置为二十毫秒，很可能出现的结果就是由于停顿目标时间太短，导致每次选出来的回收集只占堆内存很小的一部分，收集器收集的速度逐渐跟不上分配器分配的速度，导致垃圾慢慢堆积。很可能一开始收集器还能从空闲的堆内存中获得一些喘息的时间，但应用运行时间一长就不行了，最终占满堆引发Full GC反而降低性能，所以通常把期望停顿时间设置为**一两百毫秒或者两三百毫秒**会是比较合理的。

###### G1与CMS对比
优势：

- 可以指定最大停顿时间。
- 局部回收，优先处理回收价值最大的Region。
- G1运作期间不会产生内存空间碎片，垃圾收集完成之后能提供规整的可用内存。这种特性有利于程序长时间运行。

缺点：

- 内存占用较高：G1将堆内存划分为许多Region，使用更加复杂的记忆集，每个Region都维护自己的卡表，导致G1占用更多的内存；
- CPU负载占用较高：G1需要维护复杂的记忆集，并且使用了写前屏障和写后屏障，CMS只使用了写前屏障，因此G1消耗更多的运算资源。

###### G1的发展前景
按照实战经验，目前在小内存应用上CMS的表现大概率仍然要会优于G1，而在大内存应用上G1则大多能发挥其优势，这个优劣势的Java堆容量平衡点通常在6GB至8GB之间，当然，以上这些也仅是经验之谈，不同应用需要量体裁衣地实际测试才能得出最合适的结论，随着HotSpot的开发者对G1的不断优化，也会让对比结果继续向G1倾斜。

###### 自己对G1产生的问题
*问题：为什么G1算法要对Heap堆进行划分？*

*回答：G1对堆内存“化整为零”，划分为多个Region，将Region为最小的内存回收单元，每次回收的内存空间都是Region的整数倍，这样可以避免对整个堆内存进行垃圾回收。*

![image](8DBBFD4ABC2C4F86AF91F63B77B1E579)

#### Shenandoah收集器
最初Shenandoah是由RedHat公司独立发展的新型收集器项目，在2014年RedHat把Shenandoah贡献给了OpenJDK，并推动它成为OpenJDK12的正式特性之一。Shenandoah是一款只有OpenJDK才会包含，而OracleJDK里反而不存在的收集器。

Shenandoah收集器的目标是圾收集的停顿时间限制在10ms以内（RedHat官方在2016年实测中只达到50ms级别）。该目标意味着相比CMS和G1，Shenandoah不仅要进行并发的垃圾标记，还要并发地进行对象清理后的整理作。

##### Shenandoah收集器与G1相比的特点：
1. 最重要的当然是支持并发的整理算法。G1的回收阶段是可以多线程并行的，但却不能与用户线程并发。
2. Shenandoah（目前）是默认不使用分代收集的。
3. Shenandoah摒弃了在G1中耗费大量内存和计算资源去维护的记忆集，改用名为“连接矩阵”（ConnectionMatrix）的全局数据结构来记录跨Region的引用关系。

连接矩阵：可以简单理解为二维矩阵
![image](16F55E0EE6D3413A85965FE756FBE421)

##### Shenandoah收集器九个阶段
1. 初始标记（Initial Marking）、**并发标记**（Concurrent Marking）、最终标记（Final Marking）：与G1完全相同。
2. 并发清理（Concurrent Cleanup）：这个阶段用于清理那些整个区域内连一个存活对象都没有找到的Region。
3. **并发回收**（Concurrent Evacuation）：Shenandoah要把回收集里面的存活对象并发复制一份到其他未被使用的Region之中。用户线程仍然可能不停对被移动的对象进行读写访问,Shenandoah将会通过读屏障和被称为“Brooks Pointers”的转发指针来解决收集线程来解决并发问题。
4. 初始引用更新（Initial Update Reference）：并发回收阶段复制对象结束后，还需要把堆中所有指向旧对象的引用修正到复制后的新地址，这个操作称为引用更新。引用更新的初始化阶段实际上并未做什么具体的处理，设立这个阶段只是为了建立一个线程集合点。初始引用更新时间很短，会产生一个非常短暂的停顿。
5. **并发引用更新**（Concurrent Update Reference）：真正开始进行引用更新操作，将堆中所有指向旧对象的引用修正到复制后的新地址，这个阶段是与用户线程一起并发的。
6. 最终引用更新（Final Update Reference）：解决了堆中的引用更新后，还要修正存在于GC Roots中的引用。这个阶段是Shenandoah的最后一次停顿，停顿时间只与GC Roots的数量相关。
7. 并发清理（Concurrent Cleanup）：清理经过并发回收和引用更新之后，整个回收集中所有的已再无存活对象的Region。
![image](7B9B92EDCA124747A95958445A2DF4F1)

RedHat官方在2016年实测数据
![image](563437A8237445C980D98AE73CE6949E)

#### ZGC收集器
ZGC是一款在JDK 11中新加入的具有实验性质的低延迟垃圾收集器，是由Oracle公司研发的。2018年Oracle创建了JEP 333将ZGC提交给OpenJDK，推动其进入OpenJDK11的发布清单之中。在JDK 15从体验版本修改为正式版（JEP 377：http://openjdk.java.net/jeps/377）。

ZGC收集器是一款基于Region内存布局的，（暂时）
不设分代的，使用了读屏障、染色指针和内存多重映射等技术来实现可并发的标记-整理算法的，以低延迟（10ms）为首要目标的一款垃圾收集器。

##### 内存布局
在x64硬件平台下，ZGC的Region可以具有大、中、小三类容量
![image](F66CE0261E2F4C71AEA724ECFADE5FF0)

##### 染色指针（Colored Pointer）
ZGC的染色指针是最直接的、最纯粹的，它直接把标记信息记在引用对象的指针上。ZGC能够管理的内存不可以超过4TB（2的42次幂）
![image](6C1A7DADDFB5434D89208B45BEDE7E7A)

##### ZGC收集过程
![image](40FAF5D4C74742AAAE38328A44371601)

并发标记 -> 并发预备重分配 -> 并发重分配 -> 并发重映射（与并发标记阶段合并）

##### 如何开启ZGC
JDK15之后 ZGC成为正式版本

开启 ZGC: -XX:+UseZGC

调整堆参数：
- –If you care about latency, do not overprovision your machine
- –Max heap size: -Xmx<size>
- –Number of concurrent GC threads: -XX:ConcGCThreads=<number>

在JDK 9之前使用-XX：+PrintGCDetails，JDK9之后使用-Xlog:gc开启日志

#### 各垃圾回收器对比

浅灰色表示需要Stop The Word，不能与用户线程并发执行
![image](8C071C144E154BDABE66F3A6DA69D03E)

##### 吞吐量测试
![image](05D8D616F65C4303BEDCF34C3FD148F8)

##### 收集停顿时间测试
![image](306E12C5566E4EBBA1134C9B623209F1)

##### jdk默认垃圾收集器

jdk7 默认垃圾收集器Parallel Scavenge（新生代）+Serial Old（老年代）

jdk8 默认垃圾收集器Parallel Scavenge（新生代）+Serial Old（老年代）

jdk9~jdk15 jdk9开始G1成为默认的收集器，jdk11 ZGC作为体验版收集器支持Linux平台，JDK15 ZGC作为正式版收集器支持全平台

##### 选择收集器
小内存服务器，比如流行的微服务，使用的堆内存在4G~8G，推荐使用CMS收集器。更大的内存服务器，可以使用G1或者ZGC。

### JVM监控、故障处理

##### jps 虚拟机进程状况工具
jps -l 可以列出正在运行的虚拟机进程<br/>
-v 输出虚拟机进程启动时的JVM 参数

##### jinfo Java配置信息
查看未被显式指定的参数的系统默认值

java -XX:+PrintFlagsFinal 查看所有默认的JVM参数

查看某一进程的某个参数值，例：

jinfo -flag CMSInitiatingOccupancyFraction 1444

##### jstat 虚拟机统计信息监视工具
jstat -gcutil 1288 1s 100

![image](5227052972BE465F91AD9F64BE6FA9E9)

S0：幸存1区当前使用比例<br/>
S1：幸存2区当前使用比例<br/>
E：伊甸园区使用比例<br/>
O：老年代使用比例<br/>
M：元数据区使用比例 (jdk7之前是P表示永久代)<br/>
CCS：压缩使用比例<br/>
YGC：年轻代垃圾回收次数<br/>
FGC：老年代垃圾回收次数<br/>
FGCT：老年代垃圾回收消耗时间<br/>
GCT：垃圾回收消耗总时间<br/>

##### jmap 查看java内存
jmap主要可以用于打印Java进程的内存映射或堆内存（Heap Dump文件）细节。（如：产生哪些对象，以及数量等）。主要是用在检查内存泄漏、一些严重影响性能的大对象，检查系统中什么对象创建的最多，分析各种对象所占用的大小等。

https://www.cnblogs.com/boothsun/p/8027297.html

0x000001707ce00000

#查看对象所在jvm位置
scanoops 0x000001e6f9200000 0x000001e6f9c00000 org.fenixsoft.jvm.chapter4.JHSDBTestCase$ObjectHolder

##### jstack 线程快照
jstack pid

### 调优案例
##### 美团GC优化案例：
https://tech.meituan.com/2017/12/29/jvm-optimize.html

##### 《深入理解JVM》项目优化案例：
###### 1.在线文档类型网站，项目部署单机大内存服务器，产生大量的老年代对象，频繁发生Full GC
书中给出的优化：单机器部署多个resion实例，利用apache负载均衡，利用sessionId将用户固定在某一个台服务器。

我的思考：大对象且长期存驻内存，且修改不频繁，可以启动一个缓存服务例如redis或者memcache缓存大对象。在启动多个resion实例，老年代可以适当调大，这些java进程读取缓存中的数据。后台更新时主动更新缓存。

### 类文件结构

#### ClassFile结构
![image](232DF2EFDC324282A58637973A1D3B04)
- magic （ 魔数） 的唯一作用是确定这个文件是否为一个能被虚拟机所接受的酗a s s 文件。魔
数值固定为0xCAFEBABE ， 不会改变。
- minor version （ 副版本号） 、major_version （ 主版本号）。
- constant_pool_count （ 常量池计数器），constant_pool []（ 常量池）。
- access_flags （ 访问标志）是一种由标志所构成的掩码，用于表示某个类或者接口的访问权限及属性。（具体可以查看《Java虚拟机规范》）。
- this class （ 类索引），super_class （ 父类索引）。
- interfaces_count, interfaces[] 接口索引
- fields_count, fields[] 字段表，用于描述接口或者类中声明的变量。Java语言中的“字段”（Field）包括类级变量以及实例级变量，但不包括在方法内部声明的局部变量。
- methods_count, methods[] 方法表，当前类或者接口中的方法。
- attributes, 属性表,在前面的讲解之中已经出现过数次，Class文件、字段表、方法表都可以携带自己的属性表集合，以描述某些场景专有的信息.

#### 字节码指令
Java虚拟机的字节码指令由一个字节长度的、代表着某种特定操作含义的数字（称为操作码，Opcode）以及跟随其后的零至多个代表此操作所需的参数（称为操作数，Operand）构成。例如：bipush 6 将常量值6推向栈顶。Java虚拟机操作码的长度为一个字节（即0～255），这意味着指令集的操作码总数不能够超过256条。
![image](06817CB84E0A4C0CBA6966A7434DDC41)

### 第七章 JVM类加载机制

#### 类加载时机

######  类文件在JVM执行顺序
![image](36E0B216A218441CACB212219D48A5FE)

除了解析、卸载过程，加载、验证、准备、初始化、使用这5个过程一定是按顺序执行的。

###### 初始化时机
《Java虚拟机规范》
则是严格规定了有且只有六种情况必须立即对类进行“初始化”（而加载、验证、准备自然需要在此之前开始）

1. 遇到new、getstatic、putstatic或invokestatic这四条字节码指令时。

    - 使用new关键字实例化对象时候。
    - 读取（getstatic）、设置（putstatic）静态字段时候。
    - 调用（invokestatic）静态方法时候。

2. 使用java.lang.reflect包的方法对类型进行反射调用的时候。    
3. 当初始化类的时候，如果发现其父类还没有进行过初始化，则需要先触发其父类的初始化。
4. 启动JVM时，初始化含有main入口方法的主类。
5. 当使用JDK 7新加入的动态语言支持时，如果一个java.lang.invoke.MethodHandle实例最后的解析结果为REF_getStatic、REF_putStatic、REF_invokeStatic、REF_newInvokeSpecial四种类型的方法句柄，并且这个方法句柄对应的类没有进行过初始化，则需要先触发其初始化。
6. 当一个接口中定义了JDK8新加入的默认方法（被default关键字修饰的接口方法）时，如果有这个接口的实现类发生了初始化，那该接口要在其之前被初始化。

###### 被动引用
以下3种被动引用，不会触发类的初始化

- 子类继承了父类的静态字段，通过子类读取(getstatic)这个静态字段，不会触发子类的初始化，只会触发父类的初始化。
- 通过数组定义来引用类，不会触发此类的初始化。例如：SuperClass[] sca = new SuperClass[10]。
- 常量在编译阶段会存入调用类的常量池中，本质上没有直接引用到定义常量的类，因此不会触发定义常量的类的初始化。

#### 类加载过程

##### 加载
加载阶段，JVM需要完成3件事
- 通过类全名获得此类的二进制字节流。可以从Class文件读取，也可以从war，jar等压缩包读取，也可以从网络读取，动态代理生成，其他文件例如JSP、txt等生成对应的class文件，数据库读取，从加密的Class文件获取。
- 将这个字节流按照JVM定义的数据结构存储在方法区。
- 在Java堆内存中实例化一个java.lang.Class类的对象，这个对象将作为程序访问方法区中的类型数据的外部接口

读取Class文件转换为二进制字节流，将字节流转化为JVM定义的数据结构，按照这个数据结构生成对应的Class对象。

##### 验证
目的在于确保Class文件的字节流中包含信息符合当前虚拟机要求，不会危害虚拟机自身安全。主要包括四种验证，文件格式验证，元数据验证，字节码验证，符号引用验证。

- 文件格式验证，验证字节流是否符合Class文件格式规范，例如，验证魔数、版本号、常量池、索引值、CONSTANT_Utf8_info编码、Class文件本身是否被删除或者附加其他信息。
- 元数据验证，对类的元数据信息进行语义校验，保证不存在与《Java语言规范》定义相悖的元数据信息。例如，检查是否有父类（Object类除外），非抽象类是否实现了父类中的所有方法，类中的字段、方法是否与父类产生矛盾。
- 字节码验证，这阶段就要对类的方法体（Class文件中的Code属性）进行校验分析，确定程序语义是合法的、符合逻辑的。
- 符号引用验证，发生在解析阶段。检查该类是否缺少或者被禁止访问它依赖的某些外部类、方法、字段等资源。

##### 准备
准备阶段是正式为类中定义的类变量（即静态变量，被static修饰的变量）分配内存并设置类变量初始0值。

##### 解析
将常量池总的类引用CONSTANT_Class_info、字段引用CONSTANT_Fieldref_info、方法引用CONSTANT_Methodref_info，这些符号引用替换为直接引用。

##### 初始化
初始化阶段就是执行类构造器<clinit>()方法的过程，<clinit>()方法是由编译器自动收集类中的所有类变量的赋值动作和静态语句块（static{}块）中的语句合并产生的。也就是说初始化是对类变量赋值操作和执行静态语句块。

#### 类加载器
加载阶段“通过类的类全限定名获取该类的二进制字节流”这个动作，是通过类加载器（Classloader）来实现的。

###### 类与类加载器
两个类即使类全名相同，如果加载他们的ClassLoader不同，那么他们也不是同一个类。
###### 双亲委派
- BootstrapCLassloader：启动类加载器，处于类加载器层次结构的最高层，负责 sun.boot.class.path 路径下类的加载，默认为 <JAVA_HOME>/jre/lib 目录下的核心 API 或 -Xbootclasspath 选项指定的 jar 包，比如rt.jar。在HotSpot中它是通过C++实现的。

- ExtClassLoader：扩展类加载器，实现类sun.misc.Launcher$ExtClassLoader，加载 <JAVA_HOME>/libjre/lib/ext 目录或者 -Djava.ext.dirs 指定目录下的 jar 包加载。JDK的开发团队允许用户将具有通用性的类库放置在ext目录里以扩展Java SE的功能。

java.ext.dirs=<JAVA_HOME>/libjre/lib/ext是启动时JVM的参数
```
    private static File[] getExtDirs() {
        //加载<JAVA_HOME>/lib/ext目录中的类库
        String s = System.getProperty("java.ext.dirs");
        ...
    }

```

- AppClassloader：应用程序类加载器，它负责加载用户类路径
（ClassPath）上所有的类库，开发者同样可以直接在代码中使用这个类加载器。如果应用程序中没有自定义过自己的类加载器，一般情况下这个就是程序中默认的类加载器。通过ClassLoader#getSystemClassLoader()方法可以获取到该类加载器。

######  双亲委派模型的工作过程是：
如果一个类加载器收到了类加载的请求，它首先不会自己去尝试加载这个类，而是把这个请求委派给父类加载器去完成。只有当父加载器反馈自己无法完成这个加载请求，才会交给子加载器处理。
![image](984D709DBC394841B78040F47D63BA58)

代码层面关系,Launcher()构造方法时，AppClassLoader设置parent为ExtClassLoader
![image](EC342F6BCA6A42BEA4728037A4719A7F)

###### 双亲委派好处
保证同一个类由相同的类加载器加载，从而保证加载出的class对象是同一个

###### Java 9模块化下ClassLoader的修改
Java 9仍然保留了三层类加载器结构，不过为了支持模块系统，对它们做了一些调整。扩展机制被移除，扩展类加载器由于向后兼容性的原因被保留，不过被重命名为平台类加载器（platform class loader）。可以通过ClassLoader的新方法getPlatformClassLoader()来获取。Java 9中的内置类加载器如下所示。
- 引导类加载器：定义核心Java SE和JDK模块。
- 平台类加载器(PlatformClassLoader)：定义部分Java SE和JDK模块。
- 应用或系统类加载器：定义CLASSPATH上的类和模块路径中的模块。

AppClassLoader和PlatformClassLoader 都继承自BuiltinClassLoader， BuiltinClassLoader.loadClass()先判断该类是否能
够归属到某一个系统模块中，如果可以找到这样的归属关系，就要优先委派给负责那个模块的加载器完成加载，也许这可以算是对双亲委派的第四次破坏。比如AppClassLoader当遇到需要加载java.base.jmod模块的类，就会委派给BootstrapClassLoader加载。
![image](5043C2AFA7CB4BBD865E3273689241B4)

###### ClassLoader中重要的方法

- loadClass()：先调用findLoadedClass从缓存中查找是否已经加载，如果已加载，则直接返回Class对象；如果未加载，则调用父类ClassLoader的loadClass递归加载，如果一直未找到，则调用findClass方法加载。
- findClass()：自定义类加载器覆盖这个方法，通常这个方法和defineClass一起使用。
- defineClass()： ClassLoader定义的方法，用来将byte字节流解析成JVM能够识别的Class对象，一般用findClass()获取byte字节流，在调用super.defineClass()。

###### 线程上下文类加载器 - 破坏了双亲委托

https://blog.csdn.net/javazejian/article/details/73413292
https://blog.csdn.net/yangcheng33/article/details/52631940

Thread.getContextClassLoader() 与Thread.setContextClassLoader()方法来获取和设置线程的上下文类加载器.

- 在Java应用中存在着很多服务提供者接口（Service Provider Interface，SPI），这些接口允许第三方为它们提供实现，如常见的 SPI 有 JDBC、JNDI等，这些 SPI 的接口属于 Java 核心库，一般存在rt.jar包中，由Bootstrap类加载器加载，而 SPI 的第三方实现代码则是作为Java应用所依赖的 jar 包被存放在classpath路径下，由于SPI接口中的代码经常需要加载具体的第三方实现类并调用其相关方法，但SPI的核心接口类是由引导类加载器来加载的，而Bootstrap类加载器无法直接加载SPI的实现类，同时由于双亲委派模式的存在，Bootstrap类加载器也无法反向委托AppClassLoader加载器SPI的实现类。在这种情况下，我们就需要一种特殊的类加载器来加载第三方的类库，而线程上下文类加载器就是很好的选择。创建数据库连接，是通过rt.jar核心类DriverManager方法建立的。loadInitialDrivers()方法最终是调用ServiceLoader类的hasNextService()和nextService()方法，找到META-INF/services/目录下的Driver实现类名称com.mysql.cj.jdbc.Driver，Thread.getContextClassLoader()获取ClassLoader，再调用Class.forName(driverName, false,loader)加载Driver实现类。

- web容器比如Tomcat启动时，会将WebAppClassLoader通过Thread.setContextClassLoader中，spring的ContextLoaderListener最终通过ContextLoader初始化WebApplicationContext ，determineContextClass()方法中决定Context的Class对象的过程是调用Thread.getContextClassLoader来ClassUtils.forName()实现的。

- 如果将spring相关的jar放入到tomcat容器的common或者shared目录，ContextLoader也是通过Thread.getContextClassLoader获得ClassLoader，最终用ClassLoader作为key，Context最为value，这样spring就可区分是哪个webapp的ClassLoader。

###### Tomcat的类加载器
Common类加载器、Catalina类加载器（也称为Server类
加载器）、Shared类加载器和Webapp类加载器则是Tomcat自己定义的类加载器，它们分别加载/common/*、/server/*、/shared/*和/WebApp/WEB-INF/*中的Java类库。其中WebApp类加载器和JSP类加载器通常还会存在多个实例，每一个Web应用程序对应一个WebApp类加载器，每一个JSP文件对应一个JasperLoader类加载器。

![image](5EA1E2E3042442CD8486F01EE574B8D5)

###### GroovyClassLoader分析 TODO

### 第八章 虚拟机字节码执行引擎

#### 动态类型语言支持
JDK 7时新加入invokedynamic指令和java.lang.invoke包，提供一种新的动态确定目标方法的机制。

#### 方法调用

##### 方式一，解析：
调用目标在程序代码写好、编译器进行编译那一刻就已经确定下来。这类方法
的调用被称为解析（Resolution）。比如静态方法、私有方法、构造方法、final修饰的方法、父类的方法（不包括重写父类方法）。查看字节码，invokestatic和invokespecial指令调用的方法是解析调用方式。

##### 方式二，分派

###### 静态分派：
有依赖静态类型来决定方法执行版本的分派动作，都称为静态分派。静态分派的最典型应用表现就是方法重载。重载调用方法完全取决于传入参数的数量和数据类型。
当重载的方法参数数量相同，参数又是父子关系，虚拟机（或者准确地说是编译器）在**重载时是通过参数的静态类型而不是实际类型**作为判定依据的（也可以说是根据形参的类型，而不是根据实参的类型调用）。查看字节码，是通过invokevirtual指令码调用的。


```
public class StaticDispatch {

    static abstract class Human {
    }

    static class Man extends Human {
    }

    static class Woman extends Human {
    }

    public void sayHello(Human guy) {
        System.out.println("hello,guy!");
    }

    public void sayHello(Man guy) {
        System.out.println("hello,gentleman!");
    }

    public void sayHello(Woman guy) {
        System.out.println("hello,lady!");
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        StaticDispatch sr = new StaticDispatch();
        sr.sayHello(man);
        sr.sayHello(woman);
    }
    
    /**
     * hello,guy!
     * hello,guy!
     */
}
```


###### 动态分派
动态分派的典型应用是多态性特性之一的重写（Override）。是通过invokevirtual指令实现的。

决定调用哪个重写的方法的执行步骤：

1. 找到形参引用的对象的实际类型C（形参很可能为父类引用类型）；
2. 如果在类型C中找到与调用父类的方法名一样的方法，则调用该方法（而不是调用父类的方法）；
3. 如果未找到，则按照继承关系从下至上依次对C的各个父类进行第二次的搜索；
4. 如果始终未找到，则抛出java.lang.AbstractMethodError异常。

```
public class DynamicDispatch {

    static abstract class Human {
        protected abstract void sayHello();
    }

    static class Man extends Human {
        @Override
        protected void sayHello() {
            System.out.println("man say hello");
        }
    }

    static class Woman extends Human {
        @Override
        protected void sayHello() {
            System.out.println("woman say hello");
        }
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        man.sayHello();
        woman.sayHello();
        man = new Woman();
        man.sayHello();
    }
}
```

方法具有多态性，**字段没有多态性**。当子类与父类含有相同的字段，使用父类的引用获取public字段，则得到的是父类的字段值，而不是子类的字段值。如果是用get方法获取字段方式除外，因为方法具有多态。

```
public class FieldHasNoPolymorphic2 {

    static class Father {
        public int money = 1;

        public int getMoney() {
            return money;
        }
    }

    static class Son extends Father {
        public int money = 3;

        @Override
        public int getMoney() {
            return money;
        }
    }

    public static void main(String[] args) {
        Father gay = new Son();
        System.out.println("This gay has $" + gay.money);
        System.out.println("This gay has $" + gay.getMoney());
    }

    /**
     * This gay has $1
     * This gay has $3
     */
}
```

#### 8.4 动态类型语言支持

##### 8.4.1 动态类型语言
- 静态语言：在编译时就确定变量类型，通过符号引用（比如Java的字节码）已经能确定方法定义在哪个具体类型之中（比如多态）、方法的名字以及参数顺序、参数类型和方法返回值等信息。比如Java，C++
- 动态语言：在运行期才确定变量类型，是否含有某个方法。

以下代码，如果是Java，需要在编译之前就需要obj的具体类型（少时是父类或者接口类型），是否含有println方法。而PHP只需要在运行时，obj中含有println函数就行。
```
obj.println("hello world");
```

##### 8.4.3 java.lang.invoke包
动态语言能实现以下代码，直接将fun函数当做参数传递，实现动态调用。
```
void sort(list[], fun(int, int){ doSomeThing();})
```

java要做到这一点则需要创建一个类（或者是匿名内部内）实现Comparator接口，将对象传入到方法中。也就是在编译阶段就必须确定Comparator内部的方法。
```
void sort(list, comparator);
```

JDK7提供了动态确定目标方法的机制，称为“方法句柄”（MethodHandle）。由于MethodHandle是对字节码的方法指令invokedynamic调用的模拟，那理论上虚拟机在这方面做的各种优化（如方法内联），在MethodHandle上也应当可以采用类似思路去支持

##### 8.4.4 invokedynamic指令
invokedynamic指令在字节码指令实现动态方法调用。


#### 字节码解释执行引擎

##### 各语言编译和执行步骤
- Java：编译器编译java代码成为class字节码，执行引擎根据字节码执行字节码指令。Java的编译器是半独立的；
- C/C++ : 编译器、目标代码生成器成为一体，可以完全不用关心目标代码如何执行；
- JavaScript： 编译器、执行引擎全部集中封装在一起；
![image](59178DC7915F40ABA84317723D8F4235)

##### 指令集架构（Instruction Set Architecture，ISA）
栈的指令集架构：Javac编译器输出的字节码指令流，基本上是一种基于栈的指令集架构。<br/>
寄存器的指令集架构：主流的PC物理机都是采用寄存器指令集架构，比如x86，ARM。

栈指令集架构优缺点：
- 优点主要是可移植，跨物理平台；编译器实现更加简单，比如JVM字节码指令只需与操作数栈、程序计数器打交道，不需要直接与物理机的寄存机打交道。
- 缺点是理论上执行速度相对来说会稍慢一些

### 第九章 类加载与执行子系统

#### 动态代理

- jdk动态代理：
Proxy::newProxyInstance()方法实现动态代理，最终调用sun.misc.ProxyGenerator::generateProxyClass()方法来完成生成字节码。JDK动态代理目标对象一定要实现接口。

- cglib动态代理：
enhancer.create()方法实现cglib动态代理。目标对象不需要实现接口。

- spring aop动态代理：默认有接口的类使用jdk动态代理，无接口类使用cglib动态代理。<aop:aspectj-autoproxy proxy-target-class="true"/>开启cglib，无论是否实现接口，都是用cglib动态代理。

#### BackPort反向移植
- Retrotranslator工具可以将将JDK 5编译出来的Class文件转变为可以在JDK 1.4或1.3上部署的版本。
- Retrolambda工具将JDK 8的Lambda表达式和try-resources语法转变为可以在JDK
5、JDK 6、JDK 7中使用的形式

#### 热部署方案
1）手动上传class + 自定义类加载器 +jsp执行。
自定义类加载器，上传更新之后真正需要执行的class文件，jsp调用自定义ClassLoader加载class对象，在通过反射调用class对象对应的method方法；

2）消息队列 + GroovyClassLoader + Spring。
消息队列获取最新的java文本信息，GroovyClassLoader编译并且加载class对象，将class对象交给spring统一管理。

### 第十章 程序编译与优化

#### 10.2 Javac编译器

##### 10.2.1  Javac源码与调试

###### OpenJDK8下载方法：

- openjdk源码仓库地址 http://hg.openjdk.java.net/。
- 下载源码版本。例如我的本地安装的是RedHat官网下载的java-1.8.0-openjdk-1.8.0.282-1.b08，在openjdk源码仓库找到jdk8u，看到有各种分支，选择从Master分支（jdk8u不带分支名的就是Master分支）点击langtools（其他的几个目录的作用可以参见https://blog.csdn.net/baichoufei90/article/details/85055655），点击左上方的tags进入标签页面，点击jdk8u282-b08（自己选择对应的版本就好）进入页面，点击左边的zip开始下载。
- 剩下步骤可以参考 https://blog.csdn.net/u013490280/article/details/108440023

###### Javac编译过程
![image](94603AE43F2C46159672989A34F0D2AD)

![image](E04CCA3274934AD3803FE12CB11E1156)

#### 10.3 Java语法糖

##### 10.3.1 泛型

###### Java与C#泛型
Java选择的泛型实现方式叫作“类型擦除式泛型”（Type Erasure Generics），而C#选择的泛型实现方式是“具现化式泛型”（Reified Generics）。

Java直接把已有的类型泛型化，即让所有需要泛型化的已有类型都原地泛型化，不添加任何平行于已有类型的泛型版，譬如ArrayList，原地泛型化后变成
了ArrayList<T>。而且保证以前直接用ArrayList的代码在泛型新版本里必须还能继续用这同一个容器，这就必须让所有泛型化的实例类型，譬如ArrayList<Integer>、ArrayList<String>这些全部自动成为ArrayList的子类型才能可以，否则类型转换就是不安全的。

而C#以前有的就保持不变，然后平行地加一套泛型化版本
的新类型，添加了一组System.Collections.Generic的新容器，以前的
System.Collections以及System.Collections.Specialized容器类型继续存在。

###### Java 类型擦除式泛型弊端：
- 效率低下。对于Java来说所有泛型编译之后都会变成原生类型，ArrayList<String>编译后字节码擦除泛型，最终变成原来的ArrayList裸类型。获取ArrayList中的值，还需要做类型转换。所以无论在使用效果上还是运行效率上，几乎是全面落后于C#的具现化式泛型，泛型成为Java语言不如C#语言好用的“铁证”被众人嘲讽。
- 无法支持原生类型。比如ArrayList<int>。把泛型信息擦除后，到要插入强制转型代码的地方就没办法往下做了，因为不支持int、long与Object之间的强制转型。
- 运行期无法取到泛型类型信息。无法对泛型<T> 进行instanceof实例类型判定，也无法对T进行实例化，无法使用泛型创建数组。一些代码还会变得相当啰嗦，比如List<T> 转换为T[]数组，Array.newInstance(componentType, list.size())还得传入Class<T> componentType参数。
- 不支持泛型方法的重载。void method(List<String> list)与method(List<Integer> list)被认为是同一个方法。

###### 值类型与未来的泛型
在2014年，Oracle建立了一个名为Valhalla的语言改进项目。在Valhalla项目中规划了几种不同的新泛型实现方案，被称为Model 1到Model 3，在这些新的泛型
设计中，泛型类型有可能被具现化，也有可能继续维持类型擦除以保持兼容。不过目前方案还在候选中。

Valhalla项目wiki：https://wiki.openjdk.java.net/display/valhalla/Main

###### 10.3.2 自动装箱、拆箱与循环
- 自动装箱：字节码中对应的是调用Integer.valueOf()。
- 自动拆箱：字节码中对应的是调用Integer.intValue()。
- 遍历循环则是把代码还原成了迭代器的实现，这也是为何遍历循环需要被遍历的类实现Iterable接口的原因。比如for(int i : List)，字节码最终变成Iterator迭代。

#### 10.4 插入式注解处理器
在JDK 6中又提出并通过了JSR-269提案 [1] ，该提案设
计了一组被称为“插入式注解处理器”的标准API - AbstractProcessor，允许读取、修改、添加抽象语法树中的任意元素。

比较有名的项目：Lombok， Hibernate Validator

#### 10.5 插入式注解与字节码生成技术
- AbstractProcessor插入式注解是在前端编译器编译源码过程中，生成相应的语法树，最终生成相应的字节码。

- 字节码生成技术例如JDK动态代理、CGlib、Spring AOP，直接按照字节码规范生成可以执行的字节码二进制class文件

### 第十一章 后端编译与优化

#### 11.2 即时编译器

##### 11.2.1 解释器与编译器两者各有优势：

- 当程序需要迅速启动和执行的时候，解释器可以首先发挥作用，省去编译的时间，立即运行。
- 当程序启动后，随着时间的推移，编译器逐渐发挥作用，把越来越多的代码编译成本地代码，这样可以减少解释器的中间损耗，获得更高的执行效率。

我的理解：JVM是通过字节码文件执行程序，解释器一行行解释执行代码，如果多次执行，可以将字节码命令通过即时编译器编译成本地操作系统的机器码。当第二次执行时，可以提高执行效率。但是解释器在程序运行中需要占用资源

###### HotSpot中解释器与编译器
目前HotSpot中的内置了客户端编译器”（Client Compiler）和“服务端编译器”（Server Compiler），或者简称为C1编译器和
C2编译器。第三个是在JDK 10时才出现的、长期目标
是代替C2的Graal编译器。Oracle还创建了独立了项目GraalVM。

HotSpot虚拟机通常是采用解释器与其中
一个编译器直接搭配的方式工作，程序使用哪个编译器，只取决于虚拟机运行的模式，HotSpot虚拟机
会根据自身版本与宿主机器的硬件性能自动选择运行模式，用户也可以使用“-client”或“-server”参数去
强制指定虚拟机运行在客户端模式还是服务端模式。

HotSpot默认使用混合模式（Mixed Mode），参数“-Xint”强制虚拟机运行于“解释模
式”（Interpreted Mode），使用参数“-Xcomp”强制虚拟机运行于“编译模式”（Compiled Mode）。

##### 11.2.2 即时编译器编译（JIT，Just In Time）对象与触发条件
即时编译器编译的目标是“热点代码”：被多次调用的方法、被多次执行的循环体

空循环，循环内部do nothing，会被即时编译器优化掉。空循环在最终的本地代码里实际上是不会被执行的。

#### 11.3 提前编译器（AOT，Ahead Of Time ）
##### 11.3.1　提前编译的优劣得失

提前编译器两条技术路线：

1）做与传统C、C++ 在程序运行之前把程序代码编译成机器码的静态翻译工作；

劣势：利用即时编译器在程序运行之前提前编译指令码，但是这是占用原本可以用程序执行的时间。

2） 把原本即时编译器在运行时要做的编译工作提前做好并保存下来，下次运行到这些代码（譬如公共库代码在被同一台机器其他Java进程使用）时直接把它加载进来使用。

优势：本质是给即时编译器做缓存加速，去改善Java程序的启动时间，以
及需要一段时间预热后才能到达最高性能的问题。例如OpenJDK/OracleJDK 9中所带的Jaotc提前编译器（基于Graal编译器开发的，目前Jaotc只支
持G1和Parallel（PS+PS Old）两种垃圾收集器），Graal VM中的Substrate VM。

### 第十二章 Java内存模型与线程

#### 12.3 Java内存模型
Java内存模型：主要目的是定义程序中各种变量（能被线程共享的变量，实例变量、类变量,不包含局部变量和方法参数，因为后者是线程私有的）的访问规则，保证程序执行的原子性、可见性、有序性。

##### 12.3.1 主内存与工作内存

![image](493726BE611449C3815DD8C0F5403562)

我的理解：jvm执行引擎对实例变量、类变量进行访问时，将变量从堆中（实际对应的是物理内存）Load到工作内存（对应的是CPU的寄存器或者高速缓存），当Java线程（对应的是操作系统的线程）完成修改后，在从工作内存中Save到堆中。

![image](34248F15D7B04C40B61C1F9F140BE69B)

##### 12.3.3 volatile型变量的特殊规则

volatile两项特性：
- 保证被volatile修饰的共享变量对所有线程总数可见的。
- 禁止指令重排序优化。

###### 可见性
可见性就是指当一个线程修改了共享变量的值时，其他线程能够立即得知这个修改。volatile的特殊规则保证了新值能立即同步到主内存，以及每次使用前立即从主内存刷新。

###### 禁止指令重排
普通的变量仅会保证在该方法的执行过程中所有依赖赋值结果的地方都能获取到正确的结果，而不能保证变量赋值操作的顺序与程序代码中的执行顺序一致。<br/>

我的理解：即时编译器编译后的机器码很可能是无序的，机器码指令被分配到各cpu中执行。线程A内部机器码很可能是无序分配到多个cpu中执行，JMM可以保证在A线程内部最终得到的结果是顺序的（happens-before原则保证操作有序性），但是如果B线程中依赖了A线程中的变量，那么B线程很可能得到的是错误的结果。比如以下代码：

```
/**
如果initialized不用volatile修饰，很可能initialized = true先执行，loadConfig()还未执行时B代码已经执行了, 这时B线程readFromConfig()读取的就可能是空数据。

*/
volatile boolean initialized = false;
A{
    loadConfig();//加载配置文件
    initialized = true;
}

B{
   while(!initialized){
      sleep();
   }
   readFromConfig();//从已加载的配置文件中读取配置
}
```

###### 双锁检查单例模式（Double Check Lock，DCL）
```
public class Singleton {
    private volatile static Singleton instance;
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton(); //volatile使其他线程立即可见instance的值
                }
            }
        }
        return instance;
    }
}
```

如果instance未加volatile关键词修饰， instance = new Singleton()对应的机器码指令我们原本以为是按照1,2,3执行，那么对于即时编译器编译后的机器码指令，很可能是这样：
```
memory = allocate();　　// 1：分配对象的内存空间
instance = memory;　　// 3：设置instance指向刚分配的内存地址
// 注意，此时对象还没有被初始化！
ctorInstance(memory);　// 2：初始化对象
```

当多个线程都执行getInstance()方法时，A线程刚只完成了instance = memory为instance引用分配了内存地址，还未初始化对象。B线程判断instance!=null,就reture instance,但这时B将返回的是一个未初始化完成的对象。

加上了volatile关键词修饰, 禁止了指令重排。则可以得到正确的结果。
```
memory = allocate();　　// 1：分配对象的内存空间
ctorInstance(memory);　// 2：初始化对象
instance = memory;　　// 3：设置instance指向刚分配的内存地址
```

###### volatile性能
- volatile变量读操作的性能消耗与普通变量几乎没有什么差别，但是写操作则可能
会慢上一些，因为它需要在本地代码中插入许多内存屏障指令来保证处理器不发生乱序执行。
- volatile的同步机制的性能确实要优于锁
（使用synchronized关键字或java.util.concurrent包里面的锁），但是由于虚拟机对锁实行的许多消除和优化，使得我们很难确切地说volatile就会比synchronized快上多少。
- 我们在volatile与锁中选择的唯一判断依据仅仅是volatile的语义能否满足使用场景的需求

##### 12.3.5 原子性、可见性与有序性

###### 原子性（Atomicity）
Java内存模型来直接保证的原子性变量操作包括read、load、assign、use、store和write这六个。基本数据类型的访问、读写都是具备原子性的。<br/>
更大范围的原子性，Java内存模型还提供了lock和unlock操作来满足这种需求，monitorenter和monitorexit来隐式地使用这两个操作，对于程序员层面也就是synchronize关键字。

###### 可见性（Visibility）
可见性就是指当一个线程修改了共享变量的值时，其他线程能够立即得知这个修改。

- volatile的特殊规则保证了新值能立即同步到主内存，以及每次使用前立即从主内存刷新。
- synchronized同步块的可见性是由“对一个变量执行unlock操作之前，必须先把此变量同步回主内存中（执行store、write操作）”这条规则获得的。
- final基本数据类型变量一旦初始化之后，就不能被修改；final的引用类型的变量，则在对其初始化之后便不能再让其指向另一个对象。final修饰的变量对其他线程来说也就具有可见性，它们无须同步就能被其他线程正确访问。

###### 有序性（Ordering）
Java程序中天然的有序性可以总结为一句话：如果在本线程内观察，所有的操作都是有序的；如果在一个线程中观察另一个线程，所有的操作都是无序的。<br/>
有序性是通过valotile、synchronize和Happens-Before（先行发生）原则来保证的。

##### 12.3.6 Happens-Before（先行发生）原则
先行发生是Java内存模型中定义的两项操作之间的偏序关系，比如说操作A先行发生于操作B，其实就是说在发生操作B之前，操作A产生的影响能被操作B观察到。

- 程序次序规则（Program Order Rule）：在一个线程内，按照控制流顺序，书写在前面的操作先行发生于书写在后面的操作。控制流顺序要考虑分支、循环、异常等。
- 管程锁定规则（Monitor Lock Rule）：一个unlock操作先行发生于后面对同一个锁的lock操作。也就是对于同一个锁加锁的先决条件是这个锁已经被解锁。
- volatile变量规则（Volatile Variable Rule）：对一个volatile变量的写操作先行发生于后面对这个变量
的读操作。
- 线程启动规则（Thread Start Rule）：Thread对象start()方法先于线程中任何动作。
- 线程终止规则（Thread Termination Rule）：线程中的所有操作都先行发生于对此线程的终止检测，我们可以通过Thread::join()方法是否结束、Thread::isAlive()的返回值等手段检测线程是否已经终止执行。
- 线程中断规则（Thread Interruption Rule）：对线程interrupt()方法的调用先行发生于被中断线程的代码检测到中断事件的发生，可以通过Thread::interrupted()方法检测到是否有中断发生。
- 对象终结规则（Finalizer Rule）：一个对象的初始化完成（构造函数执行结束）先行发生于它的
finalize()方法的开始。
传递性（Transitivity）：如果操作A先行发生于操作B，操作B先行发生于操作C，那就可以得出操作A先行发生于操作C的结论。

时间先后顺序与先行发生原则之间基本没有因果关系，
所以我们衡量并发安全问题的时候不要受时间顺序的干扰，一切必须以先行发生原则为准。

#### 12.4 Java与线程
操作系统运行一个程序会创建一个进程，在一个进程里可以创建多个线程。线程是操作系统调用的最小单元

##### 12.4.1 线程的实现方式
- 内核线程实现（1：1实现）：程序使用内核线程（Kernel-Level Thread，KLT）支持的轻量级进程（Light
Weight Process，LWP）来实现线程，而每个轻量级进程都由一个内存线程支持。因此这种实现方式程序线程与内核线程是1:1对应关系。HotSpot线程使用此方式实现。
![image](76D4CBD2D28348DA9FE1B8599D56E900)

- 用户线程实现（1：N实现）：这里所指的用户级线程主要是创建在用户空间的线程库上，系统内核感受不到线程的实现方式。用户线程的建立、同步、销毁等在用户态中完成，不需要内核的介入。这种进程和用户线程（UT）之间1:N的关系称为一对多线程模型。Golang使用这种方式实现多线程。

- 用户线程加轻量级进程混合实现（N：M实现）：将内核线程与用户线程一
起使用的实现方式，被称为N：M实现。在这种混合实现下，既存在用户线程，也存在轻量级进程。

###### Java线程的实现

java天生就是多线程的程序。

```
public class MultiThread {

    public static void main(String[] args) {
        // 获取Java线程管理MXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        // 不需要获取同步的monitor和synchronizer信息，仅仅获取线程和线程堆栈信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        // 遍历线程信息，仅打印线程ID和线程名称信息
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("[" + threadInfo.getThreadId() + "] " + threadInfo.getThreadName());
        }

        /**
         * 运行结果
         * [4] Signal Dispatcher　// 分发处理发送给JVM信号的线程
         * [3] Finalizer　　　　// 调用对象finalize方法的线程
         * [2] Reference Handler // 清除Reference的线程
         * [1] main　　　　　// main线程，用户程序入口
         */
    }
}
```

###### 多线程带来的好处
  - 利用多核心cpu提高运行效率
  - 并发执行提高程序响应时间，比如异步消息队列

#### 创建、启动线程

- 创建：继承Thread覆盖run方法或者实现Runnable接口；
- 启动：调用start()方法，此方法调用native方法start0()启动线程, 最终JVM调用run()执行。

###### 线程优先级
线程优先级并不是一项稳定的调节手段，很显然因为主流虚拟机上的Java线程是被映射到系统的原生线程上来实现的，所以线程调度最终还是由操作系统说了算。例如，Windows平台的虚拟机中使用了除
THREAD_PRIORITY_IDLE之外的其余6种线程优先级，因此在Windows下设置线程优先级为1和2、3和4、6和7、8和9的效果是完全相同的。<br/>
![image](987565FC8C724A0DA213DBB289C4436D)
![image](698CFBD76868478289F039C19F2B470F)
还有其他情况让我们不能过于依赖线程优先级：优先级可能会被系统自行改变，例
如在Windows系统中存在一个叫“优先级推进器”的功能。因此，我们并不能在程序中通过优先级来完全准确判断一组状态都为Ready的线程将会先执行哪一个。

##### 12.4.3 线程状态

参考 https://blog.csdn.net/pange1991/article/details/53860651

- 1）New，初始化状态，调用start之前的状态；
- 2）Runnable， 运行状态（包含Ready和Running两种，Ready获取cpu时间片被调度转变为Running，Running由Thread.yield()或者时间片已到转变为Ready）;
- 3）Time_Wating，超时等待(Thread.sleep(10s)等)，在指定的时间自行返回
- 4）Waiting，无限等待（Object::wait(), LockSupport::part()，Thread::join()），需要被其他线程notify或者interuput
- 5）Blocked，阻塞，等待进入synchronize方法或者代码块，没有获取到锁。“阻塞”与“等待”区别，阻塞是等待获得互斥的排他锁，而等待是等待一段时间或者被唤醒。
- 6）Terminated，终止 

![clipboard](E5F959D2DFCC419AB706732577A4D57E)

几个方法的比较：
- Thread.sleep(long millis) 线程进入Time_Wating状态，等待或者被唤醒后会进入Ready状态，重新竞争CPU时间片；
- Thread.yeild() 线程让渡CPU时间片，线程进入Ready状态，重新竞争CPU时间片；
- thread.join()/thread.join(long millis) 在某个线程t中调用thread.join()方法，则线程t进入WAITING或者TIME_WAITING状态，直到thread执行完毕或者超时。实际上 join是基于wait实现;
- Object.wait() 当前线程内调用对象wait方法，当前线程进入WAITING状态，释放对象锁，并且线程进入monitor的_WaitSet。wait()方法必须在synchronize方法或者代码块内调用，否则会抛出IllegalMonitorStateException异常（因为wait方法之前必须获得对象锁，notify(),nofiyAll()也是如此）；举例：synchronize(obj){obj.wait()}; 或 public synchronized void set(){ wait(); }
- Object.notify() 随机唤醒一个在对象monitor监视器上WAITING的线程。notifyAll()唤醒所有线程；
- LockSupport.park(this) 当前线程进入WAITING状态，可以被unpark()方法唤醒；


***问题：thread.join方法中调用了wait方法，理论上应该是thread进入WAITING状态，为什么是main线程进入WAITING呢？**<br/>
**答案**：Object.wait()注释“造成当前线程等待”，这里的当前线程是指调用了thread.join()线程，也就是main线程。所以是main线程等待*

#### 12.5 Java与协程

##### 12.5.1　内核线程的局限
- 线程调度成本高昂。Java虚拟机线程，映射到操作系统上的线程天然的缺陷是切换、调度成本高昂，系统能容纳的线程数量也很有限。现在在每个请求本身的执行时间变得很短、数量变得很多的前提下，用户线程切换的开销甚至可能会接近用于计算本身的开销，这就会造成严重的浪费。

***问题：为什么内核线程调度切换起来成本就要更高？**<br/>
**回答**：内核线程的调度成本主要来自于用户态与核心态之间的状态转换，而这两种状态转换的开销主要来自于响应中断、保护和恢复执行现场的成本。这种保护和恢复现场的工作，免不了涉及一系列数据在各种寄存器、缓存中的来回拷贝，当然不可能是一种轻量级的操作。*

###### 协程的复苏
协程优势：协程会完整地做调用栈的保护、恢复工作，这部分由程序员完成，而不依赖操作系统对内核的调用。

###### Java线程与协程
如果不显式设置-Xss或-XX：ThreadStackSize，则在64位Linux上HotSpot的线程栈
容量默认是1MB，此外内核数据结构（Kernel Data Structures）还会额外消耗16KB内存。与之相对的，一个协程的栈通常在几百个字节到几KB之间，所以Java虚拟机里线程池容量达到两百就已经不算小了，而很多支持协程的应用中，同时并存的协程数量可数以十万计。

###### Java的纤程（Fiber）
OpenJDK在2018年创建了Loom项目，重新提供对用户线程的支持，但与过去的绿色线程不同，这些新功能不是为了取代当前基于操作系统的线程实现，而是会有两个并发编程模型在Java虚拟机中并存，可以在程序中同时使用。Loom项目目前仍然在进行当中，还没有明确的发布日期。

### 第13章 线程安全及锁优化

#### 13.2 线程安全
当多个线程同时访问一个对象时，不需要做额外的同步，调用这个对象的行为都可以获得正确的结果，那就称这个对象使线程安全的。

线程安全的代码：代码本身封装了所有必要的正确性保障手段（如互斥同步等），令调用者无须关心多线程下的调用问题，更无须自己实现任何措施来保证多线程环境下的正确调用。

##### 13.2.1 Java语言中的线程安全

###### 不可变
- 基础变量用final修饰表示不可变的，线程一定是安全的。
- 如果对象使不可变得，那么不仅需要对象需要增加final修饰，而且对象本身自行保证其行为不会对其状态产生任何影响才行。比如String类是不可变对象，不仅用final修饰，而且内部的char[]数组也是final的，用户调用它的substring()、replace()和concat()这些方法都不会影响它原来的值，只会返回一个新构造的字符串对象。类似的还有Integer、Long、Double等包装类型、BigInteger和BigDecimal等大数据类型。

###### 绝对线程安全
相对的线程安全，在一定环境的代码中，在做一次同步。
没有哪一个类脱离了逻辑环境能保证线程安全，即使有代价也是极其高昂的。

###### 相对线程安全
相对线程安全就是我们通常意义上所讲的线程安全，它需要保证对这个对象单次的操作是线程安全的，我们在调用的时候不需要进行额外的保障措施，但是对于一些特定顺序的连续用，就可能需要在调用端使用额外的同步手段来保证调用的正确性。例如StringBuffer，Vector等。


##### 13.2.2 线程安全实现方法

###### 互斥同步
1）synchronized重量级锁
- 被synchronized修饰的同步块对同一条线程来说是可重入的。这意味着同一线程反复进入同步块也不会出现自己把自己锁死的情况。
比如： synchronized A(){  B()  };   synchronized B(){ doSomeThing()}; 对于同一个对象，一条线程获得这个对象执行A和B方法，不会出现锁死的情况。

- 被synchronized修饰的同步块在持有锁的线程执行完毕并释放锁之前，会无条件地阻塞后面其他线程的进入。

2）ReentrantLock可重入锁
- 等待可中断。遇到执行非常长的代码块可中断。
- 提供非公平锁和公平锁，默认非公平锁。synchronized只有非公平锁。
- 锁绑定多个条件。是指一个ReentrantLock对象可以同时绑定多个Condition对象。

synchronized与ReentrantLock使用情况：<br/>
原则：推荐在synchronized与ReentrantLock都可满足需要时优先使用synchronized。因为：
- synchronized是在Java语法层面的同步，足够清晰，也足够简单。
- Lock应该确保在finally块中释放锁，否则一旦受同步保护的代码块中抛出异常，则有可能永远不会释放持有的锁，这一点必须由程序员自己来保证。而使用synchronized的话则可以由Java虚拟机来确保即使出现异常，锁也能被自动释放。
- JDK6针对synchronized做了优化，与Reentrant的性能基本上能够持平。Java虚拟机更容易针对synchronized来进行优化，因为Java虚拟机可以在线程和对象
的元数据中记录synchronized中锁的相关信息，而使用J.U.C中的Lock的话，Java虚拟机是很难得知具体哪些锁对象是由特定线程锁持有的。

###### 非阻塞同步
乐观并发策略的实现不再需要把线程阻塞挂起，因此这种同步操作被
称为非阻塞同步（Non-BlockingSynchronization），使用这种措施的代码也常被称为无锁（Lock-Free）编程。Java里最终暴露出来的是CAS操作。

硬件保证某些从语义上看起来需要多次操作的行为可以只通过一条处理器指令就能完成，这类指令常用的有：
- 测试并设置（Test-and-Set）；
- 获取并增加（Fetch-and-Increment）；
- 交换（Swap）；
- 比较并交换（Compare-and-Swap，下文称CAS）；
- 加载链接/条件储存（Load-Linked/Store-Conditional，下文称LL/SC）。

###### 无同步方案
线程本地存储（Thread Local Storage），主要使用ThreadLocal（本地线程变量）。


**Java中的非阻塞同步：CAS**（compareAndSwap）

参考 https://blog.csdn.net/javazejian/article/details/72772470

![image](A0744AE091C04FEBA4E4ECFF50BC724E)

 - ABA问题

假设这样一种场景，当第一个线程执行CAS(V,E,U)操作，在获取到当前变量V，准备修改为新值U前，另外两个线程已连续修改了两次变量V的值，使得该值又恢复为旧值，这样的话，我们就无法正确判断这个变量是否已被修改过，如下图

![image](9A43665CE64D43C6BDB6B0F903914A25)

解决方法 AtomicStampedReference。大部分情况下ABA问题不会影响程序并发的正确性，如果需要解决ABA问题，改用传统的互斥同步可能会比原子类更为高效。

#### synchronize实现原理与应用

https://blog.csdn.net/javazejian/article/details/72828483

https://tech.meituan.com/2018/11/15/java-lock.html

https://blog.csdn.net/lkforce/article/details/81128115

#####  1）用法
 
-  修饰方法，是对实例对象加锁，进入方法前要获得实例对象锁
-  修饰代码块，synchronize（xx），指定对象xx，是对xx加锁，进入代码块要获得xx的锁
-  修饰静态方法，是对当前class加锁，进入此静态方法前要获取class锁

##### 2）实现原理

在JVM中，对象在内存中的布局分为三块区域：对象头、实例数据和对齐填充。如下：

![image](F600B47B5BF5405B8FA0B00611BB65A2)

- 对象头：包含Mark Word（标记字段）和Klass Pointer（类型指针）两部分。
- 实例数据：对象的内部变量，父类属性。
- 填充数据：jvm要求对象起始地址必须是8字节的整数倍，不足则填充数据。

##### 对象头结构
分为Mark Word（标记字段）和Klass Pointer（类型指针）两部分。类型指针指向方法区中对象对应的类元数据地址（Class Metadata Address）

![image](7C1C5BDCC48D435D866C9C9BB7F15E4C)

###### Mark Word结构，结构随着锁的升级结构和存储的数据也不同
![image](D12A938129AB435A88C0A7A3F16A01A8)

###### Klass Point：对象指向它的类元数据的指针，虚拟机通过这个指针来确定这个对象是哪个类的实例。使用JHSDB工具可以查看对象内容。
![image](7A2D8FA3261A4C4FA0D89CE1E11B4B16)

##### synchronized实现同步过程

当synchronized的对象锁，锁标识位为10，为重量级锁，其中指针指向的是monitor对象（也称为管程或监视器锁）的起始地址

![image](2E789BE37691433691BF36FE5D8EDC6D)

monitor是由ObjectMonitor实现的，其主要数据结构如下（位于HotSpot虚拟机源码ObjectMonitor.hpp文件，C++实现的）
```
ObjectMonitor() {
    _header       = NULL;
    _count        = 0; //记录个数
    _waiters      = 0,
    _recursions   = 0;
    _object       = NULL;
    _owner        = NULL;
    _WaitSet      = NULL; //处于wait状态的线程，会被加入到_WaitSet
    _WaitSetLock  = 0 ;
    _Responsible  = NULL ;
    _succ         = NULL ;
    _cxq          = NULL ;
    FreeNext      = NULL ;
    _EntryList    = NULL ; //处于等待锁block状态的线程，会被加入到该列表
    _SpinFreq     = 0 ;
    _SpinClock    = 0 ;
    OwnerIsThread = 0 ;
  }
```

3) 线程同步过程

 当一个线程执行到synchronize关键字时，会执行到对应的字节码指令（具体指令下面有介绍），此时被同步的对象实例头所指向的监视器对象（ObjectMonitor），会将ower变量设置为当前线程，这个操作也就是让当前线程持有该对象实例的锁，其他线程
 再次进入synchronize方法或者代码块时，就会被加入到EntryList同步队列中，当线程执行到结束的字节码指令时，owner值恢复为null，同时释放锁。
如果在同步块内部，对象调用Object.wait()方法，owner也会被恢复为null, 线程此时进入WAITING状态，同时该线程进入WaitSet集合等待被唤醒。

注：wait(),notify(),notifyAll()方法存在于Object对象中并且必须在synchronize方法或者代码块中，是因为执行这几个方法时，必须拿到monitor对象，ObjectMonitor中有WaitSet线程信息，而synchronize可以要拿到monitor对象。

![image](0B4062156701460DB76DF0119530B50F)


-  synchronize修饰方法

![image](8194582C2CAF4850BB1FE1A7848649F5)

javap之后的字节码中ACC_SYNCHRONIZED标识，该标识指明了该方法是一个同步方法，JVM通过该ACC_SYNCHRONIZED访问标志来辨别一个方法是否声明为同步方法，从而执行相应的同步调用
![image](F66FE3FBBAD0458189AF2803EA46FC75)

- synchronize 同步代码块

从字节码中可知同步语句块的实现使用的是monitorenter 和 monitorexit 指令，其中monitorenter指令指向同步代码块的开始位置，monitorexit指令则指明同步代码块的结束位置

![image](20802AC73E91488FAB4AEF8768B523D4)

##### synchronize锁的优化
jdk1.6对synchronize锁做了优化，HotSpot虚拟机开发团队在这个版本上花
费了大量的资源去实现各种锁优化技术，如适应性自旋（Adaptive Spinning）、锁消除（LockElimination）、锁膨胀（Lock Coarsening）、轻量级锁（Lightweight Locking）、偏向锁（BiasedLocking）等。

###### 自旋锁与自适应自旋
- 自旋锁：有两个以上的线程竞争锁的时候，让其中一个线程自旋等待，但并不放弃CPU的执行时间。自旋锁在JDK1.4.2中就已经引入，只不过默认是关闭的，可以使用-XX：+UseSpinning参数来开启，在JDK 6中就已经改为默认开启。自旋次数
的默认值是十次，用户也可以使用参数-XX：PreBlockSpin来自行更改。

- 自适应自旋：自旋次数不在是固定的，jvm根据前一次在同一个锁上自旋的时间及拥有者的状态来调整这次的自旋次数或者不自旋。

###### 锁消除
即时编译器对一些已经同步的代码，但是不可能存在共享数据竞争时，对锁进行消除。比如在局部变量使用StringBuffer.append()，方法虽然有锁，但是可以被安全的消除。

###### 锁粗化
比如StringBuffer是一个共享变量，在一个代码块内多次的StringBuffer.append()，将会把加锁同步的范围扩展（粗化）到整个操作序列的外部。

###### 轻量级锁
**轻量级锁是相对于使用互斥量的重量级锁而言的**，因为互斥会导致线程挂起，挂起和恢复线程需要消耗更多的资源。轻量级锁并不是要代替重量级锁，而是在没有多线程竞争的情况下，轻量级锁便通过CAS操作成功避免了使用互斥量的开销，减少传统重量级锁使用系统的互斥量而带来的消耗。但如果确
实存在锁竞争，除了互斥量的本身开销外，还额外发生了CAS操作的开销。因此在有竞争的情况下，轻量级锁反而会比传统的重量级锁更慢。

**轻量级锁执行过程**：
![image](E5E6DFFCC9D94944AAA756312C0097AD)

1）在代码进入同步块的时候，如果同步对象锁状态为无锁状态（锁标志位为“01”状态，是否为偏向锁为“0”），虚拟机首先将在当前线程的栈帧中建立一个名为锁记录（Lock Record）的空间，用于存储锁对象目前的Mark Word的拷贝，然后拷贝对象头中的Mark Word复制到锁记录中。如图13-3

2）拷贝成功后，虚拟机将使用CAS操作尝试将对象的Mark Word更新为指向Lock Record的指针，并将Lock Record里的owner指针指向对象的Mark Word。如图13-4

3）如果这个更新动作成功了，那么这个线程就拥有了该对象的锁，并且对象Mark Word的锁标志位设置为“00”，表示此对象处于轻量级锁定状态。

4）如果轻量级锁的更新操作失败了，虚拟机首先会检查对象的Mark Word是否指向当前线程的栈帧，如果是就说明当前线程已经拥有了这个对象的锁，那就可以直接进入同步块继续执行，否则说明多个线程竞争锁。

######  偏向锁
偏向锁是在只有一个线程执行同步块时进一步提高性能。减少轻量级锁获取和释放需要依赖多次CAS原子指令，偏向锁只需在置换MarkWord中的线程ID依赖的一次CAS原子指令。

1、偏向锁获取过程：

（1）访问Mark Word中偏向锁的标识是否设置成1，锁标志位是否为01——确认为可偏向状态。

（2）如果为可偏向状态，则测试线程ID是否指向当前线程，如果是，进入步骤（5），否则进入步骤（3）。

（3）如果线程ID并未指向当前线程，则通过CAS操作竞争锁。如果竞争成功，则将Mark Word中线程ID设置为当前线程ID，然后执行（5）；如果竞争失败，执行（4）。

（4）如果CAS获取偏向锁失败，则表示有竞争。当到达全局安全点（safepoint）时获得偏向锁的线程被挂起，偏向锁升级为轻量级锁，然后被阻塞在安全点的线程继续往下执行同步代码。

（5）执行同步代码。

　　
 2、偏向锁的释放：

偏向锁的撤销在上述第四步骤中有提到。偏向锁只有遇到其他线程尝试竞争偏向锁时，持有偏向锁的线程才会释放锁，线程不会主动去释放偏向锁

#################################

4）synchronized 关键字之锁的升级（偏向锁->轻量级锁->重量级锁）
![image](88D4F28EBEC144CAAC2DDBF26351827C)

3.重量级锁、轻量级锁和偏向锁之间转换

![image](5FABCF2AACA84208865414FD39EB6A35)

- 偏向锁，在线程第一次获得锁，会记录thread ID，并且设置偏向。如果持有偏向锁的线程以后每次进入同步块时，jvm不做任何同步操作。
- 轻量级锁。前一次同步已退出，另外一个线程来竞争锁，如果发现Mark  Word中thread ID不是本线程，这偏向设置马上撤销，恢复到未锁定或者轻量级锁。
- 重量级锁（Heavyweight Lock）。未获得锁的线程被操作系统挂起。

![image](744C2CA5B9584156920434FFE585FEC6)

#### 7.ReentrantLock 

参考 https://blog.csdn.net/javazejian/article/details/75043422

ReentrantLock 基于AQS(AbstractQueueSynchronizer)队列同步器实现的，默认使用NonfairSync（继承AQS）。

**可重入性**：是指一条线程能够反复进入被它自己持有锁的同步块的特性。

![image](827E36030E6F4ADD9886D3AC1A11054B)

AQS内部保持了一个虚拟队列，通过操作head和tail来实现线程队列的插入和删除。

当一个线程尝试获取锁失败，则插入队列，并且该线程在自旋并且前驱pre为head节点时获取同步状态。如果获取锁失败，则LockSupport.park()挂起线程；如果获取锁成功，则占有锁，修改锁状态为1。unlock解锁会唤醒线程，自旋后会重新尝试获取锁。


![image](1D9B87C3728942E4B9D4C989491A7093)

获取同步状态失败的线程入队

![image](DC318B697AAD449EB37209174DF406A7)

前驱pre为head线程并且自旋获取同步状态成功，则删除节点

![image](BFF2233FFEC94BD7955CBEAA6CF42560)


相同：

 - 都可重入
 - 公平锁都是用的队列

不同：
 
 - synchronize 锁的持有与释放都是隐式的，ReentrantLock需要手动加锁（lock）和解锁（unlock）
 - synchronize 同步过程不能被中断，ReentrantLock同步可以被中断

#### 8.Condition

Condition的具体实现类是AQS的内部类ConditionObject，前面我们分析过AQS中存在两种队列，一种是同步队列，一种是等待队列，而等待队列就相对于Condition而言的。**注意在使用Condition前必须获得锁**, 内部也是使用Node实现的单向列表的队列。

![image](085ADCC381DE4D7C8D08153E9ED1B9FF)

doSignal()逻辑图

![image](99F890FA6CC649C3BB1F57C41B9697B2)

#### 9.阻塞队列ArrayBlockingQueue、LinkedBlockingQueue

相同点

- 阻塞队列都是通过ReentrantLock实现并发，Condition实现阻塞与唤醒

- put(): 将元素插入此队列的尾部，如果该队列已满，则一直阻塞

  take(): 获取并移除此队列头元素，若没有元素则一直阻塞。

区别

- ArrayBlockingQueue 是一个用数组实现的有界阻塞队列，初始化需要指定长度

- LinkedBlockingQueue 是一个基于链表的阻塞队列，其内部维持一个基于链表的数据队列，，内部默认最大长度为Integer.MAX_INTEGER，初始化最好指定长度，也是FIFO（先进先出原则）。当入队列速度大于出队列速度，会导致内存溢出。


#### 小知识：原码，反码，补码
原则：
- 正数的原码，反码，补码相同
- 负数反码为除符号位取反，补码为反码加1

byte a = +1：
[0000 0001]原 = [0000 0001]反 = [0000 0001]补

byte b = -1：
[1000 0001]原 = [1111 1110]反 = [1111 1111]补 

根据补码求原码：
- 如果补码的符号位为“0”，表示是一个正数，所以补码就是该数的原码
- 如果补码的符号位为“1”，表示是一个负数，求原码的操作可以是：符号位为1，其余各位取反，然后再整个数加1。

例如已知-1的补码为[1111 1111]，求原码：
[1111 1111]补 = [1000 0000]反 = [1000 0001]原

- byte类型取值范围：
[1000 0000]~[0111 1111] 即 -2^8 ~ 2^8 -1

TIPS: [1000 0000]按照理论上说不是应该是-0吗？实际上-0也就是0，0已经用[0000 0000]表示，计算机用0的补码[1000 0000]表示-128：
(-1) + (-127) = [1000 0001]原 + [1111 1111]原 = [1111 1111]补 + [1000 0001]补 = [1000 0000]补

- int类型取值范围：
[1000 0000 0000 0000 0000 0000 0000 0000] ~ [0111 1111 1111 1111 1111 1111 1111 1111] 即 -2^32 ~ 2^32 -1

- 在Java中，使用补码来表示整数并参与运算
例如Integer.toBinaryString(-1)得到的是 [11111111111111111111111111111111]补码，原码是[10000000000000000000000000000001]


##### 10.Executor线程池

https://tech.meituan.com/2020/04/02/java-pooling-pratice-in-meituan.html

1）使用线程池的好处：
- 降低创建、销毁线程开销；
- 利用已有的线程提高响应速度；
- 线程池合理分配、调优、监控线程，避免无限制创建线程，消耗系统资源，提高系统的稳定性；
- 提供扩展性，比如饱和策略、延时定时线程池ScheduleThreadPoolExecutor；

2）ThreadPoolExecutor核心参数

- corePoolSize：核心线程数
- maximumPoolSize：最大线程数
- keepAliveTime：非核心线程闲置时的超时时长，超过这个时长，非核心线程就会被回收。当ThreadPoolExecutor的allowCoreThreadTimeOut属性设置为true时，keepAliveTime同样会作用于核心线程。
- unit：超时时间单位，TimeUnit.MILLISECONDS(毫秒)，TimeUnit.SECONDS(秒)以及TimeUnit.MINUTES(分钟)等。
- workQueue：任务队列
- threadFactory：线程工厂
- RejectedExecutionHandler：饱和策略处理类。


处理逻辑：

- 首先检测线程池运行状态，如果不是RUNNING，则直接拒绝，线程池要保证在RUNNING的状态下执行任务。
- 如果workerCount < corePoolSize，则创建并启动一个线程来执行新提交的任务。
- 如果workerCount >= corePoolSize，且线程池内的阻塞队列未满，则将任务添加到该阻塞队列中。
- 如果workerCount >= corePoolSize && workerCount < maximumPoolSize，且线程池内的阻塞队列已满，则创建并启动一个线程来执行新提交的任务。
- 如果workerCount >= maximumPoolSize，并且线程池内的阻塞队列已满, 则根据拒绝策略来处理该任务, 默认的处理方式是直接抛异常。

3）ThreadPoolExecutor可以抽象为两大功能模块：任务管理和线程管理。在代码中逻辑是混在一起的。

- 任务管理：添加任务execute、任务缓冲（BlockingQueue）、任务申请getTask、任务拒绝reject，任务调度通过execute方法完成，任务缓冲是阻塞队列来实现，任务申请是通过getTask()实现的，任务拒绝由饱和策略决定；

![image](0C0D8497125F4A99BB9014387F71D0B6)

CallerRunPolicy 由提交任务的线程执行，如果是main线程execute(task)，那么内部直接在main线程执行task.run()方法，这个策略目的是为了保证所有的任务都被执行，但是，可能会造成提交任务线程执行缓慢。

- 线程管理：增加线程（addWorker），回收线程（processWorkerExit），执行线程（run）

4）回收线程：当阻塞队列没有任务，非核心线程处于idle空闲状态，非核心线程将会被回收

```
    final void runWorker(Worker w) {
        ...
        for (;;) {
            //如果worker数量大于核心线程数量，或者允许核心线程也会超时回收
            boolean timed = allowCoreThreadTimeOut || wc > corePoolSize;

            if ((wc > maximumPoolSize || (timed && timedOut))
                && (wc > 1 || workQueue.isEmpty())) {
                if (compareAndDecrementWorkerCount(c))
                    return null;
                continue;
            }

            try {
                //timed为true时从workQueue队列中超过keepAliveTime阻塞时间拿不到数据（也就相当于没有task需要被执行，worker线程idle，在keepAliveTime时间后会被回收），则timeOut=true，在下一次循环中timed && timedOu为true，就会返回null，work将会被processWorkerExit回收，
                Runnable r = timed ?
                    workQueue.poll(keepAliveTime, TimeUnit.NANOSECONDS) :
                    workQueue.take();
                if (r != null)
                    return r;
                timedOut = true;
            } catch (InterruptedException retry) {
                timedOut = false;
            }
        }
        ...
        
    }

```

![image](3CAC0834B9094AF78F78ADDA879C623F)

Executors创建线程池，各自的适用场景（一般直接通过构造方法创建，不使用无界无界阻塞队列）
- FixedThreadPool：定长线程池，核心线程数和最大线程数一致，使用LinkedBlockingQueue无界队列。使用于每个任务都需要被执行的情况。
- SingleThreadExecutor：单线程化的Executor，核心线程数和最大线程数都是1，使用LinkedBlockingQueue无界队列，适用于需要保证执行顺序地执行各个任务；并且在任意时间点，不会有多个线程是活动的场景。
- CachedThreadPool：核心线程数为0，最大线程数为Integer.MAX_VALUE，使用的是SynchronousQueue队列，此队列没有容量。线程池数量无限大，新任务会直接分配或者创建一个线程，使用于希望提交的任务尽快的分配线程
- ScheduledThreadPool 可以处理延迟、周期调度线程池


#### 11.Callable，FutureTask

1）Callable 类似Runnable，差别是Callable.run()方法带返回值，一般都配合FutureTask来用；

2）FutureTask 继承自Runnable和Future，run()方法内部调用Callable.run()，类似于封装类，当run()方法执行完成，结果会存储在内部变量outcome; get()方法会根据状态判断来判断run是否执行完成，如果未执行完成，则会调用LockSupport.park()使线程等待，当run执行完成之后会调用用LockSupport.unpark()恢复线程。这样就能达到get()阻塞效果，也就能确保能拿到返回值。

```
    public V get() throws InterruptedException, ExecutionException {
        int s = state;
        if (s <= COMPLETING)
            s = awaitDone(false, 0L);
        return report(s);
    }
    
    private int awaitDone(boolean timed, long nanos){
        ...
        else if (timed) {
                nanos = deadline - System.nanoTime();
                if (nanos <= 0L) {
                    removeWaiter(q);
                    return state;
                }
                LockSupport.parkNanos(this, nanos);
            }
            else
                LockSupport.park(this);//run未执行完成会使线程先等待
        ...
    }
    
    
    private void finishCompletion() {
        // assert state > COMPLETING;
        for (WaitNode q; (q = waiters) != null;) {
            if (UNSAFE.compareAndSwapObject(this, waitersOffset, q, null)) {
                for (;;) {
                    Thread t = q.thread;
                    if (t != null) {
                        q.thread = null;
                        LockSupport.unpark(t);//通知get()所在的线程执行完毕
                    }
                    WaitNode next = q.next;
                    if (next == null)
                        break;
                    q.next = null; // unlink to help gc
                    q = next;
                }
                break;
            }
        }

       ...
    }

```

#### ThreadLocal-线程局部变量

https://blog.csdn.net/Justin_zhao/article/details/78470713

作用：它并不是为了自带了线程安全，并发控制这些机制，它是通过牺牲空间（创建额外更多的对象来），它的目的仅仅就是为了让线程使用自己的变量。让同一个线程上的所有代码块都是使用的该变量。

ThreadLocal无同步方案<br/>
- 线程内部持有一个Map存放着自己的局部变量，这些变量的key是ThreadLocal对象（可以理解为什么叫做线程局部变量了吧）；
- 有一个变量想放入线程的局部变量的你就需要**创建**一个ThreadLocal对象；
- ThreadLocal本身没有什么存储结构，只是提供了方法，所以变量不是存在ThreadLocal中的，ThreadLocal负责搬运。
![image](6D429493385846C5AF7F6CB455CC74F5)

总结：ThreadLocal 将一些线程不安全的变量，将用同步的方式转为为每个线程创建一个对象来实现线程安全。

ThreadLocal设计思想：

从set和get用法来看，ThreadLocal更像一个Map，为什么没有设计成已Thread为key，变量为value的结构呢？
这样设计会有两个问题：

- 多线程set和get情况下，需要保证ThreadLocal.Map线程安全。
- 线程销毁需要手动维护这个Map.Entry键值对，如果Thread结束，不主动维护这个Map，JVM无法垃圾回收这个Map.Entry内存，造成内存泄露。

![image](93547140A85E4ACBA9D396C9ACABE906)

实际上设计方案：

![image](2968D92500694F33BADB4CC38189C2BF)

Thread本地维护一个ThreadLocalMap,以ThreadLocal为key，要储存的对象为value。这样设计就不存在线程安全的问题，因为不同的线程本地变量是隔离的。那又是如何解决当ThreadLocal销毁，ThreadLocalMap.Entry还存在造成内存泄露问题呢？

- ThreadLocalMap.Entry键值对继承了WeakReference，ThreadLocal为key是弱引用，当ThreadLocal实例对象没有强引用时，ThreadLocal实例对象就会被回收；
- ThreadLocal为key被回收了，所对应的键值对Entry是如何回收的呢？ThreadLocalMap.set(T)中，会对Entry的key判断是否为空，对为空的Entry执行null的操作，使Entry对象也被回收；

###### ThreadLocal在数据库连接池中的作用：

https://blog.csdn.net/qq_42405666/article/details/108258820

引入threadlocal，其实是为了解决数据库事务，而事务是和连接有关的，每个连接对应一个事务，多个连接的事务是不一样的。

举例：
如果一个请求中涉及多个 DAO 操作，而如果这些DAO中的Connection都是独立的话，就没有办法完成一个事务。但是如果DAO 中的 Connection 是从 ThreadLocal 中获得的（意味着都是同一个对象）， 那么这些 DAO 就会被纳入到同一个 Connection 之下。

详细解释
- 首先，我们为了避免单一数据库连接的创建和关闭耗费时间和性能，引入了数据库连接池，提前创建好了n条连接放入池中，如果是单线程情况下，那这样挺好的
- 那如果是多线程情况下呢？还是上面那段话，假设同一时间多个线程从数据库连接池获取连接，那肯定拿的是不同的连接，我当前线程和别的线程拿的连接不一样，那我当前在crud的时候，不在一个事务之内。
- 假设不同时间的多个线程要从数据库连接池拿连接，那这个时候就可能拿到的是同一个连接了，那我多个线程线程拿到的是同一个连接，也就是说在多个线程在同一个事务之内，线程a执行了插入还没来得及提交，线程b此时来了个更新，在线程a还未操作完之前，线程b更新完了后，直接把连接给close了，线程a插了一半发现插不了了。。。此时肯定在想，这™是谁在搞我。
- 为了确保不同时间多个线程可能拿到的是同一个连接，那么此时threadlocal闪亮登场，就算我拿的是“同一个连接”，在引入了threadlocal后，每个线程之间都会创建独立的连接副本，将collection各自copy一份，这样就互相不干扰了。

#### Java内存泄露

https://juejin.im/post/6844903809165033479

造成内存泄露的几种常用的方式：

- 本地变量没有使用，没有使用时未回收（这种情况实际web业务编码时不常见，因很web编程时，本地变量线程不安全，很少使用本地变量储存数据）
```
public class Simple {
    private Object object;
    public void method() {
        object = new Object();

        object = null;//这里要设置为null，以便内存回收
    }
}

```

集合未使用未回收

```
Vector vec = new Vector();
for (int i = 1; i < 100; i++)
{
    Object obj = new Object();
    vec.add(obj);
    obj = null;
}
vec = null;//
```

- 容器元素没有使用时未回收
下面是我们通过 ArrayList 实现的一个 pop 方法。
```
public E pop(){
    if(size == 0)
        return null;
    else
        return (E) elementData[--size];
}
```

size 虽然变小了，但是elementData[size]元素还存在于集合中，这个元素永远都不会被使用。常见于工具类编程利于ThreadLocal对ThreadLocalMap.entry设置null，HashMap.remove()对Node设置null。而web编程时不常见，因为随着方法作用域的结束，集合也被销毁了

改进

```
public E pop(){
    if(size == 0)
        return null;
    else{
        E e = (E) elementData[--size];
        elementData[size] = null;
        return e;
    }
}
```

- 集合对象在内存中的存储
```
/*创建集合对象*/
Collection c1 = new ArrayList();
/*往集合中添加元素*/
String s1 = new String("abc");
c1.add(s1);
String s2 = new String("def");
c1.add(s2);
String x = new String("abc");
System.out.println(c1.contains(x));
```

问题：我们现在把s1,s2两个对象放入到了c1集合里面，现在又声明了一个x对象，里面的内容和s1一样都是abc，我们现在调用c1的contains方法，判断c1.contains(x)返回true还是false。

内存图

![image](331FE3A468034EDE919910950BE2D497)

答案：true，s1和x实际上都是指向常量池中的"abc"字符串，ArrayList.contains()方法最终调用的是String.equals(),这个equasl已经覆写实际上比较的就是字符串是否相同


#### HashMap

https://tech.meituan.com/2016/06/24/java-hashmap.html

https://crossoverjie.top/2018/07/23/java-senior/ConcurrentHashMap/

- 当new HashMap(cap)给定容量参数，确定HashMap的数组长度

```
    /**
     * https://blog.csdn.net/fan2012huan/article/details/51097331
     *
     * 计算new HashMap(cap)给定容量参数的时候，确定HashMap的数组长度
     *
     * 这个方法目的使高位为1之后的数全部变为1，比如cap=18， n=cap-1得到17（10001），
     * 经过两次移位运算就已经达到（11111），这个数字也就是31，最后+1，结果得到32
     *
     * 1）cap-1是为了防止，cap已经是2的幂。如果cap已经是2的幂， 又没有执行这个减1操作，
     * 则执行完后面的几条无符号右移操作之后，返回的capacity将是这个cap的2倍
     *
     * @param cap
     * @return
     */
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
```

- 用key的hashCode值，进行高低位与运算

```
    /**
     * https://www.zhihu.com/question/20733617
     *
     * 用key的hash值，进行高低位与运算，目的是为了让高低位都参与到整个数组索引定位计算过程 hashcode & (length -1) ，
     * 尽量避免hash算法得到的离散值进行与运算时得到相同的索引值，这样就降低了元素碰撞几率，这样在一个元素链表上出现相同的索引值更少，提高查询效率
     *
     *如果不进行这一步运算，那么永远都是hashcode的低位运算。在tableSizeFor（）方法我们已经知道 length-1得到的二进制数，低位永远为1，
     * 例如数组长度为16，length-1为15（1111），hashcode & (length -1)永远只有低四位参与运算，
     *
     *这个函数也可以称之为“扰动函数”， Java 8觉得扰动做一次就够了，做4次的话，多了可能边际效用也不大
     * @param key
     * @return
     */
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
```

- 根据key的哈希值求索引：length是2的N次方幂（length默认是16），（length - 1）转换为二进制一定是 xxxx 1111, 最终h & (length-1)运算相当于 (h mod length) h对length取模。例如如果h=17(二进制为10001),length=16，length-1则为000...1111,与的结果为0001，也就是最终索引为1 

```
    /**
     * dk1.7的源码，jdk1.8没有这个方法，但是实现原理一样的
     * 运算等价于对length取模
     * @param h
     * @param length
     * @return
     */
    static int indexFor(int h, int length) {
        return h & (length-1); 
    }
```

- 扩容机制resize()

jdk1.7 扩容机制：容量扩容为原来的2倍，重新hash计算得出索引位置，将新进入的元素放在链表前面
![image](6AAB5EE9E3E14B998D4B567A727F23B4)

jdk1.8 扩容机制：不在需要对key进行hash运算，只需要看看新的length增加2倍之后，经过重新计算h & (length-1)，原来的hash值新增的那个bit是1还是0就好了，是0的话索引没变，是1的话索引变成“原索引+oldCap”。

![image](1F1921FA28124BAD917F9BAE0C31B16E)

1.8尾插法，扩容节点都是插入链接尾部或者红黑树

![image](D65AD50E013E4FA18CDA3001792BB7C6)

- HashMap 1.8 与 1.7 区别

https://blog.csdn.net/qq_36520235/article/details/82417949

https://www.jianshu.com/p/8324a34577a0?utm_source=oschina-app

1）resize()扩容后存储索引位置优化：

- 1.8 resize()之后不再hash运算：扩容后索引 = 原索引 or 原索引 + oldCap；
- 1.7 resize()之后需要再次调用hash()方法做hashCode运算和扰动处理，再确定索引位置；

```
 void addEntry(int hash, K key, V value, int bucketIndex) {
        if ((size >= threshold) && (null != table[bucketIndex])) {
            resize(2 * table.length);
            hash = (null != key) ? hash(key) : 0; //1.7jdk 扩容再次调用hash()
            bucketIndex = indexFor(hash, table.length);
        }

        createEntry(hash, key, value, bucketIndex);
    }
```

2）扩容时转移数据方式不同

- 1.8 尾插法，并且引入红黑树的数据结构，将数据插入到链表尾部或者当链表数据数量大于8时，转换为红黑树结构
- 1.7 头插法，与扩容前的数据位置发生颠倒，出现逆序，并发条件下会出现死循环

putVal()方法中
```
...
if (binCount >= TREEIFY_THRESHOLD - 1) //binCount 从0开始
    treeifyBin(tab, hash);
...
```

![image](813A790633D74489BA57DB462A718A8B)

3）扩容时插入的数据时机不同

- 1.8 先插入再扩容
- 1.7 先扩容再插入

- 使用HashMap注意事项

1）扩容是特别耗时的操作，初始化的时候尽量给出初始容量；

2）HashMap线程不安全，不要在多线程环境使用；

3）虽然loadFactor负载因子可以修改，但是不建议修改；

#### ConcurrentHashMap

http://www.jasongj.com/java/concurrenthashmap/

https://crossoverjie.top/2018/07/23/java-senior/ConcurrentHashMap/

- 1.7结构：Segment继承ReentrantLock

![image](8F666E264296489A87D500EE70F43B4D)

1）寻址方式：对key作hash运算获得segment位置

2）同步方式：put方法中，先通过索引定位segment，segment通过自旋tryLock()尝试获得锁，如果超过retry次数，则调用lock()申请锁，如果还是未获得锁，lock()方法将线程park()不再尝试。

```
while (!tryLock()) {
    ...
    else if (++retries > MAX_SCAN_RETRIES) {
                    lock();//超过最大尝试次数，挂起线程break出循环不在尝试
                    break;
                }
    ...
}
```

自旋锁效率较高，但是比较消耗CPU，因此设定retry阈值，达到一定次数使用互斥锁。

- 1.8结构：结构与HashMap类似，采用数组+链表（或红黑树），没有使用segment

![image](21C3630F0E74498D84458FEFABAC5F8B)

1）寻址方式：key的hashcode与数组长度取模 h & (length -1), 与1.8HashMap相同

2）同步方式：对于put操作，

- 如果哈希桶为null，则用CAS + 自旋方式添加元素

```
for (Node<K,V>[] tab = table;;) {
    ...
    else if ((f = tabAt(tab, i = (n - 1) & hash)) == null) {
                //如果成功则结束CAS操作，如果失败再次进入循环
                if (casTabAt(tab, i, null,
                             new Node<K,V>(hash, key, value, null)))
                    break;
            }
    ...
}
```

- 如果哈希桶不为null，则用synchronize关键字同步

```
synchronized (f) {
    ...
}
```

1.7与1.8不同

- 1.7 采用segment数组 + 链表结构， 1.8采用 哈希桶数组 + 链表或红黑树
- 1.7 同步方式ReentrantLock保证线程安全，1.8采用CAS + 自旋 + synchronize保证线程安全
