/*
* William Taylor
* Software Engineering Fall 2018 
* Professor: Dr. Profeta
* 
* CTCModule.java
* 
* Version Final : For use with The Conductors files
* This module runs the CTC Office for the train project
* 
* No Copyright
*
*/

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.List;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ImageHyperlink;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import java.io.File;
import javax.swing.JFileChooser;
import java.util.*;
import java.io.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.eclipse.swt.widgets.TableItem;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CTCModule {

    protected Shell shlTrackModel;
    private final FormToolkit formToolkit = new FormToolkit(
            Display.getDefault());
    
    /* These arraylists are mostly used to store info about all sections of track or trains*/
    static ArrayList<String> redSection1List = new ArrayList<String>();
    static ArrayList<String> redSection2List = new ArrayList<String>();
    static ArrayList<String> redSection3List = new ArrayList<String>();
    static ArrayList<String> redSection4List = new ArrayList<String>();
    static ArrayList<String> redSection5List = new ArrayList<String>();
    static ArrayList<String> redSection6List = new ArrayList<String>();
    static ArrayList<String> redSection7List = new ArrayList<String>();
    static ArrayList<String> redSection8List = new ArrayList<String>();
    static ArrayList<String> redOccupations = new ArrayList<String>();
    static ArrayList<String> greenOccupations = new ArrayList<String>();
    static ArrayList<String> crossingsArrayList = new ArrayList<String>();
    static ArrayList<String> signalsArrayList = new ArrayList<String>();
    static ArrayList<String> switchesArrayList = new ArrayList<String>();
    static ArrayList<String> greenSectionsArrayList = new ArrayList<String>();
    static ArrayList<String> redSectionsArrayList = new ArrayList<String>();
    static ArrayList<String> greenStops = new ArrayList<String>();
    static ArrayList<String> redStops = new ArrayList<String>();
    static ArrayList<String> greenSchedule = new ArrayList<String>();
    static ArrayList<String> redSchedule = new ArrayList<String>();
    static ArrayList<Date> requestTimes = new ArrayList<Date>();
    static ArrayList<String> trainsAlreadySent = new ArrayList<String>();

    /* These hashmaps are mostly used to convert raw data to displayable data */
    static HashMap<String, String> crossStates = new HashMap<String, String>();
    static HashMap<String, String> signalStates = new HashMap<String, String>();
    static HashMap<String, String> switchStates = new HashMap<String, String>();
    static HashMap<String, String> greenSectionStates = new HashMap<String, String>();
    static HashMap<String, String> redSectionStates = new HashMap<String, String>();
    static HashMap<String, String> trackConvert = new HashMap<String, String>();
    static HashMap<String, String> switchConversion = new HashMap<String, String>();
    static HashMap<String, String> crossingConversionForSend = new HashMap<String, String>();
    static HashMap<String, String> lineForTrainToSend = new HashMap<String, String>();
    static HashMap<String, String> scheduleForTrainToSend = new HashMap<String, String>();
    static HashMap<String, String> outTrainsToColors = new HashMap<String, String>();

    /* These are different counter variables to track where we've
     * Been in the program, or count different things that have happened */
    static int iii = 0;
    static int firstTimeThrough = 1;
    static int test = 0;
    static int automatic = 0;
    static int newProgram = 1;
    static int pastInitital = 0;
    static int totalSecondsToAdd = 0;
    static int timeMultiplierInt = 1;
    static int numberOfLastTrainSent = 0;
    static int currentIndexSchedule = 0;
    
    static List crossStatesList;
    static List switchStatesList;
    static List signalStatesList;
    static List redTrackStatesList;
    static List greenTrackStatesList;
    
    static Label lblSchedule;
    static Label lblHhmmss;
    static Label throughputText;
    static Label lblPassengers;

    static long startCycleTime = 999999999;
    static long differenceClock = 0;
    static long START_CLOCK_TIME = System.currentTimeMillis();
    static long lastUpdateLists = 0;
    static long lastUpdateThroughput=0;
    
    
    private Text suggSpeedGreen;
    private Text suggSpeedRed;
    private Text blockToCloseText;

    static Date lastRefresh;
    static Date LastroutingCheck;
    static Date dateLastUpdate;
    
    
    static Combo autoChoiceCombo;
    static Combo scheduleToPick;
    
    static String throughputFromXML = "X";

    static Document doc; static List infrList;

    private Table trainsTable;
    
    
    /**
     * Launch the application.
     * 
     * @param args
     */
    public static void main(String[] args) {
        crossingConversionForSend.put("G:E", "G1");
        crossingConversionForSend.put("R:I", "R1");

        switchConversion.put("R:CYd", "R1");
        switchConversion.put("R:E", "R2");
        switchConversion.put("R:TH", "R3");
        switchConversion.put("R:RH", "R4");
        switchConversion.put("R:QH", "R5");
        switchConversion.put("R:OH", "R6");
        switchConversion.put("R:J", "R7");
        switchConversion.put("G:C", "G1");
        switchConversion.put("G:G", "G2");
        switchConversion.put("G:JToYd", "G3");
        switchConversion.put("G:JFmYd", "G4");
        switchConversion.put("G:M", "G5");
        switchConversion.put("G:O", "G6");

        trackConvert.put("Red:A", "R1");
        trackConvert.put("Red:B", "R2");
        trackConvert.put("Red:C", "R3");
        trackConvert.put("Red:D", "R4");
        trackConvert.put("Red:E", "R5");
        trackConvert.put("Red:F", "R6");
        trackConvert.put("Red:G", "R7");
        trackConvert.put("Red:H", "R8");
        trackConvert.put("Red:I", "R9");
        trackConvert.put("Red:J", "R10");
        trackConvert.put("Red:K", "R11");
        trackConvert.put("Red:L", "R12");
        trackConvert.put("Red:M", "R13");
        trackConvert.put("Red:N", "R14");
        trackConvert.put("Red:O", "R15");
        trackConvert.put("Red:P", "R16");
        trackConvert.put("Red:Q", "R17");
        trackConvert.put("Red:R", "R18");
        trackConvert.put("Red:S", "R19");
        trackConvert.put("Red:T", "R20");

        trackConvert.put("Green:A", "G1");
        trackConvert.put("Green:B", "G2");
        trackConvert.put("Green:C", "G3");
        trackConvert.put("Green:D", "G4");
        trackConvert.put("Green:E", "G5");
        trackConvert.put("Green:F", "G6");
        trackConvert.put("Green:G", "G7");
        trackConvert.put("Green:H", "G8");
        trackConvert.put("Green:I", "G9");
        trackConvert.put("Green:J", "G10");
        trackConvert.put("Green:K", "G11");
        trackConvert.put("Green:L", "G12");
        trackConvert.put("Green:M", "G13");
        trackConvert.put("Green:N", "G14");
        trackConvert.put("Green:O", "G15");
        trackConvert.put("Green:P", "G16");
        trackConvert.put("Green:Q", "G17");
        trackConvert.put("Green:R", "G18");
        trackConvert.put("Green:S", "G19");
        trackConvert.put("Green:T", "G20");
        trackConvert.put("Green:U", "G21");
        trackConvert.put("Green:V", "G22");
        trackConvert.put("Green:W", "G23");
        trackConvert.put("Green:X", "G24");
        trackConvert.put("Green:Y", "G25");
        trackConvert.put("Green:Z", "G26");

        redSectionsArrayList.addAll(
                Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                        "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T"));
        greenSectionsArrayList.addAll(Arrays.asList("A", "B", "C", "D", "E",
                "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
                "S", "T", "U", "V", "W", "X", "Y", "Z"));

        try {
            CTCModule window = new CTCModule();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Open the window.
     */
    public void open() {
        Display display = Display.getDefault();

        if (pastInitital == 0) {
            createContents();
            updateLists();
        }

        shlTrackModel.open();
        shlTrackModel.layout();
        long NowClockTime = System.currentTimeMillis();

        Calendar calInitial = Calendar.getInstance();
        dateLastUpdate = calInitial.getTime();

        while (!shlTrackModel.isDisposed()) {

            updateXMLInput();

            NowClockTime = System.currentTimeMillis();
            differenceClock = ((NowClockTime - START_CLOCK_TIME) / 1000)
                    * timeMultiplierInt;

            Calendar cal = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            cal.add(Calendar.SECOND, totalSecondsToAdd);
            cal2.add(Calendar.SECOND, totalSecondsToAdd);
            Date date = cal.getTime();
            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            String formattedDate = dateFormat.format(date);

            if (!lblHhmmss.getText().equals(formattedDate)) {
                totalSecondsToAdd = totalSecondsToAdd + timeMultiplierInt - 1;
                cal = Calendar.getInstance();
                cal.add(Calendar.SECOND, totalSecondsToAdd);
                cal2.add(Calendar.SECOND, totalSecondsToAdd);
                date = cal.getTime();
                dateFormat = new SimpleDateFormat("HH:mm:ss");
                formattedDate = dateFormat.format(date);

            }

            lblHhmmss.setText(formattedDate);
            

            if (!throughputFromXML.equals("X")) {
                double elapsedForCalcTime = System.currentTimeMillis()
                        + (totalSecondsToAdd * 1000);
                elapsedForCalcTime = elapsedForCalcTime
                        - (double) START_CLOCK_TIME;
                elapsedForCalcTime = (elapsedForCalcTime / 1000) / (60 * 60);
                double throughputDoub = Double.parseDouble(throughputFromXML);

                if (elapsedForCalcTime != (double) 0) {
                    long tempTime = System.currentTimeMillis();
                    double throughputForTime = throughputDoub
                            / elapsedForCalcTime;
                    int throughRounded = (int) throughputForTime;
                    String afterConversionRounded = Integer.toString(throughRounded);
                    if ((tempTime-lastUpdateThroughput) > 1000)
                    {
                        throughputText.setText(afterConversionRounded);
                        lastUpdateThroughput = System.currentTimeMillis();
                    }
                    // throughputText.setText(throughputFromXML);
                }else {
                    throughputText.setText(throughputFromXML);
                }
            }else {
                throughputText.setText(throughputFromXML);
            }

            try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            if ((automatic == 1) && (infrList.getItemCount() != 0)) {
                long elapsedTime = System.currentTimeMillis() - startCycleTime;
                long elapsedSeconds = (elapsedTime / 1000) * timeMultiplierInt;
                long sendNextTrainTime = 500;

                if (startCycleTime == (long) 999999999) {
                    startCycleTime = System.currentTimeMillis();
                }

                if (elapsedSeconds > sendNextTrainTime) {
                    numberOfLastTrainSent++;
                    String lineNow = "";
                    if (scheduleToPick.getSelectionIndex() == 0) {
                        lineNow = "Red";
                    } else if (scheduleToPick.getSelectionIndex() == 1) {
                        lineNow = "Green";
                    }

                    dispatchAuto(Integer.toString(numberOfLastTrainSent), "40",
                            "0", "NextPlace", lineNow);
                    startCycleTime = System.currentTimeMillis();
                }
            } else if ((infrList.getItemCount() == 0) && automatic == 1) {
                autoChoiceCombo.select(0);
                automatic = 0;
            }

            if (!requestTimes.isEmpty()) {
                Calendar cal3 = Calendar.getInstance();
                cal3.add(Calendar.SECOND, totalSecondsToAdd);
                cal3.add(Calendar.SECOND, -15);
                Date date2 = cal3.getTime();

                if (requestTimes.get(0).compareTo(date2) < 0) {
                    removeFirstElement();
                    requestTimes.remove(0);
                }
            }
            checkIfTrainsSentOut();
            updateXMLInput();

            if (iii == 10) {
                iii = 0;
            }
            iii++;

            Calendar calForLists = Calendar.getInstance();
            Date veryCurrentDate = calForLists.getTime();
            calForLists.add(Calendar.SECOND, totalSecondsToAdd);
            Date nowTime = calForLists.getTime();
            calForLists.add(Calendar.SECOND, -5);
            Date dateLists = calForLists.getTime();

            if ((pastInitital == 0)
                    || (dateLastUpdate.compareTo(dateLists) < 0)) {
                dateLastUpdate = nowTime;
                pastInitital = 1;
                long tempTime = System.currentTimeMillis();
                if ((tempTime -lastUpdateLists) > 1000)
                    updateLists();
                LastroutingCheck = veryCurrentDate;
                
            }

            Calendar calToMinus = Calendar.getInstance();
            calToMinus.add(Calendar.SECOND, -4);
            Date dateForroutingCheck = calToMinus.getTime();

            if (pastInitital == 1) {

                if (LastroutingCheck.compareTo(dateForroutingCheck) < 0) {
                    routingCheck();
                    LastroutingCheck = veryCurrentDate;
                }
            }
            shlTrackModel.pack();

            if (!display.readAndDispatch()) {
                display.sleep();
            }

        }
    }

    /**
     * Create contents of the window.
     * 
     * @wbp.parser.entryPoint
     */
    protected void createContents() {
        shlTrackModel = new Shell();
        shlTrackModel.setSize(1841, 1014);
        shlTrackModel.setText("OCC");

        // }

        scheduleToPick = new Combo(shlTrackModel, SWT.NONE);
        scheduleToPick.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {

                int RedOrGreen = 0;
                int lengthOfArrayList = 0;

                infrList.removeAll();

                if (scheduleToPick.getSelectionIndex() == 0) {
                    RedOrGreen = 0;
                    lengthOfArrayList = redSchedule.size();
                } else {
                    RedOrGreen = 1;
                    lengthOfArrayList = greenSchedule.size();
                }

                for (int i = 0; i < lengthOfArrayList; i++) {
                    if (RedOrGreen == 0) {
                        infrList.add(redSchedule.get(i));
                    } else {
                        infrList.add(greenSchedule.get(i));
                    }
                }

            }
        });
        scheduleToPick.setItems(new String[] { "Red", "Green" });
        scheduleToPick.setBounds(1612, 30, 70, 28);
        scheduleToPick.select(0);

        ListViewer listViewer2 = new ListViewer(shlTrackModel,
                SWT.BORDER | SWT.V_SCROLL);
        infrList = listViewer2.getList();
        infrList.setFont(SWTResourceManager.getFont("Courier", 9, SWT.NORMAL));
        infrList.setBounds(1334, 83, 401, 262);

        autoChoiceCombo = new Combo(shlTrackModel, SWT.NONE);
        autoChoiceCombo.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                String AutoChoice = autoChoiceCombo
                        .getItem(autoChoiceCombo.getSelectionIndex())
                        .toString();
                if (AutoChoice.equals("ON")) {
                    automatic = 1;
                } else if (AutoChoice.equals("OFF")) {
                    automatic = 0;
                }

            }
        });
        autoChoiceCombo.setItems(new String[] { "OFF", "ON" });
        autoChoiceCombo.setBounds(878, 56, 55, 23);
        autoChoiceCombo.select(0);
        autoChoiceCombo.setText("OFF");

        Combo SectionOpenOrClose = new Combo(shlTrackModel, SWT.NONE);
        SectionOpenOrClose.setItems(new String[] { "Open", "Close" });
        SectionOpenOrClose.setBounds(545, 672, 70, 28);
        formToolkit.adapt(SectionOpenOrClose);
        formToolkit.paintBordersFor(SectionOpenOrClose);
        SectionOpenOrClose.setText("A");
        SectionOpenOrClose.select(0);

        Combo CrossingUpOrDown = new Combo(shlTrackModel, SWT.NONE);
        CrossingUpOrDown.setItems(new String[] { "Up", "Down" });
        CrossingUpOrDown.setBounds(215, 646, 70, 28);
        formToolkit.adapt(CrossingUpOrDown);
        formToolkit.paintBordersFor(CrossingUpOrDown);
        CrossingUpOrDown.select(0);

        Combo SwitchNormalOrReverse = new Combo(shlTrackModel, SWT.NONE);
        SwitchNormalOrReverse.setItems(new String[] { "Normal", "Reverse" });
        SwitchNormalOrReverse.setBounds(373, 646, 90, 28);
        formToolkit.adapt(SwitchNormalOrReverse);
        formToolkit.paintBordersFor(SwitchNormalOrReverse);
        SwitchNormalOrReverse.select(0);

        Label lblThroughput = new Label(shlTrackModel, SWT.NONE);
        lblThroughput.setForeground(SWTResourceManager.getColor(255, 0, 0));
        lblThroughput
                .setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
        lblThroughput.setBounds(10, 9, 163, 34);
        lblThroughput.setText("THROUGHPUT");

        lblSchedule = new Label(shlTrackModel, SWT.NONE);
        lblSchedule
                .setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
        lblSchedule.setBounds(1462, 24, 117, 27);
        lblSchedule.setText("Schedule");

        lblHhmmss = new Label(shlTrackModel, SWT.NONE);
        lblHhmmss.setBounds(1008, 59, 82, 25);
        lblHhmmss.setText("Seconds");

        Label lblLine_1 = new Label(shlTrackModel, SWT.NONE);
        lblLine_1.setBounds(1334, 57, 27, 20);
        lblLine_1.setText("Line");

        Label lblInfrastr = new Label(shlTrackModel, SWT.NONE);
        lblInfrastr.setBounds(1537, 59, 90, 20);
        lblInfrastr.setText("Stop");

        Label lblTime = new Label(shlTrackModel, SWT.NONE);
        lblTime.setBounds(1409, 59, 48, 20);
        lblTime.setText("Time");

        Label lblClose = new Label(shlTrackModel, SWT.NONE);
        lblClose.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
        lblClose.setBounds(472, 574, 196, 28);
        lblClose.setText("Close/Open Track");

        Combo LineToClose = new Combo(shlTrackModel, SWT.NONE);
        LineToClose.setItems(new String[] { "Red", "Green" });
        LineToClose.setBounds(545, 611, 70, 28);
        formToolkit.adapt(LineToClose);
        formToolkit.paintBordersFor(LineToClose);
        LineToClose.select(0);
        LineToClose.setText("Red");

        ImageHyperlink mghprlnkNewImagehyperlink = formToolkit
                .createImageHyperlink(shlTrackModel, SWT.NONE);
        mghprlnkNewImagehyperlink
                .setImage(SWTResourceManager.getImage("./Images/Medium.jpg"));
        mghprlnkNewImagehyperlink.setBounds(841, 124, 446, 582);
        formToolkit.paintBordersFor(mghprlnkNewImagehyperlink);
        mghprlnkNewImagehyperlink.setText("New ImageHyperlink");

        Label label_1 = new Label(shlTrackModel, SWT.NONE);
        label_1.setText("Line");
        label_1.setBounds(491, 614, 48, 20);

        Label label_2 = new Label(shlTrackModel, SWT.NONE);
        label_2.setText("Block");
        label_2.setBounds(489, 641, 50, 20);

        CLabel label = new CLabel(shlTrackModel, SWT.NONE);
        label.setText("AUTO:");
        label.setBounds(878, 31, 40, 21);

        Button btnCloseSection = new Button(shlTrackModel, SWT.NONE);
        btnCloseSection.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                String WhatToChange = "Section";
                String Label = LineToClose
                        .getItem(LineToClose.getSelectionIndex()).toString()
                        + ":" + blockToCloseText.getText();
                String WhatToDo = "Close";
                String ChoiceForSection = SectionOpenOrClose
                        .getItem(SectionOpenOrClose.getSelectionIndex())
                        .toString();

                int BlockInt = Integer.parseInt(blockToCloseText.getText());

                if (LineToClose.getItem(LineToClose.getSelectionIndex())
                        .toString().equals("Red")) {
                    if (BlockInt <= 76 && BlockInt >= 1)
                        requestSend(WhatToChange, Label, WhatToDo,
                                ChoiceForSection);
                } else if (LineToClose.getItem(LineToClose.getSelectionIndex())
                        .toString().equals("Green")) {
                    if (BlockInt <= 150 && BlockInt >= 1)
                        requestSend(WhatToChange, Label, WhatToDo,
                                ChoiceForSection);
                }

            }
        });
        btnCloseSection.setBounds(503, 706, 112, 30);
        btnCloseSection.setText("Request Change");

        Label lblFlipSwitch = new Label(shlTrackModel, SWT.NONE);
        lblFlipSwitch.setText("Flip Switch");
        lblFlipSwitch
                .setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
        lblFlipSwitch.setBounds(327, 578, 123, 28);

        Combo SwitchToFlip = new Combo(shlTrackModel, SWT.NONE);
        SwitchToFlip.setItems(new String[] { "R:CYd", "R:E", "R:TH", "R:RH",
                "R:QH", "R:OH", "R:J", "G:C", "G:G", "G:JToYd", "G:JFmYd",
                "G:M", "G:O" });
        SwitchToFlip.setBounds(373, 612, 90, 28);
        formToolkit.adapt(SwitchToFlip);
        formToolkit.paintBordersFor(SwitchToFlip);
        SwitchToFlip.select(0);

        Label lblSwitch_1 = new Label(shlTrackModel, SWT.NONE);
        lblSwitch_1.setText("Switch");
        lblSwitch_1.setBounds(310, 617, 57, 20);

        Button btnFlipSwitch = new Button(shlTrackModel, SWT.NONE);
        btnFlipSwitch.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                String WhatToChange = "Switch";
                String Label = SwitchToFlip
                        .getItem(SwitchToFlip.getSelectionIndex()).toString();
                String WhatToDo = "Flip";
                String ChoiceForSwitch = SwitchNormalOrReverse
                        .getItem(SwitchNormalOrReverse.getSelectionIndex())
                        .toString();
                requestSend(WhatToChange, Label, WhatToDo, ChoiceForSwitch);
            }
        });
        btnFlipSwitch.setText("Flip Switch");
        btnFlipSwitch.setBounds(337, 680, 90, 30);

        Label lblChangeCrossing = new Label(shlTrackModel, SWT.NONE);
        lblChangeCrossing.setText("Change Cross");
        lblChangeCrossing
                .setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
        lblChangeCrossing.setBounds(152, 578, 152, 28);

        Combo ComboCrossToChange = new Combo(shlTrackModel, SWT.NONE);
        ComboCrossToChange.setItems(new String[] { "G:E", "R:I" });
        ComboCrossToChange.setBounds(215, 612, 70, 28);
        formToolkit.adapt(ComboCrossToChange);
        formToolkit.paintBordersFor(ComboCrossToChange);
        ComboCrossToChange.select(0);

        Label lblCrossing_1 = new Label(shlTrackModel, SWT.NONE);
        lblCrossing_1.setText("Crossing");
        lblCrossing_1.setBounds(152, 617, 57, 20);

        Button btnChangeCross = new Button(shlTrackModel, SWT.NONE);
        btnChangeCross.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {

                String WhatToChange = "Crossing";
                String Label = ComboCrossToChange
                        .getItem(ComboCrossToChange.getSelectionIndex())
                        .toString();
                String WhatToDo = "Change";
                String ChoiceForCross = CrossingUpOrDown
                        .getItem(CrossingUpOrDown.getSelectionIndex())
                        .toString();
                requestSend(WhatToChange, Label, WhatToDo, ChoiceForCross);

            }
        });
        btnChangeCross.setText("Change Xing");
        btnChangeCross.setBounds(179, 680, 90, 30);

        throughputText = new Label(shlTrackModel, SWT.NONE);
        throughputText.setFont(
                SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
        throughputText.setText("Number");
        throughputText.setBounds(48, 50, 82, 25);

        lblPassengers = new Label(shlTrackModel, SWT.NONE);
        lblPassengers.setFont(
                SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
        lblPassengers.setText("Passengers/Hr");
        lblPassengers.setBounds(36, 80, 118, 28);

        Label lblNewLabel_2 = new Label(shlTrackModel, SWT.NONE);
        lblNewLabel_2
                .setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
        lblNewLabel_2.setBounds(1008, 25, 82, 28);
        lblNewLabel_2.setText("Clock");

        Label lblChoice = new Label(shlTrackModel, SWT.NONE);
        lblChoice.setText("Choice");
        lblChoice.setBounds(310, 649, 57, 20);

        Label lblChoice_1 = new Label(shlTrackModel, SWT.NONE);
        lblChoice_1.setText("Choice");
        lblChoice_1.setBounds(152, 651, 57, 20);

        Label lblChoice_2 = new Label(shlTrackModel, SWT.NONE);
        lblChoice_2.setText("Choice");
        lblChoice_2.setBounds(491, 670, 48, 20);

        Label lblTrains = new Label(shlTrackModel, SWT.NONE);
        lblTrains.setText("Active Trains");
        lblTrains.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
        lblTrains.setBounds(1462, 367, 152, 27);

        trainsTable = new Table(shlTrackModel, SWT.BORDER | SWT.FULL_SELECTION);
        trainsTable.setBounds(1334, 411, 401, 315);
        formToolkit.adapt(trainsTable);
        formToolkit.paintBordersFor(trainsTable);
        trainsTable.setHeaderVisible(true);
        trainsTable.setLinesVisible(true);

        TableColumn tblclmnNumber = new TableColumn(trainsTable, SWT.NONE);
        tblclmnNumber.setWidth(66);
        tblclmnNumber.setText("Number");

        TableColumn tblclmnLine = new TableColumn(trainsTable, SWT.NONE);
        tblclmnLine.setWidth(56);
        tblclmnLine.setText("Line");

        TableColumn tblclmnNextDestination = new TableColumn(trainsTable,
                SWT.NONE);
        tblclmnNextDestination.setWidth(3000);
        tblclmnNextDestination.setText("Schedule");

        Combo TimeMultiplier = new Combo(shlTrackModel, SWT.NONE);
        TimeMultiplier.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                String CurrentSelected = TimeMultiplier
                        .getItem(TimeMultiplier.getSelectionIndex()).toString();

                if (CurrentSelected.equals("1x")) {
                    timeMultiplierInt = 1;
                    writeMultiplier("1");
                } else if (CurrentSelected.equals("2x")) {
                    timeMultiplierInt = 2;
                    writeMultiplier("2");
                } else if (CurrentSelected.equals("10x")) {
                    timeMultiplierInt = 10;
                    writeMultiplier("10");
                } else if (CurrentSelected.equals("100x")) {
                    timeMultiplierInt = 100;
                    writeMultiplier("100");
                }
            }
        });
        TimeMultiplier.setItems(new String[] { "1x", "2x", "10x", "100x" });
        TimeMultiplier.setBounds(1154, 56, 55, 28);
        formToolkit.adapt(TimeMultiplier);
        formToolkit.paintBordersFor(TimeMultiplier);
        TimeMultiplier.setText("OFF");
        TimeMultiplier.select(0);

        Label lblTimeMultiplier = new Label(shlTrackModel, SWT.NONE);
        lblTimeMultiplier.setText("Time Multiplier");
        lblTimeMultiplier.setBounds(1154, 25, 105, 25);

        ListViewer listViewer = new ListViewer(shlTrackModel,
                SWT.BORDER | SWT.V_SCROLL);
        crossStatesList = listViewer.getList();
        crossStatesList
                .setFont(SWTResourceManager.getFont("Courier", 9, SWT.NORMAL));
        crossStatesList.setBounds(189, 446, 142, 68);

        Label lblcrossStates = new Label(shlTrackModel, SWT.NONE);
        lblcrossStates.setText("Xing States");
        lblcrossStates
                .setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
        lblcrossStates.setBounds(189, 411, 144, 28);

        ListViewer listViewer_1 = new ListViewer(shlTrackModel,
                SWT.BORDER | SWT.V_SCROLL);
        signalStatesList = listViewer_1.getList();
        signalStatesList
                .setFont(SWTResourceManager.getFont("Courier", 9, SWT.NORMAL));
        signalStatesList.setBounds(340, 109, 134, 405);

        ListViewer listViewer_2 = new ListViewer(shlTrackModel,
                SWT.BORDER | SWT.V_SCROLL);
        switchStatesList = listViewer_2.getList();
        switchStatesList
                .setFont(SWTResourceManager.getFont("Courier", 9, SWT.NORMAL));
        switchStatesList.setBounds(189, 109, 142, 278);

        ListViewer listViewer_3 = new ListViewer(shlTrackModel,
                SWT.BORDER | SWT.V_SCROLL);
        redTrackStatesList = listViewer_3.getList();
        redTrackStatesList
                .setFont(SWTResourceManager.getFont("Courier", 9, SWT.NORMAL));
        redTrackStatesList.setBounds(645, 109, 159, 405);

        Label lblRedTrackStates = new Label(shlTrackModel, SWT.NONE);
        lblRedTrackStates.setText("R Line");
        lblRedTrackStates
                .setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
        lblRedTrackStates.setBounds(675, 78, 70, 28);

        ListViewer listViewer_4 = new ListViewer(shlTrackModel,
                SWT.BORDER | SWT.V_SCROLL);
        greenTrackStatesList = listViewer_4.getList();
        greenTrackStatesList
                .setFont(SWTResourceManager.getFont("Courier", 9, SWT.NORMAL));
        greenTrackStatesList.setBounds(480, 110, 159, 405);

        Label lblGreenTrackStates = new Label(shlTrackModel, SWT.NONE);
        lblGreenTrackStates.setText("G Line");
        lblGreenTrackStates
                .setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
        lblGreenTrackStates.setBounds(522, 78, 75, 28);

        Label lblswitchStates = new Label(shlTrackModel, SWT.NONE);
        lblswitchStates.setText("Switch States");
        lblswitchStates
                .setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
        lblswitchStates.setBounds(179, 75, 144, 28);

        Label lblsignalStates = new Label(shlTrackModel, SWT.NONE);
        lblsignalStates.setText("Signal States");
        lblsignalStates
                .setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
        lblsignalStates.setBounds(340, 75, 144, 34);

        Label label_3 = new Label(shlTrackModel, SWT.SEPARATOR | SWT.VERTICAL);
        label_3.setBounds(174, 18, 2, 514);

        Label label_4 = new Label(shlTrackModel,
                SWT.SEPARATOR | SWT.HORIZONTAL);
        label_4.setBounds(10, 534, 794, 2);

        Label label_5 = new Label(shlTrackModel,
                SWT.SEPARATOR | SWT.HORIZONTAL);
        label_5.setBounds(10, 114, 144, 2);

        Label lblManual = new Label(shlTrackModel, SWT.NONE);
        lblManual.setForeground(SWTResourceManager.getColor(255, 0, 0));
        lblManual.setText("MANUAL");
        lblManual.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
        lblManual.setBounds(31, 124, 117, 36);

        Label lblSystemStatus = new Label(shlTrackModel, SWT.NONE);
        lblSystemStatus.setForeground(SWTResourceManager.getColor(255, 0, 0));
        lblSystemStatus.setText("SYSTEM STATUS");
        lblSystemStatus
                .setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
        lblSystemStatus.setBounds(277, 10, 185, 34);

        Label lblRequests = new Label(shlTrackModel, SWT.NONE);
        lblRequests.setForeground(SWTResourceManager.getColor(255, 0, 0));
        lblRequests.setText("REQUESTS");
        lblRequests
                .setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
        lblRequests.setBounds(327, 538, 123, 34);

        Label label_6 = new Label(shlTrackModel, SWT.SEPARATOR);
        label_6.setBounds(823, 33, 2, 693);
        formToolkit.adapt(label_6, true, true);

        Label label_7 = new Label(shlTrackModel, SWT.SEPARATOR);
        label_7.setBounds(1310, 33, 2, 693);
        formToolkit.adapt(label_7, true, true);

        Label label_8 = new Label(shlTrackModel,
                SWT.SEPARATOR | SWT.HORIZONTAL);
        label_8.setBounds(836, 90, 468, 2);
        formToolkit.adapt(label_8, true, true);

        Label label_9 = new Label(shlTrackModel,
                SWT.SEPARATOR | SWT.HORIZONTAL);
        label_9.setBounds(10, 761, 1757, 2);
        formToolkit.adapt(label_9, true, true);

        Label label_10 = new Label(shlTrackModel, SWT.SEPARATOR | SWT.VERTICAL);
        label_10.setBounds(1773, 10, 2, 753);

        Combo GreenStationsCombo = new Combo(shlTrackModel, SWT.NONE);
        GreenStationsCombo.setItems(new String[] { "PIONEER", "EDGEBROOK", "D",
                "WHITED", "SOUTH BANK", "CENTRAL", "INGLEWOOD", "OVERBROOK",
                "GLENBURY", "DORMONT", "MT LEBANON", "POPLAR", "CASTLE SHANNON",
                "DORMONT" });
        GreenStationsCombo.setBounds(10, 200, 144, 28);
        formToolkit.adapt(GreenStationsCombo);
        formToolkit.paintBordersFor(GreenStationsCombo);
        GreenStationsCombo.setText("A");
        GreenStationsCombo.select(0);

        Combo RedStationsCombo = new Combo(shlTrackModel, SWT.NONE);
        RedStationsCombo.setItems(new String[] { "SHADYSIDE", "HERON AVE",
                "SWISSVILLE", "PENN STATION", "STEEL PLAZA", "FIRST AVE",
                "STATION SQUARE", "SOUTH HILLS JUNCTION" });
        RedStationsCombo.setBounds(10, 363, 144, 28);
        formToolkit.adapt(RedStationsCombo);
        formToolkit.paintBordersFor(RedStationsCombo);
        RedStationsCombo.setText("A");
        RedStationsCombo.select(0);

        Label lblRedDispatch = new Label(shlTrackModel, SWT.NONE);
        lblRedDispatch.setText("Green Dispatch");
        lblRedDispatch
                .setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
        lblRedDispatch.setBounds(5, 166, 168, 28);

        Label lblRedDispatch_1 = new Label(shlTrackModel, SWT.NONE);
        lblRedDispatch_1.setText("Red Dispatch");
        lblRedDispatch_1
                .setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
        lblRedDispatch_1.setBounds(5, 329, 152, 28);

        Button DispatchRedButton = new Button(shlTrackModel, SWT.NONE);
        DispatchRedButton.setText("Dispatch");
        DispatchRedButton.setBounds(36, 423, 75, 25);
        DispatchRedButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                String SuggSpeed = suggSpeedRed.getText();

                int suggInt = Integer.parseInt(SuggSpeed);

                if (suggInt < 70 && suggInt >= 0) {
                    String DestBlock = RedStationsCombo
                            .getItem(RedStationsCombo.getSelectionIndex())
                            .toString();
                    String DestLine = "Red";

                    numberOfLastTrainSent++;

                    dispatchManual(Integer.toString(numberOfLastTrainSent), SuggSpeed,
                            DestLine, DestBlock);
                }
            }
        });

        Button DispatchGreenButton = new Button(shlTrackModel, SWT.NONE);
        DispatchGreenButton.setText("Dispatch");
        DispatchGreenButton.setBounds(36, 259, 75, 25);
        DispatchGreenButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {

                String SuggSpeed = suggSpeedGreen.getText();

                int suggInt = Integer.parseInt(SuggSpeed);

                if (suggInt < 70 && suggInt >= 0) {
                    String DestBlock = GreenStationsCombo
                            .getItem(GreenStationsCombo.getSelectionIndex())
                            .toString();
                    String DestLine = "Green";

                    numberOfLastTrainSent++;
                    dispatchManual(Integer.toString(numberOfLastTrainSent), SuggSpeed,
                            DestLine, DestBlock);
                }
            }
        });

        Label label_11 = new Label(shlTrackModel, SWT.NONE);
        label_11.setText("Speed:");
        label_11.setBounds(10, 232, 55, 21);

        suggSpeedGreen = new Text(shlTrackModel, SWT.BORDER);
        suggSpeedGreen.setText("10");
        suggSpeedGreen.setBounds(71, 234, 40, 21);

        Label label_12 = new Label(shlTrackModel, SWT.NONE);
        label_12.setText("mph");
        label_12.setBounds(117, 232, 40, 25);

        Label label_13 = new Label(shlTrackModel, SWT.NONE);
        label_13.setText("Speed:");
        label_13.setBounds(10, 396, 55, 21);

        suggSpeedRed = new Text(shlTrackModel, SWT.BORDER);
        suggSpeedRed.setText("10");
        suggSpeedRed.setBounds(71, 398, 40, 21);

        Label label_14 = new Label(shlTrackModel, SWT.NONE);
        label_14.setText("mph");
        label_14.setBounds(117, 396, 40, 25);

        blockToCloseText = new Text(shlTrackModel, SWT.BORDER);
        blockToCloseText.setText("1");
        blockToCloseText.setBounds(544, 646, 40, 21);
        formToolkit.adapt(blockToCloseText, true, true);

        Button btnLoadSchedule = new Button(shlTrackModel, SWT.NONE);
        btnLoadSchedule.setText("Load Schedule");
        btnLoadSchedule.setBounds(1334, 30, 111, 25);
        btnLoadSchedule.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new java.io.File("./CTCSchedules"));

                if (fileChooser
                        .showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    java.io.File file = fileChooser.getSelectedFile();
                    String line;

                    try {
                        infrList.removeAll();
                        BufferedReader br = new BufferedReader(
                                new FileReader(file));
                        line = br.readLine();
                        String[] tempData = line.split(",");
                        if (tempData[0].equals("Red")) {
                            redSchedule.clear();
                            redStops.clear();
                        } else if (tempData[0].equals("Green")) {
                            greenSchedule.clear();
                            greenStops.clear();
                        }

                        while ((line = br.readLine()) != null) {
                            String[] data = line.split(",");

                            int lineLengthToAdd = 10 - data[0].length();
                            int infrLengthToAdd = 40 - data[1].length();
                            int timeLengthToAdd = 15 - data[2].length();

                            String PlainDestination = data[1];

                            String LineColor = data[0];
                            String data1 = "";

                            for (int i = 0; i < lineLengthToAdd; i++) {
                                data[0] = data[0] + " ";
                            }
                            for (int i = 0; i < infrLengthToAdd; i++) {
                                String arraySplit[] = data[1].split("Station:");
                                data1 = arraySplit[1] + " ";
                            }
                            for (int i = 0; i < timeLengthToAdd; i++) {
                                data[2] = data[2] + " ";
                            }
                            String wholeLine = data[0] + data[2] + data1;

                            if (LineColor.equals("Red")) {
                                redSchedule.add(wholeLine);
                                redStops.add(PlainDestination);
                                scheduleToPick.select(0);
                            } else if (LineColor.equals("Green")) {
                                greenSchedule.add(wholeLine);
                                greenStops.add(PlainDestination);
                                scheduleToPick.select(1);
                            }

                            infrList.add(wholeLine);

                        }
                        startCycleTime = System.currentTimeMillis();
                        br.close();
                        currentIndexSchedule = 0;
                        automatic = 1;

                        autoChoiceCombo.select(1);
                        numberOfLastTrainSent += 1;

                        String lineNow = scheduleToPick
                                .getItem(scheduleToPick.getSelectionIndex())
                                .toString();

                        dispatchAuto(Integer.toString(numberOfLastTrainSent),
                                "40", "0", "NextPlace", lineNow);

                    } catch (Exception ee) {

                    }

                }

            }
        });

        if (newProgram == 1) {
            newXMLFile(1, 1, 1);
            newProgram = 0;
        }

    }

    /* This is used to recreate the xml files I edit to their starting states.
     * This is run at the start of my module.
     */
    public void newXMLFile(int CtcOrNo, int TrainOrNo, int multOrNo) {
        try {
            File file;
            FileWriter writer;

            if (CtcOrNo == 1) {
                file = new File("./xml/CTCOutput.xml");
                writer = new FileWriter(file);
                writer.write(
                        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><bits></bits>");
                writer.flush();
                writer.close();
            }

            if (multOrNo == 1) {
                file = new File("./xml/multiplier.txt");
                writer = new FileWriter(file);
                writer.write("1");
                writer.flush();
                writer.close();
            }

            if (TrainOrNo == 1) {
                File file2 = new File("./xml/TrainOutputs.xml");
                writer = new FileWriter(file2);
                writer.write(
                        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><Dispatches></Dispatches>");
                writer.flush();
                writer.close();
            }
        } catch (Exception e) {
        }
    }

    /* This is used to remove the first element in my output
     * file to the Track Controller.
     */
    public void removeFirstElement() {
        try {
            File inputFile = new File("./xml/CTCOutput.xml");
            DocumentBuilderFactory docFactory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc2 = docBuilder.parse(inputFile);

            Node Dispatch = doc2.getElementsByTagName("bits").item(0);
            Node getElement = Dispatch.getFirstChild();
            Dispatch.removeChild(getElement);

            TransformerFactory transformerFactory = TransformerFactory
                    .newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc2);
            StreamResult result = new StreamResult(
                    new File("./xml/CTCOutput.xml"));
            transformer.transform(source, result);

        } catch (Exception e) {
        }
    }

    /* This is used when a train is dispatched manually, to write
     * to XML to the Train Model.
     */
    public void dispatchManual(String trainNumber, String SuggSpeed, String Line,
            String DestBlock) {
        try {

            int suggInt = Integer.parseInt(SuggSpeed);
            if (suggInt >= 0 && suggInt < 70) {
                File inputFile = new File("./xml/TrainOutputs.xml");
                DocumentBuilderFactory docFactory = DocumentBuilderFactory
                        .newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                Document doc1 = docBuilder.parse(inputFile);

                Node Dispatch = doc1.getElementsByTagName("Dispatches").item(0);
                Element unit = doc1.createElement("Dispatch");

                Attr attrType = doc1.createAttribute("IDNum");
                attrType.setValue(trainNumber);
                unit.setAttributeNode(attrType);

                Attr attrSuggSpeed = doc1.createAttribute("SpeedSugg");
                attrSuggSpeed.setValue(SuggSpeed);
                unit.setAttributeNode(attrSuggSpeed);

                String AllStops = "";

                Attr lineAttr = doc1.createAttribute("Line");
                lineAttr.setValue(Line);
                unit.setAttributeNode(lineAttr);

                Attr nextDestOnSchedule;
                nextDestOnSchedule = doc1.createAttribute("Stop0000");
                nextDestOnSchedule.setValue(DestBlock);
                unit.setAttributeNode(nextDestOnSchedule);

                nextDestOnSchedule = doc1.createAttribute("Stop0001");
                nextDestOnSchedule.setValue("Yard");
                unit.setAttributeNode(nextDestOnSchedule);

                AllStops = AllStops + DestBlock + "-->Yard";

                scheduleForTrainToSend.put(trainNumber, AllStops);
                lineForTrainToSend.put(trainNumber, Line);

                if (Line.equals("Red")) {
                    requestSend("Switch", "R:CYd", "Flip", "Reverse");
                } else if (Line.equals("Green")) {
                    requestSend("Switch", "G:JFmYd", "Flip", "Reverse");

                }

                unit.appendChild(doc1.createTextNode("0"));
                Dispatch.appendChild(unit);

                TransformerFactory transformerFactory = TransformerFactory
                        .newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc1);
                StreamResult result = new StreamResult(
                        new File("./xml/TrainOutputs.xml"));
                transformer.transform(source, result);

            }

        } catch (Exception e) {
        }
    }

    /* This checks if the Train Model has approved the trains I sent,
     * and therefore they go in my train viewer window.
     * Also removes trains if they are back at the yard.
     */
    public void checkIfTrainsSentOut() {
        try {

            File inputFile = new File("./xml/TrainOutputs.xml");
            DocumentBuilderFactory docFactory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc2 = docBuilder.parse(inputFile);

            NodeList NodeList = doc2.getElementsByTagName("Dispatch");

            String temporaryTest = "";
            for (int temp = 0; temp < NodeList.getLength(); temp++) {
                Node nowNode = NodeList.item(temp);

                Element nowElement = (Element) nowNode;
                String ThisID = nowElement.getAttribute("IDNum");
                String LineNow = nowElement.getAttribute("Line");
                String value = nowElement.getTextContent();
                temporaryTest = temporaryTest + ThisID + "a";

                if ((!trainsAlreadySent.contains(ThisID))
                        && value.equals("1")) {
                    trainsAlreadySent.add(ThisID);
                    TableItem tableItem = new TableItem(trainsTable, SWT.NONE);
                    tableItem.setText(0, ThisID);
                    tableItem.setData(ThisID);
                    outTrainsToColors.put(ThisID, LineNow);

                    tableItem.setText(1, lineForTrainToSend.get(ThisID));
                    tableItem.setText(2, scheduleForTrainToSend.get(ThisID));
                }
                if (trainsAlreadySent.contains(ThisID) && value.equals("2")) {
                    trainsAlreadySent.remove(ThisID);
                    removeFromtrainsTable(ThisID);
                    if (NodeList.getLength() > 2) {
                        nowNode.getParentNode().removeChild(nowNode);

                        TransformerFactory transformerFactory = TransformerFactory
                                .newInstance();
                        Transformer transformer = transformerFactory
                                .newTransformer();
                        DOMSource source = new DOMSource(doc2);
                        StreamResult result = new StreamResult(
                                new File("./xml/TrainOutputs.xml"));
                        transformer.transform(source, result);

                        result.getOutputStream().close();
                    } else {
                        newXMLFile(0, 1, 0);
                    }
                }
            }
        } catch (Exception e) {
        }

    }

    /* This is used to write a train to the Train Model
     * when a train is automatically sent with the schedule.
     */
    public void dispatchAuto(String trainNumber, String SuggSpeed,
            String SuggAuth, String Dest, String Line) {
        try {

            File inputFile = new File("./xml/TrainOutputs.xml");
            DocumentBuilderFactory docFactory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc1 = docBuilder.parse(inputFile);

            Node Dispatch = doc1.getElementsByTagName("Dispatches").item(0);
            Element unit = doc1.createElement("Dispatch");

            Attr attrType = doc1.createAttribute("IDNum");
            attrType.setValue(trainNumber);
            unit.setAttributeNode(attrType);

            String AllStops = "";

            if (Line.equals("Red")) {
                requestSend("Switch", "R:CYd", "Flip", "Reverse");

                Attr lineAttr = doc1.createAttribute("Line");
                lineAttr.setValue("Red");
                unit.setAttributeNode(lineAttr);

                Attr SuggAttr = doc1.createAttribute("SpeedSugg");
                SuggAttr.setValue(SuggSpeed);
                unit.setAttributeNode(SuggAttr);

                for (int i = 0; i < redStops.size(); i++) {
                    Attr nextDestOnSchedule;
                    if (i < 10) {
                        nextDestOnSchedule = doc1.createAttribute(
                                "Stop" + "000" + Integer.toString(i));
                    } else if (i < 100) {
                        nextDestOnSchedule = doc1.createAttribute(
                                "Stop" + "00" + Integer.toString(i));

                    } else if (i < 1000) {
                        nextDestOnSchedule = doc1.createAttribute(
                                "Stop" + "0" + Integer.toString(i));
                    } else {
                        nextDestOnSchedule = doc1
                                .createAttribute("Stop" + Integer.toString(i));
                    }

                    String[] nextDestSplit = redStops.get(i).split(":");
                    String redGet = "";
                    if (nextDestSplit.length == 2
                            || nextDestSplit.length == 3) {
                        nextDestOnSchedule.setValue(nextDestSplit[1]);
                        redGet = nextDestSplit[1];
                    } else {
                        nextDestOnSchedule.setValue(redStops.get(i));
                        redGet = redStops.get(i);
                    }

                    unit.setAttributeNode(nextDestOnSchedule);
                    AllStops = AllStops + redGet + "-->";
                }
            } else if (Line.equals("Green")) {
                requestSend("Switch", "G:JFmYd", "Flip", "Reverse");

                Attr lineAttr = doc1.createAttribute("Line");
                lineAttr.setValue("Green");
                unit.setAttributeNode(lineAttr);

                for (int i = 0; i < greenStops.size(); i++) {
                    Attr nextDestOnSchedule;
                    if (i < 10) {
                        nextDestOnSchedule = doc1.createAttribute(
                                "Stop" + "000" + Integer.toString(i));
                    } else if (i < 100) {
                        nextDestOnSchedule = doc1.createAttribute(
                                "Stop" + "00" + Integer.toString(i));

                    } else if (i < 1000) {
                        nextDestOnSchedule = doc1.createAttribute(
                                "Stop" + "0" + Integer.toString(i));
                    } else {
                        nextDestOnSchedule = doc1
                                .createAttribute("Stop" + Integer.toString(i));
                    }

                    String[] nextDestSplit = greenStops.get(i).split(":");
                    String greenGet = "";

                    if (nextDestSplit.length == 2
                            || nextDestSplit.length == 3) {
                        nextDestOnSchedule.setValue(nextDestSplit[1]);
                        greenGet = nextDestSplit[1];
                    } else {
                        nextDestOnSchedule.setValue(greenStops.get(i));
                        greenGet = greenStops.get(i);
                    }

                    unit.setAttributeNode(nextDestOnSchedule);
                    AllStops = AllStops + greenGet + "-->";
                }
            }

            unit.appendChild(doc1.createTextNode("0"));
            Dispatch.appendChild(unit);

            scheduleForTrainToSend.put(trainNumber, AllStops);
            lineForTrainToSend.put(trainNumber, Line);

            TransformerFactory transformerFactory = TransformerFactory
                    .newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc1);
            StreamResult result = new StreamResult(
                    new File("./xml/TrainOutputs.xml"));
            transformer.transform(source, result);

        } catch (Exception e) {
        }
    }

    /* This sends a request to the Track Controller, such as
     * a switch change, track closure, or crossing change.
     */
    public void requestSend(String WhatToChange, String Label, String WhatToDo,
            String Choice) {
        try {
            if (WhatToDo.equals("Initialize")) {
                WhatToDo = "0";
            } else if (WhatToDo.equals("Close") || WhatToDo.equals("Reverse")
                    || WhatToDo.equals("Down")) {
                WhatToDo = "1";
            } else {
                WhatToDo = "1";
            }

            if (WhatToChange.equals("Switch")) {
                Label = switchConversion.get(Label);
                Label = Label + "S";
                if (Choice.equals("Normal")) {
                    Label = Label + "NR";
                } else if (Choice.equals("Reverse")) {
                    Label = Label + "RR";
                }
            } else if (WhatToChange.equals("Crossing")) {
                Label = crossingConversionForSend.get(Label);

                if (Label.equals("G1")) {
                    Label = "G19";
                } else if (Label.equals("R1")) {
                    Label = "R47";
                }

                if (Choice.equals("Up")) {
                    Label = Label + "UR";
                } else if (Choice.equals("Down")) {
                    Label = Label + "DR";
                }
            } else if (WhatToChange.equals("Section")) {
                String splitLabel[];

                if (Label.charAt(0) == ('G')) {
                    splitLabel = Label.split("reen:");
                    Label = splitLabel[0] + splitLabel[1];
                } else if (Label.charAt(0) == ('R')) {
                    splitLabel = Label.split("ed:");
                    Label = splitLabel[0] + splitLabel[1];
                }

                Label = Label + "T";

                if (Choice.equals("Open")) {
                    Label = Label + "OR";
                } else if (Choice.equals("Close")) {
                    Label = Label + "CR";
                }
            }

            File inputFile = new File("./xml/CTCOutput.xml");
            DocumentBuilderFactory docFactory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc1 = docBuilder.parse(inputFile);

            Node Dispatch = doc1.getElementsByTagName("bits").item(0);
            Element unit = doc1.createElement("bit");

            Attr attrType = doc1.createAttribute("name");
            attrType.setValue(Label);
            unit.setAttributeNode(attrType);

            unit.appendChild(doc1.createTextNode(WhatToDo));
            Dispatch.appendChild(unit);

            TransformerFactory transformerFactory = TransformerFactory
                    .newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc1);
            StreamResult result = new StreamResult(
                    new File("./xml/CTCOutput.xml"));
            transformer.transform(source, result);

            Calendar cal = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            cal.add(Calendar.SECOND, totalSecondsToAdd);
            cal2.add(Calendar.SECOND, totalSecondsToAdd);
            Date DateToAdd = cal.getTime();

            requestTimes.add(DateToAdd);

        } catch (Exception e) {
        }
    }

    /* This physically adds the train to my display.
     * This is called when a train is sent from the yard.
     */
    public void addTrainToTable(int TrainNumber, String Line,
            String NextDestination) {
        TableItem tableItem = new TableItem(trainsTable, SWT.NONE);
        tableItem.setText(0, Integer.toString(TrainNumber));
        tableItem.setText(1, Line);
        tableItem.setText(2, NextDestination);
    }

    /* This refreshes the lists for states of sections, crossings,
     * switches, lights.
     */
    public void updateLists() {
        crossStatesList.removeAll();
        crossStatesList.add("CROSS  STATE");
        for (int i = 0; i < crossingsArrayList.size(); i++) {
            String toAdd = crossingsArrayList.get(i);
            int totalSpacesToAdd = 7 - toAdd.length();

            for (int k = 0; k < totalSpacesToAdd; k++) {
                toAdd += " ";
            }

            toAdd += crossStates.get(crossingsArrayList.get(i));
            crossStatesList.add(toAdd);
        }

        switchStatesList.removeAll();
        switchStatesList.add("SWITCH  STATE");
        for (int i = 0; i < switchesArrayList.size(); i++) {
            String toAdd = switchesArrayList.get(i);
            int totalSpacesToAdd = 8 - toAdd.length();

            for (int k = 0; k < totalSpacesToAdd; k++) {
                toAdd += " ";
            }

            if (switchesArrayList.get(i).length() > 0) {
                toAdd += switchStates.get(switchesArrayList.get(i));
                switchStatesList.add(toAdd);
            }
        }

        signalStatesList.removeAll();
        signalStatesList.add("SIGNAL  STATE");
        Collections.sort(signalsArrayList);

        for (int i = 0; i < signalsArrayList.size(); i++) {
            String toAdd = signalsArrayList.get(i);
            int totalSpacesToAdd = 8 - toAdd.length();

            for (int k = 0; k < totalSpacesToAdd; k++) {
                toAdd += " ";
            }

            toAdd += signalStates.get(signalsArrayList.get(i));
            signalStatesList.add(toAdd);
        }

        redTrackStatesList.removeAll();
        redTrackStatesList.add("SECT. OCCUP");
        for (int i = 0; i < redSectionsArrayList.size(); i++) {
            String toAdd = redSectionsArrayList.get(i);
            char charOnLine = toAdd.charAt(0);
            String SpotOnLine = Character.toString(charOnLine);
            int totalSpacesToAdd = 6 - toAdd.length();

            for (int k = 0; k < totalSpacesToAdd; k++) {
                toAdd += " ";
            }

            totalSpacesToAdd = 6 - toAdd.length();
            for (int k = 0; k < totalSpacesToAdd; k++) {
                toAdd += " ";
            }

            // redOccupations.add("A");
            if (redOccupations.contains(SpotOnLine)) {
                toAdd += "OCCUP";
            } else {
                toAdd += "unoc.";
            }

            redTrackStatesList.add(toAdd);
        }

        greenTrackStatesList.removeAll();
        greenTrackStatesList.add("SECT. OCCUP");
        for (int i = 0; i < greenSectionsArrayList.size(); i++) {
            String toAdd = greenSectionsArrayList.get(i);
            String SpotOnLine = toAdd;
            int totalSpacesToAdd = 6 - toAdd.length();

            for (int k = 0; k < totalSpacesToAdd; k++) {
                toAdd += " ";
            }

            totalSpacesToAdd = 6 - toAdd.length();
            for (int k = 0; k < totalSpacesToAdd; k++) {
                toAdd += " ";
            }

            if (greenOccupations.contains(SpotOnLine)) {
                toAdd += "OCCUP";
            } else {
                toAdd += "unoc.";
            }

            greenTrackStatesList.add(toAdd);
        }

    }

    /* This removes the train from the display once the train  
     * is back in the yard.
     */
    public void removeFromtrainsTable(String TrainNumber) {
        for (int i = 0; i < trainsTable.getItemCount(); i++) {
            if (trainsTable.getItem(i).getData().equals(TrainNumber)) {
                trainsTable.remove(i);
            }
        }

    }

    /* This updates my stored occupancy, throughput, crossings,  
     * switches, signals, etc.
     */
    public void updateXMLInput() {
        try {
            File inputFile = new File("./xml/TrackModelOutputs.xml");
            DocumentBuilderFactory docFactory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc2 = docBuilder.parse(inputFile);
            NodeList throughputList = doc2.getElementsByTagName("throughput");
            for (int temp = 0; temp < throughputList.getLength(); temp++) {
                Node nowNode = throughputList.item(temp);

                Element nowElement = (Element) nowNode;
                String value = nowElement.getTextContent();

                throughputFromXML = value;

            }

            NodeList throughList = doc2.getElementsByTagName("bit");
            redOccupations.clear();
            greenOccupations.clear();
            for (int temp = 0; temp < throughList.getLength(); temp++) {
                Node nowNode = throughList.item(temp);

                Element nowElement = (Element) nowNode;
                String Name = nowElement.getAttribute("name");
                String value = nowElement.getTextContent();

                char SetThis = Name.charAt(0);
                String Line = Character.toString(SetThis);

                Name = Name.substring(1);
                Name = Name.substring(0, Name.length() - 1);
                int tempSectValue = Integer.parseInt(Name);

                switch (Line) {
                case "G":
                    if (tempSectValue <= 3 && value.equals("0"))// a
                    {
                        greenOccupations.add("A");
                    } else if (tempSectValue >= 4 && tempSectValue <= 6
                            && value.equals("0"))// b
                    {
                        greenOccupations.add("B");
                    } else if (tempSectValue >= 7 && tempSectValue <= 12
                            && value.equals("0"))// c
                    {
                        greenOccupations.add("C");
                    } else if (tempSectValue >= 13 && tempSectValue <= 16
                            && value.equals("0"))// d
                    {
                        greenOccupations.add("D");
                    } else if (tempSectValue >= 17 && tempSectValue <= 20
                            && value.equals("0"))// e
                    {
                        greenOccupations.add("E");
                    } else if (tempSectValue >= 21 && tempSectValue <= 28
                            && value.equals("0"))// f
                    {
                        greenOccupations.add("F");
                    } else if (tempSectValue >= 29 && tempSectValue <= 32
                            && value.equals("0"))// g
                    {
                        greenOccupations.add("G");
                    } else if (tempSectValue >= 33 && tempSectValue <= 35
                            && value.equals("0"))// h
                    {
                        greenOccupations.add("H");
                    } else if (tempSectValue >= 36 && tempSectValue <= 57
                            && value.equals("0"))// i
                    {
                        greenOccupations.add("I");
                    } else if (tempSectValue >= 58 && tempSectValue <= 62
                            && value.equals("0"))// j
                    {
                        greenOccupations.add("J");
                    } else if (tempSectValue >= 63 && tempSectValue <= 68
                            && value.equals("0"))// k
                    {
                        greenOccupations.add("K");
                    } else if (tempSectValue >= 69 && tempSectValue <= 73
                            && value.equals("0"))// l
                    {
                        greenOccupations.add("L");
                    } else if (tempSectValue >= 74 && tempSectValue <= 76
                            && value.equals("0"))// m
                    {
                        greenOccupations.add("M");
                    } else if (tempSectValue >= 77 && tempSectValue <= 85
                            && value.equals("0"))// n
                    {
                        greenOccupations.add("N");
                    } else if (tempSectValue >= 86 && tempSectValue <= 88
                            && value.equals("0"))// o
                    {
                        greenOccupations.add("O");
                    } else if (tempSectValue >= 89 && tempSectValue <= 97
                            && value.equals("0"))// p
                    {
                        greenOccupations.add("P");
                    } else if (tempSectValue >= 98 && tempSectValue <= 100
                            && value.equals("0"))// q
                    {
                        greenOccupations.add("Q");
                    } else if (tempSectValue >= 101 && tempSectValue <= 101
                            && value.equals("0"))// r
                    {
                        greenOccupations.add("R");
                    } else if (tempSectValue >= 102 && tempSectValue <= 104
                            && value.equals("0"))// s
                    {
                        greenOccupations.add("S");
                    } else if (tempSectValue >= 105 && tempSectValue <= 109
                            && value.equals("0"))// t
                    {
                        greenOccupations.add("T");
                    } else if (tempSectValue >= 110 && tempSectValue <= 116
                            && value.equals("0"))// u
                    {
                        greenOccupations.add("U");
                    } else if (tempSectValue >= 117 && tempSectValue <= 121
                            && value.equals("0"))// v
                    {
                        greenOccupations.add("V");
                    } else if (tempSectValue >= 122 && tempSectValue <= 143
                            && value.equals("0"))// w
                    {
                        greenOccupations.add("W");
                    } else if (tempSectValue >= 144 && tempSectValue <= 146
                            && value.equals("0"))// x
                    {
                        greenOccupations.add("X");
                    } else if (tempSectValue >= 147 && tempSectValue <= 149
                            && value.equals("0"))// y
                    {
                        greenOccupations.add("Y");
                    } else if (tempSectValue >= 150 && tempSectValue <= 150
                            && value.equals("0"))// z
                    {
                        greenOccupations.add("Z");
                    }

                    break;

                case "R":
                    if (tempSectValue <= 3 && value.equals("0"))// a
                    {
                        redOccupations.add("A");
                    } else if (tempSectValue >= 4 && tempSectValue <= 6
                            && value.equals("0"))// b
                    {
                        redOccupations.add("B");
                    } else if (tempSectValue >= 7 && tempSectValue <= 9
                            && value.equals("0"))// c
                    {
                        redOccupations.add("C");
                    } else if (tempSectValue >= 10 && tempSectValue <= 12
                            && value.equals("0"))// d
                    {
                        redOccupations.add("D");
                    } else if (tempSectValue >= 13 && tempSectValue <= 15
                            && value.equals("0"))// e
                    {
                        redOccupations.add("E");
                    } else if (tempSectValue >= 16 && tempSectValue <= 20
                            && value.equals("0"))// f
                    {
                        redOccupations.add("F");
                    } else if (tempSectValue >= 21 && tempSectValue <= 23
                            && value.equals("0"))// g
                    {
                        redOccupations.add("G");
                    } else if (tempSectValue >= 24 && tempSectValue <= 45
                            && value.equals("0"))// h
                    {
                        redOccupations.add("H");
                    } else if (tempSectValue >= 46 && tempSectValue <= 48
                            && value.equals("0"))// i
                    {
                        redOccupations.add("I");
                    } else if (tempSectValue >= 49 && tempSectValue <= 54
                            && value.equals("0"))// j
                    {
                        redOccupations.add("J");
                    } else if (tempSectValue >= 55 && tempSectValue <= 57
                            && value.equals("0"))// k
                    {
                        redOccupations.add("K");
                    } else if (tempSectValue >= 58 && tempSectValue <= 60
                            && value.equals("0"))// l
                    {
                        redOccupations.add("L");
                    } else if (tempSectValue >= 61 && tempSectValue <= 63
                            && value.equals("0"))// m
                    {
                        redOccupations.add("M");
                    } else if (tempSectValue >= 64 && tempSectValue <= 66
                            && value.equals("0"))// n
                    {
                        redOccupations.add("N");
                    } else if (tempSectValue >= 67 && tempSectValue <= 67
                            && value.equals("0"))// o
                    {
                        redOccupations.add("O");
                    } else if (tempSectValue >= 68 && tempSectValue <= 70
                            && value.equals("0"))// p
                    {
                        redOccupations.add("P");
                    } else if (tempSectValue >= 71 && tempSectValue <= 71
                            && value.equals("0"))// q
                    {
                        redOccupations.add("Q");
                    } else if (tempSectValue >= 72 && tempSectValue <= 72
                            && value.equals("0"))// r
                    {
                        redOccupations.add("R");
                    } else if (tempSectValue >= 73 && tempSectValue <= 75
                            && value.equals("0"))// s
                    {
                        redOccupations.add("S");
                    } else if (tempSectValue >= 76 && tempSectValue <= 76
                            && value.equals("0"))// t
                    {
                        redOccupations.add("T");
                    }
                    break;

                default:
                    break;
                }

            }

        } catch (Exception e) {
        }

        try {
            File inputFile = new File("./xml/TrackControllerOutputs.xml");
            DocumentBuilderFactory docFactory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc2 = docBuilder.parse(inputFile);

            NodeList throughList = doc2.getElementsByTagName("bit");

            for (int temp = 0; temp < throughList.getLength(); temp++) {
                Node nowNode = throughList.item(temp);

                Element nowElement = (Element) nowNode;
                String Name = nowElement.getAttribute("name");
                String value = nowElement.getTextContent();

                char SetThis = Name.charAt(0);
                String Line = Character.toString(SetThis);

                String Type = Character
                        .toString(Name.charAt(Name.length() - 1));

                String NumberSection = "";
                Name = Name.substring(1);

                if (Type.equals("C")) {
                    String[] NumberSectionArray = Name.split("C");
                    NumberSection = NumberSectionArray[0];
                } else if (Type.equals("K")) {
                    NumberSection = Name.substring(0, Name.length() - 2);
                } else if (Type.equals("P")) {
                    String[] NumberSectionArray = Name.split("S");
                    NumberSection = NumberSectionArray[0];
                }

                if (Type.equals("C")) {
                    if (NumberSection.equals("19")) {
                        if (!crossingsArrayList.contains("G:E")) {
                            crossingsArrayList.add("G:E");
                        }
                        if (value.equals("1")) {
                            crossStates.put("G:E", "Up");
                        } else if (value.equals("0")) {
                            crossStates.put("G:E", "Down");
                        }
                    } else if (NumberSection.equals("47")) {
                        if (!crossingsArrayList.contains("R:I")) {
                            crossingsArrayList.add("R:I");
                        }

                        if (value.equals("1")) {
                            crossStates.put("R:I", "Up");
                        } else if (value.equals("0")) {
                            crossStates.put("R:I", "Down");
                        }
                    }
                } else if (Type.equals("K")) {
                    if (Line.equals("G")) {
                        if (NumberSection.equals("1")) {
                            if (Line.equals("G")) {
                                if (!signalsArrayList.contains("G1")) {
                                    signalsArrayList.add("G1");
                                }

                                if (value.equals("1")) {
                                    signalStates.put("G1", "Green");
                                } else if (value.equals("0")) {
                                    signalStates.put("G1", "Red");
                                }
                            } else if (Line.equals("R")) {
                                if (!signalsArrayList.contains("R1")) {
                                    signalsArrayList.add("R1");
                                }
                                if (value.equals("1")) {
                                    signalStates.put("R1", "Green");
                                } else if (value.equals("0")) {
                                    signalStates.put("R1", "Red");
                                }
                            }
                        } else if (NumberSection.equals("13")) {
                            if (!signalsArrayList.contains("G13"))
                                signalsArrayList.add("G13");
                            if (value.equals("1"))
                                signalStates.put("G13", "Green");
                            else if (value.equals("0"))
                                signalStates.put("G13", "Red");
                        } else if (NumberSection.equals("27")) {
                            if (!signalsArrayList.contains("G27"))
                                signalsArrayList.add("G27");
                            if (value.equals("1"))
                                signalStates.put("G27", "Green");
                            else if (value.equals("0"))
                                signalStates.put("G27", "Red");
                        } else if (NumberSection.equals("57")) {
                            if (!signalsArrayList.contains("G57"))
                                signalsArrayList.add("G57");
                            if (value.equals("1"))
                                signalStates.put("G57", "Green");
                            else if (value.equals("0"))
                                signalStates.put("G57", "Red");
                        } else if (NumberSection.equals("61")) {
                            if (!signalsArrayList.contains("G61"))
                                signalsArrayList.add("G61");
                            if (value.equals("1"))
                                signalStates.put("G61", "Green");
                            else if (value.equals("0"))
                                signalStates.put("G61", "Red");
                        } else if (NumberSection.equals("75")) {
                            if (!signalsArrayList.contains("G75"))
                                signalsArrayList.add("G75");
                            if (value.equals("1"))
                                signalStates.put("G75", "Green");
                            else if (value.equals("0"))
                                signalStates.put("G75", "Red");
                        } else if (NumberSection.equals("77")) {
                            if (!signalsArrayList.contains("G77"))
                                signalsArrayList.add("G77");
                            if (value.equals("1"))
                                signalStates.put("G77", "Green");
                            else if (value.equals("0"))
                                signalStates.put("G77", "Red");
                        } else if (NumberSection.equals("150")) {
                            if (!signalsArrayList.contains("G150"))
                                signalsArrayList.add("G150");
                            if (value.equals("1"))
                                signalStates.put("G150", "Green");
                            else if (value.equals("0"))
                                signalStates.put("G150", "Red");
                        }
                    } else if (Line.equals("R")) {
                        if (NumberSection.equals("1")) {
                            if (!signalsArrayList.contains("R1"))
                                signalsArrayList.add("R1");
                            if (value.equals("1"))
                                signalStates.put("R1", "Green");
                            else if (value.equals("0"))
                                signalStates.put("R1", "Red");
                        } else if (NumberSection.equals("8")) {
                            if (!signalsArrayList.contains("R8"))
                                signalsArrayList.add("R8");
                            if (value.equals("1"))
                                signalStates.put("R8", "Green");
                            else if (value.equals("0"))
                                signalStates.put("R8", "Red");
                        } else if (NumberSection.equals("10")) {
                            if (!signalsArrayList.contains("R10"))
                                signalsArrayList.add("R10");
                            if (value.equals("1"))
                                signalStates.put("R10", "Green");
                            else if (value.equals("0"))
                                signalStates.put("R10", "Red");
                        } else if (NumberSection.equals("14")) {
                            if (!signalsArrayList.contains("R14"))
                                signalsArrayList.add("R14");
                            if (value.equals("1"))
                                signalStates.put("R14", "Green");
                            else if (value.equals("0"))
                                signalStates.put("R14", "Red");
                        } else if (NumberSection.equals("16")) {
                            if (!signalsArrayList.contains("R16"))
                                signalsArrayList.add("R16");
                            if (value.equals("1"))
                                signalStates.put("R16", "Green");
                            else if (value.equals("0"))
                                signalStates.put("R16", "Red");
                        } else if (NumberSection.equals("26")) {
                            if (!signalsArrayList.contains("R26"))
                                signalsArrayList.add("R26");
                            if (value.equals("1"))
                                signalStates.put("R26", "Green");
                            else if (value.equals("0"))
                                signalStates.put("R26", "Red");
                        } else if (NumberSection.equals("28")) {
                            if (!signalsArrayList.contains("R28"))
                                signalsArrayList.add("R28");
                            if (value.equals("1"))
                                signalStates.put("R28", "Green");
                            else if (value.equals("0"))
                                signalStates.put("R28", "Red");
                        } else if (NumberSection.equals("31")) {
                            if (!signalsArrayList.contains("R31"))
                                signalsArrayList.add("R31");
                            if (value.equals("1"))
                                signalStates.put("R31", "Green");
                            else if (value.equals("0"))
                                signalStates.put("R31", "Red");
                        } else if (NumberSection.equals("33")) {
                            if (!signalsArrayList.contains("R33"))
                                signalsArrayList.add("R33");
                            if (value.equals("1"))
                                signalStates.put("R33", "Green");
                            else if (value.equals("0"))
                                signalStates.put("R33", "Red");
                        } else if (NumberSection.equals("37")) {
                            if (!signalsArrayList.contains("R37"))
                                signalsArrayList.add("R37");
                            if (value.equals("1"))
                                signalStates.put("R37", "Green");
                            else if (value.equals("0"))
                                signalStates.put("R37", "Red");
                        } else if (NumberSection.equals("37")) {
                            if (!signalsArrayList.contains("R37"))
                                signalsArrayList.add("R37");
                            if (value.equals("1"))
                                signalStates.put("R37", "Green");
                            else if (value.equals("0"))
                                signalStates.put("R37", "Red");
                        } else if (NumberSection.equals("39")) {
                            if (!signalsArrayList.contains("R39"))
                                signalsArrayList.add("R39");
                            if (value.equals("1"))
                                signalStates.put("R39", "Green");
                            else if (value.equals("0"))
                                signalStates.put("R39", "Red");
                        } else if (NumberSection.equals("42")) {
                            if (!signalsArrayList.contains("R42"))
                                signalsArrayList.add("R42");
                            if (value.equals("1"))
                                signalStates.put("R42", "Green");
                            else if (value.equals("0"))
                                signalStates.put("R42", "Red");
                        } else if (NumberSection.equals("76")) {
                            if (!signalsArrayList.contains("R76"))
                                signalsArrayList.add("R76");
                            if (value.equals("1"))
                                signalStates.put("R76", "Green");
                            else if (value.equals("0"))
                                signalStates.put("R76", "Red");
                        } else if (NumberSection.equals("67")) {
                            if (!signalsArrayList.contains("R67"))
                                signalsArrayList.add("R67");
                            if (value.equals("1"))
                                signalStates.put("R67", "Green");
                            else if (value.equals("0"))
                                signalStates.put("R67", "Red");
                        } else if (NumberSection.equals("71")) {
                            if (!signalsArrayList.contains("R71"))
                                signalsArrayList.add("R71");
                            if (value.equals("1"))
                                signalStates.put("R71", "Green");
                            else if (value.equals("0"))
                                signalStates.put("R71", "Red");
                        } else if (NumberSection.equals("72")) {
                            if (!signalsArrayList.contains("R72"))
                                signalsArrayList.add("R72");
                            if (value.equals("1"))
                                signalStates.put("R72", "Green");
                            else if (value.equals("0"))
                                signalStates.put("R72", "Red");
                        } else if (NumberSection.equals("44")) {
                            if (!signalsArrayList.contains("R44"))
                                signalsArrayList.add("R44");
                            if (value.equals("1"))
                                signalStates.put("R44", "Green");
                            else if (value.equals("0"))
                                signalStates.put("R44", "Red");
                        } else if (NumberSection.equals("51")) {
                            if (!signalsArrayList.contains("R51"))
                                signalsArrayList.add("R51");
                            if (value.equals("1"))
                                signalStates.put("R51", "Green");
                            else if (value.equals("0"))
                                signalStates.put("R51", "Red");
                        } else if (NumberSection.equals("53")) {
                            if (!signalsArrayList.contains("R53"))
                                signalsArrayList.add("R53");
                            if (value.equals("1"))
                                signalStates.put("R53", "Green");
                            else if (value.equals("0"))
                                signalStates.put("R53", "Red");
                        } else if (NumberSection.equals("66")) {
                            if (!signalsArrayList.contains("R66"))
                                signalsArrayList.add("R66");
                            if (value.equals("1"))
                                signalStates.put("R66", "Green");
                            else if (value.equals("0"))
                                signalStates.put("R66", "Red");
                        }

                    }
                }

                else if (Type.equals("P")) {
                    if (Line.equals("R")) {
                        if (NumberSection.equals("1")) {
                            if (!switchesArrayList.contains("R:CYd"))
                                switchesArrayList.add("R:CYd");

                            if (value.equals("1"))
                                switchStates.put("R:CYd", "Normal");
                            else if (value.equals("0"))
                                switchStates.put("R:CYd", "Reverse");
                        } else if (NumberSection.equals("2")) {
                            if (!switchesArrayList.contains("R:E"))
                                switchesArrayList.add("R:E");

                            if (value.equals("1"))
                                switchStates.put("R:E", "Normal");
                            else if (value.equals("0"))
                                switchStates.put("R:E", "Reverse");
                        } else if (NumberSection.equals("3")) {
                            if (!switchesArrayList.contains("R:TH"))
                                switchesArrayList.add("R:TH");

                            if (value.equals("1"))
                                switchStates.put("R:TH", "Normal");
                            else if (value.equals("0"))
                                switchStates.put("R:TH", "Reverse");
                        } else if (NumberSection.equals("4")) {
                            if (!switchesArrayList.contains("R:RH"))
                                switchesArrayList.add("R:RH");

                            if (value.equals("1"))
                                switchStates.put("R:RH", "Normal");
                            else if (value.equals("0"))
                                switchStates.put("R:RH", "Reverse");
                        } else if (NumberSection.equals("5")) {
                            if (!switchesArrayList.contains("R:QH"))
                                switchesArrayList.add("R:QH");

                            if (value.equals("1"))
                                switchStates.put("R:QH", "Normal");
                            else if (value.equals("0"))
                                switchStates.put("R:QH", "Reverse");
                        } else if (NumberSection.equals("6")) {
                            if (!switchesArrayList.contains("R:OH"))
                                switchesArrayList.add("R:OH");

                            if (value.equals("1"))
                                switchStates.put("R:OH", "Normal");
                            else if (value.equals("0"))
                                switchStates.put("R:OH", "Reverse");
                        } else if (NumberSection.equals("7")) {
                            if (!switchesArrayList.contains("R:NJ"))
                                switchesArrayList.add("R:NJ");

                            if (value.equals("1"))
                                switchStates.put("R:NJ", "Normal");
                            else if (value.equals("0"))
                                switchStates.put("R:NJ", "Reverse");
                        }
                    } else if (Line.equals("G")) {
                        if (NumberSection.equals("1")) {
                            if (!switchesArrayList.contains("G:C"))
                                switchesArrayList.add("G:C");

                            if (value.equals("1"))
                                switchStates.put("G:C", "Normal");
                            else if (value.equals("0"))
                                switchStates.put("G:C", "Reverse");
                        } else if (NumberSection.equals("2")) {
                            if (!switchesArrayList.contains("G:G"))
                                switchesArrayList.add("G:G");

                            if (value.equals("1"))
                                switchStates.put("G:G", "Normal");
                            else if (value.equals("0"))
                                switchStates.put("G:G", "Reverse");
                        } else if (NumberSection.equals("3")) {
                            if (!switchesArrayList.contains("G:JToYd"))
                                switchesArrayList.add("G:JToYd");

                            if (value.equals("1"))
                                switchStates.put("G:JToYd", "Normal");
                            else if (value.equals("0"))
                                switchStates.put("G:JToYd", "Reverse");
                        } else if (NumberSection.equals("4")) {
                            if (!switchesArrayList.contains("G:JFmYd"))
                                switchesArrayList.add("G:JFmYd");

                            if (value.equals("1"))
                                switchStates.put("G:JFmYd", "Normal");
                            else if (value.equals("0"))
                                switchStates.put("G:JFmYd", "Reverse");
                        } else if (NumberSection.equals("5")) {
                            if (!switchesArrayList.contains("G:M"))
                                switchesArrayList.add("G:M");

                            if (value.equals("1"))
                                switchStates.put("G:M", "Normal");
                            else if (value.equals("0"))
                                switchStates.put("G:M", "Reverse");
                        } else if (NumberSection.equals("6")) {
                            if (!switchesArrayList.contains("G:O"))
                                switchesArrayList.add("G:O");

                            if (value.equals("1"))
                                switchStates.put("G:O", "Normal");
                            else if (value.equals("0"))
                                switchStates.put("G:O", "Reverse");
                        }

                    }

                }

            }

        } catch (Exception e) {
        }

    }

    /* This requests switch changes based on where trains are on the track.
     * This is the routing being done.
     */
    public void routingCheck() {
        if (greenOccupations.contains("L")) {
            // Switch for NM(5 needs normal
            requestSend("Switch", "G:M", "Flip", "Normal");
        }
        if (greenOccupations.contains("M")) {
            // switch for NO(6 needs normal
            requestSend("Switch", "G:O", "Flip", "Normal");
        }
        if (greenOccupations.contains("P")) {
            // switch for NO(6 needs reverse
            requestSend("Switch", "G:O", "Flip", "Reverse");
        }
        if (greenOccupations.contains("Q")) {
            // switch for MNR(5 needs reverse
            requestSend("Switch", "G:M", "Flip", "Reverse");
        }
        if (greenOccupations.contains("S")) {
            // switch for MNR(5 needs normal
            requestSend("Switch", "G:M", "Flip", "Normal");
        }
        if (greenOccupations.contains("Y")) {
            // switch for ZFG(2 needs reverse
            requestSend("Switch", "G:G", "Flip", "Reverse");
        }
        if (greenOccupations.contains("E")) {
            // switch for DAC(1 needs normal
            requestSend("Switch", "G:", "Flip", "Normal");
            // switch for ZFG(2 needs normal
            requestSend("Switch", "G:", "Flip", "Normal");
        }
        if (greenOccupations.contains("B")) {
            // switch for DAC(1 needs reverse
            requestSend("Switch", "G:C", "Flip", "Reverse");
        }
        if (greenOccupations.contains("H")) {
            // switch for IYd(3 needs to yard?
            requestSend("Switch", "G:JToYd", "Flip", "Reverse");
        }
        if (greenOccupations.contains("I")) {
            // if ItoYd(3 is going from IJ(Normal, AwayFromYard(4 needs Normal
            if (switchStates.get("G:JToYd").equals("Normal"))
                requestSend("Switch", "G:JFromYd", "Flip", "Normal");

        }

        if (redOccupations.contains("G")) {
            // E(2 switch to reverse
            requestSend("Switch", "R:E", "Flip", "Reverse");
        }
        if (redOccupations.contains("B")) {
            // CYd(1 to normal
            requestSend("Switch", "R:CYd", "Flip", "Reverse");
         }

        if (redOccupations.contains("G")) {
            // HT(3, HR(4, HQ(5, HO(6 need normal
            requestSend("Switch", "R:TH", "Flip", "Normal");
            requestSend("Switch", "R:RH", "Flip", "Normal");
            requestSend("Switch", "R:QH", "Flip", "Normal");
            requestSend("Switch", "R:OH", "Flip", "Normal");
        }
        if (redOccupations.contains("I")) {
            // JN(7) needs normal
            requestSend("Switch", "R:J", "Flip", "Normal");
        }
        if (redOccupations.contains("M")) {
            // JN(7) needs reverse
            requestSend("Switch", "R:J", "Flip", "Reverse");
        }
        if (redOccupations.contains("K")) {
            // JN(7) needs normal
            requestSend("Switch", "R:TH", "Flip", "Normal");
        }
    }

    /* This writes the current multiplier my module has, to 
     * a general file to be read by other modules.
     */
    public void writeMultiplier(String Amount) {
        try {
            File myFoo = new File("./xml/multiplier.txt");
            FileWriter fooWriter = new FileWriter(myFoo, false);

            fooWriter.write(Amount);
            fooWriter.close();
        } catch (Exception e) {
        }
    }

}
