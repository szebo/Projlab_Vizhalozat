package tests.SkeletonTests.playerActionTests;

import main.map.Cistern;
import main.map.Pipe;
import main.map.Pump;
import main.players.Mechanic;

public class PlayerPickUpTest {
    public static void mechanicPickUpOnCistern(){
        System.out.println("A Szerelő Felvesz Egy Ciszternán Teszt megkezdése!\nInicializálás");
        Cistern cistern1 = new Cistern();
        Pipe pipe1 = new Pipe();
        Mechanic mechanic1 = new Mechanic();

        cistern1.attachPipe(pipe1);
        cistern1.addPlayer(mechanic1);
        mechanic1.setMapElement(cistern1);

        System.out.println("A szerelő felvesz egy pumpát");
        mechanic1.pickUpPump();

        System.out.println("A szerelő felvesz egy csövet");
        mechanic1.pickUpPipe(pipe1);
    }

    public static void mechanicPickUpOnPump(){
        System.out.println("A Szerelő Felvesz Egy Pumpán Teszt megkezdése!\nInicializálás");
        Pump pump1 = new Pump();
        Mechanic mechanic1 = new Mechanic();
        Pipe pipe1 = new Pipe();
        Pipe occupiedPipe1 = new Pipe();
        Mechanic mechanic2 = new Mechanic();

        pump1.attachPipe(pipe1);
        pump1.attachPipe(occupiedPipe1);
        pump1.addPlayer(mechanic1);
        mechanic1.setMapElement(pump1);
        occupiedPipe1.addPlayer(mechanic2);
        mechanic2.setMapElement(occupiedPipe1);

        System.out.println("A szerelő fel akar venni egy csövet");
        mechanic1.pickUpPipe(occupiedPipe1);

        System.out.println("A szerelő fel akar venni egy másik csövet");
        mechanic1.pickUpPipe(pipe1);
    }
}
