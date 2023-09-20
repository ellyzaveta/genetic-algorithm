package com.app.geneticalgorithm.services;

import com.app.geneticalgorithm.models.City;
import com.app.geneticalgorithm.models.Solution;
import com.app.geneticalgorithm.utils.DataFactory;

import java.util.Collections;
import java.util.List;

public class MutationService {

    public static void mutate(Solution firstChild, Solution secondChild, double mutationPercent) {
        List<City> firstChildCities = firstChild.getCities();
        List<City> secondChildCities = secondChild.getCities();

        randomlyMutate(firstChildCities, mutationPercent);
        randomlyMutate(secondChildCities, mutationPercent);
    }

    private static void randomlyMutate(List<City> childCities, Double mutationPercent) {
        if (shouldMutate(mutationPercent)) {
            int cityNum = childCities.size();

            int mutationIndex1 = DataFactory.getRandInt(cityNum);
            int mutationIndex2;

            do {
                mutationIndex2 = DataFactory.getRandInt(cityNum);
            } while (mutationIndex1 == mutationIndex2);

            Collections.swap(childCities, mutationIndex1, mutationIndex2);
        }
    }

    private static boolean shouldMutate(Double mutationPercent) {
        final double randomValue = DataFactory.getRandDouble(100);
        return randomValue <= mutationPercent;
    }
}

