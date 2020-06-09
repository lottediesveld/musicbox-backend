package com.musicbox.playlist.controllers;

import com.google.gson.Gson;
import com.musicbox.playlist.DTO.AddToPlaylistDTO;
import com.musicbox.playlist.models.Playlist;
import com.musicbox.playlist.repositories.PlaylistRepository;
import com.musicbox.playlist.services.PlaylistService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PlaylistController {

    private final PlaylistService playlistService;
    private final PlaylistRepository playlistRepo;

    public PlaylistController(PlaylistService playlistService, PlaylistRepository playlistRepo) {
        this.playlistService = playlistService;
        this.playlistRepo = playlistRepo;
    }

    @RequestMapping(value = REST_URI_Constant.findPlaylistIDByTitle, method = RequestMethod.GET)
    public @ResponseBody
    Long getId(@RequestParam("title") String title){
        return playlistService.getId(title);
    }

    //    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = REST_URI_Constant.deletePlaylist, method = RequestMethod.DELETE)
    public void delete(@RequestBody Playlist playlist) {
        playlistService.deletePlaylist(playlist);
    }

    @RequestMapping(value = REST_URI_Constant.findAllPlaylistsOfUser, method = RequestMethod.GET)
    public @ResponseBody
    Iterable<Playlist> allPlaylistsOfUser(@RequestParam("id") Long id) {
        Iterable<Playlist> playlists = playlistService.allplaylistsOfUser(id);
        return playlists;
    }

    @RequestMapping(value = REST_URI_Constant.findAllPlaylists, method = RequestMethod.GET)
    public @ResponseBody
    Iterable<Playlist> allPlaylists() {
        Iterable<Playlist> playlists = playlistService.allplaylists();
        return playlists;
    }

    @RequestMapping(value = REST_URI_Constant.findPlaylistByTitle, method = RequestMethod.GET)
    public @ResponseBody
    Playlist getPlaylistByTitle(@RequestParam("title") String title) {
        return playlistService.getByTitle(title);
    }

    @RequestMapping(value = REST_URI_Constant.findPlaylistById, method = RequestMethod.GET)
    public @ResponseBody
    Playlist getPlaylistById(@RequestParam("id") long id) {
        return playlistService.getById(id);
    }

    @RequestMapping(value = REST_URI_Constant.addSongToPlaylist, method = RequestMethod.POST)
    public @ResponseBody
    Playlist addSongToPlaylist(@RequestBody String dto) {
        Gson gson = new Gson();
        var dtoObject = gson.fromJson(dto, AddToPlaylistDTO.class);

        return playlistService.addSongToPlaylist(dtoObject.getSong(), dtoObject.getPlaylist());
    }

    @RequestMapping(value = REST_URI_Constant.deleteSongFromPlaylist, method = RequestMethod.POST)
    public @ResponseBody
    Playlist deleteSongFromPlaylist(@RequestBody String dto) {
        Gson gson = new Gson();
        var dtoObject = gson.fromJson(dto, AddToPlaylistDTO.class);

        return playlistService.deleteSongFromPlaylist(dtoObject.getSong(), dtoObject.getPlaylist());
    }

    @RequestMapping(value = REST_URI_Constant.createNewPlaylist, method = RequestMethod.POST)
    public @ResponseBody
    Playlist createNewPlaylist(@RequestParam("playlist") Playlist playlist) {
        return playlistService.newPlaylist(playlist);
    }
}
