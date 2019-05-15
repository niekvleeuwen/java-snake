import javafx.geometry.Pos;
import java.awt.Graphics;

import java.awt.*;

public class Head extends Tile{
    int tileSize = 20;
    int x = 10;
    int y = 10;
    Head(String name, Point point){
        super(name, point);
    }

    public Graphics draw(Graphics g, Point point){
        g.setColor(Color.red);
        g.fillRect(point.x, point.y, tileSize, tileSize);
        return g;
    }
}
