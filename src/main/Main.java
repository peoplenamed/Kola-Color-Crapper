package main;

//---------Kola Patern Generator---------
//
//----------Peter Black------------------
//-----------Max Claus-------------------
//------------Kola Industries LLC--------
//
//
// This is an Open-Source work derived from other Open-Source works. 
// This software is licensed using the GPL v3.0
// 		Thank You:
//			OpenCSV http://opencsv.sourceforge.net/
//			SourceForge.net
//			
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;
 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import com.sun.xml.internal.ws.util.StringUtils;

public class Main {
	public static JPanel ToolPanel = new JPanel();// This Panel holds the Top 4 panels
	public static JPanel GridPanel = new JPanel();// Define the Panel that Holds
	public static ArrayList <String> csvData = new ArrayList <String>();
	public static  ArrayList<String> dataLine ;
public static String[] PatternReader;
	public static String[] header;					 
	public static String[] row,row1,row2,row3,row4,row5,row6,row7,row8,row9,row10;
	public static JPanel ToolPanelTopLeft = new JPanel();    // Top Left Panel
	public static JPanel ToolPanelTopRight = new JPanel();   // Top Right Panel
	public static JPanel ToolPanelBottomLeft = new JPanel(); // Bottom Left Panel
	public static JPanel ToolPanelBottomRight = new JPanel();// Bottom Right Panel
	public static String [][] board = new String [100][256];
	public static JPanel FullPanel = new JPanel();    //This is the Panel the other panels will populate
	public static JButton button[] = new JButton[256];// Define button array for
													  // 256 buttons
	private static JColorChooser colorChooser;// Color Chooser
											  // http://docs.oracle.com/javase/6/docs/api/javax/swing/JColorChooser.html
	
	public static JButton createCodeButton = new JButton("Create Code"); //This Button populates CSV HEX values in JTextArea ColorChooserOutputText
	public static JTextArea ColorChooserOutputText = new JTextArea();
	static JScrollPane scroll = new JScrollPane(ColorChooserOutputText); // Allows Scrolling for JTextArea ColorChooserOutputText
	
	public static JButton OpenDesignButton = new JButton("Open");   // Open file dialog
	public static JButton SaveDesignButton = new JButton("Save");   // Save file dialog
	public static JButton ExportCodeButton = new JButton("Export"); // Export file dialog
	public static JButton ExitButton = new JButton("Exit");      // Exit Button
	public static JTextArea FileSaveTextArea = new JTextArea("Gibberish"); // Work in progress

	public static JFrame MaineWindow = new JFrame("Kola Pattern Generator"); // Main Frame
	final static String ToolPanelString = "Tool Panel"; 
	final static String GridPanelString = "Grid Panel";
	public static JTextArea tracker; // used for debuging purposes. Will be removed in the end. Possibly replaced with another dialog
	public static JPanel optPanel;
	public static JFileChooser fileDialog;// File Chooser Dialog
	public static JComboBox<String> ComboBox = new JComboBox();
	

	
	
	public static ActionListener ButtonArrayActionListener = new ActionListener() {
		public void actionPerformed(ActionEvent ae1) {
			Color newColor = colorChooser.getColor();
			if (ae1.getSource() instanceof JButton) {
				((JButton) ae1.getSource()).setBackground(newColor);
			}
		}
	};

	public static ActionListener TestButtonActionListener = new ActionListener() {
		public void actionPerformed(ActionEvent ae2) {
			String st = "Welcome";
			JOptionPane.showMessageDialog(null, st);
		}
	};

	public static ActionListener ExitActionListener = new ActionListener() {
		public void actionPerformed(ActionEvent ae3) {
			System.exit(0);
		}
	};

	public static ActionListener CreateCodeButtonActionListener = new ActionListener() {
		public void actionPerformed(ActionEvent ae4) {
			CreateCodePlease();

		
		}
	};

	public static ActionListener OpenDesignButtonActionListener = new ActionListener() {
		public void actionPerformed(ActionEvent ae5) {
			String st = "Welcome";
			JOptionPane.showMessageDialog(null, st);
		}
	};

	public static ActionListener SaveDesignButtonActionListener = new ActionListener() {
		public void actionPerformed(ActionEvent ae6) {

		}
	};

	public static ActionListener SendButtonActionListener = new ActionListener() {
		public void actionPerformed(ActionEvent ae7) {

		}

	};

	public static ActionListener ExportCodeButtonActionListener = new ActionListener() {
		public void actionPerformed(ActionEvent ae8) {
			String st = "Welcome";
			JOptionPane.showMessageDialog(null, st);

		}
	};
	
	
	public static ActionListener ComboBoxActionListener = new ActionListener() {
		public void actionPerformed(ActionEvent ae9) {
			if (ComboBox.getItemCount()==0){
				System.out.println("ERROR!!: There is nothing in the comboBox.");
			}else{//
				
			
			
				if (csvData == null){//---
					System.out.println("ERROR!!: csvData = null...." );//---
				
					
				}else{//---
					ColorChooserOutputText.setText(csvData.get(0));
					
					ColorChooserOutputText.setText("");//---
					int ComboBoxSelectedIndex = ComboBox.getSelectedIndex();
					ColorChooserOutputText.setText(csvData.get(ComboBoxSelectedIndex));
					String[] line = csvData.get(ComboBoxSelectedIndex).split(",");
						for (int RowNumber = 0; RowNumber<256; RowNumber++){//---
			        		button[RowNumber].setBackground(Color.decode("0x"+line[RowNumber]));

				       //---		        	
					}
				
			        
					}
			}
		}
	};

	public static void main(String[] args) {// Main (Run)
		ColorChooserOutputText.setLineWrap(true);
		// setup the gui with buttons
		CreateFullPanel();
		CreateMaineWindow();
	}

	public static void CreateFullPanel() {

		CreateGridPanel();
		CreateCodeMakerToolPanel();

	}

	public static void ToolPanelTopLeft() {
		ToolPanelTopLeft.setBorder(BorderFactory
				.createLineBorder(Color.blue, 2));
		scroll.setBorder(BorderFactory.createLineBorder(Color.blue, 2));
		scroll.setEnabled(true);
		scroll.setPreferredSize(new Dimension(600, 250));
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		ToolPanelTopLeft.add(scroll);
		ToolPanel.add(ToolPanelTopLeft);
		
	}

	public static void ToolPanelTopRight() {
		ToolPanelTopRight.setBorder(BorderFactory.createLineBorder(Color.blue,
				2));
		JLabel KolaLabel1 = new JLabel(
				"<html><center><H1>Kola Pattern Generator </H1><br><H2'>BETA  <br>Created by:  <br>Peter Black & Max Claus  <br>Licensed using GPL v 3.0  <br> Kola Industries LLC</html>");
		ToolPanelTopRight.add(KolaLabel1);
		ToolPanel.add(ToolPanelTopRight);

	}

	public static void ToolPanelBottomLeft() {
		ToolPanelBottomLeft.setBorder(BorderFactory.createLineBorder(
				Color.blue, 2));
		ToolPanelBottomLeft.add(colorChooser = new JColorChooser());
		colorChooser.setPreviewPanel(new JPanel());// Disables Preview panel
		colorChooser.getSelectionModel();
		ToolPanelBottomLeft.setBorder(BorderFactory.createLineBorder(
				Color.blue, 2));

		ToolPanelBottomLeft.add(createCodeButton);
		createCodeButton.addActionListener(CreateCodeButtonActionListener);
		ToolPanel.add(ToolPanelBottomLeft);
	}

	public static void ToolPanelBottomRight() {
		// This will be the Open/Save Panel
		ToolPanelBottomRight.setBorder(BorderFactory.createLineBorder(
				Color.blue, 2));
		ToolPanelBottomRight.setLayout(new BorderLayout());

		// create an instance of JFileChooser class
		fileDialog = new JFileChooser();// "C:\\Documents and Settings\\Owner\\My Documents"

		// Using a JTextArea to diplay feedback
		tracker = new JTextArea("File Tracker:");
		tracker.setVisible(true);
		ToolPanelBottomRight.add(tracker, BorderLayout.NORTH);

		optPanel = new JPanel();
		optPanel.setLayout(new GridLayout(1, 2));

		ToolPanelBottomRight.add(optPanel, BorderLayout.SOUTH);
		ToolPanelBottomRight.add(ComboBox, BorderLayout.NORTH);
		ComboBox.addActionListener(ComboBoxActionListener);
		

		JButton openButton = new JButton("Open Dialog");
		openButton.setActionCommand("Open Dialog");
		openButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				openDialog();
			}
		});

		optPanel.add(openButton);

		JButton saveButton = new JButton("Save Dialog");
		saveButton.setActionCommand("Save Dialog");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				saveDialog();
			}
		});

		optPanel.add(saveButton);
		ToolPanel.add(ToolPanelBottomRight);
	}

	public static void CreateCodeMakerToolPanel() {
		ToolPanelTopLeft();
		ToolPanelTopRight();
		ToolPanelBottomLeft();

		ToolPanelBottomRight();

		ToolPanel.setLayout(new GridLayout(2, 2));
		ToolPanel.setBorder(BorderFactory.createLineBorder(Color.blue, 2));

	}

	public static void CreateGridPanel() {

		GridPanel.setBorder(BorderFactory.createLineBorder(Color.blue, 2));
		GridPanel.setLayout(new GridLayout(16, 16, 0, 0));

		CreateButtonArray();// Create array of buttons

	}

	public static void CreateButtonArray() {

		for (int i = 0; i < button.length; i++) {
			button[i] = new JButton(Integer.toString(i + 1));
			button[i].addActionListener(ButtonArrayActionListener);
			button[i].setBackground(Color.black);
			GridPanel.add(button[i]);
			button[i].setToolTipText("Set the Color");
		}
	}

	public static void CreateMaineWindow() {
		MaineWindow.setLayout(new GridLayout(2, 1));// Main Window is resizable
		MaineWindow.add(ToolPanel, ToolPanelString);
		MaineWindow.add(GridPanel, ToolPanelString);
		MaineWindow.setBounds(0, 0, 100, 400);
		MaineWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MaineWindow.pack();// pack all the contents of Main Window
		MaineWindow.setVisible(true);// Show Main Window
	}

	
	
	
	
	
	
	
	//////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
	
	/*
	 public static String[][] openDialog()
	  {
	    FileInputStream inStream;
	    InputStreamReader inFile;
	    BufferedReader br;
	    String line;
	    int lineNum, tokNum, ii, jj;
	    String [][] CSV, TempArray, TempArray2;

	    lineNum = tokNum = ii = jj  = 0;
	    TempArray = new String[50][50];

	    ////////////////////////////
		ColorChooserOutputText.setText("");	
	int openChoice = fileDialog.showOpenDialog(ToolPanelBottomRight);
		String[] strLine;
	//	String ColorstrLine;
		
		
		// display choice using tracker
		logChoice(openChoice, "Open Dialog");

		if (openChoice == JFileChooser.APPROVE_OPTION) {
			// Put open file code in here
			File openFile = fileDialog.getSelectedFile();
			tracker.setText("");
			tracker.append("\nThe file selected is " + openFile.getName());
			tracker.append("\nThe file's path is " + openFile.getPath());
   ////////////////////////////////////////////////
	    
	    try
	    {
	    	
			BufferedReader CSVFile = new BufferedReader(new FileReader(openFile));//load file in to reader 
			String dataRow = CSVFile.readLine(); // Read the first line of data.
	    	
	    	
	    	URL url = new URL(openFile.getAbsolutePath());
	    	
	        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
	        System.out.println("Please enter the file path of the CSV");
	        String fileName = in.readLine();
	        inStream = new FileInputStream(fileName);
	        inFile = new InputStreamReader(inStream);
	        br = new BufferedReader(inFile);
	        StringTokenizer tok,tok2;


	        lineNum = 0;
	            line = br.readLine();
	            tokNum = 0;
	            tok = new StringTokenizer(line, ",");

	            while( tok.hasMoreTokens())
	            {
	                TempArray[tokNum][0] = tok.nextToken();
	                tokNum++;
	            }
	            tokNum = 0;
	            lineNum++;

	            while( line != null)
	            {
	                    line = br.readLine();
	                    if (line != null)
	                    {
	                        tokNum = 0;
	                        tok2 = new StringTokenizer(line, ",");

	                        while(tok2.hasMoreTokens())
	                        {
	                            TempArray[tokNum][lineNum] = tok2.nextToken();
	                            tokNum++;
	                        }
	                    }
	                    lineNum++;
	            }
	    }    
	    catch(IOException e)
	    {
	        System.out.println("Error file  may not be accessible, check the path and try again");
	    }

	    CSV = new String[tokNum][lineNum-1];
	    for (ii=0; ii<tokNum ;ii++)
	    {
	        System.arraycopy(TempArray[ii],0,CSV[ii],0,lineNum-1);
	    }
	    return CSV;
	}    
	///////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////
	
	
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// Show a open file dialog box
	public static void openDialog() {
		
		
		
		
		ColorChooserOutputText.setText("");	// ---
		int openChoice = fileDialog.showOpenDialog(ToolPanelBottomRight);// ---
		logChoice(openChoice, "Open Dialog");// ---
		if (openChoice == JFileChooser.APPROVE_OPTION) {// ---
			// Put open file code in here
			File openFile = fileDialog.getSelectedFile();// ---
			tracker.setText("");// ---
			tracker.append("\nThe file selected is " + openFile.getName());// ---
			tracker.append("\nThe file's path is " + openFile.getPath());// ---
		//	readCSVFile(openFile);//---
		//}//}
		
	//	public static void readCSVFile(File openFile){//---
			ComboBox.removeAllItems();;
			try {//---
				 
				 
			 FileReader fileReader = new FileReader(openFile);//---
			       BufferedReader bufferedReader = new BufferedReader(fileReader);//---
			     
			     CSVReader csvReader = new  CSVReader(new FileReader (openFile));
					String line = null;
						while ((line = bufferedReader.readLine()) != null) {
						
							 String[] splitted = line.split("H");
				 
							 dataLine = new ArrayList<String>(splitted.length);

					            for (String data : splitted)
					                dataLine.add(data);
					            csvData.addAll(dataLine);
					            // System.out.println(dataLine);
					            //System.out.println(csvData);
				            }
/*Print ALL Lines
			            String [] number = new String [csvData.size()];

					        for(int z = 0; z < csvData.size(); z++)
					        {
					            number[z] = csvData.get(z);
					          System.out.println(number[z]);
					            System.out.println("1***");
					        }
Print ALL Lines*/
			            
				        for(int z = 0; z < csvData.size(); z++)
				        {
				        	 ComboBox.addItem("Pattern# "+z);
				        }
					        ColorChooserOutputText.setText(csvData.get(0));
					      					        
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
		
			
	}
		
		
	
	// Show a save file dialog box and save colors
	private static void saveDialog() {
		CreateCodePlease();


		int saveChoice = fileDialog.showSaveDialog(ToolPanelBottomRight);
		String ReturnSaveVal = ColorChooserOutputText.getText();
		// display choice using tracker
		logChoice(saveChoice , "Open Dialog");

		if (saveChoice == JFileChooser.APPROVE_OPTION) {
			// Put save file code in here
			File saveFile = fileDialog.getSelectedFile();
			tracker.setText("");
			tracker.append("\nThe file selected is " + saveFile.getName());
			tracker.append("\nThe file's path is " + saveFile.getPath());
			try {

				BufferedWriter writer = null;
				writer = new BufferedWriter(new FileWriter(
						saveFile.getAbsolutePath(), true));
				//saveFile.getAbsolutePath()+ ".kola", true));
				writer.write(ReturnSaveVal+ "H");
				writer.close();

				infoBoxCorrect();

			} catch (IOException e) {
				infoBoxWrong();

			}
		}
	}

	// append the button choice to the tracker JTextArea

	private static void logChoice(int choice, String dialog) {
		tracker.append("");
		switch (choice) {
		// The user pressed cancel button
		case JFileChooser.CANCEL_OPTION:
			tracker.append("\nCancel Option received from " + dialog);
			break;

		// The user pressed the open/save button
		case JFileChooser.APPROVE_OPTION:
			tracker.append("\nApprove Option received from " + dialog);
			break;

		// The user dismissed the dialog without pressing a button
		case JFileChooser.ERROR_OPTION:
			tracker.append("\nError Option received from " + dialog);
			break;
		}
	}

	public static void infoBoxCorrect() {
		JOptionPane.showMessageDialog(ToolPanelBottomRight,
				"The Message was Saved Successfully!", "Success!",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public static void infoBoxWrong() {
		JOptionPane.showMessageDialog(ToolPanelBottomRight,
				"The Text could not be Saved!", "Error!",
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void CreateCodePlease(){
		
		ColorChooserOutputText.setText("");
		String MyRValue = null;
		String MyGValue = null;
		String MyBValue = null;

		for (int i = 0; i < button.length; i++) {
			Color[] list = new Color[256];

			list[i] = button[i].getBackground();
		//	ColorChooserOutputText.append("#");
			// ColorChooserOutputText.append(Integer.toHexString(list[i].getRed()));
			// ColorChooserOutputText.append(Integer.toHexString(list[i].getGreen()));
			// ColorChooserOutputText.append(Integer.toHexString(list[i].getBlue()));

			MyRValue = Integer.toHexString(list[i].getRed());
			MyGValue = Integer.toHexString(list[i].getGreen());
			MyBValue = Integer.toHexString(list[i].getBlue());

			MyRValue = ("00" + MyRValue).substring(MyRValue.length());
			MyGValue = ("00" + MyGValue).substring(MyGValue.length());
			MyBValue = ("00" + MyBValue).substring(MyBValue.length());
			ColorChooserOutputText.append(MyRValue + MyGValue + MyBValue);

			ColorChooserOutputText.append(",");	
	}
	}
	
	

}