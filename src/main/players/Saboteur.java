package main.players;

/**
 * Olyan játékos, amely feladata, hogy a csöveken és pumpákon haladva akadályozza a víz folyását.
 * Ezt a csövek kilyukasztásával teheti meg, illetve a pumpák forgatására is van lehetősége.
 * **/
public class Saboteur extends Player {

    /**
     * Csúszóssá teszi a csövet, amin áll a játékos.
     */
    public void useSlipperyGoo(){
        mapElement.makeSlippery(2);
    }
}
