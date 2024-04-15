package com.bitdragon.external.ws;

import com.iohao.game.external.core.ExternalServer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class InitServer  implements CommandLineRunner {
    private final WSExternalServerConfig gameExternal;

    @Override
    public void run(String... args) throws Exception {
        ExternalServer server = gameExternal.createExternalServer();
        server.startup();
    }
}
