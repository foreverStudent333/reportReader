import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MonthlyReport {
    public HashMap<Integer, ArrayList<MonthData>> everyMonthData;

    public MonthlyReport() {
        everyMonthData = new HashMap<>();
    }

    public void readMonthlyReports() {
        for (int i = 1; i <= 3; i++) {
            readMonthlyReportsByPath("resources/m.20210" + i + ".csv", i);
        }
    }

    public void readMonthlyReportsByPath(String path, int month) {
        ArrayList<MonthData> monthData = new ArrayList<>();
        List<String> content = readFileContents(path);
        if (content.isEmpty()) {
            return;
        }
        for (int i = 1; i < content.size(); i++) {
            String line = content.get(i); //item_name,is_expense,quantity,sum_of_one
            String[] lineContents = line.split(",");
            String itemName = lineContents[0];
            boolean isExpense = Boolean.parseBoolean(lineContents[1]);
            int quantity = Integer.parseInt(lineContents[2]);
            int sumOfOne = Integer.parseInt(lineContents[3]);

            MonthData monthDatas = new MonthData(itemName, isExpense, quantity, sumOfOne);
            monthData.add(monthDatas);
        }
        everyMonthData.put(month, monthData);
    }

    public int getTotalIncome(int month) {
        int sum = 0;
        HashMap<String, Integer> income = getMapOfIncomePerMonth(month);
        if (income == null) {
            return -1;
        }

        for (String name : income.keySet()) {
            sum += income.get(name);
        }
        return sum;
    }

    public int getTotalExpense(int month) {
        int sum = 0;
        HashMap<String, Integer> expenses = getMapOfExpensesPerMonth(month);
        if (expenses == null) {
            return -1;
        }

        for (String name : expenses.keySet()) {
            sum += expenses.get(name);
        }
        return sum;
    }

    public void printMonthlyReport() {
        if (everyMonthData.isEmpty()) {
            System.out.println("Месячный отчёт еще не был считан!");
            return;
        }
        for (int i = 0; i < everyMonthData.size(); i++) {
            String monthName = MonthNames.getMonthNameByNumber(i + 1);
            System.out.println("Отчёт за " + monthName + ":");
            getAndPrintTopMonthProfitProduct(i + 1);
            getAndPrintTopMonthExpenseProduct(i + 1);
        }

    }

    private void getAndPrintTopMonthProfitProduct(int month) {
        String topProductName = "";
        int topProductProfit = 0;
        HashMap<String, Integer> income = getMapOfIncomePerMonth(month);
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

    private void getAndPrintTopMonthExpenseProduct(int month) {
        String topProductName = "";
        int topProductExpense = 0;
        HashMap<String, Integer> expenses = getMapOfExpensesPerMonth(month);
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

    private HashMap<String, Integer> getMapOfIncomePerMonth(int month) {
        ArrayList<MonthData> monthData = getMonthDataList(month);
        if (monthData == null || monthData.isEmpty()) {
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

    private HashMap<String, Integer> getMapOfExpensesPerMonth(int month) {
        ArrayList<MonthData> monthData = getMonthDataList(month);
        if (monthData == null || monthData.isEmpty()) {
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

    private ArrayList<MonthData> getMonthDataList(int num) {
        if (everyMonthData.isEmpty() || num > everyMonthData.size() || num < 1) {
            return null;
        }
        return everyMonthData.get(num);
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
