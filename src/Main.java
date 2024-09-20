import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int count = 0;
        while (true) {
            System.out.println("Введите путь к файлу");
            String path = new Scanner(System.in).nextLine();
            File file = new File(path);

            if (file.isDirectory()) {
                System.out.println("Указанный путь является путём к папке, а не к файлу");
                continue;
            }
            if (!file.exists()) {
                System.out.println("Указанный файл не существует");
                continue;
            } else {
                count++;
                System.out.println("Путь указан верно. Это файл номер " + count);
            }
            getStatisticLogs(path);
        }

    }

    private static void getStatisticLogs(String path) {
        try {
            BufferedReader reader = readFile(path);
            LogParser result = getParseLog(reader);
            printOutput(result);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printOutput(LogParser result) {
        System.out.println();
        System.out.println("Работа с файлом закончена");
        System.out.println("Общее количество строк в файле " + result.sumLength);
        System.out.println(result.st);
        System.out.println("Статистика по операционным системам: " + result.st.getOsStatistic(result.st.osCount));
        System.out.println("Статистика по браузерам: " + result.st.getBrowsersStatistic(result.st.browsersCount));
        System.out.println("Использованный трафик (килобайт/час): " + result.st.getTrafficRate(result.st.maxTime, result.st.minTime, result.st.totalTraffic));
    }

    private static LogParser getParseLog(BufferedReader reader) throws IOException {
        String line;
        Statistics st = new Statistics();
        int sumLength = 0;
        while ((line = reader.readLine()) != null) {
            int length = line.length();
            if (length > 0 && length <= 1024) {
                sumLength = sumLength + 1;
                LogEntry log = new LogEntry(line);
                st.addEntry(log);
            } else throw new IllegalCountExeption("Невалидная длина строки");
        }
        LogParser result = new LogParser(st, sumLength);
        return result;
    }

    private static class LogParser {
        public final Statistics st;
        public final int sumLength;

        public LogParser(Statistics st, int sumLength) {
            this.st = st;
            this.sumLength = sumLength;
        }
    }

    private static BufferedReader readFile(String path) throws FileNotFoundException {
        FileReader fileReader = new FileReader(path);
        BufferedReader reader =
                new BufferedReader(fileReader);
        return reader;
    }
}


