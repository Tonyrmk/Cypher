package runner;

import contants.Operation;
import cryptor.Cryptor;
import exceprions.FileNotExistException;
import exceprions.IllegalArgumentException;
import exceprions.ShiftParseException;
import utils.FileUtils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Runner {

    public static void run(String[] args) {

        if(args.length<2){
            throw new IllegalArgumentException("please add at least 2 argument");
        }
        Operation operation = getOperation(args[0]);
        File fileForCryptor = getFile(args[1]);
        int  shift = getShift(args,operation);


        switch (operation){
            case ENCRYPT:
                //System.out.println(Cryptor.encrypt(fileForCryptor, shift));
                FileUtils.fileWriter(fileForCryptor,Cryptor.encrypt(fileForCryptor, shift),operation);
                break;

            case DECRYPT:
                //System.out.println(Cryptor.decrypt(fileForCryptor, shift));
                FileUtils.fileWriter(fileForCryptor,Cryptor.decrypt(fileForCryptor, shift),operation);
                break;
            case BRUTE_FORCE:
                Cryptor.brutoForce(fileForCryptor);
                break;
        }


    }

    public static int getShift(String[] args , Operation operation) {
        if (args.length == 3 && operation.equals(Operation.DECRYPT) || operation.equals(Operation.ENCRYPT)) {
            try {
                return Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                throw new ShiftParseException("can't parse the shift, please use figures for the operation");
            }
        }
        else {
            throw new IllegalArgumentException("please add correct shift value for Encryption and Decryption");
        }

    }

    public static Operation getOperation(String operation) {
        return Operation.valueOf(operation);
    }

    public static File getFile(String filePath) {
        Path path = Paths.get(filePath);
        if (Files.exists(path)) {
            return new File(filePath);
        } else {
            throw new FileNotExistException("the file is not found, please check the path");
        }
    }
}
