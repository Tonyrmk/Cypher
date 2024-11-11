package utils;

import runner.Runner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CliHelper {

    public static void getOperation(){
        System.out.println("write an operation commons:");
        System.out.println("ENCRYPT | DECRYPT | BRUTE_FORCE");

    }


    public static BufferedReader getReader(){
    try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
        return reader;
    }
    catch (IOException ex){
        ex.getMessage();
    }
     return null;
    }
}
