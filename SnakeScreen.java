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

public class SnakeScreen extends JPanel implements KeyListener, ActionListener {
    private final int tileSize = 20;
    private final int tilesTotal = 900; // Total number of tiles
    private int[] xCoor = new int[tilesTotal];
    private int[] yCoor = new int[tilesTotal];
    private int width, height;
    private int fruit_x, fruit_y;
    private int snakeSize = 3; //beginnen met een slang van lengte 3
    private boolean inGame = true;
    private static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
    private int pressedKey = KeyEvent.VK_DOWN; //naar beneden gaan bij het opstarten
    private int direction = DOWN;

    private Color snake = Color.decode("#00FF00");
    private Color bgColor = Color.decode("#808080");

    SnakeScreen(int width, int height){
        this.height = height;
        this.width = width;
        setBackground(bgColor);

        // Set snake starting coordinates.
        for(int i = 0; i < snakeSize; i++){
            yCoor[i] = 140 - (i * 30);
            xCoor[i] = 140;
        }

        spawnfruitCoor();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (inGame) {
            //teken het fruit
            g.setColor(Color.red);
            g.fillRect(fruit_x, fruit_y, tileSize, tileSize);

            //teken de slang
            g.setColor(snake);
            for (int i = 0; i < snakeSize; i++) {
                g.fillRect(xCoor[i], yCoor[i], tileSize, tileSize);
            }

            //teken de score
            g.setFont(new Font("Segoe UI", Font.BOLD, 20));
            g.drawString("Score: " + getScore(), 500, 30);

        } else {
            gameOver(g);
        }
    }

    public void actionPerformed(ActionEvent e) {
        checkTile();
        moveSnake();
        repaint();
    }

    //sla de toets op
    public void keyPressed(KeyEvent e) {
        pressedKey = e.getKeyCode();
    }

    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}

    private void checkTile(){
        /* Check if outside of wall. */
        if ( xCoor[0] > width || xCoor[0] < 0 || yCoor[0] > height || yCoor[0] < 0 ) {
            inGame = false;
        }

        /* Check for collisions. */
        for(int i = 1; i < xCoor.length; i++){
            if (xCoor[0] == xCoor[i] && yCoor[0] == yCoor[i]){
                inGame = false;
            }
        }

        /* Check for fruits. */
        if ((xCoor[0] == fruit_x) && (yCoor[0] == fruit_y)) {
            snakeSize++;
            spawnfruitCoor();
        }
    }

    /** Generates random coordinates for fruit. */
    private void spawnfruitCoor() {
        int r = (int) (Math.random() * Math.sqrt(tilesTotal) - 1);
        fruit_x = ((r * tileSize));

        r = (int) (Math.random() * Math.sqrt(tilesTotal) - 1);
        fruit_y = ((r * tileSize));
    }

    /** Simply prints a gameOver-message to screen when called. */
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

    private void moveSnake(){

        /* Move coordinates up one in the matrix.*/
        for (int i = snakeSize; i > 0; i--) {
            xCoor[i] = xCoor[(i - 1)];
            yCoor[i] = yCoor[(i - 1)];
        }

        if(pressedKey == KeyEvent.VK_DOWN && direction != UP){
            direction = DOWN;
            yCoor[0] += tileSize;
        }else if(pressedKey == KeyEvent.VK_UP && direction != DOWN){
            direction = UP;
            yCoor[0] -= tileSize;
        }else if(pressedKey == KeyEvent.VK_LEFT && direction != RIGHT){
            direction = LEFT;
            xCoor[0] -= tileSize;
        }else if(pressedKey == KeyEvent.VK_RIGHT && direction != LEFT){
            direction = RIGHT;
            xCoor[0] += tileSize;
        }
    }

    private String getScore(){
        return "" + (snakeSize - 3);
    }

}
