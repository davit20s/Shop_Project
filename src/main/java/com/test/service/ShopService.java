package com.test.service;

import com.test.model.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ShopService {

    void save(String name, String street);

    Page<Shop> getAll(Pageable pageable);

    void delete(int id);

    Optional<Shop> getById(int id);

    void saveByJson(Shop shop);

    void update(Shop shop);
    }



