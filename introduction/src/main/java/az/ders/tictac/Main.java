package az.ders.tictac;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        Scanner scanner = new Scanner(System.in);
        String result;
        int x, y;

        System.out.println("Tic-Tac-Toe Game");
        System.out.println("Player X starts first!");

        while (true) {
            System.out.println("Current Player: " + game.nextPlayer());
            System.out.print("Enter row (1-3): ");
            x = scanner.nextInt();
            System.out.print("Enter column (1-3): ");
            y = scanner.nextInt();

            try {
                result = game.play(x, y);
                System.out.println(result);
                if (!result.equals("No winner")) {
                    break;
                }
            } catch (RuntimeException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}