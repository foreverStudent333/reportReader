import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class YearlyReport {
    public ArrayList<YearData> yearData;
    public int year;

    public YearlyReport(int year) {
        yearData = new ArrayList<>();
        this.year = year;
    }

    public void readYearlyReport() {
        String path = "resources/y.2021.csv";
        List<String> content = readFileContents(path);
        for (int i = 1; i < content.size(); i++) {
            String line = content.get(i); //month,amount,is_expense
            String[] lineContents = line.split(",");
            int month = Integer.parseInt(lineContents[0]);
            int amount = Integer.parseInt(lineContents[1]);
            boolean is_expense = Boolean.parseBoolean(lineContents[2]);

            YearData yearData = new YearData(month, amount, is_expense);
            this.yearData.add(yearData);
        }
    }

    public void printYearReport() {
        if (yearData.isEmpty()) {
            System.out.println("Отчёт за год еще не считан");
            return;
        }
        System.out.println("Отчёт за " + year + " год:");
        getAndPrintMonthlyProfit();
        System.out.println("Средний расход за все месяцы в году - " + getAverageYearExpense());
        System.out.println("Средний доход за все месяцы в году - " + getAverageYearIncome());
    }

    private void getAndPrintMonthlyProfit() {
        for (int i = 0; i < yearData.size(); i += 2) {
            int month = i / 2;
            System.out.println("Прибыль за " + (MonthNames.getMonthNameByNumber(month+1)) + " месяц: " + getMonthlyProfit(i));
        }
    }

    public int getAverageYearIncome() {
        if (yearData.isEmpty()) {
            return -1;
        }
        int sumIncome = getSumIncome();
        return sumIncome / (yearData.size() / 2);
    }


    public int getAverageYearExpense() {
        if (yearData.isEmpty()) {
            return -1;
        }
        int sumExpense = getSumExpense();
        return sumExpense / (yearData.size()/2);
    }

    private int getSumExpense() {
        int sumExpense = 0;
        for (YearData yearDatum : yearData) {
            if (yearDatum.is_expense) {
                sumExpense += yearDatum.amount;
            }
        }
        return sumExpense;
    }

    private int getSumIncome() {
        int sumIncome = 0;
        for (YearData yearDatum : yearData) {
            if (!yearDatum.is_expense) {
                sumIncome += yearDatum.amount;
            }
        }
        return sumIncome;
    }

    private int getMonthlyProfit(int month) {
        int profit = 0;
        int monthExpense;
        int monthIncome;
        if (yearData.get(month).is_expense) {
            monthExpense = yearData.get(month).amount;
            monthIncome = yearData.get(month + 1).amount;
        } else {
            monthExpense = yearData.get(month + 1).amount;
            monthIncome = yearData.get(month).amount;
        }
        profit = monthIncome - monthExpense;

        return profit;
    }

    public int getTotalIncomeByMonth(int month) {
        int sum = 0;
        if (yearData.isEmpty()) {
            return -1;
        }
        if (!(yearData.get(month * 2).is_expense)) {
            sum = yearData.get(month * 2).amount;
        } else {
            sum = yearData.get(month * 2 + 1).amount;
        }
        return sum;
    }

    public int getTotalExpenseByMonth(int month) {
        int sum = 0;
        if (yearData.isEmpty()) {
            return -1;
        }
        if (yearData.get(month * 2).is_expense) {
            sum = yearData.get(month * 2).amount;
        } else {
            sum = yearData.get(month * 2 + 1).amount;
        }
        return sum;
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
