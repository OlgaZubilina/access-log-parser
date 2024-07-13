import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
System.out.println("Введите число:");
int x = new Scanner(System.in).nextInt();
        System.out.println("Введите второе число:");
int y = new Scanner(System.in).nextInt();
        System.out.println("Сумма чисел равна: "+(x+y));
        System.out.println("Разность чисел равна: "+(x-y));
        System.out.println("Произведение чисел равно: "+(x*y));
        System.out.println("Частное чисел равно: "+(double) x/y);
    }
}