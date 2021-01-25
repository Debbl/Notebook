package run.aiwan.ba01;

public class SomeServiceImpl implements SomeService {
    @Override
    public void doSome(String name, Integer age) {
        System.out.println("SomeService的doSome方法");
    }
}
