package eu.beersafe.mooc.utils;

import org.apache.commons.codec.digest.DigestUtils;

import eu.beersafe.mooc.data.objects.User;

public class AuthenticationUtils {

	public static boolean verifyAndClearPassword(User user, String password) {
		boolean result = user.getPassword().equals(calculatePasswordHash(password));
		user.setPassword("");
		return result;
	}
	
	private static String calculatePasswordHash(String password) {
		return DigestUtils.sha1Hex(password);
	}
}