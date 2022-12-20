import java.util.Scanner;

public class CommandLineManager {
    Scanner scanner = new Scanner(System.in);
    MonthlyReport monthlyReport1 = new MonthlyReport();
    MonthlyReport monthlyReport2 = new MonthlyReport();
    MonthlyReport monthlyReport3 = new MonthlyReport();
    YearlyReport yearlyReport = new YearlyReport();
    public void printMenuAndHandleCommandInfinitely() {
        printMenu();
        int command = scanner.nextInt();

        while (command != 0) {
            switch (command){
                case 1:
                    monthlyReport1.readMonthlyReports("resources/m.202101.csv");
                    monthlyReport2.readMonthlyReports("resources/m.202102.csv");
                    monthlyReport3.readMonthlyReports("resources/m.202103.csv");
                    System.out.println("Месячные отчёты считаны!");
                    break;
                case 2:
                    yearlyReport.readYearlyReport();
                    System.out.println("Годовой отчет считан!");
                    break;
                case 3:

                    break;
                case 4:
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Такой команды нет, введите команду снова.");
                    break;
            }

            printMenu();
            command = scanner.nextInt();
        }

    }

    public void printMenu() {
        System.out.println("Привет! Что хочешь сделать?");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("0 - Выйти из приложения.");
    }
}
