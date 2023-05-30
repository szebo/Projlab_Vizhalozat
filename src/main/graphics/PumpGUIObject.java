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

/**
 * A GUI objektum kattintásának működését megvalósító függvény
 * @param e A kattintás jellemzői
 * */
    @Override
    public void onClick(MouseEvent e) {
        if(rectangle.contains(e.getPoint())){
            Controller.SELECTED_ELEMENT = pump;
            System.out.println("Pump selected as selected item" + Controller.SELECTED_ELEMENT.getLogID());
            if(pump.getPlayers().contains(Controller.CURRENT_PLAYER)){
                String actionsString = "<html>configure";
                if(pump.isBroken()){actionsString = actionsString.concat("<br>heal");}
                if(pump.getNeighbours().length > 0){actionsString = actionsString.concat("<br>Pick up Pipe");}
                actionsString.concat("<br>Place pipe");
                actionsString.concat("</html>");
                GameView.actions.setText(actionsString);
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
        if (pump.getInput() != null) {
            g.setColor(Color.yellow);
            GUIObject GUIInputPipe = GUIManager.getInstance().getGUIObjectByID((pump.getInput().getLogID()));
            int pipeX = GUIInputPipe.getPosition().x;
            int pipeY = GUIInputPipe.getPosition().y;

            double angle = Math.atan2(pipeY  - position.y, pipeX - position.x);

            // Calculate the position of the new circle on the edge of the main circle
            int newX = (int) (position.x + RECTANGLE_SIZE/2 * Math.cos(angle) - RECTANGLE_SIZE/4);
            int newY = (int) (position.y + RECTANGLE_SIZE/2 * Math.sin(angle) - RECTANGLE_SIZE/4);

            // Draw the new circle
            g.setColor(Color.yellow);
            g.fillOval(newX, newY, RECTANGLE_SIZE/2, RECTANGLE_SIZE/2);
        }

        if (pump.getOutput() != null) {
            g.setColor(new Color(138, 43, 226));
            GUIObject GUIOutputPipe = GUIManager.getInstance().getGUIObjectByID((pump.getOutput().getLogID()));
            if(GUIOutputPipe == null || GUIOutputPipe.getPosition() == null) return;
            int pipeX = GUIOutputPipe.getPosition().x;
            int pipeY = GUIOutputPipe.getPosition().y;

            double angle = Math.atan2(pipeY - position.y, pipeX - position.x);

            // Calculate the position of the new circle on the edge of the main circle
            int newX = (int) (position.x + RECTANGLE_SIZE / 2 * Math.cos(angle) - RECTANGLE_SIZE / 4);
            int newY = (int) (position.y + RECTANGLE_SIZE / 2 * Math.sin(angle) - RECTANGLE_SIZE / 4);

            // Draw the new circle
            g.fillOval(newX, newY, RECTANGLE_SIZE / 2, RECTANGLE_SIZE / 2);
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
     * Beállítja a GUI elem pozícióját
     * @param p A Beállítandó pont
     * */
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
