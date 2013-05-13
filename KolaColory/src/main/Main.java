package main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Main {
	public static JPanel ToolPanel = new JPanel();	
	public static JPanel GridPanel = new JPanel();// Define the Panel that Holds 256 Button Array
	public static JPanel FullPanel  = new JPanel();	
	public static JButton button[] = new JButton[256];// Define button array for 256 buttons
	private static JColorChooser colorChooser;  
	public static JButton createCodeButton = new JButton("Create Code");

	public static JButton ExitButton = new JButton("Exit");

	public static JTextArea ColorChooserOutputText = new JTextArea("CoDe");
	static JScrollPane scroll = new JScrollPane(ColorChooserOutputText);
	public static JFrame MaineWindow = new JFrame("Kola Color Crapper");
	final static String ToolPanelString = "Tool Panel";
	final static String GridPanelString = "Grid Panel";

	
	public static  ActionListener ButtonArrayActionListener = new ActionListener() {
        public void actionPerformed(ActionEvent aef) {
        	Color newColor = colorChooser.getColor();
          if (aef.getSource() instanceof JButton) {
            	((JButton) aef.getSource()).setBackground(newColor);}}};
          
                public static  ActionListener TestButtonActionListener = new ActionListener() {
                    public void actionPerformed(ActionEvent aeef) {
                      	  String st="Welcome";
                      	  JOptionPane.showMessageDialog(null,st);
                  	      }};
                  	 
                  	    public static  ActionListener ExitActionListener = new ActionListener() {
                            public void actionPerformed(ActionEvent kjh) {
                            	System.exit(0);
                          	      }};   
              	          
                  	      public static  ActionListener CreateCodeButtonActionListener = new ActionListener() {
                  	        public void actionPerformed(ActionEvent aGf) {
                  	        ColorChooserOutputText.setText  ("");
                  	                          	      	
                  	      	for (int i = 0; i < button.length; i++) {
                  	        Color[] list = new Color[256];
                  	      
                  	        	  list[i] = button[i].getBackground();
                   	        	ColorChooserOutputText.append("#");
                  	        	ColorChooserOutputText.append(Integer.toHexString(list[i].getRGB()));
                  	        	ColorChooserOutputText.append(", ");
                  	        	// \n = newline
                  	        	
                  	          }
                  	                        	      	      }};
      	                          	
		public static void main( String[] args ){//Main (Run)
			ColorChooserOutputText.setLineWrap(true); 
			
			// setup the gui with buttons
			CreateFullPanel();
			CreateMaineWindow();
}
//CreateToolPanel() is just used to test formatting
		
		public static void CreateFullPanel(){
//	CreateToolPanel();
			CreateColorChooserToolPanel();
	CreateGridPanel();
	CreateCodeMakerToolPanel();
		
	}
		public static void CreateColorChooserToolPanel(){
				 ToolPanel.setBorder(BorderFactory.createLineBorder(Color.blue, 2));
				ToolPanel.add(colorChooser = new JColorChooser());
				colorChooser.setPreviewPanel(new JPanel());//Disables Preview panel
			     colorChooser.getSelectionModel();
			}
		
		public static void CreateCodeMakerToolPanel(){
			//	 colorChooser = new JColorChooser();
				 ToolPanel.setLayout(new GridLayout(2,1));
				 ToolPanel.setBorder(BorderFactory.createLineBorder(Color.blue, 2));
				 ToolPanel.add(ExitButton);
				 ExitButton.addActionListener(ExitActionListener);
				ToolPanel.add(createCodeButton);
				createCodeButton.addActionListener(CreateCodeButtonActionListener);
				ToolPanel.add(scroll);
				//ToolPanel.add(ColorChooserOutputText);
			}
	/*	
		public static void CreateToolPanel(){
		 ToolPanel.setBorder(BorderFactory.createLineBorder(Color.blue, 2));
		 ToolPanel.setLayout(new GridLayout(1,0));
		 ColorChooserButton.addActionListener(ColorChooserButtonActionListener);
		 ToolPanel.add(ColorChooserButton);
	     ColorChooserButton.setToolTipText("This is a tool tip.");
	}
*/


	public static void CreateGridPanel() {
	    GridPanel.setBorder(BorderFactory.createLineBorder(Color.blue, 2));
	    GridPanel.setLayout(new GridLayout(16, 16, 0, 0));
		CreateButtonArray();// Create array of buttons
		
	}

public static void CreateButtonArray(){
	  for (int i = 0; i < button.length; i++) {
        button[i] = new JButton(Integer.toString(i + 1));
		button[i].addActionListener(ButtonArrayActionListener);
        GridPanel.add(button[i]);
        button[i].setToolTipText("Set the Color");
    }
}
	
	public static void CreateMaineWindow(){
		MaineWindow.setLayout(new GridLayout(2,1));//Main Window is resizable
		MaineWindow.add(ToolPanel, ToolPanelString);
		MaineWindow.add(GridPanel, ToolPanelString);
		MaineWindow.setBounds(0,0,100,800);
		MaineWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    MaineWindow.pack();// pack all the contents of Main Window
		MaineWindow.setVisible(true);//Show Main Window
		}
	


}
