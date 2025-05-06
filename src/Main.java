import java.util.*;

public class Main {
    public static void main(String[] args) {
        int numGames = 100000;

        Policy alwaysRoll = new ThresholdPolicy(10000);  // high threshold to force always roll
        Player player = new Player(alwaysRoll);

        //run a single game multiple times to gather stats
        for (int i = 0; i < numGames; i++) {
            Game game = new Game(player);
            game.play();
        }

        //print the empirical state transition data
        player.getTracker().printResults();
    }
}
