package tests.SkeletonTests;

import tests.SkeletonTests.playerActionTests.PlayerPickUpTest;
import tests.SkeletonTests.playerActionTests.PlayerPlaceTest;

public class TestController {

    public static void runTests(){
        PlayerPickUpTest.mechanicPickUpOnCistern();
        PlayerPickUpTest.mechanicPickUpOnPump();
        PlayerPlaceTest.mechanicPlacesPipeOnPump();
        PlayerPlaceTest.mechanicPlacesPipeOnSpring();
        PlayerPlaceTest.mechanicPlacesPipeOnCistern();
        PlayerPlaceTest.mechanicPlacesPump();
    }

}
