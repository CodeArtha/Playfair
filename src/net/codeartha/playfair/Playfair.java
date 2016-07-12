package net.codeartha.playfair;

import java.awt.Point;

import net.codeartha.playfair.PairPointReturn;
import net.codeartha.utils.StringHelper;

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

	public Playfair(){}
	public Playfair(String password)
	{
		this.setKey(password);
		this.generateKeyGrid(password);
	}
	
	public String getMsgOut() {return this.msgOut;}
	public String getMsgIn(){return this.msgIn;}
	public void clearMsgIn(){this.msgIn = "";}
	public void clearMsgOut(){this.msgOut = "";}
	public void setKey(String k) { this.key = k.toLowerCase(); }
	public void setMsgIn(String m) 
	{
		this.msgIn = m.toLowerCase();
		if(this.msgIn.length() % 2 != 0)	//odd number of characters
		{
			this.msgIn = this.msgIn.concat("x");	//we add a character, we chose X.
		}
	}
	
	public void decryptMsg()
	{
		int i = 0;
		while(i < this.msgIn.length()-1)
		{
			String char1 = msgIn.substring(i, i + 1);
			String char2 = msgIn.substring(i + 1, i + 2);
			
			Point locationChar1 = getLetterCoords(char1);
			Point locationChar2 = getLetterCoords(char2);
			
			PairPointReturn clearLetters = decryptCoords(locationChar1, locationChar2);
			
			Point firstClear = clearLetters.getFirst();
			Point secondClear = clearLetters.getSecond();
			
			this.msgOut = this.msgOut + this.keyGrid[firstClear.y][firstClear.x] + this.keyGrid[secondClear.y][secondClear.x];
			i = i + 2;
		}
	}
		
	public void encryptMsg()
	{
		int i = 0;
		while(i < this.msgIn.length()-1)
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
		this.key = StringHelper.removeDuplicates((this.key + "abcdefghijklmnopqrstuvwxyz").replace('v', 'u'));
		
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
		this.key = StringHelper.removeDuplicates((this.key + "abcdefghijklmnopqrstuvwxyz").replace('v', 'u'));
		
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
	 * Takes two Points (=coords) as parameter, A being the coordinates of the
	 * first letter, B the ones of the second one. This function returns the
	 * coordinates of the encrypted characters, they then have to be passed to the
	 * keyGrid to extract the encrypted character.
	 * 
	 * @param point A
	 * @param point B
	 * @return Object PairPointReturn
	 */
	private static PairPointReturn encryptCoords(Point clearA, Point clearB)
	{

		Point cryptA = new Point(0, 0);
		Point cryptB = new Point(0, 0);

		if (clearA.x == clearB.x && clearA.y == clearB.y)
		{
			return new PairPointReturn(clearA, clearB);
		}
		else if (clearA.x == clearB.x && clearA.y != clearB.y)
		{
			cryptA.x = clearA.x;
			cryptA.y = (clearA.y + 1)%5;
			cryptB.x = clearB.x;
			cryptB.y = (clearB.y + 1)%5;
			return new PairPointReturn(cryptA, cryptB);
		}
		else if (clearA.y == clearB.y && clearA.x != clearB.x)
		{
			cryptA.x = (clearA.x + 1)%5;
			cryptA.y = clearA.y;
			cryptB.x = (clearB.x + 1)%5;
			cryptB.y = clearB.y;
			return new PairPointReturn(cryptA, cryptB);
		}
		else
		{
			cryptA.x = clearB.x;
			cryptA.y = clearA.y;
			cryptB.x = clearA.x;
			cryptB.y = clearB.y;
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

		if (cryptedA.x == cryptedB.x && cryptedA.y == cryptedB.y)
		{
			return new PairPointReturn(cryptedA, cryptedB);
		}
		else if (cryptedA.x == cryptedB.x && cryptedA.y != cryptedB.y)
		{
			clearA.x = cryptedA.x;
			clearA.y = Math.abs((cryptedA.y - 1)%5);
			clearB.x = cryptedB.x;
			clearB.y = Math.abs((cryptedB.y - 1)%5);
			return new PairPointReturn(clearA, clearB);
		}
		else if (cryptedA.y == cryptedB.y && cryptedA.x != cryptedB.x)
		{
			clearA.x = Math.abs((cryptedA.x - 1)%5);
			clearA.y = cryptedA.y;
			clearB.x = Math.abs((cryptedB.x - 1)%5);
			clearB.y = cryptedB.y;
			return new PairPointReturn(clearA, clearB);
		}
		else
		{
			clearA.x = cryptedB.x;
			clearA.y = cryptedA.y;
			clearB.x = cryptedA.x;
			clearB.y = cryptedB.y;
			return new PairPointReturn(clearA, clearB);
		}
	}
}
