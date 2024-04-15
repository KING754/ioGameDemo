package com.bitdragon.rpc.task;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import com.bitdragon.rpc.ProtoConstant;
import com.iohao.game.widget.light.protobuf.ProtoFileMerge;

@ProtobufClass
@ProtoFileMerge(fileName = ProtoConstant.PROTO_NAME_TASK, filePackage = "com.bitdragon.rpc.task")
public enum E_TaskStatus {
    E_NOT_START,            //未领取
    E_START,                //已领取
    E_DOING,                //进行中
    E_DONG,                 //已完成
}
