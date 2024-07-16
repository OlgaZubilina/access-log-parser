import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int count = 0;
        while (true) {
            System.out.println("Введите путь к файлу");

            String path = new Scanner(System.in).nextLine();
            File file = new File(path);
            Boolean fileExists = file.exists();
            Boolean isDirectory = file.isDirectory();
            if (isDirectory) {
                System.out.println("Указанный путь является путём к папке, а не к файлу");
                continue;
            }

            if (!fileExists) {
                System.out.println("Указанный файл не существует");
                continue;
            } else {
                count++;
                System.out.println("Путь указан верно. Это файл номер " + count);
            }
        }


    }
}