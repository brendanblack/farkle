public class Game {
    private Player player;
    private int turnCount;
    private static final int WINNING_SCORE = 10000;

    public Game(Player player) {
        this.player = player;
        this.turnCount = 0;
    }

    public void play() {
        while (player.getTotalScore() < WINNING_SCORE) {
            int score = player.takeTurn();
            player.addToScore(score);
            turnCount++;
        }
    }

    public int getTurnCount() {
        return turnCount;
    }
}
