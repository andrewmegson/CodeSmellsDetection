package CodeSmellsClasses;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class Method,stores all information on a single Method of a java class.
 *
 * @author Andrew Megson
 * @version 10/07/19
 */
public class Method {

	public static int longMethodMetric = 10;
	
	private String methodBody; // probably not needed should be a parameter to
								// the constructor
	private String methodTitle; // or methodName
	private int methodLOC;
	private boolean noParameters;
	private boolean globalMethod;

	private boolean longMethod; // maybe should be calculated in higher class
	private boolean getterSetter;
	private boolean proceduralMethodName;
	
	

	/**
	 * Constructor for objects of Method class
	 * 
	 * @param String, a String of the full methodBody i.e. all the text in the method
	 */

	private Method(String methodBody) {
		// call setter methods

		setMethodBody(methodBody);
		setMethodTitle();
		setMethodLOC();
		setNoParameters();
		setGlobalMethod();
		setLongMethod();
		setGetterSetter();
		setProceduralMethodName();

	}

	
	/**
	* A static method that returns an instance of Method according to the parameter provided
	* 
	* @param methodBody,takes a String that is the full body of text of the method
	* 
	* @return a Method object, created from given parameter representing the full body of text of the method
	* 
	*/		
	public static Method getMethodInstance(String methodBody) {
		// checks
		
		if(methodBody == null)  
		{
			throw new IllegalArgumentException("null methodBody parameter passed to Method getMethodInstance method");
		}		
		

		Method M = new Method(methodBody);

		return M;
	}

	/**
	 * Private method to set the methodBody field
	 * 
	 * @param methodBody, a String representing the full text of the method
	 */	
	private void setMethodBody(String methodBody) {
		this.methodBody = methodBody;
	}

	
	/**
	 * Method to get the methodBody
	 * 
	 * @return a String, that represents the full text of the entire method
	 */	
	public String getMethodBody()
	{
		return methodBody;
	}
	

	/**
	 * Private method to get a String of the beginning of the method up to the first opening method body bracket
	 * 
	 *@return a String, which represents the beginning of the method up to the first opening method body bracket
	 */	
	private String getStartOfMethod() {
		String startOfMethod = "";
		// gets full start of method up to {
		Pattern pattern = Pattern.compile("^.+?[{]");
		Matcher matcher = pattern.matcher(methodBody);
		
		if (matcher.find())
		{
		startOfMethod = matcher.group().trim();
		}

		return startOfMethod;
	}

	/**
	 * Private method to set the methodTitle field, method selects method title and assigns it to relevant field
	 * 
	 */
	private void setMethodTitle() {

		// gets full start of method up to {
		String startOfMethod = this.getStartOfMethod();

		// get title up to and including (
		Pattern pattern2 = Pattern.compile("\\S+(?=[(])"); // could use regex match
														// but don't include see
														// notes
		Matcher matcher2 = pattern2.matcher(startOfMethod);
		
		String methodName ="";
		if (matcher2.find())
		{	
		methodName = matcher2.group();
		}
		// remove bracket from end

		String methodTitle = methodName.trim();

		// set name

		this.methodTitle = methodTitle;

	}

	/**
	 * Method to get the method title
	 * 
	 * @return a String, that represents the method title
	 */	
	public String getMethodTitle() // defensive copies (not needed for strings)
	{
		return methodTitle;
	}

	/**
	 * Private method to set the methodLOC field, method counts the lines of code in the method and assign the value to methodLOC
	 */
	private void setMethodLOC() {
		// count up all ; in methodBody

		int lines = methodBody.split(";").length - 1;

		methodLOC = lines;

	}

	/**
	 * Method to get a int which represents the number of lines of code for a method
	 * 
	 * @return int, which represents the number of lines of code for the method
	 */
	public int getMethodLOC() {
		return methodLOC;
	}

	/**
	 * Private method to set noParameters field, sets value to true if the method has no parameters, false otherwise
	 */
	private void setNoParameters() {

		String startOfMethod = this.getStartOfMethod();

		if (startOfMethod.contains("()")) {
			noParameters = true;
		} else {
			noParameters = false;
		}
	}

	/**
	 * Method to get a boolean representing if method has parameters
	 * 
	 * @return boolean, true is returned if method has no parameters, false otherwise
	 */
	public boolean getNoParameters() {
		return noParameters;
	}

	/**
	 * Private method to set globalMethod field, sets value to true if the method is declared static, false otherwise
	 */	
	private void setGlobalMethod() {
		String startOfMethod = this.getStartOfMethod();

		if (startOfMethod.contains(" static ")) {
			globalMethod = true;
		} else {
			globalMethod = false;
		}

	}

	/**
	 * Method to get a boolean representing if method has is global
	 * 
	 * @return boolean, true is returned if method is declared static, false otherwise
	 */	
	public boolean getGlobalMethod() {
		return globalMethod;
	}

	/**
	 * Private method to set longMethod field, sets value to true if the method has more lines than pre-determined constant value
	 * (which acts as a measure for if a method is considered long or not), false otherwise
	 */	
	private void setLongMethod()
	{
		if(methodLOC >= longMethodMetric)
		{
			longMethod = true;
		}
		else
		{
			longMethod = false;
		}
		
	}

	/**
	 * Method to get a boolean representing if method classed as long or not
	 * 
	 * @return boolean, true is returned if method has more lines than pre-determined constant value
	 * (which acts as a measure for if a method is considered long or not), false otherwise
	 */		
	public boolean getLongMethod()
	{
		return longMethod;
	}
	
	/**
	 * Private method to set getterSetter boolean, sets to true if method is a getter or setter, false otherwise
	 * 
	 */
	
private void setGetterSetter()
{
	getterSetter = false;
	
	//could also check if single line containing return
	
	if(methodTitle.toLowerCase().contains("get") || methodTitle.toLowerCase().contains("set"))
	{
		getterSetter = true;
	}
	
	if(this.methodLOC ==1 && methodBody.contains("return"))
	{
		getterSetter = true;
	}
}

/**
 * Method to get a boolean representing if method is a getter or setter
 * 
 * @return boolean, true is returned if method is a getter or setter method 
 * (determined by if method title contains 'get' or 'set' or if method consists of 
 * a single line containing a return key word), false otherwise
 */		
public boolean getGetterSetter()
{
	return getterSetter;
}
	


/**
 * Private method to set proceduralMethodName field, it is set to true if the method name is 
 * contained in a list of defined procedural names, and false otherwise
 */

private void setProceduralMethodName() {

	String startOfMethod = this.getStartOfMethod();

	proceduralMethodName = false;

	for (String X : Class.proceduralNames) {
		if ((startOfMethod.toLowerCase()).contains(X.toLowerCase())) {
			proceduralMethodName = true;
		}

	}

}

/**
 * Method to get a boolean representing if method has a procedural name or not
 * 
 * @return boolean, true is returned if the method name is 
 * contained in a list of defined procedural names, and false otherwise
 */
public boolean getProceduralName() 
{
	return proceduralMethodName;
}


}