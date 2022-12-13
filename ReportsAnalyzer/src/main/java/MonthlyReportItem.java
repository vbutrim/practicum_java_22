/**
 * @author butrim
 */
public class MonthlyReportItem {
    public String name;
    public boolean isExpense;
    public int quantity;
    public int price; // sum_of_one

    public MonthlyReportItem(String name, boolean isExpense, int quantity, int price) {
        this.name = name;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.price = price;
    }
}
