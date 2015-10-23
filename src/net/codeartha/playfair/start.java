package net.codeartha.playfair;

public class start {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Playfair pf = new Playfair();
		pf.setKey("senga");
		pf.generateKeyGrid();
		pf.printKeyGrid();
		
		System.out.println(pf.getLetterCoords("n")); //prints [x=0,y=2]  which is wrong, have to swap
		//pf.setMsgIn("unpetittexteachiffrer");
		//pf.encryptMsg();
		//System.out.println(pf.getMsgOut());
	}

}
