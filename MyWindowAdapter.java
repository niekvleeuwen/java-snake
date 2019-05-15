/*
 * Author: Niek van Leeuwen 0967267
 * Date 14-05-2019
 */
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
class MyWindowAdapter extends WindowAdapter {
    private Frame f;

    MyWindowAdapter(Frame f) {
        this.f = f;
    }

    public void windowClosing(WindowEvent e) {
        f.dispose();
        System.exit(0);
    }
}
