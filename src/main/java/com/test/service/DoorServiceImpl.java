package com.test.service;

import com.test.model.Door;
import com.test.model.Shop;
import com.test.repository.DoorRepository;
import com.test.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Service
public class DoorServiceImpl implements DoorService {

    @Autowired
    private DoorRepository doorRepository;

    @Autowired
    private DoorService doorService;


    @Transactional
    public void save(String color, double width, double height, Shop shop_id) {

        Door door = new Door();

        door.setColor(color);
        door.setWidth(width);
        door.setHeight(height);
        door.setShop(shop_id);
        doorRepository.save(door);
    }


    public Page<Door> getAll(Pageable pageable) {
        return doorRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Optional<Door> getById(int id) {
        Optional<Door> door = doorRepository.findById(id);
        return door;
    }

    @Override
    @Transactional
    public void delete(int id) {
        doorRepository.deleteById(id);
    }


}
