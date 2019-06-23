package ru.ddg.java.commons.services;

/**
 * Utility service for generating password and other unique codes.
 */
public interface PasswordService {
    /**
     * Generate alphabet password with the specified length.
     *
     * @param length length of the required password.
     * @return unique generated password.
     */
    String generatePassword(int length);

    /**
     * Generate numeric unique code with the specified length.
     *
     * @param length length of the required code.
     * @return unique generated code.
     */
    String generateCode(int length);
}
