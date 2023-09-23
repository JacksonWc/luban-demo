package luban.demo.order;


import luban.demo.cart.api.DubboTestApi;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;

/**
 *步骤7
 * 启动dubbo客户端
 * 到nacos发现 provider注册信息
 * 获取注册信息利用注册信息建立底层连接
 * 从而实现远程调用DubboTestApi方法
 */
public class ConsumerTestRun {
    public static void main(String[] args) {
        //consumer消费者 配置对象
        ReferenceConfig<DubboTestApi> referenceConfig=new ReferenceConfig<>();
        //设置 接口反射
        referenceConfig.setInterface(DubboTestApi.class);
        //启动dubbo进程 配置 注册中心 配置协议(和对方兼容-就是provider怎么配我这里就配一样的)
        DubboBootstrap.getInstance()
                .application("luban-demo-order-consumer")
                .registry(new RegistryConfig("nacos://localhost:8848"))
//                这里配置消费者要消费的provider对象
                .reference(referenceConfig);

        //从consumer对象 reference中获取一个代理对象
        DubboTestApi dubboTestApi = referenceConfig.get();
        System.out.println("当前消费者获取的dubboTestApi类型:"+dubboTestApi.getClass().getName());
        //使用这个代理,调用方法
        String result = dubboTestApi.sayHi("王翠花");
        System.out.println("获取远程调用结果:"+result);
    }
}
