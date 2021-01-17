package com.volkangurbuz.blogapp;

import java.io.File;
import java.util.Locale;
import java.util.Scanner;

public class Test {
  public static void main(String[] args) {
    String pathToFile = "test.txt";
    long maxDifference = Integer.MIN_VALUE;
    long difference;
    long population;
    int k = 0;
    File file = new File(pathToFile);

    // try (Scanner scanner = new Scanner(file).useLocale(Locale.ENGLISH))
    try (Scanner scanner = new Scanner(file)) {
      while (scanner.hasNext()) {
        // Skip first line (description of columns)
        scanner.nextLine();
        String[] values = scanner.nextLine().split("\\t");

        long[] numbers = new long[Math.round(values.length - 1 / 2)]; // last line empty
        // if unset Locale.English long[] numbers = Long.parseLong(scanner.next().replace(",",""));

        for (int j = 0; j < values.length; j += 2) {
          for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Long.parseLong(values[j].replace(",", ""));
          }
        }

        for (int i = 0; i < numbers.length - 1; i++) {
          difference = numbers[i + 1] - numbers[i];
          if (difference > maxDifference) {
            maxDifference = difference;
            k = i;
          }
        }
      }

      // System.out.println(k + 1950);
      System.out.println(k + 1950);

    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
