package spring.ch06;

import org.junit.Test;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class PropertyConfigurerDemo {

    @Test
    public void test1() {
        XmlBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("beanFactoryProcessor.xml"));
        BeanFactoryPostProcessor obscenityRemovingBeanFactoryPostProcessor =
                (BeanFactoryPostProcessor) beanFactory.getBean("obscenityRemovingBeanFactoryPostProcessor");
        obscenityRemovingBeanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

        Object simplePostProcessor = beanFactory.getBean("simplePostProcessor");
        System.out.println(simplePostProcessor);
    }

    @Test
    public void test2() {
        ClassPathXmlApplicationContext xmlApplicationContext = new ClassPathXmlApplicationContext("beanFactoryProcessor.xml");

        Object simplePostProcessor = xmlApplicationContext.getBean("simplePostProcessor");
        System.out.println(simplePostProcessor);
    }


}
