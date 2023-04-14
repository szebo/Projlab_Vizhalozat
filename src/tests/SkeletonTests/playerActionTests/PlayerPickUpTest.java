package tests.SkeletonTests.playerActionTests;

import main.map.Cistern;
import main.map.Pipe;
import main.map.Pump;
import main.map.Spring;
import main.players.Mechanic;

public class PlayerPickUpTest {
    public static void mechanicPickUpOnCistern(){
        Cistern cistern1 = new Cistern();
        Pipe pipe1 = new Pipe();
        Mechanic mechanic1 = new Mechanic();

        cistern1.attachPipe(pipe1);
        cistern1.addPlayer(mechanic1);
        mechanic1.setMapElement(cistern1);
    }

    public static void mechanicPickUpOnPump(){
        Pump pump1 = new Pump();
        Mechanic mechanic1 = new Mechanic();
        Pipe pipe1 = new Pipe();
        Pipe occupiedPipe1 = new Pipe();

        pump1.attachPipe(pipe1);
        pump1.attachPipe(occupiedPipe1);
        pump1.addPlayer(mechanic1);
        mechanic1.setMapElement(pump1);
        occupiedPipe1.setOccupied(true);
    }
}
