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
        MeasurementArgs.initialize(amount, firstCapacity, secondCapacity);
        System.out.printf("Measurement started: first container capacity is %d, second container capacity is %d\n", firstCapacity, secondCapacity);

        Optional<MeasurementState> stateOptional = findMeasurement();
        if (stateOptional.isPresent()) {
            printPositiveResult(stateOptional.get());
        } else {
            printNegativeResult();
        }
    }

    /**
     * Tries to find a measurement step by step, calculating a bunch of new measurements on each step
     *
     * @return optional of right measurement state with needed amount
     */
    private static Optional<MeasurementState> findMeasurement() {
        MeasurementState firstState = new MeasurementState();
        MeasurementHistory measurementHistory = new MeasurementHistory(firstState);

        while (!isMeasurementFinished(measurementHistory) && !isMeasurementFound(measurementHistory)) {
            makeMeasurementStep(measurementHistory);
        }

        return measurementHistory.getMeasurementStates().stream()
                .filter(s -> s.getFirstContainerFill() == MeasurementArgs.getAmount() || s.getSecondContainerFill() == MeasurementArgs.getAmount()).findFirst();
    }

    /**
     * Checks whether all states are measured from
     *
     * @param measurementHistory set of already calculated states
     * @return if measurement is finished
     */
    private static boolean isMeasurementFinished(MeasurementHistory measurementHistory) {
        return measurementHistory.getMeasurementStates().stream().allMatch(MeasurementState::isMeasuredFrom);
    }

    /**
     * Checks whether a measurement is found
     *
     * @param measurementHistory set of already calculated states
     * @return if measurement is found
     */
    private static boolean isMeasurementFound(MeasurementHistory measurementHistory) {
        return measurementHistory.getMeasurementStates().stream()
                .anyMatch(s -> s.getFirstContainerFill() == MeasurementArgs.getAmount() || s.getSecondContainerFill() == MeasurementArgs.getAmount());
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
     */
    private static void printPositiveResult(MeasurementState state) {
        System.out.println(String.format("Measurement %d amount with two containers of %d and %d capacities is possible.\n" +
                "To measure this amount, follow printed steps:\n", MeasurementArgs.getAmount(), MeasurementArgs.getFirstCapacity(), MeasurementArgs.getSecondCapacity()));
        MeasuringUtils.printSuccessfulMeasurementProcess(state);
    }

    /**
     * Prints message that measuring needed amount is not possible
     */
    private static void printNegativeResult() {
        System.out.println(String.format("Measurement %d amount with two containers of %d and %d capacities is not possible.\n", MeasurementArgs.getAmount(), MeasurementArgs.getFirstCapacity(), MeasurementArgs.getSecondCapacity()));
    }
}