package eu.beersafe.mooc.utils;

public class StandaloneBcrypt {

	public static void main(String[] args) {
		String password = "mysecretpassword";
		int roundsExponent = 12;

		String hash = hash(password, roundsExponent);
		check(password, hash);
		check("notthepassword", hash);
	}
	
	public static String hash(String pass, int rounds) {
		long start = System.currentTimeMillis();
		String hash = BCrypt.hashpw(pass,  BCrypt.gensalt(rounds));
		long stop = System.currentTimeMillis();
		System.out.println("Generated BCRYPT hash in " + (stop - start) + "ms (password: " + pass + ", hash: " + hash + ", cost factor: " + rounds + ")");
		return hash;
	}
	
	public static void check(String pass, String hash) {
		long start = System.currentTimeMillis();
		boolean result = BCrypt.checkpw(pass,  hash);
		long stop = System.currentTimeMillis();
		if(result) {
			System.out.println("Verified valid BCRYPT hash in " + (stop - start) + "ms (password: " + pass + ", hash: " + hash + ")");
		}
		else {
			System.out.println("Verified invalid BCRYPT hash in " + (stop - start) + "ms (password: " + pass + ", hash: " + hash + ")");
		}
	}
}