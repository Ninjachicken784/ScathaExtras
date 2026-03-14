package ninjachicken784.scathaextras;

public class ScathaTracker {
    public static int count = 0;
    public static long startTime = 0;
    public static long lastDetectionTime = 0;

    public static void reset() {
        count = 0;
        startTime = 0;
        lastDetectionTime = 0;
    }

    public static void addDetection() {
        if (startTime == 0) startTime = System.currentTimeMillis();
        count++;
        lastDetectionTime = System.currentTimeMillis();
    }

    public static String getTimeRunning() {
        if (startTime == 0) return "00:00";
        long elapsed = (System.currentTimeMillis() - startTime) / 1000;
        return String.format("%02d:%02d", elapsed / 60, elapsed % 60);
    }

    public static String getTimeSinceLast() {
        if (lastDetectionTime == 0) return "-";
        long elapsed = (System.currentTimeMillis() - lastDetectionTime) / 1000;
        if (elapsed < 60) return elapsed + "s";
        if (elapsed < 3600) return (elapsed / 60) + "m";
        return (elapsed / 3600) + "h";
    }
}
