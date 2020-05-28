package com.musicbox.playlist.controllers;

import com.musicbox.playlist.models.Playlist;
import com.musicbox.playlist.models.Song;
import com.musicbox.playlist.repositories.PlaylistRepository;
import com.musicbox.playlist.services.PlaylistService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PlaylistController {

    private final PlaylistService playlistServiceService;
    private final PlaylistRepository playlistRepo;

    public PlaylistController(PlaylistService playlistServiceService, PlaylistRepository playlistRepo) {
        this.playlistServiceService = playlistServiceService;
        this.playlistRepo = playlistRepo;
    }

    @RequestMapping(value = REST_URI_Constant.findPlaylistIDByTitle, method = RequestMethod.GET)
    public @ResponseBody
    Long getId(@RequestParam("title") String title){
        return playlistServiceService.getId(title);
    }

    //    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = REST_URI_Constant.deletePlaylist, method = RequestMethod.DELETE)
    public void delete(@RequestBody Playlist playlist) {
        playlistServiceService.deletePlaylist(playlist);
    }

    //    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = REST_URI_Constant.findAllPlaylists, method = RequestMethod.GET)
    public @ResponseBody
    Iterable<Playlist> allPlaylists() {
        return playlistServiceService.allplaylists();
    }

    @RequestMapping(value = REST_URI_Constant.findPlaylistByTitle, method = RequestMethod.GET)
    public @ResponseBody
    Playlist getPlaylistByTitle(@RequestParam("title") String title) {
        return playlistServiceService.getByTitle(title);
    }

    @RequestMapping(value = REST_URI_Constant.findPlaylistById, method = RequestMethod.GET)
    public @ResponseBody
    Playlist getPlaylistById(@RequestParam("id") long id) {
        return playlistServiceService.getById(id);
    }

    @RequestMapping(value = REST_URI_Constant.addSongToPlaylist, method = RequestMethod.POST)
    public @ResponseBody
    Playlist addSongToPlaylist(@RequestParam("song") Song song, @RequestParam("playlist") Playlist playlist) {
        return playlistServiceService.addSongToPlaylist(song, playlist);
    }

    @RequestMapping(value = REST_URI_Constant.deleteSongFromPlaylist, method = RequestMethod.POST)
    public @ResponseBody
    Playlist deleteSongFromPlaylist(@RequestParam("song") Song song, @RequestParam("playlist") Playlist playlist) {
        return playlistServiceService.deleteSongFromPlaylist(song, playlist);
    }

    @RequestMapping(value = REST_URI_Constant.createNewPlaylist, method = RequestMethod.POST)
    public @ResponseBody
    Playlist createNewPlaylist(@RequestParam("playlist") Playlist playlist) {
        return playlistServiceService.newPlaylist(playlist);
    }
}
