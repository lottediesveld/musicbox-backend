package com.musicbox.playlist.repositories;

import com.musicbox.playlist.models.Playlist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends CrudRepository<Playlist, Long> {
    Playlist findPlaylistByTitle(String title);
    Playlist findPlaylistByUserId(Long user_id);
    long findIdByTitle(String title);
    Playlist findPlaylistById(long id);
    void deletePlaylistById(long id);
}
