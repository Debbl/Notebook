package run.aiwan.ba03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("student")
public class Student {
    @Value("foo")
    private String name;
    private int age;
    private School school;

    public void setName(String name) {
        this.name = name;
    }
    @Value("21")
    public void setAge(int age) {
        this.age = age;
    }

    @Autowired
    public void setSchool(School school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", school=" + school +
                '}';
    }
}
