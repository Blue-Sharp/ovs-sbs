package de.bluesharp.sbs.ovs;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @NoArgsConstructor(access = AccessLevel.NONE)
    public static class Profile {
        public static final String LOCAL = "local";
        public static final String NOT_LOCAL = "!local";
    }
}
