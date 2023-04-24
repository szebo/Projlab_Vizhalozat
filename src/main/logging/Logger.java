package main.logging;

import java.io.*;

public class Logger {
    public static void log(String file, String message){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(file)));
            writer.append(message+"\n");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void log(String file, Exception error){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(file)));
            writer.append(error.getMessage()+"\n");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void logToConsole(String file, String message){
        System.out.println(message);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(file)));
            writer.append(message+"\n");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
