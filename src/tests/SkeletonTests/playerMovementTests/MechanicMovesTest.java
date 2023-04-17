package tests.SkeletonTests.playerMovementTests;

import main.map.Cistern;
import main.map.Pipe;
import main.map.Pump;
import main.map.Spring;
import main.players.Mechanic;
import main.players.Saboteur;

public class MechanicMovesTest {

    /**
     *Ezzel a függvénnyel egy szerelő egy forrásról egy csőre lép.
     *Először létrehozzuk az elemeket, beállítjuk a karakter pozícióját, majd léptetjük.
     */
    public static void SpringToPipe(){
        System.out.println("Mechanic moves from Spring to Pipe");

        Spring spring1 = new Spring();
        Pipe freePipe1 = new Pipe();
        Mechanic mechanic1 = new Mechanic();

        spring1.attachPipe(freePipe1);
        spring1.addPlayer(mechanic1);
        mechanic1.setMapElement(spring1);

        System.out.println("Mechanic position before step: " + mechanic1.getMapElementAsString());

        mechanic1.step(freePipe1);
        System.out.println("Mechanic position after step: " + mechanic1.getMapElementAsString());
        System.out.println();

    }

    /**
     *Ezzel a függvénnyel egy szerelő egy csőről egy forrásra lép.
     *Először létrehozzuk az elemeket, beállítjuk a karakter pozícióját, majd léptetjük.
     */
    public static void PipeToSpring(){
        System.out.println("Mechanic moves from Pipe to Spring");

        Spring spring1 = new Spring();
        Pipe freePipe1 = new Pipe();
        Mechanic mechanic1 = new Mechanic();

        spring1.attachPipe(freePipe1);
        freePipe1.addPlayer(mechanic1);
        mechanic1.setMapElement(freePipe1);

        System.out.println("Mechanic position before step: " + mechanic1.getMapElementAsString());

        mechanic1.step(spring1);
        System.out.println("Mechanic position after step: " + mechanic1.getMapElementAsString());
        System.out.println();
    }

    /**
     *Ezzel a függvénnyel egy szerelő egy csőről egy pumpára lép.
     *Először létrehozzuk az elemeket, beállítjuk a karakter pozícióját, majd léptetjük.
     */
    public static void PipeToPump(){
        System.out.println("Mechanic moves from Pipe to Pump");

        Pump pump1 = new Pump();
        Pipe freePipe1 = new Pipe();
        Mechanic mechanic1 = new Mechanic();

        pump1.attachPipe(freePipe1);
        freePipe1.addPlayer(mechanic1);
        mechanic1.setMapElement(freePipe1);

        System.out.println("Mechanic position before step: " + mechanic1.getMapElementAsString());

        mechanic1.step(pump1);
        System.out.println("Mechanic position after step: " + mechanic1.getMapElementAsString());
        System.out.println();
    }

    /**
     *Ezzel a függvénnyel egy szerelő egy pumpáról egy csőre lép.
     *Először létrehozzuk az elemeket, beállítjuk a karakter pozícióját, majd léptetjük.
     */
    public static void PumpToPipe(){
        System.out.println("Mechanic moves from Pump to Pipe");

        Pump pump1 = new Pump();
        Pipe freePipe1 = new Pipe();
        Mechanic mechanic1 = new Mechanic();

        pump1.attachPipe(freePipe1);
        pump1.addPlayer(mechanic1);
        mechanic1.setMapElement(pump1);

        System.out.println("Mechanic position before step: " + mechanic1.getMapElementAsString());

        mechanic1.step(freePipe1);
        System.out.println("Mechanic position after step: " + mechanic1.getMapElementAsString());
        System.out.println();
    }

    /**
     *Ezzel a függvénnyel egy szerelő egy csőről egy ciszternára lép.
     *Először létrehozzuk az elemeket, beállítjuk a karakter pozícióját, majd léptetjük.
     */
    public static void PipeToCistern(){
        System.out.println("Mechanic moves from Pipe to Cistern");

        Cistern cistern1 = new Cistern();
        Pipe freePipe1 = new Pipe();
        Mechanic mechanic1 = new Mechanic();

        cistern1.attachPipe(freePipe1);
        freePipe1.addPlayer(mechanic1);
        mechanic1.setMapElement(freePipe1);

        System.out.println("Mechanic position before step: " + mechanic1.getMapElementAsString());

        mechanic1.step(cistern1);
        System.out.println("Mechanic position after step: " + mechanic1.getMapElementAsString());
        System.out.println();
    }

    /**
     *Ezzel a függvénnyel egy szerelő egy ciszternáról egy csőre lép.
     *Először létrehozzuk az elemeket, beállítjuk a karakter pozícióját, majd léptetjük.
     */
    public static void CisternToPipe(){
        System.out.println("Mechanic moves from Cistern to Pipe");

        Cistern cistern1 = new Cistern();
        Pipe freePipe1 = new Pipe();
        Mechanic mechanic1 = new Mechanic();

        cistern1.attachPipe(freePipe1);
        cistern1.addPlayer(mechanic1);
        mechanic1.setMapElement(cistern1);

        System.out.println("Mechanic position before step: " + mechanic1.getMapElementAsString());

        mechanic1.step(freePipe1);
        System.out.println("Mechanic position after step: " + mechanic1.getMapElementAsString());
        System.out.println();
    }

    /**
     *Ezzel a függvénnyel egy szerelő egy forrásról egy csőre lép.
     *Először létrehozzuk az elemeket, beállítjuk a karakterek pozícióját, majd léptetjük a szerelőt.
     *Itt nem történik tényleges lépés, ugyanis egy foglalt mezőre próbálunk lépni, ami ugye nem lehetséges.
     */
    public static void SpringToOccupiedPipe(){
        System.out.println("Mechanic moves from Spring to Occupied Pipe");

        Spring spring1 = new Spring();
        Pipe occupiedPipe = new Pipe();
        Mechanic mechanic1 = new Mechanic();
        Saboteur saboteur1 = new Saboteur();

        spring1.attachPipe(occupiedPipe);
        spring1.addPlayer(mechanic1);
        mechanic1.setMapElement(spring1);
        occupiedPipe.addPlayer(saboteur1);
        saboteur1.setMapElement(occupiedPipe);

        System.out.println("Mechanic position before step: " + mechanic1.getMapElementAsString());

        mechanic1.step(occupiedPipe);
        System.out.println("Mechanic position after step: " + mechanic1.getMapElementAsString());
        System.out.println();

    }

    /**
     *Ezzel a függvénnyel egy szerelő egy forrásról egy csőre lép.
     *Először létrehozzuk az elemeket, beállítjuk a karakterek pozícióját, majd léptetjük a szerelőt.
     *Itt nem történik tényleges lépés, ugyanis egy foglalt mezőre próbálunk lépni, ami ugye nem lehetséges.
     */
    public static void PumpToOccupiedPipe(){
        System.out.println("Mechanic moves from Pump to Occupied Pipe");

        Pump pump1 = new Pump();
        Pipe occupiedPipe = new Pipe();
        Mechanic mechanic1 = new Mechanic();
        Saboteur saboteur1 = new Saboteur();

        pump1.attachPipe(occupiedPipe);
        pump1.addPlayer(mechanic1);
        mechanic1.setMapElement(pump1);
        occupiedPipe.addPlayer(saboteur1);
        saboteur1.setMapElement(occupiedPipe);

        System.out.println("Mechanic position before step: " + mechanic1.getMapElementAsString());

        mechanic1.step(occupiedPipe);
        System.out.println("Mechanic position after step: " + mechanic1.getMapElementAsString());
        System.out.println();
    }

    /**
     *Ezzel a függvénnyel egy szerelő egy forrásról egy csőre lép.
     *Először létrehozzuk az elemeket, beállítjuk a karakterek pozícióját, majd léptetjük a szerelőt.
     *Itt nem történik tényleges lépés, ugyanis egy foglalt mezőre próbálunk lépni, ami ugye nem lehetséges.
     */
    public static void CisternToOccupiedPipe(){
        System.out.println("Mechanic moves from Cistern to Occupied Pipe");

        Cistern cistern1 = new Cistern();
        Pipe occupiedPipe = new Pipe();
        Mechanic mechanic1 = new Mechanic();
        Saboteur saboteur1 = new Saboteur();

        cistern1.attachPipe(occupiedPipe);
        cistern1.addPlayer(mechanic1);
        mechanic1.setMapElement(cistern1);
        occupiedPipe.addPlayer(saboteur1);
        saboteur1.setMapElement(occupiedPipe);

        System.out.println("Mechanic position before step: " + mechanic1.getMapElementAsString());

        mechanic1.step(occupiedPipe);
        System.out.println("Mechanic position after step: " + mechanic1.getMapElementAsString());
        System.out.println();
    }
}
