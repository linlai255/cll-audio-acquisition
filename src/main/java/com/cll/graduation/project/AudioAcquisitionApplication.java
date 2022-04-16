package com.cll.graduation.project;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@Slf4j
@EnableSwagger2
public class AudioAcquisitionApplication {
    public static void main(String[] args) {
        SpringApplication.run(AudioAcquisitionApplication.class, args);
        log.info("==========================================");
        log.info("============== start success =============");
        log.info("==========================================");
    }
}
