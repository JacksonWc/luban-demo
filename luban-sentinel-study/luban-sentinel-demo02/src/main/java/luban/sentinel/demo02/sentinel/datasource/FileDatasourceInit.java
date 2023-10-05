package luban.sentinel.demo02.sentinel.datasource;

import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.datasource.FileRefreshableDataSource;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSON;

import java.net.URL;
import java.util.List;

/**
 * 使用这个类
 * 初始化加载json本地规则文件
 * 生成数据源数据 提供给sentinel使用
 */
public class FileDatasourceInit implements InitFunc {
    //init是懒加载,会在sentinel使用规则计算时才加载
    @Override
    public void init() throws Exception {
        System.out.println("当前初始化方法被加载了,SPI方式成功");
        //读取文件
        ClassLoader classLoader = FileDatasourceInit.class.getClassLoader();
        URL resource = classLoader.getResource("flowRules.json");//允许类加载器相对于他得根路径读取文件
        //文件全路径名称
        String fileAllName=resource.getFile();
        //sentinelapi调用 解析List<FlowRule>对象
        //String表示数据来源 类型 json
        //List<FlowRule> 转化的数据
        ReadableDataSource<String, List<FlowRule>> datasource=
                //创建数据源实现类对象 很多种 file nacos redis等
                //fileName
                //转化器 json文本转化成List<FlowRule>
            new FileRefreshableDataSource<List<FlowRule>>(
                    fileAllName, new Converter<String, List<FlowRule>>() {
                @Override
                public List<FlowRule> convert(String json) {
                    //json 文本中的内容 转化成list需要使用fastjson的api
                    return JSON.parseArray(json,FlowRule.class);
                }
            });
        //交给sentinel FlowRuleManager加载
        FlowRuleManager.register2Property(datasource.getProperty());
    }
}
