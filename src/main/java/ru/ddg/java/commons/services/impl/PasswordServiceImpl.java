package ru.ddg.java.commons.services.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ddg.java.commons.services.PasswordService;
import ru.ddg.java.commons.services.RandomService;

import java.math.BigInteger;

@Component
public class PasswordServiceImpl implements PasswordService {
    @Autowired
    private RandomService randomService;

    /**
     * Генерация псевдослучайного пароля на основе SecureRandom. Цифро-буквенный набор (22 символа + 10 цифр)
     * @param length length in symbols.
     * @return result password.
     */
    @Override
    public String generatePassword(int length) {
        BigInteger integer = randomService.randomBits(5 * length);
        // бывает случай, когда первые биты образуют целочисленный 0, и в таком случае он теряется
        String password = integer.toString(32);
        // поэтому дополняем нулями
        String paddedPassword = leftPadWithZero(password, length);
        return paddedPassword;
    }

    @Override
    public String generateCode(int length) {
        BigInteger integer = randomService.randomBits(4 * length);
        //noinspection ConstantConditions
        String code = integer.toString(10);
        // int might be 0, or too big, so padding before and cut
        String paddedCode = leftPadWithZero(code, length);
        return paddedCode;
    }

    private String leftPadWithZero(String source, int requiredLength) {
        String padded = StringUtils.leftPad(source, requiredLength, '0').substring(0, requiredLength);
        return padded;
    }
}
