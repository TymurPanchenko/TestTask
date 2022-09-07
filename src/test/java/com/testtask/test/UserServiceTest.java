package com.testtask.test;

import com.testtask.model.User;
import com.testtask.repository.UserRepository;
import com.testtask.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    User expected;

    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    final Long ID = 1L;
    final String FIRST_NAME = "name";
    final String LAST_NAME = "surname";
    final Date BIRTH_DATE = df.parse("2000-12-12");;

    public UserServiceTest() throws ParseException {
    }


    @BeforeEach
    void setUp() {
        expected = new User();
        expected.setId(ID);
        expected.setFirstName(FIRST_NAME);
        expected.setLastName(LAST_NAME);
        expected.setDateOfBirth(BIRTH_DATE);
    }

    @Test
    void shouldCreateUser() {
        when(userRepository.save(any(User.class))).thenReturn(expected);

        User actual = userService.create(expected);

        verify(userRepository).save(any(User.class));
        Assertions.assertEquals(expected, actual);
    }


    @Test
    void shouldReadByIdAndReturnUser() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(expected));

        User actual = userService.readById(ID);

        verify(userRepository).findById(anyLong());
        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(expected.getId(), actual.getId());
    }

    @Test
    void shouldThrowEntityNotFoundExceptionReadById() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> {
            userService.readById(ID);
        });

    }
}
