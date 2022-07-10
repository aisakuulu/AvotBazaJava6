package com.peaksoft.createJsonFiler;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class JsonFileService {

    public static void writeFile(String obj, Path path) {

        try {
            Path write = Paths.get(String.valueOf(path));
            Files.writeString(write, obj, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFile(Path path) {
        String json = "";
        try {
            FileReader fileReader =  new FileReader(String.valueOf(path));
            int a;
            while((a=fileReader.read())!=-1) {
                json+=(char)a;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
}
