package eu.beersafe.mooc.utils;

import java.security.SecureRandom;
import java.util.Random;

public class SecureRandomString {

	private static char[] VALID_CHARACTERS =
		    "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456879".toCharArray();

	// cs = cryptographically secure
	public static String generate(int numChars) {
	    SecureRandom srand = new SecureRandom();
	    Random rand = new Random();
	    char[] buff = new char[numChars];

	    for (int i = 0; i < numChars; ++i) {
	      // reseed rand every once in a while, including the first time
	      if ((i % 10) == 0) {
	          rand.setSeed(srand.nextLong());
	      }
	      buff[i] = VALID_CHARACTERS[rand.nextInt(VALID_CHARACTERS.length)];
	    }
	    return new String(buff);
	}
	
	public static void main(String[] args) {
		for(int i = 0; i < 500; i++) {
			System.out.println(generate(12));;
		}
	}
	
}
