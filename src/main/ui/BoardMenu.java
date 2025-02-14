package ui;

import java.util.Scanner;

import model.*;

// Represents the menu system for editing a board.
public class BoardMenu {
    private Scanner reader;
    private static Set set;
    private Board board;

    // EFFECTS: - Connect the board to the menu
    //          - if its the first board instantiated in the planner
    //            also instantiate the shared set.
    //          - Displays the board
    //         
    public BoardMenu(Board b) {
        this.board = b;
        reader = new Scanner(System.in);
        displayBoard();
        displayBoardOptions();
    }

    // EFFECTS: Display all board coordinates
    //          If champion is at location also display name
    public void displayBoard() {
        System.out.println("-----------|" + board.getName() + "|----------");
        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 7; y++) {
                Placeable champion = board.getChampionFromBoard(x,y);
                if (champion != null) {
                    System.out.print("|" + champion.getName() + "(" + x + "," + y + ")" + "|     ");
                } else {
                    System.out.print("(" + x + "," + y + ")      ");
                }
            }
            System.out.println();
        }
    }

    // EFFECTS: collect option and run consequent functions
    public void recieveBoardOptions() {
        int option = reader.nextInt();
        switch (option) {
            case 1: {
                System.out.println("Please input the exact name of the Champion");
                addChampion(reader.nextLine());
            }

            case 2:

            case 3:

            case 4:

            default: {
                System.out.println("Invalid option. Please try again.");
                recieveBoardOptions();
            }
        }
    }

    // EFFECT: display board options on terminal
    public void displayBoardOptions() {
        System.out.println();
        System.out.println("----------|Options|-----------");
        System.out.println("1. Add champion to board");
        System.out.println("2. Move a champion's position");
        System.out.println("3. Remove a champion from board");
        System.out.println("4. Go back to main menu");
    }

    // MODIFIES: Board, Planner
    // EFFECT: add the desired champion to the board
    //         prompt users to try again if:
    //          - champion is already there
    //          - invalid champion name
    //          - invalid champion location
    public void addChampion(String userSearch) {
        
        if (set.findChampionTemplate(userSearch) != null) {
            int x;
            int y;
            System.out.println("Champion found:" + userSearch);
            System.out.println("Input x coordinate:");
            x = reader.nextInt();
            while (x < 0 || x > 7) {
                System.out.println("Please insert a proper x coordinate [0, 7]");
                x = reader.nextInt();
            }
            System.out.println("Input y coordinate:");
            y = reader.nextInt();
            while (y < 0 || y > 4) {
                System.out.println("Please insert a proper y coordinate [0, 4]");
                y = reader.nextInt();
            }
            board.addChampionToBoard(set.findChampionTemplate(userSearch), x, y);
        }  else {
            System.out.println("Couldn't find champion, please try again");
            addChampion(reader.nextLine());
        }
    }
}
