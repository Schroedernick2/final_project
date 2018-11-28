
// THS FILE IS OUTDATED, CHECK FOR A NEW ONE IN REPO


package tc;
import java.util.*;

public class TC {
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
