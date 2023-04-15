package main.map;

import main.players.Mechanic;

import java.util.List;

public class Cistern extends ActiveElement {

    private List<Pump> pumpsInReserve;

    /**
     *  létrehoz egy új pumpát és eltárolja a cisternen.
     */
    @Override
    public void control() {
        newPipe();
        System.out.println("Új cső készült el a Ciszternánál.");
    }

    /**
     * Átad egy pumpát a mechanicnak és kiveszi a listából.
     * @param mechanic a mechanic akinek átadjuk a pumpát
     */
    public void givePump(Mechanic mechanic){
        mechanic.addPumpToInventory(pumpsInReserve.get(pumpsInReserve.size()-1));
        pumpsInReserve.remove(pumpsInReserve.size()-1);
        System.out.println("A pumpát odaadja a Ciszterna a Mechanicnak.");
    }

    /**
     * létrehoz egy új Pipe objektumot controller hívásra és hozzáköti a Cistern-hez
     */
    public void newPipe(){
        Pipe newpipe = new Pipe();
        this.attachPipe(newpipe);
    }


}
