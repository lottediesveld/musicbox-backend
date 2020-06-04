package com.musicbox.playlist.models;

import javax.persistence.*;
import java.util.*;


@Entity
@Inheritance(
        strategy = InheritanceType.JOINED
)
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private long userId;

    @Column(nullable = false)
    private String title;

    public List<Song> getSongs() {
        return songs;
    }

//    @ManyToMany(fetch = FetchType.EAGER)
//    private List<Long> songIds;

//    @Column(nullable = false)
//    private HashSet<Long> songs;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "song_playlist",
            joinColumns = @JoinColumn(name = "playlist_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "song_id", referencedColumnName = "id"))
    private List<Song> songs;

    public Playlist() {
        this.songs = new ArrayList<>();
    }

    public Playlist(long id, long userId, String title) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.songs = new ArrayList<>();
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

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public void addToPlaylist(Song song) {
        songs.add(song);
    }

    public void removeFromPlaylist(Long songid){
        this.songs.removeIf(song -> song.getId() == songid);
    }
}
