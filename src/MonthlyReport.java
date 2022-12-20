import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MonthlyReport {
    public ArrayList<MonthData> monthData;

    public MonthlyReport() {
        monthData = new ArrayList<>();
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

    public List<String> readFileContents(String path) {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
            return Collections.emptyList();
        }
    }
}
