package com.bitbake.ebilli.controller;

import com.bitbake.ebilli.entity.User;
import com.bitbake.ebilli.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path="/addUser")
    public @ResponseBody
    ResponseEntity<String> addNewUser (@RequestBody User user) throws Exception {
        User savedUser = userService.addUser(user);
        if (savedUser == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User with email " + user.getUserName() + " present");
        return ResponseEntity.status(HttpStatus.OK).body(savedUser.toString());
    }
}
