package com.api.musicmanager.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "MM_MUSIC")
@Data
public class Music implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, unique = false, length = 150)
    private String name;
    @Column(nullable = false, unique = false, length = 150)
    private String artist;
    @Column(nullable = true, unique = false, length = 150)
    private String album;
    @Column(nullable = false)
    private String origin;
    @Column(nullable = false, unique = true)
    private String link;

}
