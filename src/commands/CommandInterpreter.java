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

    public void runCommand(String cmd){
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
                    Main.currentPlayer.pickUpPipe(Main.map.getElement(splits[2]));
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
                loadMap(splits[1]);
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
                create(splits[1]);
                break;

            case "attach":
                Main.map.getElement(splits[2]).attachPipe(Main.map.getElement(splits[1]));
                break;

            case "save_map":
                saveMap(splits[1]);
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
                Tester.runTest(splits[1]);
                break;

            default:
                break;
        }
    }

    private void move(){
        MapElement element = Main.currentPlayer.getMapElement();
        for(MapElement neighbour : element.getNeighbours()){
            System.out.println(neighbour.getLogID());
        }
        Scanner scanner = new Scanner(System.in);
        String target = scanner.nextLine();
        Main.currentPlayer.step(Main.map.getElement(target));
    }

    private void configure(){
        MapElement playerMapElement = Main.currentPlayer.getMapElement();
        for(MapElement neighbour : playerMapElement.getNeighbours()){
            System.out.println(neighbour.getLogID());
        }
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String output = scanner.nextLine();
        Main.currentPlayer.configurePump(Main.map.getElement(input), Main.map.getElement(output));
    }

    private void create(String type){
        switch (type){
            case "Pump":
                Pump pump = new Pump();
                Main.map.storeNewMapElement(pump);
                Main.map.addActive(pump);
                break;
            case "Cistern":
                Cistern cistern = new Cistern();
                Main.map.storeNewMapElement(cistern);
                Main.map.addActive(cistern);
                break;
            case "Spring":
                Spring spring = new Spring();
                Main.map.storeNewMapElement(spring);
                Main.map.addActive(spring);
                break;
            case "Pipe":
                Pipe pipe = new Pipe();
                Main.map.storeNewMapElement(pipe);
                Main.map.addUpdatable(pipe);
                break;
        }
    }

    private void loadMap(String id){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("maps/"+id+".txt"));
            List<String> lines = reader.lines().toList();
            boolean readingElements = true;
            for(String line : lines){
                if(line.equals("Pipes")) readingElements = false;
                String[] splits = line.split(",");
                if(readingElements) {
                    ActiveElement element = null;
                    switch (splits[0]) {
                        case "Cistern":
                            element = new Cistern();
                            break;

                        case "Spring":
                            element = new Spring();
                            break;

                        case "Pump":
                            element = new Pump(Integer.parseInt(splits[1]));
                            break;
                    }
                    Main.map.storeNewMapElement(element);
                    Main.map.addActive(element);
                }
                else{
                    Pipe pipe = new Pipe(Integer.parseInt(splits[2]));
                    Main.map.storeNewMapElement(pipe);
                    Main.map.getElement(Integer.parseInt(splits[0])).attachPipe(pipe);
                    Main.map.getElement(Integer.parseInt(splits[1])).attachPipe(pipe);
                    Main.map.addUpdatable(pipe);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveMap(String id){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("maps/"+id+".txt"));
            writer.append("ActiveElements\n");
            for(ActiveElement element : Main.map.activeElements){
                writer.append(element.getLogID()+","+element.getCapacity()+"\n");
            }
            writer.append("Connectins\n");
            for()
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
