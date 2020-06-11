package com.musicbox.playlist.eventbus;

import com.google.gson.Gson;
import com.musicbox.playlist.DTO.RegisterDTO;
import com.musicbox.playlist.models.Playlist;
import com.musicbox.playlist.repositories.PlaylistRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

public class MessageConsumer {
    @Autowired
    PlaylistRepository playlistRepo;

    public MessageConsumer() {}

    @RabbitListener(queues = "#{newUserQueue.name}")
    @Transactional
    public void receiveNewUser(String message) throws IOException {
        Gson gson = new Gson();
        RegisterDTO user = gson.fromJson(message, RegisterDTO.class);
        if (playlistRepo.findPlaylistByUserId(user.getUserId()) == null) {
            Playlist newPlaylist = new Playlist(user.getUserId(), user.getUsername() + "splaylist");
            playlistRepo.save(newPlaylist);
        } else {
            System.out.println("Playlist already exists");
        }
    }

    @RabbitListener(queues = "#{deleteUserQueue.name}")
    @Transactional
    public void receiveDeleteUser(String message) throws IOException {
        Gson gson = new Gson();
        Long user_id = gson.fromJson(message, Long.class);
        for (Playlist playlist : playlistRepo.findAll() ) {
            if (playlist.getUserId() == user_id){
                playlistRepo.deletePlaylistById(playlist.getId());
                break;
            }
            System.out.println(playlist.getUserId());
        }
    }
}