package com.test.controler;

import com.test.model.Door;
import com.test.model.Shop;
import com.test.service.DoorService;
import jdk.nashorn.internal.ir.Optimistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/door")
public class DoorControler {

    @Autowired
    private DoorService doorService;

    @GetMapping
//    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity getAll(@PageableDefault Pageable pageable) {
        Page<Door> doorList;
        doorList = doorService.getAll(pageable);
        return ResponseEntity.ok(doorList);

    }

    @PostMapping
    public ResponseEntity save(@RequestParam("color") String color,
                               @RequestParam("width") double width,
                               @RequestParam("height") double height,
                               @RequestParam("shop_id") Shop shop_id) {

        doorService.save(color, width, height, shop_id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity getById(@PathVariable("id") int id) {

        Optional<Door> doorOptional = doorService.getById(id);
        return ResponseEntity.ok(doorOptional);
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam("id") int id) {
        doorService.delete(id);
        return ResponseEntity.ok().build();
    }


}
