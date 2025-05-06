import java.util.*;

public class Player {
    private final TransitionTracker tracker = new TransitionTracker(); 
    public TransitionTracker getTracker() {
        return tracker;
    }

    private int totalScore;
    private final Policy policy;
    private final FarkleScorer scorer;

    /**
     * Constructor assigns the policy and scorer for the player.
     * Total score starts at 0.
     */
    public Player(Policy policy) {
        this.policy = policy;
        this.totalScore = 0;
        this.scorer = policy.getFarkleScorer();
    }

    public int takeTurn() {
        DiceSet diceSet = new DiceSet();  //start w/6 dice
        int runningTotal = 0;
        List<Integer> remainingDice = diceSet.roll(); //initial roll

        while (true) {
            int currentDiceCount = remainingDice.size();

            //checking for farkle
            if (scorer.isFarkle(remainingDice)) {
                tracker.record(currentDiceCount, "Farkle", 0); //logging the farkle in the tracker
                runningTotal = 0;
                break;
            }

            // Choose and set aside all scoring dice
            List<Integer> toSetAside = setAsideDice(remainingDice, diceSet);
            // Calculate the score from the selected scoring dice
            int score = scorer.calculateScore(toSetAside);
            runningTotal += score; // Add to running total

            int newDiceCount = diceSet.getValues().size();
            String nextState = (newDiceCount == 0) ? "6 (Hot Dice)" : String.valueOf(newDiceCount);

            tracker.record(currentDiceCount, nextState, score);

            if (newDiceCount == 0) {
                diceSet = new DiceSet();
                remainingDice = diceSet.roll();
            } else {
                remainingDice = diceSet.roll();
            }

            if (!policy.shouldRollAgain(runningTotal, remainingDice)) break;
        }

        return runningTotal;
    }

    private List<Integer> setAsideDice(List<Integer> remainingDice, DiceSet diceSet) {
        List<Integer> toSetAside = scorer.chooseScoringDice(remainingDice);
        diceSet.setAside(toSetAside);
        return toSetAside;
    }

    public void addToScore(int score) {
        totalScore += score;
    }

    public void setScore(int score) {
        totalScore = score;
    }

    public int getTotalScore() {
        return totalScore;
    }
}
