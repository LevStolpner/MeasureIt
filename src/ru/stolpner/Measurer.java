package ru.stolpner;

import java.util.Random;

/**
 * Class which is going to solve the measuring problem
 */
class Measurer {

    private static final int MAXIMUM_NUMBER_OF_STEPS = 30;

    /**
     * Solves the problem given verified arguments
     *
     * @param amount amount to be measured
     * @param firstCapacity capacity of the first container
     * @param secondCapacity capacity of the second container
     */
    static void solveProblem(Integer amount, Integer firstCapacity, Integer secondCapacity) {
        if (amount <= 0 || firstCapacity <= 0 || secondCapacity <= 0) throw new IllegalArgumentException("Arguments can not be negative");
        if (amount > firstCapacity + secondCapacity) throw new IllegalArgumentException("Target amount is too big");
        System.out.printf("Measurement started: first container capacity is %d, second container capacity is %d\n", firstCapacity, secondCapacity);

        //TODO move to another method
        int numberOfSteps = 0;
        Random random = new Random();
        int numberOfActions = MeasuringAction.values().length;
        Container first = new Container(firstCapacity);
        Container second = new Container(secondCapacity);
        MeasuringSequence sequence = new MeasuringSequence();
        while (!isMeasurementAchieved(amount, first, second) && numberOfSteps < MAXIMUM_NUMBER_OF_STEPS) {
            makeStep(random, numberOfActions, first, second, sequence);
            numberOfSteps++;
        }

        printResult(amount, numberOfSteps, sequence);
    }

    /**
     * Checks whether needed amount is produced in measurement process
     *
     * @param amount amount needed
     * @param first first container
     * @param second second container
     * @return true if measurement is done successfully, false if not
     */
    private static boolean isMeasurementAchieved(Integer amount, Container first, Container second) {
        return first.getCurrentFill() == amount || second.getCurrentFill() == amount
                || first.getCurrentFill() + second.getCurrentFill() == amount;
    }

    private static void makeStep(Random random, int numberOfActions, Container first, Container second, MeasuringSequence sequence) {
        int index = random.nextInt(numberOfActions);
        MeasuringAction selectedAction = MeasuringAction.values()[index];
        switch (selectedAction) {
            case FILL_A:
                first.fill();
                break;
            case FILL_B:
                second.fill();
                break;
            case EMPTY_A:
                first.empty();
                break;
            case EMPTY_B:
                second.empty();
                break;
            case POUR_A_IN_B:
                MeasuringUtils.pour(first, second);
                break;
            case POUR_B_IN_A:
                MeasuringUtils.pour(second, first);
        }
        MeasuringStep step = new MeasuringStep(selectedAction, first.getCurrentFill(), second.getCurrentFill());
        sequence.addMeasuringStep(step);
    }

    /**
     * Defines the result and prints appropriate message
     *
     * @param amount amount needed to measure
     * @param numberOfSteps number of steps made
     * @param sequence sequence describing made steps
     */
    private static void printResult(int amount, int numberOfSteps, MeasuringSequence sequence) {
        if (numberOfSteps < MAXIMUM_NUMBER_OF_STEPS) {
            printPositiveResult(sequence);
        } else {
            printPositiveResult(sequence);
            printNegativeResult(amount);
        }
    }

    /**
     * Prints message that measuring completed successfully and prints steps to do it
     *
     * @param sequence sequence representing steps to measure needed amount
     */
    private static void printPositiveResult(MeasuringSequence sequence) {
        System.out.println("WE FOUND THE MEASURE!!!1!");
        System.out.println(sequence.toString());
    }

    /**
     * Prints message that measuring needed amount is not possible
     */
    private static void printNegativeResult(int requiredAmount) {
        String result = String.format("Measuring %d amount of substance turned out to be impossible :(", requiredAmount);
        System.out.println(result);
    }
}