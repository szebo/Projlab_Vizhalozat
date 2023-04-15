package tests.SkeletonTests;

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
    }

}
