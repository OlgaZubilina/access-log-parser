public class UserAgent {
    String userAgentInfo;
    String typeOs;
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
            this.setTypeOs(this.userAgentInfo);
            this.setTypeBrowser(this.userAgentInfo);
        }
    }

    public String getTypeBrowser() {
        return typeBrowser;
    }

    public String getTypeOs() {
        return typeOs;
    }

    public boolean isYandexBot() {
        return yandexBot;
    }

    public String getUserAgentInfo() {
        return userAgentInfo;
    }

    public boolean isGoogleBot() {
        return googleBot;
    }

    private void setTypeOs(String userAgentInfo) {
        TypeOs[] osTypes = TypeOs.values();
        for (int i = 0; i < osTypes.length; i++)
            if (osTypes[i].getOs(userAgentInfo) == null) continue;
            else {
                this.typeOs = osTypes[i].name();
                break;
            }
    }

    private void setTypeBrowser(String userAgentInfo) {
        Browser[] browsers = Browser.values();
        for (int i = 0; i < browsers.length; i++) {
            if (browsers[i].getBrowser(userAgentInfo) == null) continue;
            else {
                this.typeBrowser = browsers[i].name();
                break;
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


