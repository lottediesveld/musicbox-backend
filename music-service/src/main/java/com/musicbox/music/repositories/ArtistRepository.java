package com.musicbox.music.repositories;

import com.musicbox.music.models.Artist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends CrudRepository<Artist, Long> {
    Artist findArtistByName(String name);
}
