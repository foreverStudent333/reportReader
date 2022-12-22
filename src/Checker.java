public class Checker {
    public MonthlyReport monthlyReport;
    public YearlyReport yearlyReport;


    public Checker(MonthlyReport monthlyReport, YearlyReport yearlyReport) {
        this.monthlyReport = monthlyReport;
        this.yearlyReport = yearlyReport;

    }

    public void check() {
        if (monthlyReport.everyMonthData.isEmpty()) {
            System.out.println("Месячный отчёт еще не был считан!");
            return;
        }
        int month;
        for (int i = 0; i < monthlyReport.everyMonthData.size(); i++) {
            month = i + 1;
            int incomeMonthlyReport = monthlyReport.getTotalIncome(month);
            if (incomeMonthlyReport == -1) {
                System.out.println("За " + (month) + " месяц отчёт еще не был считан!");
                return;
            }
            int expenseMonthlyReport = monthlyReport.getTotalExpense(month);
            int incomeYearlyReport = yearlyReport.getTotalIncomeByMonth(i);
            if (incomeYearlyReport == -1) {
                System.out.println("Годовой отчёт еще не был считан!");
                return;
            }
            int expenseYearlyReport = yearlyReport.getTotalExpenseByMonth(i);
            if (incomeMonthlyReport == incomeYearlyReport && expenseMonthlyReport == expenseYearlyReport) {
                System.out.println("Отчет за " + (month) + " месяц верный!");
            } else {
                System.out.println("Отчёт за " + (month) + " месяц не сходится!");
            }
        }
    }
}
