package luban.sentinel.demo02.service.impl;

import luban.sentinel.demo02.service.IHelloService;
import org.springframework.stereotype.Service;


@Service
public class HelloServiceImpl implements IHelloService {


    @Override
    public String sayHello(String name) {
        return "和sentinel打个招呼:"+name;
    }
}
