package trainmodel;

import java.util.*;

/*TrainModel
    Main frame of the class
    Train Module execution condensed to run from the main in this file
 */
public class TrainModel {

    //MAIN
    public static void main(String[] args) {
        //set a list of trains
        ArrayList<Train> trains = new ArrayList<Train>();

        java.awt.EventQueue.invokeLater(new Runnable() {
            //launch the train model gui
            public void run() {
                TrainModelGui dialog = new TrainModelGui(new javax.swing.JFrame(), true, trains);
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
