package ninjachicken784.scathaextras;

public class ScathaTracker {
    public static int count = 0;
    public static long startTime = 0;       // session start (first detection)
    public static long lastDetectionTime = 0;

    public static void reset() {
        count = 0;
        startTime = 0;
        lastDetectionTime = 0;
    }

    public static void addDetection() {
        if (startTime == 0) {
            startTime = System.currentTimeMillis();
        }
        count++;
        lastDetectionTime = System.currentTimeMillis();
    }

    public static String getTimeRunning() {
        if (startTime == 0) return "00:00";
        long elapsedMs = System.currentTimeMillis() - startTime;
        long seconds = elapsedMs / 1000;
        long minutes = seconds / 60;
        seconds %= 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    public static String getTimeSinceLast() {
        if (lastDetectionTime == 0) return "-";
        long elapsedMs = System.currentTimeMillis() - lastDetectionTime;
        long seconds = elapsedMs / 1000;

        if (seconds < 60) {
            return seconds + "s";
        } else if (seconds < 3600) {
            long minutes = seconds / 60;
            return minutes + "m";
        } else {
            long hours = seconds / 3600;
            return hours + "h";
        }
    }
}
