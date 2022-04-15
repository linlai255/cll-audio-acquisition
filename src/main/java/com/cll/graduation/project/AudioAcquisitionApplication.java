package com.cll.graduation.project;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class AudioAcquisitionApplication {
    public static void main(String[] args) {
        SpringApplication.run(AudioAcquisitionApplication.class, args);
        log.info("==========================================");
        log.info("============== start success =============");
        log.info("==========================================");
    }
}
