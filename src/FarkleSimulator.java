import java.util.*;

public class FarkleSimulator {
    private List<Policy> policies;
    private int numGames;

    public FarkleSimulator(List<Policy> policies, int numGames) {
        this.policies = policies;
        this.numGames = numGames;
    }

    public void runSimulations() {
        for (Policy policy : policies) {
            int totalTurns = 0;
            int totalScore = 0;

            for (int i = 0; i < numGames; i++) {
                Player player = new Player(policy);
                Game game = new Game(player);
                game.play();
                totalTurns += game.getTurnCount();
                totalScore += player.getTotalScore();
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
