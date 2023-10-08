package rocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RocketMain {
    public static void main(String[] args) {
        //java -jar **.jar --server.port=8888
        //--rocketmq.config.namesrvAddr=localhost:9876
        //-Xms128M -Xmx128M
        SpringApplication.run(RocketMain.class,args);
    }
}
