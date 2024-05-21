package exercise.exception;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MultiCatchEx {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] cards = {4, 5, 6, 1, 2, 3};

        try {
            System.out.println("뽑고 싶은 카드 순서를 입력해주세요.");
            int cardIdx = scanner.nextInt();

            System.out.println("뽑은 카드 번호 : " + cardIdx);
            System.out.println("뽑은 카드에 적힌 숫자 : " + cards[cardIdx]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("없는 번호입니다.");
        } catch (InputMismatchException e) {
            System.out.println("숫자만 입력해주세요.");
        }
    }
}
