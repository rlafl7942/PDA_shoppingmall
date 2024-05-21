package exercise.exception;

import java.util.Scanner;

public class AgeThrowCheckerEx {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("나이(0~100)를 입력하세요.");
                int age = scanner.nextInt();

                if (age == -1)
                    break;

                if (age < 0 || age > 100) {
                    throw new InputBoundErrorException("0~100 사이로 입력해주세요.");
                }

                System.out.println("당신.. " + age + "살이시군요..");
            } catch (InputBoundErrorException e) {
                System.out.println(e.getMessage());
            } finally {
                scanner.close();
            }
        }

    }
}
