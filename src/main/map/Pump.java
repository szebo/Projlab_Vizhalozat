package main.map;

import main.logging.Logger;
import main.players.SaboteurTeam;

import java.util.ArrayList;

/**
 * Pumpákat, és azok viselkedését leíró osztály
 */
public class Pump extends ActiveElement {

    private static int nextID = 1;

    /**
     * A pumpa bemeneti csövét tárolja
     */
    private Pipe input;
    /**
     * A pumpa kimeneti csövét tárolja
     */
    private Pipe output;

    /**
     * Alapvető konstruktor
     */
    public Pump(){
        this.capacity = 20;
        pipes = new ArrayList<>();
        players = new ArrayList<>();
        this.ID = nextID++;
    }

    /**
     * Kapacitást beállító konstuktor
     * @param capacity A pumpa belső kapacitása
     */
    public Pump(int capacity){
        this.capacity = capacity;
        pipes = new ArrayList<>();
        players = new ArrayList<>();
        this.ID = nextID++;
        //Logger.log("console.txt", "["+getLogID()+"]: created", true);
    }

    /**
     * controller hívásra eltörik a pumpa.
     */
    @Override
    public void control() {
        setBroken(true);
    }

    /**
     * Getter
     * @return input cső
     */
    public Pipe getInput() {
        return input;
    }

    /**
     * Beállítja a pumpa bemenetét a paraméterben kapott csőre.
     * @param input a cső amire a bemenetet szeretnénk állítani.
     */
    public void setInput(Pipe input) {
        if(this.input != input)
            this.input = input;
        Logger.log("log.txt", "Input set", false);
    }

    public Pipe getOutput() {
        return output;
    }

    /**
     * Beállítja a pumpa kimenetét a paraméterben kapott csőre.
     * @param output a cső amire a kimenetet szeretnénk állítani.
     */
    public void setOutput(Pipe output) {
        if(this.output != output)
            this.output = output;
        Logger.log("log.txt", "Output set", false);
    }

    /**
     * Vízet szív a tárolójába a bemeneti csőből, aztán kipumpál a kimeneti csőbe.
     */
    @Override
    public void pumpWater() {
        if(!isBroken) {
            int taken = input.removeWater(input.water);
            int cached = this.addWater(taken);
            if (output.isBroken()) {
                int removedWater = this.removeWater(this.water);
                SaboteurTeam.getInstance().addPoints(removedWater);
            } else {
                int pumped = output.addWater(cached);
                this.removeWater(pumped);
            }
        }
    }

    public String getLogID(){
        return "Pump"+this.ID;
    }

    /**
     * Létrehoz egy stringet a pumpa információival
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
        stringBuilder.append(isBroken ? "\nBroken\n" : "\nNot Broken\n");
        stringBuilder.append("Water: "+water+"\nCapacity: "+capacity+"\n");
        stringBuilder.append("Input: "+input.getLogID()+"\nOutput: "+output.getLogID()+"\n");
        return stringBuilder.toString();
    }

    public void resetAfterTest(){
        nextID = 1;
    }
}
