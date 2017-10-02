package eu.beersafe.mooc.utils;

import java.sql.SQLException;

import org.apache.commons.codec.digest.DigestUtils;

import eu.beersafe.mooc.data.access.UserDAO;
import eu.beersafe.mooc.data.objects.User;
import eu.beersafe.mooc.logger.Logger;

public class AuthenticationUtils {
	
	private static final int COST_FACTOR = 12;

	public static boolean verifyAndClearPassword(User user, String password) {
		if(user.getPassword().startsWith("$2a$")) {
			boolean result = BCrypt.checkpw(password, user.getPassword());
			user.setPassword("");
			return result;
		}
		else {
			boolean result = user.getPassword().equals(calculateSHA1Hash(password));
			if(result) {
				String bcryptHash = calculateBCryptHash(password);
				try {
					(new UserDAO()).updatePassword(user, bcryptHash);
					Logger.info("Authentication with old hash succeeded, upgraded to a BCrypt hash");
				}
				catch(SQLException e) {
					Logger.error("Authentication with old hash succeeded, but upgrade to a BCrypt hash failed", e);
				}
			}
			
			user.setPassword("");
			return result;
		}
	}
	
	private static String calculateSHA1Hash(String password) {
		return DigestUtils.sha1Hex(password);
	}
	
	private static String calculateBCryptHash(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt(COST_FACTOR));
	}
}