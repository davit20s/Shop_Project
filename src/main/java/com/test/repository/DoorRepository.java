package com.test.repository;

import com.test.model.Door;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoorRepository extends JpaRepository<Door, Integer> {

    Page<Door> findAll(Pageable pageable);
}
