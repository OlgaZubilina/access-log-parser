import java.io.*;
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

                try {
                    FileReader fileReader = new FileReader(path);
                    BufferedReader reader =
                            new BufferedReader(fileReader);
                    String line;
                    int max = 0;
                    int min = 1024;
                    int sumLength = 0;
                    while ((line = reader.readLine()) != null) {
                        int length = line.length();
                        if (length > 0 && length <= 1024){
                            sumLength = sumLength+1;
                            if (length>max)max = length;
                            if (length<min)min = length;
                        }else throw new IllegalCountExeption("Невалидная длина строки");
                    }
                    System.out.println("Работа с файлом закончена");
                    System.out.println("Общее количество строк в файле " + sumLength);
                    System.out.println("Длина самой короткой строки "+ min);
                    System.out.println("Длина самой длинной строки "+ max);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }


        }
    }
}