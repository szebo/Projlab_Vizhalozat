package tests.SkeletonTests.waterMovementTests;

import main.map.*;
import main.players.MechanicTeam;
import main.players.SaboteurTeam;

public class WaterMoveTest {

    public static void waterNormalMoveTest(){
        System.out.println("A Víz Mozgatás Teszt megkezdése!\nInicializálás");
        Pump pump1 = new Pump();
        Cistern cistern1 = new Cistern();
        Spring spring1 = new Spring();
        Pipe pipe1 = new Pipe();
        Pipe pipe2 = new Pipe();

        cistern1.attachPipe(pipe1);
        pump1.attachPipe(pipe1);
        pump1.attachPipe(pipe2);
        spring1.attachPipe(pipe2);
        pump1.setInput(pipe2);
        pump1.setOutput(pipe1);

        Map map1 = new Map();
        map1.addActive(pump1);
        map1.addActive(cistern1);
        map1.addActive(spring1);

        map1.waterFlow(3);
        System.out.println("A tesztnek vége");
    }

    public static void waterBrokenPipeMoveTest(){
        System.out.println("A Víz Mozgatás Törött Csővel Teszt megkezdése!\nInicializálás");
        Pump pump1 = new Pump();
        Cistern cistern1 = new Cistern();
        Spring spring1 = new Spring();
        Pipe pipe1 = new Pipe();
        Pipe pipe2 = new Pipe();

        cistern1.attachPipe(pipe1);
        pump1.attachPipe(pipe1);
        pump1.attachPipe(pipe2);
        spring1.attachPipe(pipe2);
        pump1.setInput(pipe2);
        pump1.setOutput(pipe1);
        pipe1.breakElement();

        Map map1 = new Map();
        map1.addActive(pump1);
        map1.addActive(cistern1);
        map1.addActive(spring1);

        map1.waterFlow(3);
        System.out.println("A tesztnek vége");
    }

}
