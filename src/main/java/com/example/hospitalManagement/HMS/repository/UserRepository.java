package com.example.hospitalManagement.HMS.repository;

import com.example.hospitalManagement.HMS.Domain.Role;
import com.example.hospitalManagement.HMS.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);

    List<User> findAllByRole(Role role);
}