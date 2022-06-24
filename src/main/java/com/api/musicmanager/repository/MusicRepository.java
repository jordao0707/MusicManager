package com.api.musicmanager.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.musicmanager.dto.MusicDTO;
import com.api.musicmanager.model.Music;
import com.api.musicmanager.repository.implementation.MusicRepositoryImpl;

@Repository
public interface MusicRepository extends  JpaRepository<Music, UUID>, MusicRepositoryImpl<Music,UUID>{
    public boolean existsByLink(String link);
    public Page<Music> filter(Pageable page, MusicDTO music);
}