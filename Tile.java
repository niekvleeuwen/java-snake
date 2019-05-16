/*
 * Author: Niek van Leeuwen 0967267
 * Date 14-05-2019
 */
import java.awt.*;

public abstract class Tile extends SnakeElement {
    Point point;
    Tile(String name, Point point){
        super(name); //pass the name and position to the parent class
        this.point = point;
    }

    public abstract Point getPoint();

    public abstract void setPoint(Point point);
}
