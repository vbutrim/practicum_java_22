import java.util.ArrayList;
import java.util.List;

/**
 * @author butrim
 *
 * Формат месячного отчёта
 * Месячный отчёт содержит информацию о всех тратах, произведённых в течение календарного месяца.
 * Сюда попадает информация как о доходах, так и о расходах парка развлечений.
 *
 * Пример CSV файла с месячным отчётом:
 * item_name,is_expense,quantity,sum_of_one
 * Воздушные шарики,TRUE,5000,5
 * Автоматы с мороженным,TRUE,12,15000
 * Продажа мороженного,FALSE,1000,120
 *
 * Месячные отчёты состоят из четырёх полей:
 * item_name — название товара;
 * is_expense — одно из двух значений: TRUE или FALSE. Обозначает, является ли запись тратой (TRUE) или доходом (FALSE);
 * quantity — количество закупленного или проданного товара;
 * sum_of_one — стоимость одной единицы товара. Целое число.
 */
public class MonthlyReport {
    // option 1:
    // list of items

    // option 2:
    // array of items

    // option 3:
    // map by some key

    // option 4:


    private List<MonthlyReportItem> expenses;
    private List<MonthlyReportItem> deposits;

    // another category items

    MonthlyReport(List<MonthlyReportItem> items) {
        this.expenses = new ArrayList<>();
        this.deposits = new ArrayList<>();

        for (MonthlyReportItem item : items) {
            if (item.isExpense) {
                expenses.add(item);
            } else {
                deposits.add(item);
            }
        }
    }

    public int getSumExpenses() {

    }

    public int getSumDeposits() {

    }
}
