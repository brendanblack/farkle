import java.util.*;

public abstract class FarkleSimulator {
    protected List<Policy> policies;
    protected int numGames;

    public FarkleSimulator(List<Policy> policies, int numGames) {
        this.policies = policies;
        this.numGames = numGames;
    }

    public abstract void runSimulations();

}
