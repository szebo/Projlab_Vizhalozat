package commands;

import main.Controller;
import main.Main;


import main.interfaces.Updatable;
import main.logging.Logger;
import main.map.*;
import main.players.*;
import tests.ProtoTests.Tester;

import java.io.*;
import java.util.List;
import java.util.Scanner;


public class CommandInterpreter {

    public static void runCommand(String cmd, Player exec){
        String[] splits = cmd.split(" ");
        switch (splits[0]) {
            case "move":
                move(exec);
                break;

            case "place":
                if (splits[1].equals("pipe")) {
                    exec.placePipe();
                } else if (splits[1].equals("pump")) {
                    exec.placePump();
                }
                break;

            case "pickup":
                pickUp(splits[1], exec);
                break;

            case "break":
                exec.breakElement();
                break;

            case "repair":
                exec.repair();
                break;

            case "configure":
                configure(exec);
                break;

            case "make_slippery":
                exec.useSlipperyGoo();
                break;

            case "make_sticky":
                exec.useStickyGoo();
                break;

            case "end_turn":
                Logger.logToConsole("log.txt", exec.getLogID()+": turn ended");
                break;

            case "debug_break":
                Map.getInstance().getElement(splits[1]).setBroken(true);
                Logger.logToConsole("log.txt", Map.getInstance().getElement(splits[1]).getLogID()+": broken by tester");
                break;

            case "debug_repair":
                Map.getInstance().getElement(splits[1]).setBroken(false);
                Logger.logToConsole("log.txt", Map.getInstance().getElement(splits[1]).getLogID()+": repaired by tester");
                break;

            case "flow":
                Map.getInstance().waterFlow(Integer.parseInt(splits[1]));
                break;

            case "exit":
                Logger.logToConsole("log.txt", "[Game]: exiting...");
                System.exit(1);
                break;

            case "start":
                if(MechanicTeam.getInstance().players.size()>1 && SaboteurTeam.getInstance().players.size()>1) {
                    Logger.logToConsole("log.txt", "[Game]: starting...");
                    Controller.run();
                }
                else
                    Logger.logToConsole("log.txt", "[Game]: can’t start due to not having enough players");
                break;

            case "load_map":
                Map.getInstance().loadMap(splits[1]);
                break;

            case "create_saboteur":
                Saboteur newSaboteur = new Saboteur();
                SaboteurTeam.getInstance().addPlayer(newSaboteur);
                Logger.logToConsole("log.txt", newSaboteur.getLogID()+": created");
                break;

            case "create_mechanic":
                Mechanic newMechanic = new Mechanic();
                MechanicTeam.getInstance().addPlayer(newMechanic);
                Logger.logToConsole("log.txt", newMechanic.getLogID()+": created");
                break;

            case "setplayerposition":
                exec.setMapElement(Map.getInstance().getElement(splits[2]));
                Map.getInstance().getElement(splits[2]).addPlayer(exec);
                Logger.logToConsole("log.txt", exec.getLogID()+": position set to "+Map.getInstance().getElement(splits[2]).getLogID());
                break;

            case "create_pipe":
                Map.getInstance().getElement(splits[1]).newPipe();
                break;

            case "debug_info":
                Logger.logToConsole("console.txt", Map.getInstance().getElement(splits[1]).printInfo());
                break;

            case "create":
                Map.getInstance().create(splits[1]);
                break;

            case "attach":
                Map.getInstance().getElement(splits[2]).attachPipe(Map.getInstance().getPipe(splits[1]));
                break;

            case "save_map":
                Map.getInstance().saveMap(Integer.parseInt(splits[1]));
                break;

            case "force_start":
                Logger.logToConsole("log.txt", "[Game]: forcefully started");
                Controller.run();
                break;

            case "debug_slippery":
                Map.getInstance().getElement(splits[1]).makeSlippery(3);
                Logger.logToConsole("log.txt", Map.getInstance().getElement(splits[1]).getLogID()+": now slippery");
                break;

            case "debug_sticky":
                Map.getInstance().getElement(splits[1]).makeSticky(3);
                Logger.logToConsole("log.txt", Map.getInstance().getElement(splits[1]).getLogID()+": now sticky");
                break;

            case "runtest":
                runTest(splits[1]);
                break;

            default:
                break;
        }
    }

    private static void move(Player exec){
        MapElement element = exec.getMapElement(); //holymoly...
        for(MapElement neighbour : element.getNeighbours()){
            System.out.println(neighbour.getLogID());
        }

        boolean validInput = false;
        String target = System.console().readLine();

        for(MapElement neighbour : element.getNeighbours()){
            if(target.equals(neighbour)) validInput = true;
        }

        if(validInput)
            exec.step(Map.getInstance().getElement(target));
        else
            Logger.logToConsole("log.txt", "Nem létező szomszéd lett megadva.");
    }

    private static void configure(Player exec){
        MapElement playerMapElement = exec.getMapElement();
        for(MapElement neighbour : playerMapElement.getNeighbours()){
            System.out.println(neighbour.getLogID());
        }

        boolean validInput = false;
        boolean validOutput = false;
        String input = System.console().readLine();
        String output = System.console().readLine();

        for(MapElement neighbour : playerMapElement.getNeighbours()){
            if(!input.equals(output)){
                if(input.equals(neighbour)) validInput = true;

                else if(output.equals(neighbour)) validOutput = true;
            }
            else Logger.logToConsole("log.txt", "Az input és az output ugyan az lett!");
        }

        if(validOutput && validInput) exec.configurePump(Map.getInstance().getPipe(input), Map.getInstance().getPipe(output));
        else if(!validOutput) Logger.logToConsole("log.txt", "Az outputnak nem létező cső lett megadva.");
        else if(!validInput) Logger.logToConsole("log.txt", "Az inputnak nem létező cső lett megadva.");
        else Logger.logToConsole("log.txt", "Az inputnak és outputnak is nem létező cső lett megadva.");
    }

    private static void pickUp(String cmd, Player exec){
        if (cmd.equals("pipe")) {
            MapElement playerMapElement = exec.getMapElement();
            for(MapElement neighbour : playerMapElement.getNeighbours()){
                System.out.println(neighbour.getLogID());
            }
            String target = System.console().readLine();
            boolean valid = false;
            for(MapElement neighbour : playerMapElement.getNeighbours()){
                if(neighbour.getLogID().equals(target)) valid = true;
            }

            if(valid)
                exec.pickUpPipe(Map.getInstance().getPipe(target));
            else
                Logger.logToConsole("log.txt", "Invalid target!");
        } else if (cmd.equals("pump")) {
            exec.pickUpPump();
        }
    }

    private static void runTest(String cmd){
        if(cmd.contains("-")){
            int low = Integer.parseInt(cmd.split("-")[0]);
            int high = Integer.parseInt(cmd.split("-")[1]);
            for(int i = low; i <= high; i++){
                Tester.runTest(i);
            }
        }
        else if(cmd.contains(",")){
            String[] params = cmd.split(",");
            for(String p : params){
                Tester.runTest(Integer.parseInt(p));
            }
        }
        else
            Tester.runTest(Integer.parseInt(cmd));
    }
}
