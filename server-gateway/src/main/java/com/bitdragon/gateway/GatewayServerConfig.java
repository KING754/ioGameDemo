package com.bitdragon.gateway;

import com.bitdragon.common.config.AppConfig;
import com.bitdragon.common.net.ServerUtil;
import com.iohao.game.bolt.broker.cluster.BrokerRunModeEnum;
import com.iohao.game.bolt.broker.core.common.IoGameGlobalConfig;
import com.iohao.game.bolt.broker.server.BrokerServer;
import com.iohao.game.bolt.broker.server.BrokerServerBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GatewayServerConfig {
    private final AppConfig appConfig;
    private final ServerUtil serverUtil;

    public BrokerServer createGatewayServer() {
        if (!serverUtil.checkServerConfig(appConfig, false, true)) {
            System.exit(0);
        }

        IoGameGlobalConfig.openTraceId = true;
        IoGameGlobalConfig.externalLog = true;
        IoGameGlobalConfig.broadcastLog = true;
        IoGameGlobalConfig.openLog = true;

        BrokerServerBuilder gatewayBuilder = BrokerServer.newBuilder();
        gatewayBuilder.port(appConfig.getPort());

        gatewayBuilder.brokerRunMode(BrokerRunModeEnum.STANDALONE);

        return gatewayBuilder.build();
    }
}
