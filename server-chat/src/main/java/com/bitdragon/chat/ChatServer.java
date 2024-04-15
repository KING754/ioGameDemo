package com.bitdragon.chat;

import com.bitdragon.chat.action.ChatAction;
import com.bitdragon.common.config.AppConfig;
import com.bitdragon.common.net.ServerUtil;
import com.bitdragon.logic.base.JYLJBarSkeletonConfig;
import com.iohao.game.action.skeleton.core.BarSkeleton;
import com.iohao.game.action.skeleton.core.BarSkeletonBuilder;
import com.iohao.game.action.skeleton.core.BarSkeletonBuilderParamConfig;
import com.iohao.game.action.skeleton.core.flow.internal.DebugInOut;
import com.iohao.game.action.skeleton.core.flow.internal.TraceIdInOut;
import com.iohao.game.bolt.broker.client.AbstractBrokerClientStartup;
import com.iohao.game.bolt.broker.core.client.BrokerAddress;
import com.iohao.game.bolt.broker.core.client.BrokerClient;
import com.iohao.game.bolt.broker.core.client.BrokerClientBuilder;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatServer extends AbstractBrokerClientStartup {
    private final AppConfig appConfig;
    private final ServerUtil serverUtil;

    @Override
    public BrokerAddress createBrokerAddress() {
        return new BrokerAddress(appConfig.getGatewayIP(), appConfig.getPort());
    }

    @Override
    public BarSkeleton createBarSkeleton() {
        BarSkeletonBuilderParamConfig builder = JYLJBarSkeletonConfig.createBarSkeletonBuilderParamConfig();      // 业务框架构建器
        builder.scanActionPackage(ChatAction.class);


        BarSkeletonBuilder barSkeletonBuilder = JYLJBarSkeletonConfig.createBarSkeletonBuilder(builder);
        barSkeletonBuilder.getSetting().setValidator(true);                                                // 开启 jsr380 验证


        // 添加控制台输出插件
        barSkeletonBuilder.addInOut(new DebugInOut());
        barSkeletonBuilder.addInOut(new TraceIdInOut());
        barSkeletonBuilder.getSetting().setCmdMaxLen(1000);
        barSkeletonBuilder.getSetting().setSubCmdMaxLen(60000);
        barSkeletonBuilder.getSetting().setPrintAction(true);

        return barSkeletonBuilder.build();
    }

    @Override
    public BrokerClientBuilder createBrokerClientBuilder() {
        if (!serverUtil.checkServerConfig(appConfig, true, false)) {
            System.exit(0);
        }

        String serverFlag = appConfig.getServerType() + "-" + appConfig.getId();
        BrokerAddress gatewayIAddress = new BrokerAddress(appConfig.getGatewayIP(), appConfig.getPort());

        BrokerClientBuilder builder = BrokerClient.newBuilder();
        builder.appName(serverFlag);
        builder.id(serverFlag);
        builder.tag(appConfig.getServerType());
        builder.brokerAddress(gatewayIAddress);

        return builder;
    }


}
