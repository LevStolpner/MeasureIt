package ru.stolpner;

/**
 * Class with information on one measuring step
 */
class MeasuringStep {

    private final MeasuringAction action;
    private final int firstContainerFill;
    private final int secondContainerFill;

    /**
     * Creates instance with all information
     * @param action one of the measuring actions made
     * @param firstContainerFill first container fill after this step
     * @param secondContainerFill second container fill after this step
     */
    public MeasuringStep(MeasuringAction action, int firstContainerFill, int secondContainerFill) {
        this.action = action;
        this.firstContainerFill = firstContainerFill;
        this.secondContainerFill = secondContainerFill;
    }

    /**
     * Gets measuring action
     *
     * @return measuring action
     */
    MeasuringAction getAction() {
        return action;
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
}