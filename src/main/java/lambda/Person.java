package lambda;


import lombok.Getter;

class Person {
    @Getter
    public String name;

    public Person(String name) {
        this.name = name;
    }

    public int compare(Person person1, Person person2){
        return person1.getName().compareTo(person2.getName());
    }

}
