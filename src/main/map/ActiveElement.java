package main.map;

import main.interfaces.IControllable;

import java.util.List;

public abstract class ActiveElement extends MapElement implements IControllable {
   /**
    * Az aktív elemre felcsatolt csövek gyűjteménye
    **/
    protected List<Pipe> pipes;

    /**
     * Beállítja az isBroken értékét a paraméterül kapottra.
     * @param value
     */
    @Override
    public void setBroken(boolean value){
        super.setBroken(value);
    }


    /**
     * Lecsatolja “p” csövet az elemről, attól függetlenül, hogy ki-/bemeneti cső-e éppen.
     * Ha a cső foglalt, akkor nem lehet lecsatolni.
     * A csőben lévő víz ilyenkor kifolyik.
     * @param pipe A lecsatolandó cső
     * **/
    @Override
    public void detachPipe(Pipe pipe)  {
        if(!pipe.isOccupied()) {
            this.pipes.remove(pipe);
            pipe.removeWater(pipe.water);
        }
        System.out.println("Pumpáról cső lecsatolva.");
    }
    /**
     * Felcsatolja “p” csövet az aktív elemre.
     * @param pipe A felcsatolandó cső
    * **/
    @Override
    public void attachPipe(Pipe pipe){
        pipes.add(pipe);
        pipe.addElement(this);
        System.out.println("Pumpához cső hozzáadva");

    }

    /**
     * B Terv Vízmozgásra
     */
    public abstract void pumpWater();
}
