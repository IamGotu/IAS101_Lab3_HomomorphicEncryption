package ias101_lab3_homomorphicencryption;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Paillier {
    private BigInteger p, q, n, nsquare, g, lambda;
    private int bitLength = 512;
    private SecureRandom rnd;

    public Paillier() {
        rnd = new SecureRandom();
        keyGeneration();
    }

    private void keyGeneration() {
        p = new BigInteger(bitLength / 2, 64, rnd);
        q = new BigInteger(bitLength / 2, 64, rnd);
        n = p.multiply(q);
        nsquare = n.multiply(n);
        g = n.add(BigInteger.ONE); // g = n + 1
        lambda = lcm(p.subtract(BigInteger.ONE), q.subtract(BigInteger.ONE));
    }

    private BigInteger lcm(BigInteger a, BigInteger b) {
        return a.multiply(b).divide(a.gcd(b));
    }

    private BigInteger L(BigInteger u) {
        return u.subtract(BigInteger.ONE).divide(n);
    }

    public BigInteger encrypt(BigInteger m) {
        BigInteger r;
        do {
            r = new BigInteger(bitLength, rnd);
        } while (r.compareTo(n) >= 0 || r.gcd(n).intValue() != 1);
        return g.modPow(m, nsquare).multiply(r.modPow(n, nsquare)).mod(nsquare);
    }

    public BigInteger decrypt(BigInteger c) {
        BigInteger u = g.modPow(lambda, nsquare).subtract(BigInteger.ONE).divide(n).modInverse(n);
        return L(c.modPow(lambda, nsquare)).multiply(u).mod(n);
    }

    public BigInteger getN() {
        return n;
    }

    public BigInteger getNsquare() {
        return nsquare;
    }

    public BigInteger getG() {
        return g;
    }

    public int getBitLength() {
        return bitLength;
    }
}