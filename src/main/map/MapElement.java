package main.map;

import main.players.Player;

import java.util.List;

public abstract class MapElement {

    //Attributes
    protected boolean isBroken;
    protected List<Player> players;
    protected int water;

    //Abstract Methods
    public abstract void detachPipe(Pipe pipe);

    public abstract void attachPipe(Pipe pipe);

    public abstract void interact();

    //Methods
    public void heal(){
        setBroken(false);
    }

    public void breakElement(){
        setBroken(true);
    }

    public void acceptPlayer(Player player){
        addPlayer(player);
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public void removePlayer(Player player){
        players.remove(player);
    }

    public int addWater(int water){
        return 0;
    }

    public int removeWater(int water) {
        return 0;
    }

    public void setBroken(boolean value){
        isBroken = value;
    }

    public boolean isBroken(){
        return isBroken;
    }
}
