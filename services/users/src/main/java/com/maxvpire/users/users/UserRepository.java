package com.maxvpire.users.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    @Query("select u from  User u where u.mainId = ?1")
    Optional<User> findUserByMainId(String mainId);
}
