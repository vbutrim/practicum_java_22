import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author butrim
 */
public class ReportParser {

    public static MonthlyReport readMonthlyReport(String fileName) {
        List<String> lines = readFileContents(fileName);

        ArrayList<MonthlyReportItem> items = new ArrayList<>();

        // skip first line and read others
        for (int i = 1; i < lines.size(); ++i) {
            // 1: read lines
            String line = lines.get(i);
            String[] fields = line.split(",");

            // 2: parse fields
            String itemName = fields[0];
            boolean isExpense = Boolean.parseBoolean(fields[1]);
            int quantity = Integer.parseInt(fields[2]);
            int price = Integer.parseInt(fields[3]);

            // 3: create item
            MonthlyReportItem item = new MonthlyReportItem(
                    itemName,
                    isExpense,
                    quantity,
                    price
            );

            // 4: add item to items
            items.add(item);
        }

        // 5: return report
        return new MonthlyReport(items);
    }

    public static YearlyReport readYearlyReport(String fileName) {
        return new YearlyReport();
    }

    private static List<String> readFileContents(String path) {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
            return Collections.emptyList();
        }
    }
}
