package paymentsystem.encryption;

import java.util.Base64;

public class EncryptorDecryptorUtils {
    private EncryptorDecryptorUtils() {

    }

    public static String encrypt(String plainText) {
        return Base64.getEncoder().encodeToString(plainText.getBytes());
    }

    public static String decrypt(String cipherText) {
        return new String(Base64.getDecoder().decode(cipherText));
    }
}
