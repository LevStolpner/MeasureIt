package ru.stolpner;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the sequence of measuring steps
 */
class MeasuringSequence {

    private List<MeasuringStep> measuringSteps;

    /**
     * Create sequence
     */
    MeasuringSequence() {
        this.measuringSteps = new ArrayList<>();
    }

    /**
     * Add measuring step into sequence
     *
     * @param step measuring step
     */
    void addMeasuringStep(MeasuringStep step) {
        measuringSteps.add(step);
    }

    /**
     * Builds full measuring sequence information string
     *
     * @return measuring sequence information string
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < measuringSteps.size(); i++) {
            MeasuringStep step = measuringSteps.get(i);
            String stepPrint = String.format("Step %d: %s, A container: %d, B container %d\n", i + 1,
                    step.getAction().getActionName(), step.getFirstContainerFill(), step.getSecondContainerFill());
            builder.append(stepPrint);
        }
        return builder.toString();
    }
}