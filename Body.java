import java.awt.*;

public class Body extends Tile{
    Body(String name, Point point){
        super(name, point);
    }

    public Point getPoint(){
        return point;
    }

    public void setPoint(Point point){
        this.point = point;
    }
}
