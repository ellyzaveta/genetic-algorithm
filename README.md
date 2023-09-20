# 🧩 Solving the traveling salesman problem using a classical genetic algorithm

## 🪄 To run the app:
* Make sure Node.js and npm are installed on your computer.
* From the command line in the './frontend' folder, run the command 'npm install' to install all necessary dependencies.
* After installing the dependencies, build the project by running 'npm run build'.
* After launching the Spring Boot application, open a browser and go to the address 'http://localhost:8080' (or another port on which Spring Boot works by default).

<br />

## 🔷 Implementation of the genetic algorithm for finding the shortest path between cities

<br />

![image](https://drive.google.com/uc?export=view&id=1rnq4hRg-Dp4ItZtO9SbmMxQPwA-c1JYl)

<br />

## 🔷 Analysis of the efficiency of the algorithm and its convergence based on the work performed

<br />

Based on the obtained results, first of all it should be noted that the presented solution is not perfect for several reasons:

<br />

- Using a two-point crossover:
In this implementation of the crossover, a random selection of two exchange points is assumed. If the crossover points are close, then the exchange of parts of the solutions can lead to a loss of diversity, since the offspring will be almost indistinguishable from the parent. If the points are chosen too far away, we will get too big changes in the decisions of the descendants, and we will lose the necessary information.

<br />

- Using tournament selection as a parent pair selection operator:
Tournament selection can contribute to the loss of diversity in the population because it aims to select the best solutions.

<br />

In addition to the type of crossover and the parent pair selection operator, the parameters of the algorithm - the number of iterations, the population size, and the mutation rate - are equally important factors that affect the convergence and effectiveness of GA, the optimal combination of which is important to determine in order to achieve the desired result.

<br />

| 1 | 2 | 3 |
|---------|---------|---------|
| ![image](https://drive.google.com/uc?export=view&id=1zCUaA7p2Ml8RWDo3z-z73mQOlg7GVAei) | ![image](https://drive.google.com/uc?export=view&id=1CNL8npIPl2Hzj8gmt8NL4q69fPgTNHt3) | ![image](https://drive.google.com/uc?export=view&id=1HEh4qAajEfuYDhp4vkHbZHHSx8rB3-5T) | 

### 🔹 Number of iterations

Based on the plot 1 above it can be said that already on the 20th iterations, the algorithm approaches the optimum, showing the best result, which does not improve after that. This is a sign that additional iterations do not significantly improve the quality of the solution, and in some cases even significantly worsen it. Thus, an excessive number of iterations only increases the duration of calculations and wastes resources without benefit.

<br />

### 🔹 Population size

Regarding population size, it can be argued that too small a population can cause the GA to quickly limit itself to a certain number of genetic solutions, and selection will occur with limited diversity. On the other hand, too large a population size can cause the algorithm to stay in one search area for a long time without effectively searching for solutions in other areas. In plot 2 we can see that for a population size of 10 to 50, the optimal solution is close to or equal to the best. As the number of individuals increases, the optimal solution is closer to the worst than to the best.

<br />

### 🔹 Mutation percent

In Plot 3, you can see that when the percentage of mutation is greater than 10, the found optimal solution is equal to the worst. This is expected because too high mutation percent can cause useful solutions already found in the population to be randomly changed or destroyed, and optimization progress is lost. Moreover, if mutation is applied too often, the new solutions may be too similar to the original population, which may prevent better solutions from being found.

<br />

## 🔷 Conclusion

So, to achieve the best results when using this implementation of the genetic algorithm, the optimal combination of input parameters is: number of iterations - 20, population size - 30, mutation percentage - 2%.
