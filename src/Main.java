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
                    int ya;
                    int go;
                    int sumLength = 0;
                    while ((line = reader.readLine()) != null) {
                        int length = line.length();
                        if (length > 0 && length <= 1024){
                            sumLength = sumLength+1;

                         /* if(line.contains("(")&&line.contains(")")&&(line.indexOf('(')+1)<(line.indexOf(')'))) {
                            String firstBrackets = line.substring((line.indexOf('(')+1),(line.indexOf(')')));
                            String[] parts = firstBrackets.split(";");
                            for (String part:parts){
                                part.trim();
                            }
                            if (parts.length >= 2) {
                                String fragment = parts[1];
                                System.out.println(fragment);
                            }}*/

                        }else throw new IllegalCountExeption("Невалидная длина строки");

                    }
                    System.out.println("Работа с файлом закончена");
                    System.out.println("Общее количество строк в файле " + sumLength);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }


        }
    }
}