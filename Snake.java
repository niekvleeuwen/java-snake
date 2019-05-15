/*
 * Author: Niek van Leeuwen 0967267
 * Date 14-05-2019
 */

import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.Point;
import java.awt.Color;
import java.util.ArrayList;

public class Snake {
    private SnakeScreen snakeScreen;
    private DisplayText scoreText;
    private Color snake = Color.decode("#00FF00");
    private Color bgColor = Color.decode("#4c4c4c");
    private Timer timer;
    private ScreenButton test;

    private int score = 0;
    private final int interval = 100;
    private String input;

    Snake() {
        snakeScreen = new SnakeScreen(600, 600);
        JFrame f = new JFrame("Snake");
        f.setSize(600, 600);
        f.getContentPane().setBackground(bgColor);
        f.addWindowListener(new MyWindowAdapter(f));
        f.addKeyListener(snakeScreen);
        f.add(snakeScreen);
        f.setResizable(false);
        f.setVisible(true);

        scoreText = new DisplayText("Score", new Point(515,3));
        test = new ScreenButton("Test", new Point(100,100));

        timer = new Timer(interval, snakeScreen);
        timer.start();
    }

//    private void startGame(){
//        updateScore();
//    }
//
//    private void updateScore(){
//        addText(scoreText, "Score: " + score);
//    }
//
//    //this function is used to add text to the screen
//    private void addText(DisplayText displayText, String text){
//        displayText.giveOutput(text);
//    }

}
