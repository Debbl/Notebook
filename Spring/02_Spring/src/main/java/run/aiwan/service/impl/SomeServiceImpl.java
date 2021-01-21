package run.aiwan.service.impl;

import run.aiwan.service.SomeService;

public class SomeServiceImpl implements SomeService {
    public SomeServiceImpl() {
        System.out.println("SomeServiceImpl的无参构造方法执行...");
    }

    @Override
    public void doSome() {
        System.out.println("SomeService的doSome方法执行...");
    }
}
