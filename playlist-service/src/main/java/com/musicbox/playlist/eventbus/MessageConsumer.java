package com.musicbox.playlist.eventbus;

import com.google.gson.Gson;
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