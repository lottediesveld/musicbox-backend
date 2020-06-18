package com.musicbox.user.controllers;

import com.google.gson.Gson;
import com.musicbox.user.common.dto.UserRegisterDTO;
import com.musicbox.user.common.models.User;
import com.musicbox.user.repositories.UserRepository;
import com.musicbox.user.services.UserService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static com.musicbox.user.common.security.Role.USER;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserControllerTest {

    private UserController subject;

    @Mock
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        subject = new UserController(userService, userRepository, passwordEncoder);
    }

    @Test
    public void shouldReturnNewCustomer() throws Exception {
        Gson gson = new Gson();
        UserRegisterDTO registerDTO = new UserRegisterDTO("testname", "testsurname", "testuser", "testmail@email.com", "12345");

        String json = gson.toJson(registerDTO);

        String result = subject.userRegister(json);

        assertEquals("saved", result);
    }

    @Test
    public void shouldGetAllCustomers() throws Exception   {
        User user1 = new User("victor", "leeuwen", "victory", "password",
                "test@test.com", true, true, true,
                true, USER.getGrantedAuthorities());

        User user2 = new User("Kees", "Janssen", "Kachel", "password",
                "kachel@test.com", true, true, true,
                true, USER.getGrantedAuthorities());

        given(userService.alluser()).willReturn(Arrays.asList(user1, user2));

        Iterable<User> response =  subject.allUsers();

        assertThat(response, Matchers.<User> iterableWithSize(2));
    }

    @Test
    public void shouldGetUserByUsername() throws Exception {
        User user = new User("Kees", "Janssen", "Kachel", "password",
                "kachel@test.com", true, true, true,
                true, USER.getGrantedAuthorities());

        given(userService.getByUsername("Kachel")).willReturn(user);

        User response = subject.getUserByUsername("Kachel");

        assertThat(response.getUsername(), is("Kachel"));
    }
}