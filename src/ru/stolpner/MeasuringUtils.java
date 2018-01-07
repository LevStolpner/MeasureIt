package ru.stolpner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static ru.stolpner.MeasurementAction.*;

/**
 * Helper class
 */
class MeasuringUtils {

    private final static int ARGUMENTS_LENGTH = 3;

    /**
     * Verifies that arguments are correct and converts them to integers.
     *
     * @param input array of string input arguments
     * @return list of converted to integer input arguments
     */
    static List<Integer> convertInput(String[] input) {
        if (input == null || input.length < ARGUMENTS_LENGTH) {
            throw new IllegalArgumentException("Not enough arguments provided");
        }
        if (input.length > ARGUMENTS_LENGTH) {
            throw new IllegalArgumentException("Maximum 6 arguments allowed");
        }

        try {
            return Arrays.stream(input).map(Integer::parseInt).collect(Collectors.toList());
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("Arguments should only contain integer numbers");
        }
    }

    /**
     * Full transition to next state, applying action and calculating container fills
     *
     * @param state state to apply action to
     * @param action action to apply to state
     * @return new state, created from previous state by applying action
     */
    static MeasurementState applyActionToMeasurementState(MeasurementState state, MeasurementAction action) {
        int newFirstContainerFill = state.getFirstContainerFill();
        int newSecondContainerFill = state.getSecondContainerFill();
        switch (action) {
            case FILL_FIRST:
                newFirstContainerFill = MeasurementArgs.getFirstCapacity();
                break;
            case FILL_SECOND:
                newSecondContainerFill = MeasurementArgs.getSecondCapacity();
                break;
            case EMPTY_FIRST:
                newFirstContainerFill = 0;
                break;
            case EMPTY_SECOND:
                newSecondContainerFill = 0;
                break;
            case POUR_FIRST_IN_SECOND:
                if (state.getFirstContainerFill() <= MeasurementArgs.getSecondCapacity() - state.getSecondContainerFill()) {
                    newFirstContainerFill = 0;
                    newSecondContainerFill += state.getFirstContainerFill();
                } else {
                    newFirstContainerFill -= MeasurementArgs.getSecondCapacity() - state.getSecondContainerFill();
                    newSecondContainerFill = MeasurementArgs.getSecondCapacity();
                }
                break;
            case POUR_SECOND_IN_FIRST:
                if (state.getSecondContainerFill() <= MeasurementArgs.getFirstCapacity() - state.getFirstContainerFill()) {
                    newFirstContainerFill += state.getSecondContainerFill();
                    newSecondContainerFill = 0;
                } else {
                    newFirstContainerFill = MeasurementArgs.getFirstCapacity();
                    newSecondContainerFill -= MeasurementArgs.getFirstCapacity() - state.getFirstContainerFill();
                }
        }

        List<MeasurementAction> newActionList = new ArrayList<>(state.getActions());
        newActionList.add(action);

        return new MeasurementState(newActionList, newFirstContainerFill, newSecondContainerFill, false);
    }

    /**
     * Print information on sequence of steps for measuring needed amount
     *
     * @param state last state with needed amount
     */
    static void printSuccessfulMeasurementProcess(MeasurementState state) {
        String result = "";
        int stepCounter = 1;
        MeasurementState reproducingState = new MeasurementState();
        for (MeasurementAction action : state.getActions()) {
            reproducingState = applyActionToMeasurementState(reproducingState, action);
            result += String.format("Step %d: %s. First container fill = %d, Second container fill = %d\n",
                    stepCounter, action.getActionName(), reproducingState.getFirstContainerFill(), reproducingState.getSecondContainerFill());
            stepCounter++;
        }

        System.out.println(result);
    }
}