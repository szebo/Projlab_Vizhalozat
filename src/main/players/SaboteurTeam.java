package main.players;

import java.util.ArrayList;
import java.util.List;

public class SaboteurTeam {
    private static SaboteurTeam instance = null;
    public List<Saboteur> players = new ArrayList<>() ;
    private int counter = 0;
    private int points = 0;
    private SaboteurTeam(){System.out.println("Szabotőr csapat létrehozva");}
    public static synchronized SaboteurTeam getInstance()
    {
        if (instance == null)
            instance = new SaboteurTeam();
        return instance;
    }
    public void addPlayer(Player player)
    {
        if(players.size() < 5){
            players.add((Saboteur)player);
        System.out.println("Játékos hozzáadva a szabotőrőkhoz");
        }
        System.out.println("Játékost nem lehetett hozzáadni a csapathoz, túl népesedés miatt.");
    }

    public void addPoints(int point)
    {
        points+=point;
        System.out.println("point added to mechanic team. current points: "+ points);
    }

    public Saboteur getSabotuer()
    {
        counter++;
        return players.get(counter%2);
    }

    public boolean hasWon()
    {
        return points > 50; //mitoménmennnyilgyen
    }
}
