package ru.stolpner;

import java.util.HashSet;
import java.util.Set;

/**
 * Class for storing a set of calculated measurements
 */
class MeasurementHistory {

    private static Set<MeasurementState> measurementStates;

    /**
     * Sets initial measurement state in history
     *
     * @param firstState initial state
     */
    static void initialize(MeasurementState firstState) {
        measurementStates = new HashSet<>();
        measurementStates.add(firstState);
    }

    /**
     * Gets measurement states
     *
     * @return measurement states
     */
    static Set<MeasurementState> getMeasurementStates() {
        return measurementStates;
    }
}
