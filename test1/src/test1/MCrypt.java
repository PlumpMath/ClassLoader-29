package test1;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class MCrypt {

	private static int key_size = 128;

	public static byte[] getencrypt(String key, byte[] plain) {
		byte[] encrypted = null;
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(key_size, new SecureRandom(key.getBytes()));
			SecretKey skey = kgen.generateKey();
			byte[] raw = skey.getEncoded();

			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

			encrypted = cipher.doFinal(plain);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return encrypted;
	}

	public static byte[] getdecrypt(String k2, byte[] encrypt) {
		byte[] decrypted = null;

		try {
			KeyGenerator kgen2 = KeyGenerator.getInstance("AES");
			kgen2.init(key_size, new SecureRandom(k2.getBytes()));
			SecretKey skey2 = kgen2.generateKey();
			byte[] raw2 = skey2.getEncoded();
			SecretKeySpec skeySpec2 = new SecretKeySpec(raw2, "AES");

			Cipher cipher2 = Cipher.getInstance("AES");
			cipher2.init(Cipher.DECRYPT_MODE, skeySpec2);
			decrypted = cipher2.doFinal(encrypt);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return decrypted;
	}

}
