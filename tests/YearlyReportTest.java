import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class YearlyReportTest {
    YearlyReport yearlyReport;
    ArrayList<YearData> yearData;

    @BeforeEach
    void beforeEach() {
        yearData = new ArrayList<>(Arrays.asList(
                new YearData(1, 50, true),
                new YearData(1, 100, false),
                new YearData(2, 50, true),
                new YearData(2, 100, false)
        ));
        yearlyReport = new YearlyReport(yearData, 2021);
    }

    @Test
    void getAverageYearIncome() {
        assertTrue(yearlyReport.getAverageYearIncome() > 0);
        assertEquals(100, yearlyReport.getAverageYearIncome());
    }

    @Test
    void getAverageYearExpense() {
        assertTrue(yearlyReport.getAverageYearExpense() > 0);
        assertEquals(50, yearlyReport.getAverageYearExpense());
    }

    @Test
    void getTotalIncomeByMonth() {
        assertTrue(yearlyReport.getTotalIncomeByMonth(0) > 0);
        assertEquals(100, yearlyReport.getTotalIncomeByMonth(0));
    }

    @Test
    void getTotalExpenseByMonth() {
        assertTrue(yearlyReport.getTotalExpenseByMonth(0) > 0);
        assertEquals(50, yearlyReport.getTotalExpenseByMonth(0));
    }
}