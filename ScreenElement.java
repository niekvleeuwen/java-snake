/*
 * Author: Niek van Leeuwen 0967267
 * Date 14-05-2019
 */
import java.awt.Point;
import java.awt.Container;

public abstract class ScreenElement extends SnakeElement {
    private Point pos;
    ScreenElement(String name, Point pos){
        super(name); //pass the name and position to the parent class
        this.pos = pos;
    }

    public abstract void setContainer(Container container);
}
