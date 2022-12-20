public class MonthData {
    public String itemName;
    public boolean isExpense;
    public int quantity;
    public int sumOfOne;

    public MonthData(String itemName, boolean isExpense, int quantity, int sumOfOne) {
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
    }
}
