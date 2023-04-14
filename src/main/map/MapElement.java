package main.map;

import main.players.Player;

import java.util.List;

public abstract class MapElement {

    //Attributes
    protected boolean isBroken;
    protected List<Player> playerList;

    //Abstract Methods
    public abstract void detachPipe(Pipe pipe);

    public abstract void attachPipe(Pipe pipe);

    public abstract void interact();

    public abstract int addWater(int water);

    public abstract int removeWater(int water);

    //Methods
    public void heal(){
        setBroken(false);
    }

    public void breakElement(){
        setBroken(true);
    }

    public void acceptPlayer(Player player){

    }

    public void removePlayer(Player player){

    }

    public void setBroken(boolean value){

    }

    public boolean isBroken(){
        return isBroken;
    }
}
