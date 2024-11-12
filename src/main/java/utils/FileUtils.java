package utils;


import contants.Operation;

import java.io.*;

public class FileUtils {

    public static String fileReader(File file) {
        StringBuilder result = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()))) {
            while (reader.ready()) {
                result.append(reader.readLine()+"\n");
            }
        } catch (IOException exp) {
            exp.getMessage();
        }
        return result.toString();

    }


    public static void fileWriter(File file, String result, Operation operation) {
        String filename = getNewFileName(file, operation);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename))) {
            bufferedWriter.write(result);
            bufferedWriter.flush();
        } catch (IOException ex) {
            ex.getMessage();
        }

    }

    private static String getNewFileName(File file, Operation operation) {
        String newFileName;
        String[] fileArray = file.getPath().split("\\.");
        if (fileArray.length > 1) {

            int pathLength = fileArray.length;
            String filePrefix = fileArray[pathLength - 2];
            String fileExtension = fileArray[pathLength - 1];
            newFileName = filePrefix + "[" + operation + "]." + fileExtension;
        } else {
            newFileName = file.getName() + "[" + operation + "]";
        }

        return newFileName;
    }
}
