import java.util.Scanner;

import input.InputHandler;
import model.InputContent;
import output.OutputHandler;
import solve.SolveHandler;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // System.out.print("Enter input file path: ");
        // String inputPath = scanner.nextLine();

        String inputText = InputHandler.readTxtFile("Input.txt");

        InputContent inputContent = InputHandler.parseInputContent(inputText);

        String result = SolveHandler.solve(inputContent);

        OutputHandler.writeTxtFile("Output.txt", result);

        System.out.println("Result:\n");
        System.out.println(result);

        scanner.close();
    }
}