package com.example.confluencetoolbe.repository;


import com.example.confluencetoolbe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    User findByUsernameOrEmail(String username, String email);

    User findByEmail(String email);

}
