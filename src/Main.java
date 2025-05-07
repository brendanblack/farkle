import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("\nExpectedRewardPolicy\n");
        FarkleScorer scorer = new StandardFarkleScorer();
        ExpectedRewardDP dp = new ExpectedRewardDP(scorer);
        dp.findPolicy();
        dp.printThresholds();


        System.out.println("\nExpectedRewardValidationSimulator: pits ExpectedRewardPolicy against DiceNumberPolicy to\n" +
                "determine if the expected reward system is better than a naive playing strategy\n(stop rolling with" +
                "less than 3 dice).\n");
        ExpectedRewardPolicy p0 = new ExpectedRewardPolicy();
        DiceNumberPolicy p1 = new DiceNumberPolicy();
        ArrayList<Policy> list = new ArrayList<>();
        list.add(p0);
        list.add(p1);
        ExpectedRewardValidationSimulator ervs = new ExpectedRewardValidationSimulator(list, 1000);
        ervs.runSimulations();


        /**
         * This ThresholdPolicy Code is commented out because the threshold policy gave inconsistent results
         */
//        System.out.println("\nThresholdPolicy (stop rolling when you have more than [threshold] points): \n");
//        FarkleScorer s = new StandardFarkleScorer();
//        ThresholdPolicySimulator tps = new ThresholdPolicySimulator(new ArrayList<Policy>(), 1000);
//        tps.runSimulations();
    }
}
