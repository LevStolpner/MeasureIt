package ru.stolpner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
     * Performs the complete pouring procedure between two containers
     *
     * @param from container to pour from
     * @param to container to pour into
     */
    static void pour(Container from, Container to) {
        if (from.isEmpty() || to.isFull()) {
            return;
        }

        if (from.getCurrentFill() <= to.getFreeCapacity()) {
            to.pourIn(from.getCurrentFill());
            from.empty();
        } else {
            from.pourOut(to.getFreeCapacity());
            to.fill();
        }
    }
}