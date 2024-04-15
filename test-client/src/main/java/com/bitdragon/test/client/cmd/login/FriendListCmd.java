package com.bitdragon.test.client.cmd.login;

import com.bitdragon.rpc.E_ModuleId;
import com.bitdragon.rpc.E_MsgId;
import com.bitdragon.rpc.friend.FriendListReq;
import com.bitdragon.rpc.friend.FriendListResp;
import com.iohao.game.external.client.AbstractInputCommandRegion;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FriendListCmd extends AbstractInputCommandRegion {

    @Override
    public void initInputCommand() {
        super.inputCommandCreate.cmd = E_ModuleId.E_M_Lobby_Id.getValue();
        ofCommand(E_MsgId.E_FriendList.getValue()).setTitle(E_MsgId.E_FriendList.name())
                .setRequestData(()->{
                    FriendListReq req = new FriendListReq(1111l);
                    return req;
                }).callback(result -> {
                    FriendListResp resp = result.getValue(FriendListResp.class);
                    log.info("FriendListCmd:{}",resp);
                });
    }

}
