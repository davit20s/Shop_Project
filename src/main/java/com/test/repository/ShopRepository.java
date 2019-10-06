package com.test.repository;

import com.test.model.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<Shop,Integer> {

//    void deleteById(int id);

    Page<Shop> findAll(Pageable pageable);

}
