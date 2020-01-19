package TestClasses;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import CodeSmellsClasses.Field;

class FieldTest {

	
//	String privateBody;
//	String globalBody;
//	Field privateField;	
//	Field staticField;
//	
//	public FieldTest()
//	{
//		
//	}
//	
//	@BeforeAll
//	public void setUpBeforeClass() throws Exception {
//		
//	privateBody = "private String PName;";	
//	globalBody = "public static String GName;";
//	privateField = Field.getFieldInstance(privateBody);	
//	staticField	= Field.getFieldInstance(globalBody);
//	}
//
//	@AfterAll
//	public void tearDownAfterClass() throws Exception {
//	}

	@Test
	public void testGetFieldInstance() throws Exception {
		
		String privateBody = "private String PName;";
		Field privateField = Field.getFieldInstance(privateBody);
		
		assertNotNull(privateField);
		
		//TODO need to check the exceptions when they are added!
		
	}

	@Test
	public void testGetFieldBody() throws Exception {
		String privateBody = "private String PName;";
		Field privateField = Field.getFieldInstance(privateBody);
		
		assertEquals(privateField.getFieldBody(), "private String PName;");
		
		
	}

	@Test
	void testGetFieldName() throws Exception {
		String privateBody = "private String PName;";
		Field privateField = Field.getFieldInstance(privateBody);
		
		assertEquals(privateField.getFieldName(), "PName");
	}

	
	
	
	
	@Test
	void testGetIsPrivate1() throws Exception {
		String privateBody = "private String PName;";
		Field privateField = Field.getFieldInstance(privateBody);
		
		assertEquals(privateField.getIsPrivate(), true);
		
	}
	
	@Test
	void testGetIsPrivate2() throws Exception {
		String globalBody = "public static String GName;";
		Field staticField = Field.getFieldInstance(globalBody);
		
		assertEquals(staticField.getIsPrivate(), false);
		
		
	
	}

	@Test
	void testGetIsGlobal1() throws Exception {
		String globalBody = "public static String GName;";
		Field staticField = Field.getFieldInstance(globalBody);
		
		assertEquals(staticField.getIsGlobal(), true);
	}
	
	@Test
	void testGetIsGlobal2() throws Exception {
		String privateBody = "private String PName;";
		Field privateField = Field.getFieldInstance(privateBody);
		
		assertEquals(privateField.getIsGlobal(), false);
	}

}
