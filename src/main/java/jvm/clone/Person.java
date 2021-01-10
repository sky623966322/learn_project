package jvm.clone;

public class Person implements Cloneable {
    public String name;
    public Integer age;
    public String desc;
    public Address address;

    public Person(String name, Integer age, String desc, Address address) {
        this.name = name;
        this.age = age;
        this.desc = desc;
        this.address = address;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Person person = (Person) super.clone();
        person.address = (Address) address.clone();
        return person;
    }

    @Override
    public String toString() {
        return super.toString() + "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", desc='" + desc + '\'' +
                ", address=" + address +
                '}';
    }

    static class Address implements Cloneable{
        public String province;
        public String city;

        public Address(String province, String city) {
            this.province = province;
            this.city = city;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        @Override
        public String toString() {
            return super.toString() + "Address{" +
                    "province='" + province + '\'' +
                    ", city='" + city + '\'' +
                    '}';
        }
    }
}
