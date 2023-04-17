package tests.SkeletonTests.playerActionTests;

import main.players.Mechanic;
import main.players.Saboteur;
import main.players.MechanicTeam;
import main.players.SaboteurTeam;
public class PlayerTeamsTest {

    public static void addPlayerToMechanicTeam()
    {
        Mechanic mechanic1 = new Mechanic();
        Mechanic mechanic2 = new Mechanic();
        Mechanic mechanic3 = new Mechanic();
        MechanicTeam.getInstance().addPlayer(mechanic1);
        MechanicTeam.getInstance().addPlayer(mechanic2);
        MechanicTeam.getInstance().addPlayer(mechanic3);
    }

    public static void addPlayerToSaboteurTeam()
    {
        Saboteur saboteur1 = new Saboteur();
        Saboteur saboteur2 = new Saboteur();
        Saboteur saboteur3 = new Saboteur();
        SaboteurTeam.getInstance().addPlayer(saboteur1);
        SaboteurTeam.getInstance().addPlayer(saboteur2);
        SaboteurTeam.getInstance().addPlayer(saboteur3);
    }

    public static void addPointsToTeams()
    {
        MechanicTeam.getInstance().addPoints(10);
        MechanicTeam.getInstance().addPoints(10);
        SaboteurTeam.getInstance().addPoints(5);
        SaboteurTeam.getInstance().addPoints(40);
    }
}
