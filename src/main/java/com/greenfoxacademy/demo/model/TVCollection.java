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
public class TVCollection {

    private int page;
    private List<TV> results;
    private int total_pages;
    private int total_results;

}
