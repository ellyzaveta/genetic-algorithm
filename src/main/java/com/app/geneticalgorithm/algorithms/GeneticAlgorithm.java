package com.app.geneticalgorithm.algorithms;

import com.app.geneticalgorithm.models.*;
import com.app.geneticalgorithm.services.EvolutionService;
import com.app.geneticalgorithm.services.InitializationService;
import com.app.geneticalgorithm.utils.Constants;
import com.app.geneticalgorithm.utils.DataFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class GeneticAlgorithm implements Runnable {

    private List<Solution> population;
    private final AtomicReference<Solution> solution;
    private final List<List<Path>> paths;
    private final int iterationNumber;
    private final double mutationPercent;
    private int currentIterationNumber;

    public GeneticAlgorithm(UserInput userInput, FrameProperties frameProperties) {
        this.iterationNumber = userInput.getIterationNumber();
        this.mutationPercent = userInput.getMutationPercent();

        this.paths = new ArrayList<>();

        List<City> cities = DataFactory.getRandCities(userInput.getCitiesNumber(), frameProperties.getWidth(), frameProperties.getHeight());
        this.population = InitializationService.initPopulation(cities, Constants.DEFAULT_POPULATION_SIZE);

        this.solution = new AtomicReference<>();
        this.solution.set(new Solution(cities));
    }

    public Solution getSolution() {
        return solution.get();
    }

    @Override
    public void run() {
        if (!isFinished()) {
            step();
        }
    }

    private boolean isFinished() {
        return currentIterationNumber == iterationNumber;
    }

    public void step() {
        if (isFirstIteration()) solution.set(population.get(0));

        evolvePopulation();

        updatePaths();
        updateCurrentIteration();

        System.out.printf("\n%d: Best solution - %f", currentIterationNumber, solution.get().getPathLength());
    }

    private boolean isFirstIteration() {
        return currentIterationNumber == 0;
    }

    private void evolvePopulation() {
        population = EvolutionService.evolvePopulation(population, mutationPercent);
        solution.set(population.get(0));
    }

    private void updateCurrentIteration() {
        currentIterationNumber++;
        solution.set(new Solution(solution.get().getCities(), solution.get().getPathLength(), solution.get().getPaths(), currentIterationNumber));
    }

    private void updatePaths() {
        addNewPaths();
        solution.set(new Solution(solution.get().getCities(), solution.get().getPathLength(), new ArrayList<>(paths), solution.get().getCurrentIterationNumber()));
    }

    private void addNewPaths() {
        List<Path> newPaths = new ArrayList<>();

        List<City> cities = new ArrayList<>(solution.get().getCities());

        for (int i = 1; i < cities.size(); i++) {
            City prevCity = cities.get(i - 1);
            City city = cities.get(i);
            newPaths.add(new Path(prevCity.getX(), prevCity.getY(), city.getX(), city.getY()));
        }

        synchronized (paths) {
            paths.add(new ArrayList<>(newPaths));
        }
    }

}

