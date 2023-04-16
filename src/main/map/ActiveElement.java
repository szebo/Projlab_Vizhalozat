package main.map;

import main.interfaces.IControllable;

import java.util.List;

public abstract class ActiveElement extends MapElement implements IControllable {
    protected List<Pipe> pipes;

    @Override
    public void setBroken(boolean value){
        super.setBroken(value);
    }

    @Override
    public void detachPipe(Pipe pipe)  {
        if(!pipe.isOccupied()) {
            this.pipes.remove(pipe);
            pipe.removeWater(pipe.water);
        }

    }
    @Override
    public void attachPipe(Pipe pipe){
        pipes.add(pipe);
        pipe.addElement(this);
    }

    /**
     * B Terv Vízmozgásra
     */
    public abstract void pumpWater();
}
