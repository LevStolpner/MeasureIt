package ru.stolpner;

/**
 * Enum of all possible measurement actions
 */
enum MeasurementAction {
    FILL_FIRST ("Fill first container"),
    FILL_SECOND ("Fill second container"),
    EMPTY_FIRST ("Empty first container"),
    EMPTY_SECOND ("Empty second container"),
    POUR_FIRST_IN_SECOND ("Pour from first container to second container"),
    POUR_SECOND_IN_FIRST ("Pour from second container to first container");

    private final String actionName;

    /**
     * Sets action name for action
     *
     * @param s action name
     */
    MeasurementAction(String s) {
        this.actionName = s;
    }

    /**
     * Gets action name
     *
     * @return action name
     */
    String getActionName() {
        return this.actionName;
    }
}
