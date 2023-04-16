package main.players;

import main.map.Pipe;
import main.map.Pump;
import java.util.*;

public class Mechanic extends Player {

    private Pipe pipeInHand;
    private List<Pump> pumpsInInventory;

    public void pickUpPipe(Pipe pipe){
        this.pipeInHand = Objects.requireNonNull(pipe, "Null értéket kapott a pipeInHand!");
    }

    public void placePipe(){
        mapElement.attachPipe(pipeInHand);
        pipeInHand = null; // atirni
    }

    public void pickUpPump(){
       // pumpsInInventory.add(mapElement); ide kene valami praktikus. givePump()ot nem ismer a player
    }

    public void placePump(){
        mapElement.cut(pumpsInInventory.get(0));
        this.setMapElement(pumpsInInventory.get(0));
        pumpsInInventory.remove(0);
    }

    public void repair(){

    }

    public void addPumpToInventory(Pump pump){

    }

}
