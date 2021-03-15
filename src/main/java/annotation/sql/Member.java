package annotation.sql;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@DBTable(name = "MEMBER")
public class Member {

    @SQLString(name = "id", value = 32, constraint = @Constraints(primaryKey = true))
    private String id;

    @SQLString(name = "name", value = 32)
    private String name;

    @SQLInteger(name = "age")
    private int age;

    @SQLString(name = "description", value = 1024)
    private String description;//个人描述

}
