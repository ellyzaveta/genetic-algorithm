package com.app.geneticalgorithm.utils;

import com.app.geneticalgorithm.models.City;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class DataFactory {

    public static Integer getRandInt(Integer max) {
        return new Random().nextInt(max);
    }

    public static Integer getRandInt(Integer min, Integer max) {
        return new Random().nextInt(max - min + 1) + min;
    }

    public static List<City> getRandCities(Integer citiesNumber, Integer sceneWidth, Integer sceneHeight) {
        List<City> cities = new CopyOnWriteArrayList<>();
        for(int i = 0; i < citiesNumber; i++) {
            cities.add(new City(i, getRandInt(10, sceneWidth), getRandInt(50, sceneHeight - 50)));
        }
        return cities;
    }

    public static double getRandDouble(int max) {
        return Math.random() * max;
    }

    public static List<Integer> getRandPoints(int maxIndex) {
        Integer firstPoint = getRandInt(maxIndex);
        Integer secondPoint;

        do {
            secondPoint = getRandInt(maxIndex);
        } while (firstPoint.equals(secondPoint));

        if (firstPoint > secondPoint) {
            return List.of(secondPoint, firstPoint);
        } else {
            return List.of(firstPoint, secondPoint);
        }
    }
}
