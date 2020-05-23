//Rezvan Nafee
//112936468
//rnafee


import java.util.Random;
import java.util.Scanner;

public class ConnectFour {
    private char[] players = {'R', 'Y'};
    private char[][] gameBoard = new char[6][7];
    private boolean tryAgain;
    private boolean foundWinner;
    private boolean checkHorizontals;
    private boolean checkVerticals;
    private boolean checkLeftDiagonals;
    private boolean checkRightDiagonals;


    public ConnectFour() {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                gameBoard[i][j] = ' ';
            }
        }
        tryAgain = false;
        foundWinner = false;
        checkHorizontals = false;
        checkVerticals = false;
        checkLeftDiagonals = false;
        checkRightDiagonals = false;
    }

    public boolean isTryAgain() {
        return tryAgain;
    }

    public boolean getFoundWinner() {
        return foundWinner;
    }

    public boolean getHorizontals() {
        return this.checkHorizontals;
    }

    public boolean getVerticals() {
        return this.checkVerticals;
    }

    public boolean getLeftDiagonals() {
        return this.checkLeftDiagonals;
    }

    public boolean getRightDiagonals() {
        return this.checkRightDiagonals;
    }

    public char[] getPlayers() {
        return players;
    }

    public void setCheckHorizontals(boolean checkHorizontals) {
        this.checkHorizontals = checkHorizontals;
    }

    public void setCheckLeftDiagonals(boolean checkLeftDiagonals) {
        this.checkLeftDiagonals = checkLeftDiagonals;
    }

    public void setCheckRightDiagonals(boolean checkRightDiagonals) {
        this.checkRightDiagonals = checkRightDiagonals;
    }

    public void setCheckVerticals(boolean checkVerticals) {
        this.checkVerticals = checkVerticals;
    }

    public int determineRandomPlayer() {
        Random rand = new Random();
        return rand.nextInt(2);
    }

    public boolean isColFull(int col) {
        if (gameBoard[0][col] != ' ') {
            System.out.println("Column " + (col + 1) + " is filled. Please try a different column!");
            tryAgain = true;
            return false;
        }
        return true;
    }

    public void makeMove(int col, char move) {
        boolean legal = isColFull(col);
        if (legal) {
            for (int i = gameBoard.length - 1; i >= 0; i--) {
                if (gameBoard[i][col] == ' ') {
                    gameBoard[i][col] = move;
                    tryAgain = false;
                    break;
                }
            }
        }
    }

    public void checkHorizontally() {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length - 3; j++) {
                if (gameBoard[i][j] != ' ' && gameBoard[i][j] == gameBoard[i][j + 1] &&
                        gameBoard[i][j] == gameBoard[i][j + 2] && gameBoard[i][j] == gameBoard[i][j + 3]) {
                    setCheckHorizontals(true);
                }
            }
        }
    }

    public void checkVertically() {
        for (int i = 0; i < gameBoard[0].length - 4; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                if (gameBoard[i][j] != ' ' && gameBoard[i][j] == gameBoard[i + 1][j] &&
                        gameBoard[i][j] == gameBoard[i + 2][j] && gameBoard[i][j] == gameBoard[i + 3][j]) {
                    setCheckVerticals(true);
                }
            }
        }
    }

    public void checkLeftDiagonal() {
        for (int i = 0; i < gameBoard.length - 3; i++) {
            for (int j = 0; j < gameBoard[i].length - 3; j++) {
                if (gameBoard[i][j] != ' ' && gameBoard[i][j] == gameBoard[i + 1][j + 1] && gameBoard[i][j] ==
                        gameBoard[i + 2][j + 2] && gameBoard[i][j] == gameBoard[i + 3][j + 3])
                    setCheckLeftDiagonals(true);
            }
        }
    }


    public void checkRightDiagonal() {
        for (int i = 0; i < gameBoard.length - 3; i++) {
            for (int j = 3; j < gameBoard[i].length; j++) {
                if (gameBoard[i][j] != ' ' && gameBoard[i][j] == gameBoard[i + 1][j - 1] &&
                        gameBoard[i][j] == gameBoard[i + 2][j - 2] && gameBoard[i][j] == gameBoard[i + 3][j - 3]) {
                    setCheckRightDiagonals(true);
                }
            }
        }
    }

    public boolean isTie() {
        int counter = 0;
        for (int i = 0; i < gameBoard[0].length; i++) {
            if (gameBoard[0][i] != ' ')
                counter += 1;
        }
        return (counter == gameBoard[0].length ? true : false);
    }

    public boolean isWinner() {
        checkRightDiagonal();
        checkLeftDiagonal();
        checkVertically();
        checkHorizontally();
        if (getHorizontals() || getVerticals() || getLeftDiagonals() || getRightDiagonals()) {
            foundWinner = true;
            return true;
        }
        foundWinner = false;
        return false;
    }

    public void displayGameBoard() {
        for (int i = 0; i < this.gameBoard.length; i++) {
            System.out.print("|");
            for (int j = 0; j < this.gameBoard[i].length; j++) {
                System.out.print(" " + this.gameBoard[i][j] + " |");
            }
            System.out.println();
            System.out.println("-----------------------------");
        }
    }

}

class Game {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ConnectFour game = new ConnectFour();
        System.out.println();
        System.out.println("Let's play a game of Connect 4!");
        System.out.println("Player 1 will be Red!");
        System.out.println("Player 2 will be Yellow!");
        System.out.println();
        String num = "";
        int counter = game.determineRandomPlayer();
        if (counter == 0) {
            System.out.println("Player 1 will go first!");
        } else {
            System.out.println("Player 2 will go first!\n");
        }
        String msg = "";
        while (true) {
            if (game.isTie()) {
                game.displayGameBoard();
                msg += "Player 1 and Player 2 tied!";
                break;
            }
            game.displayGameBoard();
            if (counter % 2 == 0) {
                try {
                    System.out.println("Player 1's turn:");
                    System.out.println("Which column would you like to drop the RED disk in (1-7)?");
                    num = input.nextLine().trim();
                    int col = (Integer.parseInt(num)) - 1;
                    if (!(col >= 0 && col <= 6)) {
                        System.out.println("Error! " + (col + 1) + " cannot be placed on the board!\nPlease enter a valid column " +
                                "number found on the board!");
                        continue;
                    }
                    game.makeMove(col, game.getPlayers()[counter % 2]);
                    if (game.isTryAgain()) {
                        continue;
                    }
                    if (game.isWinner()) {
                        game.displayGameBoard();
                        msg += "Player 1 won the game!";
                        break;
                    }
                } catch (NumberFormatException ex) {
                    if (isNumber(num))
                        System.out.println("Error! " + num + " cannot be placed on the board!\nPlease enter a valid column " +
                                "number found on the board!");
                    else
                        System.out.println("Error! " + num + " is not a valid number!\nPlease enter a valid column " +
                                "number found on the board!");
                    continue;
                }
            } else {
                try {
                    System.out.println("Player 2's turn:");
                    System.out.println("Which column would you like to drop the YELLOW disk in (1-7)?");
                    num = input.nextLine().trim();
                    int col = (Integer.parseInt(num)) - 1;
                    if (!(col >= 0 && col <= 6)) {
                        System.out.println("Error! " + (col + 1) + " cannot be placed on the board!\nPlease enter a valid column " +
                                "number found on the board!");
                        continue;
                    }
                    game.makeMove(col, game.getPlayers()[counter % 2]);
                    if (game.isTryAgain()) {
                        continue;
                    }
                    if (game.isWinner()) {
                        game.displayGameBoard();
                        msg += "Player 2 won the game!";
                        break;
                    }
                } catch (NumberFormatException ex) {
                    if (isNumber(num))
                        System.out.println("Error! " + num + " cannot be placed on the board!\nPlease enter a valid column " +
                                "number found on the board!");
                    else
                        System.out.println("Error! " + num + " is not a valid number!\nPlease enter a valid column " +
                                "number found on the board!");
                    continue;
                }
            }
            counter++;
        }
        System.out.println("\n" + msg + "\nThank You for Playing!");
        input.close();
    }

    public static boolean isNumber(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c))
                return false;
        }
        return true;
    }
}

