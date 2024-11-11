package cryptor;

import utils.FileUtils;

import java.io.File;

public class Cryptor {


    public static String decrypt(File filename, int shift){

        String stringForDecrypting = FileUtils.fileReader(filename);
        StringBuilder decrypted = new StringBuilder();


        for (char c : stringForDecrypting.toCharArray()) {

            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                decrypted.append((char) ((c - base - shift + 26) % 26 + base));
            } else {
                decrypted.append(c);
            }
        }

        return decrypted.toString();

    }

    public static String encrypt(File filename, int shift){
        String stringForCrypting = FileUtils.fileReader(filename);
        StringBuilder encrypted = new StringBuilder();

        for (char c : stringForCrypting.toCharArray()) {

            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                encrypted.append((char) ((c - base + shift) % 26 + base));
            } else {
                encrypted.append(c); // Non-alphabetic characters remain unchanged
            }
        }

        return encrypted.toString();
    }

    public static void brutoForce(File filename){
        System.out.println("hello from bruto_force");
    }

}
