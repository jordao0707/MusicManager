package com.api.musicmanager.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.musicmanager.dto.MusicDTO;
import com.api.musicmanager.model.Music;
import com.api.musicmanager.service.MusicService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/musics")
public class MusicController {
    final MusicService musicService;

    public MusicController(MusicService musicService) {
        this.musicService = musicService;
    }

    @PostMapping()
    public ResponseEntity<Object> saveMusic(@RequestBody @Valid MusicDTO music) {
        if(this.musicService.existsByLink(music.getLink()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This link has already been registred");
        
        return ResponseEntity.ok(this.musicService.saveMusic(music));
    }

    @GetMapping()
    public ResponseEntity<Page<Music>> getMusic(
        @PageableDefault(
            page = 0 , 
            size = 2, 
            sort = "name", 
            direction = Sort.Direction.ASC) Pageable page) {
        return ResponseEntity.status(HttpStatus.OK).body(this.musicService.getAllMusics(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getMusicById(@PathVariable() UUID id) {
        if(!this.musicService.existsById(id))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This id not exists !");

        return ResponseEntity.status(HttpStatus.OK).body(this.musicService.getMusicById(id));
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMusicById(@PathVariable() UUID id){
        var music = this.musicService.getMusicById(id); 
        if(music.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This id not exists !");
        this.musicService.deleteMusic(music.get());
        return ResponseEntity.status(HttpStatus.OK).body(music);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMusicById(@PathVariable() UUID id, @RequestBody @Valid MusicDTO music){ 
        if(!this.musicService.existsById(id))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This id not exists !");
        return ResponseEntity.status(HttpStatus.OK).body(this.musicService.updateMusic(id,music));
    }


}
