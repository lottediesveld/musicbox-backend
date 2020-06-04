package com.musicbox.playlist.services;

import com.musicbox.playlist.models.Playlist;
import com.musicbox.playlist.models.Song;
import com.musicbox.playlist.repositories.PlaylistRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlaylistService {
    private final PlaylistRepository playlistRepo;
    private final ModelMapper modelMapper;

    public PlaylistService(PlaylistRepository playlistRepo, ModelMapper modelMapper) {
        this.playlistRepo = playlistRepo;
        this.modelMapper = modelMapper;
    }

    public Playlist newPlaylist(Playlist playlist) {
        playlistRepo.save(playlist);
        Playlist newPlaylist = playlistRepo.findPlaylistById(playlist.getId());
        return newPlaylist;
    }

    public Playlist addSongToPlaylist(Song song, Playlist playlist) {
        Playlist existingPlaylist = playlistRepo.findPlaylistById(playlist.getId());
        existingPlaylist.addToPlaylist(song);
        playlistRepo.save(existingPlaylist);
        Playlist changedPlaylist = playlistRepo.findPlaylistById(playlist.getId());
        return changedPlaylist;
    }

    public Long getId(String title) {
        return playlistRepo.findIdByTitle(title);
    }

    public Iterable<Playlist> allplaylists(Long user_id) {
        List<Playlist> test = new ArrayList<>();
        test.add(playlistRepo.findPlaylistByUserId(user_id));
        return test;
    }

    public Playlist getById(long id) {
        return playlistRepo.findPlaylistById(id);
    }

    public Playlist getByTitle(String title) { return  playlistRepo.findPlaylistByTitle(title); }

    public Playlist deleteSongFromPlaylist(Song song, Playlist playlist) {
        Playlist test = playlistRepo.findPlaylistByTitle(playlist.getTitle());
        test.removeFromPlaylist(song.getId());
        playlistRepo.save(test);
        Playlist changedPlaylist = playlistRepo.findPlaylistById(playlist.getId());
        return changedPlaylist;
    }

    public void deletePlaylist(Playlist playlist) {
        playlistRepo.delete(playlist);
    }
}
