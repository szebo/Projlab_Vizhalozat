package main.map;

public class Pump extends ActiveElement {



    private Pipe input;
    private Pipe output;
    public Pump(){
        this.capacity = 20;
    }
    public Pump(int capacity){
        this.capacity = capacity;
    }

    /**
     *
     */
    @Override
    public void control() {
        breakElement();
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

    public void setInput(Pipe input) {
        this.input = input;
    }

    public Pipe getOutput() {
        return output;
    }

    public void setOutput(Pipe output) {
        this.output = output;
    }
}
