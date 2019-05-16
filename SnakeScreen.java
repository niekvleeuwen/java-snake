/*
 * Author: Niek van Leeuwen 0967267
 * Date 14-05-2019
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class SnakeScreen extends JPanel implements KeyListener, ActionListener {
    private final int tileSize = 20;
    private final int tilesTotal = 900; // Total number of tiles
    private int[] xCoor = new int[tilesTotal];
    private int[] yCoor = new int[tilesTotal];
    private int width, height;
    private int snakeSize = 3; //beginnen met een snake van lengte 3
    private boolean inGame = true;
    private static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
    private int pressedKey = KeyEvent.VK_DOWN; //naar beneden gaan bij het opstarten
    private int direction = DOWN;
    private final int startSize = 3;
    private Point headStartPoint;
    private Color snakeColor = Color.decode("#00FF00");
    private Color bgColor = Color.decode("#808080");
    private Fruit fruit;
    private Head head;
    private ArrayList<Tile> snake = new ArrayList<>();

    SnakeScreen(int width, int height){
        this.height = height;
        this.width = width;
        setBackground(bgColor);

        headStartPoint = new Point(140, 140); //de begin coordinaten van de snake
        //voeg het hoofd toe aan de matrix
        xCoor[0] = headStartPoint.x;
        yCoor[0] = headStartPoint.y;

        fruit = new Fruit("Apple", new Point(0,0), tilesTotal);
        head = new Head("Hoofd", headStartPoint, width, height);
        snake.add(head);
        //maak de lichaamsdelen aan en voeg ze aan de matrix toe
        for(int i = 1; i < startSize; i++){
            Point point = new Point();
            point.y = headStartPoint.y - (i * 30);
            point.x = headStartPoint.x;
            Body body = new Body(Integer.toString(i), point);
            snake.add(body); //voeg het lichaamsdeel toe aan de snake
            xCoor[i] = point.x;
            yCoor[i] = point.y;
        }
        fruit.spawn();
    }

    public void actionPerformed(ActionEvent e) {
        checkTile();
        moveSnake();
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (inGame) {
            //teken het fruit
            g.setColor(Color.red);
            g.fillRect(fruit.getPoint().x, fruit.getPoint().y, tileSize, tileSize);

            //teken de snake
            g.setColor(snakeColor);
            for(Tile tile : snake){
                Point point = tile.getPoint();
                g.fillRect(point.x, point.y, tileSize, tileSize);
            }

            //teken de score
            g.setFont(new Font("Segoe UI", Font.BOLD, 20));
            g.drawString("Score: " + getScore(), 500, 30);
        } else {
            gameOver(g);
        }
    }

    private void checkTile(){
        //checken of de snake zich binnen het scherm bevindt
        if(!head.checkInsideWindow()){
            inGame = false;
            System.out.println("Game over");
        }

        //checken of de snake botst met zichzelf
        Point headPoint = head.getPoint();
        for(Tile tile : snake){
            Point point = tile.getPoint();
            if(point.x == headPoint.x && point.y == headPoint.y && !tile.getName().equals("Hoofd")){
                inGame = false;
            }
        }

        //checken of de snake botst met een stuk fruit
        if(head.checkForFruit(fruit.getPoint())){
            addBodyElement(); //voeg een element toe aan de snake
            fruit.spawn(); //plaats een nieuw fruit stukje
        }
    }

    private void addBodyElement(){
        Body body = new Body(""+snake.size()+1, new Point(10,10));
        snake.add(body);
    }

    private void moveSnake(){
        for (int i = (snake.size()-1); i > 0; i--) {
            xCoor[i] = xCoor[(i - 1)];
            yCoor[i] = yCoor[(i - 1)];
            snake.get(i).setPoint(new Point(xCoor[i], yCoor[i]));
        }

        Point newHead = head.getPoint();
        if(pressedKey == KeyEvent.VK_DOWN && direction != UP){
            direction = DOWN;
            newHead.y += tileSize;
        }else if(pressedKey == KeyEvent.VK_UP && direction != DOWN){
            direction = UP;
            newHead.y -= tileSize;
        }else if(pressedKey == KeyEvent.VK_LEFT && direction != RIGHT){
            direction = LEFT;
            newHead.x -= tileSize;
        }else if(pressedKey == KeyEvent.VK_RIGHT && direction != LEFT){
            direction = RIGHT;
            newHead.x += tileSize;
        }
        xCoor[0] = newHead.x;
        yCoor[0] = newHead.y;
        head.setPoint(newHead);

    }

    private int getScore(){
        return (snake.size() - 3);
    }

    private void gameOver(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Segoe UI", Font.BOLD, 20));
        g.drawString(("Game Over! Je score is " + (getScore())),
                width / 4, height / 2);
        g.drawString("Druk op de spatiebalk om opnieuw te spelen",
                width / 8, height / 2 + 30);

        //maak een nieuwe game aan wanneer de gebruiker op spatie klikt
        if (pressedKey == KeyEvent.VK_SPACE){
            inGame = true;
            pressedKey = KeyEvent.VK_DOWN;
            Snake s = new Snake();
        }
    }

    //sla de toets op
    public void keyPressed(KeyEvent e) {
        pressedKey = e.getKeyCode();
    }

    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}

}
