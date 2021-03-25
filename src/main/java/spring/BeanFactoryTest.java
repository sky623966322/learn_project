package spring;

import org.junit.Test;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import spring.bean.MyTestBean;

import static org.junit.Assert.assertEquals;

public class BeanFactoryTest {

    @Test
    public void testSimpleLoad() {
        XmlBeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanFactoryTest.xml"));
        MyTestBean myTestBean = (MyTestBean) bf.getBean("myTestBean");
        assertEquals("testStr", myTestBean.getTestStr());
    }


}
