import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MonthlyReport {
    public ArrayList<MonthData> monthData;
    public String monthName;

    public MonthlyReport(String monthName) {
        monthData = new ArrayList<>();
        this.monthName = monthName;
    }

    public void readMonthlyReports(String path) {
        List<String> content = readFileContents(path);
        for (int i = 1; i < content.size(); i++) {
            String line = content.get(i); //item_name,is_expense,quantity,sum_of_one
            String[] lineContents = line.split(",");
            String itemName = lineContents[0];
            boolean isExpense = Boolean.parseBoolean(lineContents[1]);
            int quantity = Integer.parseInt(lineContents[2]);
            int sumOfOne = Integer.parseInt(lineContents[3]);

            MonthData monthData = new MonthData(itemName, isExpense, quantity, sumOfOne);
            this.monthData.add(monthData);
        }
    }

    public int getTotalIncome() {
        int sum = 0;
        HashMap<String, Integer> income = getMapOfIncome();
        if (income == null) {
            return -1;
        }

        for (String name : income.keySet()) {
            sum += income.get(name);
        }
        return sum;
    }

    public int getTotalExpense() {
        int sum = 0;
        HashMap<String, Integer> expenses = getMapOfExpenses();
        if (expenses == null) {
            return -1;
        }

        for (String name : expenses.keySet()) {
            sum += expenses.get(name);
        }
        return sum;
    }

    public void printMonthlyReport() {
        if (monthData.isEmpty()) {
            System.out.println("Отчёт еще не считан");
            return;
        }
        System.out.println("Отчёт за " + monthName + ":");
        getAndPrintTopProfitProduct();
        getAndPrintTopExpenseProduct();
    }

    private void getAndPrintTopProfitProduct() {
        String topProductName = "";
        int topProductProfit = 0;
        HashMap<String, Integer> income = getMapOfIncome();
        if (income == null) {
            return;
        }

        for (String name : income.keySet()) {
            int productProfit = income.get(name);
            if (productProfit > topProductProfit) {
                topProductName = name;
                topProductProfit = productProfit;
            }
        }
        System.out.println("Самый прибыльный товар - " + topProductName + " был продан на сумму " + topProductProfit);
    }

    private void getAndPrintTopExpenseProduct() {
        String topProductName = "";
        int topProductExpense = 0;
        HashMap<String, Integer> expenses = getMapOfExpenses();
        if (expenses == null) {
            return;
        }

        for (String name : expenses.keySet()) {
            int productProfit = expenses.get(name);
            if (productProfit > topProductExpense) {
                topProductName = name;
                topProductExpense = productProfit;
            }
        }
        System.out.println("Самая большая трата - " + topProductName + " на сумму " + topProductExpense);
    }

    private HashMap<String, Integer> getMapOfIncome() {
        if (monthData.isEmpty()) {
            return null;
        }
        HashMap<String, Integer> income = new HashMap<>();
        for (MonthData data : monthData) {
            if (!data.isExpense) {
                int amount = data.quantity * data.sumOfOne;
                income.put(data.itemName, income.getOrDefault(data.itemName, 0) + amount);
            }
        }
        return income;
    }

    private HashMap<String, Integer> getMapOfExpenses() {
        if (monthData.isEmpty()) {
            return null;
        }
        HashMap<String, Integer> expenses = new HashMap<>();
        for (MonthData data : monthData) {
            if (data.isExpense) {
                int amount = data.quantity * data.sumOfOne;
                expenses.put(data.itemName, expenses.getOrDefault(data.itemName, 0) + amount);
            }
        }
        return expenses;
    }

    public List<String> readFileContents(String path) {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
            return Collections.emptyList();
        }
    }
}
