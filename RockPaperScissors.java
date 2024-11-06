import java.util.Scanner;
import java.util.Random;

public class RockPaperScissors {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Welcome to Rock-Paper-Scissors!");
        System.out.print("Enter your choice (rock, paper, or scissors): ");
        String playerChoice = scanner.nextLine().toLowerCase();

        // Computer makes a random choice
        String[] choices = {"rock", "paper", "scissors"};
        String computerChoice = choices[random.nextInt(3)];

        System.out.println("Computer chose: " + computerChoice);

        // Determine the winner
        if (playerChoice.equals(computerChoice)) {
            System.out.println("It's a tie!");
        } else if ((playerChoice.equals("rock") && computerChoice.equals("scissors")) ||
                   (playerChoice.equals("paper") && computerChoice.equals("rock")) ||
                   (playerChoice.equals("scissors") && computerChoice.equals("paper"))) {
            System.out.println("You win!");
        } else {
            System.out.println("Computer wins!");
        }

        scanner.close();
    }
}
