package com.bitdragon.chat;


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
public class ChatServertApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatServertApplication.class, args);

        ChatServer loginServer = ApplicationContextUtil.getBean(ChatServer.class);
        BrokerClientApplication.start(loginServer);

        BarSkeletonDoc.me().buildDoc("D:\\jiuyulingjing\\gitea\\proto-project\\rpc\\proto\\interface-doc\\chat-doc.txt");
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
