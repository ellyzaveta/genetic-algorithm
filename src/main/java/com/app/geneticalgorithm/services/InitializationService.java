package com.app.geneticalgorithm.services;

import com.app.geneticalgorithm.models.City;
import com.app.geneticalgorithm.models.Solution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class InitializationService {

    public static List<Solution> initPopulation(List<City> cities, int populationSize) {
        List<Solution> population = new ArrayList<>();

        for (int i = 0; i < populationSize; i++) {
            population.add(getSolution(cities));
        }

        return population;
    }

    private static Solution getSolution(List<City> cities) {

        List<City> solutionCities = new CopyOnWriteArrayList<>(cities);

        Collections.shuffle(solutionCities, new Random());

        double generalPathLength = PathService.getGeneralPathLength(solutionCities);

        City firstCity = solutionCities.get(0);
        solutionCities.add(firstCity);

        return new Solution(new CopyOnWriteArrayList<>(solutionCities), generalPathLength);
    }

}