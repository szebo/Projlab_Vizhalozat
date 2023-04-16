package main.players;

import main.map.Pipe;
import main.map.Pump;
import java.util.*;

public class Mechanic extends Player {

    private Pipe pipeInHand;
    private List<Pump> pumpsInInventory;

    public void pickUpPipe(Pipe pipe){

    }

    public void placePipe(){

    }

    public void pickUpPump(){

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
