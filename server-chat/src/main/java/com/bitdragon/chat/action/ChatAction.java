package com.bitdragon.chat.action;


import com.bitdragon.rpc.E_ModuleId_Values;
import com.bitdragon.rpc.E_MsgId_Values;
import com.bitdragon.rpc.chat.SendFriendMsgReq;
import com.iohao.game.action.skeleton.annotation.ActionController;
import com.iohao.game.action.skeleton.annotation.ActionMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ActionController(E_ModuleId_Values.E_M_Lobby_Id)
public class ChatAction {

    @ActionMethod(E_MsgId_Values.E_SendFriendMsg)
    public void sendMsg(SendFriendMsgReq req){
        log.info("send msg:{}",req);
    }
}
