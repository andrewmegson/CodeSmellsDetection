




package CodeSmellsClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class JavaProgram, stores all information of a java programs classes.
 *
 * @author Andrew Megson
 * @version 10/07/19
 */


public class JavaProgram {

	private ArrayList<Class> classes = new ArrayList<>();;

	Scanner inFile;
	
	public static int largeNumberOfFieldsThreshold = 10;
	public static int linkedDataClassesThreshold = 10;
	
/**
* Constructor for objects of JavaProgram class
* 
* @param programFolder, takes a String that is the file location of the folder 
* that contains the java programs .java files (i.e. source code files)
 * @throws Exception 
*/
	private JavaProgram(String programFolder) throws Exception {
		this.readInJavaProgram(programFolder);

	}


	
/**
* A static method that returns an instance of JavaProgram according to the parameter provided
* 
* @param programFolder, takes a String that is the file location of the folder 
* 
* @return a JavaProgram object, created from given parameter representing the java program
 * @throws Exception 
*/
	
	public static JavaProgram getJavaProgramInstance(String programFolder) throws Exception {
		
		
		if(programFolder == null)   //parameter checks
		{
			throw new IllegalArgumentException("null programFolder parameter passed to JavaProgram getJavaProgramInstance method");
		}		
		
		
		JavaProgram javaProgram = new JavaProgram(programFolder);

		return javaProgram;
	}

	
	
/**
 * Private method to get a String representation of a java class from a given file location
 * 
 * @param fileLocation, a String of the file location of the file to be converted to a String
 * 
 * @return a String which is the full body of the file located at the given location
 * 
 * @throws FileNotFoundException, if the given parameter for the file location in invalid
 */

	private String readClassInfo(String fileLocation) throws FileNotFoundException {

		// code reference; https://stackoverflow.com/questions/3402735/what-is-simplest-way-to-read-a-file-into-string
		inFile = new Scanner(new FileReader(fileLocation));

		String classBodyString = inFile.useDelimiter("\\Z").next();

		inFile.close();
		
		return classBodyString;

	}



/**
 * Private method to create java objects of all class, method, field information 
 * from source code .java files at a specified folder location
 * 
 * @param folderLocation, takes a String that is the file location of the folder 
 * containing all source code i.e. .java files for java program 
 * @throws Exception 
 */
		
	private void readInJavaProgram(String folderLocation) throws Exception {
		
		// code reference; https://stackoverflow.com/questions/5694385/getting-the-filenames-of-all-files-in-a-folder
		// get a list of all files in folder
		// loop through files if .java make into class object and add to list

		File programFolder = new File(folderLocation);		//reference method as below stackoverflow.com
		File[] listOfFiles = programFolder.listFiles();

		// go through arraylist if .java make file to class object, add to arraylist<class>		
		boolean validLocation = false;
		
		for (File F : listOfFiles) 
		{
			if (F.getAbsolutePath().contains(".java")) {
				String classBody = this.readClassInfo(F.getAbsolutePath());

				Class C = Class.getClassInstance(classBody);

				classes.add(C);
				validLocation = true;

			}
		}

		if(!validLocation)
		{
			throw new Exception("The entered file location is not valid as it does not contain any Java files.");
		}
		
	}


/**
 * Method to get a list of all classes in a JavaProgram object that contain a spaghetti code smell
 *
 * 	
 * @return ArrayList<String>, an ArrayList of the names of all the classes in the JavaProgram object 
 * where the spaghetti code smell has been detected
 */
public ArrayList<Class> getClassesWithSpaghettiCodeSmell()  //instead return a string of all info on smell detected fields, methods affected
{
	//go through each class to check for spaghetti code
	//if present make a note of it
	
	//output which classes contain spaghetti code and refactoring method recommended.
	
	ArrayList<Class> classesWithSpaghettiCode = new ArrayList<>();
	
	
	for(Class C : classes)
	{
		boolean longMethodAndNoParam = false;
		boolean globalVariable =false;
		boolean proceduralClassName =false;
		boolean proceduralMethodName = false;
		boolean noPolyInheritance =false;
		
		
		
		noPolyInheritance = C.getNoPolyInheritance();
		proceduralClassName = C.getProceduralName();
		
		
		
		for(Field F : C.getClassFields())
		{
			if(F.getIsGlobal())
			{
				globalVariable = true;
			}
			
			
		}
		
		for(Method M : C.getClassMethods())
		{
			if(M.getNoParameters() && M.getLongMethod())
			{
				longMethodAndNoParam = true;
				
			}
			
			
			
			if(M.getProceduralName())
			{
				proceduralMethodName = true;
			}
			
		}
		
		
		//if statement
		
		if
		
		((longMethodAndNoParam) ||  ((globalVariable ||   (proceduralClassName || proceduralMethodName)) ||  noPolyInheritance)  )
		
		{
			classesWithSpaghettiCode.add(C);
		}		
		
		//add to arraylist
		//change variable smell detected to true??
		
		
	}
	
	return classesWithSpaghettiCode;

}
	
	
public ArrayList<Class>	getDataClasses()
{
	ArrayList<Class> listOfDataClasses = new ArrayList<>();
	
	for(Class C : classes)
	{
		// if matches Data class definition add to arraylist
		if(C.getDataClass())
		{
			listOfDataClasses.add(C);
		}
		
	}
	
	return listOfDataClasses;

	
}
	



public ArrayList<Class> getClassesWithGodClassCodeSmell()	
{
	ArrayList<Class> classesWithGodClassCodeSmell = new ArrayList<>();
	
	//GodClass if; depends on data classes, many fields, method LCOM, processing name
	
	for(Class C : classes)
	{
		boolean declareManyFields = false;
		boolean lowLCOM = false;
		boolean proceduralName = false;
		boolean dependsOnDataClasses = false;
		
		if(C.getClassFields().size() >= largeNumberOfFieldsThreshold )
		{
			declareManyFields = true;
		}
		
		if(C.getClassLCOM() >=  Class.LCOMThreshold)
		{
			lowLCOM = true;
		}
		
		if(C.getProceduralName())
		{
			proceduralName = true;
		}
		
		//check if class depends on data classes??
		
		int numberOfDataClassesLinked = 0;
		
		for(Class DC : this.getDataClasses())
		{
			if(C.getClassBody().contains(DC.getClassTitle()))
			{
				numberOfDataClassesLinked++;
			}
	
		}
		
		if(numberOfDataClassesLinked >= linkedDataClassesThreshold)  //dataclass threshold should be no. of dataclasses/2
		{
			dependsOnDataClasses = true;
		}
				
		
		if((declareManyFields || lowLCOM) || (proceduralName || dependsOnDataClasses))
				{
					classesWithGodClassCodeSmell.add(C);			
				}
				
	}
	
	
	
	return classesWithGodClassCodeSmell;
}
	
	
	
	
	public ArrayList<Class> getAllClasses()
	{
		return classes;
	}
	
	
	
	
	
	
	
	
	
	
}