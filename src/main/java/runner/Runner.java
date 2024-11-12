package runner;

import contants.Operation;
import cryptor.Cryptor;
import utils.FileUtils;

import java.io.File;


public class Runner {

    public static void run(String[] args) {
        ArgsParser parser = new ArgsParser();
        Operation operation = parser.getOperation(args[0]);
        File fileForCryptor = parser.getFilePath(args[1]);
        int shift = parser.getShift(args, operation);

        switch (operation) {
            case ENCRYPT:
                //System.out.println(Cryptor.encrypt(fileForCryptor, shift));
                FileUtils.fileWriter(fileForCryptor, Cryptor.encrypt(fileForCryptor, shift), operation);
                break;

            case DECRYPT:
                //System.out.println(Cryptor.decrypt(fileForCryptor, shift));
                FileUtils.fileWriter(fileForCryptor, Cryptor.decrypt(fileForCryptor, shift), operation);
                break;
            case BRUTE_FORCE:
                FileUtils.fileWriter(fileForCryptor,Cryptor.bruteForce(fileForCryptor),operation);
                break;
        }


    }


}
