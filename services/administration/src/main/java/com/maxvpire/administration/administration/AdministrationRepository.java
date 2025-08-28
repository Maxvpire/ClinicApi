package com.maxvpire.administration.administration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministrationRepository extends JpaRepository<Administration, String> {
}
