package sentinal.demo001.service.impl;

import org.springframework.stereotype.Service;
import sentinal.demo001.service.IHelloService;

@Service
public class HelloServiceImpl implements IHelloService {


    @Override
    public String sayHello(String name) {
        return "和sentinel打个招呼:"+name;
    }
}
