import java.util.HashMap;

/**
 * @author butrim
 */
public class YearlyReports {
    private HashMap<Integer, MonthlyReport> monthlyReports;
    private YearlyReport yearlyReport;

    public YearlyReports() {
    }

    void saveYearlyReport(YearlyReport yearlyReport) {
        this.yearlyReport = yearlyReport;
    }

    void saveMonthlyReports(HashMap<Integer, MonthlyReport> monthlyReports) {
        this.monthlyReports = monthlyReports;
    }

    public boolean getCompareResult() {
        return false;
    }
}
