package com.bitdragon.gateway;

import com.iohao.game.bolt.broker.server.BrokerServer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class InitServer implements CommandLineRunner {
    private final GatewayServerConfig gatewayServerConfig;

    @Override
    public void run(String... args) throws Exception {
        BrokerServer server = gatewayServerConfig.createGatewayServer();
        server.startup();
    }
}
