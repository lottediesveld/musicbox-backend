package com.musicbox.music.controllers;

import com.musicbox.music.models.Song;
import com.musicbox.music.repositories.SongRepository;
import com.musicbox.music.services.SongService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SongController {
    private final SongService songService;
    private final SongRepository songRepo;

    public SongController(SongService songService, SongRepository songRepo) {
        this.songService = songService;
        this.songRepo = songRepo;
    }

    @RequestMapping(value = REST_URI_Constant.idByTitle, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Long getId(@RequestParam("title") String title){
        return songService.getId(title);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = REST_URI_Constant.deletesong, method = RequestMethod.DELETE)
    public void delete(@RequestBody Song song) {
        songService.deleteSong(song);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = REST_URI_Constant.allsongs, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Iterable<Song> allSongs() {
        return songService.allsongs();
    }

    @RequestMapping(value = REST_URI_Constant.songById, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Song getSongByTitle(@RequestParam("id") Long id) {
        System.out.println(id);
        return songService.getById(id);
    }

    @RequestMapping(value = REST_URI_Constant.songByTitle, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Song getSongByTitle(@RequestParam("title") String title) {
        return songService.getByTitle(title);
    }

    @RequestMapping(value = REST_URI_Constant.songByTitleAndAlbum, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Song getSongByTitleAndAlbum(@RequestParam("title") String title, @RequestParam("album") String album) {
        return songService.getByNameAndAlbum(title, album);
    }

    @RequestMapping(value = REST_URI_Constant.songByTitleAndArtist, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Song getSongByTitleAndArtist(@RequestParam("title") String title, @RequestParam("artist") String artist) {
        return songService.getByTitleAndArtist(title, artist);
    }

    @RequestMapping(value = REST_URI_Constant.searchSong, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Iterable<Song> searchSongs(@RequestParam("search") String search) {
        return songService.searchSongs(search);
    }
}
