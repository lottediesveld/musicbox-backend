package com.musicbox.music.services;

import com.musicbox.music.models.Song;
import com.musicbox.music.repositories.SongRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class SongService {
    private final SongRepository songRepo;
    private final ModelMapper modelMapper;

    public SongService(SongRepository songRepo, ModelMapper modelMapper) {
        this.songRepo = songRepo;
        this.modelMapper = modelMapper;
    }

    public Song newSong(Song song) {
        songRepo.save(song);
        Song newsong = songRepo.findSongByTitleAndAlbum(song.getTitle(), song.getAlbum());
        return newsong;
    }
    public Long getId(String name){
        return songRepo.findIdByTitle(name);
    }


    public Iterable<Song> allsongs() {
        return songRepo.findAll();
    }

    public Song getByTitle(String title) {
        return songRepo.findSongByTitle(title);
    }

    public Song getByNameAndAlbum(String name, String album) {
        return songRepo.findSongByTitleAndAlbum(name, album);
    }

    public Song getByTitleAndArtist(String title, String artist) {
        return songRepo.findSongByTitleAndArtist(title, artist);
    }

    //TODO: make artist only
    public void deleteSong(Song song) {
        songRepo.delete(song);
    }

}
