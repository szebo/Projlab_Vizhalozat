package main.players;

import main.logging.Logger;

import java.util.ArrayList;
import java.util.List;

public class MechanicTeam {
    private static MechanicTeam instance = null;

    public List<Mechanic> players = new ArrayList<>() ;
    private int counter = 0;
    private int points = 0;
    private MechanicTeam(){
        Logger.log("log.txt","Mechanic team created");
    }
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
            Logger.log("log.txt","Player added to MechanicTeam");
        }
        Logger.log("log.txt","Player could not be added to MechanicTeam, due to overpopulation");
    }

    public void addPoints(int point)
    {
        points+=point;
        Logger.log("log.txt","Points added, currently the Mechanics have "+ points +" points");
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
