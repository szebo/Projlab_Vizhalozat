package main.map;

public class Pump extends ActiveElement {

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

    }

    /**
     * @param pipe
     */
    @Override
    public void detachPipe(Pipe pipe) {

    }

    /**
     * @param pipe
     */
    @Override
    public void attachPipe(Pipe pipe) {

    }

    /**
     *
     */
    @Override
    public void interact() {

    }
}
