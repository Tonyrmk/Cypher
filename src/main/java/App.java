
import exceprions.WrongNumberArgumentsException;
import runner.Runner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        if (args.length < 2) {
            throw new WrongNumberArgumentsException("Please add at least 2 arguments\n" +
                    "For encrypting and decrypting use format: " +
                    "java -jar cryptor-1.0-SNAPSHOT.jar ENCRYPT/DECRYPT \'filePath/filename\' shift\n" +
                    "For bruteforce mode use format: " +
                    "java -jar cryptor-1.0-SNAPSHOT.jar BRUTE_FORCE \'filePath/filename\'");}


            Runner.run(args);
    }
}
