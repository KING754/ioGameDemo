package com.bitdragon.rpc;

public class E_MsgId_Values {
    public static final int E_Error_MsgId = 0;
    //登录模块1000~1999


    public static final int E_Login = 1;//登录
    public static final int E_Logout = 2;//登出



    //大厅模块2000~6999
    public static final int E_LobbyTaskTest = 2000;                             //任务测试协议
    public static final int E_FriendList = 2001;                             //获取好友列表
    public static final int E_FriendDetail = 2002;                             //获取好友详情

    public static final int E_Task_List = 2050;


    //聊天模块7000~9000
    public static final int E_SendFriendMsg = 7000;                             //给好友发送消息
    public static final int E_ReceiveFriend_Notify = 7001;                              //接收到好友消息通知

}
