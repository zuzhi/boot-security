package com.example.corespring.user;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author zuzhi
 * @date 04/04/2018
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @RequestMapping(value = "/user/user_details", method = GET)
    @ResponseBody
    public UserDetails currentUserAuthorities(Authentication authentication) {
        return (UserDetails) authentication.getPrincipal();
    }
}
