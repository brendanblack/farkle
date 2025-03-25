import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Policy> policies = new ArrayList<>();
        policies.add(new ThresholdPolicy(300));
        policies.add(new ThresholdPolicy(500));
        policies.add(new ThresholdPolicy(750));

        FarkleSimulator simulator = new FarkleSimulator(policies, 1000);
        simulator.runSimulations();
    }
}
