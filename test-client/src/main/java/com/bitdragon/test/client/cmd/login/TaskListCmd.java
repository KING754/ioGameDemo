package com.bitdragon.test.client.cmd.login;

import com.bitdragon.rpc.E_ModuleId;
import com.bitdragon.rpc.E_MsgId;
import com.bitdragon.rpc.task.TaskListReq;
import com.bitdragon.rpc.task.TaskListResp;
import com.iohao.game.external.client.AbstractInputCommandRegion;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TaskListCmd extends AbstractInputCommandRegion {

    @Override
    public void initInputCommand() {
        super.inputCommandCreate.cmd = E_ModuleId.E_M_Lobby_Id.getValue();
        ofCommand(E_MsgId.E_Task_List.getValue()).setTitle(E_MsgId.E_Task_List.name())
                .setRequestData(()->{
                    TaskListReq req = new TaskListReq(1111l);
                    return req;
                }).callback(result -> {
                    TaskListResp resp = result.getValue(TaskListResp.class);
                    log.info("TaskListCmd:{}",resp);
                });
    }

}
