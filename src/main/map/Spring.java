package main.map;

public class Spring extends ActiveElement {
    /**
     * controller hív
     */
    @Override
    public void control() {
        giveWater();
        System.out.println("Új víz fakad a forrásból.");
    }

    /**
     *  minden rákötött Pipe-ra a Spring vizet küld controller hívásra
     *  a vizet maximális kapacitással tolja ki
     */
    public void giveWater(){
        for(Pipe p: pipes){
            p.addWater(p.capacity);
        }
    }
}
