package com.app.geneticalgorithm.services;

import com.app.geneticalgorithm.models.Solution;
import com.app.geneticalgorithm.utils.DataFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SelectionService {

    public static List<Solution> selectParents(List<Solution> population) {
        Solution firstParent = tournamentSelection(population);
        Solution secondParent;

        do {
            secondParent = tournamentSelection(population);
        } while (firstParent == secondParent);

        population.remove(firstParent);
        population.remove(secondParent);

        return List.of(firstParent, secondParent);
    }

    private static Solution tournamentSelection(List<Solution> population) {
        int tournamentSize = 5;
        List<Solution> tournamentParticipants = new ArrayList<>();

        for (int i = 0; i < tournamentSize; i++) {
            int randIndex = DataFactory.getRandInt(population.size());
            tournamentParticipants.add(population.get(randIndex));
        }

        return tournamentParticipants.stream()
                .min(Comparator.comparingDouble(Solution::getPathLength))
                .orElse(null);
    }
}
