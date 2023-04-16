package main.map;

import java.util.ArrayList;

public class Spring extends ActiveElement {
    /**
     * controller hív
     */

    public Spring(){
        pipes = new ArrayList<>();
        players = new ArrayList<>();
    }

    @Override
    public void control() {
        giveWater();
        System.out.println("Új víz fakad a forrásból.");
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
}
