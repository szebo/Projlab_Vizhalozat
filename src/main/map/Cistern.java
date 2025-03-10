package main.map;

import main.graphics.GUIManager;
import main.logging.Logger;
import main.players.Mechanic;
import main.players.MechanicTeam;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Reprezentálja a ciszternákat, és azok működését írja le.
 */
public class Cistern extends ActiveElement {

    private static int nextID = 1;
    private final List<Pump> pumpsInReserve;

    /**
     * Cistern alapvető konstruktora
     */
    public Cistern(){
        pipes = new ArrayList<>();
        players = new ArrayList<>();
        pumpsInReserve = new ArrayList<>();
        this.ID = nextID++;
    }

    public Cistern(int x, int y){
        pipes = new ArrayList<>();
        players = new ArrayList<>();
        pumpsInReserve = new ArrayList<>();
        this.ID = nextID++;
        GUIManager.getInstance().createCisternGUIObject(this, new Point(x, y));
    }

    /**
     *  létrehoz egy új pumpát és eltárolja a cisternen.
     */
    private static int turn = 0;
    @Override
    public void control() {
        turn++;
        if(turn % 5 == 0)
            newPipe();
    }

    /**
     * Átad egy pumpát a mechanicnak és kiveszi a listából.
     * @param mechanic a mechanic akinek átadjuk a pumpát
     */
    public void givePump(Mechanic mechanic){
        Pump p = new Pump();
        mechanic.addPumpToInventory(p);
        Logger.log("log.txt", "Pump given to Mechanic", false);
    }

    /**
     * létrehoz egy új Pipe objektumot controller hívásra és hozzáköti a Cistern-hez
     */
    public void newPipe(){
        Pipe newpipe = new Pipe();
        GUIManager.getInstance().createPipeGUIObject(newpipe);
        this.attachPipe(newpipe);
        Logger.log("console.txt", "["+newpipe.getLogID()+"]: created", true);
    }

    /**
     * Vízet szív az összes hozzácsatlakozó csőből.
     */
    @Override
    public void pumpWater() {
        for(Pipe p : pipes){
            int taken = p.removeWater(p.water);
            MechanicTeam.getInstance().addPoints(taken);        //TODO pont logolás
        }
    }

    /**
     * Leszármazott osztálytól függően visszaad egy String az osztály nevével és az objektum azonosítójával.
     * @return StringID
     */
    public String getLogID(){
        return "Cistern"+this.ID;
    }

    /**
     * Létrehoz egy stringet a ciszterna információival
     * @return info
     */
    public String printInfo(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("["+getLogID()+"]");
        stringBuilder.append("\nPlayers: ");
        if(players.size()<1) stringBuilder.append(" -");
        else {
            for (int i = 0; i < players.size(); i++) {
                if (i == 0) stringBuilder.append(players.get(i).getLogID());
                else stringBuilder.append(", " + players.get(i).getLogID());
            }
        }
        stringBuilder.append("\nPipes: ");
        if(pipes.size()<1) stringBuilder.append(" -");
        else {
            for (int i = 0; i < players.size(); i++) {
                if (i == 0) stringBuilder.append(pipes.get(i).getLogID());
                else stringBuilder.append(", " + pipes.get(i).getLogID());
            }
        }
        stringBuilder.append("\nPumps in Reserve: "+pumpsInReserve+"\n");
        return stringBuilder.toString();
    }

    public List<Pipe> getOutputPipes()
    {
        return null;
    }
    public static void resetAfterTest(){
        nextID = 1;
    }

}
