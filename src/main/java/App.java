
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
            throw new WrongNumberArgumentsException("please add at least 2 argument");}

            Runner.run(args);
    }
}
