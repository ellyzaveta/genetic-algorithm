package com.app.geneticalgorithm.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Solution {
    private List<City> cities = new CopyOnWriteArrayList<>();
    private double pathLength = 0.0;
    private List<List<Path>> paths = new CopyOnWriteArrayList<>();
    private Integer currentIterationNumber;

    public Solution(List<City> cities) {
        this.cities = cities;
    }

    public Solution(List<City> cities, double pathLength) {
        this.cities = cities;
        this.pathLength = pathLength;
    }
}
