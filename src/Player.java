import java.util.*;

public class Player {
    private int totalScore;
    private final Policy policy;
    private final FarkleScorer scorer;

    public Player(Policy policy) {
        this.policy = policy;
        this.totalScore = 0;
        this.scorer = policy.getFarkleScorer();
    }


    public int takeTurn() {
        DiceSet diceSet = new DiceSet();
        int runningTotal = 0;
        List<Integer> remainingDice = diceSet.roll();

        while (true) {
            if (scorer.isFarkle(remainingDice))  {
                runningTotal = 0;
                break;
            }

            // set aside dice
            List<Integer> toSetAside = scorer.chooseScoringDice(remainingDice);
            diceSet.setAside(toSetAside);

            // get score
            int score = scorer.calculateScore(toSetAside);
            runningTotal += score;

            // check if we should stop
            if (!policy.shouldRollAgain(runningTotal, remainingDice)) break;

            // roll remaining dice
            remainingDice = diceSet.roll();
        }

        return runningTotal;
    }

    public void addToScore(int score) {
        totalScore += score;
    }

    public void setScore(int score) { totalScore = score; }

    public int getTotalScore() {
        return totalScore;
    }
}

