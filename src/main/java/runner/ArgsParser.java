package runner;

import contants.Operation;
import exceprions.FileNotExistException;
import exceprions.ShiftParseException;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ArgsParser {

    public int getShift(String[] args , Operation operation) {
        if (args.length == 3 && operation.equals(Operation.DECRYPT) || operation.equals(Operation.ENCRYPT)) {
            try {
                return Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                throw new ShiftParseException("can't parse the shift, please use figures for the operation");
            }
        }
        else {
            //throw new IllegalArgumentException("please add correct shift value for Encryption and Decryption");
            return 0;
        }

    }

    public Operation getOperation(String operation) {
        return Operation.valueOf(operation);
    }

    public File getFilePath(String filePath) {
        Path path = Paths.get(filePath);
        if (Files.exists(path)) {
            return new File(filePath);
        } else {
            throw new FileNotExistException("the file is not found, please check the path");
        }
    }
}
