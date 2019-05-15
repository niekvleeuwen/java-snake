/*
 * Author: Niek van Leeuwen 0967267
 * Date 14-05-2019
 */
import java.awt.*;

public abstract class Tile extends SnakeElement {
    Tile(String name, Point pos){
        super(name); //pass the name and position to the parent class
    }

    public abstract Graphics draw(Graphics g, Point point);
}
