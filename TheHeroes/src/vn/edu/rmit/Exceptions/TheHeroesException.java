package vn.edu.rmit.Exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: KATE.LE
 * Date: 3/10/13
 * Time: 12:43 PM
 */
public class TheHeroesException extends Exception {

    public TheHeroesException(String message) {
        super(message);
    }

    public TheHeroesException() {
        super("Unspecified Exception");
    }

}
