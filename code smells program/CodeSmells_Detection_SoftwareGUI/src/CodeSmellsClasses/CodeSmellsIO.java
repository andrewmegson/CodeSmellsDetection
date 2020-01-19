package CodeSmellsClasses;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class CodeSmellsIO, provides a console interface for a user to run the program.
 *
 * @author Andrew Megson
 * @version 15/08/19
 */

public class CodeSmellsIO {
	
	
	private Scanner k; // = new Scanner(System.in);
    private JavaProgram javaProgram;
    
    
    public CodeSmellsIO()
    {       
        k = new Scanner(System.in);
    }

    
    
    public static void main(String[] args)
    {
        CodeSmellsIO codeSmellsProgram = new CodeSmellsIO();
        codeSmellsProgram.startProgram();
    }    
    
    
  
    
    public void startProgram() 
    	{
    	try {  	   	
    	//read in javaProgram
    	System.out.println("Please enter the location of the Java Program i.e. the folder containing its .java files you wish to check for Code Smells.");
    	String fileLocation = k.nextLine();
    	
    	javaProgram = JavaProgram.getJavaProgramInstance(fileLocation);
    	
        printMenu();
        char ch = k.next().charAt(0);
        k.nextLine();
        while (ch != 'f')
        {
            switch(ch)
            {
                case 'a': checkAllCodeSmells();
                break;

                case 's': checkSpaghettiCode();
                break;

                case 'g': checkGodClass();
               break;

                default: System.out.println("Invalid entry, try again");
            }
            printMenu();
            ch = k.next().charAt(0);
            k.nextLine();
        }
    	}
    	catch(Exception e)
    	{
    		System.err.println("" + e);
    	}
    }
 
    

    public void checkAllCodeSmells()
    {
    	checkSpaghettiCode();
    	checkGodClass();
    	
    	
    	
    	
    	
    }
    
  
    public void checkGodClass()
    {
	ArrayList<Class> classesWithGodClassCodeSmell = javaProgram.getClassesWithGodClassCodeSmell();
    	
    	
    	if(classesWithGodClassCodeSmell.size() == 0)
    	{
    		//no smells detected
    		System.out.println("Detection test ran for incidences of God Class code smell.");
    		System.out.println();
    		System.out.println("Results;");
    		System.out.println("No instances of the God Class code smells were detected in the program.");
    	}
    	else
    	{
    		//detail smells print out
    		System.out.println("Detection test ran for incidences of God Class code smell.");
    		System.out.println();
    		System.out.println("Results;");
    		
    		//gc detected in % of classes
    		System.out.println("God Class was detected in " + classesWithGodClassCodeSmell.size() + "of the" 
    		+ javaProgram.getAllClasses().size() + "classes in the java program.");
    		
    		
    		
    		System.out.println("God Class code smells were detected in the following java classes for the given reasons;");
    		int count = 0;
    		for(Class C : classesWithGodClassCodeSmell)
    		{
    			count++;
    			System.out.println(count + ". " + C.getClassTitle() + ";");
    			
    			//list details of elements detected in each class
    			
    			if(C.getProceduralName())
    			{
    				System.out.println("     - this class contains a proceedural class name");
    			}
    			
    			
    			if(C.getClassFields().size() >= JavaProgram.largeNumberOfFieldsThreshold)
    			{
    				System.out.println("     - this class declares a large number of fields. Number of Fields declare =" 
    			+ C.getClassFields().size() +".");
    			}
    			
    			if(C.getClassLCOM() >=  Class.LCOMThreshold)
    			{
    				System.out.println("     - this class has a high LCOM (low cohension of methods) value, LCOM value =" + C.getClassLCOM() + ".");
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
    				System.out.println("          - the class is linked to the following " + numberOfDataClassesLinked + " data classes;");
    				
    				for(Class lc: linkedDataClasses)
    				{
    					System.out.println("        " + lc.getClassTitle());
    				}
    			}
    			
	   			
    			
    		}
    		
    		System.out.println();
    		
    		
    		//above identified code patterns are signs that code may need to be refactored to work best
    		
    		System.out.println("Recommendations;");
    		
    		System.out.println("The above code patterns suggest that the classes identified may contain the God Class code smell.");
    		System.out.println("It is up to the judgement of the programmer as to whether this is an issue with the code");
    		
    				
    		System.out.println();  //add refactoring methods
    		
    		
    		
    	}
    	
    	
    }
    
    
    public void checkSpaghettiCode()
    {
    	ArrayList<Class> classesWithSpaghettiCode = javaProgram.getClassesWithSpaghettiCodeSmell();
    	
    	
    	if(classesWithSpaghettiCode.size() == 0)
    	{
    		//no smells detected
    		System.out.println("Detection test ran for incidences of Spaghetti code smell.");
    		System.out.println();
    		System.out.println("Results;");
    		System.out.println("No instances of the Spaghetti code smells were detected in the program.");
    	}
    	else
    	{
    		//detail smells print out
    		System.out.println("Detection test ran for incidences of Spaghetti code smell.");
    		System.out.println();
    		System.out.println("Results;");
    		
    		
    		//sc detected in % of classes
    		System.out.println("Spaghetti code was detected in " + classesWithSpaghettiCode.size() + "of the" 
    		+ javaProgram.getAllClasses().size() + "classes in the java program.");
    		
    		
    		
    		System.out.println("Spaghetti code smells were detected in the following java classes for the given reasons;");
    		int count = 0;
    		for(Class C : classesWithSpaghettiCode )
    		{
    			count++;
    			System.out.println(count + ". " + C.getClassTitle() + ";");
    			
    			//list details of elements detected in each class
    			
    			if(C.getProceduralName())
    			{
    				System.out.println("     - this class contains a proceedural class name");
    			}
    			
    			
    			if(C.getNoPolyInheritance())
    			{
    				System.out.println("     - this class doesn't use polymorphism or inheritance in a significant way");
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
    				System.out.println("     - the following methods were identified as long methods with no parameters");
    				for(Method M: longNoParamMethods)
    				{
    					System.out.println("        " + M.getMethodTitle());
    				}
    			}
    			
    			if(proceduralMethodName)

    			{
    				System.out.println("     - the following methods were identified as containing procedural names");
    				for(Method M: longNoParamMethods)
    				{
    					System.out.println("        " + M.getMethodTitle());
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
    				System.out.println("     - the following fields were identified as global variables");
    				for(Field F: globalVariables)
    				{
    					System.out.println("        " + F.getFieldName());
    				}
    			}
			
    		   			
    			
    		}
    		
    		System.out.println();
    		
    		
    		//above identified code patterns are signs that code may need to be refactored to work best
    		
    		System.out.println("Recommendations;");
    		
    		System.out.println("The above code patterns suggest that the classes identified may contain Spaghetti code.");
    		System.out.println("It is up to the judgement of the programmer as to whether this is an issue with the code");
    		
    				
    		System.out.println();  //add refactoring methods
    		
    		
    	}
    	
    	
    	
    }
    
    
    private void printMenu()
    {
        System.out.println("------------------------------");
        System.out.println("MENU");
        System.out.println("a - to check for instances of all types of code smells currently detectable");
        System.out.println("s - to check for instances of the spaghetti code smell");
        System.out.println("g - to check for instances of the god class code smell");
        System.out.println("f - to terminate the program");     
        System.out.println("------------------------------");
        System.out.println("Type a letter and press Enter");

    }  
    
    
    
    
    
}
