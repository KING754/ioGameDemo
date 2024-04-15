package com.bitdragon.common.config;


import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ToString
@Component
@ConfigurationProperties(prefix = "app.config")
public class AppConfig {
    private int id;
    private int port;
    private String protoName;
    private String gatewayIP;
    private int gatewayPort;
    private String serverType;
}