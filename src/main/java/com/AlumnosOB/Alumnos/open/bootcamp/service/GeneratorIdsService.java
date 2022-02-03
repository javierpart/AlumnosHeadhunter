package com.AlumnosOB.Alumnos.open.bootcamp.service;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

@Service
public class GeneratorIdsService {

    private final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZÑÑÑÑÑ";
    private final String lower = upper.toLowerCase(Locale.ROOT);
    private final String digits = "0123456789";
    private final String alphanum = upper + lower + digits;
    private Random random;
    private char[] symbols;
    private char[] buf;


    public String defaultPublicId() {
        int length = 8;
        return generateAlphanumeric(length);

    }
    public String generate(int length) {

        String symbols = digits;
        Random random = new SecureRandom();
        if (length < 1) throw new IllegalArgumentException();
        if (symbols.length() < 2) throw new IllegalArgumentException();
        this.random = Objects.requireNonNull(random);
        this.symbols = symbols.toCharArray();
        this.buf = new char[length];
        return nextString();

    }

    public String generateAlphanumeric(int length) {

        String  symbols = this.alphanum;
        Random random = new SecureRandom();
        if (length < 1) throw new IllegalArgumentException();
        if (symbols.length() < 2) throw new IllegalArgumentException();
        this.random = Objects.requireNonNull(random);
        this.symbols = symbols.toCharArray();
        this.buf = new char[length];
        return nextString();

    }

    private String nextString() {

        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbols[random.nextInt(symbols.length)];
        return new String(buf);

    }
}
