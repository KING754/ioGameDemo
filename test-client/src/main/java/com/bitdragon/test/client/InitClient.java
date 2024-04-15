package com.bitdragon.test.client;

import com.bitdragon.test.client.cmd.login.FriendListCmd;
import com.bitdragon.test.client.cmd.login.LoginCmd;
import com.bitdragon.test.client.cmd.login.TaskListCmd;
import com.iohao.game.external.client.InputCommandRegion;
import com.iohao.game.external.client.join.ClientRunOne;
import com.iohao.game.external.core.config.ExternalJoinEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
public class InitClient {
    private static final String EXTERNAL_IP = "127.0.0.1";
    private static final int EXTERNAL_PORT = 7777;
    private static final ExternalJoinEnum TCP = ExternalJoinEnum.TCP;
    private static final ExternalJoinEnum WS = ExternalJoinEnum.WEBSOCKET;


    public static ClientRunOne initClient(){
        ClientRunOne client = new ClientRunOne();
        client.setJoinEnum(TCP);
        client.setInputCommandRegions(InitClient.getAllCmdAction());
        client.setConnectAddress(EXTERNAL_IP);
        client.setConnectPort(EXTERNAL_PORT);
//        client.idle(20);

        return client;
    }

    private static List<InputCommandRegion> getAllCmdAction(){
       return List.of(
         new LoginCmd(),
         new FriendListCmd(),
         new TaskListCmd()
       );
    }



}
