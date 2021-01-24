package run.aiwan.ba01;

import org.springframework.stereotype.Component;
//@Component 默认是类名首字母小写
//@Component("student")
@Component(value = "student")
public class Student {
    private String name;
    private int age;

    public Student() {
        System.out.println("Student的无参构造方法");
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
