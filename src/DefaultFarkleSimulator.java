import java.util.*;

/**
 * DefaultFarkleSimulator simulates full Farkle games for a given set of policies.
 * for each policy-- runs a number of games, tracks avg performance stats,
 * and merges state transition information to evaluate expected behavior
 */
public class DefaultFarkleSimulator extends FarkleSimulator {

    /**
     * Constructor: passes the list of policies and number of games to the parent class.
     *
     * @param policies the list of policies to simulate
     * @param numGames the number of games to run for each policy
     */
    public DefaultFarkleSimulator(List<Policy> policies, int numGames) {
        super(policies, numGames);
    }

    public void runSimulations() {
        TransitionTracker combinedTracker = new TransitionTracker(); // NEW

        // iterating through each policy to simulate and analyze separately
        for (Policy policy : policies) {
            int totalTurns = 0;
            int totalScore = 0;

            for (int i = 0; i < numGames; i++) {
                Player player = new Player(policy);
                Game game = new Game(player);
                game.play();
                totalTurns += game.getTurnCount();
                totalScore += player.getTotalScore();
            
                combinedTracker.merge(player.getTracker()); // Merge each game's tracker
            }
            

            double avgTurns = totalTurns / (double) numGames;
            double avgScore = totalScore / (double) numGames;
            double avgPointsPerTurn = totalScore / (double) totalTurns;

            System.out.println("Policy: " + policy.getName());
            System.out.println("  Avg Turns: " + avgTurns);
            System.out.println("  Avg Score: " + avgScore);
            System.out.println("  Avg Points/Turn: " + avgPointsPerTurn);
        }


    }
}
