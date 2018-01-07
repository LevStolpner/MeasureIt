package ru.stolpner;

/**
 * Class for storing main arguments
 */
class MeasurementArgs {

    private static int amount;
    private static int firstCapacity;
    private static int secondCapacity;

    /**
     * Initializes variables with arguments
     *
     * @param _amount amount
     * @param _firstCapacity first capacity
     * @param _secondCapacity second capacity
     */
    static void initialize(int _amount, int _firstCapacity, int _secondCapacity) {
        amount = _amount;
        firstCapacity = _firstCapacity;
        secondCapacity = _secondCapacity;
    }

    /**
     * Gets amount
     *
     * @return amount
     */
    static int getAmount() {
        return amount;
    }

    /**
     * Gets first capacity
     *
     * @return first capacity
     */
    static int getFirstCapacity() {
        return firstCapacity;
    }

    /**
     * Gets second capacity
     *
     * @return second capacity
     */
    static int getSecondCapacity() {
        return secondCapacity;
    }
}