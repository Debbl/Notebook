package run.aiwan.ba07;

public class SomeServiceImpl {

    public void doSome(String name, Integer age) {
        System.out.println("doSome");
    }


    public String doOther(String name, Integer age) {
        System.out.println("doOther");
        return "foo";
    }
}
