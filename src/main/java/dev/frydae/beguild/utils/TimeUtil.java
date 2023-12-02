package dev.frydae.beguild.utils;

public enum TimeUtil {
    SECOND(1), MINUTE(60), HOUR(60 * 60), DAY(60 * 60 * 24), WEEK(60 * 60 * 24 * 7), MONTH(60 * 60 * 24 * 31), YEAR(60 * 60 * 24 * 31 * 12);

    int seconds;

    TimeUtil(int seconds) {
        this.seconds = seconds;
    }

    public static long parseTime(String duration) {
        String[] split = Patterns.COLON.split(duration);
        String unit = split[0];
        int mod = NumUtil.parseInt(split[1]);

        return switch (unit) {
            case "minute" -> MINUTE.inSeconds(mod);
            case "hour" -> HOUR.inSeconds(mod);
            case "day" -> DAY.inSeconds(mod);
            case "week" -> WEEK.inSeconds(mod);
            case "month" -> MONTH.inSeconds(mod);
            case "year" -> YEAR.inSeconds(mod);
            default -> 0;
        };
    }

    public int inSeconds(int mod) {
        return seconds * mod;
    }

    public long inSeconds(long mod) {
        return seconds * mod;
    }

    public int inMilli(int mod) {
        return seconds * mod * 1000;
    }

    public long inMilli(long mod) {
        return seconds * mod * 1000;
    }

    public int inTicks(int mod) {
        return seconds * 20 * mod;
    }

    public long inTicks(long mod) {
        return seconds * 20 * mod;
    }

    public static Long timestamp() {
        return System.currentTimeMillis() / 1000;
    }
}

