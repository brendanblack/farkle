import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Policy> thresholdPolicies = new ArrayList<>();
        ThresholdPolicySimulator simulator = new ThresholdPolicySimulator(thresholdPolicies, 1000);
        simulator.runSimulations();
    }
}
