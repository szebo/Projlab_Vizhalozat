package main.players;

import main.map.Pump;
import java.util.ArrayList;
import java.util.List;

public class MechanicTeam {
    private static MechanicTeam instance = null;

    public List<Mechanic> players = new ArrayList<Mechanic>() ;
    private int points = 0;
    private MechanicTeam(){System.out.println("mechanic team created");}
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
            System.out.println("player added to mechanic team");
        }
        System.out.println("player NOT added to mechanic team, too many members");
    }

    public void addPoints(int point)
    {
        points+=point;
        System.out.println("point added to mechanic team. current points: "+ points);
    }
}
