package com.greenfoxacademy.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TV {

        private String backdrop_path;
    private String first_air_date;
    private List<Integer> genre_ids;
    private int id;
    private String name;
    private List<String> origin_country;
    private String original_language;
    private String original_name;
    private String overview;
    private double popularity;
    private String poster_path;
    private double vote_average;
    private int vote_count;
}
