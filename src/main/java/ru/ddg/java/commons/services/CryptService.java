package ru.ddg.java.commons.services;

/**
 * Simple text crypt service.
 */
public interface CryptService {
    /**
     * Encrypt end encode data to base64
     * @param plain data to encrypt
     * @return base64 encoded string
     */
    public String encrypt(String plain);

    /**
     * Decode base 64 data and decode it.
     * @param base64data base 64 encoded encrypted data.
     * @return decrypted text.
     * @throws DecryptException
     */
    public String decrypt(String base64data) throws DecryptException;

    public static class DecryptException extends java.lang.Exception {
        public DecryptException() {
        }

        public DecryptException(String message) {
            super(message);
        }

        public DecryptException(String message, Throwable cause) {
            super(message, cause);
        }

        public DecryptException(Throwable cause) {
            super(cause);
        }

        public DecryptException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }
    }

    public static class EncryptException extends RuntimeException {
        public EncryptException() {
        }

        public EncryptException(String message) {
            super(message);
        }

        public EncryptException(String message, Throwable cause) {
            super(message, cause);
        }

        public EncryptException(Throwable cause) {
            super(cause);
        }

        public EncryptException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }
    }
}
