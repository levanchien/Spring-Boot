package com.lvc.sbsecurityjpa.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class MainController {

    @GetMapping(value = {"/", "/welcome"})
    public String welcomePage() {
        return "Welcome to LOL";
    }

    @GetMapping(value = {"/admin"})
    public String adminPage(Principal principal) {
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        return "Admin page";
    }


}
