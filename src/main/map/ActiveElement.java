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
        if(false) {
        pipes.remove(pipe);
        //TODO exception vagy jelzés
        }

    }
    @Override
    public void attachPipe(Pipe pipe){
        if(!pipes.contains(pipe)){
            pipes.add(pipe);
        }
    }
}
