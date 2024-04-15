package com.bitdragon.rpc;

import com.baidu.bjf.remoting.protobuf.EnumReadable;
import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import com.iohao.game.widget.light.protobuf.ProtoFileMerge;


@ProtobufClass()
@ProtoFileMerge(fileName = ProtoConstant.PROTO_NAME_ERROR_CODE, filePackage = "com.bitdragon.rpc")
/** 错误码 */
public enum E_ACTION_RESULT_Code implements EnumReadable {
    /** 成功 */
    E_SUSS_CODE(0),
    /** 账号不存在 */
    E_ACCOUNT_NOT_EXIST(1),
    /** 密码错误 */
    E_PASSWORD_ERROR(2);




    private int value;

    E_ACTION_RESULT_Code(int value) {
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
