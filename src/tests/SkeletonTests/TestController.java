package tests.SkeletonTests;

import main.map.*;
import main.players.Player;
import tests.SkeletonTests.PlayersMoveTests.MechanicMovesTest;
import tests.SkeletonTests.PlayersMoveTests.SaboteurMovesTest;
import tests.SkeletonTests.playerActionTests.*;

public class TestController {

    public static void runTests(){

        PlayerTeamsTest.addPlayerToMechanicTeam();
        PlayerTeamsTest.addPlayerToSaboteurTeam();
        PlayerTeamsTest.addPointsToTeams();

        PlayerPickUpTest.mechanicPickUpOnCistern();
        PlayerPickUpTest.mechanicPickUpOnPump();
        PlayerPlaceTest.mechanicPlacesPipeOnPump();
        PlayerPlaceTest.mechanicPlacesPipeOnSpring();
        PlayerPlaceTest.mechanicPlacesPipeOnCistern();
        //PlayerPlaceTest.mechanicPlacesPump(); //TODO: NEM MŰKÖDIK

        PlayerConfigurePumpTest.mechanicRotatePump();
        PlayerConfigurePumpTest.saboteurRotatePump();

        SaboteurBreaksTest.SaboteurBreaksPipe();
        MechanicRepairTest.MechanicRepairPump();
        MechanicRepairTest.MechanicRepairPipe();

        //Noel mozgás tesztek, ha a step meg   lesz valósítva, működnek
        MechanicMovesTest.SpringToPipe();
        MechanicMovesTest.PipeToSpring();
        MechanicMovesTest.CisternToPipe();
        MechanicMovesTest.PipeToCistern();
        MechanicMovesTest.PumpToPipe();
        MechanicMovesTest.PipeToPump();
        MechanicMovesTest.SpringToOccupiedPipe();
        MechanicMovesTest.PumpToOccupiedPipe();
        MechanicMovesTest.CisternToOccupiedPipe();

        SaboteurMovesTest.SpringToPipe();
        SaboteurMovesTest.PipeToSpring();
        SaboteurMovesTest.CisternToPipe();
        SaboteurMovesTest.PipeToCistern();
        SaboteurMovesTest.PumpToPipe();
        SaboteurMovesTest.PipeToPump();
        SaboteurMovesTest.SpringToOccupiedPipe();
        SaboteurMovesTest.PumpToOccupiedPipe();
        SaboteurMovesTest.CisternToOccupiedPipe();
    }

    public static void testConfigurePump(Pipe p1, Pipe p2, Player player){
        if(player.getMapElement() instanceof Pump){
            Pump place = (Pump)player.getMapElement();
            place.setInput(p1);
            place.setOutput(p2);
        }
    }

}
