public enum Browser {


    FIREFOX {
        String name = "Firefox";

        @Override
        public String getBrowser(String str) {
            if (str.contains("Firefox/"))
                return name;
            else return null;
        }
    }, OPERA {
        String name = "Opera";

        @Override
        public String getBrowser(String str) {
            if (str.contains("OPR/") || str.contains("Opera/"))
                return name;
            else return null;
        }
    },
    EDGE {
        String name = "Edge";

        @Override
        public String getBrowser(String str) {
            if (str.contains("Edg/"))
                return name;
            else return null;
        }
    }, CHROME {
        String name = "Chrome";

        @Override
        public String getBrowser(String str) {
            if (str.contains("Chrome/"))
                return name;
            else return null;
        }
    }, SAFARI {
        String name = "Safari";

        @Override
        public String getBrowser(String str) {
            if (str.contains("Safari/") && str.contains("Mac"))
                return name;
            else return null;
        }
    };

    public String getBrowser(String str) {
        return null;
    }

}


