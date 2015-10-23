package net.codeartha.playfair;

import java.awt.Point;

import net.codeartha.playfair.PairPointReturn;

/**
 * @author codeartha
 * 
 * note: I chose not to encrypt binoms that contains double letter for easier translation to binary file later on. 
 *
 */
public class Playfair {

	private String[][] keyGrid = new String [5][5];  // String[3][5] create array of 3 rows 5 columns => keyGrid[y][x]
	private String key = "";
	private String msgIn = "";
	private String msgOut = "";

	public Playfair()
	{
		
	}
	/*
	 * TODO 
	 * method for finding a letter's coords -DONE
	 * method for reading the text binom by binom
	 */
	
	public String getMsgOut() {
		return msgOut;
	}
	
	public void encryptMsg()
	{
		int i = 0;
		while(i < this.msgIn.length()-2)
		{
			String char1 = msgIn.substring(i, i + 1);
			String char2 = msgIn.substring(i + 1, i + 2);
			
			Point locationChar1 = getLetterCoords(char1);
			Point locationChar2 = getLetterCoords(char2);
			
			PairPointReturn cryptedLetters = encryptCoords(locationChar1, locationChar2);
			
			Point firstCrypt = cryptedLetters.getFirst();
			Point secondCrypt = cryptedLetters.getSecond();
			
			this.msgOut = this.msgOut + this.keyGrid[firstCrypt.y][firstCrypt.x] + this.keyGrid[secondCrypt.y][secondCrypt.x];
			i = i + 2;
		}
	}
		
	/**
	 * Finds a letter in a bidimensional array and return's it's x and y position
	 * 
	 * @param letter
	 * @return Point
	 */
	public Point getLetterCoords( String letter)//Object[][] array,
	{
		letter = letter.toLowerCase();		// just in case...
		
		if(letter.equals("v"))
		{
			letter = "u";					// v doesn't exist, we always look for u
		}
		
		Object[][] array = this.keyGrid;	// needed for debug
		if (letter == null || array == null)
			return null;

		for (int y = 0; y < array.length; y++)
		{
			Object[] row = array[y];
			if (row != null)
			{
				for (int x = 0; x < row.length; x++)
				{
					if (letter.equals(row[x]))
					{
						return new Point(x, y);    //point (x,y)
					}
				}
			}
		}
		return null; // value not found in array
	}
	
	public void setKey(String k) { this.key = k.toLowerCase(); }
	public void setMsgIn(String m) 
	{
		this.msgIn = m.toLowerCase();
		if(this.msgIn.length() % 2 != 0)	//odd number of characters
		{
			this.msgIn = this.msgIn.concat("x");	//we add a character, we chose X.
		}
	}
	
	/**
	 * 	Supposed to be only for debug purpose, but can be adapted for interactive display
	 * 	Prints the keyGrid directly to console output.
	 */
	public void printKeyGrid()
	{
		for(int y = 0 ; y <= 4 ; y++) //= lines = y
		{
			String temp = "";
			for(int x = 0 ; x <= 4 ; x++)   //= columns = x
			{
				temp = temp + " [" + this.keyGrid[y][x] + "]";
			}
			System.out.println(temp);
		}
	}
	
	/**
	 * Set keyGrid to a new array containing the secret key and all rest of the letters of the alphabet
	 */
	public void generateKeyGrid()
	{
		this.key = removeDuplicates((this.key + "abcdefghijklmnopqrstuvwxyz").replace('v', 'u'));
		
		int k = 0;
		for(int y = 0; y <= 4 ; y++)
		{
			for(int x = 0; x <= 4 ; x++)
			{
				this.keyGrid[y][x] = Character.toString(this.key.charAt(k));
				k++;
			}
		}
		
		
	}
	
	/**
	 * Set keyGrid to a new array containing the secret key and all rest of the letters of the alphabet
	 * @param secret
	 */
	public void generateKeyGrid(String secret)
	{
		this.key = secret.toLowerCase();
		this.key = removeDuplicates((this.key + "abcdefghijklmnopqrstuvwxyz").replace('v', 'u'));
		
		int k = 0;
		for(int y = 0; y <= 4 ; y++)
		{
			for(int x = 0; x <= 4 ; x++)
			{
				this.keyGrid[y][x] = Character.toString(this.key.charAt(k));
				k++;
			}
		}
		
		
	}
	
	/**
	 * This method removes any duplicated characters in a string
	 * 
	 * @param String
	 * @return String
	 */
	private static String removeDuplicates(String s)
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
	 * Takes two Points (=coords) as parameter, A being the coordinates of the
	 * first letter, B the ones of the second one. This function returns the
	 * coordinates of the encrypted characters, they then have to be passed to the
	 * keyGrid to extract the encrypted character.
	 * 
	 * @param point A
	 * @param point B
	 * @return Object PairPointReturn
	 */
	private static PairPointReturn encryptCoords(Point A, Point B)
	{

		Point cryptA = new Point(0, 0);
		Point cryptB = new Point(0, 0);

		if (A.x == B.x && A.y == B.y)
		{
			cryptA.x = A.x;
			cryptA.y = A.y;
			cryptB.x = B.x;
			cryptB.y = B.y;
			return new PairPointReturn(cryptA, cryptB);
		}
		else if (A.x == B.x && A.y != B.y)
		{
			cryptA.x = (A.x + 1)%4;
			cryptA.y = A.y;
			cryptB.x = (B.x + 1)%4;
			cryptB.y = B.y;
			return new PairPointReturn(cryptA, cryptB);
		}
		else if (A.y == B.y && A.x != B.x)
		{
			cryptA.x = A.x;
			cryptA.y = (A.y + 1)%4;
			cryptB.x = B.x;
			cryptB.y = (B.y + 1)%4;
			return new PairPointReturn(cryptA, cryptB);
		}
		else
		{
			cryptA.x = B.x;
			cryptA.y = A.y;
			cryptB.x = A.x;
			cryptB.y = B.y;
			return new PairPointReturn(cryptA, cryptB);
		}
	}
	
	/**
	 * This function takes two already encrypted coordinates and return their
	 * clear form. They will then have to be matched with the keyGrid to return
	 * the two clear characters.
	 * 
	 * @param cryptedA
	 * @param cryptedB
	 * @return Object PairPointReturn
	 */
	private static PairPointReturn decryptCoords(Point cryptedA, Point cryptedB)
	{
		Point clearA = new Point(0, 0);
		Point clearB = new Point(0, 0);

		if (cryptedA.x == cryptedB.x)
		{
			clearA.x = cryptedA.x - 1;
			clearA.y = cryptedA.y;
			clearB.x = cryptedB.x - 1;
			clearB.y = cryptedB.y;
			if (clearA.x < 0)
			{
				clearA.x = 4;
			}
			if (clearB.x < 0)
			{
				clearB.x = 4;
			}
			return new PairPointReturn(clearA, clearB);
		}
		else if (cryptedA.y == cryptedB.y)
		{
			clearA.x = cryptedA.x;
			clearA.y = cryptedA.y - 1;
			clearB.x = cryptedB.x;
			clearB.y = cryptedB.y - 1;
			if (clearA.y < 0)
			{
				clearA.y = 4;
			}
			if (clearB.y < 0)
			{
				clearB.y = 4;
			}
			return new PairPointReturn(clearA, clearB);
		}
		else if (cryptedA.x == cryptedB.x && cryptedA.y == cryptedB.y)
		{
			clearA.x = cryptedA.x;
			clearA.y = cryptedA.y;
			clearB.x = cryptedB.x;
			clearB.y = cryptedB.y;
			return new PairPointReturn(clearA, clearB);
		}
		else
		{
			clearA.x = cryptedA.x;
			clearA.y = cryptedB.y;
			clearB.x = cryptedB.x;
			clearB.y = cryptedA.y;
			return new PairPointReturn(clearA, clearB);
		}
	}
}
