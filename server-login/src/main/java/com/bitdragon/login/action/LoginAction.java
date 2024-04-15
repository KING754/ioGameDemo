package com.bitdragon.login.action;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.generator.SnowflakeGenerator;
import cn.hutool.core.util.RandomUtil;
import com.bitdragon.rpc.E_ModuleId_Values;
import com.bitdragon.rpc.E_MsgId_Values;
import com.bitdragon.rpc.common.PlayerDetail;
import com.bitdragon.rpc.common.PlayerShort;
import com.bitdragon.rpc.login.LoginReq;
import com.bitdragon.rpc.login.LoginResp;
import com.iohao.game.action.skeleton.annotation.ActionController;
import com.iohao.game.action.skeleton.annotation.ActionMethod;
import com.iohao.game.action.skeleton.core.flow.FlowContext;
import com.iohao.game.bolt.broker.client.kit.ExternalCommunicationKit;
import com.iohao.game.bolt.broker.client.kit.UserIdSettingKit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
@ActionController(E_ModuleId_Values.E_M_Login_Id)
public class LoginAction {
    @Autowired
    public SnowflakeGenerator snowflakeGenerator;

    /** 登录 */
    @ActionMethod(E_MsgId_Values.E_Login)
    public LoginResp login(LoginReq loginReq, FlowContext context){
        log.info("receive login:{}",loginReq);

        PlayerShort playerShort = new PlayerShort();
        playerShort.setPlayerId(snowflakeGenerator.next());
        playerShort.setAvatarId(1);
        playerShort.setNickname("用户"+ RandomUtil.randomNumbers(4));

        PlayerDetail playerDetail = new PlayerDetail();
        playerDetail.setShortInfo(playerShort);
        playerDetail.setLevel(1);

        LoginResp resp = new LoginResp();
        resp.setToken(RandomUtil.randomStringUpper(12));
        resp.setExpireTime(DateUtil.offsetDay(new Date(),10).getTime());
        resp.setPlayerInfo(playerDetail);

        UserIdSettingKit.settingUserId(context,playerShort.getPlayerId());

        return resp;
    }

    /** 退出 */
    @ActionMethod(E_MsgId_Values.E_Logout)
    public void logout(FlowContext context){
        log.info("receive logout :{}",context.getUserId());
        ExternalCommunicationKit.forcedOffline(context.getUserId());
    }


}
