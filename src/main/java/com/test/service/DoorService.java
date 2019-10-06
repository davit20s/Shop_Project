package com.test.service;

import com.test.model.Door;
import com.test.model.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface DoorService {

    void save(String color, double width, double height, Shop shop_id);
    void delete(int id);

    Page<Door> getAll(Pageable pageable);

    Optional<Door> getById(int id);
}
