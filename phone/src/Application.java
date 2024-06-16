package phone.src;

import java.util.Scanner;

public abstract class Application {
    protected final Scanner scanner = new Scanner(System.in);

    protected abstract boolean decodeUserInput(int input) throws Exception;

    protected abstract void printOptions();

    protected abstract void runApp();
}
