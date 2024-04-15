package com.bitdragon.external;

import cn.hutool.core.util.StrUtil;
import com.bitdragon.common.config.AppConfig;
import com.bitdragon.common.net.ServerUtil;
import com.bitdragon.external.hook.HeartBeatHook;
import com.bitdragon.external.hook.LogOutHook;
import com.bitdragon.rpc.E_ModuleId;
import com.bitdragon.rpc.E_MsgId;
import com.iohao.game.bolt.broker.core.client.BrokerAddress;
import com.iohao.game.bolt.broker.core.common.IoGameGlobalConfig;
import com.iohao.game.external.core.ExternalServer;
import com.iohao.game.external.core.config.ExternalGlobalConfig;
import com.iohao.game.external.core.config.ExternalJoinEnum;
import com.iohao.game.external.core.hook.AccessAuthenticationHook;
import com.iohao.game.external.core.hook.internal.IdleProcessSetting;
import com.iohao.game.external.core.netty.DefaultExternalServer;
import com.iohao.game.external.core.netty.DefaultExternalServerBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExternalServerConfig {
    private final AppConfig appConfig;
    private final ServerUtil serverUtil;

    public ExternalServer createExternalServer() {
        if (!serverUtil.checkServerConfig(appConfig, true, true)) {
            System.exit(0);
        }

        IoGameGlobalConfig.openTraceId = true;
        IoGameGlobalConfig.externalLog = true;
        IoGameGlobalConfig.broadcastLog = true;
        IoGameGlobalConfig.openLog = true;

        // 路由访问权限控制；https://www.yuque.com/iohao/game/nap5y8p5fevhv99y
        extractedIgnore();

        // 游戏对外服 - 构建器；https://www.yuque.com/iohao/game/ea6geg
        DefaultExternalServerBuilder builder = DefaultExternalServer.newBuilder(appConfig.getPort())
                .externalJoinEnum(this.getProtoByConfigOrDefault(appConfig.getProtoName()))             //协议类型
                .brokerAddress(new BrokerAddress(appConfig.getGatewayIP(), appConfig.getGatewayPort()));

        builder.setting().setUserHook(new LogOutHook());
        builder.setting().setIdleProcessSetting(this.getHeartBeatSetting());

        // 构建游戏对外服
        return builder.build();
    }

    private void extractedIgnore() {
        /*
         * 注意，权限相关验证配置在游戏对外服是正确的，因为是游戏对外服在控制访问验证
         * see https://www.yuque.com/iohao/game/tywkqv#qEvtB
         */
        AccessAuthenticationHook accessAuthenticationHook = ExternalGlobalConfig.accessAuthenticationHook;
        // 表示登录才能访问业务方法
//        accessAuthenticationHook.setVerifyIdentity(true);
        /*
         * 由于 accessAuthenticationHook.verifyIdentity = true; 时，需要玩家登录才可以访问业务方法 （action）
         *
         * 在这可以配置一些忽略访问限制的路由。
         * 这里配置的路由，表示不登录也可以进行访问
         * 现在忽略的 3-1，是登录 action 的路由，所以当我们访问 3-1 路由时，可以不登录。
         * 忽略的路由可以添加多个。
         */
        accessAuthenticationHook.addIgnoreAuthCmd(E_ModuleId.E_M_Login_Id.value(), E_MsgId.E_Login.value());
        // see HallCmdModule.cmd，HallCmdModule.loginVerify
//        accessAuthenticationHook.addIgnoreAuthCmd(3, 1);
//        accessAuthenticationHook.addIgnoreAuthCmd(3, 8);
//        accessAuthenticationHook.addIgnoreAuthCmd(3);
//        accessAuthenticationHook.addIgnoreAuthCmd(6);
    }

    private IdleProcessSetting getHeartBeatSetting(){
        return new IdleProcessSetting()
                // 心跳整体时间设置包括：readerIdleTime、writerIdleTime、allIdleTime
                .setIdleTime(30)
                .setIdleHook(new HeartBeatHook());
    }

    private ExternalJoinEnum getProtoByConfigOrDefault(String protoType) {
        if (StrUtil.isEmpty(protoType)) {
            return ExternalJoinEnum.TCP;
        }

        for (ExternalJoinEnum proto : ExternalJoinEnum.values()) {
            if (proto.getName().equals(protoType)) {
                return proto;
            }
        }

        return ExternalJoinEnum.TCP;
    }
}
