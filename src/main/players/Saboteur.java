package main.players;

import main.interfaces.IPipeAction;

/**
 * Olyan játékos, amely feladata, hogy a csöveken és pumpákon haladva akadályozza a víz folyását.
 * Ezt a csövek kilyukasztásával teheti meg, illetve a pumpák forgatására is van lehetősége.
 * **/
public class Saboteur extends Player implements IPipeAction {

    /**
     * Eltöri azt az elemet amelyen áll a játékos.
     */
    @Override
    public void breakElement(){
        mapElement.breakElement();
    }

    /**
     * Ragacsossá teszi a csövet, amin áll a játékos.
     */
    @Override
    public void useStickyGoo() {
        mapElement.makeSticky(10);
    }
}
