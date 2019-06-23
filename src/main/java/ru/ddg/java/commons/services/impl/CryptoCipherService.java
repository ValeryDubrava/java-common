package ru.ddg.java.commons.services.impl;

import ru.ddg.java.commons.services.CryptService;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Base64;

public class CryptoCipherService implements CryptService {
    private final static Charset UTF8_CHARSET = Charset.forName("UTF-8");
    private final static String ALGORITHM = "AES";
    private final static String HASH_ALGORITHM = "MD5";
    private final static String CIPHER_TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private final Key key;
    private final AlgorithmParameterSpec parameter;

    private Base64.Encoder base64Encoder = Base64.getUrlEncoder().withoutPadding();
    private Base64.Decoder base64Decoder = Base64.getUrlDecoder();

    public CryptoCipherService(String password, String iv) throws Exception {
        this(password, iv, true);
    }

    public CryptoCipherService(String password, String iv, boolean selfCheck) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance(HASH_ALGORITHM);
        byte[] passwordBytes = messageDigest.digest(password.getBytes(UTF8_CHARSET));
        key = new SecretKeySpec(passwordBytes, ALGORITHM);
        byte[] ivBytes = messageDigest.digest(iv.getBytes(UTF8_CHARSET));
        parameter = new IvParameterSpec(ivBytes);

        if (selfCheck) {
            Cipher encryptCipher = Cipher.getInstance(CIPHER_TRANSFORMATION);
            encryptCipher.init(Cipher.ENCRYPT_MODE, key, parameter);

            Cipher decryptCipher = Cipher.getInstance(CIPHER_TRANSFORMATION);
            decryptCipher.init(Cipher.DECRYPT_MODE, key, parameter);
            String source = "simple check";
            byte[] input = source.getBytes(UTF8_CHARSET);
            byte[] encrypted = encryptCipher.doFinal(input);
            byte[] decrypted = decryptCipher.doFinal(encrypted);
            String toCheck = new String(decrypted, UTF8_CHARSET);
            if (!source.equals(toCheck)) {
                throw new DecryptException("Wrong encryption->decryption behavior.");
            }
        }
    }

    @Override
    public String encrypt(String plain) {
        try {
            Cipher encryptCipher = Cipher.getInstance(CIPHER_TRANSFORMATION);
            encryptCipher.init(Cipher.ENCRYPT_MODE, key, parameter);

            byte[] input = plain.getBytes(UTF8_CHARSET);
            byte[] output = encryptCipher.doFinal(input);
            return base64Encoder.encodeToString(output);
        }
        catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException | InvalidAlgorithmParameterException e) {
            throw new EncryptException("Encryption failed.", e);
        }
    }

    @Override
    public String decrypt(String base64data) throws DecryptException {
        byte[] input;
        try {
            input = base64Decoder.decode(base64data);
        }
        catch (Exception ex) {
            throw new DecryptException("Decode base64 failed.", ex);
        }
        try {
            Cipher decryptCipher = Cipher.getInstance(CIPHER_TRANSFORMATION);
            decryptCipher.init(Cipher.DECRYPT_MODE, key, parameter);

            byte[] output = decryptCipher.doFinal(input);

            return new String(output, UTF8_CHARSET);
        }
        catch (IllegalBlockSizeException | InvalidKeyException | BadPaddingException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException e) {
            throw new DecryptException("Decryption failed.", e);
        }
    }
}
