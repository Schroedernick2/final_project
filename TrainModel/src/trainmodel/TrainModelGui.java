package trainmodel;

import java.util.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
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

public class TrainModelGui extends javax.swing.JDialog {

    private int selectedTrainIndex = 0;
    private int MULTIPLIER = 1;
    private ArrayList<Train> trains;

    public TrainModelGui(java.awt.Frame parent, boolean modal,
            ArrayList<Train> trains) {
        super(parent, modal);
        initComponents();

        try {
            FileWriter f1 = new FileWriter("./xml/traincontroller_trainmodel.xml", false);
            FileWriter f2 = new FileWriter("./xml/trackmodel_trainmodel.xml", false);

            String content = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><Trains></Trains>";

            f1.write(content);
            f2.write(content);

            f1.close();
            f2.close();

        } catch (Exception e) {
            System.out.println("File error");
        }

        this.trains = trains;

        Timer timer = new Timer();
        timer.schedule(new Progress(), 0, 1000);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        forceLabel2 = new javax.swing.JLabel();
        trainSelector = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        emergencyBrakeBox = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        engineFailureBox = new javax.swing.JCheckBox();
        signalPickupFailureBox = new javax.swing.JCheckBox();
        brakeFailureBox = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        heightLabel = new javax.swing.JLabel();
        widthLabel = new javax.swing.JLabel();
        lengthLabel = new javax.swing.JLabel();
        massLabel = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        powerLabel = new javax.swing.JLabel();
        velocityLabel = new javax.swing.JLabel();
        accelerationLabel = new javax.swing.JLabel();
        serviceBrakeLabel = new javax.swing.JLabel();
        emergencyBrakeLabel = new javax.swing.JLabel();
        gradeLabel = new javax.swing.JLabel();
        elevationLabel = new javax.swing.JLabel();
        nextStationLabel = new javax.swing.JLabel();
        passengerCountLabel = new javax.swing.JLabel();
        crewCountLabel = new javax.swing.JLabel();
        leftDoorsLabel = new javax.swing.JLabel();
        rightDoorsLabel = new javax.swing.JLabel();
        temperatureLabel = new javax.swing.JLabel();
        advertisementsLabel = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        lightsLabel = new javax.swing.JLabel();
        activeTimeLabel = new javax.swing.JLabel();
        distanceLabel = new javax.swing.JLabel();

        forceLabel2.setFont(new java.awt.Font("Ubuntu", 0, 16)); // NOI18N
        forceLabel2.setText("Force: 0 N");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        trainSelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trainSelectorActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Input From Passengers");

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Train Selection");

        emergencyBrakeBox.setFont(new java.awt.Font("Ubuntu", 0, 16)); // NOI18N
        emergencyBrakeBox.setText("Emergency Brake");
        emergencyBrakeBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emergencyBrakeBoxActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Failure Modes");

        engineFailureBox.setFont(new java.awt.Font("Ubuntu", 0, 16)); // NOI18N
        engineFailureBox.setText("Engine Failure");
        engineFailureBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                engineFailureBoxActionPerformed(evt);
            }
        });

        signalPickupFailureBox.setFont(new java.awt.Font("Ubuntu", 0, 16)); // NOI18N
        signalPickupFailureBox.setText("Signal Pickup Failure");
        signalPickupFailureBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signalPickupFailureBoxActionPerformed(evt);
            }
        });

        brakeFailureBox.setFont(new java.awt.Font("Ubuntu", 0, 16)); // NOI18N
        brakeFailureBox.setText("Brake Failure");
        brakeFailureBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brakeFailureBoxActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Train Dimensions");

        heightLabel.setFont(new java.awt.Font("Ubuntu", 0, 16)); // NOI18N
        heightLabel.setText("Height: 0ft");

        widthLabel.setFont(new java.awt.Font("Ubuntu", 0, 16)); // NOI18N
        widthLabel.setText("Widtht: 0ft");

        lengthLabel.setFont(new java.awt.Font("Ubuntu", 0, 16)); // NOI18N
        lengthLabel.setText("Length: 0ft");

        massLabel.setFont(new java.awt.Font("Ubuntu", 0, 16)); // NOI18N
        massLabel.setText("Mass: 0lbs");

        jLabel9.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Current Train Info");

        powerLabel.setFont(new java.awt.Font("Ubuntu", 0, 16)); // NOI18N
        powerLabel.setText("Power: 0kW");

        velocityLabel.setFont(new java.awt.Font("Ubuntu", 0, 16)); // NOI18N
        velocityLabel.setText("Velocity: 0mph");

        accelerationLabel.setFont(new java.awt.Font("Ubuntu", 0, 16)); // NOI18N
        accelerationLabel.setText("Acceleration: 0");

        serviceBrakeLabel.setFont(new java.awt.Font("Ubuntu", 0, 16)); // NOI18N
        serviceBrakeLabel.setText("Service Brake: OFF");

        emergencyBrakeLabel.setFont(new java.awt.Font("Ubuntu", 0, 16)); // NOI18N
        emergencyBrakeLabel.setText("Emergency Brake: OFF");

        gradeLabel.setFont(new java.awt.Font("Ubuntu", 0, 16)); // NOI18N
        gradeLabel.setText("Grade: 0%");

        elevationLabel.setFont(new java.awt.Font("Ubuntu", 0, 16)); // NOI18N
        elevationLabel.setText("Elevation: 0ft");

        nextStationLabel.setFont(new java.awt.Font("Ubuntu", 0, 16)); // NOI18N
        nextStationLabel.setText("Next Station: DOWNTOWN");

        passengerCountLabel.setFont(new java.awt.Font("Ubuntu", 0, 16)); // NOI18N
        passengerCountLabel.setText("Passenger Count: 0");

        crewCountLabel.setFont(new java.awt.Font("Ubuntu", 0, 16)); // NOI18N
        crewCountLabel.setText("Crew Count: 0");

        leftDoorsLabel.setFont(new java.awt.Font("Ubuntu", 0, 16)); // NOI18N
        leftDoorsLabel.setText("Left Doors: CLOSED");

        rightDoorsLabel.setFont(new java.awt.Font("Ubuntu", 0, 16)); // NOI18N
        rightDoorsLabel.setText("Right Doors: CLOSED");

        temperatureLabel.setFont(new java.awt.Font("Ubuntu", 0, 16)); // NOI18N
        temperatureLabel.setText("Temperature: 70 F");

        advertisementsLabel.setFont(new java.awt.Font("Ubuntu", 0, 16)); // NOI18N
        advertisementsLabel.setText("Advertisements: ON ");

        jLabel24.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Train Model");

        lightsLabel.setFont(new java.awt.Font("Ubuntu", 0, 16)); // NOI18N
        lightsLabel.setText("Lights: OFF ");

        activeTimeLabel.setFont(new java.awt.Font("Ubuntu", 0, 16)); // NOI18N
        activeTimeLabel.setText("Time Active: 0 seconds");

        distanceLabel.setFont(new java.awt.Font("Ubuntu", 0, 16)); // NOI18N
        distanceLabel.setText("Distance Traveled: 0 miles");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(brakeFailureBox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(signalPickupFailureBox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(engineFailureBox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(trainSelector, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(emergencyBrakeBox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(0, 21, Short.MAX_VALUE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(heightLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(widthLabel, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lengthLabel, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(massLabel, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(velocityLabel)
                                    .addComponent(powerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(accelerationLabel)
                                    .addComponent(serviceBrakeLabel)
                                    .addComponent(emergencyBrakeLabel)
                                    .addComponent(gradeLabel)
                                    .addComponent(elevationLabel)
                                    .addComponent(nextStationLabel)
                                    .addComponent(passengerCountLabel)
                                    .addComponent(crewCountLabel)
                                    .addComponent(leftDoorsLabel)
                                    .addComponent(rightDoorsLabel)
                                    .addComponent(temperatureLabel)
                                    .addComponent(advertisementsLabel)
                                    .addComponent(lightsLabel)
                                    .addComponent(distanceLabel)
                                    .addComponent(activeTimeLabel))))
                        .addGap(43, 43, 43))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(trainSelector, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(emergencyBrakeBox, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(heightLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(widthLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lengthLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(massLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(engineFailureBox, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(signalPickupFailureBox, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(brakeFailureBox, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(activeTimeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(distanceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(powerLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(velocityLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(accelerationLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(serviceBrakeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(emergencyBrakeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gradeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(elevationLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nextStationLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(passengerCountLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(crewCountLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(leftDoorsLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rightDoorsLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(temperatureLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(advertisementsLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lightsLabel)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void brakeFailureBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brakeFailureBoxActionPerformed
        trains.get(selectedTrainIndex).setBrakeFailure(brakeFailureBox.isSelected());

        displayValues();
    }//GEN-LAST:event_brakeFailureBoxActionPerformed

    private void signalPickupFailureBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signalPickupFailureBoxActionPerformed
        trains.get(selectedTrainIndex).setSignalFailure(signalPickupFailureBox.isSelected());

        displayValues();
    }//GEN-LAST:event_signalPickupFailureBoxActionPerformed

    private void engineFailureBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_engineFailureBoxActionPerformed
        trains.get(selectedTrainIndex).setEngineFailure(engineFailureBox.isSelected());

        displayValues();
    }//GEN-LAST:event_engineFailureBoxActionPerformed

    private void emergencyBrakeBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emergencyBrakeBoxActionPerformed
        //System.out.println(emergencyBrakeBox.isSelected());
        //trains.get(selectedTrainIndex).setEmergencyBrake(emergencyBrakeBox.isSelected());
        trains.get(selectedTrainIndex).setPassengerPulled(emergencyBrakeBox.isSelected());
        displayValues();
    }//GEN-LAST:event_emergencyBrakeBoxActionPerformed

    private void trainSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trainSelectorActionPerformed
        selectedTrainIndex = trainSelector.getSelectedIndex();

        displayValues();
    }//GEN-LAST:event_trainSelectorActionPerformed

    private void displayValues() {
        if (trains.size() > 0) {
            Train t = trains.get(selectedTrainIndex);

            trainSelector.setSelectedIndex(selectedTrainIndex);

            //checkboxes
            brakeFailureBox.setSelected(t.isBrakeFailure());
            emergencyBrakeBox.setSelected(t.isEmergencyBrake());
            engineFailureBox.setSelected(t.isEngineFailure());
            signalPickupFailureBox.setSelected(t.isSignalFailure());

            heightLabel.setText("Height: " + t.getHeight() + " ft");
            widthLabel.setText("Width: " + t.getWidth() + " ft");
            lengthLabel.setText("Length: " + t.getLength() + " ft");
            massLabel.setText("Mass: " + t.getMass() + " lbs");

            distanceLabel.setText("Distance Traveled: "
                    + Math.round(t.getDistance() * 100.0) / 100.0 + " miles");
            powerLabel.setText("Power: " + t.getPower() + " kw");
            velocityLabel.setText("Velocity: " + t.getVelocity() + " mph");
            accelerationLabel.setText("Acceleration: " + t.getAcceleration()
                    + " ft/s^2");
            serviceBrakeLabel.setText("Service Brake: "
                    + getStateString(t.isServiceBrake()));
            gradeLabel.setText("Grade: " + t.getGrade() + "%");
            elevationLabel.setText("Elevation: " + t.getElevation() + " ft");
            nextStationLabel.setText("Next Station: "
                    + t.getStation().toUpperCase());
            leftDoorsLabel.setText("Left Doors: "
                    + getDoorString(t.isLeftDoors()));
            rightDoorsLabel.setText("Right Doors: "
                    + getDoorString(t.isRightDoors()));
            advertisementsLabel.setText("Advertisements: "
                    + getStateString(t.isAdvertisements()));
            lightsLabel.setText("Lights: " + getStateString(t.isLights()));
            passengerCountLabel.setText("Passenger Count: "
                    + t.getPassengerCount());
            crewCountLabel.setText("Crew Count: " + t.getCrewCount());
            temperatureLabel.setText("Temperature: " + t.getTemperature()
                    + " F");
            emergencyBrakeLabel.setText("Emergenecy Brake: "
                    + getStateString(t.isEmergencyBrake()));
            activeTimeLabel.setText("Time Active: " + t.getTime(MULTIPLIER)
                    + " seconds");
        }
    }

    private String getDoorString(boolean state) {
        if (state) {
            return "OPEN";
        }
        return "CLOSED";
    }

    private String getStateString(boolean state) {
        if (state) {
            return "ON";
        }
        return "OFF";
    }

    private String[] getIDs() {
        String[] trainIDs = new String[trains.size()];
        int i = 0;
        for (Train t : trains) {
            trainIDs[i] = t.getTrainID();
            i++;
        }
        return trainIDs;
    }

    private void addTrain(Train tr) throws Exception {
        trains.add(tr);
        String[] trainIDs = getIDs();
        trainSelector.setModel(new javax.swing.DefaultComboBoxModel<>(trainIDs));

        File tm_tcXML = new File("./xml/traincontroller_trainmodel.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(tm_tcXML);

        Element root = doc.getDocumentElement();

        Element newTrain = doc.createElement("Train");
        root.appendChild(newTrain);

        Attr id = doc.createAttribute("id");
        id.setValue(tr.getTrainID());
        newTrain.setAttributeNode(id);

        Attr speed = doc.createAttribute("speed");
        speed.setValue("" + tr.getSpeed());
        newTrain.setAttributeNode(speed);

        Attr speedLimit = doc.createAttribute("speedLimit");
        speedLimit.setValue("" + tr.getSpeedLimit());
        newTrain.setAttributeNode(speedLimit);

        Attr stationAuth = doc.createAttribute("stationAuthority");
        stationAuth.setValue("" + tr.getStationAuthority());
        newTrain.setAttributeNode(stationAuth);

        Attr scheduledStation = doc.createAttribute("scheduledStation");
        scheduledStation.setValue(tr.stops.get(tr.numberOfStops));
        newTrain.setAttributeNode(scheduledStation);

        Attr blockDistanceTraveled = doc.createAttribute("blockDistanceTraveled");
        blockDistanceTraveled.setValue("" + tr.getBlockDistanceTraveled() * 1760);
        newTrain.setAttributeNode(blockDistanceTraveled);

        Attr brakeFail = doc.createAttribute("brakeFailure");
        brakeFail.setValue("" + tr.isBrakeFailure());
        newTrain.setAttributeNode(brakeFail);

        Attr engFail = doc.createAttribute("engineFailure");
        engFail.setValue("" + tr.isEngineFailure());
        newTrain.setAttributeNode(engFail);

        Attr sigFail = doc.createAttribute("signalFailure");
        sigFail.setValue("" + tr.isSignalFailure());
        newTrain.setAttributeNode(sigFail);

        Attr station = doc.createAttribute("station");
        station.setValue("" + tr.getStation());
        newTrain.setAttributeNode(station);

        Attr authority = doc.createAttribute("authority");
        authority.setValue("" + tr.getAuthority());
        newTrain.setAttributeNode(authority);

        Attr Vactual = doc.createAttribute("actualSpeed");
        Vactual.setValue("" + tr.getVelocity());
        newTrain.setAttributeNode(Vactual);

        Attr powerCmd = doc.createAttribute("powerCmd");
        powerCmd.setValue("0.0");
        newTrain.setAttributeNode(powerCmd);

        Attr emergencyBrake = doc.createAttribute("emergencyBrake");
        emergencyBrake.setValue("" + tr.isEmergencyBrake());
        newTrain.setAttributeNode(emergencyBrake);

        Attr leftDoor = doc.createAttribute("leftDoor");
        leftDoor.setValue("" + tr.isLeftDoors());
        newTrain.setAttributeNode(leftDoor);

        Attr rightDoor = doc.createAttribute("rightDoor");
        rightDoor.setValue("" + tr.isRightDoors());
        newTrain.setAttributeNode(rightDoor);

        Attr temp = doc.createAttribute("temp");
        temp.setValue("" + tr.getTemperature());
        newTrain.setAttributeNode(temp);

        Attr lights = doc.createAttribute("lights");
        lights.setValue("" + tr.isLights());
        newTrain.setAttributeNode(lights);

        Attr ads = doc.createAttribute("ads");
        ads.setValue("" + tr.isAdvertisements());
        newTrain.setAttributeNode(ads);

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer t = tf.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(
                new File("./xml/traincontroller_trainmodel.xml"));
        t.transform(source, result);

        addTrainToTrackModelXML(tr);
    }

    private int getMultiplier() throws Exception {
        int m;

        Scanner infile = new Scanner(new File("./xml/multiplier.txt"));

        if (infile.hasNextLine()) {
            m = infile.nextInt();
        } else {
            m = 1;
        }

        return m;
    }

    private void talkToTrainController() throws Exception {
        File trainControllerXML = new File("./xml/traincontroller_trainmodel.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(trainControllerXML);

        NodeList nList = doc.getElementsByTagName("Train");

        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;

                String ID = eElement.getAttribute("id");
                ID = ID.replaceAll("\\s+", "");

                double p = Double.parseDouble(eElement.getAttribute("powerCmd").replaceAll("\\s+", ""));
                boolean lights = Boolean.parseBoolean(eElement.getAttribute("lights").replaceAll("\\s+", ""));
                boolean ads = Boolean.parseBoolean(eElement.getAttribute("ads").replaceAll("\\s+", ""));
                boolean leftDoors = Boolean.parseBoolean(eElement.getAttribute("leftDoor").replaceAll("\\s+", ""));
                boolean rightDoors = Boolean.parseBoolean(eElement.getAttribute("rightDoor").replaceAll("\\s+", ""));
                int temp = Integer.parseInt(eElement.getAttribute("temp").replaceAll("\\s+", ""));

                for (Train t : trains) {
                    if (t.getTrainID().equals(ID)) {
                        t.setPower(p);
                        t.setAdvertisements(ads);
                        t.setLights(lights);
                        t.setLeftDoors(leftDoors);
                        t.setRightDoors(rightDoors);
                        t.setTemperature(temp);

                        t.updateVelocity();

                        eElement.setAttribute("blockDistanceTraveled",
                                "" + t.getBlockDistanceTraveled());
                        eElement.setAttribute("scheduledStation",
                                "" + t.stops.get(t.numberOfStops));
                        eElement.setAttribute("station",
                                "" + t.getStation());
                        eElement.setAttribute("engineFailure",
                                "" + t.isEngineFailure());
                        eElement.setAttribute("brakeFailure",
                                "" + t.isBrakeFailure());
                        eElement.setAttribute("signalFailure",
                                "" + t.isSignalFailure());
                        eElement.setAttribute("speed", "" + t.getSpeed());
                        eElement.setAttribute("speedLimit",
                                "" + t.getSpeedLimit());
                        eElement.setAttribute("authority",
                                "" + t.getAuthority());
                        eElement.setAttribute("stationAuthority",
                                "" + t.getStationAuthority());
                        eElement.setAttribute("actualSpeed",
                                "" + t.getVelocity());
                        eElement.setAttribute("emergencyBrake",
                                "" + t.isEmergencyBrake());
                    }
                }
            }

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("./xml/traincontroller_trainmodel.xml"));
            t.transform(source, result);
        }
    }

    private void addTrainToTrackModelXML(Train tr) throws Exception {
        File tm_tcXML = new File("./xml/trackmodel_trainmodel.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(tm_tcXML);

        Element root = doc.getDocumentElement();

        Element newTrain = doc.createElement("Train");
        root.appendChild(newTrain);

        Attr id = doc.createAttribute("id");
        id.setValue(tr.getTrainID());
        newTrain.setAttributeNode(id);

        Attr speed = doc.createAttribute("speed");
        speed.setValue("" + tr.getSpeed());
        newTrain.setAttributeNode(speed);

        Attr stationAuthority = doc.createAttribute("stationAuthority");
        stationAuthority.setValue("0.0");
        newTrain.setAttributeNode(stationAuthority);

        Attr authority = doc.createAttribute("authority");
        authority.setValue("" + tr.getAuthority());
        newTrain.setAttributeNode(authority);

        Attr nextStation = doc.createAttribute("nextStation");
        nextStation.setValue("" + tr.getStation());
        newTrain.setAttributeNode(nextStation);

        Attr distance = doc.createAttribute("distanceTraveled");
        distance.setValue("" + tr.getDistance());
        newTrain.setAttributeNode(distance);

        Attr passengersAtStation = doc.createAttribute("passengersAtStation");
        passengersAtStation.setValue("0");
        newTrain.setAttributeNode(passengersAtStation);

        Attr elev = doc.createAttribute("elevation");
        elev.setValue("" + tr.getElevation());
        newTrain.setAttributeNode(elev);

        Attr grade = doc.createAttribute("grade");
        grade.setValue("" + tr.getGrade());
        newTrain.setAttributeNode(grade);

        Attr next = doc.createAttribute("next");
        next.setValue("1");
        newTrain.setAttributeNode(next);

        Attr length = doc.createAttribute("length");
        length.setValue("0.0");
        newTrain.setAttributeNode(length);

        Attr direction = doc.createAttribute("direction");
        direction.setValue("f");
        newTrain.setAttributeNode(direction);

        Attr tNum = doc.createAttribute("trackNumber");
        tNum.setValue("0");
        newTrain.setAttributeNode(tNum);

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer t = tf.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("./xml/trackmodel_trainmodel.xml"));
        t.transform(source, result);
    }

    private void talkToTrackModel() throws Exception {
        File trainControllerXML = new File("./xml/trackmodel_trainmodel.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(trainControllerXML);

        NodeList nList = doc.getElementsByTagName("Train");

        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;

                String ID = eElement.getAttribute("id");
                ID = ID.replaceAll("\\s+", "");

                double authority = Double.parseDouble(eElement.getAttribute("authority").replaceAll("\\s+", ""));
                double speed = Double.parseDouble(eElement.getAttribute("speed").replaceAll("\\s+", ""));
                double elevation = Double.parseDouble(eElement.getAttribute("elevation").replaceAll("\\s+", ""));
                double grade = Double.parseDouble(eElement.getAttribute("grade").replaceAll("\\s+", ""));
                String nextStation = eElement.getAttribute("nextStation").replaceAll("\\s+", "");
                double length = Double.parseDouble(eElement.getAttribute("length").replaceAll("\\s+", ""));
                int pass = Integer.parseInt(eElement.getAttribute("passengersAtStation").replaceAll("\\s+", ""));
                double stationAuthority = Double.parseDouble(eElement.getAttribute("stationAuthority").replaceAll("\\s+", ""));

                for (Train t : trains) {
                    if (t.getTrainID().equals(ID)) {
                        t.setAuthority(authority);
                        t.setSpeedLimit(speed);
                        t.setElevation(elevation);
                        t.setGrade(grade);
                        t.setStation(nextStation);
                        t.setBlockLength(length);
                        t.setStationAuthority(stationAuthority);

                        if (pass >= 222) {
                            t.setPassengerCount(222);
                        } else {
                            Random rand = new Random();
                            if (t.getPassengerCount() != 0) {
                                int n = rand.nextInt(t.getPassengerCount()) + 1;
                                if (((t.getPassengerCount() - n) + pass) <= 222) {
                                    t.setPassengerCount((t.getPassengerCount()
                                            - n) + pass);
                                } else {
                                    t.setPassengerCount(222);
                                }
                            } else {
                                t.setPassengerCount(pass);
                            }
                        }

                        t.updateVelocity();
                        eElement.setAttribute("distanceTraveled",
                                "" + t.getDistance());
                        eElement.setAttribute("next", "" + t.getNext());

                        if (t.stops.get(t.numberOfStops).toUpperCase().equals(nextStation.toUpperCase())) {
                            t.numberOfStops++;
                        }
                    }
                }
            }

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("./xml/trackmodel_trainmodel.xml"));
            t.transform(source, result);
        }
    }

    private void talkToCTC() throws Exception {
        File trainControllerXML = new File("./xml/TrainOutputs.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(trainControllerXML);

        NodeList nList = doc.getElementsByTagName("Dispatch");

        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;

                String created = eElement.getChildNodes().item(0).getNodeValue();
                created = created.replaceAll("\\s+", "");
                int t_num = Integer.parseInt(eElement.getAttribute("IDNum"));
                String line = eElement.getAttribute("Line");
                Double suggSpeed = Double.parseDouble(eElement.getAttribute("SpeedSugg"));

                if (created.equals("0")) {
                    ArrayList<String> stops = new ArrayList<String>();
                    NamedNodeMap m = eElement.getAttributes();
                    for (int l = 0; l < m.getLength(); l++) {
                        if (m.item(l).getNodeName().startsWith("Stop")) {
                            stops.add(m.item(l).getTextContent());
                        }
                    }

                    addTrain(new Train(line, t_num, 1, 0, stops, suggSpeed));
                    eElement.getChildNodes().item(0).setTextContent("1");
                }
            }

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("./xml/TrainOutputs.xml"));
            t.transform(source, result);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel accelerationLabel;
    public javax.swing.JLabel activeTimeLabel;
    public javax.swing.JLabel advertisementsLabel;
    public javax.swing.JCheckBox brakeFailureBox;
    public javax.swing.JLabel crewCountLabel;
    public javax.swing.JLabel distanceLabel;
    public javax.swing.JLabel elevationLabel;
    public javax.swing.JCheckBox emergencyBrakeBox;
    public javax.swing.JLabel emergencyBrakeLabel;
    public javax.swing.JCheckBox engineFailureBox;
    public javax.swing.JLabel forceLabel2;
    public javax.swing.JLabel gradeLabel;
    public javax.swing.JLabel heightLabel;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel24;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel9;
    public javax.swing.JLabel leftDoorsLabel;
    public javax.swing.JLabel lengthLabel;
    public javax.swing.JLabel lightsLabel;
    public javax.swing.JLabel massLabel;
    public javax.swing.JLabel nextStationLabel;
    public javax.swing.JLabel passengerCountLabel;
    public javax.swing.JLabel powerLabel;
    public javax.swing.JLabel rightDoorsLabel;
    public javax.swing.JLabel serviceBrakeLabel;
    public javax.swing.JCheckBox signalPickupFailureBox;
    public javax.swing.JLabel temperatureLabel;
    public javax.swing.JComboBox<String> trainSelector;
    public javax.swing.JLabel velocityLabel;
    public javax.swing.JLabel widthLabel;
    // End of variables declaration//GEN-END:variables

    private class Progress extends TimerTask {

        @Override
        public void run() {
            try {
                MULTIPLIER = getMultiplier();
            } catch (Exception e) {
                System.out.println("multiplier.txt not created yet, no biggie");
            }
            for (int i = 0; i < MULTIPLIER; i++) {
                try {
                    talkToCTC();
                } catch (Exception e) {
                    System.out.println("CTC big uh-oh");
                }
                if (trains.size() > 0) {
                    try {
                        talkToTrackModel();
                    } catch (Exception e) {
                        System.out.println("Track Model big uh-oh");
                        e.printStackTrace();
                    }
                    try {
                        talkToTrainController();
                    } catch (Exception e) {
                        System.out.println("Train Controller big uh-oh");
                        e.printStackTrace();
                    }
                }

                for (Train t : trains) {
                    t.updateVelocity();
                }
                displayValues();
            }
        }
    }
}
