package spring;

import org.junit.Test;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
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
    }

    @Test
    public void testCoustomSpringTag(){
        XmlBeanFactory bf = new XmlBeanFactory(new ClassPathResource("user.xml"));
        User zs = (User) bf.getBean("zs");
        System.out.println(zs.getUserName() + "->" + zs.getEmail());
    }

}
