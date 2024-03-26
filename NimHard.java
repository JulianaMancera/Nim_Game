import java.util.*;
// HI GUYS so here nag limit na ko mismo for hard mode = 10 piles= 120 elements
// 

public class NimHard {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("You are playing Hard Mode! You have 10 piles all in all!");
        // d ko alam kung pede pa magdagdag rito ng instructions or what 

        // Initializing piles
        Stack<Integer>[] piles = new Stack[10];
        int[] pileSizes = {3, 5, 7, 9, 11, 13, 15, 17, 19, 21};
        for (int i = 0; i < 10; i++) {
            piles[i] = new Stack<>();
            int numElements = pileSizes[i];
            while (numElements > 0) {
                piles[i].push(1); // Each element in the pile is represented as 1
                numElements--;
            }
        }

        // Loop for the starting game part
        int currentPlayer = 1;
        while (true) {
            System.out.println("\nPlayer " + currentPlayer + "'s turn!");
            displayPiles(piles);

            // Player's move
            System.out.print("Choose pile to be taken out with (1 to 10): ");
            int pileIndex = scanner.nextInt() - 1;
            while (pileIndex < 0 || pileIndex >= 10 || piles[pileIndex].isEmpty()) {
                System.out.println("Invalid pile! Choose again!");
                System.out.print("Choose pile to be taken out with (1 to 10): ");
                pileIndex = scanner.nextInt() - 1;
            }

            System.out.print("Enter no. of sticks to remove (1 or 2): ");
            int sticksToRemove = scanner.nextInt();
            while (sticksToRemove != 1 && sticksToRemove != 2) {
                System.out.println("Invalid number of sticks! Choose again!");
                System.out.print("Enter no. of sticks to remove (1 or 2): ");
                sticksToRemove = scanner.nextInt();
            }

            // Update the pile
            for (int i = 0; i < sticksToRemove; i++) {
                piles[pileIndex].pop();
            }
            System.out.println("After your move, the elements of pile " + (pileIndex + 1) + " left: " + piles[pileIndex].size());

            // Check if all piles are empty
            if (allPilesEmpty(piles)) {
                System.out.println("Player " + currentPlayer + " wins the game! All piles are empty!");
                break;
            }

            // Switch to the other player
            currentPlayer = (currentPlayer == 1) ? 2 : 1;
        }
        scanner.close();
    }

    // Function to display the current state of piles
    private static void displayPiles(Stack<Integer>[] piles) {
        System.out.println("Current state of piles:");
        for (int i = 0; i < 10; i++) {
            System.out.println("Pile " + (i + 1) + ": " + piles[i].size() + " elements");
        }
    }

    // Function to check if all piles are empty
    private static boolean allPilesEmpty(Stack<Integer>[] piles) {
        for (int i = 0; i < 10; i++) {
            if (!piles[i].isEmpty()) {
                return false;
            }
        }
        return true;
    }
}