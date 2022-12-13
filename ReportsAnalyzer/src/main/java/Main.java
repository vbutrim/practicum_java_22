/**
 * @author butrim
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Hey, there");

        CommandLineManager commandLineManager = new CommandLineManager();

        MonthlyReport monthlyReport = ReportParser.readMonthlyReport("m.202101.csv");
    }
}
