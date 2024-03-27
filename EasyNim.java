import java.util.Scanner;
import java.util.Stack;

public class EasyNim {
    public static void main(String[] args) {
        System.out.println("Welcome to Easy Mode Nim!");

        Scanner scanner = new Scanner(System.in);

        
        Stack<Integer>[] piles = new Stack[4]; 
        piles[1] = new Stack<>();
        piles[2] = new Stack<>();
        piles[3] = new Stack<>();

        for (int i = 1; i <= 3; i++) { 
            for (int j = 0; j < 3 + 2 * (i - 1); j++) { 
                piles[i].push(1); // Each object represented by '1'
            }
        }

        // Game loop
        while (!isGameOver(piles)) {
            // Display current state of the piles
            displayPiles(piles);

            // Player 1's turn
            playerTurn(1, piles, scanner);

            // Check if the game is over
            if (isGameOver(piles)) {
                System.out.println("Congratulations, Player 1 won!");
                break;
            }

            // Display current state of the piles
            displayPiles(piles);

            // Player 2's turn
            playerTurn(2, piles, scanner);

            // Check if the game is over
            if (isGameOver(piles)) {
                System.out.println("Congratulations, Player 2 won!");
                break;
            }
        }
        scanner.close();
    }

    // Method to display current state of the piles
    private static void displayPiles(Stack<Integer>[] piles) {
        System.out.println("Current piles: ");
        for (int i = 1; i <= 3; i++) { // Start from 1
            System.out.println("Pile " + i + ": " + piles[i].size() + "  ");
        }
        System.out.println();
    }

    // Method for player's turn
    private static void playerTurn(int player, Stack<Integer>[] piles, Scanner scanner) {
        System.out.println("Player " + player + "'s turn:");
        while (true) {
            System.out.print("Choose a pile to remove objects (1, 2, or 3): ");
            int pileIndex = scanner.nextInt();
            if (pileIndex < 1 || pileIndex > 3 || piles[pileIndex].isEmpty()) { // Adjust bounds
                System.out.println("Invalid pile. Try again.");
                continue;
            }

            System.out.print("Choose the number of objects to remove (1 or 2): ");
            int numToRemove = scanner.nextInt();
            if (numToRemove != 1 && numToRemove != 2) {
                System.out.println("Invalid number of objects. Try again.");
                continue;
            }

            // Remove objects from the pile
            for (int i = 0; i < numToRemove; i++) {
                piles[pileIndex].pop();
            }
            break;
        }
    }

    // Method to check if the game is over
    private static boolean isGameOver(Stack<Integer>[] piles) {
        for (int i = 1; i <= 3; i++) { // Start from 1
            if (!piles[i].isEmpty()) {
                return false;
            }
        }
        return true;
    }
}