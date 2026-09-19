package com.servicehub.servicehubapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.servicehub.servicehubapi.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}