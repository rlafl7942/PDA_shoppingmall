package exercise.generics;

public class AutoUnboxing {
    public static void main(String[] args) {
        Int num1 = new Int(7);
        System.out.println(num1.i);

        int num2 = 8;
        System.out.println(num2);

        // Deprecated
        Integer num3 = 9;
        System.out.println(num3);

    }
}

class Int {
    int i;

    Int(int i) {
        this.i = i;
    }
}
