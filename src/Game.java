public class Game {
    private final Player player;
    private int turnCount;
    private static final int WINNING_SCORE = 10000;

    public Game(Player player) {
        this.player = player;
        this.turnCount = 0;
    }

    public void play() {

        //don't count score until player scores over 500 in one turn
        while (player.getTotalScore()<500) {
            player.setScore(0);
            int score = player.takeTurn();
            player.addToScore(score);
            turnCount++;
        }

        //begin accumulating score once player has scored 500 in one turn
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
