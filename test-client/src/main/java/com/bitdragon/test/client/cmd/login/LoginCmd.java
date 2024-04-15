package com.bitdragon.test.client.cmd.login;

import com.bitdragon.rpc.E_ModuleId;
import com.bitdragon.rpc.E_MsgId;
import com.bitdragon.rpc.login.LoginReq;
import com.bitdragon.rpc.login.LoginResp;
import com.iohao.game.external.client.AbstractInputCommandRegion;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginCmd extends AbstractInputCommandRegion {

    @Override
    public void initInputCommand() {
        super.inputCommandCreate.cmd = E_ModuleId.E_M_Login_Id.value();
        ofCommand(E_MsgId.E_Login.value()).setTitle(E_MsgId.E_Login.name())
                .setRequestData(()->{
                    LoginReq req = new LoginReq("TEST","123456");
                    return req;
                }).callback(result -> {
                    LoginResp resp = result.getValue(LoginResp.class);
                    log.info("登录返回:{}",resp);
                });
    }

}
