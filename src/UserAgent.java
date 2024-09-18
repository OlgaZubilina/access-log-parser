import java.util.ArrayList;

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
            else if (this.userAgentInfo.contains("Win")) {
                this.typeOs = TypeOs.WINDOWS;
            }
            else if (this.userAgentInfo.contains("Linux")) {
                this.typeOs = TypeOs.LINUX;
            }

            this.setTypeBrowser(this.userAgentInfo);
        }
    }

    public String getTypeBrowser() {
        return typeBrowser;
    }

    public TypeOs getTypeOs() {
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

    private void setTypeBrowser(String userAgentInfo) {
        Browser[] browsers = Browser.values();
        for (int i=0;i<browsers.length;i++) {
            if (browsers[i].getBrowser(userAgentInfo)==null) continue;
            else {this.typeBrowser =browsers[i].name();
                break;}

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
enum Browser{



    FIREFOX {
        String name = "Firefox";
        @Override
        public String getBrowser(String str){if (str.contains("Firefox/"))
            return name;
            return null;
        }
    }
    ,OPERA{
        String name = "Opera";
        @Override
        public String getBrowser(String str){if (str.contains("OPR/")||str.contains("Opera/"))
            return name;
            return null;
        }
    },
    EDGE {
        String name = "Edge";
        @Override
        public String getBrowser(String str){if (str.contains("Edg/"))
            return name;
            return null;
        }
    }
    ,CHROME {
        String name = "Chrome";
        @Override
        public String getBrowser(String str) {
            if (str.contains("Chrome/"))
                return name;
            return null;
        }
    }
    ,SAFARI {
        String name = "Safari";

        @Override
        public String getBrowser(String str) {
            if (str.contains("Safari/") && str.contains("Mac"))
                return name;
            return null;
        }};

    public String getBrowser(String str){return null;}

}
