package spring.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutowireServiceA {

    @Autowired
    private AutowireServiceB autowireServiceB;

    public void testA() {
        System.out.println("invoke testA first");
        autowireServiceB.testB();
    }

    public AutowireServiceB getAutowireServiceB() {
        return autowireServiceB;
    }

    public void setAutowireServiceB(AutowireServiceB autowireServiceB) {
        this.autowireServiceB = autowireServiceB;
    }
}
