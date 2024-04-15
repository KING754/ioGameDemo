package com.bitdragon.rpc;

import com.baidu.bjf.remoting.protobuf.EnumReadable;
import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import com.iohao.game.widget.light.protobuf.ProtoFileMerge;

@ProtobufClass
@ProtoFileMerge(fileName = ProtoConstant.PROTO_NAME_MSG_ID, filePackage = "com.bitdragon.rpc")
/** 主路由-模块ID */
public enum E_ModuleId  implements EnumReadable {
    E_Error_ModuleId(0),
    /** 登录及资源模块 */
    E_M_Login_Id(1),
    /** 大厅模块--》该游戏基本都在这块 */
    E_M_Lobby_Id(2),
    /** 聊天模块 */
    E_M_Chat_Id(3)
    ;

    private int value;
    E_ModuleId(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }

    @Override
    public int value() {
        return this.getValue();
    }
}
