package run.aiwan.ba03;

public class SomeServiceImpl implements SomeService {
    @Override
    public void doSome(String name, Integer age) {
        System.out.println("doSome");
    }

    @Override
    public String doOther(String name, Integer age) {
        System.out.println("doOther");
        return "foo";
    }
}
