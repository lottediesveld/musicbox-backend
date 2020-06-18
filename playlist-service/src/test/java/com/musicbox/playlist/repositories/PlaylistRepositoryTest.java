package com.musicbox.playlist.repositories;

import com.musicbox.playlist.models.Playlist;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
class PlaylistRepositoryTest {

    @Autowired
    private PlaylistRepository subject;

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
        Playlist testPlaylist = new Playlist(1, "testplaylist");
        subject.save(testPlaylist);
        Playlist isThisTestPlaylist = subject.findPlaylistByTitle("testplaylist");

        assertThat(isThisTestPlaylist.getTitle(),  equalTo("testplaylist"));
    }
}