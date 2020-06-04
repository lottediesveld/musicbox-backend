package com.musicbox.playlist.DTO;

import com.musicbox.playlist.models.Playlist;
import com.musicbox.playlist.models.Song;

public class AddToPlaylistDTO {
    Song song;
    Playlist playlist;

    public AddToPlaylistDTO() {
    }

    public AddToPlaylistDTO(Song song, Playlist playlist) {
        this.song = song;
        this.playlist = playlist;
    }

    public Song getSong() {
        return song;
    }

    public Playlist getPlaylist() {
        return playlist;
    }
}
