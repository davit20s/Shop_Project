package com.test.config;

import com.test.model.User;
import com.test.repository.UserRepository;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.sql.Timestamp;
import java.util.List;

@Configuration
@EnableScheduling
public class SpringConfig {

    @Autowired
    private UserRepository userRepository;


    @Scheduled(cron = "0 03 18 * * MON")
    public void springSchedule(){

            User user = userRepository.getByEmail("davit20s@gmail.com");

            user.setUpdated(new Timestamp(System.currentTimeMillis()));
            userRepository.save(user);
        System.out.println("Fixed delay task - " + System.currentTimeMillis() / 5000);
        }
    }

