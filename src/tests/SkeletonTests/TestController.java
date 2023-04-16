package tests.SkeletonTests;

import tests.SkeletonTests.PlayersMoveTests.MechanicMovesTest;
import tests.SkeletonTests.PlayersMoveTests.SaboteurMovesTest;
import tests.SkeletonTests.playerActionTests.*;

public class TestController {

    public static void runTests(){
        PlayerPickUpTest.mechanicPickUpOnCistern();
        PlayerPickUpTest.mechanicPickUpOnPump();
        PlayerPlaceTest.mechanicPlacesPipeOnPump();
        PlayerPlaceTest.mechanicPlacesPipeOnSpring();
        PlayerPlaceTest.mechanicPlacesPipeOnCistern();
        PlayerPlaceTest.mechanicPlacesPump();

        PlayerConfigurePumpTest.mechanicRotatePump();
        PlayerConfigurePumpTest.saboteurRotatePump();

        SaboteurBreaksTest.SaboteurBreaksPipe();
        MechanicRepairTest.MechanicRepairPump();
        MechanicRepairTest.MechanicRepairPipe();

        //Noel mozgás tesztek, ha a step meg lesz valósítva, működnek
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

}
