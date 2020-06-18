package com.musicbox.music.repositories;

import com.musicbox.music.models.Song;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
class SongRepositoryTest {
    @Autowired
    private SongRepository subject;

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
        Song testSong = new Song("Water", "It Was Good Until It Wasn't", "Kehlani");
        subject.save(testSong);
        Song isThisTestSong = subject.findSongByTitleContainingIgnoreCase("Water");

        assertThat(isThisTestSong.getTitle(),  equalTo("Water"));
    }
}