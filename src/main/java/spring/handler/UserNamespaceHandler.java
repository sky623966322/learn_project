package spring.handler;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;
import spring.parser.UserBeanDefinitionParser;

public class UserNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("user", new UserBeanDefinitionParser());
    }
}
