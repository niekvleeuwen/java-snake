/*
 * Author: Niek van Leeuwen 0967267
 * Date 14-05-2019
 */
public abstract class SnakeElement {
    private String name;

    SnakeElement(String name){
        this.name = name; //save the name in the field name
    }

    //this function is used to get the name of the Element
    public String getName(){
        return name;
    }
}
