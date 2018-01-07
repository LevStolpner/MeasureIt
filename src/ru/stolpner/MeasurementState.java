package ru.stolpner;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for state of measuring process. Contains information on containers and all previous actions
 */
class MeasurementState {

    private List<MeasurementAction> actions;
    private int firstContainerCapacity;
    private int secondContainerCapacity;
    private int firstContainerFill;
    private int secondContainerFill;
    private boolean isMeasuredFrom;

    /**
     * Constructor for initial state creation
     *
     * @param firstContainerCapacity capacity of first container
     * @param secondContainerCapacity capacity of second container
     */
    MeasurementState(int firstContainerCapacity, int secondContainerCapacity) {
        this.actions = new ArrayList<>();
        this.firstContainerCapacity = firstContainerCapacity;
        this.secondContainerCapacity = secondContainerCapacity;
        this.firstContainerFill = 0;
        this.secondContainerFill = 0;
        this.isMeasuredFrom = false;
    }

    /**
     * Constructor for creation of new states
     *
     * @param actions previous measurement actions
     * @param firstCapacity capacity of first container
     * @param secondCapacity capacity of second container
     * @param firstFill fill of first container
     * @param secondFill fill of second container
     * @param isMeasuredFrom flag for need to calculate measurements from this state
     */
    MeasurementState(List<MeasurementAction> actions, int firstCapacity, int secondCapacity, int firstFill, int secondFill, boolean isMeasuredFrom) {
        this.actions = actions;
        this.firstContainerCapacity = firstCapacity;
        this.secondContainerCapacity = secondCapacity;
        this.firstContainerFill = firstFill;
        this.secondContainerFill = secondFill;
        this.isMeasuredFrom = isMeasuredFrom;
    }

    /**
     * Gets measurement actions for this state
     *
     * @return measurement actions
     */
    List<MeasurementAction> getActions() {
        return actions;
    }

    /**
     * Gets first container capacity
     *
     * @return first container capacity
     */
    int getFirstContainerCapacity() {
        return firstContainerCapacity;
    }

    /**
     * Gets second container capacity
     *
     * @return second container capacity
     */
    int getSecondContainerCapacity() {
        return secondContainerCapacity;
    }

    /**
     * Gets first container fill
     *
     * @return first container fill
     */
    int getFirstContainerFill() {
        return firstContainerFill;
    }

    /**
     * Gets second container fill
     *
     * @return second container fill
     */
    int getSecondContainerFill() {
        return secondContainerFill;
    }

    /**
     * Gets measuredFrom flag
     *
     * @return measuredFrom flag
     */
    boolean isMeasuredFrom() {
        return isMeasuredFrom;
    }

    /**
     * Sets measuredFrom flag
     *
     * @param measuredFrom flag
     */
    void setMeasuredFrom(boolean measuredFrom) {
        isMeasuredFrom = measuredFrom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MeasurementState s = (MeasurementState) o;
        return firstContainerFill == s.getFirstContainerFill() && secondContainerFill == s.getSecondContainerFill();
    }

    @Override
    public int hashCode() {
        int result = firstContainerFill;
        result = 31 * result + secondContainerFill;
        return result;
    }
}