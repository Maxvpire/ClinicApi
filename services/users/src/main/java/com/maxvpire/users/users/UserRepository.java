package com.maxvpire.users.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    @Query("select u from  User u where u.mainId = ?1")
    Optional<User> findUserByMainId(String mainId);

    Optional<User> findUserByUsername(String userName);

    @Query("SELECT u FROM User u WHERE u.username LIKE %:keyword%")
    List<User> findByUsernameContaining(@Param("keyword") String keyword);
}
