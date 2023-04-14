package tests.SkeletonTests;

import tests.SkeletonTests.playerActionTests.PlayerPickUpTest;
import tests.SkeletonTests.playerActionTests.PlayerPlaceTest;
import tests.SkeletonTests.playerActionTests.PlayerConfigurePumpTest;

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
    }

}
