public class Main {
    public static void main(String[] args) {
        System.out.println("\nExpectedRewardDP\n");
        FarkleScorer scorer = new StandardFarkleScorer();
        ExpectedRewardDP dp = new ExpectedRewardDP(scorer);
        dp.findPolicy();
        dp.printThresholds();
    }
}
