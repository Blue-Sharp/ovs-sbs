package de.bluesharp.sbs.ovs.repository;

import lombok.val;
import org.h2.tools.Server;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.sql.SQLException;
import java.util.ArrayList;

import static de.bluesharp.sbs.ovs.Application.Profile.LOCAL;
import static org.h2.engine.Constants.DEFAULT_TCP_PORT;


//@Profile(LOCAL)
//@Configuration
//@ConditionalOnProperty(prefix = "spring.h2.tcp-server", name = "enabled", havingValue = "true")
//@EnableConfigurationProperties(H2TcpServerProperties.class)
public class H2TcpServerAutoConfiguration {

    private final H2TcpServerProperties properties;

    public H2TcpServerAutoConfiguration(H2TcpServerProperties properties) {
        this.properties = properties;
    }


    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2Server() throws SQLException {
        val settings = this.properties.getSettings();
        val args = new ArrayList<String>();

        if (DEFAULT_TCP_PORT != settings.getPort()) {
            args.add("-tcpPort");
            args.add(String.valueOf(settings.getPort()));
        } else {
            args.add("-tcpPort");
            args.add(String.valueOf(DEFAULT_TCP_PORT));
        }

        if ((settings.isTrace())) {
            args.add("-trace");
        }
        if (settings.isTcpAllowOthers()) {
            args.add("-tcpAllowOthers");
        }

        return Server.createTcpServer(args.toArray(new String[args.size()]));
    }
}
