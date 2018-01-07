package ru.stolpner;

import java.util.HashSet;
import java.util.Set;

/**
 * Class for storing a set of calculated measurements
 */
class MeasurementHistory {

    private Set<MeasurementState> measurementStates;

    /**
     * Constructor with initial state
     *
     * @param firstState initial state
     */
    MeasurementHistory(MeasurementState firstState) {
        this.measurementStates = new HashSet<>();
        this.measurementStates.add(firstState);
    }

    /**
     * Gets measurement states
     *
     * @return measurement states
     */
    Set<MeasurementState> getMeasurementStates() {
        return measurementStates;
    }
}
