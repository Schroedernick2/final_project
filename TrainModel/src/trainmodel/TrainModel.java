package trainmodel;

import java.util.*;

public class TrainModel {
    public static void main(String[] args) {
        ArrayList<Train> trains = new ArrayList<Train>();
        
        trains.add(new Train("RED",1,1,100));
        trains.add(new Train("GREEN",1,1,50));
        trains.add(new Train("RED",2,1,75));
        trains.add(new Train("RED",3,1,24));

        java.awt.EventQueue.invokeLater(new Runnable() {
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
