package com.musicbox.playlist;

import com.musicbox.playlist.models.Playlist;
import com.musicbox.playlist.models.Song;
import com.musicbox.playlist.repositories.PlaylistRepository;
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
        SpringApplication. run(PlaylistApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(PlaylistService playlistService, PlaylistRepository playlistRepository) {
        return args -> {
            var playlist1 = new Playlist(1, "testplaylist");
            playlistService.newPlaylist(playlist1);
            //playlistRepository.save(playlist1);

            var song1 = new Song(1, "Love the way you lie", "Recovery", "Eminem");
            var song2 = new Song(2, "Water", "It Was Good Until It Wasn't", "Kehlani");
            var song3 = new Song(3, "Rope", "Wasting Light", "Foo Fighters");

            playlistService.addSongToPlaylist(song1, playlist1);
            playlistService.addSongToPlaylist(song2, playlist1);
            playlistService.addSongToPlaylist(song3, playlist1);

            //playlistRepository.save(playlistService.getById(1));
        };
    }
}
