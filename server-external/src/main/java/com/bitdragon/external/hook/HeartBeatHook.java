package com.bitdragon.external.hook;

import com.iohao.game.action.skeleton.core.DataCodecKit;
import com.iohao.game.action.skeleton.protocol.BarMessage;
import com.iohao.game.common.kit.TimeKit;
import com.iohao.game.external.core.netty.hook.SocketIdleHook;
import com.iohao.game.external.core.session.UserSession;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HeartBeatHook implements SocketIdleHook {
    @Override
    public boolean callback(UserSession userSession, IdleStateEvent idleStateEvent) {
        log.info("heart check id:{}",userSession.getUserId());
        return false;
    }

    @Override
    public void pongBefore(BarMessage idleMessage) {log.info("receive pongBefore:{}",idleMessage);
        idleMessage.setData(DataCodecKit.encode(TimeKit.currentTimeMillis()));
    }
}
