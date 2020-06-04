package com.musicbox.playlist.models;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Entity
@Inheritance(
        strategy = InheritanceType.JOINED
)
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String title;

    public Collection<Song> getSongs() {
        return songs;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "song_playlist",
            joinColumns = @JoinColumn(name = "playlist_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "song_id", referencedColumnName = "id"))
    private Set<Song> songs;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinTable(name = "user")
//    public getUser() {
//        return user;
//    }

    public Playlist() {
        this.songs = new HashSet<Song>();
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

    public void removeFromPlaylist(Long songid){
        this.songs.removeIf(song -> song.getId() == songid);
    }
}
