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
		p.generateKeyGrid("perfasetnefas");
		p.printKeyGrid();
		p.setMsgIn("macheriejesuisdinguedetoi");
		p.encryptMsg();
		System.out.println("Encrypted correctly: " + p.getMsgOut()); //output=qrnjrfgfgadsdbgdthwpgpblhy
		p.clearMsgOut();
		//decrypting the output the wrong way:
		p.setMsgIn("qrnjrfgfgadsdbgdthwpgpblhy");
		p.encryptMsg();
		System.out.println("Decrypted wrong way: " + p.getMsgOut()); //output=machfpiejekdishgnguedetoix
		p.clearMsgOut();
		//decrypting the output the wrong way:
		p.setMsgIn("qrnjrfgfgadsdbgdthwpgpblhy");
		p.decryptMsg();
		System.out.println("Decrypted right way: " + p.getMsgOut()); //output=macheriejespisdgnguedetoix
		p.clearMsgOut();
		
		
		
		
	}

}
