package ru.ddg.java.commons.services;

import java.math.BigInteger;

/**
 * Random wrapping service for future purposes.
 */
public interface RandomService {
    BigInteger randomBits(int numBits);
    long rand();

    int randInt();
}
