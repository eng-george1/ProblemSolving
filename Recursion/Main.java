package Recursion;

public class Main {
    public static void main(String[] args) {
        System.out.println(fibonacci(800));
        System.out.println(fibonacci2(800));
    }

    public static int fibonacci(int n) {
        if (n == 0)
            return 0;
        if (n == 1 || n == 2)
            return 1;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static int fibonacci2(int n) {
        if (n == 0)
            return 0;
        if (n == 1 || n == 2)
            return 1;
        int firstbefore = 1, secondBefore = 1;
        for (int i = 3; i <= n; i++) {
            int temp = firstbefore;
            firstbefore = firstbefore + secondBefore;
            secondBefore = temp;
        }
        return firstbefore;
    }
}
