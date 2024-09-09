public class UserAgent {
    String userAgentInfo;
    TypeOs typeOs;
    String typeBrowser;
    boolean yandexBot;
    boolean googleBot;

    public UserAgent(String line) {
        String[] fragments = line.split(" ");
        if (!(fragments[11].equals("\"-\"")))
            if (!(line.contains("Feedly/"))) {
                this.userAgentInfo = fragments[11];
                for (int i = 11; i < fragments.length - 1; i++)
                    this.userAgentInfo = this.userAgentInfo + fragments[i + 1];
            } else this.userAgentInfo = null;
        if (this.userAgentInfo != null) {
            if (this.userAgentInfo.contains("YandexBot")) this.yandexBot = true;
            if (this.userAgentInfo.contains("Googlebot")) this.googleBot = true;
            if (this.userAgentInfo.contains("Mac")) {
                this.typeOs = TypeOs.MACINTOSH;
            }
            if (this.userAgentInfo.contains("Win")) {
                this.typeOs = TypeOs.WINDOWS;
            }
            if (this.userAgentInfo.contains("Linux")) {
                this.typeOs = TypeOs.LINUX;
            }
        }
    }

    @Override
    public String toString() {
        return "UserAgent{" +
                "userAgentInfo='" + userAgentInfo + '\'' +
                ", typeOs=" + typeOs +
                ", typeBrowser='" + typeBrowser + '\'' +
                ", yandexBot=" + yandexBot +
                ", googleBot=" + googleBot +
                '}';
    }

}
enum TypeOs {
    MACINTOSH, WINDOWS, LINUX
}