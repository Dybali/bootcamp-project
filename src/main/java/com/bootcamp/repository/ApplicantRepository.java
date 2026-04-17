package com.bootcamp.repository;

import com.bootcamp.entity.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
    Optional<Applicant> findByNationalityIdentity(String nationalityIdentity);
    Optional<Applicant> findByEmail(String email);
} 




