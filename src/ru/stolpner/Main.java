package ru.stolpner;

import java.util.List;

public class Main {

    /**
     * Application to solve classic problem of measuring given amount of substance
     * using few containers of fixed sizes. Result of the application - confirm possibility
     * of measuring given amount, if possible - display shortest way to reproduce that amount.
     *
     * @param args input data, expected to be a list of integers,
     *             whose meaning will be treated as this:
     *             first number - amount which should be measured using given container sizes
     *             following numbers - integers, representing available container capacities
     */

    //TODO improve architecture, improve algorithm, java docs, namings
    //TODO separate "global" class with info on AMOUNT and CAPACITIES
    //TODO refactoring
    public static void main(String[] args) {
        List<Integer> convertedArgs = MeasuringUtils.convertInput(args);
        Measurer.measure(convertedArgs.get(0), convertedArgs.get(1), convertedArgs.get(2));
    }
}