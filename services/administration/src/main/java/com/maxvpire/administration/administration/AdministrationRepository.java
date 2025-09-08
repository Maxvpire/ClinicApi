package com.maxvpire.administration.administration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdministrationRepository extends JpaRepository<Administration, String> {
    @Query("SELECT u FROM Administration u WHERE LOWER(u.firstname) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(u.lastname) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Administration> searchByFirstnameAndLastname(@Param("query") String query);

    @Query("SELECT u FROM Administration u WHERE u.phone LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Administration> searchByPhone(@Param("query") String query);

    Optional<Administration> findAdministrationsByPhone(String phoneNumber);
    List<Administration> findAdministrationsByGender(Gender gender);
    List<Administration> findAdministrationsByActive(Boolean active);
}
