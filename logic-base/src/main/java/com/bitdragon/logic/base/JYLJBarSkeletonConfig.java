package com.bitdragon.logic.base;

import com.iohao.game.action.skeleton.core.BarSkeletonBuilder;
import com.iohao.game.action.skeleton.core.BarSkeletonBuilderParamConfig;
import com.iohao.game.action.skeleton.core.IoGameGlobalSetting;
import com.iohao.game.action.skeleton.core.flow.internal.DebugInOut;
import com.iohao.game.action.skeleton.core.flow.internal.TraceIdInOut;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JYLJBarSkeletonConfig {

    public BarSkeletonBuilderParamConfig createBarSkeletonBuilderParamConfig() {
        return new BarSkeletonBuilderParamConfig()                                      // 业务框架构建器 配置
                .setBroadcastLog(true);                                                 // 推送(广播)文档生成
    }

    public BarSkeletonBuilder createBarSkeletonBuilder(BarSkeletonBuilderParamConfig config) {
        IoGameGlobalSetting.setDataCodec(new JYLJProtoDataCodec());

        return config.createBuilder()                                                   // 业务框架构建器
                .addInOut(new DebugInOut())                                             // 添加控制台输出插件
                .addInOut(new TraceIdInOut())                                           // 添加全链路调用日志跟踪插件
                .setFlowContextFactory(JYLJFlowContext::new);                           // 设置一个自定义的 flow 上下文生产工厂


    }
}
