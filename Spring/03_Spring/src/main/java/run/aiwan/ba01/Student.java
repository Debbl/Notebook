package run.aiwan.ba01;

public class Student {

    private String name;
    private int age;

    public Student() {
        System.out.println("Student无参的构造方法执行");
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
