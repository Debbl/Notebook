package run.aiwan.ba04;

public class SomeServiceImpl implements SomeService {
    @Override
    public void doSome(String name, Integer age) {
        System.out.println("doSome" + (10/0));
    }

    @Override
    public String doOther(String name, Integer age) {
        System.out.println("doOther");
        return "foo";
    }
}
