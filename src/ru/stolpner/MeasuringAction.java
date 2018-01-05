package ru.stolpner;

/**
 * Enum of possible measuring actions
 */
enum MeasuringAction {
    FILL_A ("fill container A"),
    FILL_B ("fill container B"),
    EMPTY_A ("empty container A"),
    EMPTY_B ("empty container B"),
    POUR_A_IN_B ("pour from container A to container B"),
    POUR_B_IN_A ("pour form container B to container A");

    private final String actionName;

    /**
     * Creates action and sets action name
     *
     * @param s action name
     */
    MeasuringAction(String s) {
        actionName = s;
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