package com.app.geneticalgorithm.services;

import com.app.geneticalgorithm.models.Solution;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static com.app.geneticalgorithm.services.CrossoverService.applyTwoPointCrossover;

public class EvolutionService {

    public static List<Solution> evolvePopulation(List<Solution> population, Double mutationPercent) {
        List<Solution> populationCopy = new ArrayList<>(population);
        List<Solution> newPopulation = new ArrayList<>();

        while(populationCopy.size() > 0) {

            List<Solution> parents = SelectionService.selectParents(populationCopy);

            Solution firstParent = parents.get(0);
            Solution secondParent = parents.get(1);

            List<Solution> children = applyTwoPointCrossover(firstParent, secondParent);

            Solution firstChild = children.get(0);
            Solution secondChild = children.get(1);

            MutationService.mutate(firstChild, secondChild, mutationPercent);

            PathService.updatePathLength(firstChild, secondChild);

            newPopulation.add(firstChild);
            newPopulation.add(secondChild);
        }

        newPopulation.sort(Comparator.comparingDouble(Solution::getPathLength));

        return newPopulation;
    }

}

