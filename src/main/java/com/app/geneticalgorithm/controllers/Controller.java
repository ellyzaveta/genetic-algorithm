package com.app.geneticalgorithm.controllers;

import com.app.geneticalgorithm.models.City;
import com.app.geneticalgorithm.models.FrameProperties;
import com.app.geneticalgorithm.models.Path;
import com.app.geneticalgorithm.models.UserInput;
import com.app.geneticalgorithm.utils.Constants;
import com.app.geneticalgorithm.workers.Worker;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ga/")
public class Controller {

    private UserInput userInput = new UserInput(Constants.DEFAULT_ITERATION_NUMBER, Constants.DEFAULT_CITIES_NUMBER, Constants.DEFAULT_MUTATION_PERCENT);
    private FrameProperties frameProperties = new FrameProperties(Constants.DEFAULT_WIDTH, Constants.DEFAULT_HEIGHT);
    private final Worker worker = new Worker(userInput, frameProperties);

    @PostMapping("/userInput")
    public void setUserInput(@RequestParam Integer iterationNumber,
                             @RequestParam Integer citiesNumber,
                             @RequestParam Double mutationPercent) {
        userInput = new UserInput(iterationNumber, citiesNumber, mutationPercent);
        worker.update(userInput, frameProperties);
    }

    @PostMapping("/frameProperties")
    public void setFrameProperties(@RequestParam Integer width,
                                   @RequestParam Integer height) {
        frameProperties = new FrameProperties(width, height);
        worker.update(userInput, frameProperties);
    }

    @GetMapping("/start")
    public void start() {
        worker.run();
    }

    @GetMapping("/cities")
    public List<City> getCities() {
        return new ArrayList<>(worker.getSolution().getCities());
    }

    @GetMapping("/paths")
    public List<List<Path>> getPaths() {
        return worker.getSolution().getPaths();
    }

    @GetMapping("/iterationNumber")
    public Integer getIterationNumber() {
        return userInput.getIterationNumber();
    }

    @GetMapping("/currentIteration")
    public Integer getCurrentIterationNumber() {
        return worker.getSolution().getCurrentIterationNumber();
    }

    @PostMapping("/delay")
    public void setDelay(@RequestParam("delay") Long delay) {
        worker.setDelay(400 / delay);
    }

    @GetMapping("/bestDistance")
    public double getBestDistance() {
        return worker.getSolution().getPathLength();
    }

    @GetMapping("/citiesNumber")
    public Integer getCitiesNumber() {
        return userInput.getCitiesNumber();
    }

    @GetMapping("/mutationPercent")
    public Double getMutationPercent() {
        return userInput.getMutationPercent();
    }

}

