package trainmodel;

import java.util.*;

public class TrainModel {
    public static void main(String[] args) {
        ArrayList<Train> trains = new ArrayList<Train>();

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TrainModelGui dialog = new TrainModelGui(new javax.swing.JFrame(),true, trains);
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
