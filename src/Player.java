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

            //check for farkle
            if (scorer.isFarkle(remainingDice))  {
                runningTotal = 0;
                break;
            }

            // set aside dice and update remaining dice
            List<Integer> toSetAside = setAsideDice(remainingDice, diceSet);
            remainingDice = diceSet.getValues();

            // get score and update running total
            int score = scorer.calculateScore(toSetAside);
            runningTotal += score;

            // check if we should stop
            if (!policy.shouldRollAgain(runningTotal, remainingDice)) break;

            // roll remaining dice
            remainingDice = diceSet.roll();
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

    public void setScore(int score) { totalScore = score; }

    public int getTotalScore() {
        return totalScore;
    }
}

