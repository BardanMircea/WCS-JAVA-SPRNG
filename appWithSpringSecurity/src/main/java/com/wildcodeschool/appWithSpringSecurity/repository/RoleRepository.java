package com.wildcodeschool.appWithSpringSecurity.repository;

import com.wildcodeschool.appWithSpringSecurity.entity.Role;
import com.wildcodeschool.appWithSpringSecurity.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleEnum name);
}