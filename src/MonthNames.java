import java.util.ArrayList;
import java.util.Arrays;

public class MonthNames {
    private static final ArrayList<String> months = new ArrayList<>(Arrays.asList("Январь", "Февраль", "Март", "Апрель",
            "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"));

    public static String getMonthNameByNumber(int num) {
        if (num < 1 || num > 12) {
            return "";
        }
        return months.get(num - 1);
    }
}
