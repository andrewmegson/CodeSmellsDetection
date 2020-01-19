package TestClasses;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import CodeSmellsClasses.Field;
import CodeSmellsClasses.Method;
import CodeSmellsClasses.Class;

class ClassTest {

private String classBody = "package CodeSmellsClasses;\r\n" + 
		"\r\n" + 
		"import java.util.regex.Matcher;\r\n" + 
		"import java.util.regex.Pattern;\r\n" + 
		"\r\n" + 
		"/**\r\n" + 
		" * Class Method,stores all information on a single Method of a java class.\r\n" + 
		" *\r\n" + 
		" * @author Andrew Megson\r\n" + 
		" * @version 10/07/19\r\n" + 
		" */\r\n" + 
		"public class Method {\r\n" + 
		"\r\n" + 
		"	public static int longMethodMetric;\r\n" + 
		"	\r\n" + 
		"	private String methodBody; // probably not needed should be a parameter to\r\n" + 
		"								// the constructor\r\n" + 
		"	private String methodTitle; // or methodName\r\n" + 
		"	private int methodLOC;\r\n" + 
		"	private boolean noParameters;\r\n" + 
		"	private boolean globalMethod;\r\n" + 
		"\r\n" + 
		"	private boolean longMethod; // maybe should be calculated in higher class\r\n" + 
		"	private boolean getterSetter;\r\n" + 
		"	private boolean proceduralMethodName;\r\n" + 
		"	\r\n" + 
		"	\r\n" + 
		"\r\n" + 
		"	/**\r\n" + 
		"	 * Constructor for objects of Method class\r\n" + 
		"	 * \r\n" + 
		"	 * @param String, a String of the full methodBody i.e. all the text in the method\r\n" + 
		"	 */\r\n" + 
		"\r\n" + 
		"	private Method(String methodBody) {\r\n" + 
		"		// call setter methods\r\n" + 
		"\r\n" + 
		"		setMethodBody(methodBody);\r\n" + 
		"		setMethodTitle();\r\n" + 
		"		setMethodLOC();\r\n" + 
		"		setNoParameters();\r\n" + 
		"		setGlobalMethod();\r\n" + 
		"		setLongMethod();\r\n" + 
		"		setGetterSetter();\r\n" + 
		"		setProceduralMethodName();\r\n" + 
		"\r\n" + 
		"	}\r\n" + 
		"\r\n" + 
		"	\r\n" + 
		"	/**\r\n" + 
		"	* A static method that returns an instance of Method according to the parameter provided\r\n" + 
		"	* \r\n" + 
		"	* @param methodBody,takes a String that is the full body of text of the method\r\n" + 
		"	* \r\n" + 
		"	* @return a Method object, created from given parameter representing the full body of text of the method\r\n" + 
		"	* \r\n" + 
		"	*/		\r\n" + 
		"	public static Method getMethodInstance(String methodBody) {\r\n" + 
		"		// checks\r\n" + 
		"\r\n" + 
		"		Method M = new Method(methodBody);\r\n" + 
		"\r\n" + 
		"		return M;\r\n" + 
		"	}\r\n" + 
		"\r\n" + 
		"	/**\r\n" + 
		"	 * Private method to set the methodBody field\r\n" + 
		"	 * \r\n" + 
		"	 * @param methodBody, a String representing the full text of the method\r\n" + 
		"	 */	\r\n" + 
		"	private void setMethodBody(String methodBody) {\r\n" + 
		"		this.methodBody = methodBody;\r\n" + 
		"	}\r\n" + 
		"\r\n" + 
		"	\r\n" + 
		"	/**\r\n" + 
		"	 * Method to get the methodBody\r\n" + 
		"	 * \r\n" + 
		"	 * @return a String, that represents the full text of the entire method\r\n" + 
		"	 */	\r\n" + 
		"	public String getMethodBody()\r\n" + 
		"	{\r\n" + 
		"		return methodBody;\r\n" + 
		"	}\r\n" + 
		"	\r\n" + 
		"\r\n" + 
		"	/**\r\n" + 
		"	 * Private method to get a String of the beginning of the method up to the first opening method body bracket\r\n" + 
		"	 * \r\n" + 
		"	 *@return a String, which represents the beginning of the method up to the first opening method body bracket\r\n" + 
		"	 */	\r\n" + 
		"	private String getStartOfMethod() {\r\n" + 
		"		String startOfMethod = \"\";\r\n" + 
		"		// gets full start of method up to {\r\n" + 
		"		Pattern pattern = Pattern.compile(\"^.+?[{]\");\r\n" + 
		"		Matcher matcher = pattern.matcher(methodBody);\r\n" + 
		"		\r\n" + 
		"		if (matcher.find())\r\n" + 
		"		{\r\n" + 
		"		startOfMethod = matcher.group().trim();\r\n" + 
		"		}\r\n" + 
		"\r\n" + 
		"		return startOfMethod;\r\n" + 
		"	}\r\n" + 
		"\r\n" + 
		"	/**\r\n" + 
		"	 * Private method to set the methodTitle field, method selects method title and assigns it to relevant field\r\n" + 
		"	 * \r\n" + 
		"	 */\r\n" + 
		"	private void setMethodTitle() {\r\n" + 
		"\r\n" + 
		"		// gets full start of method up to {\r\n" + 
		"		String startOfMethod = this.getStartOfMethod();\r\n" + 
		"\r\n" + 
		"		// get title up to and including (\r\n" + 
		"		Pattern pattern2 = Pattern.compile(\"\\\\S+[(]\"); // could use regex match\r\n" + 
		"														// but don't include see\r\n" + 
		"								}						// notes\r\n" + 
		"		Matcher matcher2 = pattern2.matcher(startOfMethod);\r\n" + 
		"		\r\n" + 
		"		String methodNamePlusBracket =\"\";\r\n" + 
		"		if (matcher2.find())\r\n" + 
		"		{	\r\n" + 
		"		methodNamePlusBracket = matcher2.group();\r\n" + 
		"		}\r\n" + 
		"		// remove bracket from end\r\n" + 
		"\r\n" + 
		"		String methodName = methodNamePlusBracket.substring(0, methodNamePlusBracket.length() - 1).trim();\r\n" + 
		"\r\n" + 
		"		// set name\r\n" + 
		"\r\n" + 
		"		this.methodTitle = methodName;\r\n" + 
		"\r\n" + 
		"	}\r\n" + 
		"\r\n" + 
		"	/**\r\n" + 
		"	 * Method to get the method title\r\n" + 
		"	 * \r\n" + 
		"	 * @return a String, that represents the method title\r\n" + 
		"	 */	\r\n" + 
		"	public String getMethodTitle() // defensive copies (not needed for strings)\r\n" + 
		"	{\r\n" + 
		"		return methodTitle;\r\n" + 
		"	}\r\n" + 
		"\r\n" + 
		"	/**\r\n" + 
		"	 * Private method to set the methodLOC field, method counts the lines of code in the method and assign the value to methodLOC\r\n" + 
		"	 */\r\n" + 
		"	private void setMethodLOC() {\r\n" + 
		"		// count up all ; in methodBody\r\n" + 
		"\r\n" + 
		"		int lines = methodBody.split(\";\").length - 1;\r\n" + 
		"\r\n" + 
		"		methodLOC = lines;\r\n" + 
		"\r\n" + 
		"	}\r\n" + 
		"\r\n" + 
		"	/**\r\n" + 
		"	 * Method to get a int which represents the number of lines of code for a method\r\n" + 
		"	 * \r\n" + 
		"	 * @return int, which represents the number of lines of code for the method\r\n" + 
		"	 */\r\n" + 
		"	public int getMethodLOC() {\r\n" + 
		"		return methodLOC;\r\n" + 
		"	}\r\n" + 
		"\r\n" + 
		"	/**\r\n" + 
		"	 * Private method to set noParameters field, sets value to true if the method has no parameters, false otherwise\r\n" + 
		"	 */\r\n" + 
		"	private void setNoParameters() {\r\n" + 
		"\r\n" + 
		"		String startOfMethod = this.getStartOfMethod();\r\n" + 
		"\r\n" + 
		"		if (startOfMethod.contains(\"()\")) {\r\n" + 
		"			noParameters = true;\r\n" + 
		"		} else {\r\n" + 
		"			noParameters = false;\r\n" + 
		"		}\r\n" + 
		"	}\r\n" + 
		"\r\n" + 
		"	/**\r\n" + 
		"	 * Method to get a boolean representing if method has parameters\r\n" + 
		"	 * \r\n" + 
		"	 * @return boolean, true is returned if method has no parameters, false otherwise\r\n" + 
		"	 */\r\n" + 
		"	public boolean getNoParameters() {\r\n" + 
		"		return noParameters;\r\n" + 
		"	}\r\n" + 
		"\r\n" + 
		"	/**\r\n" + 
		"	 * Private method to set globalMethod field, sets value to true if the method is declared static, false otherwise\r\n" + 
		"	 */	\r\n" + 
		"	private void setGlobalMethod() {\r\n" + 
		"		String startOfMethod = this.getStartOfMethod();\r\n" + 
		"\r\n" + 
		"		if (startOfMethod.contains(\" static \")) {\r\n" + 
		"			globalMethod = true;\r\n" + 
		"		} else {\r\n" + 
		"			globalMethod = false;\r\n" + 
		"		}\r\n" + 
		"\r\n" + 
		"	}\r\n" + 
		"\r\n" + 
		"	/**\r\n" + 
		"	 * Method to get a boolean representing if method has is global\r\n" + 
		"	 * \r\n" + 
		"	 * @return boolean, true is returned if method is declared static, false otherwise\r\n" + 
		"	 */	\r\n" + 
		"	public boolean getGlobalMethod() {\r\n" + 
		"		return globalMethod;\r\n" + 
		"	}\r\n" + 
		"\r\n" + 
		"	/**\r\n" + 
		"	 * Private method to set longMethod field, sets value to true if the method has more lines than pre-determined constant value\r\n" + 
		"	 * (which acts as a measure for if a method is considered long or not), false otherwise\r\n" + 
		"	 */	\r\n" + 
		"	private void setLongMethod()\r\n" + 
		"	{\r\n" + 
		"		if(methodLOC >= longMethodMetric)\r\n" + 
		"		{\r\n" + 
		"			longMethod = true;\r\n" + 
		"		}\r\n" + 
		"		else\r\n" + 
		"		{\r\n" + 
		"			longMethod = false;\r\n" + 
		"		}\r\n" + 
		"		\r\n" + 
		"	}\r\n" + 
		"\r\n" + 
		"	/**\r\n" + 
		"	 * Method to get a boolean representing if method classed as long or not\r\n" + 
		"	 * \r\n" + 
		"	 * @return boolean, true is returned if method has more lines than pre-determined constant value\r\n" + 
		"	 * (which acts as a measure for if a method is considered long or not), false otherwise\r\n" + 
		"	 */		\r\n" + 
		"	public boolean getLongMethod()\r\n" + 
		"	{\r\n" + 
		"		return longMethod;\r\n" + 
		"	}\r\n" + 
		"	\r\n" + 
		"	/**\r\n" + 
		"	 * Private method to set getterSetter boolean, sets to true if method is a getter or setter, false otherwise\r\n" + 
		"	 * \r\n" + 
		"	 */\r\n" + 
		"	\r\n" + 
		"private void setGetterSetter(gfgf gfgfg)\r\n" + 
		"{\r\n" + 
		"	getterSetter = false;\r\n" + 
		"	\r\n" + 
		"	//could also check if single line containing return\r\n" + 
		"	\r\n" + 
		"	if(methodTitle.toLowerCase().contains(\"get\") || methodTitle.toLowerCase().contains(\"set\"))\r\n" + 
		"	{\r\n" + 
		"		getterSetter = true;\r\n" + 
		"	}\r\n" + 
		"	\r\n" + 
		"	if(this.methodLOC ==1 && methodBody.contains(\"return\"))\r\n" + 
		"	{\r\n" + 
		"		getterSetter = true;\r\n" + 
		"	}\r\n" + 
		"}\r\n" + 
		"\r\n" + 
		"/**\r\n" + 
		" * Method to get a boolean representing if method is a getter or setter\r\n" + 
		" * \r\n" + 
		" * @return boolean, true is returned if method is a getter or setter method \r\n" + 
		" * (determined by if method title contains 'get' or 'set' or if method consists of \r\n" + 
		" * a single line containing a return key word), false otherwise\r\n" + 
		" */		\r\n" + 
		"public boolean getGetterSetter()\r\n" + 
		"{   test;\r\n" + 
		"	return getterSetter;\r\n" + 
		"}\r\n" + 
		"	\r\n" + 
		"\r\n" + 
		"\r\n" + 
		"/**\r\n" + 
		" * Private method to set proceduralMethodName field, it is set to true if the method name is \r\n" + 
		" * contained in a list of defined procedural names, and false otherwise\r\n" + 
		" */\r\n" + 
		"\r\n" + 
		"private void setProceduralMethodName() {\r\n" + 
		"\r\n" + 
		"	String startOfMethod = this.getStartOfMethod();\r\n" + 
		"\r\n" + 
		"	proceduralMethodName = false;\r\n" + 
		"\r\n" + 
		"	for (String X : Class.proceduralNames) {\r\n" + 
		"		if ((startOfMethod.toLowerCase()).contains(X.toLowerCase())) {\r\n" + 
		"			proceduralMethodName = true;\r\n" + 
		"		}\r\n" + 
		"\r\n" + 
		"	}\r\n" + 
		"\r\n" + 
		"}\r\n" + 
		"\r\n" + 
		"/**\r\n" + 
		" * Method to get a boolean representing if method has a procedural name or not\r\n" + 
		" * \r\n" + 
		" * @return boolean, true is returned if the method name is \r\n" + 
		" * contained in a list of defined procedural names, and false otherwise\r\n" + 
		" */\r\n" + 
		"public boolean getProceduralName() \r\n" + 
		"{\r\n" + 
		"	return proceduralMethodName;\r\n" + 
		"}\r\n" + 
		"\r\n" + 
		"\r\n" + 
		"}";


	
	
//	@BeforeAll
//	static void setUpBeforeClass() throws Exception {
//	}
//
//	@AfterAll
//	static void tearDownAfterClass() throws Exception {
//	}



	@Test
	void testGetClassInstance() throws Exception {
		
				Class testClass = Class.getClassInstance(classBody);

				assertNotNull(testClass);
				
		
	}

	@Test
	void testGetClassBody() throws Exception {
		
		Class testClass = Class.getClassInstance(classBody);
		
		
		assertEquals(testClass.getClassBody(), classBody);
		
		
	}

	@Test
	void testGetClassTitle() throws Exception {
		Class testClass = Class.getClassInstance(classBody);
		
		assertEquals(testClass.getClassTitle(),"Method");
	}

	@Test
	void testGetClassFields() throws Exception {
		Class testClass = Class.getClassInstance(classBody);
		
		assertEquals(testClass.getClassFields().size(), 9);
	}

	@Test
	void testGetClassMethods() throws Exception {
		Class testClass = Class.getClassInstance(classBody);
		
		assertEquals(testClass.getClassMethods().size(), 19);

		
	}

	@Test
	void testGetProceduralName1() throws Exception {
		Class testClass = Class.getClassInstance(classBody);
		
		assertEquals(testClass.getProceduralName(), false);
	}

	@Test
	void testGetproceduralName2() throws Exception {
		String classBodyProcedural = "public class ManagementSystem {\r\n" + 
				"\r\n" + 
				"	public static int longMethodMetric = 10;\r\n" + 
				"	\r\n" + 
				"	private String methodBody; // probably not needed should be a parameter to\r\n" + 
				"								// the constructor\r\n" + 
				"	private String methodTitle; // or methodName\r\n" + 
				"	private int methodLOC;\r\n" + 
				"	private boolean noParameters;\r\n" + 
				"	private boolean globalMethod;\r\n" + 
				"\r\n" + 
				"	private boolean longMethod; // maybe should be calculated in higher class\r\n" + 
				"	private boolean getterSetter;\r\n" + 
				"	private boolean proceduralMethodName;\r\n" + 
				"	\r\n" + 
				"	\r\n" + 
				"\r\n" + 
				"	/**\r\n" + 
				"	 * Constructor for objects of Method class\r\n" + 
				"	 * \r\n" + 
				"	 * @param String, a String of the full methodBody i.e. all the text in the method\r\n" + 
				"	 */\r\n" + 
				"\r\n" + 
				"	private Method(String methodBody) {\r\n" + 
				"		// call setter methods\r\n" + 
				"\r\n" + 
				"		setMethodBody(methodBody);\r\n" + 
				"		setMethodTitle();\r\n" + 
				"		setMethodLOC();\r\n" + 
				"		setNoParameters();\r\n" + 
				"		setGlobalMethod();\r\n" + 
				"		setLongMethod();\r\n" + 
				"		setGetterSetter();\r\n" + 
				"		setProceduralMethodName();\r\n" + 
				"\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	\r\n" + 
				"	/**\r\n" + 
				"	* A static method that returns an instance of Method according to the parameter provided\r\n" + 
				"	* \r\n" + 
				"	* @param methodBody,takes a String that is the full body of text of the method\r\n" + 
				"	* \r\n" + 
				"	* @return a Method object, created from given parameter representing the full body of text of the method\r\n" + 
				"	* \r\n" + 
				"	*/		\r\n" + 
				"	public static Method getMethodInstance(String methodBody) {\r\n" + 
				"		// checks\r\n" + 
				"\r\n" + 
				"		Method M = new Method(methodBody);\r\n" + 
				"\r\n" + 
				"		return M;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	/**\r\n" + 
				"	 * Private method to set the methodBody field\r\n" + 
				"	 * \r\n" + 
				"	 * @param methodBody, a String representing the full text of the method\r\n" + 
				"	 */	\r\n" + 
				"	private void setMethodBody(String methodBody) {\r\n" + 
				"		this.methodBody = methodBody;\r\n" + 
				"	}\r\n" + 
				"";
		
		
		Class testClassProcedural = Class.getClassInstance(classBodyProcedural);
		
		assertEquals(testClassProcedural.getProceduralName(),true);
		
	}
	
	@Test
	void testGetNoPolyInheritance1() throws Exception {
		Class testClass = Class.getClassInstance(classBody);
		
		assertEquals(testClass.getNoPolyInheritance(),true);
	}
	
	@Test
	void testGetNoPolyInheritance2() throws Exception {
		String classBodyPolyInheritance = "public class ManagementSystem implements Method {\r\n" + 
				"\r\n" + 
				"	public static int longMethodMetric = 10;\r\n" + 
				"	\r\n" + 
				"	private String methodBody; // probably not needed should be a parameter to\r\n" + 
				"								// the constructor\r\n" + 
				"	private String methodTitle; // or methodName\r\n" + 
				"	private int methodLOC;\r\n" + 
				"	private boolean noParameters;\r\n" + 
				"	private boolean globalMethod;\r\n" + 
				"\r\n" + 
				"	private boolean longMethod; // maybe should be calculated in higher class\r\n" + 
				"	private boolean getterSetter;\r\n" + 
				"	private boolean proceduralMethodName;\r\n" + 
				"	\r\n" + 
				"	\r\n" + 
				"\r\n" + 
				"	/**\r\n" + 
				"	 * Constructor for objects of Method class\r\n" + 
				"	 * \r\n" + 
				"	 * @param String, a String of the full methodBody i.e. all the text in the method\r\n" + 
				"	 */\r\n" + 
				"\r\n" + 
				"	private Method(String methodBody) {\r\n" + 
				"		// call setter methods\r\n" + 
				"\r\n" + 
				"		setMethodBody(methodBody);\r\n" + 
				"		setMethodTitle();\r\n" + 
				"		setMethodLOC();\r\n" + 
				"		setNoParameters();\r\n" + 
				"		setGlobalMethod();\r\n" + 
				"		setLongMethod();\r\n" + 
				"		setGetterSetter();\r\n" + 
				"		setProceduralMethodName();\r\n" + 
				"\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	\r\n" + 
				"	/**\r\n" + 
				"	* A static method that returns an instance of Method according to the parameter provided\r\n" + 
				"	* \r\n" + 
				"	* @param methodBody,takes a String that is the full body of text of the method\r\n" + 
				"	* \r\n" + 
				"	* @return a Method object, created from given parameter representing the full body of text of the method\r\n" + 
				"	* \r\n" + 
				"	*/		\r\n" + 
				"	public static Method getMethodInstance(String methodBody) {\r\n" + 
				"		// checks\r\n" + 
				"\r\n" + 
				"		Method M = new Method(methodBody);\r\n" + 
				"\r\n" + 
				"		return M;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	/**\r\n" + 
				"	 * Private method to set the methodBody field\r\n" + 
				"	 * \r\n" + 
				"	 * @param methodBody, a String representing the full text of the method\r\n" + 
				"	 */	\r\n" + 
				"	private void setMethodBody(String methodBody) {\r\n" + 
				"		this.methodBody = methodBody;\r\n" + 
				"	}\r\n" + 
				"";
		
		Class testClassPolyInheritance = Class.getClassInstance(classBodyPolyInheritance);
		
		assertEquals(testClassPolyInheritance.getNoPolyInheritance(),false);
	}


	@Test
	void testGetDataClass1() throws Exception {
		Class testClass = Class.getClassInstance(classBody);
		
		assertEquals(testClass.getDataClass(),false);
	}

	@Test
	void testGetDataClass2() throws Exception {
		String classBodyData = "package CodeSmellsClasses;\r\n" + 
				"\r\n" + 
				"import java.util.regex.Matcher;\r\n" + 
				"import java.util.regex.Pattern;\r\n" + 
				"\r\n" + 
				"/**\r\n" + 
				" * Class Field,stores all information on a single Field of a java class.\r\n" + 
				" *\r\n" + 
				" * @author Andrew Megson\r\n" + 
				" * @version 10/07/19\r\n" + 
				" */\r\n" + 
				"public class Field {\r\n" + 
				"\r\n" + 
				"	private String fieldBody; // probably not needed should be a parameter the\r\n" + 
				"								// constructor\r\n" + 
				"	private String fieldName; // TODO fieldName variable?? //split by \" \" select last\r\n" + 
				"								// member of array then trim\r\n" + 
				"	private boolean isPrivate;\r\n" + 
				"	private boolean isGlobal;\r\n" + 
				"\r\n" + 
				"	/**\r\n" + 
				"	 * Constructor for objects of Field class\r\n" + 
				"	 * \r\n" + 
				"	 * @param String, a String of the full fieldBody i.e. all the text in the field declaration line\r\n" + 
				"	 */\r\n" + 
				"\r\n" + 
				"	private Field(String fieldBody) {\r\n" + 
				"		setFieldBody(fieldBody);\r\n" + 
				"		setFieldName();\r\n" + 
				"		setIsPrivate();\r\n" + 
				"		setIsGlobal();\r\n" + 
				"\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	/**\r\n" + 
				"	* A static method that returns an instance of Field according to the parameter provided\r\n" + 
				"	* \r\n" + 
				"	* @param fieldBody,takes a String that is the fullBody of the field declaration line\r\n" + 
				"	* \r\n" + 
				"	* @return a Field object, created from given parameter representing the full field declaration line\r\n" + 
				"	* \r\n" + 
				"	*/		\r\n" + 
				"	\r\n" + 
				"	public static Field getFieldInstance(String fieldBody) {\r\n" + 
				"		// checks\r\n" + 
				"\r\n" + 
				"		Field F = new Field(fieldBody);\r\n" + 
				"\r\n" + 
				"		return F;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	/**\r\n" + 
				"	 * Private method to set the fieldBody field\r\n" + 
				"	 * \r\n" + 
				"	 * @param fieldBody, a String representing the full field declaration line\r\n" + 
				"	 */\r\n" + 
				"	private void setFieldBody(String fieldBody) {\r\n" + 
				"		this.fieldBody = fieldBody;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	/**\r\n" + 
				"	 * Method to get the fieldBody\r\n" + 
				"	 * \r\n" + 
				"	 * @return a String representing the full field declaration line\r\n" + 
				"	 */\r\n" + 
				"	public String getFieldBody() {\r\n" + 
				"		return fieldBody;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	\r\n" + 
				"	/**\r\n" + 
				"	 * Private method to set the fieldName field, method selects field name \r\n" + 
				"	 * from fieldBody and assigns it to fieldName field\r\n" + 
				"	 */\r\n" + 
				"	private void setFieldName()\r\n" + 
				"	{\r\n" + 
				"\r\n" + 
				"		\r\n" + 
				"		String[] fieldArray = fieldBody.split(\"\\\\s+\");\r\n" + 
				"		String fieldName = fieldArray[fieldArray.length - 1];\r\n" + 
				"		\r\n" + 
				"		\r\n" + 
				"		this.fieldName = fieldName.trim();\r\n" + 
				"	}\r\n" + 
				"	\r\n" + 
				"	/**\r\n" + 
				"	 * Method to get the field name\r\n" + 
				"	 * \r\n" + 
				"	 * @return String, representing the name of the field\r\n" + 
				"	 */\r\n" + 
				"	public String getFieldName()\r\n" + 
				"	{\r\n" + 
				"		return fieldName;\r\n" + 
				"	}\r\n" + 
				"	\r\n" + 
				"	\r\n" + 
				"	\r\n" + 
				"\r\n" + 
				"	/**\r\n" + 
				"	 * Method to set the isPrivate field, sets to true if the field has been declared private, false otherwise\r\n" + 
				"	 * \r\n" + 
				"	 */\r\n" + 
				"	private void setIsPrivate() // calculates whether field is private, searches\r\n" + 
				"								// for key word 'private'\r\n" + 
				"	{\r\n" + 
				"		this.isPrivate = fieldBody.contains(\"private\");\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	/**\r\n" + 
				"	 * Method to get a boolean representing if the field is private or not\r\n" + 
				"	 * \r\n" + 
				"	 * @return boolean, returns true if the field has been declared private, false otherwise\r\n" + 
				"	 */\r\n" + 
				"	public boolean getIsPrivate() {\r\n" + 
				"		return isPrivate;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	\r\n" + 
				"	/**\r\n" + 
				"	 * Method to set the isGlobal field, sets to true if the field has been declared static, false otherwise\r\n" + 
				"	 * \r\n" + 
				"	 */\r\n" + 
				"	private void setIsGlobal() // calculates whether field is global, searches\r\n" + 
				"								// for key word 'static'\r\n" + 
				"	{\r\n" + 
				"		this.isGlobal = fieldBody.contains(\"static\");\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	/**\r\n" + 
				"	 * Method to get a boolean representing if the field is global or not\r\n" + 
				"	 * \r\n" + 
				"	 * @return boolean, returns true if the field has been declared static, false otherwise\r\n" + 
				"	 */\r\n" + 
				"	public boolean getIsGlobal() {\r\n" + 
				"		return isGlobal;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"}";
		
		
		Class testClass = Class.getClassInstance(classBodyData);
		
		assertEquals(testClass.getDataClass(), false);
		//System.out.println(testClass.getClassLCOM());
	}	
	
	
	
	@Test
	void testGetClassLCOM() throws Exception {
		Class testClass = Class.getClassInstance(classBody);
		
		assertEquals(testClass.getClassLCOM(),0.8128654970760234);
		//System.out.println(testClass.getClassLCOM());
		
	}

	
	
	
}
