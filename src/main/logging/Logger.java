package main.logging;

import main.Main;
import tests.ProtoTests.Tester;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public static void log(String file, String message, boolean console) {
        if (console) {
            System.out.println(message);
        }

        if (Tester.currentTestLog != null && console) {
            Tester.currentTestLog.add(message);
        } else {
            try {
                LocalDateTime now = LocalDateTime.now();
                File logFile = new File(Main.rootfolder + File.separator + "files" + File.separator + "logs" + File.separator + file);
                if (!logFile.exists()) {
                    if(!logFile.getParentFile().mkdirs())throw new RuntimeException("Error while creating directories to logs.");
                    if(logFile.createNewFile()) System.out.println("A log file was missing and created!");
                }
                BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true));
                writer.append(dtf.format(now)).append("\t");
                writer.append(message).append("\n");
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void log(String file, Exception error){
        try {
            LocalDateTime now = LocalDateTime.now();
            File logFile = new File(Main.rootfolder+"/files/logs/"+file);
            if(!logFile.exists()){
                logFile.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true));
            writer.append(dtf.format(now)+"\t");
            writer.append(error.getMessage()).append("\n");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
