package main.map;

import java.util.ArrayList;

/**
 * Pumpákat, és azok viselkedését leíró osztály
 */
public class Pump extends ActiveElement {
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
    }

    /**
     * Kapacitást beállító konstuktor
     * @param capacity A pumpa belső kapacitása
     */
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
     * Vízet szív a tárolójába a bementi csőből, aztán kipumpál a kimeneti csőbe.
     */
    @Override
    public void pumpWater() {
        int taken = input.removeWater(input.water);
        int cached = this.addWater(taken);
        if(output.isBroken()){
            //TODO Pontozás
            this.removeWater(this.water);
        }
        else {
            int pumped = output.addWater(cached);
            this.removeWater(pumped);
        }
    }
}
