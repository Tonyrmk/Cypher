package cryptor;

import utils.FileUtils;

import java.io.File;

public class Cryptor {

    private static final String LATIN_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String CYRILLIC_ALPHABET = "АБВГДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩЬЮЯабвгдеёжзиіїйклмнопрстуфхцчшщьюя";
    private static final String SPECIAL_SYMBOLS = ".,«»\"':!? ";


    public static String decrypt(File filename, int shift) {
        String stringForDecrypting = FileUtils.fileReader(filename);
        StringBuilder decrypted = new StringBuilder();

        for (char c : stringForDecrypting.toCharArray()) {
            decrypted.append(shiftCharacter(c, -shift));
        }
        //System.out.println(decrypted.toString());
        return decrypted.toString();

    }

    public static String encrypt(File filename, int shift) {
        String stringForCrypting = FileUtils.fileReader(filename);
        StringBuilder encrypted = new StringBuilder();
        for (char c : stringForCrypting.toCharArray()) {
            encrypted.append(shiftCharacter(c, shift));
        }

        return encrypted.toString();
    }

    public static String bruteForce(File filename) {
        String encryptedMessage = FileUtils.fileReader(filename);

        int highestScore = Integer.MIN_VALUE;
        String bestDecryption = "";

        System.out.println("Attempting brute-force decryption");


        for (int shift = 1; shift <= LATIN_ALPHABET.length(); shift++) {
            StringBuilder possibleDecryption = new StringBuilder();

            for (char c : encryptedMessage.toCharArray()) {
                possibleDecryption.append(shiftCharacter(c, -shift));
            }

            String decryptedAttempt = possibleDecryption.toString();
            int score = bruteForceValidation(decryptedAttempt);

            //System.out.println("Shift " + shift + ": " + decryptedAttempt + " | Score: " + score);

            if (score >= highestScore) {
                highestScore = score;
                bestDecryption = decryptedAttempt;
            }
        }

        //System.out.println("Best decryption: " + bestDecryption + " | Score: " + highestScore);

        return bestDecryption;
    }

    private static int bruteForceValidation(String text) {
        int score = 0;
        char[] chars = text.toCharArray();

        for (int i = 0; i < chars.length - 2; i++) {
            char current = chars[i];
            char next = chars[i + 1];
            char afterNext = chars[i + 2];
            // "." followed by " " and a capital letter
            if (current == '.' && next == ' ' && Character.isUpperCase(afterNext)) {
                score += 5;
            }
            //"," followed by " " and a lowercase letter
            if (current == ',' && next == ' ' && Character.isLowerCase(afterNext)) {
                score += 4;
            }
            // "!" or "?" followed by " " and a capital letter
            if ((current == '!' || current == '?') && next == ' ' && Character.isUpperCase(afterNext)) {
                score += 3;
            }
            // Start of a sentence with a capital letter and end with punctuation
            if (i == 0 && Character.isUpperCase(current)) {
                score += 2;
            }
            if (i == chars.length - 2 && (current == '.' || current == '!' || current == '?')) {
                score += 2;
            }


        }

        return score;
    }

    private static char shiftCharacter(char c, int shift) {
        if (LATIN_ALPHABET.indexOf(c) != -1) {
            return shiftWithinAlphabet(LATIN_ALPHABET, c, shift);
        } else if (CYRILLIC_ALPHABET.indexOf(c) != -1) {
            return shiftWithinAlphabet(CYRILLIC_ALPHABET, c, shift);
        } else if (SPECIAL_SYMBOLS.indexOf(c) != -1) {
            return c;
        } else {
            return c;
        }
    }

    private static char shiftWithinAlphabet(String alphabet, char c, int shift) {
        int originalIndex = alphabet.indexOf(c);
        int newIndex = (originalIndex + shift + alphabet.length()) % alphabet.length();
        return alphabet.charAt(newIndex);
    }
}
