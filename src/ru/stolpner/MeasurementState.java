package ru.stolpner;

class MeasurementState {

    private String stepsToProduce;
    private int firstContainerFill;
    private int secondContainerFill;
    private boolean isMeasuredFrom;

    MeasurementState(String stepsToProduce, int firstContainerFill, int secondContainerFill, boolean isMeasuredFrom) {
        this.stepsToProduce = stepsToProduce;
        this.firstContainerFill = firstContainerFill;
        this.secondContainerFill = secondContainerFill;
        this.isMeasuredFrom = isMeasuredFrom;
    }

    public String getStepsToProduce() {
        return stepsToProduce;
    }

    public void setStepsToProduce(String stepsToProduce) {
        this.stepsToProduce = stepsToProduce;
    }

    public int getFirstContainerFill() {
        return firstContainerFill;
    }

    public void setFirstContainerFill(int firstContainerFill) {
        this.firstContainerFill = firstContainerFill;
    }

    public int getSecondContainerFill() {
        return secondContainerFill;
    }

    public void setSecondContainerFill(int secondContainerFill) {
        this.secondContainerFill = secondContainerFill;
    }

    public boolean isMeasuredFrom() {
        return isMeasuredFrom;
    }

    public void setMeasuredFrom(boolean measuredFrom) {
        isMeasuredFrom = measuredFrom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MeasurementState that = (MeasurementState) o;

        if (firstContainerFill != that.firstContainerFill || secondContainerFill != that.secondContainerFill) return false;

        return true;

    }

    @Override
    public int hashCode() {
        int result = stepsToProduce.hashCode();
        result = 31 * result + firstContainerFill;
        result = 31 * result + secondContainerFill;
        result = 31 * result + (isMeasuredFrom ? 1 : 0);
        return result;
    }
}
