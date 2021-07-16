package model;

import model.enumeration.CoinFace;
import model.interfaces.Coin;
import java.util.Random;

/*
 * Class:           CoinImpl
 * Description:     implementation of the Coin interface which represents a coin of the game 
 * Author:          Hamed Alkaff - s3708483
 */

public class CoinImpl implements Coin{



	private int number;
	private CoinFace face;


	//the constructor of the class which also generates a randomized coin face. 

	public CoinImpl(int number) {
		this.number = number;
		Random rand = new Random();

		int n = rand.nextInt(2);
		if (n==0) this.face = CoinFace.HEADS;
		else this.face = CoinFace.TAILS; 
	}


	@Override
	public int getNumber() {
		return number;
	}

	@Override
	public CoinFace getFace() {
		return face;	
	}


	@Override
	public void flip() {

		if (getFace().equals(CoinFace.HEADS)) face = CoinFace.TAILS;
		else face = CoinFace.HEADS;
	}

	@Override
	public boolean equals(Coin coin) {
		return face == coin.getFace() ;
	}

	@Override
	public boolean equals(Object coin) {
		return this.equals( (Coin)coin);
	}

	/*
	 * returns a hashcode in the format: INFO: Coin 1: Tails  3
	 * where 3 is the result of multiplying the coin number with 2 and adds 0 if the coin face was Heads and 1 for Tails.
	 */
	@Override
	public int hashCode() {
		return this.number * 2 + (face == CoinFace.HEADS ? 0 : 1 );
	}

	/*
	 * a method to capitalize the first letter of a string and the rest in lower case. 
	 * i.e. HEADS --> Heads
	 */
	private String MixedCase(String s) {
		return s.charAt(0) + s.substring(1, s.length() ).toLowerCase();
	}


	@Override
	public String toString() {
		return "Coin " + this.number + ": " + MixedCase(this.face.toString()) ;
	}

}
