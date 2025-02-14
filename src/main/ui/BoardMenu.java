package ui;

import java.util.Scanner;

import model.*;

// Represents the menu system for editing a board.
public class BoardMenu {
    private Scanner reader  = new Scanner(System.in);
    private Board board;
    private Boolean leaveMenu;

    // EFFECTS: - Connect the board to the menu
    //          - if its the first board instantiated in the planner
    //            also instantiate the shared set.
    //          - Displays the board
    //         
    public BoardMenu(Board b) {
        this.board = b;
        this.board.getSet().loadSet13();
        this.leaveMenu = false;
        do {
            displayBoard();
            displayBoardOptions();
            recieveBoardOptions();
        } while (!this.leaveMenu);
        
    }

    // EFFECTS: Display all board coordinates
    //          If champion is at location also display name
    public void displayBoard() {
        System.out.println("-----------|" + board.getName() + "|----------");
        for (int x = 0; x < 4; x++) {
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

    // MODIFIES: this
    // EFFECTS: collect option and run consequent functions
    public void recieveBoardOptions() {
        int option = Integer.parseInt(reader.nextLine());
        
        switch (option) {
            case 1: {
                System.out.println("Please input the exact name of the Champion");
                addChampion(reader.nextLine());
                break;
            } case 2: {

            } case 3: {

            } case 4: {
                leaveMenu = true;
                break;
            } default: {
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

    // MODIFIES: this
    // EFFECT: add the desired champion to the board
    //         prompt users to try again if:
    //          - champion is already there
    //          - invalid champion name
    //          - invalid champion location
    public void addChampion(String userSearch) {
        
        if (board.getSet().findChampionTemplate(userSearch) != null) {
            int x;
            int y;
            System.out.println("Champion found:" + userSearch);
            System.out.println("Input x coordinate:");
            x = Integer.parseInt(reader.nextLine());
            while (x < 0 || x > 7) {
                System.out.println("Please insert a proper x coordinate [0, 7]");
                x = Integer.parseInt(reader.nextLine());
            }
            System.out.println("Input y coordinate:");
            y = Integer.parseInt(reader.nextLine());
            while (y < 0 || y > 4) {
                System.out.println("Please insert a proper y coordinate [0, 4]");
                y = Integer.parseInt(reader.nextLine());
            }
            board.addChampionToBoard(board.getSet().findChampionTemplate(userSearch), x, y);
        }  else {
            System.out.println("Couldn't find champion, please try again");
            addChampion(reader.nextLine());
        }
    }
}
