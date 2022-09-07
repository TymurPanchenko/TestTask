package com.testtask.init;

import com.testtask.model.User;
import com.testtask.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Init implements ApplicationRunner {
    private UserRepository userRepository;

    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");


    @Autowired
    public Init(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws ParseException {
        long count = userRepository.count();

        if (count == 0) {
            User p1 = new User();

            p1.setFirstName("John");
            p1.setLastName("Johns");

            Date d1 = df.parse("1999-11-11");
            p1.setDateOfBirth(d1);
            //
            User p2 = new User();

            p2.setFirstName("Van");
            p2.setLastName("Vans");

            Date d2 = df.parse("1998-10-10");
            p2.setDateOfBirth(d2);

            userRepository.save(p1);
            userRepository.save(p2);
        }
    }
}
