package spring.bean;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

public class MyTestBean {
    private String testStr = "testStr";

    public String getTestStr() {
        return testStr;
    }

    public void init(){
        ClassPathResource classPathResource = new ClassPathResource("README1.md");

        try {
            File file = classPathResource.getFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*FileUtils.copyFile(file, )
        classPathResource.*/
    }


    public void setTestStr(String testStr) {
        this.testStr = testStr;
    }
}
