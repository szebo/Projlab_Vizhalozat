package main.players;

/**
 * Olyan játékos, amely feladata, hogy a csöveken és pumpákon haladva akadályozza a víz folyását.
 * Ezt a csövek kilyukasztásával teheti meg, illetve a pumpák forgatására is van lehetősége.
 * **/
public class Saboteur extends Player {
    /**
     * Eltöri azt az elemet amelyen áll a játékos.
     * **/
    public void breakElement(){
        mapElement.breakElement();
    }
}
