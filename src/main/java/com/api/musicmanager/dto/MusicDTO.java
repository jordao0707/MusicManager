package com.api.musicmanager.dto;

import java.time.Instant;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class MusicDTO {
    @NotBlank
    @Size(max = 150)
    private String name;
    @NotBlank
    @Size(max=150)
    private String artist;
    @Size(max = 150)
    private String album;
    @NotBlank
    private String link;
}
