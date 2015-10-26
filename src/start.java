import net.codeartha.playfair.Playfair;

public class start {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Playfair pf = new Playfair();
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
		System.out.println(pf.getMsgOut());
	}

}
