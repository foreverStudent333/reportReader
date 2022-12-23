import java.util.Scanner;

public class CommandLineManager {
    Commands command;
    Scanner scanner = new Scanner(System.in);
    MonthlyReport monthlyReport = new MonthlyReport();
    YearlyReport yearlyReport = new YearlyReport(2021);

    Checker checker = new Checker(monthlyReport, yearlyReport);

    public void printMenuAndHandleCommandInfinitely() {
        printMenu();
        int userInputCommand = scanner.nextInt();

        while (userInputCommand != 0) {
            command = Commands.findByNumber(userInputCommand);

            switch (command) {
                case READ_MONTH_REPORTS:
                    monthlyReport.readMonthlyReports();
                    System.out.println("Месячные отчёты считаны!");
                    break;
                case READ_YEAR_REPORTS:
                    yearlyReport.readYearlyReport();
                    System.out.println("Годовой отчет считан!");
                    break;
                case COMPARE_REPORTS:
                    checker.check();
                    break;
                case PRINT_MONTH_REPORT:
                    monthlyReport.printMonthlyReport();
                    break;
                case PRINT_YEAR_REPORT:
                    yearlyReport.printYearReport();
                    break;
                default:
                    System.out.println("Такой команды нет, введите команду снова.");
                    break;
            }

            printMenu();
            userInputCommand = scanner.nextInt();
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
