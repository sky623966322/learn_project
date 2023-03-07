package spring.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CircularServiceA {

    private CircularServiceB serviceB;

    public void init() {
        System.out.println("init CircularServiceA");
    }

}
