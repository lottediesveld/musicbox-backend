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
            var song1 = new Song("Love the way you lie", "Recovery", "Eminem");
            var song2 = new Song("Water", "It Was Good Until It Wasn't", "Kehlani");
            var song3 = new Song("Rope", "Wasting Light", "Foo Fighters");
            var song4 = new Song("Seduction", "Recovery", "Eminem");
            var song5 = new Song("What Now", "Unapologetic", "Rihanna");
            var song6 = new Song("Still D.R.E.", "2001", "Dr. Dre");

            song1 = songRepository.save(song1);
            song2 = songRepository.save(song2);
            song3 = songRepository.save(song3);
            song4 = songRepository.save(song4);
            song5 = songRepository.save(song5);
            song6 = songRepository.save(song6);
        };
    }
}
