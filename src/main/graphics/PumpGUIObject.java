package main.graphics;

import main.Controller;
import main.logging.Logger;
import main.map.MapElement;
import main.map.Pump;
import main.players.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class PumpGUIObject extends GUIObject{

    private Pump pump;
    private Point position;

    /*Ez a négyzet kell a kattintás helyének ellenőrzésére */
    private Rectangle rectangle;

    public PumpGUIObject(Pump pump, Point point){
        position = point;
        this.pump = pump;
        rectangle = new Rectangle();
    }

    @Override
    public void onClick(MouseEvent e) {
        if(rectangle.contains(e.getPoint())){
            if(pump.getPlayers().contains(Controller.CURRENT_PLAYER)){
                ButtonGroup buttonGroup = new ButtonGroup();
                buttonGroup.add(new JButton("Configure"));
                //buttonGroup.
            }
            else {
                if (pump.acceptPlayer(Controller.CURRENT_PLAYER)){
                    GUIObject guiObject = GUIManager.getInstance().getGUIObjectByID(pump.getLogID());
                    if(guiObject != null)
                        position = GUIManager.getInstance().getGUIObjectByID(pump.getLogID()).getPosition();
                    else
                        Logger.log("log.txt", "Non-existing element given!", false);
                }
            }
        }
    }

    @Override
    public void draw(Graphics g){
        g.setColor(Color.BLACK);
        g.fillOval(position.x, position.y, 20, 20);
        if(this.pump.isBroken()) drawBroken(position, g);
        else drawWorking(position, g);
    }

    /**
     * A cső végét hozzácsatolja a GUIn a pumpához
     * @return a becsatlakozás pontja
     * */
    Point getAttachPoint(PipeGUIObject pipe){
        Point attachPoint = new Point();
       if( pipe.getPosition().x -this.position.x < 0){
           attachPoint.x = this.position.x - 1;
       } else {
           attachPoint.x = this.position.x + 1;

       }

        if( pipe.getPosition().y -this.position.y < 0){
            attachPoint.y = this.position.y - 1;
        } else {
            attachPoint.y = this.position.y + 1;
        }

        return attachPoint;
    }

    private void drawWorking(Point point, Graphics g){
        //Ide raktam egy ilyen kis zöld kört jelezvén hogy aktív a pumpa, de lehet faszság, és elég az input output
        g.setColor(Color.green);
        g.fillOval(position.x, position.y, 5, 5);

        /*if(pump.getInput() != null) {
            g.setColor(Color.yellow);
            GUIObject GUIInputPipe = GUIManager.getInstance().getGUIObjectByID(pump.getInput().getLogID());

            //TODO Kiszámolja a 2 pont közti különbséget, majd annak irányába megy a különbséggel arányosan. Csak sugárnyit kéne mozognia
            g.drawOval((GUIInputPipe.getPosition().x-this.position.x) + this.position.x ,(GUIInputPipe.getPosition().y-this.position.y) + this.position.x ,  5, 5 );

        }

        if(pump.getOutput() != null) {
            Color purple = new Color(102, 0, 153);
            g.setColor(purple);
            GUIObject GUIInputPipe = GUIManager.getInstance().getGUIObjectByID(pump.getInput().getLogID());

            //TODO Kiszámolja a 2 pont közti különbséget, majd annak irányába megy a különbséggel arányosan. Csak sugárnyit kéne mozognia
            g.drawOval((GUIInputPipe.getPosition().x-this.position.x) + this.position.x ,(GUIInputPipe.getPosition().y-this.position.y) + this.position.x ,  5, 5 );
        }*/
    }

    private void drawBroken(Point point, Graphics g){
        g.setColor(Color.red);

        // Convert angle from degrees to radians
        double angleRadians = Math.toRadians(45);

        //Getting the first line's two ends
        // Calculate the coordinate of the points of the first line
        int point1X = (int) (point.x + 10 * Math.cos(angleRadians));
        int point1Y = (int) (point.y + 10 * Math.sin(angleRadians));

        int point2X = (int) (point.x - 10 * Math.cos(angleRadians));
        int point2Y = (int) (point.y - 10 * Math.sin(angleRadians));

        //Getting the second line's two ends
        int point3X = (int) (point.x - 10 * Math.cos(angleRadians));
        int point3Y = (int) (point.y + 10 * Math.sin(angleRadians));

        int point4X = (int) (point.x + 10 * Math.cos(angleRadians));
        int point4Y = (int) (point.y - 10 * Math.sin(angleRadians));


        g.drawLine(point1X, point1Y, point2X, point2Y);
        g.drawLine(point3X, point3Y, point4X, point4Y);
    }



    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public MapElement getElement() {
        return pump;
    }
    @Override
    public Player getPlayer() {
        return null;
    }
}
