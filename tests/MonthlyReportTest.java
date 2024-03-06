import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MonthlyReportTest {
    MonthlyReport monthlyReport;
    HashMap<Integer, ArrayList<MonthData>> everyMonthData;

    @BeforeEach
    void beforeEach() {
        everyMonthData = new HashMap<>();
        everyMonthData.put(1, new ArrayList<>(Arrays.asList(
                new MonthData("item1", true, 10, 100),
                new MonthData("item2", true, 1, 100),
                new MonthData("item3", false, 5, 100),
                new MonthData("item4", false, 1, 10))
        ));

        everyMonthData.put(2, new ArrayList<>(Arrays.asList(
                new MonthData("item1", false, 10, 1000),
                new MonthData("item2", false, 1, 1000),
                new MonthData("item3", true, 5, 1000),
                new MonthData("item4", true, 1, 10))
        ));

        everyMonthData.put(3, new ArrayList<>(Arrays.asList(
                new MonthData("item1", true, 10, 10),
                new MonthData("item2", true, 1, 10),
                new MonthData("item3", false, 5, 10),
                new MonthData("item4", false, 1, 1))
        ));

        monthlyReport = new MonthlyReport(everyMonthData);
    }

    @Test
    void getTotalIncome() {
        assertTrue(monthlyReport.getTotalIncome(1) > 0);
        assertEquals(510, monthlyReport.getTotalIncome(1));
        assertEquals(11000, monthlyReport.getTotalIncome(2));
        assertEquals(51, monthlyReport.getTotalIncome(3));
    }

    @Test
    void getTotalExpense() {
        assertTrue(monthlyReport.getTotalExpense(1) > 0);
        assertEquals(1100, monthlyReport.getTotalExpense(1));
        assertEquals(5010, monthlyReport.getTotalExpense(2));
        assertEquals(110, monthlyReport.getTotalExpense(3));
    }

    @Test
    void readFileContents() {
        List<String> fileData = monthlyReport.readFileContents("resources/m.202101.csv");
        assertFalse(fileData.isEmpty());
        assertEquals("item_name,is_expense,quantity,sum_of_one", fileData.get(0));
    }
}
