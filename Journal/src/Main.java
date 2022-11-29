import java.util.Scanner;

/**
 * @author butrim
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Journal journal = new Journal();
        CommandLineManager commandLineManager = new CommandLineManager(scanner, journal);

        commandLineManager.printMenuAndHandleCommandInfinitely();
    }
}
