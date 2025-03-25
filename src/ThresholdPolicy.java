import java.util.*;

public class ThresholdPolicy implements Policy {
    private int threshold;
    private FarkleScorer scorer;

    public ThresholdPolicy(int threshold) {
        this.threshold = threshold;
        this.scorer = new StandardFarkleScorer();
    }

    public boolean shouldRollAgain(int runningTotal, List<Integer> remainingDice) {
        return runningTotal < threshold;
    }

    public String getName() {
        return "Threshold(" + threshold + ")";
    }

    public FarkleScorer getFarkleScorer() { return scorer; }
}
