package com.app.geneticalgorithm.workers;

import com.app.geneticalgorithm.algorithms.GeneticAlgorithm;
import com.app.geneticalgorithm.models.FrameProperties;
import com.app.geneticalgorithm.models.Solution;
import com.app.geneticalgorithm.models.UserInput;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Worker {

    private final ScheduledExecutorService executor;
    private ScheduledFuture<?> future;
    private GeneticAlgorithm geneticAlgorithm;
    private long delay;

    public Worker(UserInput userInput, FrameProperties frameProperties) {
        this.executor = Executors.newScheduledThreadPool(1);
        this.future = null;
        this.delay = 100;

        initAlgorithm(userInput, frameProperties);
    }

    public void run() {
        if (future == null || future.isCancelled()) {
            future = executor.scheduleAtFixedRate(geneticAlgorithm, 0, delay, TimeUnit.MILLISECONDS);
        }
    }

    public void update(UserInput userInput, FrameProperties frameProperties) {
        if (future != null) future.cancel(false);
        initAlgorithm(userInput, frameProperties);
    }

    private void initAlgorithm(UserInput userInput, FrameProperties frameProperties) {
        this.geneticAlgorithm = new GeneticAlgorithm(userInput, frameProperties);
    }

    public void setDelay(long delay) {
        this.delay = delay;
        if (future != null && !future.isCancelled()) {
            future.cancel(false);
            run();
        }
    }

    public Solution getSolution() {
        return geneticAlgorithm.getSolution();
    }
}


