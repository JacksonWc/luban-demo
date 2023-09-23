package luban.demo.cart.test;

import com.tarena.demo.luban.all.main.DubboTestApiImpl;
import luban.demo.cart.api.DubboTestApi;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;

/**
 * 步骤5--开启一个dubbo底层 进程
 * 等待接收远程调用
 */
public class ProviderTestRun {
    public static void main(String[] args) {
        //定义注册的具体服务信息,包括 端口号
        ServiceConfig<DubboTestApi> serviceConfig=new ServiceConfig<>();
        //填写注册暴露的具体接口信息
        serviceConfig.setInterface(DubboTestApi.class);
        //填写服务的实现信息
        serviceConfig.setRef(new DubboTestApiImpl());
        //启动dubbo-provider进程
        DubboBootstrap.getInstance()
                //dubbo注册的服务名称
                .application("luban-demo-cart-provider")
                //nacos可以换成其他的同功能的组件zookeeper；和之前的应用的注册信息的内容也是不一样的
                .registry(new RegistryConfig("nacos://localhost:8848"))
                .protocol(new ProtocolConfig("dubbo",20990))
                .service(serviceConfig)
                .start()
                //一直运行这个provider端进程,直到手动停止
                .await();


    }
}
