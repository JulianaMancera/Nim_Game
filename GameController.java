import java.util.*;

public class GameController{

    public static void main(String[] args){

        System.out.println("Welcome to Nim Game!\n");
        Scanner game = new Scanner(System.in);

            System.out.println("Choose the game mode:");
            System.out.println("1. Easy Mode");
            System.out.println("2. Hard Mode");
            System.out.print("Enter your choice: ");

            int nimChoice = game.nextInt();
    
            switch (nimChoice){
                case 1:
                    EasyNim.main(args);
                    break;

                case 2:
                    HardNim.main(args);
                    break;

                default:
                    System.out.println("Invalid Choice");
                    break;

        }
        game.close();

    }

}