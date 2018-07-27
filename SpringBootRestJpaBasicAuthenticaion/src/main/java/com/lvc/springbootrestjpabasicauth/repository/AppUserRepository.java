package com.lvc.springbootrestjpabasicauth.repository;

import com.lvc.springbootrestjpabasicauth.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findAppUserByUserName(String userName);
}
