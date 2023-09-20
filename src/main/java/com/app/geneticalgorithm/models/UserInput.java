package com.app.geneticalgorithm.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInput {
    private Integer iterationNumber;
    private Integer citiesNumber;
    private Double mutationPercent;
}
