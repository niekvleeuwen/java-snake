/*
 * Author: Niek van Leeuwen 0967267
 * Date 27-03-2019
 */
import java.awt.Point;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ScreenButton extends ScreenElement implements InputDevice, ActionListener {
    private Button button;
    private Boolean inputAvailable = false;
    ScreenButton(String name, Point pos){
        super(name, pos); //pass the name and position to the parent class
        button = new Button(name);
        button.setBackground(Color.WHITE);
        button.setBounds(pos.x, pos.y, 10 + 15 * name.length(), 25);
        button.addActionListener(this);
    }

    public void actionPerformed(ActionEvent event){
        inputAvailable = true; //set to true if the button is pressed
    }

    public String getInput() {
        if(inputAvailable){ //check if the button is pressed
            inputAvailable = false;
            return super.getName(); //return the name of the button to make clear that this button is pressed
        }else{
            return null; //return null if no action is preformed
        }
    }

    //add the button to the given container
    public void setContainer(Container container) {
        container.add(button);
    }
}
