package com.testtask.test;

import com.testtask.model.User;
import com.testtask.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class UserTest {

    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private UserRepository userRepository;

    @Test
    public void getByIdTest() throws ParseException {
        User expected = new User();
        expected.setFirstName("Bill");
        expected.setLastName("Bills");
        Date d1 = df.parse("1999-11-11");
        expected.setDateOfBirth(d1);
        expected = userRepository.save(expected);
        User actual = userRepository.findById(1L).get();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getByNonExistentIDTest() {
        User actual = userRepository.findById(10L).orElse(null);
        Assertions.assertNull(actual);
    }
}
