import javafx.geometry.Pos;
import java.awt.Graphics;

import java.awt.*;

public class Head extends Tile{
    private int width;
    private int height;
    Head(String name, Point point, int width, int height){
        super(name, point);
        this.width = width;
        this.height = height;
    }

    public Boolean checkForCollision(){
        return checkInsideWindow();
    }

    public boolean checkInsideWindow(){
        if (point.x > width || point.x < 0 || point.y > height || point.y < 0 ) {
            return false;
        }else{
            return true;
        }
    }

    public boolean checkForFruit(Point fruitPoint){
        /* Check for fruits. */
        if ((point.x == fruitPoint.x) && (point.y == fruitPoint.y)) {
            return true;
        }else{
            return false;
        }
    }

    public Point getPoint(){
        return point;
    }

    public void setPoint(Point point){
        this.point = point;
    }
}
