import java.io.File;
import net.codeartha.playfair.Playfair;
import net.codeartha.reference.References;

/*
Syntax:
    java Playfair [action] "passphrase" "message"
Or
    java Playfair [action] --key "passphrase" --freetype

Actions:
--encrypt /-e
--decrypt /-d
--help (ignores further arguments)
--version (prints version and legal owner)

Key:
--key/-k sets the key

Modifier:
--freetype (user may type multiline and end with \eof)

 */

public class Start {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if((args != null) && (args.length > 0)){
			switch(args[0])
			{
				case "--help":
				    ShowHelp();
				    break;
				case "--version":
				    ShowVersion();
				    break;
				case "-e":
				    //should go straight to next case without break
				case "--encrypt":
				    //code
					System.out.println("encrypting asked");
				    break;
				case "-d":
				    //should go straight to next case
				case "--decrypt":
				    //code
					System.out.println("decrypting asked");
				    break;
				default:
				    System.out.println("I don't understand what you want me to do!");
				    System.out.println("Check the syntax of your command.");
				    System.out.println("Type \"java PlayFair --help \" to display syntax help");
				    break;
			}
		}
	}
	
	private static void ShowHelp()
	{
		System.out.print(References.HELP);
	}
	
	private static void ShowVersion()
	{
		System.out.println("Current version: "+ References.VERSION);
	}

}
