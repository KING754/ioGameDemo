package com.bitdragon.common.net;

import cn.hutool.core.util.StrUtil;
import com.bitdragon.common.config.AppConfig;
import com.bitdragon.common.constant.ServerType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ServerUtil {
    public boolean checkServerConfig(AppConfig appConfig, boolean isNeedGateWay, boolean isNeedPort) {
        if (appConfig == null) {
            log.error("Server Config is null.");
            return false;
        }
        if (appConfig.getId() <= 0) {
            log.error("Server Config id error.config:{}", appConfig);
            return false;
        }
        if (ServerType.getServerTypeByName(appConfig.getServerType()) == null) {
            log.error("Server Config type error.config:{}", appConfig);
            return false;
        }

        if (isNeedGateWay) {
            if (StrUtil.isEmpty(appConfig.getGatewayIP()) || appConfig.getGatewayPort() <= 0 || appConfig.getPort() > 65535) {
                log.error("Server Config gateway error.config:{}", appConfig);
                return false;
            }
        }

        if (isNeedPort) {
            if (appConfig.getPort() <= 0) {
                log.error("Server Config port error.config:{}", appConfig);
                return false;
            }
        }

        log.info("APPCONFIG:{}", appConfig);

        return true;
    }
}
