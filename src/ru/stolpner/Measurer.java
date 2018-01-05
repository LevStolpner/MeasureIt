package ru.stolpner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static ru.stolpner.MeasuringAction.*;

/**
 * Class which is going to solve the measuring problem
 */
class Measurer {

    private static final int MAXIMUM_NUMBER_OF_STEPS = 1000;

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

        //TODO idea: try to build full graph of possibilities and find fastest route
        //TODO pseudoplan: 1) separate type for Measurement State 2) store them in HashSet, start from (0,0)
        //TODO             3) on each step apply all possible actions, store obtained State in Set
        //TODO             4) if State is old - do nothing. if all States are old - calculate from new States
        //TODO             5) somehow remember which States in Set are already calculated from
        //TODO             6) when all States are calculated, and there are no new States - measurement finished
        //TODO             7) storing HISTORY OF CHANGES IS ABSOLUTELY NECESSARY TO EXPLAIN THE SOLUTION
        //TODO             8) conditions for "right" actions remove profit, better just calculate everything
        //TODO             9) always check if Measurement is reached, not to do extra calculations and find longer routes to same Measurement
        //TODO             10) to enhance the point of SHORTEST PATH - all State calculations must be on same-level always
        MeasuringSequence sequence = calculateMeasurement(amount, firstCapacity, secondCapacity);
        printResult(amount, sequence);
    }

    /**
     * Calculates measurement sequence
     *
     * @return sequence of measurements
     */
    private static MeasuringSequence calculateMeasurement(Integer amount, Integer firstCapacity, Integer secondCapacity) {
        Random random = new Random();
        Container first = new Container(firstCapacity);
        Container second = new Container(secondCapacity);
        MeasuringSequence sequence = new MeasuringSequence();
        while (!isMeasurementAchieved(amount, first, second) && sequence.getSequenceLength() < MAXIMUM_NUMBER_OF_STEPS) {
            makeStep(random, first, second, sequence);
        }

        return sequence;
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
        return first.getCurrentFill() == amount || second.getCurrentFill() == amount;
    }

    /**
     * Makes step of measuring
     *
     * @param random instance of Random
     * @param first first container
     * @param second second container
     * @param sequence measuring sequence
     */
    private static void makeStep(Random random, Container first, Container second, MeasuringSequence sequence) {
        List<MeasuringAction> availableActions = pickAvailableActions(first, second, sequence);
        int index = random.nextInt(availableActions.size());
        MeasuringAction selectedAction = availableActions.get(index);
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
     * Picks logically productive actions available for measurement
     *
     * @return list of possible measurement actions
     */
    private static List<MeasuringAction> pickAvailableActions(Container first, Container second, MeasuringSequence sequence) {
        if (sequence.getSequenceLength() == 0) {
            return Arrays.asList(MeasuringAction.values());
        }

        List<MeasuringAction> availableActions = new ArrayList<>();
        MeasuringAction lastAction = sequence.getLastStep().getAction();
        if (!first.isFull() && lastAction != EMPTY_A) {
            availableActions.add(FILL_A);
        }
        if (!second.isFull() && lastAction != EMPTY_B) {
            availableActions.add(FILL_B);
        }
        if (!first.isEmpty() && lastAction != FILL_A) {
            availableActions.add(EMPTY_A);
        }
        if (!second.isEmpty() && lastAction != FILL_B) {
            availableActions.add(EMPTY_B);
        }
        if (!first.isEmpty() && !second.isFull()) {
            availableActions.add(POUR_A_IN_B);
        }
        if (!first.isFull() && !second.isEmpty()) {
            availableActions.add(POUR_B_IN_A);
        }

        return availableActions;
    }

    /**
     * Defines the result and prints appropriate message
     *
     * @param amount amount needed to measure
     * @param sequence sequence describing steps made
     */
    private static void printResult(int amount, MeasuringSequence sequence) {
        if (sequence.getSequenceLength() < MAXIMUM_NUMBER_OF_STEPS) {
            printPositiveResult(sequence);
        } else {
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
        String result = String.format("Measuring %d amount of substance turned out to be impossible over %d steps:(", requiredAmount, MAXIMUM_NUMBER_OF_STEPS);
        System.out.println(result);
    }
}