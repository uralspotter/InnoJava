package task2;

public class Employee {
    private String name;
    private int age;
    private String position;

    Employee(String name, int age, String position){
        this.name = name;
        this.age = age;
        this.position = position;
    }

    public int getAge(){
        return this.age;
    }

    public String getName(){
        return this.name;
    }

    public String getPosition(){
        return this.position;
    }
}
