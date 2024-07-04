package org.example.task1;

public class Main {

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);

        StringBuilder result = new StringBuilder("1");
        for (int i = 1; i < n; i++) {
            int index = (m - 1) * i;
            int element = (index + 1) % n;
            element = (element == 0) ? n : element;

            if (element == 1) {
                break;
            }

            result.append(element);
        }

        System.out.print("Полученный путь:" + result);
    }
}
