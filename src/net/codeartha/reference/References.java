package net.codeartha.reference;

public class References {
    public static final String VERSION = "1.7.10-1.0";
    
    public static final String HELP = 
    		"Syntax: \n" + 
    		"\tjava Playfair [action] \"passphrase\" \"message\" \n" +
    		"Or \n" +
    		"\tjava Playfair [action] --key \"passphrase\" --freetype \n" +
            "\n" +
            "Actions: \n" +
            "--encrypt or -e \n" +
            "--decrypt or -d \n" +
            "--help (ignores further arguments) \n" +
            "--version \n" +
            "\n" +
            "Key: \n" +
            "--key/-k sets the key \n" +
            "\n" +
            "Modifier: \n" +
            "--freetype (user may type multiline and end with \\eof) \n" ;

}
