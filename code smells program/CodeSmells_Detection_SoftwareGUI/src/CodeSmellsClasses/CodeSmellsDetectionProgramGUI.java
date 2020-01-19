package CodeSmellsClasses;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

/**
 * Class CodeSmellsDetectionProgramGUI, provides a GUI interface for a user to run the program.
 *
 * @author Andrew Megson
 * @version 15/08/19
 */

public class CodeSmellsDetectionProgramGUI implements ActionListener {
	private String folderID = "";
	private JFrame codeSmellsFrame;
	private JLabel folderLocationL, resultsL;
	private JTextField folderLocationTF;
	private JButton browse, runAll, runSpaghetti, runGod;
	private JTextArea resultsTA;
	private JPanel panel;
	private JScrollPane scroll;
	
	
	private JavaProgram javaProgram;
	
	
	public CodeSmellsDetectionProgramGUI()
	{
		
	codeSmellsFrame = new JFrame("Code Smells DetectionProgram");
	
	folderLocationL = new JLabel("Please enter the location of the Java Program i.e. the folder containing its .java files.");
	resultsL = new JLabel("Detection Results;");
	folderLocationTF = new JTextField(40);
	resultsTA = new JTextArea(20, 60);
	
	resultsTA.setEditable(false);
	scroll = new JScrollPane(resultsTA);
	scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
	

	browse = new JButton("Browse");	
	browse.addActionListener(this);
	
	runAll = new JButton("Run detection for all detectable code smells");	
	runAll.addActionListener(this);
	
	runSpaghetti = new JButton("Run detection Spagetti code smell");	
	runSpaghetti.addActionListener(this);	
	
	runGod = new JButton("Run detection for God Class code smell");	
	runGod.addActionListener(this);	
	

	
	
	panel = new JPanel(new GridBagLayout());
	
	GridBagConstraints C = new GridBagConstraints();
	
	C.insets = new Insets(10,10,10,10);
	
	
	C.gridx = 0;
	C.gridy = 1;
	panel.add(folderLocationL, C);
	
	C.gridx = 0;
	C.gridy = 2;
	panel.add(folderLocationTF, C);
	
	C.gridx = 1;
	C.gridy = 2;
	panel.add(browse, C);
	
	C.gridx = 0;
	C.gridy = 3;
	panel.add(runAll, C);
	
	C.gridx = 0;
	C.gridy = 4;
	panel.add(runSpaghetti, C);
	
	C.gridx = 0;
	C.gridy = 5;
	panel.add(runGod, C);
	
	C.gridx = 0;
	C.gridy = 6;
	panel.add(resultsL, C);
	
	C.gridx = 0;
	C.gridy = 7;
	panel.add(scroll, C);
	
	
	codeSmellsFrame.add(panel, BorderLayout.NORTH);
	
	
	codeSmellsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	codeSmellsFrame.pack();
	codeSmellsFrame.setVisible(true);
	codeSmellsFrame.setSize(900, 800);
	}
	
	
	private void setJavaProgram() throws Exception
	{
	
			javaProgram = JavaProgram.getJavaProgramInstance(folderLocationTF.getText());

	}
	
	
	
	public void actionPerformed(ActionEvent event)
	{
	
		try {
		// ref for code to add browse button function; 
		//https://stackoverflow.com/questions/2251012/jbutton-in-java-swingto-browse-through-pc-folders
		
		if (event.getSource() == browse)
		       {
		    JFileChooser chooser = new JFileChooser(new File(System.getProperty("user.home") + "\\Downloads")); //Downloads Directory as default
		           chooser.setDialogTitle("Select Location");
		           chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		           chooser.setAcceptAllFileFilterUsed(false);
		           if (chooser.showSaveDialog(folderLocationTF) == JFileChooser.APPROVE_OPTION)
		           { 
		                folderID = chooser.getSelectedFile().getPath();
		               folderLocationTF.setText(folderID);
		           }
		           
		           setJavaProgram();
		       }
		
		
		else if(event.getSource() == runSpaghetti)
		{
			if(javaProgram == null)
			{
				setJavaProgram();
			}
			//run spaghetti code method
			checkSpaghettiCode();
    		
    		
		}
		else if (event.getSource() == runGod)
		{
			if(javaProgram == null)
			{
				setJavaProgram();
			}	
			checkGodClass();
		}
		else 
		{
			if(javaProgram == null)
			{
			
					setJavaProgram();

			}
			checkSpaghettiCode();
			checkGodClass();
		}
		
		}
		
		catch (Exception e)
		{
			resultsTA.append("" + e);
		}
	}
	
	
	public static void main(String[] args)
	{

	         
		CodeSmellsDetectionProgramGUI csProgram = new CodeSmellsDetectionProgramGUI();
	            
		 
	}
	
	
	 public void checkSpaghettiCode()
	    {
		 String output = "";
	    	ArrayList<Class> classesWithSpaghettiCode = javaProgram.getClassesWithSpaghettiCodeSmell();
	    	
	    	
	    	if(classesWithSpaghettiCode.size() == 0)
	    	{
	    		//no smells detected
	    		output += "Detection test ran for incidences of Spaghetti code smell. \n";
	    		output += "\n";
	    		output += "Results; \n";
	    		output += "No instances of the Spaghetti code smells were detected in the program. \n";
	    		output += "\n";
	    		output += "\n";
	    	}
	    	else
	    	{
	    		//detail smells print out
	    		output += "Detection test ran for incidences of Spaghetti code smell. \n";
	    		output += "\n";
	    		output += "Results; \n";
	    		
	    		//sc detected in % of classes
	    		output += "Spaghetti code was detected in " + classesWithSpaghettiCode.size() + " of the " 
	    		+ javaProgram.getAllClasses().size() + " classes in the java program. \n";
	    		
	    		
	    		
	    		output += "Spaghetti code smells were detected in the following java classes for the given reasons; \n";
	    		int count = 0;
	    		for(Class C : classesWithSpaghettiCode )
	    		{
	    			count++;
	    			output += count + ". " + C.getClassTitle() + "; \n";
	    			
	    			//list details of elements detected in each class
	    			
	    			if(C.getProceduralName())
	    			{
	    				output += "     - this class contains a proceedural class name \n";
	    			}
	    			
	    			
	    			if(C.getNoPolyInheritance())
	    			{
	    				output += "     - this class doesn't use polymorphism or inheritance in a significant way \n";
	    			}
	    			
	    			boolean longMethodAndNoParam = false;
	    			boolean proceduralMethodName = false;
	    			
	    			ArrayList<Method> longNoParamMethods = new ArrayList(); 
	    			ArrayList<Method> proceduralNamemethods = new ArrayList();
	    			
	    			for(Method M : C.getClassMethods())
	    			{
	    				if(M.getNoParameters() && M.getLongMethod())
	    				{
	    					longMethodAndNoParam = true;
	    					longNoParamMethods.add(M);
	    					
	    				}
	    				
	    				
	    				if(M.getProceduralName())
	    				{
	    					proceduralMethodName = true;
	    					proceduralNamemethods.add(M);
	    				}
	    				
	    			}
	    			
	    			if(longMethodAndNoParam)

	    			{
	    				output += "     - the following methods were identified as long methods with no parameters \n";
	    				for(Method M: longNoParamMethods)
	    				{
	    					output += "        " + M.getMethodTitle() + "\n";
	    				}
	    			}
	    			
	    			if(proceduralMethodName)

	    			{
	    				output += "     - the following methods were identified as containing procedural names \n";
	    				for(Method M: longNoParamMethods)
	    				{
	    					output += "        " + M.getMethodTitle() +"\n";
	    				}
	    			}
	    			
	    				
	    			boolean globalVariable = false;   			
	    			ArrayList<Field> globalVariables = new ArrayList(); 
	    			
	    			for(Field F : C.getClassFields())
	    			{
	    				if(F.getIsGlobal())
	    				{
	    					globalVariable = true;
	    					globalVariables.add(F);
	    				}
	    				
	    				
	    			}
	    			
	       			if(globalVariable)

	    			{
	       				output += "     - the following fields were identified as global variables \n";
	    				for(Field F: globalVariables)
	    				{
	    					output += "        " + F.getFieldName() +"\n";
	    				}
	    			}
				
	    		   			
	    			
	    		}
	    		
	    		output += "\n";
	    		
	    		
	    		//above identified code patterns are signs that code may need to be refactored to work best
	    		
	    		output += "Recommendations; \n";
	    		
	    		output += "The above code patterns suggest that the classes identified may contain Spaghetti code. \n";
	    		output += "It is up to the judgement of the programmer as to whether this is an issue with the code \n";
	    		
	    				
	    		output += "";  //add refactoring methods
	    		
	    		output += "\n";
	    		output += "\n";
	    		
	    	}
	    	
	    	
	    	resultsTA.append(output);
	    }	
	
	
	 
	   public void checkGodClass()
	    {
		   String output = "";
		ArrayList<Class> classesWithGodClassCodeSmell = javaProgram.getClassesWithGodClassCodeSmell();
	    	
	    	
	    	if(classesWithGodClassCodeSmell.size() == 0)
	    	{
	    		//no smells detected
	    		output += "Detection test ran for incidences of God Class code smell. \n";
	    		output += "\n";
	    		output += "Results; \n";
	    		output += "No instances of the God Class code smells were detected in the program. \n";
	    		output += "\n";
	    		output += "\n";
	    	}
	    	else
	    	{
	    		//detail smells print out
	    		output += "Detection test ran for incidences of God Class code smell. \n";
	    		output += "\n";
	    		output += "Results; \n";
	    		
	    		//gc detected in % of classes
	    		output += "God Class was detected in " + classesWithGodClassCodeSmell.size() + " of the " 
	    		+ javaProgram.getAllClasses().size() + " "
	    				+ "classes in the java program. \n";
	    		
	    		
	    		
	    		output += "God Class code smells were detected in the following java classes for the given reasons; \n";
	    		int count = 0;
	    		for(Class C : classesWithGodClassCodeSmell)
	    		{
	    			count++;
	    			output += count + ". " + C.getClassTitle() + "; \n";
	    			
	    			//list details of elements detected in each class
	    			
	    			if(C.getProceduralName())
	    			{
	    				output += "     - this class contains a proceedural class name \n";
	    			}
	    			
	    			
	    			if(C.getClassFields().size() >= JavaProgram.largeNumberOfFieldsThreshold)
	    			{
	    				output += "     - this class declares a large number of fields. Number of Fields declare =" 
	    			+ C.getClassFields().size() +". \n";
	    			}
	    			
	    			if(C.getClassLCOM() >=  Class.LCOMThreshold)
	    			{
	    				output += "     - this class has a high LCOM (low cohension of methods) value, LCOM value =" + C.getClassLCOM() + ". \n";
	    			}
	    			
	    			int numberOfDataClassesLinked = 0;
	    			ArrayList<Class> linkedDataClasses = new ArrayList(); 
	    			
	    			for(Class DC : javaProgram.getDataClasses())
	    			{
	    				if(C.getClassBody().contains(DC.getClassTitle()))
	    				{
	    					numberOfDataClassesLinked++;
	    					linkedDataClasses.add(DC);
	    				}
	    		
	    			}
	    			
	    			if(numberOfDataClassesLinked >= JavaProgram.linkedDataClassesThreshold)  //dataclass threshold should be no. of dataclasses/2
	    			{
	    				output += "          - the class is linked to the following " + numberOfDataClassesLinked + " data classes; \n";
	    				
	    				for(Class lc: linkedDataClasses)
	    				{
	    					output += "        " + lc.getClassTitle() +"\n";
	    				}
	    			}
	    			
		   			
	    			
	    		}
	    		
	    		output += "\n";
	    		
	    		
	    		//above identified code patterns are signs that code may need to be refactored to work best
	    		
	    		output += "Recommendations; \n";
	    		
	    		output += "The above code patterns suggest that the classes identified may contain the God Class code smell. \n";
	    		output += "It is up to the judgement of the programmer as to whether this is an issue with the code \n";
	    		
	    				
	    		output += "\n";  //add refactoring methods
	    		
	    		output += "\n";
	    		output += "\n";
	    		
	    	}
	    	
	    	resultsTA.append(output);
	    }
	    	 
	
	   
   
	

}