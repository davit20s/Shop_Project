package com.test.service;

import com.test.exceptions.InvalidParamException;
import com.test.model.User;
import org.springframework.data.domain.Page;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface UserService {

    void save(String name, String email, String password);
    List<User> getAll();
    Optional<User> getById(int id);
    void update(User user);
    void saveByJson(User user);
    CompletableFuture<User> getByEmail(String email);
    void verify(String email, String verification) throws InvalidParamException;
    void login(String email, String password);
    void resetPasswordRequest(String email);

    Page<User> getUserPage(int size,int page);


}
