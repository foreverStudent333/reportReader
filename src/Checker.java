public class Checker {
    public MonthlyReport monthlyReport;
    public YearlyReport yearlyReport;
    public int monthNumber;

    public Checker(MonthlyReport monthlyReport, YearlyReport yearlyReport, int monthNumber) {
        this.monthlyReport = monthlyReport;
        this.yearlyReport = yearlyReport;
        this.monthNumber = monthNumber;
    }

    public boolean check(){
        int incomeMonthlyReport = monthlyReport.getTotalIncome();
        if(incomeMonthlyReport == -1){
            System.out.println("За " + (monthNumber+1) + " месяц отчёт еще не был считан!");
            return true;
        }
        int expenseMonthlyReport = monthlyReport.getTotalExpense();
        int incomeYearlyReport = yearlyReport.getTotalIncomeByMonth(monthNumber);
        if(incomeYearlyReport == -1){
            System.out.println("Годовой отчёт еще не был считан!");
            return false;
        }
        int expenseYearlyReport = yearlyReport.getTotalExpenseByMonth(monthNumber);
        if (incomeMonthlyReport == incomeYearlyReport && expenseMonthlyReport == expenseYearlyReport){
            System.out.println("Отчет за " + (monthNumber+1) + " месяц верный!");
        } else{
            System.out.println("Отчёт за " + (monthNumber+1) + " месяц не сходится!");
        }
        return true;
    }
}
