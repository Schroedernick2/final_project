import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.jface.viewers.TableViewerColumn;
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
import org.eclipse.swt.events.TouchListener;
import org.eclipse.swt.events.TouchEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

import javax.swing.JFrame;

import java.util.*;
import java.io.*;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.jface.text.TextViewer;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;
import org.eclipse.swt.widgets.DateTime;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class UITest {

	protected Shell shlTrackModel;
	private Text SuggSpeedText;
	private Text SuggAuthText;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());

	static HashMap<String, String> CrossStates = new HashMap<String, String>();
	static HashMap<String, String> SignalStates = new HashMap<String, String>();
	static HashMap<String, String> SwitchStates = new HashMap<String, String>();
	static HashMap<String, String> GreenSectionStates = new HashMap<String, String>();
	static HashMap<String, String> RedSectionStates = new HashMap<String, String>();

	static Document doc;
    static int automatic=0;
    
    static List TimeList;
    static List InfrList;
    static List LineList;
    static long startCycleTime;
    static Label lblHhmmss;
    static Combo AutoChoiceCombo;

	static long differenceClock = 0;
	static long StartClockTime = System.currentTimeMillis();
    
	static Label ThroughputText;
	static String throughputFromXML="0";
	
	static int CurrentIndexSchedule=0;
	
	static Label LineSentText;
	static Label InfrSentText;
	static Label TrainSentText;
    static Combo TrainNumberCombo;
	
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		//Populate the hashmaps to start
		
	
		
		
		
		
		
		//make an xml file
		/**
		try {
			 DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			         doc = dBuilder.newDocument();
			}catch (Exception e) {}
			
		
		// root element
        Element root = doc.createElement("bits");
        doc.appendChild(root);
		
        
        // Xing element
        Element Crossings = doc.createElement("Crossings");
        root.appendChild(Crossings);
		
        // setting attribute to element
        //Attr attr = doc.createAttribute("Attr");
        //attr.setValue("A");
        //Crossings.setAttributeNode(attr);
        
        // Xing element
        Element XingName = doc.createElement("Xing");
        Attr attrType = doc.createAttribute("Line");
        attrType.setValue("Red");
        XingName.setAttributeNode(attrType);
        XingName.appendChild(doc.createTextNode("Up"));
        Crossings.appendChild(XingName);
        
       try { 
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("C:\\Users\\William\\eclipse-workspace\\UITestProject\\test.xml"));
        transformer.transform(source, result);
       }catch (Exception e) {}
       
        **/
        
        
		 /**
		CrossStates.put("Green:E","up");CrosdsStates.put("Red:I", "down");
		
		SignalStates.put("R:CDYard", "red");SignalStates.put("R:AEF", "green");SignalStates.put("R:TH", "red");
		SignalStates.put("R:RH", "green");SignalStates.put("R:QH", "red");SignalStates.put("R:OH", "green");SignalStates.put("R:NJ", "red");
		SignalStates.put("G:MNR", "green");SignalStates.put("G:NOQ", "red");SignalStates.put("G:ZFG", "green");SignalStates.put("G:DAC", "red");

		SwitchStates.put("R:CDYard", "normal");SwitchStates.put("R:AEF", "rev");SwitchStates.put("R:TH", "normal");
		SwitchStates.put("R:RH", "rev");SwitchStates.put("R:QH", "normal");SwitchStates.put("R:OH", "rev");SwitchStates.put("R:NJ", "normal");
		SwitchStates.put("G:MNR", "rev");SwitchStates.put("G:NOQ", "normal");SwitchStates.put("G:ZFG", "rev");SwitchStates.put("G:DAC", "normal");
		
		GreenSectionStates.put("A", "open");GreenSectionStates.put("B", "closed");
		GreenSectionStates.put("C", "open");GreenSectionStates.put("D", "closed");GreenSectionStates.put("E", "open");GreenSectionStates.put("F", "closed");
		GreenSectionStates.put("G", "open");GreenSectionStates.put("H", "closed");GreenSectionStates.put("I", "open");GreenSectionStates.put("J", "closed");
		GreenSectionStates.put("K", "open");GreenSectionStates.put("L", "closed");GreenSectionStates.put("M", "open");GreenSectionStates.put("N", "closed");
		GreenSectionStates.put("O", "open");GreenSectionStates.put("P", "open");GreenSectionStates.put("Q", "open");GreenSectionStates.put("R", "open");
		GreenSectionStates.put("S", "open");GreenSectionStates.put("T", "open");GreenSectionStates.put("U", "open");GreenSectionStates.put("V", "open");
		GreenSectionStates.put("W", "open");GreenSectionStates.put("X", "open");GreenSectionStates.put("Y", "open");GreenSectionStates.put("Z", "open");

		
		RedSectionStates.put("A", "open");RedSectionStates.put("B", "open");RedSectionStates.put("C", "open");RedSectionStates.put("D", "open");
		RedSectionStates.put("E", "open");RedSectionStates.put("F", "open");RedSectionStates.put("G", "open");RedSectionStates.put("H", "open");
		RedSectionStates.put("I", "open");RedSectionStates.put("J", "open");RedSectionStates.put("K", "open");RedSectionStates.put("L", "open");
		RedSectionStates.put("M", "open");RedSectionStates.put("N", "open");RedSectionStates.put("O", "open");RedSectionStates.put("P", "open");
		RedSectionStates.put("Q", "open");RedSectionStates.put("R", "open");RedSectionStates.put("S", "open");RedSectionStates.put("T", "open");
		*/
		
		
		
		
		 
		 
		
		
		try {
			UITest window = new UITest();
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
		createContents();
		shlTrackModel.open();
		shlTrackModel.layout();
		long NowClockTime = System.currentTimeMillis();
		
		while (!shlTrackModel.isDisposed()) {
			
			updateXMLInput();
			
			NowClockTime = System.currentTimeMillis();
			differenceClock = (NowClockTime-StartClockTime)/1000;
			
			lblHhmmss.setText(Long.toString(differenceClock));
			
			ThroughputText.setText(throughputFromXML);
			
			
			
			
			
			
			
			
			
			
			//This causes the stuff to not update
			
			if ((automatic==1) && (TimeList.getItemCount()!=0)) {
				//shlTrackModel.close()
		            	long elapsedTime = System.currentTimeMillis() - startCycleTime;
		            	long elapsedSeconds = elapsedTime / 1000;
		            	double CurrentTrainTime = Double.parseDouble(TimeList.getItem(0).toString());
		            	if (elapsedSeconds>CurrentTrainTime)
		            	{
		            		DispatchAuto(TrainNumberCombo.getItem(CurrentIndexSchedule),"0","0",InfrList.getItem(0), LineList.getItem(0));
		            		
		            		startCycleTime = System.currentTimeMillis();
		      
		            		//LineSentText.setText(LineList.getItem(0));
		            		//InfrSentText.setText(InfrList.getItem(0));
		            		//TrainSentText.setText(TrainNumberCombo.getItem(CurrentIndexSchedule));

		            		
		            		
		            		
		            		if (CurrentIndexSchedule >= (4))
		            		{
		            			CurrentIndexSchedule=0;
		            			automatic=0;
		            			AutoChoiceCombo.select(0);
		            		}
		            		else {
		            			CurrentIndexSchedule=CurrentIndexSchedule+1;
		            		}	
		            	}
			}
			else if ((TimeList.getItemCount() == 0) && automatic==1)
			{
				AutoChoiceCombo.select(0);
				automatic = 0;
			}
			//shlTrackModel.getDisplay();
			shlTrackModel.pack();	
			
			
			
		/**	THIS COMMENTED OUT STUFF WORKED WITH SENDING @ EACH STATION FROM THE SCHEDULE
			//This causes the stuff to not update
			
			if ((automatic==1) && (TimeList.getItemCount()!=0)) {
				//shlTrackModel.close()
		            	long elapsedTime = System.currentTimeMillis() - startCycleTime;
		            	long elapsedSeconds = elapsedTime / 1000;
		            	double CurrentTrainTime = Double.parseDouble(TimeList.getItem(0).toString());
		            	if (elapsedSeconds>CurrentTrainTime)
		            	{
		            		Dispatch(InfrList.getItem(CurrentIndexSchedule).toString(),"0","0",LineList.getItem(CurrentIndexSchedule).toString(), "NA");
		            		
		            		startCycleTime = System.currentTimeMillis();
		      
		            		LineSentText.setText(LineList.getItem(CurrentIndexSchedule));
		            		InfrSentText.setText(InfrList.getItem(CurrentIndexSchedule));
		            		TimeSentText.setText(TimeList.getItem(CurrentIndexSchedule));
		            		if (CurrentIndexSchedule >= (TimeList.getItemCount()-1))
		            		{
		            			CurrentIndexSchedule=0;
		            		}
		            		else {
		            			CurrentIndexSchedule=CurrentIndexSchedule+1;
		            		}	
		            	}
			}
			else if ((TimeList.getItemCount() == 0) && automatic==1)
			{
				AutoChoiceCombo.select(0);
				automatic = 0;
			}
			//shlTrackModel.getDisplay();
			shlTrackModel.pack();
		*/	
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			if (!display.readAndDispatch()) {
				display.sleep();
			}
			
			
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlTrackModel = new Shell();
		shlTrackModel.setSize(1233, 771);
		shlTrackModel.setText("OCC");
		
		Menu menu = new Menu(shlTrackModel, SWT.BAR);
		shlTrackModel.setMenuBar(menu);
		
		MenuItem mntmFile = new MenuItem(menu, SWT.CASCADE);
		mntmFile.setText("File");
		
		Menu menu_1 = new Menu(mntmFile);
		mntmFile.setMenu(menu_1);
		

		ListViewer listViewer = new ListViewer(shlTrackModel, SWT.BORDER | SWT.V_SCROLL);
		LineList = listViewer.getList();
		LineList.setBounds(814, 75, 55, 261);
		
		
		ListViewer listViewer2 = new ListViewer(shlTrackModel, SWT.BORDER | SWT.V_SCROLL);
		InfrList = listViewer2.getList();
			//InfrList = new List(shlTrackModel, SWT.BORDER);
		InfrList.setBounds(875, 75, 246, 261);
			//formToolkit.adapt(InfrList, true, true);
	
		ListViewer listViewer3 = new ListViewer(shlTrackModel, SWT.BORDER | SWT.V_SCROLL);
		TimeList = listViewer3.getList();
			//TimeList = new List(shlTrackModel, SWT.BORDER);
		TimeList.setBounds(1127, 75, 55, 261);
			//formToolkit.adapt(TimeList, true, true);
		
		AutoChoiceCombo = new Combo(shlTrackModel, SWT.NONE);
		AutoChoiceCombo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String AutoChoice = AutoChoiceCombo.getItem(AutoChoiceCombo.getSelectionIndex()).toString();
				if (AutoChoice.equals("ON"))
				{
					automatic = 1;
				}
				else if (AutoChoice.equals("OFF"))
				{
					automatic=0;
				}
			
			}
		});
		AutoChoiceCombo.setItems(new String[] {"OFF", "ON"});
		AutoChoiceCombo.setBounds(422, 22, 55, 23);
		AutoChoiceCombo.select(0);
		AutoChoiceCombo.setText("OFF");

		
		MenuItem mntmLoadSchedule = new MenuItem(menu_1, SWT.NONE);
		mntmLoadSchedule.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {

		        JFileChooser fileChooser = new JFileChooser();
				
				if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
					java.io.File file = fileChooser.getSelectedFile();
					
					//Line.add("Line   Infr   Time");
					String line;
					
				    try {
				    	TimeList.removeAll();
				    	InfrList.removeAll();
				    	LineList.removeAll();
				    	BufferedReader br = new BufferedReader(new FileReader(file));
				    	line = br.readLine();
				    	while ((line = br.readLine()) != null) {
			                // use comma as separator
			                String[] data = line.split(",");
			                LineList.add(data[0]);
			                InfrList.add(data[1]);
			                TimeList.add(data[2]);
			                
			            	}
				    	startCycleTime = System.currentTimeMillis();
			            br.close();
			            CurrentIndexSchedule=0;
			            automatic=1;

			    		AutoChoiceCombo.select(1);
			            
			            //do schedule stuff auto now
			            //while ((TimeList.getItemCount()!=0) && (AutoChoiceCombo.getItem(AutoChoiceCombo.getSelectionIndex()).toString().equals("ON"))) {
			            	
			            
			        /**    
			            do{
			            	long elapsedTime = System.currentTimeMillis() - startCycleTime;
			            	long elapsedSeconds = elapsedTime / 1000;
			            	
			            	long CurrentTrainTime = Long.parseLong(TimeList.getItem(0).toString());
			            	if (elapsedSeconds>CurrentTrainTime)
			            	{
			            		TimeList.add("REMOVEHERE!");
			            		TimeList.remove(0);
			            	}
			            	if (elapsedSeconds!=0)
			            	{
			            		TimeList.add("REMOVEHERE!");
			            		TimeList.remove(0);
			            	}
			            	
			            }while (TimeList.getItemCount()!=0);
			            
			    **/
			            
				    } catch (Exception ee)
				    {
				    	
				    }
		            
				}

			}
		});
		mntmLoadSchedule.setText("Load Schedule");
		
		Label lblSchedule = new Label(shlTrackModel, SWT.NONE);
		lblSchedule.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		lblSchedule.setBounds(946, 0, 134, 27);
		lblSchedule.setText("Schedule");
		
		SuggSpeedText = new Text(shlTrackModel, SWT.BORDER);
		SuggSpeedText.setText("0");
		SuggSpeedText.setBounds(1020, 591, 40, 21);
		
		SuggAuthText = new Text(shlTrackModel, SWT.BORDER);
		SuggAuthText.setText("0");
		SuggAuthText.setBounds(1020, 618, 40, 21);
		
		CLabel label = new CLabel(shlTrackModel, SWT.NONE);
		label.setText("AUTO:");
		label.setBounds(376, 24, 40, 21);
		
		Combo DispLineCombo = new Combo(shlTrackModel, SWT.NONE);
		DispLineCombo.setItems(new String[] {"Red", "Green"});
		DispLineCombo.setBounds(971, 500, 70, 23);
		DispLineCombo.select(0);
		DispLineCombo.setText("Red");

		TrainNumberCombo = new Combo(shlTrackModel, SWT.NONE);
		TrainNumberCombo.setItems(new String[] {"Train_1", "Train_2", "Train_3", "Train_4", "Train_5", "Train_6", "Train_7", "Train_8", "Train_9", "Train_10"});
		TrainNumberCombo.setBounds(952, 562, 109, 23);
		TrainNumberCombo.select(0);
		TrainNumberCombo.setText("Train_1");
		
		Combo DispBlockCombo = new Combo(shlTrackModel, SWT.NONE);
		DispBlockCombo.setItems(new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"});
		DispBlockCombo.setBounds(971, 527, 70, 23);
		DispBlockCombo.select(0);
		DispBlockCombo.setText("A");

		Combo ComboAuthUnits = new Combo(shlTrackModel, SWT.NONE);
		ComboAuthUnits.setItems(new String[] {"yds", "ft"});
		ComboAuthUnits.setToolTipText("");
		ComboAuthUnits.setBounds(1066, 618, 48, 23);
		ComboAuthUnits.select(0);
		ComboAuthUnits.setText("yds");
		
		Button btnDispatch = new Button(shlTrackModel, SWT.NONE);
		btnDispatch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String trainNumber=TrainNumberCombo.getItem(TrainNumberCombo.getSelectionIndex()).toString();
				String SuggSpeed=SuggSpeedText.getText();
				String SuggAuth=SuggAuthText.getText()+ComboAuthUnits.getItem(ComboAuthUnits.getSelectionIndex()).toString();
				String DestLine=DispLineCombo.getItem(DispLineCombo.getSelectionIndex()).toString();
				String DestBlock=DispBlockCombo.getItem(DispBlockCombo.getSelectionIndex()).toString();
				
				
				Dispatch(trainNumber, SuggSpeed, SuggAuth, DestLine, DestBlock);
				//String trainNumber, String SuggSpeed, String SuggAuth, String DestLine, String DestBlock
			}
		});
		btnDispatch.setBounds(971, 649, 75, 25);
		btnDispatch.setText("Dispatch");
		
		Label lblSpeed = new Label(shlTrackModel, SWT.NONE);
		lblSpeed.setBounds(946, 591, 55, 21);
		lblSpeed.setText("Speed:");
		
		Label lblAuthority = new Label(shlTrackModel, SWT.NONE);
		lblAuthority.setText("Authority:");
		lblAuthority.setBounds(946, 618, 68, 21);
		
		Label lblNewLabel = new Label(shlTrackModel, SWT.NONE);
		lblNewLabel.setBounds(1066, 591, 55, 25);
		lblNewLabel.setText("mph");
		
		lblHhmmss = new Label(shlTrackModel, SWT.NONE);
		lblHhmmss.setBounds(613, 50, 82, 25);
		lblHhmmss.setText("Seconds");
		
		ImageHyperlink mghprlnkNewImagehyperlink = formToolkit.createImageHyperlink(shlTrackModel, SWT.NONE);
		mghprlnkNewImagehyperlink.setImage(SWTResourceManager.getImage("Medium.jpg"));
		mghprlnkNewImagehyperlink.setBounds(362, 81, 446, 582);
		formToolkit.paintBordersFor(mghprlnkNewImagehyperlink);
		mghprlnkNewImagehyperlink.setText("New ImageHyperlink");
		
		
		Label lblLine = new Label(shlTrackModel, SWT.NONE);
		lblLine.setBounds(925, 503, 40, 20);
		formToolkit.adapt(lblLine, true, true);
		lblLine.setText("Line");
		
		Label lblBlock = new Label(shlTrackModel, SWT.NONE);
		lblBlock.setBounds(925, 530, 40, 20);
		formToolkit.adapt(lblBlock, true, true);
		lblBlock.setText("Block");
		
		Label lblLine_1 = new Label(shlTrackModel, SWT.NONE);
		lblLine_1.setBounds(828, 49, 27, 20);
		formToolkit.adapt(lblLine_1, true, true);
		lblLine_1.setText("Line");
		
		Label lblInfrastr = new Label(shlTrackModel, SWT.NONE);
		lblInfrastr.setBounds(952, 49, 90, 20);
		formToolkit.adapt(lblInfrastr, true, true);
		lblInfrastr.setText("Infrastructure");
		
		Label lblTime = new Label(shlTrackModel, SWT.NONE);
		lblTime.setBounds(1127, 49, 48, 20);
		formToolkit.adapt(lblTime, true, true);
		lblTime.setText("Time");
		
		Label lblDispatching = new Label(shlTrackModel, SWT.NONE);
		lblDispatching.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		lblDispatching.setBounds(957, 458, 117, 36);
		formToolkit.adapt(lblDispatching, true, true);
		lblDispatching.setText("Dispatch");
		
		Label lblClose = new Label(shlTrackModel, SWT.NONE);
		lblClose.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		lblClose.setBounds(10, 513, 169, 28);
		formToolkit.adapt(lblClose, true, true);
		lblClose.setText("Close Section");
		
		Combo LineToClose = new Combo(shlTrackModel, SWT.NONE);
		LineToClose.setItems(new String[] {"Red", "Green"});
		LineToClose.setBounds(83, 550, 70, 28);
		formToolkit.adapt(LineToClose);
		formToolkit.paintBordersFor(LineToClose);
		LineToClose.select(0);
		LineToClose.setText("Green");
		
		Combo BlockToClose = new Combo(shlTrackModel, SWT.NONE);
		BlockToClose.setItems(new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"});
		BlockToClose.setBounds(83, 577, 70, 28);
		formToolkit.adapt(BlockToClose);
		formToolkit.paintBordersFor(BlockToClose);
		BlockToClose.select(0);
		BlockToClose.setText("A");
		
		Label label_1 = new Label(shlTrackModel, SWT.NONE);
		label_1.setText("Line");
		label_1.setBounds(37, 553, 40, 20);
		formToolkit.adapt(label_1, true, true);
		
		Label label_2 = new Label(shlTrackModel, SWT.NONE);
		label_2.setText("Block");
		label_2.setBounds(37, 580, 40, 20);
		formToolkit.adapt(label_2, true, true);
		
		Button btnCloseSection = new Button(shlTrackModel, SWT.NONE);
		btnCloseSection.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String WhatToChange = "Close";
				String Label=LineToClose.getItem(LineToClose.getSelectionIndex()).toString()+":"+BlockToClose.getItem(BlockToClose.getSelectionIndex()).toString();
				String WhatToDo="Close";
				RequestSend(WhatToChange, Label, WhatToDo);
			}
		});
		btnCloseSection.setBounds(47, 611, 90, 30);
		formToolkit.adapt(btnCloseSection, true, true);
		btnCloseSection.setText("Close Section");
		
		Label lblFlipSwitch = new Label(shlTrackModel, SWT.NONE);
		lblFlipSwitch.setText("Flip Switch");
		lblFlipSwitch.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		lblFlipSwitch.setBounds(27, 387, 123, 28);
		formToolkit.adapt(lblFlipSwitch, true, true);
		
		Combo SwitchToFlip = new Combo(shlTrackModel, SWT.NONE);
		SwitchToFlip.setItems(new String[] {"R:CDYard", "R:AEF", "R:TH", "R:RH", "R:QH", "R:OH", "R:NJ", "G:MNR", "G:NOQ", "G:ZFG", "G:DAC"});
		SwitchToFlip.setBounds(73, 421, 70, 28);
		formToolkit.adapt(SwitchToFlip);
		formToolkit.paintBordersFor(SwitchToFlip);
		SwitchToFlip.select(0);
		
		Label lblSwitch_1 = new Label(shlTrackModel, SWT.NONE);
		lblSwitch_1.setText("Switch");
		lblSwitch_1.setBounds(10, 426, 57, 20);
		formToolkit.adapt(lblSwitch_1, true, true);
		
		Button btnFlipSwitch = new Button(shlTrackModel, SWT.NONE);
		btnFlipSwitch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String WhatToChange = "Switch";
				String Label=SwitchToFlip.getItem(SwitchToFlip.getSelectionIndex()).toString();
				String WhatToDo="Flip";
				RequestSend(WhatToChange, Label, WhatToDo);
			}
		});
		btnFlipSwitch.setText("Flip Switch");
		btnFlipSwitch.setBounds(37, 455, 90, 30);
		formToolkit.adapt(btnFlipSwitch, true, true);
		
		Label lblSwitchState = new Label(shlTrackModel, SWT.NONE);
		lblSwitchState.setText("Switch State");
		lblSwitchState.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		lblSwitchState.setBounds(212, 387, 144, 28);
		formToolkit.adapt(lblSwitchState, true, true);

		TextViewer textViewer = new TextViewer(shlTrackModel, SWT.BORDER);
		StyledText SwitchState = textViewer.getTextWidget();
		SwitchState.setBounds(257, 460, 71, 24);
		formToolkit.paintBordersFor(SwitchState);
		
		Combo SwitchToSee = new Combo(shlTrackModel, SWT.NONE);
		SwitchToSee.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				SwitchState.setText(SwitchStates.get(SwitchToSee.getItem(SwitchToSee.getSelectionIndex()).toString()));
			}
		});
		SwitchToSee.setItems(new String[] {"R:CDYard", "R:AEF", "R:TH", "R:RH", "R:QH", "R:OH", "R:NJ", "G:MNR", "G:NOQ", "G:ZFG", "G:DAC"});
		SwitchToSee.setBounds(258, 421, 70, 28);
		formToolkit.adapt(SwitchToSee);
		formToolkit.paintBordersFor(SwitchToSee);
		SwitchToSee.select(0);
		
		Label lblSwitch = new Label(shlTrackModel, SWT.NONE);
		lblSwitch.setText("Switch");
		lblSwitch.setBounds(197, 424, 55, 20);
		formToolkit.adapt(lblSwitch, true, true);
		
		Label lblState = new Label(shlTrackModel, SWT.NONE);
		lblState.setBounds(212, 462, 40, 20);
		formToolkit.adapt(lblState, true, true);
		lblState.setText("State");
		
		Label lblSignalState = new Label(shlTrackModel, SWT.NONE);
		lblSignalState.setText("Signal State");
		lblSignalState.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		lblSignalState.setBounds(212, 281, 144, 28);
		formToolkit.adapt(lblSignalState, true, true);
		
		TextViewer textViewer_1 = new TextViewer(shlTrackModel, SWT.BORDER);
		StyledText SignalState = textViewer_1.getTextWidget();
		SignalState.setBounds(257, 354, 71, 24);
		formToolkit.paintBordersFor(SignalState);
		
		Combo SignalToSee = new Combo(shlTrackModel, SWT.NONE);
		SignalToSee.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SignalState.setText(SignalStates.get(SignalToSee.getItem(SignalToSee.getSelectionIndex()).toString()));
			}
		});
		SignalToSee.setItems(new String[] {"R:CDYard", "R:AEF", "R:TH", "R:RH", "R:QH", "R:OH", "R:NJ", "G:MNR", "G:NOQ", "G:ZFG", "G:DAC"});
		SignalToSee.setBounds(258, 315, 70, 28);
		formToolkit.adapt(SignalToSee);
		formToolkit.paintBordersFor(SignalToSee);
		SignalToSee.select(0);
		
		Label lblSignal = new Label(shlTrackModel, SWT.NONE);
		lblSignal.setText("Signal");
		lblSignal.setBounds(197, 318, 55, 20);
		formToolkit.adapt(lblSignal, true, true);
		
		Label label_5 = new Label(shlTrackModel, SWT.NONE);
		label_5.setText("State");
		label_5.setBounds(212, 356, 40, 20);
		formToolkit.adapt(label_5, true, true);
		
		Label lblChangeCrossing = new Label(shlTrackModel, SWT.NONE);
		lblChangeCrossing.setText("Change Cross");
		lblChangeCrossing.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		lblChangeCrossing.setBounds(10, 164, 152, 28);
		formToolkit.adapt(lblChangeCrossing, true, true);
		
		Combo ComboCrossToChange = new Combo(shlTrackModel, SWT.NONE);
		ComboCrossToChange.setItems(new String[] {"Green:E", "Red:I"});
		ComboCrossToChange.setBounds(73, 198, 70, 28);
		formToolkit.adapt(ComboCrossToChange);
		formToolkit.paintBordersFor(ComboCrossToChange);
		ComboCrossToChange.select(0);
		
		Label lblCrossing_1 = new Label(shlTrackModel, SWT.NONE);
		lblCrossing_1.setText("Crossing");
		lblCrossing_1.setBounds(10, 203, 57, 20);
		formToolkit.adapt(lblCrossing_1, true, true);
		
		Button btnChangeCross = new Button(shlTrackModel, SWT.NONE);
		btnChangeCross.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			
				String WhatToChange = "Crossing";
				String Label=ComboCrossToChange.getItem(ComboCrossToChange.getSelectionIndex()).toString();
				String WhatToDo="Change";
				RequestSend(WhatToChange, Label, WhatToDo);
			
			}
		});
		btnChangeCross.setText("Change Xing");
		btnChangeCross.setBounds(37, 232, 90, 30);
		formToolkit.adapt(btnChangeCross, true, true);
		
		Label lblCrossState = new Label(shlTrackModel, SWT.NONE);
		lblCrossState.setText("Cross State");
		lblCrossState.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		lblCrossState.setBounds(212, 164, 144, 28);
		formToolkit.adapt(lblCrossState, true, true);
		
		Label lblCrossing = new Label(shlTrackModel, SWT.NONE);
		lblCrossing.setText("Crossing");
		lblCrossing.setBounds(197, 201, 55, 20);
		formToolkit.adapt(lblCrossing, true, true);
		
		Label label_8 = new Label(shlTrackModel, SWT.NONE);
		label_8.setText("State");
		label_8.setBounds(212, 239, 40, 20);
		formToolkit.adapt(label_8, true, true);
		
		Label lblSectionState = new Label(shlTrackModel, SWT.NONE);
		lblSectionState.setText("Section State");
		lblSectionState.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		lblSectionState.setBounds(212, 513, 144, 28);
		formToolkit.adapt(lblSectionState, true, true);
		
		TextViewer textViewer_3 = new TextViewer(shlTrackModel, SWT.BORDER);
		StyledText SectionState = textViewer_3.getTextWidget();
		SectionState.setBounds(257, 617, 71, 24);
		formToolkit.paintBordersFor(SectionState);
		
		Label label_6 = new Label(shlTrackModel, SWT.NONE);
		label_6.setText("State");
		label_6.setBounds(212, 619, 40, 20);
		formToolkit.adapt(label_6, true, true);
		
		Combo SectionLineState = new Combo(shlTrackModel, SWT.NONE);
		SectionLineState.setItems(new String[] {"Red", "Green"});
		SectionLineState.setBounds(268, 550, 70, 28);
		formToolkit.adapt(SectionLineState);
		formToolkit.paintBordersFor(SectionLineState);
		SectionLineState.setText("Green");
		SectionLineState.select(0);
		
		Combo SectionBlockState = new Combo(shlTrackModel, SWT.NONE);
		SectionBlockState.setItems(new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"});
		SectionBlockState.setBounds(268, 577, 70, 28);
		formToolkit.adapt(SectionBlockState);
		formToolkit.paintBordersFor(SectionBlockState);
		SectionBlockState.setText("A");
		SectionBlockState.select(0);
		
		SectionBlockState.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (SectionLineState.getItem(SectionLineState.getSelectionIndex()).equals("Green"))
				{
					SectionState.setText(GreenSectionStates.get(SectionBlockState.getItem(SectionBlockState.getSelectionIndex()).toString()));
				}
				else if (SectionLineState.getItem(SectionLineState.getSelectionIndex()).equals("Red")) 
				{
					SectionState.setText(RedSectionStates.get(SectionBlockState.getItem(SectionBlockState.getSelectionIndex()).toString()));
				}
			}
		});
		SectionLineState.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if (SectionLineState.getItem(SectionLineState.getSelectionIndex()).equals("Green"))
				{
					SectionState.setText(GreenSectionStates.get(SectionBlockState.getItem(SectionBlockState.getSelectionIndex()).toString()));
				}
				else if (SectionLineState.getItem(SectionLineState.getSelectionIndex()).equals("Red")) 
				{
					SectionState.setText(RedSectionStates.get(SectionBlockState.getItem(SectionBlockState.getSelectionIndex()).toString()));
				}
			}
		});
		
		Label label_3 = new Label(shlTrackModel, SWT.NONE);
		label_3.setText("Line");
		label_3.setBounds(222, 553, 40, 20);
		formToolkit.adapt(label_3, true, true);
		
		Label label_4 = new Label(shlTrackModel, SWT.NONE);
		label_4.setText("Block");
		label_4.setBounds(222, 580, 40, 20);
		formToolkit.adapt(label_4, true, true);
		
		TextViewer textViewer_2 = new TextViewer(shlTrackModel, SWT.BORDER);
		StyledText CrossStatePrint = textViewer_2.getTextWidget();
		CrossStatePrint.setBounds(257, 238, 71, 24);
		formToolkit.paintBordersFor(CrossStatePrint);
		
		Combo CrossToSee = new Combo(shlTrackModel, SWT.NONE);
		CrossToSee.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				CrossStatePrint.setText(CrossStates.get(CrossToSee.getItem(CrossToSee.getSelectionIndex()).toString()));
			}
		});
		CrossToSee.setItems(new String[] {"Green:E", "Red:I"});
		CrossToSee.setBounds(257, 198, 70, 28);
		formToolkit.adapt(CrossToSee);
		formToolkit.paintBordersFor(CrossToSee);
		CrossToSee.select(0);
		
		ThroughputText = new Label(shlTrackModel, SWT.NONE);
		ThroughputText.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		ThroughputText.setText("Number");
		ThroughputText.setBounds(126, 75, 82, 25);
		formToolkit.adapt(ThroughputText, true, true);
		
		Label lblThroughput = new Label(shlTrackModel, SWT.NONE);
		lblThroughput.setText("Throughput");
		lblThroughput.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		lblThroughput.setBounds(100, 41, 152, 28);
		formToolkit.adapt(lblThroughput, true, true);
		
		Label lblPassengers = new Label(shlTrackModel, SWT.NONE);
		lblPassengers.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblPassengers.setText("Passengers");
		lblPassengers.setBounds(212, 75, 90, 21);
		formToolkit.adapt(lblPassengers, true, true);
		
		Label lblLastSent = new Label(shlTrackModel, SWT.NONE);
		lblLastSent.setText("Last Sent Train:");
		lblLastSent.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		lblLastSent.setBounds(911, 342, 134, 21);
		formToolkit.adapt(lblLastSent, true, true);
		
		LineSentText = new Label(shlTrackModel, SWT.NONE);
		LineSentText.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		LineSentText.setBounds(933, 394, 55, 21);
		formToolkit.adapt(LineSentText, true, true);
		
		InfrSentText = new Label(shlTrackModel, SWT.NONE);
		InfrSentText.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		InfrSentText.setBounds(994, 396, 221, 21);
		formToolkit.adapt(InfrSentText, true, true);
		
		TrainSentText = new Label(shlTrackModel, SWT.NONE);
		TrainSentText.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		TrainSentText.setBounds(824, 394, 90, 21);
		formToolkit.adapt(TrainSentText, true, true);
		
		Label lblTrainNum = new Label(shlTrackModel, SWT.NONE);
		lblTrainNum.setBounds(824, 369, 70, 20);
		formToolkit.adapt(lblTrainNum, true, true);
		lblTrainNum.setText("Train Num:");
		
		Label lblLine_2 = new Label(shlTrackModel, SWT.NONE);
		lblLine_2.setBounds(935, 369, 40, 20);
		formToolkit.adapt(lblLine_2, true, true);
		lblLine_2.setText("Line:");
		
		Label lblNewLabel_1 = new Label(shlTrackModel, SWT.NONE);
		lblNewLabel_1.setBounds(994, 370, 96, 20);
		formToolkit.adapt(lblNewLabel_1, true, true);
		lblNewLabel_1.setText("Destination");
		
		Label lblNewLabel_2 = new Label(shlTrackModel, SWT.NONE);
		lblNewLabel_2.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		lblNewLabel_2.setBounds(602, 16, 82, 28);
		formToolkit.adapt(lblNewLabel_2, true, true);
		lblNewLabel_2.setText("Clock");
		
		
	}
	
	
	
	
	
	
	public void Dispatch(String trainNumber, String SuggSpeed, String SuggAuth, String DestLine, String DestBlock) {
		try {
		File inputFile = new File("test.xml");
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc1 = docBuilder.parse(inputFile);
        Node bits = doc1.getFirstChild();
        Node Dispatch = doc1.getElementsByTagName("Dispatch").item(0);
        Element nextDispatch = doc1.createElement(trainNumber);
        
        Attr attrType = doc1.createAttribute("DestLine");
        attrType.setValue(DestLine);
        nextDispatch.setAttributeNode(attrType);
        
        Attr attrType2 = doc1.createAttribute("DestBlock");
        attrType2.setValue(DestBlock);
        nextDispatch.setAttributeNode(attrType2);
        
        nextDispatch.appendChild(doc1.createTextNode("Speed:"+SuggSpeed+" Auth:"+SuggAuth));
        Dispatch.appendChild(nextDispatch);
        
        
        
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc1);
        StreamResult result = new StreamResult(new File("test.xml"));
        transformer.transform(source, result);
        
        
        
        LineSentText.setText(DestLine);
		InfrSentText.setText(DestBlock);
		TrainSentText.setText(trainNumber);
        
        
		}catch(Exception e) {}
	}
	
	public void DispatchAuto(String trainNumber, String SuggSpeed, String SuggAuth, String Dest, String Line) {
		try {
		File inputFile = new File("test.xml");
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc1 = docBuilder.parse(inputFile);
        Node bits = doc1.getFirstChild();
        Node Dispatch = doc1.getElementsByTagName("Dispatch").item(0);
        Element nextDispatch = doc1.createElement(trainNumber);
        
        Attr attrType = doc1.createAttribute("Dest");
        attrType.setValue(Line);
        nextDispatch.setAttributeNode(attrType);
        
        
        nextDispatch.appendChild(doc1.createTextNode("Speed:"+SuggSpeed+" Auth:"+SuggAuth));
        Dispatch.appendChild(nextDispatch);
        
        
        
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc1);
        StreamResult result = new StreamResult(new File("test.xml"));
        transformer.transform(source, result);
        
        
        
        LineSentText.setText(Line);
		InfrSentText.setText(Dest);
		TrainSentText.setText(trainNumber);
        
        
		}catch(Exception e) {}
	}
	
	public void RequestSend(String WhatToChange, String Label, String WhatToDo) {  //label is the exact location, WhatToChange is if it's a switch, etc, what to do is if to flip, put up etc
		try {
		File inputFile = new File("test.xml");
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc1 = docBuilder.parse(inputFile);
        Node bits = doc1.getFirstChild();
        Node Dispatch = doc1.getElementsByTagName("Requests").item(0);
        Element unit = doc1.createElement(WhatToChange);
        
        Attr attrType = doc1.createAttribute("Location");
        attrType.setValue(Label);
        unit.setAttributeNode(attrType);
        
        unit.appendChild(doc1.createTextNode(WhatToDo));
        Dispatch.appendChild(unit);
        
        
        
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc1);
        StreamResult result = new StreamResult(new File("test.xml"));
        transformer.transform(source, result);
        
		}catch(Exception e) {}
	}
	
	
	
	
	
	public void updateXMLInput() {
		

		
		
		
	      
        //Add all cross states
		 try {
		File inputFile = new File("test.xml");
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc2 = docBuilder.parse(inputFile);
        Node bits = doc2.getFirstChild();
        Node CrossingsNode = doc2.getElementsByTagName("Crossings").item(0);
    
        NodeList xingList = doc2.getElementsByTagName("Xing");
        for (int temp = 0; temp < xingList.getLength(); temp++) {
        	Node nowNode = xingList.item(temp);
			
        	Element nowElement = (Element) nowNode;
        	String Name = nowElement.getAttribute("Line");
        	String value= nowElement.getTextContent();
        	
        	if (value.equals("1"))
        	{	
        		CrossStates.put(Name,"Down");
        	}
        	else if (value.equals("0"))
   			{
        		CrossStates.put(Name, "Up");
    		}
        	//CrossStates.put("Green:E", "ITSINHERE!");
        }	

		}catch(Exception e) {}
	
		 
		 
		 
		//Add all signal states
		 try {
		File inputFile = new File("test.xml");
       DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
       DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
       Document doc2 = docBuilder.parse(inputFile);
 
       NodeList signalList = doc2.getElementsByTagName("Signal");
       for (int temp = 0; temp < signalList.getLength(); temp++) {
       	Node nowNode = signalList.item(temp);
			
       	Element nowElement = (Element) nowNode;
       	String Name = nowElement.getAttribute("Name");
       	String value= nowElement.getTextContent();
       
       	
       	if (value.equals("1"))
       	{	
       		SignalStates.put(Name,"Green");
       	}
       	else if (value.equals("0"))
  			{
       		SignalStates.put(Name, "Red");
   		}
       	else if (value.equals("2")) 
       	{
       		SignalStates.put(Name, "SupGreen");
       	}
       }	

		}catch(Exception e) {}
	
		 
		
		
		
		
		//Add all switch states
		 try {
		File inputFile = new File("test.xml");
      DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
      Document doc2 = docBuilder.parse(inputFile);

      NodeList signalList = doc2.getElementsByTagName("Switch");
      for (int temp = 0; temp < signalList.getLength(); temp++) {
      	Node nowNode = signalList.item(temp);
			
      	Element nowElement = (Element) nowNode;
      	String Name = nowElement.getAttribute("Name");
      	String value= nowElement.getTextContent();
      
      	
      	if (value.equals("1"))
      	{	
      		SwitchStates.put(Name,"Rev");
      	}
      	else if (value.equals("0"))
 			{
      		SwitchStates.put(Name, "Normal");
  		}
      	}	

		}catch(Exception e) {}
		
		
		
		//Add all green section states
		 try {
		File inputFile = new File("test.xml");
      DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
      Document doc2 = docBuilder.parse(inputFile);

      NodeList signalList = doc2.getElementsByTagName("Green");
      for (int temp = 0; temp < signalList.getLength(); temp++) {
      	Node nowNode = signalList.item(temp);
			
      	Element nowElement = (Element) nowNode;
      	String Name = nowElement.getAttribute("Name");
      	String value= nowElement.getTextContent();
      
      	
      	if (value.equals("1"))
      	{	
      		GreenSectionStates.put(Name,"Closed");
      	}
      	else if (value.equals("0"))
 			{
      		GreenSectionStates.put(Name, "Open");
  		}
      }	

		}catch(Exception e) {}
		 
		 
		
		//Add all red section states
		 try {
		File inputFile = new File("test.xml");
     DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
     DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
     Document doc2 = docBuilder.parse(inputFile);

     NodeList signalList = doc2.getElementsByTagName("Red");
     for (int temp = 0; temp < signalList.getLength(); temp++) {
     	Node nowNode = signalList.item(temp);
			
     	Element nowElement = (Element) nowNode;
     	String Name = nowElement.getAttribute("Name");
     	String value= nowElement.getTextContent();
     
     	
     	if (value.equals("1"))
     	{	
     		RedSectionStates.put(Name,"Closed");
     	}
     	else if (value.equals("0"))
			{
     		RedSectionStates.put(Name, "Open");
 		}
     }	

		}catch(Exception e) {}
	

		 try {
				File inputFile = new File("test.xml");
		     DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		     DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		     Document doc2 = docBuilder.parse(inputFile);

		     NodeList throughList= doc2.getElementsByTagName("Throughput");
		     for (int temp = 0; temp < throughList.getLength(); temp++) {
		     	Node nowNode = throughList.item(temp);
					
		     	Element nowElement = (Element) nowNode;
		     	String value= nowElement.getTextContent();
		     
		     	throughputFromXML=value;
		     	
		     }	

				}catch(Exception e) {}
		 
		 
		 
		 
		
		
		
	}
}
