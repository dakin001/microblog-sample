package com.example.moikiitos.domain.shared;


import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;

@Configuration
@Getter
@Setter
public class AppConfig {
    @Value("${bigVerifiedAccountFollowerSize:1000}")
    private Integer bigVerifiedAccountFollowerSize = 1000;

    @Value("${bigVerifiedAccountFollowerSize:1000}")
    private String hostAndPort;

    @Value("${server.port}")
    private int serverPort;

    @SneakyThrows
    public String getUrl() {
        return "http://" + InetAddress.getLocalHost().getHostAddress() + ":" + this.serverPort;
    }
}
