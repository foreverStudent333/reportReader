import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class YearlyReport {
    public ArrayList<YearData> yearData;

    public YearlyReport() {
        yearData = new ArrayList<>();
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

    public List<String> readFileContents(String path) {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
            return Collections.emptyList();
        }
    }
}
