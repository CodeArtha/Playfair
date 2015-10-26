package net.codeartha.utils;

public class StringHelper{

	/**
	 * This method removes any duplicated characters in a string
	 * 
	 * @param String
	 * @return String
	 */
	public static String removeDuplicates(String s)
	{
		StringBuilder noDupes = new StringBuilder();
		for (int i = 0; i < s.length(); i++)
		{
			String si = Character.toString(s.charAt(i));
			if (noDupes.indexOf(si) == -1)
			{
				noDupes.append(si);
			}
		}
		return noDupes.toString();
	}
	
	/**
	 * This method removes any spaces in a string
	 * 
	 * @param String
	 * @return String
	 */
	public static String removeSpaces(String s)
	{
		StringBuilder noSpace = new StringBuilder();
		for (int i = 0; i < s.length(); i++)
		{
			String si = Character.toString(s.charAt(i));
			if(si != " ")
			{
				noSpace.append(si);
			}
		}
		return noSpace.toString();
	}
	
	/**
	 * This method replaces accents by their non-accented character (French support only)
	 * 
	 * @param String
	 * @return String
	 */
	public static void replaceAccents(String s)
	{
		s.replaceAll(regex,"e");
	}
}







