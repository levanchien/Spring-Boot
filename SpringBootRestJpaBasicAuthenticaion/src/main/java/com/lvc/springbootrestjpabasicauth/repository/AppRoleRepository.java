package com.lvc.springbootrestjpabasicauth.repository;

import com.lvc.springbootrestjpabasicauth.domain.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppRoleRepository extends JpaRepository<AppRole, Long> {

    @Query(value = "SELECT Role_Name FROM App_ROLE, App_USER, USER_Role " +
            "WHERE App_User.User_Id = :userId AND App_USER.User_Id = USER_Role.User_Id " +
            "AND USER_Role.Role_Id = App_Role.Role_ID", nativeQuery = true)
    List<String> getUserRole(@Param("userId") Long id);
}