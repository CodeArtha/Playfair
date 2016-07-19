import net.codeartha.playfair.Playfair;

public class start {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*Playfair pf = new Playfair();
		pf.setKey("senga");
		pf.generateKeyGrid();
		pf.printKeyGrid();
		
		//System.out.println(pf.getLetterCoords("n")); //prints [x=0,y=2]  which is wrong, have to swap
		pf.setMsgIn("loremipsumdolorsitametvenivedivici");
		System.out.println(pf.getMsgIn());
		pf.encryptMsg();
		pf.setMsgIn(pf.getMsgOut());
		System.out.println(pf.getMsgIn());
		pf.decryptMsg();
		System.out.println(pf.getMsgOut());*/
		
		//New test: key = perfasetnefas; msg = ma cherie je suis dingue de toi
		//encrypting
		Playfair p = new Playfair();
		p.generateKeyGrid("motdepasse");
		p.printKeyGrid();
		p.setMsgIn("macheriejesuisdinguedetoi");
		System.out.println("Clear message: macheriejesuisdinguedetoi");
		p.encryptMsg();
		System.out.println("Encrypted correctly: " + p.getMsgOut()); //output=opsjczjdrcpxhbbqlhzmemdthy
		p.clearMsgOut();
		p.setMsgIn("opsjczjdrcpxhbbqlhzmemdthy");
		p.decryptMsg();
		System.out.println("Décrypted correctly: " + p.getMsgOut()); //output=macheriejesuisdinguedotoix
		
		
		
		
	}

}
