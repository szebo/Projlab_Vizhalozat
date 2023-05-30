package main.graphics;

import main.Controller;
import main.logging.Logger;
import main.map.MapElement;
import main.map.Pump;
import main.players.Player;
import main.windowing.GameView;

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
        rectangle = new Rectangle(position.x-RECTANGLE_MID_OFFSET, position.y-RECTANGLE_MID_OFFSET, RECTANGLE_SIZE, RECTANGLE_SIZE);
    }

    @Override
    public void onClick(MouseEvent e) {
        if(rectangle.contains(e.getPoint())){
            if(pump.getPlayers().contains(Controller.CURRENT_PLAYER)){
                String actionsString = "configure\n";
                if(pump.isBroken()){actionsString = actionsString.concat("heal\n");}
                if(pump.getNeighbours().length > 0){actionsString = actionsString.concat("Pick up Pipe\n");}
                GameView.actions.setText(actionsString);
            }
            else {
                if (Controller.CURRENT_PLAYER.getCurrentAction() == Player.Action.step && pump.acceptPlayer(Controller.CURRENT_PLAYER)){
                    GUIObject guiObject = GUIManager.getInstance().getGUIPlayerByID(Controller.CURRENT_PLAYER.getLogID());
                    if(guiObject != null) {
                        guiObject.setPosition(position);
                        GUIManager.getInstance().repaintGame();
                    }
                    else
                        Logger.log("log.txt", "Non-existing element given!", false);
                }
            }
        }
    }

    /**
     * A pumpa kirajzolását végző függvény
     * @param g kirajzoló grafika
     * */
    @Override
    public void draw(Graphics2D g){
        g.setColor(Color.BLACK);
        g.fillOval(rectangle.x, rectangle.y, RECTANGLE_SIZE, RECTANGLE_SIZE);
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

        if(pump.getInput() != null){
            GUIObject GUIInputPipe = GUIManager.getInstance().getGUIObjectByID(pump.getInput().getLogID());

            Point pipeCenter = GUIInputPipe.getPosition();
            double slope = (double) (pipeCenter.y - position.y) / (pipeCenter.x - position.x);
            double constant = position.y - slope * position.x;
            Point intersection = new Point((int) ((position.y - constant)/slope), (int)(slope * position.x + constant));

            g.setColor(Color.YELLOW);
            g.fillOval(intersection.x-5, intersection.y-5, 10, 10);
        }

        if(pump.getOutput() != null){
            GUIObject GUIOutputPipe = GUIManager.getInstance().getGUIObjectByID(pump.getOutput().getLogID());
            if(GUIOutputPipe == null) return;
            Point pipeCenter = GUIOutputPipe.getPosition();
            if(pipeCenter == null) return;
            double slope = (double) (pipeCenter.y - position.y) / (pipeCenter.x - position.x);
            double constant = position.y - slope * position.x;
            Point intersection = new Point((int) ((position.y - constant)/slope), (int)(slope * position.x + constant));

            g.setColor(new Color(138, 43, 226));
            g.fillOval(intersection.x-5, intersection.y-5, 10, 10);
        }
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


    /**
     * Visszaadja a GUI elem pozícióját
     * @return poisiton attribútum
     * */
    @Override
    public Point getPosition() {
        return position;
    }

    /**
     * @param p
     */
    @Override
    public void setPosition(Point p) {}

    @Override
    public MapElement getElement() {
        return pump;
    }
    @Override
    public Player getPlayer() {
        return null;
    }
}
