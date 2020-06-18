package com.musicbox.user.repositories;

import com.musicbox.user.common.models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static com.musicbox.user.common.security.Role.USER;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserRepositoryIntegrationTests {

    @Autowired
    private UserRepository subject;

    private PasswordEncoder passwordEncoder;

    @Before
    public void setUp() {

    }


    @After
    public void tearDown() {
        subject.deleteAll();
    }

    @Test
    public void shouldSaveAndFetch() {
        User testUser = new User("Test", "Test", "Testory", "test212313212312312", "testaccount@test.nl", true, true, true, true, USER.getGrantedAuthorities());
        subject.save(testUser);
        User isThisTestUser = subject.findUserByEmail("testaccount@test.nl");

        assertThat(isThisTestUser.getEmail(),  equalTo("testaccount@test.nl"));
    }
}