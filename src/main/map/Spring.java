package main.map;

import java.util.ArrayList;

public class Spring extends ActiveElement {

    private static int nextID = 0;

    /**
     * controller hív
     */

    public Spring(){
        pipes = new ArrayList<>();
        players = new ArrayList<>();
        this.ID = nextID++;
    }

    @Override
    public void control() {
    }

    /**
     *  minden rákötött Pipe-ra a Spring vizet küld controller hívásra
     *  a vizet maximális kapacitással tolja ki
     */
    public void giveWater(){
        for(Pipe p: pipes){
            p.addWater(p.capacity);
        }
    }

    /**
     * B Terv Vízmozgatásra
     */
    @Override
    public void pumpWater() {
        giveWater();
    }

    public String getLogID(){
        return "Spring"+this.ID;
    }
}
