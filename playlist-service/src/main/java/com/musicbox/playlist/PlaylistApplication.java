package com.musicbox.playlist;

import com.musicbox.playlist.services.PlaylistService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@EnableEurekaClient
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, RepositoryRestMvcAutoConfiguration.class})
@ComponentScan
public class PlaylistApplication {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(PlaylistApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(PlaylistService playlistService) {
        return args -> {
            var song1 = new Song(1, "Love the way you lie", "Recovery", "Eminem");
            var song2 = new Song(2, "Water", "It Was Good Until It Wasn't", "Kehlani");
            var song3 = new Song(3, "Rope", "Wasting Light", "Foo Fighters");

            song1 = playlistService.addSongToPlaylist(song);
            song2 = playlistRepository.save(song2);
            song3 = playlistRepository.save(song3);
        };
    }
}
