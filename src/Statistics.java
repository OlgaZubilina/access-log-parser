import java.time.LocalDateTime;
import java.util.*;

public class Statistics {
    Long totalTraffic = 0L;
    LocalDateTime minTime = LocalDateTime.MAX;
    LocalDateTime maxTime = LocalDateTime.MIN;
    int ya = 0;
    int go = 0;
    int winOsCount = 0;
    int linuxOsCount = 0;
    int macOsCount = 0;
    int edgeCount = 0;
    List<LogEntry> logs = new ArrayList<>();
    HashSet<String> pages = new HashSet<>();
    HashMap<String,Integer> osCount;

    public Statistics() {
        this.osCount = new HashMap<>();
        TypeOs[]types = TypeOs.values();
        for (int i = 0;i<types.length;i++)
            osCount.put(types[i].name(),0);
    }

    void addEntry(LogEntry logEntry) {
        logs.add(logEntry);
        totalTraffic = (totalTraffic + logEntry.getResponceSize());
        getMinMaxTime(logEntry);
        getBotStatistics(logEntry);
        if (logEntry.agent.typeBrowser == Browser.EDGE.name()) edgeCount++;
        getPages(logEntry);
        getOsCount(logEntry);


    }

    void getMinMaxTime(LogEntry logEntry){
    if (logEntry.time.compareTo(minTime) < 0) minTime = logEntry.getTime();
    if (logEntry.time.compareTo(maxTime) > 0) maxTime = logEntry.getTime();
}
    HashSet<String> getPages(LogEntry logEntry) {
        if (logEntry.responceCode == 200) pages.add(logEntry.referer);
        return pages;
    }
void getBotStatistics(LogEntry logEntry){
    if (logEntry.agent != null && logEntry.agent.yandexBot == true) ya++;
    if (logEntry.agent != null && logEntry.agent.googleBot == true) go++;
}
HashMap<String,Integer> getOsCount (LogEntry logEntry){
    TypeOs[]types = TypeOs.values();
    for (int i = 0;i<types.length;i++)
    { if (logEntry.agent.typeOs==null)return osCount;
    if (logEntry.agent.typeOs.equals(types[i].name()))
osCount.put(types[i].name(),osCount.get(types[i].name())+1);}
    return osCount;
   }
   HashMap<String,Double>getOsStatistic(HashMap<String,Integer> osCount){
  HashMap<String,Double>osStatistic = new HashMap<>();
    int totalCount = 0;
       TypeOs[]types = TypeOs.values();
   for (int i = 0;i<types.length;i++)
      totalCount = totalCount+osCount.get(types[i].name());

       for (int i = 0;i<types.length;i++)
   if (osCount.containsKey(types[i].name()))
   {
        osStatistic.put(types[i].name(),Double.valueOf(osCount.get(types[i].name()))/totalCount);}
       return osStatistic;
   }


    int getTrafficRate(LocalDateTime maxTime, LocalDateTime minTime, Long totalTraffic) {
        int fullTime = (86400 * (maxTime.getDayOfYear() - minTime.getDayOfYear())
                + 3600 * (maxTime.getHour() - minTime.getHour())
                + 60 * (maxTime.getMinute() - minTime.getMinute())
                + (maxTime.getSecond() - minTime.getSecond())) / 3600;
        return Math.toIntExact((totalTraffic) / 1024 / fullTime);
    }

    @Override
    public String toString() {
        return "Траффик: " + totalTraffic +
                ", minTime=" + minTime +
                ", maxTime=" + maxTime +
                ", количество запросов от Yandexbot: " + ya +
                ", количество запросов от Googlebot: " + go +
                ", количество запросов через Edge: " + edgeCount +
                ", список загруженных страниц: " + pages.toString() +
                ", чаcтота встречаемости разных операционных систем: " + osCount +
                '}';
    }

}
