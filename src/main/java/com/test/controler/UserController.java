package com.test.controler;

import com.test.exceptions.InvalidParamException;
import com.test.model.User;
import com.test.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity save(@RequestParam(value = "name") String name,
                               @RequestParam (value = "email") String email,
                               @RequestParam(value = "password")String password
                               ){
        userService.save(name,email,password);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity getAll(){
        logger.info("Gated all user");
        List<User> userList;
        userList = userService.getAll();
        return ResponseEntity.ok(userList);

    }
    @GetMapping(value = "/email")
    public ResponseEntity getUserByEmail(@RequestParam(value = "email") String email){
        userService.getByEmail(email);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/verify")
    public ResponseEntity verify(@RequestParam (value = "email") String email,
                                 @RequestParam (value = "verification") String verification) throws InvalidParamException {
        userService.verify(email, verification);

        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/resetpasswordrequest")
    public ResponseEntity resetPasswordRequest(@RequestParam (value = "email")String email){

        userService.resetPasswordRequest(email);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestParam (value = "email")String email,
                                @RequestParam (value = "password")String password){
        userService.login(email,password);
        return ResponseEntity.ok().build();
    }



    @PostMapping("/json")
    public ResponseEntity saveByJson(@RequestBody User user){
        userService.saveByJson(user);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/getUserPage/{page}/{size}")
    public ResponseEntity getPages(@PathVariable int  page,@PathVariable int size ){

        Page<User> userPage=userService.getUserPage(page,size);
        return ResponseEntity.ok(userPage);

    }
}
