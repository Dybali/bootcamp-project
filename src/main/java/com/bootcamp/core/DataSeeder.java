package com.bootcamp.core;

import com.bootcamp.entity.Instructor;
import com.bootcamp.repository.InstructorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataSeeder implements CommandLineRunner {

    private final InstructorRepository instructorRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(InstructorRepository instructorRepository, PasswordEncoder passwordEncoder) {
        this.instructorRepository = instructorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (instructorRepository.count() > 0) {
            return;
        }

        Instructor instructor = new Instructor();
        instructor.setFirstName("Demo");
        instructor.setLastName("Instructor");
        instructor.setEmail("instructor@demo.com");
        instructor.setDateOfBirth(LocalDate.of(1990, 1, 1));
        instructor.setNationalityIdentity("11111111111");
        instructor.setCompanyName("Bootcamp Inc.");
        instructor.setPasswordHash(passwordEncoder.encode("Password123!"));
        instructor.setPasswordSalt(null);

        instructorRepository.save(instructor);
    }
}

