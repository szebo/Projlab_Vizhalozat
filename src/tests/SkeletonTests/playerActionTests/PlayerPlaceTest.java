package tests.SkeletonTests.playerActionTests;

import main.map.Cistern;
import main.map.Pipe;
import main.map.Pump;
import main.map.Spring;
import main.players.Mechanic;

public class PlayerPlaceTest {

    public static void mechanicPlacesPump(){
        System.out.println("A Szerelő Lerak Egy Pumpát Teszt megkezdése!\nInicializálás");
        Pump pump1 = new Pump();
        Mechanic mechanic1 = new Mechanic();
        Pipe pipe1 = new Pipe();
        pipe1.addElement(pump1);

        mechanic1.addPumpToInventory(pump1);
        mechanic1.setMapElement(pipe1);
        pipe1.addPlayer(mechanic1);

        System.out.println("A szerelő megpróbál lerakni egy pumpát");
        mechanic1.placePump();
    }

    public static void mechanicPlacesPipeOnPump(){
        System.out.println("A Szerelő Lerak Egy Csövet Egy Pumpán Teszt megkezdése!\nInicializálás");
        Pump pump1 = new Pump();
        Pipe pipe1 = new Pipe();
        Mechanic mechanic1 = new Mechanic();

        mechanic1.setMapElement(pump1);
        mechanic1.setPipeInHand(pipe1);
        pump1.addPlayer(mechanic1);

        System.out.println("A szerelő megpróbál lerakni egy csövet");
        mechanic1.placePipe();
    }

    public static void mechanicPlacesPipeOnCistern(){
        System.out.println("A Szerelő Lerak Egy Csövet Egy Ciszternán Teszt megkezdése!\nInicializálás");
        Cistern cistern1 = new Cistern();
        Pipe pipe1 = new Pipe();
        Mechanic mechanic1 = new Mechanic();

        mechanic1.setMapElement(cistern1);
        mechanic1.setPipeInHand(pipe1);
        cistern1.addPlayer(mechanic1);

        System.out.println("A szerelő megpróbál lerakni egy csövet");
        mechanic1.placePipe();
    }

    public static void mechanicPlacesPipeOnSpring(){
        System.out.println("A Szerelő Lerak Egy Csövet Egy Forráson Teszt megkezdése!\nInicializálás");
        Spring spring1 = new Spring();
        Pipe pipe1 = new Pipe();
        Mechanic mechanic1 = new Mechanic();

        mechanic1.setMapElement(spring1);
        mechanic1.setPipeInHand(pipe1);
        spring1.addPlayer(mechanic1);

        System.out.println("A szerelő megpróbál lerakni egy csövet");
        mechanic1.placePipe();
    }
}
