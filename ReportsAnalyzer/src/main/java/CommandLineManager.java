/**
 * @author butrim
 */
public class CommandLineManager {
    private YearlyReports yearlyReports;

    public CommandLineManager(YearlyReports yearlyReports) {
        this.yearlyReports = yearlyReports;
    }

    public void printMenuAndHandleCommandInfinitely() {
        int command = 1;

        if (command == 1) {
            readAndSaveMonthlyReports();
        } else if (command == 2) {
            readAndSaveYearlyReports();
        } else if (command == 3) {
            compareMonthlyAndYearlyReports();
        }
    }

    private void readAndSaveMonthlyReports() {

    }

    private void readAndSaveYearlyReports() {

    }

    private void compareMonthlyAndYearlyReports() {
        System.out.println(yearlyReports.getCompareResult());
    }
}
