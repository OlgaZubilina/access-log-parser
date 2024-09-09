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
            FileReader fileReader = new FileReader(path);
            BufferedReader reader =
                    new BufferedReader(fileReader);
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
            System.out.println("Работа с файлом закончена");
            System.out.println("Общее количество строк в файле " + sumLength);
            System.out.println(st);
            System.out.println("Использованный трафик (килобайт/час): " + st.getTrafficRate(st.maxTime, st.minTime, st.totalTraffic));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


