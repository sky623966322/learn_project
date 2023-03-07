package spring;

import org.junit.Test;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import spring.bean.AutowireServiceA;
import spring.bean.MyTestBean;
import spring.bean.User;

import static org.junit.Assert.assertEquals;

public class BeanFactoryTest {

    @Test
    public void testSimpleLoad() {
        XmlBeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanFactoryTest.xml"));
        MyTestBean myTestBean = (MyTestBean) bf.getBean("myTestBean");
        assertEquals("testStr", myTestBean.getTestStr());

        //获取由FactoryBean创建的Car实例
        System.out.println(bf.getBean("car"));
        //创建FactoryBean实例
        System.out.println(bf.getBean("&car"));
        // 由alias创建bean实例
        System.out.println(bf.getBean("myTestBean2"));
    }

    @Test
    public void testCircularRef() {
        XmlBeanFactory bf = new XmlBeanFactory(new ClassPathResource("circularRefTest.xml"));
        System.out.println(bf.getBean("serviceA"));
    }

    @Test
    public void testAutowire() {
        ApplicationContext bf = new ClassPathXmlApplicationContext("autowireTest.xml");
        AutowireServiceA autowireServiceA = (AutowireServiceA)bf.getBean("autowireServiceA");
        autowireServiceA.testA();
    }

    @Test
    public void testCoustomSpringTag(){
        XmlBeanFactory bf = new XmlBeanFactory(new ClassPathResource("user.xml"));
        User zs = (User) bf.getBean("zs");
        System.out.println(zs.getUserName() + "->" + zs.getEmail());
    }

}
