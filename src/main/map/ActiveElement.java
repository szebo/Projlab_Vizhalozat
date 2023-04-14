package main.map;

import main.interfaces.IControllable;

import java.util.List;

public abstract class ActiveElement extends MapElement implements IControllable {
    private List<Pipe> pipes;

    @Override
    public abstract void setBroken();

    public void detachPipe(){
        return;
    }

    public void attachPipe(){
        return;
    }

}
