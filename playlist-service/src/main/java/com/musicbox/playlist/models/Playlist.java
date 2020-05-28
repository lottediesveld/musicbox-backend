package com.musicbox.playlist.models;

import javax.persistence.*;
import java.util.Collection;
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

    public Playlist() {
    }

    public Playlist(long id, String title, Song... songs) {
        this.id = id;
        this.title = title;
//        this.songs = Stream.of(songs).collect(Collectors.toSet());
//        this.songs.forEach(x -> x.getPlaylists().add(this));
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
