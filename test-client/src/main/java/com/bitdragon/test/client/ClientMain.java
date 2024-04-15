package com.bitdragon.test.client;


import com.iohao.game.external.client.join.ClientRunOne;

public class ClientMain {

    public static void main(String[] args) {
        ClientRunOne client = InitClient.initClient();
        client.startup();
    }


}
