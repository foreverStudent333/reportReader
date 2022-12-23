public enum Commands {
    READ_MONTH_REPORTS (1),
    READ_YEAR_REPORTS (2),
    COMPARE_REPORTS (3),
    PRINT_MONTH_REPORT (4),
    PRINT_YEAR_REPORT (5),
    DEFAULT (0);

    final int num;

    Commands(int num) {
        this.num = num;
    }

    public static Commands findByNumber(int monthNumber) {
        for (Commands compType : values()) {
            if (compType.num == monthNumber) {
                return compType;
            }
        }
        return DEFAULT;
    }

}
