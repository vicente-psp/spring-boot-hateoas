package com.vicente.springboothateoas.repositories;

import com.vicente.springboothateoas.entities.People;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeopleRepository extends JpaRepository<People, Long> {
}
