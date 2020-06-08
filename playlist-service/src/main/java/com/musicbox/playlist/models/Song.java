package com.musicbox.playlist.models;

import javax.persistence.*;

@Entity
@Inheritance(
        strategy = InheritanceType.JOINED
)
public class Song {
    @Id
    private long id;

    public Song() {
    }

    public Song(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
