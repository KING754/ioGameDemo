package com.bitdragon.lobby.action;


import cn.hutool.core.lang.generator.SnowflakeGenerator;
import com.bitdragon.rpc.E_ModuleId_Values;
import com.bitdragon.rpc.E_MsgId_Values;
import com.bitdragon.rpc.task.E_TaskStatus;
import com.bitdragon.rpc.task.TaskInfo;
import com.bitdragon.rpc.task.TaskListResp;
import com.iohao.game.action.skeleton.annotation.ActionController;
import com.iohao.game.action.skeleton.annotation.ActionMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@ActionController(E_ModuleId_Values.E_M_Lobby_Id)
public class TaskAction {
    @Autowired
    private SnowflakeGenerator snowflakeGenerator;

    /** 任务列表 */
    @ActionMethod(E_MsgId_Values.E_Task_List)
    public TaskListResp getTaskList(int playerId){
        TaskInfo t1 = new TaskInfo(1,1,1, E_TaskStatus.E_DOING);
        TaskInfo t2 = new TaskInfo(2,2,2, E_TaskStatus.E_NOT_START);
        TaskInfo t3 = new TaskInfo(3,3,1, E_TaskStatus.E_START);

        return new TaskListResp(List.of(t1,t2,t3));
    }
}
