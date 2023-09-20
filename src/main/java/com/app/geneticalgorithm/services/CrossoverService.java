package com.app.geneticalgorithm.services;

import com.app.geneticalgorithm.models.City;
import com.app.geneticalgorithm.models.Solution;
import com.app.geneticalgorithm.utils.DataFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CrossoverService {

    public static List<Solution> applyTwoPointCrossover(Solution firstParent, Solution secondParent) {

        int size = firstParent.getCities().size();

        List<Integer> points = DataFactory.getRandPoints(size);

        int firstPoint = points.get(0);
        int secondPoint = points.get(1);

        List<City> firstParentCities = firstParent.getCities();
        List<City> secondParentCities = secondParent.getCities();

        removeLastCity(firstParentCities);
        removeLastCity(secondParentCities);

        Solution firstChild = new Solution(new ArrayList<>());
        Solution secondChild = new Solution(new ArrayList<>());

        List<City> firstChildCities = firstChild.getCities();
        List<City> secondChildCities = secondChild.getCities();

        pathSectionsExchange(firstChildCities, secondChildCities,
                firstParentCities, secondParentCities,
                firstPoint, secondPoint);

        return List.of(firstChild, secondChild);
    }

    private static void removeLastCity(List<City> cities) {
        int lastCityIndex = cities.size() - 1;
        cities.remove(lastCityIndex);
    }

    private static void addFirstCity(List<City> cities) {
        City firstCity = cities.get(0);
        cities.add(firstCity);
    }

    private static void pathSectionsExchange(List<City> firstChildCities, List<City> secondChildCities,
                                             List<City> firstParentCities, List<City> secondParentCities,
                                             int firstPivot, int secondPivot) {

        for (int i = 0; i < firstParentCities.size(); i++) {
            if (i <= firstPivot) {

                City city = firstParentCities.get(i);
                firstChildCities.add(city);

                city = secondParentCities.get(i);
                secondChildCities.add(city);

            } else if (i <= secondPivot) {
                setCityAfterFirstPoint(firstChildCities, secondChildCities, firstParentCities, secondParentCities, i);
            } else {
                setCityAfterSecondPoint(firstChildCities, secondChildCities, firstParentCities, secondParentCities, i);
            }
        }

        addFirstCity(firstChildCities);
        addFirstCity(secondChildCities);
    }

    private static void setCityAfterFirstPoint(List<City> firstChildCities, List<City> secondChildCities, List<City> firstParentCities, List<City> secondParentCities, int i) {
        City firstParentCity = firstParentCities.get(i);
        City secondParentCity = secondParentCities.get(i);

        updateCitiesBasedOnParents(firstChildCities, secondParentCity, firstParentCity, secondParentCities, firstParentCities);
        updateCitiesBasedOnParents(secondChildCities, firstParentCity, secondParentCity, firstParentCities, secondParentCities);
    }

    private static void setCityAfterSecondPoint(List<City> firstChildCities, List<City> secondChildCities, List<City> firstParentCities, List<City> secondParentCities, int i) {
        City firstParentCity = firstParentCities.get(i);
        City secondParentCity = secondParentCities.get(i);

        updateCitiesBasedOnParents(firstChildCities, firstParentCity, secondParentCity, firstParentCities, secondParentCities);
        updateCitiesBasedOnParents(secondChildCities, secondParentCity, firstParentCity, secondParentCities, firstParentCities);

    }

    private static void updateCitiesBasedOnParents(List<City> childCities, City firstParentCity, City secondParentCity, List<City> firstParentCities, List<City> secondParentCities) {
        if (childCities.contains(firstParentCity)) {
            if (childCities.contains(secondParentCity)) {
                addUnvisitedCity(firstParentCities, secondParentCities, childCities);
            } else {
                childCities.add(secondParentCity);
            }
        } else {
            childCities.add(firstParentCity);
        }
    }

    private static void addUnvisitedCity(List<City> firstParentCities, List<City> secondParentCities, List<City> childCities) {
        Optional<City> optionalCity = firstParentCities.stream()
                .filter(city -> !childCities.contains(city))
                .findFirst();
        if (optionalCity.isPresent()) {
            childCities.add(optionalCity.get());
        } else {
            City unvisitedCity = secondParentCities.stream()
                    .filter(city -> !childCities.contains(city))
                    .findFirst()
                    .orElseThrow();
            childCities.add(unvisitedCity);
        }
    }

}
