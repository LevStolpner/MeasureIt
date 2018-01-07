package ru.stolpner;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Class which is going to solve the measuring problem
 */
class Measurer {

    /**
     * Tries to measure needed amount given two container capacities
     *
     * @param amount         amount to be measured
     * @param firstCapacity  capacity of the first container
     * @param secondCapacity capacity of the second container
     */
    static void measure(Integer amount, Integer firstCapacity, Integer secondCapacity) {
        if (amount <= 0 || firstCapacity <= 0 || secondCapacity <= 0)
            throw new IllegalArgumentException("Arguments can not be negative");
        if (amount > firstCapacity && amount > secondCapacity) throw new IllegalArgumentException("Target amount is too big");
        System.out.printf("Measurement started: first container capacity is %d, second container capacity is %d\n", firstCapacity, secondCapacity);
        Optional<MeasurementState> stateOptional = findMeasurement(amount, firstCapacity, secondCapacity);
        if (stateOptional.isPresent()) {
            printPositiveResult(stateOptional.get(), amount, firstCapacity, secondCapacity);
        } else {
            printNegativeResult(amount, firstCapacity, secondCapacity);
        }
    }

    /**
     * Tries to find a measurement step by step, calculating a bunch of new measurements on each step
     *
     * @param amount needed amount to measure
     * @param firstCapacity capacity of the first container
     * @param secondCapacity capacity of the second container
     * @return optional of right measurement state with needed amount
     */
    private static Optional<MeasurementState> findMeasurement(Integer amount, Integer firstCapacity, Integer secondCapacity) {
        MeasurementState firstState = new MeasurementState(firstCapacity, secondCapacity);
        MeasurementHistory measurementHistory = new MeasurementHistory(firstState);

        while (!isMeasurementFinished(measurementHistory) && !isMeasurementFound(measurementHistory, amount)) {
            makeMeasurementStep(measurementHistory);
        }

        return measurementHistory.getMeasurementStates().stream().filter(s -> s.getFirstContainerFill() == amount || s.getSecondContainerFill() == amount).findFirst();
    }

    /**
     *
     *
     * @param measurementHistory
     * @return
     */
    private static boolean isMeasurementFinished(MeasurementHistory measurementHistory) {
        return measurementHistory.getMeasurementStates().stream().allMatch(MeasurementState::isMeasuredFrom);
    }

    /**
     * Checks whether a measurement is found
     *
     * @param measurementHistory set of already calculated states
     * @param amount needed amount
     * @return if measurement is found
     */
    private static boolean isMeasurementFound(MeasurementHistory measurementHistory, Integer amount) {
        return measurementHistory.getMeasurementStates().stream().anyMatch(s -> s.getFirstContainerFill() == amount || s.getSecondContainerFill() == amount);
    }

    /**
     * Makes a step, firstly by finding a list of states to measure from, secondly by calculating new states and adding them to measurement history
     *
     * @param measurementHistory set of already calculated states
     */
    private static void makeMeasurementStep(MeasurementHistory measurementHistory) {
        List<MeasurementState> statesToMeasureFrom = measurementHistory.getMeasurementStates()
                .stream()
                .filter(s -> !s.isMeasuredFrom())
                .collect(Collectors.toList());
        measurementHistory.getMeasurementStates().forEach(s -> s.setMeasuredFrom(true));

        for (MeasurementState state : statesToMeasureFrom) {
            for (MeasurementAction action : MeasurementAction.values()) {
                MeasurementState newState = MeasuringUtils.applyActionToMeasurementState(state, action);
                measurementHistory.getMeasurementStates().add(newState);
            }
        }
    }

    /**
     * Prints positive result with information on measurement steps
     *
     * @param state final state
     * @param amount needed amount
     * @param firstCapacity capacity of the first container
     * @param secondCapacity capacity of the second container
     */
    private static void printPositiveResult(MeasurementState state, Integer amount, Integer firstCapacity, Integer secondCapacity) {
        System.out.println(String.format("Measurement %d amount with two containers of %d and %d capacities is possible.\n" +
                "To measure this amount, follow printed steps:\n", amount, firstCapacity, secondCapacity));
        MeasuringUtils.printSuccessfulMeasurementProcess(state, firstCapacity, secondCapacity);
    }

    /**
     * Prints message that measuring needed amount is not possible
     */
    private static void printNegativeResult(Integer amount, Integer firstCapacity, Integer secondCapacity) {
        System.out.println(String.format("Measurement %d amount with two containers of %d and %d capacities is not possible.\n", amount, firstCapacity, secondCapacity));
    }
}