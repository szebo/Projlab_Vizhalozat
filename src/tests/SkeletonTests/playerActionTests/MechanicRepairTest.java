package tests.SkeletonTests.playerActionTests;

import main.map.*;
import main.players.Mechanic;

public class MechanicRepairTest {
    /**
     * Létrejön egy Mechanic és egy Pump
     * A Pumpát beállítjük töröttre
     * A Mechanic megjavítja a pumpát
     */
    public static void MechanicRepairPump(){
        Mechanic mechanic = new Mechanic();
        Pump pump = new Pump();
        mechanic.setMapElement(pump);
        pump.setBroken(true);
        mechanic.repair();
    }

    /**
     * Létrejön egy Mechanic és egy Pipe
     * A Pipeot beállítjük töröttre
     * A Mechanic megjavítja a csövet
     */
    public static void MechanicRepairPipe(){
        Mechanic mechanic = new Mechanic();
        Pipe pipe = new Pipe();
        mechanic.setMapElement(pipe);
        pipe.setBroken(true);
        mechanic.repair();
    }
}
