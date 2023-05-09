package main.map;

import main.interfaces.Updatable;
import main.players.Player;
import main.players.SaboteurTeam;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Egy olyan eleme a hálózatnak, amely a belevezetett vizet továbbítja a másik vége felé,
 * az ott elhelyezkedő pumpába. A játékosok képesek rajta állni és különböző akciókat is végrehajtani,
 * attól függően, milyen játékosról beszélünk.
 * Egyetlen játékos állhat csak rajta
 **/
public class Pipe extends MapElement implements Updatable {

    private static int nextID = 0;
    private int unbreakableFor = 0;

    /**
     * Ennyi ?kör? ideig ragacsos a cső.
     */
    private int stickyFor = 0;

    /**
     * Ennyi ?kör? ideig csúszós a cső.
     */
    private int slipperyFor = 0;

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
        this.ID = nextID++;
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
        if(!this.isBroken()) {
            setBroken(true);
            System.out.println("Elem elrontva!");
        }
    }

    /**
     * Örökölt függvény, amit ez az osztály nem valósít meg
     * @param pipe not used
     */
    @Override
    public void detachPipe(Pipe pipe) {}

    /**
     * Örökölt függvény, amit ez az osztály nem valósít meg
     * @param pipe not used
     */
    @Override
    public boolean attachPipe(Pipe pipe) {return false;}

    /**
     * Elfelezi a csövet, hogy be lehessen helyezni közéjük egy pumpát
     * @param pumpPlaced A cső közepére elhelyezni kívánt pumpa
     */
    public void cut(Pump pumpPlaced){
        Pipe uj_pipe_1 = new Pipe();
        Pipe uj_pipe_2 = new Pipe();
        System.out.println("Új csövek létrehozva.");

        ActiveElement end1 = elements.get(0);
        ActiveElement end2 = null;
        if(elements.size() > 1) end2 =  elements.get(1);
        System.out.println("Eredeti cső vég elemei elmentve.");

        end1.detachPipe(this);
        end1.attachPipe(uj_pipe_1);
        if(end2 != null) {
            end2.detachPipe(this);
            end2.attachPipe(uj_pipe_2);
        }
        pumpPlaced.attachPipe(uj_pipe_1);
        pumpPlaced.attachPipe(uj_pipe_2);
        System.out.println("Új csövek csatlakoztatva.");
    }

    /**
     * A csőre lépést engedélyező függvény, amely ellenőrzi, hogy szabad-e a játékosnak a csőre lépni.
     * Amennyiben igen, akkor fogadja a játékost.
     * @param player A csőre lépni kívánó játékos
     * **/
    @Override
    public boolean acceptPlayer(Player player){
        boolean accepted = false;
        if(!this.isOccupied()) {
            if(checkSlippery()){
                player.step(getRandomEnd());
                accepted = false;
            }
            else if(checkSticky()){
                player.setStuck(stickyFor);
                addPlayer(player);
                accepted = true;
            }
        }
        return accepted;
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
     * @param element a kivenni való aktív elem
     */
    public void removeElement(ActiveElement element){
        elements.remove(element);
    }

    /**
     * Beállítja a ragacsosság értékét.
     * @param stickyFor mennyi ideig ragacsos a cső
     */
    public void setStickyFor(int stickyFor) {
        this.stickyFor = stickyFor;
    }

    /**
     * Ragacsossá teszi a csövet.
     */
    @Override
    public void makeSticky(int value){
        setStickyFor(value);
    }

    /**
     * Megvizsgálja, hogy ragacsos-e a cső.
     * @return true, ha a stickyFor értéke nagyobb mint 0.
     */
    @Override
    public boolean checkSticky(){return stickyFor > 0;}

    /**
     * Beállítja a csúszósság értékét.
     * @param slipperyFor mennyi ideig csúszós a cső
     */
    public void setSlipperyFor(int slipperyFor) {this.slipperyFor = slipperyFor;}

    /**
     * Csúszóssá teszi a csövet.
     */
    @Override
    public void makeSlippery(int value){
        setSlipperyFor(value);
    }

    /**
     * Megvizsgálja, hogy csúszós-e a cső.
     * @return true, ha a slipperyFor értéke nagyobb mint 0.
     */
    @Override
    public boolean checkSlippery() {
        return slipperyFor > 0;
    }


    public void setUnbreakableFor(){this.unbreakableFor = 2;}
    public boolean checkUnbreakableFor(){return unbreakableFor > 0;}



    /**
     * A csőnek véletlenszerűen sorsolja ki az egyik végét, és visszaadja azt.
     * @return ActiveElement: Véletlenszerűen visszakapott vége a csőnek.
     */
    @Override
    public ActiveElement getRandomEnd() {
        if(elements.size() == 1)
            return elements.get(0);
        Random rand = new Random();
        int randomInt = rand.nextInt(2);
        if(randomInt == 0)
            return elements.get(0);
        else
            return elements.get(1);
    }

    public String getLogID(){
        return "Pipe"+this.ID;
    }

    public void update(){
        if(this.slipperyFor!=0) this.slipperyFor -= 1;
        if(this.stickyFor!=0) this.stickyFor -= 1;
        if(this.unbreakableFor!=0) this.unbreakableFor -=1;
    }

    @Override
    public int addWater(int water){
        if(isBroken || elements.size() < 2){
            SaboteurTeam.getInstance().addPoints(water);
            return water;
        }
        else{
            return super.addWater(water);
        }
    }

    @Override
    public void heal(){
        setBroken(false);
        setUnbreakableFor();
    }

    @Override
    public MapElement[] getNeighbours(){
        return elements.toArray(new MapElement[2]);
    }
}
