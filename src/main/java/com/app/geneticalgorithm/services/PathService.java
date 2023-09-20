package com.app.geneticalgorithm.services;

import com.app.geneticalgorithm.models.City;
import com.app.geneticalgorithm.models.Solution;

import java.util.List;

public class PathService {

    public static double getGeneralPathLength(List<City> cities) {
        double result = 0.0;
        for (int i = 0; i < cities.size() - 1; i++) {
            result += euclideanDistance(cities.get(i), cities.get(i + 1));
        }
        return result;
    }

    private static double euclideanDistance(City current, City next) {
        return Math.sqrt(
                Math.pow(current.getX() - next.getX(), 2) + Math.pow(current.getY() - next.getY(), 2)
        );
    }

    public static void updatePathLength(Solution firstChild, Solution secondChild) {
        firstChild.setPathLength(getGeneralPathLength(firstChild.getCities()));
        secondChild.setPathLength(getGeneralPathLength(secondChild.getCities()));
    }

}
