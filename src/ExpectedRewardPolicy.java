import java.util.List;

public class ExpectedRewardPolicy implements Policy {
    private final FarkleScorer scorer;

    public ExpectedRewardPolicy() {
        this.scorer = new StandardFarkleScorer();
    }

    @Override
    public boolean shouldRollAgain(int runningTotal, List<Integer> remainingDice) {
        if (runningTotal < 300 && remainingDice.size()==1) {
            return true;
        }
        if (runningTotal < 250 && remainingDice.size()==2) {
            return true;
        }
        if (runningTotal < 400 && remainingDice.size()==3) {
            return true;
        }
        if (runningTotal < 950 && remainingDice.size()==4) {
            return true;
        }
        if (runningTotal < 2800 && remainingDice.size()==5) {
            return true;
        }
        if (runningTotal < 9700 && remainingDice.size()==6) {
            return true;
        }
        return false;
    }

    @Override
    public String getName() {
        return "ExpectedRewardPolicy";
    }

    @Override
    public FarkleScorer getFarkleScorer() {
        return scorer;
    }
}
