package commands;

import main.Controller;
import main.Main;


import main.interfaces.Updatable;
import main.logging.Logger;
import main.map.*;
import main.players.Mechanic;
import main.players.MechanicTeam;
import main.players.Saboteur;
import main.players.SaboteurTeam;
import tests.ProtoTests.Tester;

import java.io.*;
import java.util.List;
import java.util.Scanner;


public class CommandInterpreter {

    public static void runCommand(String cmd){
        String[] splits = cmd.split(" ");
        switch (splits[0]) {
            case "move":
                move();
                break;

            case "place":
                if (splits[1].equals("pipe")) {
                    Main.currentPlayer.placePipe();
                } else if (splits[1].equals("pump")) {
                    Main.currentPlayer.placePump();
                }
                break;

            case "pickup":
                if (splits[1].equals("pipe")) {
                    Main.currentPlayer.pickUpPipe((Pipe)Main.map.getElement(splits[2]));
                } else if (splits[1].equals("pump")) {
                    Main.currentPlayer.pickUpPump();
                }
                break;

            case "break":
                Main.currentPlayer.breakElement();
                break;

            case "repair":
                Main.currentPlayer.repair();
                break;

            case "configure":
                configure();
                break;

            case "make_slippery":
                Main.currentPlayer.useSlipperyGoo();
                break;

            case "make_sticky":
                Main.currentPlayer.useStickyGoo();
                break;

            case "end_turn":
                Logger.logToConsole("log.txt", Main.currentPlayer.getLogID()+": turn ended");
                break;

            case "debug_break":
                Main.map.getElement(splits[1]).breakElement();
                break;

            case "debug_repair":
                Main.map.getElement(splits[1]).heal();
                break;

            case "flow":
                Main.map.waterFlow(Integer.parseInt(splits[1]));
                break;

            case "exit":
                System.exit(1);
                break;

            case "start":
                if(MechanicTeam.getInstance().players.size()>1 && SaboteurTeam.getInstance().players.size()>1)
                    Controller.run();
                else
                    Logger.logToConsole("log.txt", "[Game]:Not enough players!");
                break;

            case "load_map":
                Map.getInstance().loadMap(splits[1]);
                break;

            case "create_saboteur":
                SaboteurTeam.getInstance().addPlayer(new Saboteur());
                break;

            case "create_mechanic":
                MechanicTeam.getInstance().addPlayer(new Mechanic());
                break;

            case "setplayerposition":
                //TODO player = getPlayer(splits[1]);
                //TODO player.setElement(Main.map.getElement(splits[2]));
                //TODO Main.map.getElement(splits[2]).addPlayer(player);
                break;

            case "create_pipe":
                Main.map.getElement(splits[1]).newPipe();
                break;

            case "debug_info":
                Logger.logToConsole("console.txt", Main.map.getElement(splits[1]).printInfo());
                break;

            case "create":
                Map.getInstance().create(splits[1]);
                break;

            case "attach":
                Main.map.getElement(splits[2]).attachPipe((Pipe)Main.map.getElement(splits[1]));
                break;

            case "save_map":
                Map.getInstance().saveMap(splits[1]);
                break;

            case "force_start":
                Controller.run();
                break;

            case "debug_slippery":
                Main.map.getElement(splits[1]).makeSlippery(3);
                break;

            case "debug_sticky":
                Main.map.getElement(splits[1]).makeSticky(3);
                break;

            case "runtest":
                //TODO parameres feldolgozas
                Tester.runTest(splits[1]);
                break;

            default:
                break;
        }
    }

    private static void move(){
        MapElement element = Main.currentPlayer.getMapElement(); //holymoly...
        for(MapElement neighbour : element.getNeighbours()){
            System.out.println(neighbour.getLogID());
        }
        Scanner scanner = new Scanner(System.in);
        String target = scanner.nextLine();
        scanner.close();
        Main.currentPlayer.step(Main.map.getElement(target));
    }

    private static void configure(){
        MapElement playerMapElement = Main.currentPlayer.getMapElement();
        for(MapElement neighbour : playerMapElement.getNeighbours()){
            System.out.println(neighbour.getLogID());
        }
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String output = scanner.nextLine();
        scanner.close();
        Main.currentPlayer.configurePump((Pipe)Main.map.getElement(input), (Pipe)Main.map.getElement(output));
    }
}
