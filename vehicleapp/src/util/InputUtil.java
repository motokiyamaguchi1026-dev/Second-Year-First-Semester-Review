package util;

import java.util.Scanner;

//入力管理
public class InputUtil {
    private Scanner scanner;
    
    public InputUtil(Scanner scanner) {
        this.scanner = scanner;
    }

    // 空入力を防ぐ
    public String inputRequired(String message) {

        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();
            if (!input.trim().isEmpty()) {
                return input;
            }
            System.out.println("入力してください。");
        }
    }

    //マイナスの記入を防ぐ
    public int inputNumber(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();
            try {
                int number = Integer.parseInt(input);
                if (number < 0) {
                    System.out.println("0以上の数字を入力してください。");
                    continue;
                }
                return number;
            } catch (NumberFormatException e) {
                System.out.println("数字を入力してください。");
            }
        }
    }
}