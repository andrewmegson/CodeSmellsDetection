package TestClasses;

import static org.junit.jupiter.api.Assertions.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import CodeSmellsClasses.Field;
import CodeSmellsClasses.Method;

class MethodTest {

	@BeforeAll
	 static void setUpBeforeClass() throws Exception {		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testGetMethodInstance() 
	{

		//TODO need to check the exceptions when they are added!
	String methodBody = "	private String getStartOfMethod() {\r\n" + 
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
			"	}";
		
	Method method1 = Method.getMethodInstance(methodBody);
	
	assertNotNull(method1);
	
	
	}	
	
	
	@Test
	void testGetMethodBody() {
		String methodBody = "	private String getStartOfMethod() {\r\n" + 
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
				"	}";
			
		Method method1 = Method.getMethodInstance(methodBody);
		
		
		assertEquals(method1.getMethodBody(), methodBody);
	}

	@Test
	void testGetMethodTitle() {
		String methodBody = "	private String getStartOfMethod() {\r\n" + 
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
				"	}";
			
		Method method1 = Method.getMethodInstance(methodBody);
		
		assertEquals(method1.getMethodTitle(), "getStartOfMethod");
		
		
	}

	@Test
	void testGetMethodLOC() {
		String methodBody = "	private String getStartOfMethod() {\r\n" + 
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
				"	}";
			
		Method method1 = Method.getMethodInstance(methodBody);
		
		assertEquals(method1.getMethodLOC(), 5);
	}

	@Test
	void testGetNoParameters1() {
		String methodBody = "	private String getStartOfMethod() {\r\n" + 
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
				"	}";
			
		Method method1 = Method.getMethodInstance(methodBody);
		
		assertEquals(method1.getNoParameters(),true);
		
	}

	@Test
	void testGetNoParameters2() {
		String methodBody = "	private String getStartOfMethod(String test) {\r\n" + 
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
				"	}";
			
		Method method1 = Method.getMethodInstance(methodBody);
		
		assertEquals(method1.getNoParameters(), false);
	}
	
	@Test
	void testGetGlobalMethod1() {
		String methodBody = "	public static String getStartOfMethod() {\r\n" + 
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
				"	}";
			
		Method method1 = Method.getMethodInstance(methodBody);
		
		assertEquals(method1.getGlobalMethod(), true);
	}
	
	@Test
	void testGetGlobalMethod2() {
		String methodBody = "	private String getStartOfMethod() {\r\n" + 
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
				"	}";
			
		Method method1 = Method.getMethodInstance(methodBody);
		
		assertEquals(method1.getGlobalMethod(), false);
	}

	@Test
	void testGetLongMethod1() {
		String methodBody = "	private String getStartOfMethod() {\r\n" + 
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
				"	}";
			
		Method method1 = Method.getMethodInstance(methodBody);
		
		assertEquals(method1.getLongMethod(),false);
	}
	
	@Test
	void testGetLongMethod2() {
		String methodBody = "	private String getStartOfMethod() {\r\n" + 
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
				"	\"		String startOfMethod = \\\"\\\";\\r\\n\" + \r\n" + 
				"				\"		// gets full start of method up to {\\r\\n\" + \r\n" + 
				"				\"		Pattern pattern = Pattern.compile(\\\"^.+?[{]\\\");\\r\\n\" + \r\n" + 
				"				\"		Matcher matcher = pattern.matcher(methodBody);\\r\\n\" + \r\n" + 
				"				\"		\\r\\n\" + \r\n" + 
				"				\"		if (matcher.find())\\r\\n\" + \r\n" + 
				"				\"		{\\r\\n\" + \r\n" + 
				"				\"		startOfMethod = matcher.group().trim();\\r\\n\" + \r\n" + 
				"				\"		}\\r\\n\" + \r\n" + 
				"				\"\\r\\n\" + \r\n" + 
				"				\"		return startOfMethod;\\r\\n\"}";
			
		Method method1 = Method.getMethodInstance(methodBody);
		
		assertEquals(method1.getLongMethod(), true);
	}

	@Test
	void testGetGetterSetter1() {
		String methodBody = "	private String getStartOfMethod() {\r\n" + 
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
				"	}";
			
		Method method1 = Method.getMethodInstance(methodBody);
		
		assertEquals(method1.getGetterSetter(), true);
	}

	@Test
	void testGetGetterSetter2() {
		String methodBody = "	private String StartOfMethod() {\r\n" + 
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
				"	}";
			
		Method method1 = Method.getMethodInstance(methodBody);
		
		assertEquals(method1.getGetterSetter(), false);
	}
	@Test
	void testGetproceduralName1() {
		String methodBody = "	private String getStartOfMethod() {\r\n" + 
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
				"	}";
			
		Method method1 = Method.getMethodInstance(methodBody);
		
		assertEquals(method1.getProceduralName(), false);
	}
	
	@Test
	void testGetproceduralName2() {
		String methodBody = "	private String StartOfMethodProcess() {\r\n" + 
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
				"	}";
			
		Method method1 = Method.getMethodInstance(methodBody);
		assertEquals(method1.getProceduralName(), true);
	}

}
