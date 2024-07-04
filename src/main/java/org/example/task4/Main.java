package org.example.task4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String path = args[0]; // src/main/java/org/example/task4/numbers.txt
        List<Integer> elements = getElements(path);
        if (!elements.isEmpty()) {
            Collections.sort(elements);
            int median = elements.get(elements.size() / 2);
            int moves = 0;

            for (int num : elements) {
                moves += Math.abs(num - median);
            }

            System.out.println("Минимальное количество ходов: " + moves);
        } else {
            System.out.println("Произошла ошибка или файл пустой");
        }
    }

    private static List<Integer> getElements(String file) {
        List<Integer> elements = new ArrayList<>();
        try (BufferedReader elementsFile = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = elementsFile.readLine()) != null) {
                elements.add(Integer.parseInt(line));
            }

            return elements;
        } catch (IOException ex) {
            return new ArrayList<>();
        }
    }
}
