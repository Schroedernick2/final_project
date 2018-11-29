package traincontroller;
import java.util.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Attr;
import java.io.File;
import java.io.FileWriter;


/**
 *
 * @author Jay Pitser
 */
public class TrainControllerGui extends javax.swing.JDialog {

    private int MULTIPLIER=1;
    private int selectedTrainIndex = 0;
    private ArrayList<Train> trains;
    /**
     * Creates new form TrainControllerGui
     */
    public TrainControllerGui(java.awt.Frame parent, boolean modal, ArrayList<Train> trains) {
        super(parent, modal);
        initComponents();
        
        try{
            FileWriter f1 = new FileWriter("./xml/traincontroller_trainmodel.xml",false);

            String content = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><Trains></Trains>";
            
            f1.write(content);
            
            f1.close();
        
        }catch(Exception e){
            System.out.println("File is a bitch");
        }
        
        this.trains = trains;
        Timer timer = new Timer();
        timer.schedule(new Progress(), 0,1000);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        CurrentTrainHeader = new javax.swing.JLabel();
        TrainSelector = new javax.swing.JComboBox<>();
        PropertiesHeader = new javax.swing.JLabel();
        CommandsHeader = new javax.swing.JLabel();
        AnnouncementsHeader = new javax.swing.JLabel();
        FailureModesHeader = new javax.swing.JLabel();
        AnnouncementsBox = new javax.swing.JScrollPane();
        AnnouncementsTextArea = new javax.swing.JTextArea();
        SuggestedSpeedLabel = new java.awt.Label();
        CurrentSpeedLabel = new java.awt.Label();
        AuthorityLabel = new java.awt.Label();
        KiLabel = new java.awt.Label();
        KpLabel = new java.awt.Label();
        CurrentStationHeader = new java.awt.Label();
        CurrentStationValue = new javax.swing.JLabel();
        ACLabel = new java.awt.Label();
        ACValue = new javax.swing.JSpinner();
        LeftDoorsLabel = new java.awt.Label();
        RightDoorsLabel = new java.awt.Label();
        InterioirLightsLabel = new java.awt.Label();
        HeadlightsLabel = new java.awt.Label();
        LeftDoorsValue = new javax.swing.JComboBox<>();
        RightDoorsValue = new javax.swing.JComboBox<>();
        InteriorLightsValue = new javax.swing.JComboBox<>();
        HeadlightsValue = new javax.swing.JComboBox<>();
        PowerLabel = new java.awt.Label();
        ServiceBrakeLabel = new java.awt.Label();
        EmergencyBrake = new javax.swing.JButton();
        EngineFailureLabel = new java.awt.Label();
        BrakeFailureLabel = new java.awt.Label();
        SignalFailureLabel = new java.awt.Label();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        CurrentTrainHeader.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        CurrentTrainHeader.setText("Current Train:");

        TrainSelector.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        TrainSelector.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "N/A" }));
        TrainSelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TrainSelectorActionPerformed(evt);
            }
        });

        PropertiesHeader.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        PropertiesHeader.setText("Properties");

        CommandsHeader.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        CommandsHeader.setText("Commands");

        AnnouncementsHeader.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        AnnouncementsHeader.setText("Announcements");

        FailureModesHeader.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        FailureModesHeader.setText("Failure Modes");

        AnnouncementsTextArea.setColumns(20);
        AnnouncementsTextArea.setRows(5);
        AnnouncementsBox.setViewportView(AnnouncementsTextArea);

        SuggestedSpeedLabel.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        SuggestedSpeedLabel.setText("Suggested Speed: ");

        CurrentSpeedLabel.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        CurrentSpeedLabel.setText("Current Speed: ");

        AuthorityLabel.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        AuthorityLabel.setText("Authority: ");

        KiLabel.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        KiLabel.setText("Ki: ");

        KpLabel.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        KpLabel.setText("Kp:");

        CurrentStationHeader.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        CurrentStationHeader.setText("Current Station:");

        CurrentStationValue.setText("Next Station");

        ACLabel.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        ACLabel.setText("A/C:");

        ACValue.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                ACValueStateChanged(evt);
            }
        });

        LeftDoorsLabel.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        LeftDoorsLabel.setText("Left Doors:");

        RightDoorsLabel.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        RightDoorsLabel.setText("Right Doors:");

        InterioirLightsLabel.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        InterioirLightsLabel.setText("Interioir Lights");

        HeadlightsLabel.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        HeadlightsLabel.setText("Headlights:");

        LeftDoorsValue.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Closed", "Open" }));
        LeftDoorsValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LeftDoorsValueActionPerformed(evt);
            }
        });

        RightDoorsValue.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Closed", "Open" }));
        RightDoorsValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RightDoorsValueActionPerformed(evt);
            }
        });

        InteriorLightsValue.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Off", "On" }));
        InteriorLightsValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InteriorLightsValueActionPerformed(evt);
            }
        });

        HeadlightsValue.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Off", "On" }));
        HeadlightsValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HeadlightsValueActionPerformed(evt);
            }
        });

        PowerLabel.setFont(new java.awt.Font("Dialog", 1, 21)); // NOI18N
        PowerLabel.setText("Power:");

        ServiceBrakeLabel.setFont(new java.awt.Font("Dialog", 1, 21)); // NOI18N
        ServiceBrakeLabel.setText("Service Break:");

        EmergencyBrake.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        EmergencyBrake.setForeground(new java.awt.Color(250, 0, 0));
        EmergencyBrake.setText("Emergency Brake");
        EmergencyBrake.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        EmergencyBrake.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmergencyBrakeActionPerformed(evt);
            }
        });

        EngineFailureLabel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        EngineFailureLabel.setText("ENGINE FAILURE");

        BrakeFailureLabel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        BrakeFailureLabel.setText("BRAKE FAILURE");

        SignalFailureLabel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        SignalFailureLabel.setText("SIGNAL FAILURE");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(PropertiesHeader)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(CommandsHeader)
                .addGap(278, 278, 278))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(246, 246, 246)
                        .addComponent(CurrentTrainHeader)
                        .addGap(18, 18, 18)
                        .addComponent(TrainSelector, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(AnnouncementsBox, javax.swing.GroupLayout.PREFERRED_SIZE, 909, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 28, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(KiLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CurrentSpeedLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SuggestedSpeedLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(AnnouncementsHeader))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(AuthorityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(KpLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ACLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ACValue, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(HeadlightsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(HeadlightsValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(InterioirLightsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(InteriorLightsValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(RightDoorsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LeftDoorsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LeftDoorsValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(RightDoorsValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(CurrentStationHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(CurrentStationValue, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(FailureModesHeader)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(EmergencyBrake, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(124, 124, 124))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(251, 251, 251)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(PowerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ServiceBrakeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(87, 87, 87)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(SignalFailureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(BrakeFailureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(EngineFailureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TrainSelector, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CurrentTrainHeader))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(PropertiesHeader))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(CommandsHeader)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SuggestedSpeedLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CurrentSpeedLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(PowerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AuthorityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ServiceBrakeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(KiLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(KpLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(CurrentStationHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(EmergencyBrake, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(CurrentStationValue)
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ACLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ACValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(FailureModesHeader)
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(LeftDoorsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(LeftDoorsValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(BrakeFailureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(RightDoorsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(RightDoorsValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(26, 26, 26)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(InterioirLightsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(InteriorLightsValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(14, 14, 14)
                                        .addComponent(HeadlightsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(HeadlightsValue, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                                .addComponent(AnnouncementsHeader)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(SignalFailureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(EngineFailureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(AnnouncementsBox, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        InterioirLightsLabel.getAccessibleContext().setAccessibleName("Interior Lights");
        InterioirLightsLabel.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    //*<?xml version="1.0" encoding="UTF-8" standalone="no"?><Trains>
    // <Train actualSpeed="0.0" ads="true" authority="0.0" currentPower="0.0" emergencyBrake="false" 
    // id="GREEN_1" leftDoor="false" lights="false" powerCmd="0.0" rightDoor="false" speed="0.0" temp="70"/>
    
    // <Train actualSpeed="0.0" ads="true" authority="0.0" currentPower="0.0" emergencyBrake="false" id="RED_1" 
    //leftDoor="false" lights="false" powerCmd="0.0" rightDoor="false" speed="0.0" temp="70"/></Trains>
      
    
    
    
    
    private void ACValueStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_ACValueStateChanged
        trains.get(selectedTrainIndex).setTemperature((int) ACValue.getValue());
        displayValues();
    }//GEN-LAST:event_ACValueStateChanged

    private void EmergencyBrakeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmergencyBrakeActionPerformed
        trains.get(selectedTrainIndex).setEmergencyBrake(EmergencyBrake.isSelected());
        
        //WRITE TO XML
        displayValues();
    }//GEN-LAST:event_EmergencyBrakeActionPerformed

    private void TrainSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TrainSelectorActionPerformed
        selectedTrainIndex = TrainSelector.getSelectedIndex();
        displayValues();
    }//GEN-LAST:event_TrainSelectorActionPerformed

    private void InteriorLightsValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InteriorLightsValueActionPerformed
        if (InteriorLightsValue.getSelectedIndex() == 0)
            trains.get(selectedTrainIndex).setLights(false);
        else if (InteriorLightsValue.getSelectedIndex() == 1)
            trains.get(selectedTrainIndex).setLights(true);
        else;
        displayValues();
    }//GEN-LAST:event_InteriorLightsValueActionPerformed

    private void HeadlightsValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HeadlightsValueActionPerformed
        if (HeadlightsValue.getSelectedIndex() == 0)
            trains.get(selectedTrainIndex).setHeadlights(false);
        else if (HeadlightsValue.getSelectedIndex() == 1)
            trains.get(selectedTrainIndex).setHeadlights(true);
        else;
        displayValues();
    }//GEN-LAST:event_HeadlightsValueActionPerformed

    private void LeftDoorsValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LeftDoorsValueActionPerformed
        if (LeftDoorsValue.getSelectedIndex() == 0)
            trains.get(selectedTrainIndex).setLeftDoors(false);
        else if (LeftDoorsValue.getSelectedIndex() == 1)
            trains.get(selectedTrainIndex).setLeftDoors(true);
        else;
        displayValues();
    }//GEN-LAST:event_LeftDoorsValueActionPerformed

    private void RightDoorsValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RightDoorsValueActionPerformed
        if (RightDoorsValue.getSelectedIndex() == 0)
            trains.get(selectedTrainIndex).setRightDoors(false);
        else if (RightDoorsValue.getSelectedIndex() == 1)
            trains.get(selectedTrainIndex).setRightDoors(true);
        else;
        displayValues();
    }//GEN-LAST:event_RightDoorsValueActionPerformed
      
    //XML format to use
    //*<?xml version="1.0" encoding="UTF-8" standalone="no"?><Trains>
    //      TRAIN OBJ Format
    // <Train actualSpeed="0.0" ads="true" authority="0.0" currentPower="0.0" emergencyBrake="false" 
    // id="GREEN_1" leftDoor="false" lights="false" powerCmd="0.0" rightDoor="false" speed="0.0" temp="70"/>
    
    private void talkToTrainModel() throws Exception{
        // WRITE Power, EmergencyBrake, other low priority values
        // READ for new Train, Vcmd, Vact, Authority, Station, Failures, ServiceBrake, EB
        File trainControllerXML = new File("./xml/traincontroller_trainmodel.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(trainControllerXML);
                        
        NodeList nList = doc.getElementsByTagName("Train");
        int numtrnz = trains.size();
        
        for(int i=0;i<nList.getLength();i++){
            Node nNode = nList.item(i);
            
            if(nNode.getNodeType() == Node.ELEMENT_NODE){
                Element eElement = (Element) nNode;
                
                String ID = eElement.getAttribute("id");
                ID = ID.replaceAll("\\s+","");
                
                double vcmd = Double.parseDouble(eElement.getAttribute("speed").replaceAll("\\s+",""));
                double vact = Double.parseDouble(eElement.getAttribute("currentVelocity").replaceAll("\\s+",""));
                boolean eb = Boolean.parseBoolean(eElement.getAttribute("emergencyBrake").replaceAll("\\s+",""));
                double auth = Double.parseDouble(eElement.getAttribute("authority").replaceAll("\\s+",""));
                boolean signalF = Boolean.parseBoolean(eElement.getAttribute("signalFailure").replaceAll("\\s+",""));
                boolean brakeF = Boolean.parseBoolean(eElement.getAttribute("brakeFailure").replaceAll("\\s+",""));
                boolean engineF = Boolean.parseBoolean(eElement.getAttribute("engineFailure").replaceAll("\\s+",""));
                String stationID = new String(eElement.getAttribute("station").replaceAll("\\s+",""));
                
                //Check for new train, aka when numtrains in XML is more than numtrains in arraylist
                if(numtrnz<(i+1)){
                    Train newT = new Train(ID, i);
                    addTrain(newT);
                }
                
                for(Train t : trains){
                    if(t.getTrainID().equals(ID)){
                        t.setSpeed(vcmd);
                        t.setActualSpeed(vact);
                        t.setAuthority(auth);
                        t.setEmergencyBrake(eb);
                        t.updatePower();
                        t.setBrakeFailure(brakeF);
                        t.setSignalFailure(signalF);
                        t.setEngineFailure(engineF);
                        t.setStation(stationID);
                        eElement.setAttribute("leftDoors",""+t.isLeftDoors());
                        eElement.setAttribute("rightDoors",""+t.isRightDoors());
                        eElement.setAttribute("lights",""+t.isHeadlights());
                        eElement.setAttribute("powerCmd",""+t.getPower());
                        eElement.setAttribute("temp",""+t.getTemperature());
                        eElement.setAttribute("ads",""+t.isAdvertisements());
                        //eElement.setAttribute("emergencyBrake",""+t.isEmergencyBrake());
                        //write -5 to power when This module sets EB, rather than 
                        //writing to emergencyBrake, which occurs when train model sets it
                    }
                }   
            }
            
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("./xml/traincontroller_trainmodel.xml"));
            t.transform(source,result);
        }        
        
    }
    
    
    
    
    
     private void displayValues(){
        if(trains.size()==0)return;
        Train t = trains.get(selectedTrainIndex);
        
        TrainSelector.setSelectedIndex(selectedTrainIndex);
        
        //checkboxes
        if(t.isBrakeFailure()) 
            BrakeFailureLabel.setText("BRAKE FAILURE:  Active");
        else BrakeFailureLabel.setText("BRAKE FAILURE:  OFF");
        if(t.isEngineFailure()) 
            EngineFailureLabel.setText("ENGINE FAILURE:  Active");
        else EngineFailureLabel.setText("ENGINE FAILURE:  OFF");
        if(t.isSignalFailure()) 
            SignalFailureLabel.setText("SIGNAL FAILURE:  Active");
        else SignalFailureLabel.setText("SIGNAL FAILURE:  OFF");
        AnnouncementsTextArea.setText(t.getAnnouncementsText());
        ACValue.setValue(t.getTemperature());
        EmergencyBrake.setSelected(t.isEmergencyBrake());
        //activeTimeLabel.setText("Time Active: "+ t.getTime() + " seconds");
        KiLabel.setText("Ki: "+ t.getKi());
        KpLabel.setText("Kp: "+ t.getKp());
        LeftDoorsValue.setSelectedIndex(t.isLeftDoors() ? 1 : 0 ); // true(open/on)=1, false(closed/off)=0
        RightDoorsValue.setSelectedIndex(t.isRightDoors() ? 1 : 0 );
        InteriorLightsValue.setSelectedIndex(t.isLights() ? 1 : 0);
        HeadlightsValue.setSelectedIndex(t.isHeadlights() ? 1 : 0);
        PowerLabel.setText("Power: " + t.getPower() +" kw");
        SuggestedSpeedLabel.setText("Suggested Speed: "+ t.getSuggestedSpeed() +" mph");
        CurrentSpeedLabel.setText("Current Speed: "+ t.getActualSpeed() +" mph");
        if(t.isServiceBrake()) 
            ServiceBrakeLabel.setText("Service Brake:  Active");
        else ServiceBrakeLabel.setText("Service Brake:  OFF");
        CurrentStationValue.setText( t.getStation().toUpperCase());
        AuthorityLabel.setText( "Authority: " + t.getAuthority() + " ft.");
        //advertisementsLabel.setText("Advertisements: "+ getStateString(t.isAdvertisements()));
        //lightsLabel.setText("Lights: "+ getStateString(t.isLights()));
        //activeTimeLabel.setText("Time Active: "+ t.getTime() + " seconds");

    }
     
     private String[] getIDs(){
        
        String[] trainIDs = new String[trains.size()];
        int i=0;
        for(Train t : trains){
            trainIDs[i] = t.getTrainID();
            i++;
        }
        return trainIDs;
    }
    
    private void addTrain(Train t){
        trains.add(t);
        String[] trainIDs = getIDs();
        this.TrainSelector.setModel(new javax.swing.DefaultComboBoxModel<>(trainIDs));
       //open dialog box for entering ki,kp
        displayValues();
        KvalueDialog Kdialog = new KvalueDialog(new javax.swing.JFrame(), true, t);
        Kdialog.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                Kdialog.bye();
            }
        });
        Kdialog.setVisible(true);
        Kdialog.main( null);
        
        // set Ki,Kp (pop up window)
        
    }
    
    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Label ACLabel;
    private javax.swing.JSpinner ACValue;
    private javax.swing.JScrollPane AnnouncementsBox;
    private javax.swing.JLabel AnnouncementsHeader;
    private javax.swing.JTextArea AnnouncementsTextArea;
    private java.awt.Label AuthorityLabel;
    private java.awt.Label BrakeFailureLabel;
    private javax.swing.JLabel CommandsHeader;
    private java.awt.Label CurrentSpeedLabel;
    private java.awt.Label CurrentStationHeader;
    private javax.swing.JLabel CurrentStationValue;
    private javax.swing.JLabel CurrentTrainHeader;
    private javax.swing.JButton EmergencyBrake;
    private java.awt.Label EngineFailureLabel;
    private javax.swing.JLabel FailureModesHeader;
    private java.awt.Label HeadlightsLabel;
    private javax.swing.JComboBox<String> HeadlightsValue;
    private java.awt.Label InterioirLightsLabel;
    private javax.swing.JComboBox<String> InteriorLightsValue;
    private java.awt.Label KiLabel;
    private java.awt.Label KpLabel;
    private java.awt.Label LeftDoorsLabel;
    private javax.swing.JComboBox<String> LeftDoorsValue;
    private java.awt.Label PowerLabel;
    private javax.swing.JLabel PropertiesHeader;
    private java.awt.Label RightDoorsLabel;
    private javax.swing.JComboBox<String> RightDoorsValue;
    private java.awt.Label ServiceBrakeLabel;
    private java.awt.Label SignalFailureLabel;
    private java.awt.Label SuggestedSpeedLabel;
    private javax.swing.JComboBox<String> TrainSelector;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

    
    
    private class Progress extends TimerTask{
        //private int runs=0;
        @Override
        public void run(){
            MULTIPLIER = 1;
            for(int i=0;i<MULTIPLIER;i++){
                if(trains.size()>=0){        //CHECK THIS BC size=0 at init, and need to call this to read 4 new train
                    try{
                        talkToTrainModel();
                    }catch(Exception e){
                        System.out.println("Train Model FUCKED!");
                    }
                }
                for(Train t : trains)
                    t.updatePower();
                displayValues(); 
            
            //runs++;
            }
        }
    }
    

}