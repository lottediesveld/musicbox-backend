package com.musicbox.playlist.DTO;

public class AddToPlaylistDTO {
    Long song;
    Long playlist;

    public AddToPlaylistDTO() {
    }

    public AddToPlaylistDTO(Long song, Long playlist) {
        this.song = song;
        this.playlist = playlist;
    }

    public Long getSong() {
        return song;
    }

    public Long getPlaylist() {
        return playlist;
    }
}
