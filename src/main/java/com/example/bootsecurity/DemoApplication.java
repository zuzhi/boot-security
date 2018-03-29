package com.example.bootsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * DemoApplication
 *
 * @author zuzhi
 * @date 29/03/2018
 */
@SpringBootApplication
public class DemoApplication {

    @RestController
    class HelloController {
        @RequestMapping(value = "/", method = RequestMethod.GET)
        public String hello() {
            return "Hello, World!";
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
