package main.map;

import main.players.Mechanic;

import java.util.ArrayList;
import java.util.List;

/**
 * Reprezentálja a ciszternákat, és azok működését írja le.
 */
public class Cistern extends ActiveElement {

    /**
     * Egy lista a tartalék pumpákról amit felvehetnek a szerelők. Inicializálásál feltöltődik egyszer nagy számra.
     */
    private List<Pump> pumpsInReserve;

    /**
     * Cistern alapvető konstruktora
     */
    public Cistern(){
        pipes = new ArrayList<>();
        players = new ArrayList<>();
        pumpsInReserve = new ArrayList<>();
        for(int i = 0; i < 100; i++){
            pumpsInReserve.add(new Pump());
        }
    }

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
        mechanic.addPumpToInventory(pumpsInReserve.get(0));
        pumpsInReserve.remove(0);
        System.out.println("A pumpát odaadja a Ciszterna a Mechanicnak.");
    }

    /**
     * létrehoz egy új Pipe objektumot controller hívásra és hozzáköti a Cistern-hez
     */
    public void newPipe(){
        Pipe newpipe = new Pipe();
        this.attachPipe(newpipe);
    }

    /**
     * Vízet szív az összes hozzácsatlakozó csőből.
     */
    @Override
    public void pumpWater() {
        for(Pipe p : pipes){
            int taken = p.removeWater(p.water);
            //TODO Pontozas
        }
    }
}
