package main.map;

public class Spring extends ActiveElement {
    /**
     * controller hív
     */
    @Override
    public void control() {
        giveWater();
    }

    /**
     *  minden rákötött Pipe-ra a Spring vizet küld controller hívásra
     */
    public void giveWater(){
        for(Pipe p: pipes){
            p.addWater(p.capacity); //maxra tolja a csövet
        }
    }
}
