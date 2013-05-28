package main;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import java.io.*;

public class Main {
	public static JPanel ToolPanel = new JPanel();	
	public static JPanel GridPanel = new JPanel();// Define the Panel that Holds 256 Button Array
	
	public static JPanel ToolPanelTopLeft = new JPanel();
	public static JPanel ToolPanelTopRight = new JPanel();
	public static JPanel ToolPanelBottomLeft = new JPanel();
	public static JPanel ToolPanelBottomRight = new JPanel();
	
	public static JPanel FullPanel  = new JPanel();	
	public static JButton button[] = new JButton[256];// Define button array for 256 buttons
	private static JColorChooser colorChooser;  
	public static JButton createCodeButton = new JButton("Create Code");
	public static JButton OpenDesignButton = new JButton("Open");
	public static JButton SaveDesignButton = new JButton("Save");
	public static JButton ExportCodeButton = new JButton("Export");
	public static JFileChooser FileChooser = new JFileChooser();
	public static JButton ExitButton = new JButton("Exit");
	public static JTextArea FileSaveTextArea= new JTextArea("Gibberish");
	public static JTextArea ColorChooserOutputText = new JTextArea();
	static JScrollPane scroll = new JScrollPane(ColorChooserOutputText);
	public static JFrame MaineWindow = new JFrame("Kola Pattern Generator");
	final static String ToolPanelString = "Tool Panel";
	final static String GridPanelString = "Grid Panel";
	public static JTextArea tracker; 
    public static JPanel optPanel;
    public static JFileChooser fileDialog;
	
	public static  ActionListener ButtonArrayActionListener = new ActionListener() {
        public void actionPerformed(ActionEvent ae1) {
        	Color newColor = colorChooser.getColor();
          if (ae1.getSource() instanceof JButton) {
            	((JButton) ae1.getSource()).setBackground(newColor);}}};
          
                public static  ActionListener TestButtonActionListener = new ActionListener() {
                    public void actionPerformed(ActionEvent ae2) {
                      	  String st="Welcome";
                      	  JOptionPane.showMessageDialog(null,st);
                  	      }};
                  	 
                  	    public static  ActionListener ExitActionListener = new ActionListener() {
                            public void actionPerformed(ActionEvent ae3) {
                            	System.exit(0);
                          	      }};   
              	          
                           	      public static  ActionListener CreateCodeButtonActionListener = new ActionListener() {
                            	        public void actionPerformed(ActionEvent ae4) {
                            	        ColorChooserOutputText.setText  ("");
                  	      String MyRValue = null;
                  	      String MyGValue = null;
                  	      String MyBValue = null;
                  	 
                            	        
                            	        
                            	        for (int i = 0; i < button.length; i++) {
                  	        Color[] list = new Color[256];
                  	      
                  	        	  list[i] = button[i].getBackground();
                   	        	ColorChooserOutputText.append("#");
                   	        	//ColorChooserOutputText.append(Integer.toHexString(list[i].getRed()));
                   	        	//ColorChooserOutputText.append(Integer.toHexString(list[i].getGreen()));
                   	        	//ColorChooserOutputText.append(Integer.toHexString(list[i].getBlue()));
                   	     
                   	        	MyRValue = Integer.toHexString(list[i].getRed());
                   	        	MyGValue = Integer.toHexString(list[i].getGreen());
                   	        	MyBValue = Integer.toHexString(list[i].getBlue());
                   	        	
                   	        	
                   	        	MyRValue = ("00"+MyRValue).substring(MyRValue.length());
                   	        	MyGValue = ("00"+MyGValue).substring(MyGValue.length());
                   	        	MyBValue = ("00"+MyBValue).substring(MyBValue.length());
                   	        	ColorChooserOutputText.append(MyRValue+MyGValue+MyBValue);
                   	        	
                  	        	ColorChooserOutputText.append(", ");
                  	                  	        	
                  	          }
                  	                        	      	      }};

                  	                        	      	     public static  ActionListener OpenDesignButtonActionListener = new ActionListener() {
                  	                                           public void actionPerformed(ActionEvent ae5) {
                  	                                             	  String st="Welcome";
                  	                                             	  JOptionPane.showMessageDialog(null,st);
                  	                                         	      }};
                  	                                         	      
                  	                                         	      
                  	                                         	      
                  	                                         	      
                  	                                         	     public static  ActionListener SaveDesignButtonActionListener = new ActionListener() {
                  	                                                   public void actionPerformed(ActionEvent ae6) {
                  	                                                          	   
                  	                                            
                  	                                                 	      }};
                  	                                                 	      
                  	                                                 	      
                  	                                                 	      
                  	                                                 	      
                  	                                                 	      
                  	                                                	     public static  ActionListener SendButtonActionListener = new ActionListener() {
                            	                                                   public void actionPerformed(ActionEvent ae7) {
                            	                                                	   
                            	                                                	
                            	                                                   }
                            	                                                	   
                            	                                                 	      };
                  	                                                 	      
                  	                                                 	      
                  	                                                 	      
                  	                                                 	      
                  	                                                 	      
                  	                                                 	      
                  	                                                 	      
                  	                                                 	     public static  ActionListener ExportCodeButtonActionListener = new ActionListener() {
                  	                                                           public void actionPerformed(ActionEvent ae8) {
                  	                                                             	  String st="Welcome";
                  	                                                             	  JOptionPane.showMessageDialog(null,st);
                  	                                                	                 	                                                	   
                  	                                                	
                  	                                                	   
                  	                                                   }   
                  	                                                    };
                  	                        	      	      
                  	                        	      	      
                  	                        	      	      
                  	                        	      	      
                  	                        	      	      
		public static void main( String[] args ){//Main (Run)
			ColorChooserOutputText.setLineWrap(true); 
			// setup the gui with buttons
			CreateFullPanel();
			CreateMaineWindow();
}
		
		public static void CreateFullPanel(){
			
	CreateGridPanel();
	CreateCodeMakerToolPanel();
		
	}
		
	
		public static void ToolPanelTopLeft(){
			 ToolPanelTopLeft.setBorder(BorderFactory.createLineBorder(Color.blue, 2));
			 scroll.setBorder(BorderFactory.createLineBorder(Color.blue, 2));
	        scroll.setEnabled(true);
	    	scroll.setPreferredSize(new Dimension(600, 250));
			 scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			 ToolPanelTopLeft.add(scroll);
			 ToolPanel.add(ToolPanelTopLeft);
		}
		
		public static void ToolPanelTopRight(){
			 ToolPanelTopRight.setBorder(BorderFactory.createLineBorder(Color.blue, 2));
			 JLabel KolaLabel1 = new JLabel("<html><center><H1>Kola Pattern Generator </H1><br><H2'>BETA  <br>Created by:  <br>Peter Black & Max Claus  <br>Licensed using GPL v 3.0  <br> Kola Industries LLC</html>");
		 ToolPanelTopRight.add(KolaLabel1);
               ToolPanel.add(ToolPanelTopRight);
			 
		}
		
		public static void ToolPanelBottomLeft(){
			 ToolPanelBottomLeft.setBorder(BorderFactory.createLineBorder(Color.blue, 2));
			 ToolPanelBottomLeft.add(colorChooser = new JColorChooser());
			colorChooser.setPreviewPanel(new JPanel());//Disables Preview panel
		     colorChooser.getSelectionModel();
		     ToolPanelBottomLeft.setBorder(BorderFactory.createLineBorder(Color.blue, 2));

			 ToolPanelBottomLeft.add(createCodeButton);
				createCodeButton.addActionListener(CreateCodeButtonActionListener);
		     ToolPanel.add(ToolPanelBottomLeft);
		}
		public static void ToolPanelBottomRight(){
			//This will be the Open/Save Panel
			ToolPanelBottomRight.setBorder(BorderFactory.createLineBorder(Color.blue, 2));
			 ToolPanelBottomRight.setLayout(new BorderLayout());
		        
		        //create an instance of JFileChooser class
		        fileDialog = new JFileChooser();//"C:\\Documents and Settings\\Owner\\My Documents"
		        
		        //Using a JTextArea to diplay feedback
		        tracker = new JTextArea("File Tracker:");
		        tracker.setVisible(true);
		        ToolPanelBottomRight.add(tracker, BorderLayout.NORTH);
		        
		        optPanel = new JPanel();
		        optPanel.setLayout(new GridLayout(1,2));
		        
		        
		        ToolPanelBottomRight.add(optPanel,BorderLayout.SOUTH);

		        JButton openButton = new JButton("Open Dialog");
		        openButton.setActionCommand("Open Dialog");
		        openButton.addActionListener(new ActionListener()
		        {
		            public void actionPerformed(ActionEvent event)
		            {
		                openDialog();
		            }
		        });
		        
		        optPanel.add(openButton);
		        
		        JButton saveButton = new JButton("Save Dialog");
		        saveButton.setActionCommand("Save Dialog");
		        saveButton.addActionListener(new ActionListener()
		        {
		            public void actionPerformed(ActionEvent event)
		            {
		                saveDialog();
		            }
		        });
		        
		        optPanel.add(saveButton);
			 ToolPanel.add(ToolPanelBottomRight);
		}
	
		
		public static void CreateCodeMakerToolPanel(){
			 ToolPanelTopLeft();
				ToolPanelTopRight();
			ToolPanelBottomLeft();
			
			ToolPanelBottomRight();	
			
			ToolPanel.setLayout(new GridLayout(2,2));
				 ToolPanel.setBorder(BorderFactory.createLineBorder(Color.blue, 2));
		
				
			
			}
		

	public static void CreateGridPanel() {

		GridPanel.setBorder(BorderFactory.createLineBorder(Color.blue, 2));
	    GridPanel.setLayout(new GridLayout(16, 16, 0, 0));
	
		CreateButtonArray();// Create array of buttons
		
	}

public static void CreateButtonArray(){

	  for (int i = 0; i < button.length; i++) {
        button[i] = new JButton(Integer.toString(i + 1));
		button[i].addActionListener(ButtonArrayActionListener);
		button[i].setBackground(Color.black);
	        GridPanel.add(button[i]);
        button[i].setToolTipText("Set the Color");
    }
}
	
	public static void CreateMaineWindow(){
		MaineWindow.setLayout(new GridLayout(2,1));//Main Window is resizable
		MaineWindow.add(ToolPanel, ToolPanelString);
		MaineWindow.add(GridPanel, ToolPanelString);
		MaineWindow.setBounds(0,0,100,400);
		MaineWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    MaineWindow.pack();// pack all the contents of Main Window
		MaineWindow.setVisible(true);//Show Main Window
		}
	
    //Show a open file dialog box
    public static void openDialog()
    {
        int openChoice = fileDialog.showOpenDialog(ToolPanelBottomRight);
        //display choice using tracker 
        logChoice(openChoice, "Open Dialog");
        
        if (openChoice == JFileChooser.APPROVE_OPTION)
        {
            //Put open file code in here
        	File openFile = fileDialog.getSelectedFile();
            tracker.append("\nThe file selected is " + openFile.getName());
            tracker.append("\nThe file's path is " + openFile.getPath());
 
            try
            {

                String strLine;
                 File f = fileDialog.getSelectedFile();
                 BufferedReader br = new BufferedReader(new FileReader(f));
                 ColorChooserOutputText.setText("");
                while((strLine = br.readLine()) != null)
                {
                	ColorChooserOutputText.append(strLine + "\n");

                    System.out.println(strLine);

                }
            }
            catch(Exception e)
            {
                System.err.println("Error: " + e.getMessage());
            } 
        }
    }
	
	
	//Show a save file dialog box
    private static void saveDialog()
    {
        int saveChoice = fileDialog.showSaveDialog(ToolPanelBottomRight);
        String ReturnSaveVal = ColorChooserOutputText.getText();
        //display choice using tracker 
        logChoice(saveChoice, "Open Dialog");
        
        if (saveChoice == JFileChooser.APPROVE_OPTION)
        {
            //Put save file code in here
            File saveFile = fileDialog.getSelectedFile();
            tracker.append("\nThe file selected is " + saveFile.getName());
            tracker.append("\nThe file's path is " + saveFile.getPath());
            try  
            {  
            	
            	 BufferedWriter writer = null; 
            writer = new BufferedWriter( new FileWriter( saveFile.getAbsolutePath()));  
            writer.write(ReturnSaveVal);  
            writer.close( );  
         
            infoBoxCorrect();
            
            }  
            catch (IOException e)  
            {  
                infoBoxWrong();

            }  
        }
    }
    
    //append the button choice to the tracker JTextArea
	
    private static void logChoice(int choice, String dialog)
    {
        tracker.append("");
    	switch (choice)
        {
            //The user pressed cancel button
            case JFileChooser.CANCEL_OPTION:
                tracker.append("\nCancel Option received from " + dialog);
                break;
                
            //The user pressed the open/save button
            case JFileChooser.APPROVE_OPTION:
                tracker.append("\nApprove Option received from " + dialog);
                break;
                
            //The user dismissed the dialog without pressing a button
            case JFileChooser.ERROR_OPTION:
                tracker.append("\nError Option received from " + dialog);
                break;
        }
    }
    
    
 
    
    
    public static void infoBoxCorrect()
    {
    	   JOptionPane.showMessageDialog(ToolPanelBottomRight, "The Message was Saved Successfully!",  
                   "Success!", JOptionPane.INFORMATION_MESSAGE);          }
    
    public static void infoBoxWrong()
    {
        JOptionPane.showMessageDialog(ToolPanelBottomRight, "The Text could not be Saved!",  
                "Error!", JOptionPane.INFORMATION_MESSAGE);          }
   
	
}