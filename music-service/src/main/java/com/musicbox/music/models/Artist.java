package com.musicbox.music.models;

import javax.persistence.*;

@Entity
@Inheritance(
        strategy = InheritanceType.JOINED
)
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @Column(nullable = false)
    String name;
}
