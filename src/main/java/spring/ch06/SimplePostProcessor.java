package spring.ch06;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SimplePostProcessor {

    private String connectionString;
    private String password;
    private String userName;

}
