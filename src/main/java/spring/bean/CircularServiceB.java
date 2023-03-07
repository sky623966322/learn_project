package spring.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CircularServiceB {

    private CircularServiceA serviceA;

}
