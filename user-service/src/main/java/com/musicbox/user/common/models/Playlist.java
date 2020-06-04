package com.musicbox.user.common.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Playlist {
    private long id;

    private String title;

    public Collection<Song> getSongs() {
        return songs;
    }

    private Set<Song> songs;

    public Playlist() {
    }

    public Playlist(long id, String title) {
        this.id = id;
        this.title = title;
        this.songs = new HashSet<Song>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }
}
