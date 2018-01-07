package ru.stolpner;

class MeasurementArgs {

    private static int amount;
    private static int firstCapacity;
    private static int secondCapacity;

    static void initialize(int _amount, int _firstCapacity, int _secondCapacity) {
        amount = _amount;
        firstCapacity = _firstCapacity;
        secondCapacity = _secondCapacity;
    }

    static int getAmount() {
        return amount;
    }

    static int getFirstCapacity() {
        return firstCapacity;
    }

    static int getSecondCapacity() {
        return secondCapacity;
    }
}