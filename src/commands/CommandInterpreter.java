package commands;

import main.Main;


import main.logging.Logger;
import main.players.Mechanic;
import main.players.MechanicTeam;
import main.players.Saboteur;
import main.players.SaboteurTeam;


public class CommandInterpreter {

    public void runCommand(String cmd){
        String[] splits = cmd.split(" ");
        switch (splits[0]) {
            case "move":
                Main.currentPlayer.step(Main.map.getElement(splits[1]));
                break;

            case "place":
                if (splits[1].equals("pipe")) {
                    Main.currentPlayer.placePipe();
                } else if (splits[1].equals("pump")) {

                }
                break;

            case "pickup":
                if (splits[1].equals("pipe")) {
                    return;
                } else if (splits[1].equals("pump")) {
                    return;
                }
                break;

            case "break":
                Main.currentPlayer.breakElement();
                break;

            case "repair":
                Main.currentPlayer.repair();
                break;

            case "configure":
                Main.currentPlayer.configurePump();
                break;

            case "make_slippery":
                Main.currentPlayer.useSlipperyGoo();
                break;

            case "make_sticky":
                Main.currentPlayer.useStickyGoo();
                break;

            case "end_turn":
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
                break;

            case "load_map":
                break;

            case "create_saboteur":
                SaboteurTeam.getInstance().addPlayer(new Saboteur());
                break;

            case "create_mechanic":
                MechanicTeam.getInstance().addPlayer(new Mechanic());
                break;

            case "setplayerposition":
                break;

            case "create_pipe":
                break;

            case "debug_info":
                Logger.logToConsole("console.txt", Main.map.getElement(splits[1]).printInfo());
                break;

            case "create":
                break;

            case "attach":
                break;

            case "save_map":
                break;

            default:
                break;
        }
    }

}
