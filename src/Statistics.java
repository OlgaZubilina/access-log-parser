
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;



public class Statistics {
    Long totalTraffic = 0L;
    LocalDateTime minTime = LocalDateTime.MAX;
    LocalDateTime maxTime = LocalDateTime.MIN;
    int ya = 0;
    int go = 0;
    List<LogEntry> logs = new ArrayList<>();
    HashSet<String> pages = new HashSet<>();
    HashMap<String, Integer> osCount;
    HashSet<String> pagesNotFound = new HashSet<>();
    HashMap<String, Integer> browsersCount;
    HashSet<String> sites = new HashSet<>();
    HashMap<String ,Integer> users = new HashMap<>();



    public Statistics() {
        this.osCount = new HashMap<>();
        TypeOs[] types = TypeOs.values();
        for (int i = 0; i < types.length; i++)
            osCount.put(types[i].name(), 0);
        this.browsersCount = new HashMap<>();
        Browser[] browsers = Browser.values();
        for (int i = 0; i < browsers.length; i++)
            browsersCount.put(browsers[i].name(), 0);
    }

    void addEntry(LogEntry logEntry) {
        logs.add(logEntry);
        getTotalTraffic(logEntry);
        getMinMaxTime(logEntry);
        getBotStatistics(logEntry);
        getPages(logEntry);
        getPagesNotFound(logEntry);
        getOsCount(logEntry);
        getBrowsersCount(logEntry);
        getSite(logEntry);
        addUser(logEntry);

    }

    private void addUser(LogEntry logEntry) {
if (logEntry.agent != null && logEntry.agent.isBot == false)
if(users.containsKey(logEntry.ipAddr)) users.put(logEntry.ipAddr,(users.get(logEntry.ipAddr)+1));
else users.put(logEntry.ipAddr, 1);
    }

    int getMaxVizitsForUser(HashMap<String,Integer> users){
        return users.values().stream().max(Integer::compare).get();
    }

    int getAllVizits(HashMap<String,Integer> users){
        return users.values().stream().reduce(0, Integer::sum);
    }
    void getSite(LogEntry logEntry) {
        if (logEntry.site!=null)
            sites.add(logEntry.site);
    }


    int getMaxAttendance(List<LogEntry> logs) {

        List<LogEntry> vizitLogs = logs.stream().filter(logEntry ->
                (logEntry.agent != null && logEntry.agent.isBot == false)).sorted(Comparator.comparing(logEntry -> logEntry.time)).
                collect(Collectors.toList());
        HashMap<Integer, Integer> attendanceStatistic = new HashMap<>();
        LocalDateTime timeNow = vizitLogs.get(0).time;
        int vizitCount = 1;
        int second = 1;
        attendanceStatistic.put(second, vizitCount);
        for (int i = 1; i < vizitLogs.size(); i++) {
            if (vizitLogs.get(i).time.equals(timeNow)) {
                vizitCount++;
                attendanceStatistic.put(second, vizitCount);
            }
            if (vizitLogs.get(i).time.isAfter(timeNow)) {
                vizitCount = 1;
                timeNow = logs.get(i).time;
                second++;
                attendanceStatistic.put(second, vizitCount);
            }
        }
        return attendanceStatistic.values().stream().max(Integer::compare).get();

    }

    int getUnicIpAddr(List<LogEntry> logs) {
        return logs.stream().filter(logEntry ->
                        (logEntry.agent != null && logEntry.agent.isBot == false))
                .map(logEntry -> {
                    String ip = logEntry.ipAddr;
                    return ip;
                })
                .collect(Collectors.toSet()).size();
    }

    int getVizitCount(List<LogEntry> logs) {
        return (int) logs.stream()
                .filter(logEntry ->
                        (logEntry.agent != null && logEntry.agent.isBot == false)).count();
    }

    int getErrorCount(List<LogEntry> logs) {
        return (int) logs.stream()
                .filter(logEntry -> (("" + logEntry.responceCode).startsWith("4") || ("" + logEntry.responceCode).startsWith("5"))).count();
    }

    private void getTotalTraffic(LogEntry logEntry) {
        totalTraffic = (totalTraffic + logEntry.getResponceSize());
    }

    void getMinMaxTime(LogEntry logEntry) {
        if (logEntry.time.compareTo(minTime) < 0) minTime = logEntry.getTime();
        if (logEntry.time.compareTo(maxTime) > 0) maxTime = logEntry.getTime();
    }

    HashSet<String> getPages(LogEntry logEntry) {
        if (logEntry.responceCode == 200) pages.add(logEntry.referer);
        return pages;
    }

    HashSet<String> getPagesNotFound(LogEntry logEntry) {
        if (logEntry.responceCode == 404) pagesNotFound.add(logEntry.referer);
        return pagesNotFound;
    }

    void getBotStatistics(LogEntry logEntry) {
        if (logEntry.agent != null && logEntry.agent.yandexBot == true) ya++;
        if (logEntry.agent != null && logEntry.agent.googleBot == true) go++;
    }

    HashMap<String, Integer> getBrowsersCount(LogEntry logEntry) {
        Browser[] browsers = Browser.values();
        for (int i = 0; i < browsers.length; i++) {
            if (logEntry.agent.typeBrowser == null) return osCount;
            if (logEntry.agent.typeBrowser.equals(browsers[i].name()))
                browsersCount.put(browsers[i].name(), browsersCount.get(browsers[i].name()) + 1);
        }
        return browsersCount;
    }

    HashMap<String, Double> getBrowsersStatistic(HashMap<String, Integer> browsersCount) {
        HashMap<String, Double> browsersStatistic = new HashMap<>();
        int totalCount = 0;
        Browser[] browsers = Browser.values();
        for (int i = 0; i < browsers.length; i++)
            totalCount = totalCount + browsersCount.get(browsers[i].name());

        for (int i = 0; i < browsers.length; i++)
            if (browsersCount.containsKey(browsers[i].name())) {
                browsersStatistic.put(browsers[i].name(), Double.valueOf(browsersCount.get(browsers[i].name())) / totalCount);
            }
        return browsersStatistic;
    }

    HashMap<String, Integer> getOsCount(LogEntry logEntry) {
        TypeOs[] types = TypeOs.values();
        for (int i = 0; i < types.length; i++) {
            if (logEntry.agent.typeOs == null) return osCount;
            if (logEntry.agent.typeOs.equals(types[i].name()))
                osCount.put(types[i].name(), osCount.get(types[i].name()) + 1);
        }
        return osCount;
    }

    HashMap<String, Double> getOsStatistic(HashMap<String, Integer> osCount) {
        HashMap<String, Double> osStatistic = new HashMap<>();
        int totalCount = 0;
        TypeOs[] types = TypeOs.values();
        for (int i = 0; i < types.length; i++)
            totalCount = totalCount + osCount.get(types[i].name());

        for (int i = 0; i < types.length; i++)
            if (osCount.containsKey(types[i].name())) {
                osStatistic.put(types[i].name(), Double.valueOf(osCount.get(types[i].name())) / totalCount);
            }
        return osStatistic;
    }

    int getTrafficRate(LocalDateTime maxTime, LocalDateTime minTime, Long totalTraffic) {
        return Math.toIntExact((long) ((totalTraffic) / 1024 / (double) (getFullTime(maxTime, minTime) / 3600)));
    }

    int getVizitRate(LocalDateTime maxTime, LocalDateTime minTime, List<LogEntry> logs) {
        return (int) (getVizitCount(logs) / (double) (getFullTime(maxTime, minTime) / 3600));
    }

    int getErrorsRate(LocalDateTime maxTime, LocalDateTime minTime, List<LogEntry> logs) {
        return (int) (getErrorCount(logs) / (double) (getFullTime(maxTime, minTime) / 3600));
    }

    int getFullTime(LocalDateTime maxTime, LocalDateTime minTime) {
        int fullTime = (86400 * (maxTime.getDayOfYear() - minTime.getDayOfYear())
                + 3600 * (maxTime.getHour() - minTime.getHour())
                + 60 * (maxTime.getMinute() - minTime.getMinute())
                + (maxTime.getSecond() - minTime.getSecond()));
        return fullTime;
    }

    int getAverageAttendance(List<LogEntry> logs) {
        return getVizitCount(logs) / getUnicIpAddr(logs);
    }
    int getAverageAttendance1(HashMap<String,Integer>users) {
        return getAllVizits(users) / users.size();
    }
    @Override
    public String toString() {
        return "Траффик: " + totalTraffic +
                ", minTime=" + minTime +
                ", maxTime=" + maxTime +
                ", количество запросов от Yandexbot: " + ya +
                ", количество запросов от Googlebot: " + go +
                ", чаcтота встречаемости разных операционных систем: " + osCount +
                ", чаcтота встречаемости разных браузеров: " + browsersCount +
                '}';
    }

    public HashSet<String> getSites() {
        return sites;
    }
}
