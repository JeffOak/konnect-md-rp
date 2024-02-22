package com.skylab;

import com.skylab.core.database.DatabaseCreation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class KonnectMdRp {

    public static void main(String[] args) {
        new DatabaseCreation().verifyDatabase();
        SpringApplication.run(KonnectMdRp.class, args);
    }
}
