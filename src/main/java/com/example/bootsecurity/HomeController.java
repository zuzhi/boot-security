package com.example.bootsecurity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.HEAD;

/**
 * HomeController
 *
 * @author zuzhi
 * @date 02/04/2018
 */
@RestController
public class HomeController {

    @RequestMapping(value = "/", method = {GET, HEAD})
    public Map home() {
        Map<String, String> map = new HashMap<>(1);
        map.put("msg", "hello, world");
        return map;
    }
}
