import java.awt.*;

public class Fruit extends Tile{
    private Point point;
    private int tilesTotal;
    Fruit(String name, Point point, int tilesTotal){
        super(name, point);
        this.tilesTotal = tilesTotal;
    }

    public void spawn(){
        point = randomPoint(tilesTotal); //geef het fruit een random plek in het window
    }

    public Point getPoint(){
        return point;
    }

    public void setPoint(Point point){
        this.point = point;
    }

    public Point randomPoint(int tilesTotal){
        Point point = new Point();
        int r = (int) (Math.random() * Math.sqrt(tilesTotal) - 1);
        System.out.println("X: " + r);
        point.x = ((r * 20));

        r = (int) (Math.random() * Math.sqrt(tilesTotal) - 1);
        System.out.println("Y: " + r);
        point.y = ((r * 20));
        return point;
    }
}
