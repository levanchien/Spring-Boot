package com.lvc.springbootrestjpabasicauth.services;

import com.lvc.springbootrestjpabasicauth.domain.AppUser;
import com.lvc.springbootrestjpabasicauth.repository.AppRoleRepository;
import com.lvc.springbootrestjpabasicauth.repository.AppUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    AppRoleRepository appRoleRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> optional = appUserRepository.findAppUserByUserName(username);
        if (!optional.isPresent()) {
            log.info("Sorry, " + username +" not found!" + optional.toString());
            throw new UsernameNotFoundException("Sorry, " + username +" not found!");
        }

        AppUser appUser = optional.get();
        log.info("Found User: " + appUser);

        List<String> ROLES = appRoleRepository.getUserRole(appUser.getUserId());
        List<GrantedAuthority> grantList = ROLES.stream()
                .filter(r -> !r.isEmpty())
                .map(GrantedAuthorityImpl::new)
                .collect(Collectors.toList());

        return new User(appUser.getUserName(), appUser.getEncrytedPassword(), grantList);
    }
}

class GrantedAuthorityImpl implements GrantedAuthority {
    private String role;
    private static final long serialVersionUID = -5246117266247684905L;

    GrantedAuthorityImpl(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role;
    }
}