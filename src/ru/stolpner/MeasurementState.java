package ru.stolpner;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for state of measuring process. Contains information on containers and all previous actions
 */
class MeasurementState {

    private List<MeasurementAction> actions;
    private int firstContainerFill;
    private int secondContainerFill;
    private boolean isMeasuredFrom;

    /**
     * Constructor for initial state creation
     */
    MeasurementState() {
        this.actions = new ArrayList<>();
        this.firstContainerFill = 0;
        this.secondContainerFill = 0;
        this.isMeasuredFrom = false;
    }

    /**
     * Constructor for creation of new states
     *
     * @param actions previous measurement actions
     * @param firstFill fill of first container
     * @param secondFill fill of second container
     * @param isMeasuredFrom flag for need to calculate measurements from this state
     */
    MeasurementState(List<MeasurementAction> actions, int firstFill, int secondFill, boolean isMeasuredFrom) {
        this.actions = actions;
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