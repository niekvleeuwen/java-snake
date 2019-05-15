/*
 * Author: Niek van Leeuwen 0967267
 * Date 14-05-2019
 */
import java.awt.Color;
import java.awt.Label;
import java.awt.Font;
import java.awt.Point;
import java.awt.Container;

public class DisplayText extends ScreenElement{
    private Label label;
    DisplayText(String name, Point pos){
        super(name, pos); //pass the name to the parent class
        //make a label
        label = new Label();
        label.setForeground(Color.WHITE);
        label.setFont(new Font("SansSerif", Font.PLAIN, 15));
        label.setBounds(pos.x, pos.y, 400, 35);
    }

    //add the label to the given container
    public void setContainer(Container container) {
        container.add(label);
    }

    //set the text to the given string
    public void giveOutput(String string) {
        label.setText(string);
    }
}
