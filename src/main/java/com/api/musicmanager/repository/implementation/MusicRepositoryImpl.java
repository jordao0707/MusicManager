package com.api.musicmanager.repository.implementation;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.musicmanager.model.Music;

public interface MusicRepositoryImpl extends JpaRepository<Music, UUID> {
    
}
