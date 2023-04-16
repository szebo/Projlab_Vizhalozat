package main.map;

import java.util.ArrayList;

public class Pump extends ActiveElement {



    private Pipe input;
    private Pipe output;
    public Pump(){
        this.capacity = 20;
        pipes = new ArrayList<>();
        players = new ArrayList<>();
    }
    public Pump(int capacity){
        this.capacity = capacity;
        pipes = new ArrayList<>();
    }

    /**
     * controller hívásra eltörik a pumpa.
     */
    @Override
    public void control() {
        setBroken(true);
    }


    /**
     *
     */
    @Override
    public void interact() {
        //létezik, de soha senki nem hívja. Dokumentációban is hiányzik, csak az osztálydiagramon van fent.
        //TODO ha nem lát el funkciót, ki kell venni
    }

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
        System.out.println("Pumpa Input beállítva.");
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
        System.out.println("Pumpa Output beállítva.");
    }

    /**
     * B Terv Vízmozgatásra
     */
    @Override
    public void pumpWater() {
        int taken = input.removeWater(input.water);
        int cached = this.addWater(taken);
        if(output.isBroken()){
            //Pontozás
            this.removeWater(this.water);
        }
        else {
            int pumped = output.addWater(cached);
            this.removeWater(pumped);
        }
    }
}
