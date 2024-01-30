import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String[] rps = {"Rock", "Paper", "Scissor"};
            String computerMove = rps[new Random().nextInt(rps.length)];
            String playerMove;

            while (true) {
                System.out.println("Please enter your move (Rock, Paper or Scissor)");
                playerMove = scanner.nextLine();
                if (playerMove.equals("Rock") || playerMove.equals("Paper") || playerMove.equals("Scissor")) {
                    break;
                }
                System.out.println(playerMove + " is not a valid move.");
            }
            System.out.println("Computer played: " + computerMove);

            if (playerMove.equals(computerMove)) {
                System.out.println("The game was a tie!");

            }
            //---------------------PlayerMove = Rock------------------------------------
            else if (playerMove.equals("rock")) {
                if (computerMove.equals("Paper")) {
                    System.out.println("You lose!");
                } else if (computerMove.equals("Scissor")) {
                    System.out.println("You win!");
                }
            }
            //---------------------PlayerMove = Paper------------------------------
            else if (playerMove.equals("Paper")) {
                if (computerMove.equals("Scissor")) {
                    System.out.println("You lose!");
                } else if (computerMove.equals("Rock")) {
                    System.out.println("You win!");
                }
            }
            //-------------------PlayerMove = Scissor------------------------------
            else if (playerMove.equals("Scissor")) {
                if (computerMove.equals("Paper")) {
                    System.out.println("You win!");
                } else if (computerMove.equals("Rock")) {
                    System.out.println("You lose!");
                }
            }
            //-------------------------------------------------------------------
            System.out.println("Play again? (y/n)");
            String playAgain = scanner.nextLine();

            if (!playAgain.equals("y")) {
                break;
            }
        }

    }
}


