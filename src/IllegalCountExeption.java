import java.io.PrintStream;

public class IllegalCountExeption extends RuntimeException{
    public IllegalCountExeption(String message) {
        super(message);
    }



    public IllegalCountExeption() {
    }
}
