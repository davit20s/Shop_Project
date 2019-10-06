package com.test.service;

import com.sun.corba.se.impl.orbutil.closure.Future;
import com.test.Enums.Status;
import com.test.exceptions.InvalidParamException;
import com.test.exceptions.NotFoundException;
import com.test.model.User;
import com.test.repository.UserRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class UserServiceImpl implements UserService {

    public static final long twentyfourhours = 300000;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Transactional
    public void save(String name, String email, String password) {
        User user = userRepository.getByEmail(email);

        RandomString randomString = new RandomString();

        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setStatus(Status.UNVERIFIED);
        user.setUpdated(new Timestamp(System.currentTimeMillis()));
        String code = randomString.nextString();

        while (userRepository.getByVerification(code) != null) {
            code = randomString.nextString();

        }
        user.setVerification(code);


        userRepository.save(user);

        String text = "Your verification code is" + user.getVerification();

        emailService.sendSimpleMessage(user.getEmail(), "verification code", text);


        user.setVerificationTime(System.currentTimeMillis());

    }

    public void resetPasswordRequest(String email) {
        User user = userRepository.getByEmail(email);

        RandomString randomString = new RandomString();

        if (user != null) {
            user.setResetPasswordCode(randomString.nextString());
            String text = "Your reset password code is" + user.getResetPasswordCode();
            emailService.sendSimpleMessage(user.getEmail(), "reset password code", text);
            user.setResetPasswordTime(System.currentTimeMillis());
            userRepository.save(user);
        } else throw new InvalidParamException("Wrong mail");
    }

    @Override
    public Page<User> getUserPage( int page,int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> userPage=userRepository.findAll(pageable);
        return userPage;
    }

    public void login(String email, String password) {
        User user = userRepository.getByEmail(email);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                if (user.getStatus().equals(Status.UNVERIFIED)) {
                    try {
                        throw new InvalidParamException("confirm verification code");
                    } catch (InvalidParamException e) {
                        e.printStackTrace();
                    }
                } else
                    System.out.println("Ok!");
            } else
                throw new InvalidParamException("incorrect password");
        } else
            throw new NotFoundException("email is not found");
    }

    @Transactional
    public void verify(String email, String verification) throws InvalidParamException {
        User user = userRepository.getByEmail(email);
        if (user != null) {
            if (verification.equals(user.getVerification())) {
                if (System.currentTimeMillis() - user.getVerificationTime() <= twentyfourhours) {
                    user.setStatus(Status.VERIFIED);
                    user.setVerification(null);
                    userRepository.save(user);

                } else
                    throw new InvalidParamException("Your verification code is expired");
            } else
                throw new NotFoundException("Email or password is incorrect");
        } else throw new NotFoundException("User not found");
    }


    @Async
    public CompletableFuture<User> getByEmail(String email) {
        User user = userRepository.getByEmail(email);
        return CompletableFuture.completedFuture(user);
    }


    @Override
    @Transactional
    public Optional<User> getById(int id) {
        Optional<User> user = userRepository.findById(id);

        return user;
    }


    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void update(User user) {

    }

    @Transactional
    public void saveByJson(User user) {

        User user1 = userRepository.getByEmail(user.getEmail());

        if (user1 != null) {
            throw new InvalidParamException("Mail is exist");
        } else {

            RandomString randomString = new RandomString();

            String code = randomString.nextString();

            while (userRepository.getByVerification(code) != null) {
                code = randomString.nextString();

            }
            user.setVerification(code);

            String text = "Your verification code is" + user.getVerification();

            emailService.sendSimpleMessage(user.getEmail(), "verification code", text);

            user.setVerificationTime(System.currentTimeMillis());

            userRepository.save(user);


        }
    }


}
