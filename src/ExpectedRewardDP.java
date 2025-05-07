import java.util.*;

public class ExpectedRewardDP {
    /**
     * Set a few constant values and helper methods here so the code is less ugly
     */
    private static final int POINT_INCREMENT = 50;
    private static final int NUM_POSSIBLE_POINT_VALUES = 200;
    private final FarkleScorer scorer;
    private double[][] memoTable;
    private final HashMap<Integer, List<List<Integer>>> rollsByDice = new HashMap<>();

    public ExpectedRewardDP(FarkleScorer scorer) {
        this.scorer = scorer;
        initializeMemoTable();
        populateRollsByDiceMap();
    }

    /**
     * This is the meat of the whole thing. A bottom-up dynamic programming approach. Basically, instead of starting
     * at a given (dice number, point value), trying to brute force every path and calculate a weighted average
     * (which just ran indefinitely), this method starts all the way at the maximum possible score
     * of 10,000 (the 'bottom' of the recursion tree). If we say that the expected reward of rolling when you have 10k
     * point is 0 (essentially limiting recursion to the maximum possible score achievable in the game),
     * this creates a 'base case' for a recursive algorithm. The bottom-up DP approach starts at this base case and
     * moves backward up the recursion tree, adding each expected reward calculation to our memoTable. This allows us to
     * simply look up the recursive expected rewards we need from the memoTable instead of recursing downward. After
     * we've seen an expected reward for a certain (dice number, point value), we don't need to recurse downward to
     * find it again!
     *
     * This means the algorithm runs in linear time instead of exponential time!
     *
     * Here's the youtube video I used to get the idea.
     * https://www.youtube.com/watch?v=OQ5jsbhAv_M
     */
    public void findPolicy() {
        for (int pointColumnIndex = NUM_POSSIBLE_POINT_VALUES-1; pointColumnIndex >= 0; pointColumnIndex--) {
            for (int diceNum = 1; diceNum <= 6; diceNum++) {
                double continueEv = 0;
                double stopEv = (pointColumnIndex+1)*POINT_INCREMENT;
                for (List<Integer> roll : rollsByDice.get(diceNum)) {
                    List<Integer> scoringDice = scorer.chooseScoringDice(roll);
                    int score = scorer.calculateScore(scoringDice);

                    double nextScore = 0;
                    int nextDiceNum = nextDiceNumFromPoints(diceNum, roll);
                    int nextPointColumnIndex = Math.min(pointColumnIndex+(score/POINT_INCREMENT), NUM_POSSIBLE_POINT_VALUES-1);

                    if (score!=0) {
                        nextScore = memoTable[nextDiceNum-1][nextPointColumnIndex];
                    }

                    continueEv += getRollProb(diceNum)*nextScore;
                }
                memoTable[diceNum-1][pointColumnIndex] = Math.max(stopEv, continueEv);
            }
        }
    }

    public void printThresholds() {
        for (int diceNum = 1; diceNum <= 6; diceNum++) {
            for (int pointColumnIndex = 1; pointColumnIndex <= NUM_POSSIBLE_POINT_VALUES; pointColumnIndex++) {
                double score = pointColumnIndex * POINT_INCREMENT;
                double ev = memoTable[diceNum-1][pointColumnIndex-1];
                if (Math.abs(ev - score) < .001) {
                    System.out.println("Keep rolling with " + diceNum + " dice if you have under " + score + " points in your running total.");
                    break;
                }
            }
        }
    }

    /**
     * Below are the helper methods that I made so this whole thing is more readable
     */

    //gets the probability of a given roll occurring
    private double getRollProb(int diceNumber) {
        return 1/Math.pow(6, diceNumber);
    }


    /**
     * sets up the memoTable and sets the expected reward for each (diceNum, pointColumnIndex) in the memo table
     * equal to the current point value. Worst case, we get 0 points and decide to stop with the current
     * number of points we have
     */
    private void initializeMemoTable() {
        memoTable = new double[6][NUM_POSSIBLE_POINT_VALUES];
        for (int diceNumIndex = 0; diceNumIndex <= 5; diceNumIndex++) {
            for (int pointColumnIndex = 0; pointColumnIndex < NUM_POSSIBLE_POINT_VALUES; pointColumnIndex++) {
                memoTable[diceNumIndex][pointColumnIndex] = pointColumnIndex * POINT_INCREMENT;
            }
        }
    }


    /**
     * helper method that gets the remaining dice given amount of current dice and a
     * particular roll.
     */
    private int nextDiceNumFromPoints(int currentDiceNum, List<Integer> dice) {
        List<Integer> scoringDice = scorer.chooseScoringDice(dice);
        if (scoringDice.isEmpty()) {
            return 6;
        }

        int leftover = currentDiceNum - scoringDice.size();

        if (leftover == 0) {
            return 6;
        }
        return leftover;
    }


    /**
     * method that gets all possible rolls for each possible amount of dice 1-6 and adds it to
     * a map so they can be accessed by # of dice
     */
    private void populateRollsByDiceMap() {
        for (int diceNum = 1; diceNum <= 6; diceNum++) {
            List<List<Integer>> rolls = new DiceSet().getAllOutcomes(diceNum);
            rollsByDice.put(diceNum, rolls);
        }
    }

}
