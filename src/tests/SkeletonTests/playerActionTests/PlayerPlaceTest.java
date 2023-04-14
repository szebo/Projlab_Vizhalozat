package tests.SkeletonTests.playerActionTests;

import main.map.Cistern;
import main.map.Pipe;
import main.map.Pump;
import main.map.Spring;
import main.players.Mechanic;

public class PlayerPlaceTest {

    public static void mechanicPlacesPump(){
        Pump pump1 = new Pump();
        Mechanic mechanic1 = new Mechanic();
        Pipe pipe1 = new Pipe();

        mechanic1.addPumpToInventory(pump1);
        mechanic1.setMapElement(pipe1);
        pipe1.addPlayer(mechanic1);
    }

    public static void mechanicPlacesPipeOnPump(){
        Pump pump1 = new Pump();
        Pipe pipe1 = new Pipe();
        Mechanic mechanic1 = new Mechanic();

        mechanic1.setMapElement(pump1);
        mechanic1.setPipeInHand(pipe1);
        pump1.addPlayer(mechanic1);
    }

    public static void mechanicPlacesPipeOnCistern(){
        Cistern cistern1 = new Cistern();
        Pipe pipe1 = new Pipe();
        Mechanic mechanic1 = new Mechanic();

        mechanic1.setMapElement(cistern1);
        mechanic1.setPipeInHand(pipe1);
        cistern1.addPlayer(mechanic1);
    }

    public static void mechanicPlacesPipeOnSpring(){
        Spring spring1 = new Spring();
        Pipe pipe1 = new Pipe();
        Mechanic mechanic1 = new Mechanic();

        mechanic1.setMapElement(spring1);
        mechanic1.setPipeInHand(pipe1);
        spring1.addPlayer(mechanic1);
    }
}
