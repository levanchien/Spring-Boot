package com.lvc.springbootrestjpabasicauth.controller;

import com.lvc.springbootrestjpabasicauth.domain.AppUser;
import com.lvc.springbootrestjpabasicauth.repository.AppUserRepository;
import com.lvc.springbootrestjpabasicauth.services.TokenAuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@RestController
public class MainController {

    @Autowired
    AppUserRepository appUserRepository;

    @GetMapping("/")
    public String homePage() {
        return "Home page";
    }

    @GetMapping("/login")
    public ResponseEntity loginPage(@RequestParam String username, @RequestParam String password) {
        Optional<AppUser> optional = appUserRepository.findAppUserByUserName(username);
        log.info(optional.get().getEncrytedPassword());
        log.info(bCryptPasswordEncoder().encode(password));
        if (optional.isPresent() && !bCryptPasswordEncoder().encode(password).equals(optional.get().getEncrytedPassword())) {
            return ResponseEntity.ok()
                    .header("Authorization", TokenAuthenticationService.token(username))
                    .body(null);
        }

        return ResponseEntity.status(403).build();
    }

    @GetMapping("/user")
    public String userPage() {
        return "User Page";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "Admin Page";
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
