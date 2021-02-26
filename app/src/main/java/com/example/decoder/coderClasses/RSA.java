package com.example.decoder.coderClasses;

import java.io.DataInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

public class RSA {
    private BigInteger p;
    private BigInteger q;
    private BigInteger n;
    private BigInteger phi;
    private BigInteger e;
    private BigInteger d;
    private int bitlength = 1024;
    private Random r;

    public RSA() {
        r = new Random();
        // choosing random number
        p = BigInteger.probablePrime(bitlength, r);
        q = BigInteger.probablePrime(bitlength, r);

        //multiplying -> public key
        n = p.multiply(q);

        // phi = (p - 1) * (q - 1)
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        // private sender key, must be prime; e < phi, no coprime between them e.
        e = BigInteger.probablePrime(bitlength / 2, r);
        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) {
            e.add(BigInteger.ONE);
        }

        // d decyphering key.
        d = e.modInverse(phi);
    }

    // Encrypt message
    public byte[] encrypt(byte[] text) {
        return (new BigInteger(text)).modPow(e, n).toByteArray();
    }

    // Decrypt message
    public byte[] decrypt(byte[] text) {
        return (new BigInteger(text)).modPow(d, n).toByteArray();
    }

    public static String bytesToString(byte[] byteArray) {
        String test = "";
        for (byte b : byteArray) {
            test += Byte.toString(b);
        }
        return test;
    }
}
