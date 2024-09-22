
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class LogEntry {
    final String ipAddr;
    final LocalDateTime time;
    final HttpMethod method;
    final String path;
    final int responceCode;
    final int responceSize;
    final String referer;
    final UserAgent agent;
    String site;

    public LogEntry(String line) {
        String[] fragments = line.split(" ");
        this.time = LocalDateTime.parse((fragments[3].substring(1, fragments[3].length()) + " " + fragments[4].substring(0, 5)),
                DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z").withLocale(Locale.ENGLISH));
        this.ipAddr = fragments[0];
        this.method = HttpMethod.valueOf(fragments[5].substring(1, (fragments[5].length())));
        this.path = fragments[6].substring(0, (fragments[6].length() - 1));
        this.responceCode = Integer.parseInt(fragments[8]);
        this.responceSize = Integer.parseInt(fragments[9]);
        this.referer = fragments[10].substring(1, (fragments[10].length() - 1));
       this.setSite();;
        this.agent = new UserAgent(line);
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }


    public UserAgent getAgent() {
        return agent;
    }

    public String getReferer() {
        return referer;
    }

    public int getResponceSize() {
        return responceSize;
    }

    public int getResponceCode() {
        return responceCode;
    }

    public LocalDateTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "LogEntry{" +
                "ipAddr='" + ipAddr + '\'' +
                ", time=" + time +
                ", method=" + method +
                ", path='" + path + '\'' +
                ", responceCode=" + responceCode +
                ", responceSize=" + responceSize +
                ", referer='" + referer + '\'' +
                ", agent=" + agent +
                '}';
    }

    public void setSite() {

        String temp = this.referer;
        if (temp.contains("http")) {String[]fr = temp.split("/");
            if(fr.length>=2)
            this.site = fr[0]+"//"+fr[2];}

    }
}

enum HttpMethod {OPTIONS, GET, HEAD, POST, PUT, PATCH, DELETE, TRACE, CONNECT}