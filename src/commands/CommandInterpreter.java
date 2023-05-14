package commands;

import main.Controller;

import main.logging.Logger;
import main.map.*;
import main.players.*;
import tests.ProtoTests.Tester;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class CommandInterpreter {

    public static void runCommand(String cmd, Player player){
        String[] splits = cmd.split(" ");
        switch (splits[0]) {
            case "move":
                move(player);
                break;

            case "place":
                if (splits[1].equals("pipe")) {
                    player.placePipe();
                } else if (splits[1].equals("pump")) {
                    player.placePump();
                }
                break;

            case "pickup":
                pickUp(splits[1], player);
                break;

            case "break":
                player.breakElement();
                break;

            case "repair":
                player.repair();
                break;

            case "configure":
                configure(player);
                break;

            case "make_slippery":
                player.useSlipperyGoo();
                break;

            case "make_sticky":
                player.useStickyGoo();
                break;

            case "end_turn":
                Logger.log("console.txt", "["+player.getLogID()+"]: turn ended", true);
                break;

            case "debug_break":
                Map.getInstance().getElement(splits[1]).setBroken(true);
                Logger.log("console.txt", "["+Map.getInstance().getElement(splits[1]).getLogID()+"]: broken by tester", true);
                break;

            case "debug_repair":
                Map.getInstance().getElement(splits[1]).setBroken(false);
                Logger.log("console.txt", "["+Map.getInstance().getElement(splits[1]).getLogID()+"]: repaired by tester", true);
                break;

            case "flow":
                Map.getInstance().waterFlow(Integer.parseInt(splits[1]));
                break;

            case "exit":
                Logger.log("console.txt", "[Game]: exiting...", true);
                System.exit(1);
                break;

            case "start":
                if(MechanicTeam.getInstance().players.size()>1 && SaboteurTeam.getInstance().players.size()>1) {
                    Logger.log("console.txt", "[Game]: starting...", true);
                    Controller.run();
                }
                else
                    Logger.log("console.txt", "[Game]: can’t start due to not having enough players", true);
                break;

            case "load_map":
                Map.getInstance().loadMap(splits[1]);
                break;

            case "create_saboteur":
                Saboteur newSaboteur = new Saboteur();
                SaboteurTeam.getInstance().addPlayer(newSaboteur);
                Logger.log("console.txt", "["+newSaboteur.getLogID()+"]: created", true);
                break;

            case "create_mechanic":
                Mechanic newMechanic = new Mechanic();
                MechanicTeam.getInstance().addPlayer(newMechanic);
                Logger.log("console.txt", "["+newMechanic.getLogID()+"]: created", true);
                break;

            case "setplayerposition":
                setPlayerPosition(splits);
                break;

            case "create_pipe":
                Map.getInstance().getElement(splits[1]).newPipe();
                break;

            case "debug_info":
                Logger.log("console.txt", Map.getInstance().getElement(splits[1]).printInfo(), true);
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
                Logger.log("console.txt", "[Game]: forcefully started", true);
                Controller.debug_run();
                break;

            case "debug_slippery":
                Map.getInstance().getElement(splits[1]).makeSlippery(3);
                Logger.log("console.txt", "["+Map.getInstance().getElement(splits[1]).getLogID()+"]: now slippery", true);
                break;

            case "debug_sticky":
                Map.getInstance().getElement(splits[1]).makeSticky(3);
                Logger.log("console.txt", "["+Map.getInstance().getElement(splits[1]).getLogID()+"]: now sticky", true);
                break;

            case "runtest":
                Tester.runTest(splits[1]);
                if(Tester.currentTestLog != null) {
                    Tester.currentTestLog.clear();
                }
                Tester.currentTestLog = null;
                break;
            default:
                break;
        }
    }

    private static void move(Player player){
        MapElement element = player.getMapElement(); //holymoly...
        for(MapElement neighbour : element.getNeighbours()){
            System.out.println(neighbour.getLogID());
        }

        boolean validInput = false;
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter(System.lineSeparator());
        String target = scanner.nextLine();
        //scanner.close();

        for(MapElement neighbour : element.getNeighbours()){
            if(target.equals(neighbour.getLogID())) validInput = true;
        }

        if(validInput)
            player.step(Map.getInstance().getElement(target));
        else
            Logger.log("console.txt", "["+element.getLogID()+"]: Nem létező szomszéd lett megadva.", true);
    }

    private static void configure(Player player){
        MapElement playerMapElement = player.getMapElement();
        for(MapElement neighbour : playerMapElement.getNeighbours()){
            System.out.println(neighbour.getLogID());
        }

        boolean validInput = false;
        boolean validOutput = false;
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String output = scanner.nextLine();
        //scanner.close();

        for(MapElement neighbour : playerMapElement.getNeighbours()){
            if(!input.equals(output)){
                if(input.equals(neighbour.getLogID())) validInput = true;

                else if(output.equals(neighbour.getLogID())) validOutput = true;
            }
            else Logger.log("console.txt", "["+playerMapElement.getLogID()+"]: Az input és az output ugyanaz lett!", true);
        }

        if(validOutput && validInput) player.configurePump(Map.getInstance().getPipe(input), Map.getInstance().getPipe(output));
        else if(!validOutput) Logger.log("console.txt", "["+playerMapElement.getLogID()+"]: Az outputnak nem létező cső lett megadva.", true);
        else if(!validInput) Logger.log("console.txt", "["+playerMapElement.getLogID()+"]: Az inputnak nem létező cső lett megadva.", true);
        else Logger.log("console.txt", "["+playerMapElement.getLogID()+"]: Az inputnak és outputnak is nem létező cső lett megadva.", true);
    }

    private static void pickUp(String cmd, Player player){
        if (cmd.equals("pipe")) {
            MapElement playerMapElement = player.getMapElement();
            for(MapElement neighbour : playerMapElement.getNeighbours()){
                Logger.log("console.txt",neighbour.getLogID(), true);
            }
            Scanner scanner = new Scanner(System.in);
            String target = scanner.nextLine();
            //scanner.close();
            boolean valid = false;
            for(MapElement neighbour : playerMapElement.getNeighbours()){
                if(neighbour.getLogID().equals(target)) valid = true;
            }

            if(valid)
                player.pickUpPipe(Map.getInstance().getPipe(target));
            else
                Logger.log("console.txt", "["+playerMapElement.getLogID()+"]: Invalid target!", true);
        } else if (cmd.equals("pump")) {
            player.pickUpPump();
        }
    }

    private static void setPlayerPosition(String[] cmd){
        List<Player> players = new ArrayList<>();
        players.addAll(MechanicTeam.getInstance().getPlayers());
        players.addAll(SaboteurTeam.getInstance().getPlayers());
        Player player = null;
        for(Player p : players){
            if(p.getLogID().equals(cmd[1])) player = p;
        }
        if(player == null) Logger.log("console.txt", "[System]: Player doesn't exist", false);
        else {
            player.setMapElement(Map.getInstance().getElement(cmd[2]));
            MapElement element = Map.getInstance().getElement(cmd[2]);
            if(element != null) {
                element.addPlayer(player);
                Logger.log("console.txt", "[" + player.getLogID() + "]: position set to " + Map.getInstance().getElement(cmd[2]).getLogID(), true);
            }
            else Logger.log("console.txt", "[System]: not valid element", true);
        }
    }
}
