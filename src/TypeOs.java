public enum TypeOs {
    MACINTOSH {
        String name = "Macintosh";
        public String getOs(String str) {
            if (str.contains("Mac")) return name;
            else return null;
        }
    }, WINDOWS {String name = "Windows";
        public String getOs(String str) {
            if (str.contains("Win")) return name;
            else return null;
        }
    }, LINUX {String name = "Linux";
        public String getOs(String str) {
            if (str.contains("Linux")) return name;
            else return null;
        }
    };
    public String getOs(String str) {
        return null;
    }
}
