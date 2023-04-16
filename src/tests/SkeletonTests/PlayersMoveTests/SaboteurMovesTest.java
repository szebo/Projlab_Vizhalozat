package tests.SkeletonTests.PlayersMoveTests;

import main.map.Cistern;
import main.map.Pipe;
import main.map.Pump;
import main.map.Spring;
import main.players.Mechanic;
import main.players.Saboteur;

public class SaboteurMovesTest {

    public static void SpringToPipe(){
        System.out.println("Saboteur moves from Spring to Pipe");
                                                                                                            //Szereplők létrehozása
        Spring spring1 = new Spring();
        Pipe freePipe1 = new Pipe();
        Saboteur saboteur1 = new Saboteur();
                                                                                                            //A szereplők kiinduló adatainak beállítása
        spring1.attachPipe(freePipe1);
        spring1.addPlayer(saboteur1);
        saboteur1.setMapElement(spring1);

        System.out.println("Saboteur position before step: " + saboteur1.getMapElement());
                                                                                                            //A tényleges teszteset eseményének indulása
        saboteur1.step(freePipe1);
        System.out.println("Saboteur position after step: " + saboteur1.getMapElement());                   //A teszt utáni pozíció, sikeresség ellenőrzése
        System.out.println();
    }

    public static void PipeToSpring(){
        System.out.println("Saboteur moves from Pipe to Spring");
                                                                                                            //Szereplők létrehozása
        Spring spring1 = new Spring();
        Pipe freePipe1 = new Pipe();
        Saboteur saboteur1 = new Saboteur();
                                                                                                            //A szereplők kiinduló adatainak beállítása
        spring1.attachPipe(freePipe1);
        freePipe1.addPlayer(saboteur1);
        saboteur1.setMapElement(freePipe1);

        System.out.println("Saboteur position before step: " + saboteur1.getMapElement());
                                                                                                            //A tényleges teszteset eseményének indulása
        saboteur1.step(spring1);
        System.out.println("Saboteur position after step: " + saboteur1.getMapElement());                   //A teszt utáni pozíció, sikeresség ellenőrzése
        System.out.println();
    }

    public static void PipeToPump(){
        System.out.println("Saboteur moves from Pipe to Pump");
                                                                                                            //Szereplők létrehozása
        Pump pump1 = new Pump();
        Pipe freePipe1 = new Pipe();
        Saboteur saboteur1 = new Saboteur();
                                                                                                            //A szereplők kiinduló adatainak beállítása
        pump1.attachPipe(freePipe1);
        freePipe1.addPlayer(saboteur1);
        saboteur1.setMapElement(freePipe1);

        System.out.println("Saboteur position before step: " + saboteur1.getMapElement());
                                                                                                            //A tényleges teszteset eseményének indulása
        saboteur1.step(pump1);
        System.out.println("Saboteur position after step: " + saboteur1.getMapElement());                   //A teszt utáni pozíció, sikeresség ellenőrzése
        System.out.println();
    }

    public static void PumpToPipe(){
        System.out.println("Saboteur moves from Pump to Pipe");
                                                                                                            //Szereplők létrehozása
        Pump pump1 = new Pump();
        Pipe freePipe1 = new Pipe();
        Saboteur saboteur1 = new Saboteur();
                                                                                                            //A szereplők kiinduló adatainak beállítása
        pump1.attachPipe(freePipe1);
        pump1.addPlayer(saboteur1);
        saboteur1.setMapElement(pump1);

        System.out.println("Saboteur position before step: " + saboteur1.getMapElement());
                                                                                                            //A tényleges teszteset eseményének indulása
        saboteur1.step(freePipe1);
        System.out.println("Saboteur position after step: " + saboteur1.getMapElement());                   //A teszt utáni pozíció, sikeresség ellenőrzése
        System.out.println();
    }

    public static void PipeToCistern(){
        System.out.println("Saboteur moves from Pipe to Cistern");
                                                                                                            //Szereplők létrehozása
        Cistern cistern1 = new Cistern();
        Pipe freePipe1 = new Pipe();
        Saboteur saboteur1 = new Saboteur();
                                                                                                            //A szereplők kiinduló adatainak beállítása
        cistern1.attachPipe(freePipe1);
        freePipe1.addPlayer(saboteur1);
        saboteur1.setMapElement(freePipe1);

        System.out.println("Saboteur position before step: " + saboteur1.getMapElement());
                                                                                                            //A tényleges teszteset eseményének indulása
        saboteur1.step(cistern1);
        System.out.println("Saboteur position after step: " + saboteur1.getMapElement());                   //A teszt utáni pozíció, sikeresség ellenőrzése
        System.out.println();
    }

    public static void CisternToPipe(){
        System.out.println("Saboteur moves from Cistern to Pipe");
                                                                                                            //Szereplők létrehozása
        Cistern cistern1 = new Cistern();
        Pipe freePipe1 = new Pipe();
        Saboteur saboteur1 = new Saboteur();
                                                                                                            //A szereplők kiinduló adatainak beállítása
        cistern1.attachPipe(freePipe1);
        cistern1.addPlayer(saboteur1);
        saboteur1.setMapElement(cistern1);

        System.out.println("Saboteur position before step: " + saboteur1.getMapElement());
                                                                                                            //A tényleges teszteset eseményének indulása
        saboteur1.step(freePipe1);
        System.out.println("Saboteur position after step: " + saboteur1.getMapElement());                   //A teszt utáni pozíció, sikeresség ellenőrzése
        System.out.println();
    }

    public static void SpringToOccupiedPipe(){
        System.out.println("Saboteur moves from Spring to Occupied Pipe");
                                                                                                            //Szereplők létrehozása
        Spring spring1 = new Spring();
        Pipe occupiedPipe = new Pipe();
        Mechanic mechanic1 = new Mechanic();
        Saboteur saboteur1 = new Saboteur();
                                                                                                            //A szereplők kiinduló adatainak beállítása
        spring1.attachPipe(occupiedPipe);
        spring1.addPlayer(saboteur1);
        saboteur1.setMapElement(spring1);
        occupiedPipe.addPlayer(mechanic1);
        mechanic1.setMapElement(occupiedPipe);

        System.out.println("Saboteur position before step: " + saboteur1.getMapElement());
                                                                                                            //A tényleges teszteset eseményének indulása
        saboteur1.step(occupiedPipe);
        System.out.println("Saboteur position after step: " + saboteur1.getMapElement());                   //A teszt utáni pozíció, sikeresség ellenőrzése
        System.out.println();
    }

    public static void PumpToOccupiedPipe(){
        System.out.println("Saboteur moves from Pump to Occupied Pipe");
                                                                                                            //Szereplők létrehozása
        Pump pump1 = new Pump();
        Pipe occupiedPipe = new Pipe();
        Mechanic mechanic1 = new Mechanic();
        Saboteur saboteur1 = new Saboteur();
                                                                                                            //A szereplők kiinduló adatainak beállítása
        pump1.attachPipe(occupiedPipe);
        pump1.addPlayer(saboteur1);
        saboteur1.setMapElement(pump1);
        occupiedPipe.addPlayer(mechanic1);
        mechanic1.setMapElement(occupiedPipe);

        System.out.println("Saboteur position before step: " + saboteur1.getMapElement());
                                                                                                            //A tényleges teszteset eseményének indulása
        saboteur1.step(occupiedPipe);
        System.out.println("Saboteur position after step: " + saboteur1.getMapElement());                   //A teszt utáni pozíció, sikeresség ellenőrzése
        System.out.println();
    }

    public static void CisternToOccupiedPipe(){
        System.out.println("Saboteur moves from Cistern to Occupied Pipe");
                                                                                                            //Szereplők létrehozása
        Cistern cistern1 = new Cistern();
        Pipe occupiedPipe = new Pipe();
        Mechanic mechanic1 = new Mechanic();
        Saboteur saboteur1 = new Saboteur();
                                                                                                            //A szereplők kiinduló adatainak beállítása
        cistern1.attachPipe(occupiedPipe);
        cistern1.addPlayer(saboteur1);
        saboteur1.setMapElement(cistern1);
        occupiedPipe.addPlayer(mechanic1);
        mechanic1.setMapElement(occupiedPipe);

        System.out.println("Saboteur position before step: " + saboteur1.getMapElement());
                                                                                                            //A tényleges teszteset eseményének indulása
        saboteur1.step(occupiedPipe);
        System.out.println("Saboteur position after step: " + saboteur1.getMapElement());                   //A teszt utáni pozíció, sikeresség ellenőrzése
        System.out.println();
    }
}
