package com.api.musicmanager.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.musicmanager.dto.MusicDTO;
import com.api.musicmanager.model.Music;
import com.api.musicmanager.repository.MusicRepository;

@Service
public class MusicService {

    public final MusicRepository musicRepository;

    public MusicService(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }

    @Transactional
    public Music saveMusic(MusicDTO music) {
        
        Music newMusic = new Music();
        System.out.println(music);
        BeanUtils.copyProperties(music, newMusic);
        this.setOrigin(newMusic);
        return musicRepository.save(newMusic);
    }

    public Page<Music> getAllMusics(Pageable page) {
        return this.musicRepository.findAll(page);
    }

    public boolean existsByLink(String link) {
        return this.musicRepository.existsByLink(link);
    }
    public boolean existsById(UUID id) {
        return this.musicRepository.existsById(id);
    }
    public Optional<Music> getMusicById(UUID id) {
        return this.musicRepository.findById(id);
    }

    public void deleteMusic(Music music) {
        this.musicRepository.delete(music);
    }

    public Object updateMusic(UUID id,MusicDTO music) {
        var newMusic = new Music();
        BeanUtils.copyProperties( music, newMusic);
        newMusic.setId(id);
        this.setOrigin(newMusic); 
        return this.musicRepository.save(newMusic);
    }

    private void setOrigin(Music music){
        music.setOrigin(music.getLink().split("\\.")[1]);
    }

}
