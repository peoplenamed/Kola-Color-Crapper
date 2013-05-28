package main;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;


public class ReadWrite {
	////////////////////////
	String ReturnSaveVal = "Hello";
	////////////////////////
    static JFrame guiFrame;
    JTextArea tracker; 
    JPanel optPanel;
    JFileChooser fileDialog;
    
    //Note: Typically the main method will be in a
    //separate class. As this is a simple one class
    //example it's all in the one class.
    public static void main(String[] args) {
     
         //Use the event dispatch thread for Swing components
         EventQueue.invokeLater(new Runnable()
         {
             
            @Override
             public void run()
             {
                 
                 new ReadWrite();         
             }
         });
              
    }
     
    //handles the creation of the JFrame and
    //all it's components
    public ReadWrite()
    { 
        guiFrame = new JFrame();
        //make sure the program exits when the frame closes
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("Dialog Box Example");
        guiFrame.setSize(500,300);
        
        //This will center the JFrame in the middle of the screen
        guiFrame.setLocationRelativeTo(null);
        
        
        guiFrame.setLayout(new BorderLayout());
        
        //create an instance of JFileChooser class
        fileDialog = new JFileChooser();//"C:\\Documents and Settings\\Owner\\My Documents"
        
        //Using a JTextArea to diplay feedback
        tracker = new JTextArea("File Tracker:");
        tracker.setVisible(true);
        guiFrame.add(tracker, BorderLayout.NORTH);
        
        optPanel = new JPanel();
        optPanel.setLayout(new GridLayout(1,2));
        
        
        guiFrame.add(optPanel,BorderLayout.SOUTH);

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
        
        guiFrame.setVisible(true);
    }
    
    //Show a open file dialog box
    private void openDialog()
    {
        int openChoice = fileDialog.showOpenDialog(guiFrame);
        
        //display choice using tracker 
        logChoice(openChoice, "Open Dialog");
        
        if (openChoice == JFileChooser.APPROVE_OPTION)
        {
            //Put open file code in here
            File openFile = fileDialog.getSelectedFile();
            tracker.append("\nThe file selected is " + openFile.getName());
            tracker.append("\nThe file's path is " + openFile.getPath());
        }
    }
        
    //Show a save file dialog box
    private void saveDialog()
    {
        int saveChoice = fileDialog.showSaveDialog(guiFrame);
        
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
            writer = new BufferedWriter( new FileWriter( saveFile.getAbsolutePath()+".txt"));  
            writer.write( ReturnSaveVal);  
            writer.close( );  
         
            infoBoxCorrect();
            
            }  
            catch (IOException e)  
            {  
                infoBoxWrong();

            } } 

            
            
        }
        
        
        public static void infoBoxCorrect()
        {
        	   JOptionPane.showMessageDialog(guiFrame, "The Message was Saved Successfully!",  
                       "Success!", JOptionPane.INFORMATION_MESSAGE);          }
        
        public static void infoBoxWrong()
        {
            JOptionPane.showMessageDialog(guiFrame, "The Text could not be Saved!",  
                    "Error!", JOptionPane.INFORMATION_MESSAGE);          }
        //////////////////////////////////////////
    
    //append the button choice to the tracker JTextArea
    private void logChoice(int choice, String dialog)
    {
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
}