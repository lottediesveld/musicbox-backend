package com.musicbox.music.services;

import com.musicbox.music.models.Song;
import com.musicbox.music.repositories.SongRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public Song getById(Long id) { return songRepo.findSongById(id); }

    public Song getByTitle(String title) {
        return songRepo.findSongByTitleContainingIgnoreCase(title);
    }

    public Song getByArtist(String Artist) {
        return songRepo.findSongByArtistContainingIgnoreCase(Artist);
    }

    public Song getByAlbum(String Album) {
        return songRepo.findSongByAlbumContainingIgnoreCase(Album);
    }

    public Song getByNameAndAlbum(String name, String album) {
        return songRepo.findSongByTitleAndAlbum(name, album);
    }

    public Song getByTitleAndArtist(String title, String artist) {
        return songRepo.findSongByTitleAndArtist(title, artist);
    }

    public Iterable<Song> searchSongs(String search) {
        List<Song> foundSongs = new ArrayList<>();
        Iterable<Song> allSongs = songRepo.findAll();
        for (Song song: allSongs) {
            System.out.println(song.getTitle());
            if (song.getTitle().toLowerCase().equals(search.toLowerCase())){
                foundSongs.add(song);
            } else if (song.getArtist().toLowerCase().equals(search.toLowerCase())){
                foundSongs.add(song);
            } else if (song.getAlbum().toLowerCase().equals(search.toLowerCase())){
                foundSongs.add(song);
            } else if (song.getTitle().toLowerCase().contains(search.toLowerCase())){
                foundSongs.add(song);
            } else if (song.getArtist().toLowerCase().contains(search.toLowerCase())){
                foundSongs.add(song);
            } else if (song.getAlbum().toLowerCase().contains(search.toLowerCase())){
                foundSongs.add(song);
            }
            System.out.println(foundSongs.size());
        }
//        if (getByTitle(search) != null){
//            foundSongs.add(getByTitle(search));
//        } else if (getByArtist(search) != null) {
//            foundSongs.add(getByArtist(search));
//        } else if (getByAlbum(search) != null) {
//            foundSongs.add(getByAlbum(search));
//        }
        return foundSongs;
    }

    //TODO: make artist only
    public void deleteSong(Song song) {
        songRepo.delete(song);
    }

}
