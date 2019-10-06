package com.test.service;


import com.test.exceptions.NotFoundException;
import com.test.model.Shop;
import com.test.repository.ShopRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.lang3.RandomStringUtils;


import java.util.List;
import java.util.Optional;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ShopService shopService;

    @Transactional
    public void save(String name, String street) {
        Shop shop = new Shop();

        shop.setName(name);
        shop.setStreet(street);
        shopRepository.save(shop);
    }

    public Page<Shop> getAll(Pageable pageable) {
        return shopRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public void delete(int id) {
        shopRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<Shop> getById(int id){
        Optional<Shop> shop = shopRepository.findById(id);
        return shop;
    }


    public void saveByJson(Shop shop){
        shopRepository.save(shop);
    }
    @Test
    public void givenUsingApache_whenGeneratingRandomAlphabeticString_thenCorrect() {
        String generatedString = RandomStringUtils.randomAlphabetic(10);

        System.out.println(generatedString);
    }

    public void update(Shop shop) {
        Optional<Shop> optionalShop =shopRepository.findById(shop.getId());
        if(!optionalShop.isPresent()){
            throw new NotFoundException("Could not find shop with current id");
        }
         shopRepository.save(shop);

    }

}
