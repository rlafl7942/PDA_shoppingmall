package exercise.exception;

public class ArithmeticThrows {
    public static void main(String[] args) {
        try {
            int result = divide(10, 0);
        } catch (ArithmeticException e) {
            System.out.println("오류다!!!!!!!!!!!!!!!!!");
        }
    }

    public static int divide(int x, int y)
            throws ArithmeticException {
        int result = 0;
        result = x / y;
        return result;
    }
}
