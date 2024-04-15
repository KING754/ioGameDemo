package com.bitdragon.rpc;

import com.baidu.bjf.remoting.protobuf.EnumReadable;
import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import com.iohao.game.widget.light.protobuf.ProtoFileMerge;

@ProtobufClass
@ProtoFileMerge(fileName = ProtoConstant.PROTO_NAME_MSG_ID, filePackage = "com.bitdragon.rpc")
/** 子路由-消息ID */
public enum E_MsgId implements EnumReadable {
    E_Error_MsgId(0),
    //登录模块1000~1999

    /** 登录 */
    E_Login(1),
    /** 登出 */
    E_Logout(2),


    //大厅模块2000~6999
    /** 任务测试协议 */
    E_LobbyTaskTest(2000),
    /** 获取好友列表 */
    E_FriendList(2001),
    /** 获取好友详情 */
    E_FriendDetail(2002),
    /** 获取任务列表 */
    E_Task_List(2050),


    //聊天模块7000~9000
    /** 给好友发送消息 */
    E_SendFriendMsg(7000),
    /** 接收到好友消息通知 */
    E_ReceiveFriend_Notify(7001)
    ;


    private final int value;

    E_MsgId(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public int value() {
        return this.getValue();
    }
}
