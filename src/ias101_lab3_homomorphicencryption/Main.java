package ias101_lab3_homomorphicencryption;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        Paillier paillier = new Paillier();

        BigInteger m1 = new BigInteger("15");
        BigInteger m2 = new BigInteger("25");

        System.out.println("Original m1: " + m1);
        System.out.println("Original m2: " + m2);

        // Encrypt m1 and m2
        BigInteger c1 = paillier.encrypt(m1);
        BigInteger c2 = paillier.encrypt(m2);

        System.out.println("Encrypted m1: " + c1);
        System.out.println("Encrypted m2: " + c2);

        // Homomorphic Addition: E(m1) * E(m2) mod n² = E(m1 + m2)
        BigInteger cSum = c1.multiply(c2).mod(paillier.getNsquare());

        // Decrypt the sum
        BigInteger decryptedSum = paillier.decrypt(cSum);

        System.out.println("Decrypted (m1 + m2): " + decryptedSum);
    }
}