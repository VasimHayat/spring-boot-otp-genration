package com.app.auth.controller;

import com.app.auth.beans.EOUser;
import com.app.auth.service.AuthService;
import com.app.auth.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path="/auth")
@CrossOrigin(origins = "*")
public class AuthController {




    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;

    @GetMapping(path="otp/{email}")
    public Map<String,String> getUser(@PathVariable("email") String  email) {
       return  Collections.singletonMap("otp", String.valueOf(this.authService.generateOtp(email)));
    }

    @PostMapping(path="otp_verify")
    public EOUser addUser(@RequestBody Map<String ,Object> objMap) {
        return this.authService.verifyOtp(objMap);
    }
}
