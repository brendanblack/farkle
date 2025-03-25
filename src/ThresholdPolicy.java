import java.util.*;

public class ThresholdPolicy implements Policy {
    private int threshold;

    public ThresholdPolicy(int threshold) {
        this.threshold = threshold;
    }

    public boolean shouldRollAgain(int runningTotal, List<Integer> remainingDice) {
        return runningTotal < threshold;
    }

    public String getName() {
        return "Threshold(" + threshold + ")";
    }
}
