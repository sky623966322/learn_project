package annotation.sql;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TableCreator {

    @Test
    public void creatMemberSQL() throws ClassNotFoundException {
        System.out.println(createTableSql("annotation.sql.Member"));
    }

    public static String createTableSql(String className) throws ClassNotFoundException{

        /**
         * 1. 获取Class类对象
         * 2. 获取表名
         * 3. 获取类对象所有的字段Field
         * 4. Field.getDeclareAnnotation()获取注解信息
         * 5. 对注解进行解析，拼接sql
         */

        Class<?> clazz = Class.forName(className);
        DBTable dbTable = clazz.getAnnotation(DBTable.class);
        if(dbTable == null) {
            System.out.println("No DBTable annotations in class " + className);
            return null;
        }
        String dbTablename = dbTable.name();
        String tableName = dbTablename == null || dbTablename.length() < 1 ?
                className.toUpperCase() : dbTablename;

        Field[] fields = clazz.getDeclaredFields();//包含父类的Field
        if (fields.length == 0) return null;

        List<String> columnDefs = new ArrayList<>();
        for (Field field : fields) {
            Annotation[] annotations = field.getDeclaredAnnotations();

            if (annotations.length == 0) return null;

            Annotation annotation = annotations[0];
            if (annotation instanceof SQLString){
                SQLString sqlString = (SQLString) annotation;

                String columnName = "";
                if(sqlString== null || sqlString.name().length() < 1)
                    columnName = field.getName().toUpperCase();
                else
                    columnName = sqlString.name();

                columnDefs.add(columnName + " VARCHAR(" +
                        sqlString.value() + ")" +
                        getConstraints(sqlString.constraint()));
            }

            if (annotation instanceof SQLInteger){
                SQLInteger sqlInteger = (SQLInteger) annotation;

                String columnName = "";
                if(sqlInteger== null || sqlInteger.name().length() < 1)
                    columnName = field.getName().toUpperCase();
                else
                    columnName = sqlInteger.name();

                columnDefs.add(columnName + " INT "+
                        getConstraints(sqlInteger.constraint()));
            }
        }

        //数据库表构建语句
        StringBuilder createCommand = new StringBuilder(
                "CREATE TABLE " + tableName + "(");
        for(String columnDef : columnDefs)
            createCommand.append("\n    " + columnDef + ",");

        // Remove trailing comma
        String tableCreate = createCommand.substring(
                0, createCommand.length() - 1) + ");";
        return tableCreate;
    }

    /**
     * 判断该字段是否有其他约束
     * @param con
     * @return
     */
    private static String getConstraints(Constraints con) {
        String constraints = "";
        if(!con.allowNull())
            constraints += " NOT NULL";
        if(con.primaryKey())
            constraints += " PRIMARY KEY";
        if(con.unique())
            constraints += " UNIQUE";
        return constraints;
    }

}
