package com.gaurang.session5;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

public class Main {

    static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static void main(String[] args) {

        // Predicate for checking if number is zero
        Predicate<Integer> isZero = i -> i == 0;

        // Using isZero predicate for creating the negation predicate
        Predicate<Integer> notIsZero = Predicate.not(isZero);

        try {
            FileReader fileReader = new FileReader("resources/File.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            List<Integer> integerList = new ArrayList<>();

            while((line = bufferedReader.readLine()) != null) {
                if (!line.isEmpty()) {
                    Integer i = Integer.parseInt(line.stripLeading());
                    integerList.add(i);
                }
            }

            List<Integer> returnList = integerList.stream().filter(notIsZero).map(i -> i + 5).toList();
            returnList.forEach(i -> {
                stringBuffer.append(i.toString());
                stringBuffer.append("\n");
            });

            BufferedWriter bufferedWriter =
                    new BufferedWriter(new FileWriter(new File("resources/File2.txt")));
            bufferedWriter.write(stringBuffer.toString());
            bufferedWriter.flush();
            bufferedWriter.close();
            bufferedReader.close();
            fileReader.close();
        } catch (Exception e) {
            logger.info("Some error occurred: " + e.getMessage());
        }
    }

}
