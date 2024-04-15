package com.bitdragon.login;


import cn.hutool.core.lang.generator.SnowflakeGenerator;
import com.bitdragon.common.util.ApplicationContextUtil;
import com.iohao.game.action.skeleton.core.doc.BarSkeletonDoc;
import com.iohao.game.action.skeleton.ext.spring.ActionFactoryBeanForSpring;
import com.iohao.game.bolt.broker.client.BrokerClientApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.bitdragon.*")
@SpringBootApplication
public class LoginServertApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginServertApplication.class, args);

        LoginServer loginServer = ApplicationContextUtil.getBean(LoginServer.class);
        BrokerClientApplication.start(loginServer);

        BarSkeletonDoc.me().buildDoc("D:\\jiuyulingjing\\gitea\\proto-project\\rpc\\proto\\interface-doc\\login-doc.txt");
    }

    @Bean
    public ActionFactoryBeanForSpring actionFactoryBean() {
        return ActionFactoryBeanForSpring.me();                             // 将业务框架交给 spring 管理
    }

    @Bean
    public SnowflakeGenerator SnowflakeGenerator(){
        return new SnowflakeGenerator();
    }
}
