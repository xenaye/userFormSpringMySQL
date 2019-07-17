package com.xenaye.projects.userservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xenaye.projects.userservice.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
