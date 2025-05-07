import java.util.*;

public class DiceNumberPolicy implements Policy {
    private final FarkleScorer scorer;

    public DiceNumberPolicy() {
        this.scorer = new StandardFarkleScorer();
    }

    public boolean shouldRollAgain(int runningTotal, List<Integer> remainingDice) {
        return remainingDice.size()<3;
    }

    public String getName() {
        return "DiceNumberPolicy";
    }

    public FarkleScorer getFarkleScorer() { return scorer; }
}
