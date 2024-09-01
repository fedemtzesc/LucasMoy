package com.fdxsoft.lucas.controllers;

import com.fdxsoft.lucas.models.UserModel;
import com.fdxsoft.lucas.services.UserService;
import com.fdxsoft.lucas.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class SecurityController {
    @Autowired
    UserService userService;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("login")
    public String getLogin(@RequestBody UserModel userModel){
        UserModel loggedUser = userService.getLogin(userModel);
        if(loggedUser!=null){
            String tokenJWT = jwtUtil.create(String.valueOf(loggedUser.getId()), loggedUser.getEmail());
            return tokenJWT;
        }else{
            return "FAIL";
        }
    }
}
