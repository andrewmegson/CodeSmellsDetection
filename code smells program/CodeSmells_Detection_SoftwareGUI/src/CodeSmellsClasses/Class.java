

package CodeSmellsClasses;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class Class,stores all information on a single Class of a java program.
 *
 * @author Andrew Megson
 * @version 10/07/19
 */
public class Class {

	public static String[] proceduralNames = { "process", "control", "manage", "Procedure", "System", "compute", "display" }; 														

	public static double LCOMThreshold = 0.5;  //TODO move to separate class
	
	private String classBody; // probably not needed should be a parameter to
								// the constructor
	private String classTitle;
	private ArrayList<Field> classFields;
	private ArrayList<Method> classMethods;
	private boolean proceduralClassName;
	private boolean noPolyInheritance;
	private double classLCOM;
	private boolean dataClass;
	

	

/**
* Constructor for objects of Class class
* 
* @param String, a String of the full classBody i.e. all the text in the relevant .java file
 * @throws Exception 
*/
	private Class(String classBody) throws Exception {

		setClassBody(classBody);
		setClassTitle();
		setClassFields();
		setClassMethods();
		setProceduralClassName();
		setNoPolyInheritance();
		setDataClass();
		setClassLCOM();
	}

	
/**
* A static method that returns an instance of Class according to the parameter provided
* 
* @param classBody,takes a String that is the fullBody of the class's .java file 
* 
* @return a Class object, created from given parameter representing the java class
 * @throws Exception 
* 
*/	
	public static Class getClassInstance(String classBody) throws Exception {
		// checks
		
		if(classBody == null)   //parameter checks
		{
			throw new IllegalArgumentException("null classBody parameter passed to Class getClassInstance method");
		}
		
		
		Class C = new Class(classBody);

		return C;

	}

	/**
	 * Method to set the classBody which represents the java class as a single String
	 * 
	 * @param classBody, a single String of the full class info i.e. all content of its .java file 
	 */
	private void setClassBody(String classBody) {
		this.classBody = classBody;
	}

	/**
	 * Private method to get a String of the beginning of the java class up to the first opening class bracket
	 * 
	 *@return a String, which represents the beginning of the class java file up to the first opening class bracket
	 */
	
	private String getStartOfClass() {

		// gets full start of Class up to { (excluding comments, and imports)

		String startOfClass = "";
		
		Pattern pattern = Pattern.compile("([^;\\/]+?[{])");
		Matcher matcher = pattern.matcher(classBody);
		
		if (matcher.find())
		{
			startOfClass = matcher.group().trim();
		}		

		return startOfClass;
	}

	/**
	 * Method to get the classBody
	 * 	
	 * @return a single String that is the fullBody of the class's .java file 
	 */
	public String getClassBody() {
		return classBody;
	}

	/**
	 * Private method to set the classTitle field, method selects classTitle and assigns it to relevant field
	 * 
	 */
	private void setClassTitle() {

		String classPlusTitle = "";
		String startOfClass = this.getStartOfClass(); 

		// get title from start of class
		Pattern pattern = Pattern.compile("class\\s+[^\\s]+");
		Matcher matcher = pattern.matcher(startOfClass); 
		
		if (matcher.find())
		{
			classPlusTitle = matcher.group().trim();
		}		

		
		


		String[] titleArray = classPlusTitle.split("\\s+");
		String title = titleArray[titleArray.length - 1];


		title = title.trim();

		this.classTitle = title;

	}

	/**
	 * Method to get the class title
	 * 
	 * @return a String, that represents the class title
	 */
	
	public String getClassTitle() {
		return classTitle;
	}

	/**
	 * Private method to setClassFields field, method selects all fieldBody Strings 
	 * from classBody field and adds them to classFields ArrayList
	 * @throws Exception 
	 */
	private void setClassFields() throws Exception {

		classFields = new ArrayList<>();
		
		// regex to match a fullFieldBody
		String fieldRegex = "(private|public|protected)\\s+\\S+\\s+\\S+(\\s+\\S+)?(?=(\\s+)?(;|=))";

		ArrayList<String> allFieldBodyMatches = new ArrayList<>();

		Pattern pattern = Pattern.compile(fieldRegex);
		Matcher m = pattern.matcher(classBody);

		while (m.find()) {
			allFieldBodyMatches.add(m.group());
		}

		// now create fields with fieldBodymatches and add to classFields

		for (String X : allFieldBodyMatches) {
			Field F = Field.getFieldInstance(X);

			classFields.add(F);
		}

	}

	/**
	 * Method to get the list of class fields
	 * 
	 * @return an ArrayList<Field>, that contains all the class fields
	 */	
		
	public ArrayList<Field> getClassFields() {
		return classFields;
	}

	/**
	 * Private method to setClassMethods field, method selects all methodBody Strings 
	 * from classBody field and adds them to methodFields ArrayList
	 */	
	private void setClassMethods() {

		classMethods = new ArrayList<>();
		
		// regex to match a fullmethodBody
		
		String methodRegex = "(private|public|protected)(\\s+[^;({]+)(\\(\\)|\\(.+\\))"
				+ "[\\s\\S]+?(?=(private|public|protected)(\\s+[^;({]+)(\\(\\)|\\(.+\\))|\\Z)";

		ArrayList<String> allMethodBodyMatches = new ArrayList<>();

		Pattern pattern = Pattern.compile(methodRegex);
		Matcher m = pattern.matcher(classBody);

		while (m.find()) {
			allMethodBodyMatches.add(m.group());
		}

		// now create methods with methodBodymatches and add to classMethods

		for (String X : allMethodBodyMatches) {
			Method M = Method.getMethodInstance(X);

			classMethods.add(M);
		}

	}

	/**
	 * Method to get the list of class methods
	 * 
	 * @return an ArrayList<Method>, that contains all the class methods
	 */	
	
	public ArrayList<Method> getClassMethods() {

		return classMethods;
	}

	/**
	 * Private method to set proceduralClassName field, it is set to true if the class name is 
	 * contained in a list of defined procedural names, and false otherwise
	 */
	
	private void setProceduralClassName() {

		String startOfClass = this.getStartOfClass();

		proceduralClassName = false;

		for (String X : proceduralNames) {
			if ((startOfClass.toLowerCase()).contains(X.toLowerCase())) {
				proceduralClassName = true;
			}

		}

	}

	/**
	 * Method to get a boolean representing if class has a procedural name or not
	 * 
	 * @return boolean, true is returned if the class name is 
	 * contained in a list of defined procedural names, and false otherwise
	 */
	public boolean getProceduralName() {
		return proceduralClassName;
	}

	/**
	 * Private method to set noPolyInheritance field, it is set to true if the class doesn't use 
	 * inheritance and doesn't use polymorphism, and false otherwise
	 */	
	private void setNoPolyInheritance() {

		String startOfClass = this.getStartOfClass();

		noPolyInheritance = true;
		
		if (startOfClass.contains("extends") || startOfClass.contains("implements")) {
			noPolyInheritance = false;
			
		//polymorphism not accounted for need to add check TODO	
		}

	}

	/**
	 * Method to get a boolean representing if class uses polymorphism and inheritance or not 
	 * 
	 * @return boolean,true is returned if the class doesn't use 
	 * inheritance and doesn't use polymorphism, and false otherwise
	 */
	public boolean getNoPolyInheritance() 
	{
		return noPolyInheritance;
	}


/**
 * Method to check if the class is a data class i.e. only contains getter and setter methods
 * 	
 */
	
	
private void setDataClass()
{
	dataClass = true;
	
	
	for(Method M: classMethods)
	{
		//check if getter or setter
		if(!M.getGetterSetter())
		{
			dataClass = false;
		}
				
	}
	
	if (classLCOM > LCOMThreshold)
	{
		dataClass = false;
	}
}

/**
 * Method to check if class is a data class or not
 * 
 * @return boolean, returns true if all methods are getter or setters, false otherwise
 */
public boolean getDataClass()
{
	return dataClass;
}


/**
 * Method to set the classLCOM field of the class, this field represents the lack(low) of cohesion of methods of the class 
 * 
 * The value is a double between 0 and 1 based on the method of calculation defined by 
 * https://www.ndepend.com/docs/code-metrics?_ga=2.115313103.956256712.1563445519-488629983.1563445519#LCOM
 */
private void setClassLCOM()

{
	// need to calculate; no. of methods, no. of fields, no. of methods accessing a given field	
	double M = classMethods.size();	
	double F = classFields.size();	
	double sumMF = 0;
	
	for(Field field: this.classFields)
	{
		double MF = 0;
		
		for(Method method: this.classMethods)
		{
			if(method.getMethodBody().contains(field.getFieldName()))
			{
				MF++;
			}			
		}		
		sumMF += MF;		
	}
	
	double LCOM = 0;	
	
	LCOM = 1 - (sumMF/(M*F));		// LCOM takes a value between 0-1, 1 being a high level of LCOM
	
	classLCOM = LCOM;
	
}
	

	/**
	 * method to get a double representing the classes Lack(low) cohesion of methods (LCOM) value, value return is between 0 and 1
	 * 
	 * @return double, the higher the value returned is (i.e. closer to 1) the higher the LCOM of the class is
	 */
	public double getClassLCOM()
	{
		return classLCOM;
	}

	
	
	
	
	
}