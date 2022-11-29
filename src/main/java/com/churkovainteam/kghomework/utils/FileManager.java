package com.churkovainteam.kghomework.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileManager {
    public static List<String> readAllLinesFromFile(String path) throws IOException {
        if (path == null) {
            return null;
        } else {
            return isFileExist(path) ? Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8) : null;
        }
    }

    public static String readAllTextFromFile(String path) throws IOException {
        if (path == null) {
            return null;
        } else {
            return isFileExist(path) ? Files.readString(Paths.get(path), StandardCharsets.UTF_8) : null;
        }
    }

    public static void createFileWithText(String filePath, String text) throws IOException {
        if (!isFileExist(filePath)) {
            File file = new File(filePath);
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            fw.write(text);
            fw.close();
        }
    }

    public static boolean isFileExist(String filePath) {
        return filePath != null && (new File(filePath)).exists();
    }
}
