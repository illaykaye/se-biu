package phone.src;

import java.util.Scanner;

public abstract class Application {
    protected final Scanner scanner = new Scanner(System.in);
    protected String appName;

    protected abstract boolean decodeUserInput(int input) throws Exception;

    protected abstract void printOptions();

    protected abstract void printAllData();

    protected String getAppName() {
        return this.appName;
    }

    public void runApp() {
        boolean exit = false;
        while (!exit) {
            printOptions();
            System.out.println("Choose an option: ");
            int input = Integer.parseInt(scanner.nextLine());
            try {
                exit = decodeUserInput(input);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
