package tests.SkeletonTests.playerActionTests;

import main.map.*;
import main.players.*;

public class PlayerBreaksTest {
    /**
     * Létrejön egy szabotőr és egy cső
     * A szabotőrnek beállítjuk a mapElement értékét a csőre jelezve, hogy rálépett.
     * A szabotőr eltöri a csövet.
     */
    public static void SaboteurBreaksPipe(){
        Saboteur saboteur = new Saboteur();
        Pipe pipe = new Pipe();
        saboteur.setMapElement(pipe);
        saboteur.breakElement();
    }
    
    /**
     * Létrejön egy szerelő és egy cső
     * A szerelőnek beállítjuk a mapElement értékét a csőre jelezve, hogy rálépett.
     * A szerelő eltöri a csövet.
     */
    public static void MechanicBreaksPipe(){
        Mechanic mechanic = new Mechanic();
        Pipe pipe = new Pipe();
        
        mechanic.setMapElement(pipe);
        mechanic.breakElement();
    }
}
