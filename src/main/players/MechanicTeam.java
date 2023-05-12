package main.players;

import java.util.ArrayList;
import java.util.List;

public class MechanicTeam {
    private static MechanicTeam instance = null;

    public List<Mechanic> players = new ArrayList<>() ;
    private int counter = 0;
    private int points = 0;
    private MechanicTeam(){System.out.println("Szerelő csapat létrehozva");}
    public static synchronized MechanicTeam getInstance()
    {
        if (instance == null)
            instance = new MechanicTeam();
        return instance;
    }
    public void addPlayer(Player player)
    {
        if(players.size() < 3) {
            players.add((Mechanic) player);
            System.out.println("Játékos hozzáadva a szabotőrőkhoz");
        }
        System.out.println("Játékost nem lehetett hozzáadni a csapathoz, túlnépesedés miatt.");
    }

    public void addPoints(int point)
    {
        points+=point;
        System.out.println("Pontok rögzítve, jelenleg "+ points +" pontja van a Szerelőknek.");
    }

    public Mechanic getMechanic()
    {
        counter++;
        return players.get(counter%2);
    }

    public boolean hasWon()
    {
        return points > 100; //mitoménmennnyilgyen
    }
}
