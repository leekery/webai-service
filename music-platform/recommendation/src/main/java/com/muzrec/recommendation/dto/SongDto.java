package com.muzrec.recommendation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SongDto {

    private String title;
    private String artist;
    private double similarity;

    public SongDto(String title, String artist, double similarity) {
        this.title = title;
        this.artist = artist;
        this.similarity = similarity;
    }
}

