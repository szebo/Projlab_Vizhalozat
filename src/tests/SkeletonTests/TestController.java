package tests.SkeletonTests;

import main.map.*;
import main.players.Player;
import tests.SkeletonTests.playerMovementTests.MechanicMovesTest;
import tests.SkeletonTests.playerMovementTests.SaboteurMovesTest;
import tests.SkeletonTests.playerActionTests.*;
import tests.SkeletonTests.waterMovementTests.WaterMoveTest;

import java.util.Objects;

public class TestController {

    public static void runTests(){

        //Csapatkezelés tesztek
        PlayerTeamsTest.addPlayerToMechanicTeam();
        PlayerTeamsTest.addPlayerToSaboteurTeam();
        PlayerTeamsTest.addPointsToTeams();

        //Elem mozgatás tesztek
        PlayerPickUpTest.mechanicPickUpOnCistern();
        PlayerPickUpTest.mechanicPickUpOnPump();
        PlayerPlaceTest.mechanicPlacesPipeOnPump();
        PlayerPlaceTest.mechanicPlacesPipeOnSpring();
        PlayerPlaceTest.mechanicPlacesPipeOnCistern();
        PlayerPlaceTest.mechanicPlacesPump();

        //Pumpa beállítás tesztek
        PlayerConfigurePumpTest.mechanicRotatePump();
        PlayerConfigurePumpTest.saboteurRotatePump();

        //Akció tesztek
        PlayerBreaksTest.SaboteurBreaksPipe();
        MechanicRepairsTest.MechanicRepairPump();
        MechanicRepairsTest.MechanicRepairPipe();

        //Víz mozgatás tesztek.
        WaterMoveTest.waterBrokenPipeMoveTest();
        WaterMoveTest.waterNormalMoveTest();

        //Szerelő mozgatás tesztek.
        MechanicMovesTest.SpringToPipe();
        MechanicMovesTest.PipeToSpring();
        MechanicMovesTest.CisternToPipe();
        MechanicMovesTest.PipeToCistern();
        MechanicMovesTest.PumpToPipe();
        MechanicMovesTest.PipeToPump();
        MechanicMovesTest.SpringToOccupiedPipe();
        MechanicMovesTest.PumpToOccupiedPipe();
        MechanicMovesTest.CisternToOccupiedPipe();

        //Szabotőr mozgás tesztek.
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
        Objects.requireNonNull(player, "Null értékű paramétert kapott a testConfigurePump!");
        if(player.getMapElement() instanceof Pump place){
            place.setInput(p1);
            place.setOutput(p2);
        }
    }

}
