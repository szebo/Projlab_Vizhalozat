package tests.SkeletonTests.playerActionTests;

import main.map.*;
import main.players.*;
import tests.SkeletonTests.TestController;

public class PlayerConfigurePumpTest {
    public static void mechanicRotatePump(){
        Mechanic m = new Mechanic();
        Pipe p1 = new Pipe();
        Pipe p2 = new Pipe();
        Pump p = new Pump();

        p.attachPipe(p1);
        p.attachPipe(p2);
        m.setMapElement(p);
        TestController.testConfigurePump(p1, p2, m);        //A fő dolog ennek a belsejében történik
    }

    public static void saboteurRotatePump(){
        Saboteur s = new Saboteur();
        Pipe p1 = new Pipe();
        Pipe p2 = new Pipe();
        Pump p = new Pump();

        s.setMapElement(p);
        p.attachPipe(p1);
        p.attachPipe(p2);
        TestController.testConfigurePump(p1, p2, s);        //A fő dolog ennek a belsejében történik
    }
}
