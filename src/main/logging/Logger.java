package main.logging;

import java.io.*;

public class Logger {
    public static void log(String file, String message){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.append(message).append("\n");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void log(String file, Exception error){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.append(error.getMessage()).append("\n");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void logToConsole(String file, String message){
        System.out.println(message);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.append(message).append("\n");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
