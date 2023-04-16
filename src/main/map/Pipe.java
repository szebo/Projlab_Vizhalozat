package main.map;

import main.players.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Egy olyan eleme a hálózatnak, amely a belevezetett vizet továbbítja a másik vége felé,
 * az ott elhelyezkedő pumpába. A játékosok képesek rajta állni és különböző akciókat is végrehajtani,
 * attól függően, milyen játékosról beszélünk.
 * Egyetlen játékos állhat csak rajta
 **/
public class Pipe extends MapElement {

    /**
     * A cső két végén elhelyezkedő pumpákat tároló lista
     * **/
    private List<ActiveElement> elements;

    /**
     * A cső osztály def. konstruktora.
     * Beállítja a kapacitást és az elemeket alapértelmezettre.
     * **/
    public Pipe(){
        this.capacity = 20;
        elements = new ArrayList<>();
        players = new ArrayList<>();
    }

    /**
     * A cső osztály konstruktora.
     * Beállítja a kapacitást a paraméterben kapottra és az elemeket alapértelmezettre.
     * @param capacity A beállítandó kapacitás
     * **/
    public Pipe(int capacity){
        this.capacity = capacity;
    }

    /** Eltöri a csövet, aminek következményében a víz elfolyik majd.
     * **/
    @Override
    public void breakElement(){
        setBroken(true);
        System.out.println("Elem elrontva!");
    }

    /**
     * Örökölt függvény, amit ez az osztály nem valósít meg
     * @param pipe
     */
    @Override
    public void detachPipe(Pipe pipe) {}

    /**
     * Örökölt függvény, amit ez az osztály nem valósít meg
     * @param pipe
     */
    @Override
    public boolean attachPipe(Pipe pipe) {return false;}

    /** Elfelezi a csövet, hogy be lehessen helyezni közéjük egy pumpát
     * @return A félbevágott két cső objektumot adjva vissza **/
    public void cut(Pump pumpPlaced){
        Pipe uj_pipe_1 = new Pipe();
        Pipe uj_pipe_2 = new Pipe();
        elements.get(0).detachPipe(this);
        elements.get(0).attachPipe(uj_pipe_1);
        elements.get(1).detachPipe(this);
        elements.get(1).attachPipe(uj_pipe_2);
        pumpPlaced.attachPipe(uj_pipe_1);
        pumpPlaced.attachPipe(uj_pipe_2);
    }

    /**
     * A csőre lépést engedélyező függvény, amely ellenőrzi, hogy szabad-e a játékosnak a csőre lépni.
     * Amennyiben igen, akkor fogadja a játékost.
     * @param player A csőre lépni kívánó játékos
     * **/
    @Override
    public boolean acceptPlayer(Player player){
        if(!this.isOccupied()) {
            addPlayer(player);
            return true;
        }
        return false;
    }

    /**
     * Hozzáad a cső egyik végéhez egy aktív elemet, amennyiben nincsen mindkét végéhez hozzárendelve aktív elem
     * @param element A csőhöz kapcsolni kívánt aktív elem
     * **/
    public void addElement(ActiveElement element){
        if(elements.size()<2) {
            this.elements.add(element);
            System.out.println("Elem hozzáadva a csőhöz");
        }
    }

    /**
     * Kivesz egy elemet az elements listából.
     * @param element
     */
    public void removeElement(ActiveElement element){
        elements.remove(element);
    }
}
