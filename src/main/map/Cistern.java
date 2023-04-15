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
        pumpsInReserve.add(new Pump());
    }

    /**
     * Átad egy pumpát a mechanicnak és kiveszi a listából.
     * @param mechanic a mechanic akinek átadjuk a pumpát
     */
    public void givePump(Mechanic mechanic){
        mechanic.addPumpToInventory(pumpsInReserve.get(pumpsInReserve.size()-1));
        pumpsInReserve.remove(pumpsInReserve.size()-1);
    }

    public void newPipe(){

    }


}
