package main.players;

import java.util.ArrayList;
import java.util.List;

public class SaboteurTeam {
    private static SaboteurTeam instance = null;
    public List<Saboteur> players = new ArrayList<Saboteur>() ;
    private int points = 0;
    private SaboteurTeam(){System.out.println("saboteur team created");}
    public static synchronized SaboteurTeam getInstance()
    {
        if (instance == null)
            instance = new SaboteurTeam();
        return instance;
    }
    public void addPlayer(Player player)
    {
        if(players.size() < 3){
            players.add((Saboteur)player);
        System.out.println("player added to saboteur team");
        }
        System.out.println("player NOT added to saboteur team, too many members");
    }

    public void addPoints(int point)
    {
        points+=point;
        System.out.println("point added to mechanic team. current points: "+ points);
    }
}
