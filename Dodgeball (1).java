import java.util.Random;
import java.util.Scanner;

public class Dodgeball {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] field = {".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."};
        int hit = 0;
        int touches = 0;
        field[8] = "|";
        String startingPlayerSymbol = "P";
        String playerSymbolWithBall = "8";
        String ballSymbol = "0";
        String computerSymbol = "R";
        field[3] = startingPlayerSymbol;
        field[13] = computerSymbol;
        field[0] = ballSymbol;
        displayField(field);
        System.out.print("Player 1 command: ");
        String command = input.nextLine();
        while (!command.equalsIgnoreCase("quit")) {
            if (command.equalsIgnoreCase("move")) {
                playerMove(field, input);
            } else if (command.equalsIgnoreCase("throw")) {
                hit = playerThrow(field);
                if (hit == 1) {
                    touches += hit;
                }
            } else {
                System.out.println("use move, throw or quit command");
            }
            winningCondition(touches);
            System.out.print("Player 1 command: ");
            command = input.next();
        }
    }

    public static void displayField(String[] field) {
        System.out.print("[");
        for (String element : field) {
            System.out.print(element);
        }
        System.out.print("]");
        System.out.println();
    }

    public static void playerMove(String[] field, Scanner input) {
        String playerSymbol = "P";
        System.out.print("How far? ");
        int move = input.nextInt();
        int position = 0;
        for (int i = 0; i < field.length; i++) {
            if (field[i].equalsIgnoreCase("8")) {
                playerSymbol = "8";
            }
            if (field[i].equalsIgnoreCase(playerSymbol)) {
                field[i] = ".";
                position = i;
            }
        }
        int movement = position + move;
        if (movement == 0) {
            playerSymbol = "8";
            field[0] = playerSymbol;
        } else if (movement < 0 || movement > 7) {
            field[position] = ".";
            playerSymbol = "8";
            field[7] = playerSymbol;
        } else {
            field[movement] = playerSymbol;
        }
        System.out.print("moving: " + move);
        System.out.println();
        displayField(field);
    }

    public static int playerThrow(String[] field) {
        int hit = 0;
        int playerPosition = 0;
        int botPosition = 0;
        int botMovement;
        Random random = new Random();
        int randomNumber = random.nextInt(18);
        System.out.println(randomNumber);
        for (int i = 0; i < field.length; i++) {
            if (field[i].equalsIgnoreCase("8")) {
                playerPosition = i;
            }
        }
        for (int i = 0; i < field.length; i++) {
            if (field[i].equalsIgnoreCase("R")) {
                botPosition = i;
            }
        }
        if (randomNumber > (Math.abs(playerPosition - botPosition))) {
            System.out.println("Throwing...");
            System.out.println("It's a hit");
            field[playerPosition] = "P";
            field[0] = "0";
            botMovement = random.nextInt(5) - 2;
            field[botPosition] = ".";
            field[botMovement + botPosition] = "R";
            hit++;
        } else {
            System.out.println("Throwing...");
            System.out.println("Miss :(");
            field[playerPosition] = "P";
            field[0] = "0";
            botMovement = random.nextInt(5) - 2;
            field[botPosition] = ".";
            field[botMovement + botPosition] = "R";
        }
        displayField(field);
        return hit;
    }

    public static void winningCondition(int hit) {
        if (hit >= 2) {
            System.out.println("You win");
            System.exit(0);
        }
    }
}
/*
Output
[0..P....|....R...]
Player 1 command: move
How far? 2
moving: 2
[0....P..|....R...]
Player 1 command: move
How far? -5
moving: -5
[8.......|....R...]
Player 1 command: move
How far? 100
moving: 100
[.......8|....R...]
Player 1 command: throw
11
Throwing...
It's a hit
[0......P|...R....]
Player 1 command: move
How far? -7
moving: -7
[8.......|...R....]
Player 1 command: move
How far? 7
moving: 7
[.......8|...R....]
Player 1 command: throw
12
Throwing...
It's a hit
[0......P|..R.....]
You win


 */