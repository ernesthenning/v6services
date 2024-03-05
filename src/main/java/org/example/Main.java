package org.example;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        sortText();

    }
    public static void sortText() {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        ArrayList<String> lines = new ArrayList<String>();
        try {
            reader = new BufferedReader(new FileReader("C:\\input.txt"));
            String currentLine = reader.readLine();
            while(currentLine != null) {
                lines.add(currentLine);
                currentLine = reader.readLine();
            }
            List<String> countedLines = countLines(lines);
            System.out.println("Для сортировки по первому методу введите 1, для сортировки по 2 методу введите 2, для сортировки по 3 методу введите 3");
            BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
            List<String> newLines = new ArrayList<>();
            String method = r.readLine();
            if (method.equals("1")) {
                sortArrayAlphabet(countedLines);
                newLines = countedLines;
            }
            if (method.equals("2")) {
                newLines = sortArrayLength(countedLines);
            }
            if (method.equals("3")) {
                System.out.println("Введите индекс слова для сортировки");
                BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
                String word = read.readLine();
                int index = Integer.parseInt(word);
                newLines = sortAlphabetByWord(countedLines, index);
            }
            writer = new BufferedWriter(new FileWriter("C:\\output.txt"));
            for (String line : newLines) {
                writer.write(line);
                writer.newLine();
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void sortArrayAlphabet(List<String> array) {
        Collections.sort(array);
    }

    private static List<String> sortArrayLength(List<String> array) {
        return array.stream().sorted(Comparator.comparing(String::length)).collect(Collectors.toList());
    }

    private static List<String> sortAlphabetByWord(List<String> array, int wordIndex) {
        Map<String, String> sortingMap = new TreeMap<>();
        for (String s: array) {
            sortingMap.put(s.split(" ")[wordIndex], s);
        }
        return new ArrayList<>(sortingMap.values());
    }

    private static List<String> countLines(List<String> list) {
        Map<String, Integer> countMap = new HashMap<>();
        for (String s : list) {
            if (countMap.containsKey(s)) {
                countMap.put(s, countMap.get(s) + 1);
            } else {
                countMap.put(s, 1);
            }
        }
        List<String> newList = new ArrayList<>();
        countMap.forEach((k,v) -> {
            newList.add(k + " " + v);
        });
        return newList;
    }
}