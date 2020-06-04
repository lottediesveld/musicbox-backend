package com.musicbox.music;

import com.musicbox.music.models.Song;
import com.musicbox.music.repositories.SongRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@EnableEurekaClient
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, RepositoryRestMvcAutoConfiguration.class})
public class MusicApplication {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(MusicApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(SongRepository songRepository) {
        return args -> {
            var song1 = new Song(1, "Love the way you lie", "Recovery", "Eminem");
            var song2 = new Song(2, "Water", "It Was Good Until It Wasn't", "Kehlani");
            var song3 = new Song(3, "Rope", "Wasting Light", "Foo Fighters");

            song1 = songRepository.save(song1);
            song2 = songRepository.save(song2);
            song3 = songRepository.save(song3);
        };
    }
}
