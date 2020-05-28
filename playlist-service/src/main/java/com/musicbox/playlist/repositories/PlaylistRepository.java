package com.musicbox.playlist.repositories;

import com.musicbox.playlist.models.Playlist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends CrudRepository<Playlist, Long> {
    Playlist findPlaylistByTitle(String title);
//    Playlist addSongToPlaylist(Song song, Playlist playlist);
    long findIdByTitle(String title);
    Playlist findPlaylistById(long id);
//    Playlist deleteSongFromPlaylist(Song song, Playlist playlist);
}
