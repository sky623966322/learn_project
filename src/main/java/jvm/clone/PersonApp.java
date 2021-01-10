package jvm.clone;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.SerializationUtils;

/**
 * -Xlog[:[selector][:[output][:[decorators][:output-options]]]]
 *
 * -Xlog:gc*,gc+ref=debug,gc+heap=debug,gc+age=trace,
 * gc+ergo*=trace:file=/tmp/idea-gc-%p-%t.log:tags,uptime,time,level:filecount=10,filesize=50m

 -Xlog:gc::uptime,tid
 */
public class PersonApp {

    public static void main(String[] args) {

        //cloneByObject();
        cloneByBeanUtils();
        //cloneBySerializationUtils();

    }

    /**
     * Bean需要实现Cloneable接口，因为Object.clone()的native方法实现中检查了cloneable的标记，
     * Object。clone() : guarantee(cloneable == klass->is_cloneable(), "incorrect cloneable flag");
     * 参考link: https://www.zhihu.com/question/52490586/answer/130744569
     *
     *
     * Person.clone 覆盖了Object.clone,并且Address.clone也是一样需要覆盖，这样才能保证深拷贝
     * 参考：https://zhuanlan.zhihu.com/p/95686213
     */
    public static void cloneByObject(){
        Person.Address address = new Person.Address("湖北省", "武汉市");
        Person zhangs = new Person("张三", 18, "高中学生", address);
        System.out.println("zhangs: " + zhangs);

        try {
            Person lisi = (Person) zhangs.clone();
            lisi.name = "李四";
            lisi.address.city= "黄冈市";

            //zhangs的city也被改变了，并且他们Address的内存地址相同，也印证了Address还是浅拷贝
            System.out.println("zhangs: " + zhangs);
            System.out.println("lisi: " + lisi);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 参考：https://www.zhihu.com/question/35240637/answer/61845590
     *
     * BeanUtils.clone() 浅拷贝
     * Bean需要提高getters and setters，并且需要无参构造方法
     */
    public static void cloneByBeanUtils(){
        System.out.println("##cloneByBeanUtils##");

        Person1 zhangs = new Person1("张三", 18, "高中学生");
        zhangs.setAddress(new Person1.Address1("湖北省", "武汉市"));
        System.out.println("zhangs: " + zhangs);

        try {
            Person1 lisi = (Person1) BeanUtils.cloneBean(zhangs);
            lisi.setName("李四");
            lisi.getAddress().setCity("黄冈市");

            //zhangs的city也被改变了，并且他们Address的内存地址相同，也印证了Address还是浅拷贝
            System.out.println("zhangs: " + zhangs);
            System.out.println("lisi: " + lisi);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 参考 https://www.zhihu.com/question/35240637/answer/61845590
     *
     * 深拷贝，Bean都需要实现Serializable
     * 内部最终通过IO对可源对象进行序列化，然后在反序列化为新的对象实现复制
     */
    public static void cloneBySerializationUtils(){
        System.out.println("##cloneBySerializationUtils##");

        Person1 zhangs = new Person1("张三", 18, "高中学生");
        zhangs.setAddress(new Person1.Address1("湖北省", "武汉市"));
        System.out.println("zhangs: " + zhangs);

        try {
            Person1 lisi = (Person1) SerializationUtils.clone(zhangs);
            lisi.setName("李四");
            lisi.getAddress().setCity("黄冈市");

            //zhangs的city也被改变了，并且他们Address的内存地址相同，也印证了Address还是浅拷贝
            System.out.println("zhangs: " + zhangs);
            System.out.println("lisi: " + lisi);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
