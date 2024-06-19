package org.example.wigell_padel;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WigellPadelApplication {

    public static void main(String[] args) {
        //PropertyConfigurator.configure("src/main/resources/log4j.properties");
        SpringApplication.run(WigellPadelApplication.class, args);
    }

}
