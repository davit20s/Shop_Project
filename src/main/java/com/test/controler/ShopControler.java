package com.test.controler;

import com.test.model.Shop;
import com.test.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/shop")
public class ShopControler {

    @Autowired
    private ShopService shopService;

    @PostMapping
    public ResponseEntity save(@RequestParam(value = "name", required = false) String name,
                               @RequestParam("street") String street) {

        shopService.save(name, street);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity getAll(@PageableDefault Pageable pageable){
        Page<Shop> shopList;
        shopList = shopService.getAll(pageable);
        return ResponseEntity.ok(shopList);

    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam("id") int id) {
         shopService.delete(id);
         return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getById(@PathVariable("id") int id){
        Optional<Shop> shopOptional =shopService.getById(id);
        return ResponseEntity.ok(shopOptional);
    }

    @PostMapping("/json")
    public ResponseEntity saveByJson(@RequestBody Shop shop){

        shopService.saveByJson(shop);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity update(@Valid @RequestBody Shop shop){

        shopService.update(shop);
        return ResponseEntity.ok().build();
    }

}
