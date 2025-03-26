import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Policy> policies = new ArrayList<>();
        policies.add(new ThresholdPolicy(300));
        policies.add(new ThresholdPolicy(500));
        policies.add(new ThresholdPolicy(750));
        policies.add(new ThresholdPolicy(1000));
        policies.add(new ThresholdPolicy(1250));
        policies.add(new ThresholdPolicy(1500));
        policies.add(new ThresholdPolicy(3000));

        FarkleSimulator simulator = new FarkleSimulator(policies, 10000);
        simulator.runSimulations();
    }
}
