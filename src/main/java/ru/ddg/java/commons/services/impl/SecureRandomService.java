package ru.ddg.java.commons.services.impl;

import org.springframework.stereotype.Component;
import ru.ddg.java.commons.services.RandomService;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Random service implementation based on SecureRandom.
 */
@Component
public class SecureRandomService implements RandomService {
    private SecureRandom secureRandom = new SecureRandom();

    @Override
    public BigInteger randomBits(int numBits) {
        return new BigInteger(numBits, secureRandom);
    }

    @Override
    public long rand() {
        return secureRandom.nextLong();
    }

    @Override
    public int randInt() {
        return secureRandom.nextInt();
    }
}
