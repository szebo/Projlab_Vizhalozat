package tests.SkeletonTests.PlayersMoveTests;

import main.map.Cistern;
import main.map.Pipe;
import main.map.Pump;
import main.map.Spring;
import main.players.Mechanic;
import main.players.Saboteur;

public class SaboteurMoves {

    public void SpringToPipe(){
                                                        //Szereplők létrehozása
        Spring spring1 = new Spring();
        Pipe freePipe1 = new Pipe();
        Saboteur saboteur1 = new Saboteur();
                                                        //A szereplők kiinduló adatainak beállítása
        spring1.attachPipe(freePipe1);
        spring1.addPlayer(saboteur1);
        saboteur1.setMapElement(spring1);
                                                        //A tényleges teszteset eseményének indulása
        saboteur1.step(freePipe1);
    }

    public void PipeToSpring(){
                                                        //Szereplők létrehozása
        Spring spring1 = new Spring();
        Pipe freePipe1 = new Pipe();
        Saboteur saboteur1 = new Saboteur();
                                                        //A szereplők kiinduló adatainak beállítása
        spring1.attachPipe(freePipe1);
        freePipe1.addPlayer(saboteur1);
        saboteur1.setMapElement(freePipe1);
                                                        //A tényleges teszteset eseményének indulása
        saboteur1.step(spring1);
    }

    public void PipeToPump(){
                                                        //Szereplők létrehozása
        Pump pump1 = new Pump();
        Pipe freePipe1 = new Pipe();
        Saboteur saboteur1 = new Saboteur();
                                                        //A szereplők kiinduló adatainak beállítása
        pump1.attachPipe(freePipe1);
        freePipe1.addPlayer(saboteur1);
        saboteur1.setMapElement(freePipe1);
                                                        //A tényleges teszteset eseményének indulása
        saboteur1.step(pump1);
    }

    public void PumpToPipe(){
                                                        //Szereplők létrehozása
        Pump pump1 = new Pump();
        Pipe freePipe1 = new Pipe();
        Saboteur saboteur1 = new Saboteur();
                                                        //A szereplők kiinduló adatainak beállítása
        pump1.attachPipe(freePipe1);
        pump1.addPlayer(saboteur1);
        saboteur1.setMapElement(pump1);
                                                        //A tényleges teszteset eseményének indulása
        saboteur1.step(freePipe1);
    }

    public void PipeToCistern(){
                                                        //Szereplők létrehozása
        Cistern cistern1 = new Cistern();
        Pipe freePipe1 = new Pipe();
        Saboteur saboteur1 = new Saboteur();
                                                        //A szereplők kiinduló adatainak beállítása
        cistern1.attachPipe(freePipe1);
        freePipe1.addPlayer(saboteur1);
        saboteur1.setMapElement(freePipe1);
                                                        //A tényleges teszteset eseményének indulása
        saboteur1.step(cistern1);
    }

    public void CisternToPipe(){
                                                        //Szereplők létrehozása
        Cistern cistern1 = new Cistern();
        Pipe freePipe1 = new Pipe();
        Saboteur saboteur1 = new Saboteur();
                                                        //A szereplők kiinduló adatainak beállítása
        cistern1.attachPipe(freePipe1);
        cistern1.addPlayer(saboteur1);
        saboteur1.setMapElement(cistern1);
                                                        //A tényleges teszteset eseményének indulása
        saboteur1.step(freePipe1);
    }

    public void SpringToOccupiedPipe(){
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
                                                        //A tényleges teszteset eseményének indulása
        saboteur1.step(occupiedPipe);
    }

    public void PumpToOccupiedPipe(){
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
                                                        //A tényleges teszteset eseményének indulása
        saboteur1.step(occupiedPipe);
    }

    public void CisternToOccupiedPipe(){
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
                                                        //A tényleges teszteset eseményének indulása
        saboteur1.step(occupiedPipe);
    }
}
