package main.map;

import main.graphics.GUIManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Spring extends ActiveElement {

    private static int nextID = 1;

    /**
     * controller hív
     */

    public Spring(){
        pipes = new ArrayList<>();
        players = new ArrayList<>();
        this.ID = nextID++;
        //Logger.log("console.txt", "["+getLogID()+"]: created", true);
    }

    public Spring(int x, int y){
        pipes = new ArrayList<>();
        players = new ArrayList<>();
        this.ID = nextID++;
        GUIManager.getInstance().createSpringGUIObject(this, new Point(x, y));
        //Logger.log("console.txt", "["+getLogID()+"]: created", true);
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

    /**
     * Létrehoz egy stringet a forrás információival
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
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    public List<Pipe> getOutputPipes()
    {
        return pipes;
    }
    
    public static void resetAfterTest(){
        nextID = 1;
    }
}
