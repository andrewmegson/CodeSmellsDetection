package CodeSmellsClasses;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class Field,stores all information on a single Field of a java class.
 *
 * @author Andrew Megson
 * @version 10/07/19
 */
public class Field {

	private String fieldBody; 
	private String fieldName;
	private boolean isPrivate;
	private boolean isGlobal;

	/**
	 * Constructor for objects of Field class
	 * 
	 * @param String, a String of the full fieldBody i.e. all the text in the field declaration line
	 */

	private Field(String fieldBody) {
		setFieldBody(fieldBody);
		setFieldName();
		setIsPrivate();
		setIsGlobal();

	}

	/**
	* A static method that returns an instance of Field according to the parameter provided
	* 
	* @param fieldBody,takes a String that is the fullBody of the field declaration line
	* 
	* @return a Field object, created from given parameter representing the full field declaration line
	* 
	*/		
	
	public static Field getFieldInstance(String fieldBody) throws Exception
	{
		
		if(fieldBody == null)   //parameter checks
		{
			throw new IllegalArgumentException("null fieldBody parameter passed to Field getFieldInstance method");
		}		
		Field F = new Field(fieldBody);

		return F;
	}

	/**
	 * Private method to set the fieldBody field
	 * 
	 * @param fieldBody, a String representing the full field declaration line
	 */
	private void setFieldBody(String fieldBody) {
		this.fieldBody = fieldBody;
	}

	/**
	 * Method to get the fieldBody
	 * 
	 * @return a String representing the full field declaration line
	 */
	public String getFieldBody() {
		return fieldBody;
	}

	
	/**
	 * Private method to set the fieldName field, method selects field name 
	 * from fieldBody and assigns it to fieldName field
	 */
	private void setFieldName()
	{

		
		String[] fieldArray = fieldBody.split("\\s+");
		String fieldName = fieldArray[fieldArray.length - 1];
		
		fieldName = fieldName.substring(0, fieldName.length() -1);
		
		this.fieldName = fieldName.trim();
	}
	
	/**
	 * Method to get the field name
	 * 
	 * @return String, representing the name of the field
	 */
	public String getFieldName()
	{
		return fieldName;
	}
	
	
	

	/**
	 * Method to set the isPrivate field, sets to true if the field has been declared private, false otherwise
	 * 
	 */
	private void setIsPrivate() // calculates whether field is private, searches
								// for key word 'private'
	{
		this.isPrivate = fieldBody.contains("private");
	}

	/**
	 * Method to get a boolean representing if the field is private or not
	 * 
	 * @return boolean, returns true if the field has been declared private, false otherwise
	 */
	public boolean getIsPrivate() {
		return isPrivate;
	}

	
	/**
	 * Method to set the isGlobal field, sets to true if the field has been declared static, false otherwise
	 * 
	 */
	private void setIsGlobal() // calculates whether field is global, searches
								// for key word 'static'
	{
		this.isGlobal = fieldBody.contains("static");
	}

	/**
	 * Method to get a boolean representing if the field is global or not
	 * 
	 * @return boolean, returns true if the field has been declared static, false otherwise
	 */
	public boolean getIsGlobal() {
		return isGlobal;
	}

}
