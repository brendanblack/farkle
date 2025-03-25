import java.util.*;

public interface Policy {
    boolean shouldRollAgain(int runningTotal, List<Integer> remainingDice);
    String getName();
    FarkleScorer getFarkleScorer();
}
