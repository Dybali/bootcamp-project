package com.bootcamp.repository;

import com.bootcamp.entity.Blacklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface BlacklistRepository extends JpaRepository<Blacklist, Long> {
    Optional<Blacklist> findByApplicantId(Long applicantId);
} 


