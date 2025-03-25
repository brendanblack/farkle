import java.util.*;

public class Player {
    private int totalScore;
    private Policy policy;

    public Player(Policy policy) {
        this.policy = policy;
        this.totalScore = 0;
    }

    //TODO: Set aside scored dice after each turn

    public int takeTurn() {
        DiceSet diceSet = new DiceSet();
        int runningTotal = 0;
        List<Integer> remainingDice = diceSet.roll();

        while (true) {
            if (policy.getFarkleScorer().isFarkle(remainingDice)) break;
            int score = policy.getFarkleScorer().calculateScore(remainingDice);
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

