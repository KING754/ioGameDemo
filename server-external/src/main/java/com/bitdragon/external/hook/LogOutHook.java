package com.bitdragon.external.hook;

import com.bitdragon.rpc.E_ModuleId;
import com.bitdragon.rpc.E_MsgId;
import com.iohao.game.action.skeleton.core.CmdKit;
import com.iohao.game.action.skeleton.protocol.HeadMetadata;
import com.iohao.game.action.skeleton.protocol.RequestMessage;
import com.iohao.game.bolt.broker.core.aware.BrokerClientAware;
import com.iohao.game.bolt.broker.core.client.BrokerClient;
import com.iohao.game.external.core.aware.UserSessionsAware;
import com.iohao.game.external.core.hook.UserHook;
import com.iohao.game.external.core.message.ExternalCodecKit;
import com.iohao.game.external.core.session.UserSession;
import com.iohao.game.external.core.session.UserSessions;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogOutHook implements UserHook, UserSessionsAware, BrokerClientAware {
    BrokerClient brokerClient;
    UserSessions<?, ?> userSessions;
    @Override
    public void setBrokerClient(BrokerClient brokerClient) {
        this.brokerClient = brokerClient;
    }

    @Override
    public void setUserSessions(UserSessions<?, ?> userSessions) {
        this.userSessions = userSessions;
    }

    @Override
    public void into(UserSession userSession) {

    }

    @Override
    public void quit(UserSession userSession) {
        long userId = userSession.getUserId();
        log.info("玩家退出 userId: {} -- {}", userId, userSession.getUserChannelId());
        log.info("当前在线玩家数量： {}", this.userSessions.countOnline());

        // 房间主路由和房间子路由（退出房间）
        int mergeCmd = CmdKit.merge(E_ModuleId.E_M_Login_Id.value(), E_MsgId.E_Logout.value());

        RequestMessage request = ExternalCodecKit.createRequest();
        HeadMetadata headMetadata = request.getHeadMetadata();
        headMetadata.setCmdMerge(mergeCmd);

        ExternalCodecKit.employ(request, brokerClient);
        // 给请求消息加上一些 user 自身的数据
        userSession.employ(request);

        try {
            // 请求游戏网关
            this.brokerClient.oneway(request);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
