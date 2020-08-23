package com.huiaong.pikachu.common.util;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.util.UUID;

public class EncryptUtil {
    private static final HashFunction SHA512 = Hashing.sha512();
    private static final Splitter SPLITTER = Splitter.on('@').trimResults();
    private static final Joiner JOINER = Joiner.on('@').skipNulls();
    private static final HashFunction MD5 = Hashing.md5();

    public static String encrypt(String password) {
        String salt = MD5.newHasher().putString(UUID.randomUUID().toString(), Charsets.UTF_8).putLong(System.currentTimeMillis()).hash().toString().substring(0, 4);
        String realPassword = SHA512.hashString(password + salt, Charsets.UTF_8).toString().substring(0, 20);
        return JOINER.join(salt, realPassword, new Object[0]);
    }

    public static boolean match(String password, String encryptedPassword) {
        Iterable<String> parts = SPLITTER.split(encryptedPassword);
        String salt = (String) Iterables.get(parts, 0);
        String realPassword = (String) Iterables.get(parts, 1);
        return Objects.equal(SHA512.hashString(password + salt, Charsets.UTF_8).toString().substring(0, 20), realPassword);
    }
}
