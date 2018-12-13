/**
 * @author Jay Pitser
 * for the Train Controller module of the Train Control System made by The Conductors
 * to be used by Port Authority of Allegheny County
 * 
 */
package traincontroller;
import java.util.*;

public class TrainController {
    public static void main(String[] args) {
        ArrayList<Train> trains = new ArrayList<Train>();
      
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TrainControllerGui dialog = new TrainControllerGui(new javax.swing.JFrame(), true, trains);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
}