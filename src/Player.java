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

    //TODO: Set aside scored dice after each turn

    public int takeTurn() {
        DiceSet diceSet = new DiceSet();
        int runningTotal = 0;
        List<Integer> remainingDice = diceSet.roll();

        while (true) {
            if (scorer.isFarkle(remainingDice)) break;
            int score = scorer.calculateScore(remainingDice);
            runningTotal += score;
            if (!policy.shouldRollAgain(runningTotal, remainingDice)) break;
            remainingDice = diceSet.roll();
        }

        return runningTotal;
    }

    public void addToScore(int score) {
        totalScore += score;
    }

    public int getTotalScore() {
        return totalScore;
    }
}

