package com.musicbox.music.repositories;

import com.musicbox.music.models.Song;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends CrudRepository<Song, Long> {
    Song findSongById(Long id);

    Song findSongByTitleContainingIgnoreCase(String title);
    Song findSongByArtistContainingIgnoreCase(String artist);
    Song findSongByAlbumContainingIgnoreCase(String album);
    Song findSongByTitleAndAlbum(String title, String album);
    Song findSongByTitleAndArtist(String title, String artist);
    Long findIdByTitle(String title);
}
