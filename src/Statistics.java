import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Statistics {
    Long totalTraffic = 0L;
    LocalDateTime minTime = LocalDateTime.MAX;
    LocalDateTime maxTime = LocalDateTime.MIN;
    int ya = 0;
    int go = 0;
    int winOsCount = 0;
    int linuxOsCount = 0;
    int macOsCount = 0;
homework
    int edgeCount = 0;
=======
master
    List<LogEntry> logs = new ArrayList<>();

    void addEntry(LogEntry logEntry) {
        logs.add(logEntry);
        totalTraffic = (totalTraffic + logEntry.getResponceSize());
        if (logEntry.time.compareTo(minTime) < 0) minTime = logEntry.getTime();
        if (logEntry.time.compareTo(maxTime) > 0) maxTime = logEntry.getTime();
        if (logEntry.agent != null && logEntry.agent.yandexBot == true) ya++;
        if (logEntry.agent != null && logEntry.agent.googleBot == true) go++;
        if (logEntry.agent.typeOs == TypeOs.LINUX) linuxOsCount++;
        if (logEntry.agent.typeOs == TypeOs.MACINTOSH) macOsCount++;
        if (logEntry.agent.typeOs == TypeOs.WINDOWS) winOsCount++;
homework
        if (logEntry.agent.typeBrowser == Browser.EDGE.name()) edgeCount++;
=======
master
    }

    int getTrafficRate(LocalDateTime maxTime, LocalDateTime minTime, Long totalTraffic) {
        int fullTime = (86400 * (maxTime.getDayOfYear() - minTime.getDayOfYear())
                + 3600 * (maxTime.getHour() - minTime.getHour())
                + 60 * (maxTime.getMinute() - minTime.getMinute())
                + (maxTime.getSecond() - minTime.getSecond())) / 3600;
        return Math.toIntExact((totalTraffic)/ 1024 / fullTime);
    }

    @Override
    public String toString() {
        return  "Статистика: totalTraffic (в байтах): " + totalTraffic +
                ", minTime: " + minTime +
                ", maxTime: " + maxTime +
                ", Число запросов от YandexBot: " + ya +
                ", Число запросов от Googlebot: " + go +
                ", Число запросов c ос Windows: " + winOsCount +
                ", Число запросов c ос Linux: " + linuxOsCount +
homework
                ", Число запросов c ос Mac OS: " + macOsCount+
        ", Через Edge: " + edgeCount;
=======
                ", Число запросов c ос Mac OS: " + macOsCount;
master
    }
}
