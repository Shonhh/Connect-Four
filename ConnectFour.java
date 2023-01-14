import java.util.*;
import static java.lang.System.*;
import java.io.*;

public class ConnectForMe {
    public static String line = null;
    public static int index = 0;
    public static String turn = "R";

    public static String[] rowOne = { " ", " ", " ", " ", " ", " ", " " };
    public static String[] rowTwo = { " ", " ", " ", " ", " ", " ", " " };
    public static String[] rowThree = { " ", " ", " ", " ", " ", " ", " " };
    public static String[] rowFour = { " ", " ", " ", " ", " ", " ", " " };
    public static String[] rowFive = { " ", " ", " ", " ", " ", " ", " " };
    public static String[] rowSix = { " ", " ", " ", " ", " ", " ", " " };
    public static String[] rowSeven = { " ", " ", " ", " ", " ", " ", " " };
    public static String[][] gameState = { rowOne, rowTwo, rowThree, rowFour, rowFive, rowSix, rowSeven };

    public static void drawBoard() {
        out.println("-------------------------------------------");
        out.println("|  1  |  2  |  3  |  4  |  5  |  6  |  7  |");
        out.println("-------------------------------------------");

        for (String[] arrN : gameState) {
            int i = 0;
            for (String n : arrN) {
                out.print("|  " + n + "  ");
                if (i > 5) {
                    out.println("|");
                    out.println("-------------------------------------------");
                }
                i++;
            }
        }
    }

    public static void dropPiece(int input) {
        for (int i = 6; i >= 0; i--) {
            if (Objects.equals(gameState[i][input], " ")) {
                gameState[i][input] = turn;
                index++;
                break;
            }
        }
    }

    public static void checkWin() {
        // Vertical Wins
        for (int j = 0; j < 7; j++) {
            for (int i = 0; i < 4; i++) {
                line = gameState[i][j] + gameState[i + 1][j] + gameState[i + 2][j] + gameState[i + 3][j];
                if (line.equals("RRRR") || line.equals("YYYY")) {
                    out.println(turn + "'s have won!");
                    exit(0);
                }
                line = null;
            }
        }
        // Horizontal Wins
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 7; i++) {
                line = gameState[i][j] + gameState[i][j + 1] + gameState[i][j + 2] + gameState[i][j + 3];
                if (line.equals("RRRR") || line.equals("YYYY")) {
                    out.println(turn + "'s have won!");
                    exit(0);
                }
                line = null;
            }
        }
        // Top to Bot Diagonals
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                line = gameState[i][j] + gameState[i + 1][j + 1] + gameState[i + 2][j + 2] + gameState[i + 3][j + 3];
                if (line.equals("RRRR") || line.equals("YYYY")) {
                    out.println(turn + "'s have won!");
                    exit(0);
                }
                line = null;
            }
        }
        // Bot to Top Diagonals
        for (int j = 0; j < 4; j++) {
            for (int i = 6; i > 2; i--) {
                line = gameState[i][j] + gameState[i - 1][j + 1] + gameState[i - 2][j + 2] + gameState[i - 3][j + 3];
                if (line.equals("RRRR") || line.equals("YYYY")) {
                    out.println(turn + "'s have won!");
                    exit(0);
                }
                line = null;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        drawBoard();
        for (int i = 0; i < 49; i++) {
            turn = (Objects.equals(turn, "Y")) ? "R" : "Y";
            out.print("Player " + turn + ": Enter the column you want to put your piece in: ");
            int input = sc.nextInt() - 1;

            dropPiece(input);
            drawBoard();
            checkWin();
        }
        out.println("It's a tie!");
        sc.close();
    }
}