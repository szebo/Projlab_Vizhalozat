package main.map;

import main.interfaces.IControllable;

import java.util.List;

public abstract class ActiveElement extends MapElement implements IControllable {
    private List<Pipe> pipes;

    public void setBroken(boolean value){
        super.setBroken(value);
    }

    public void detachPipe(){
        return;
    }

    public void attachPipe(){
        return;
    }

}
