package com.bootcamp.repository;

import com.bootcamp.entity.Bootcamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface BootcampRepository extends JpaRepository<Bootcamp, Long> {
    Optional<Bootcamp> findByName(String name);
} 




