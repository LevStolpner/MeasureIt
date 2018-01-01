package ru.stolpner;

/**
 * Class representing the container for measuring
 */
class Container {

    private int capacity;
    private int currentFill;

    /**
     * Creates container with given capacity
     *
     * @param capacity size of the container
     */
    Container(int capacity) {
        this.capacity = capacity;
        this.currentFill = 0;
    }

    /**
     * Gets for container capacity
     *
     * @return container capacity
     */
    int getCapacity() {
        return capacity;
    }

    /**
     * Gets for current fill
     *
     * @return container current fill
     */
    int getCurrentFill() {
        return currentFill;
    }

    /**
     * Gets free capacity
     *
     * @return free capacity of the container
     */
    int getFreeCapacity() {
        return this.capacity - this.currentFill;
    }

    /**
     * Calculates current fill after pouring in given amount of substance
     *
     * @param amount amount of substance pouring in
     */
    void pourIn(int amount) {
        this.currentFill = this.currentFill + amount > this.capacity ? this.capacity : this.currentFill + amount;
    }

    /**
     * Calculates current fill after pouring out given amount of substance
     *
     * @param amount amount of substance pouring out
     */
    void pourOut(int amount) {
        this.currentFill = this.currentFill - amount < 0 ? 0 : this.currentFill - amount;
    }

    /**
     * Fills the container
     */
    void fill() {
        this.currentFill = this.capacity;
    }

    /**
     * Empties the container
     */
    void empty() {
        this.currentFill = 0;
    }

    /**
     * Checks if the container is full
     *
     * @return is container full
     */
    boolean isFull() {
        return this.currentFill == this.capacity;
    }

    /**
     * Checks if the container is empty
     *
     * @return is container empty
     */
    boolean isEmpty() {
        return this.currentFill == 0;
    }
}