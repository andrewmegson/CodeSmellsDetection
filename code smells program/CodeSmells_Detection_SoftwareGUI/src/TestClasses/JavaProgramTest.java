
package TestClasses;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import CodeSmellsClasses.JavaProgram;

class JavaProgramTest {

	String folderLocation = "C:\\Users\\Andrew\\Desktop\\CodeSmell_TestPrograms\\CW_Java Programs\\Coursework part 1";

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testGetJavaProgramInstance() throws Exception {
		JavaProgram javaProgram = JavaProgram.getJavaProgramInstance(folderLocation);
		
		assertNotNull(javaProgram);

	}


	
	@Test
	void testGetClassesWithSpaghettiCodeSmell() throws Exception {
		JavaProgram javaProgram = JavaProgram.getJavaProgramInstance(folderLocation);
		assertEquals(javaProgram.getClassesWithSpaghettiCodeSmell().size(), 8);
	}



	@Test
	void testGetClassesWithGodClassCodeSmell() throws Exception {
		JavaProgram javaProgram = JavaProgram.getJavaProgramInstance(folderLocation);
		assertEquals(javaProgram.getClassesWithGodClassCodeSmell().size(), 3);
	}

	@Test
	void testGetAllClasses() throws Exception {
		JavaProgram javaProgram = JavaProgram.getJavaProgramInstance(folderLocation);
		assertEquals(javaProgram.getAllClasses().size(),8);
	}

	
	
	
	
	
	
	
}
