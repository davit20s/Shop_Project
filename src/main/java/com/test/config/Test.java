package com.test.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Test {
    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(11);
        String s = passwordEncoder.encode("d");
        System.out.println(s);

        System.out.println(System.currentTimeMillis());


    }


}
