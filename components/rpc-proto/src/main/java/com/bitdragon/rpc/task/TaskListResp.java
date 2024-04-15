package com.bitdragon.rpc.task;


import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import com.bitdragon.rpc.ProtoConstant;
import com.iohao.game.widget.light.protobuf.ProtoFileMerge;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@ToString
@ProtobufClass
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PUBLIC)
@ProtoFileMerge(fileName = ProtoConstant.PROTO_NAME_TASK, filePackage = "com.bitdragon.rpc.task")
public class TaskListResp {
    List<TaskInfo> tasks;
}
