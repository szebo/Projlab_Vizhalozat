package main.players;

import main.map.Pump;
import java.util.ArrayList;
import java.util.List;

public class MechanicTeam {
    private static MechanicTeam instance = null;
    private final List<MechanicTeam> teamMembers = new ArrayList<MechanicTeam>() ;
    private MechanicTeam(){}
    public static synchronized MechanicTeam getInstance()
    {
        if (instance == null)
            instance = new MechanicTeam();
        return instance;
    }
}
