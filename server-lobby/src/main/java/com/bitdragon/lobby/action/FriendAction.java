package com.bitdragon.lobby.action;


import cn.hutool.core.lang.generator.SnowflakeGenerator;
import com.bitdragon.rpc.E_ModuleId_Values;
import com.bitdragon.rpc.E_MsgId_Values;
import com.bitdragon.rpc.common.PlayerDetail;
import com.bitdragon.rpc.common.PlayerShort;
import com.bitdragon.rpc.friend.FriendDetailResp;
import com.bitdragon.rpc.friend.FriendListReq;
import com.bitdragon.rpc.friend.FriendListResp;
import com.iohao.game.action.skeleton.annotation.ActionController;
import com.iohao.game.action.skeleton.annotation.ActionMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@ActionController(E_ModuleId_Values.E_M_Lobby_Id)
public class FriendAction {
    @Autowired
    private SnowflakeGenerator snowflakeGenerator;

    /** 好友列表 */
    @ActionMethod(E_MsgId_Values.E_FriendList)
    public FriendListResp getFriendList(FriendListReq req){
        log.info("getFriendList receive:{}",req);

        PlayerShort playerShort1 = new PlayerShort();
        playerShort1.setPlayerId(1);
        playerShort1.setAvatarId(1);
        playerShort1.setNickname("好友1");


        PlayerShort playerShort2 = new PlayerShort();
        playerShort2.setPlayerId(2);
        playerShort2.setAvatarId(2);
        playerShort2.setNickname("好友2");

        return new FriendListResp(List.of(playerShort1,playerShort2));
    }

    /** 好友详情 */
    @ActionMethod(E_MsgId_Values.E_FriendDetail)
    public FriendDetailResp getFriendDetail(long destPlayerId){
        log.info("getFriendDetail receive:{}",destPlayerId);

        PlayerShort playerShort1 = new PlayerShort();
        playerShort1.setPlayerId(1);
        playerShort1.setAvatarId(1);
        playerShort1.setNickname("好友详情");

        PlayerDetail detail = new PlayerDetail(playerShort1,11,12);

        return new FriendDetailResp(detail);
    }
}
