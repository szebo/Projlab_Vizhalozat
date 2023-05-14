package main.logging;

import main.Main;

import java.io.*;

public class Logger {
    public static void log(String file, String message){
        try {
            File logFile = new File(Main.rootfolder+"/files/logs/"+file);
            if(!logFile.exists()){
                logFile.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(logFile));
            writer.append(message).append("\n");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void log(String file, Exception error){
        try {
            File logFile = new File(Main.rootfolder+"/files/logs/"+file);
            if(!logFile.exists()){
                logFile.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(logFile));
            writer.append(error.getMessage()).append("\n");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void logToConsole(String file, String message){
        System.out.println(message);
        try {
            File logFile = new File(Main.rootfolder+"/files/logs/"+file);
            if(!logFile.exists()){
                logFile.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(logFile));
            writer.append(message).append("\n");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
