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
            displayWinHistory();
            displayAllChampsInSet();
            displayBoardOptions();
            recieveBoardOptions();
        } while (!this.leaveMenu);
        
    }

    // EFFECTS: Display all board coordinates
    //          If champion is at location also display name
    public void displayBoard() {
        System.out.println("-----------|" + board.getName() + "|----------");
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 7; x++) {
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

    // EFFECTS: Display win history of board
    public void displayWinHistory() {
        System.out.print("History:");
        for (int i: board.getWinHistory()) {
            System.out.print(i + ", ");
        }
        System.err.println();
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
                removeChampion();
                break;
            } case 3: {
                updateMatchHistory();
                break;
            } case 4: {
                leaveMenu = true;
                break;
            } default: {
                System.out.println("Invalid option. Please try again.");
                recieveBoardOptions();
            }
        }
    }

    // EFFECT: display available champs in the set
    public void displayAllChampsInSet() {
        System.out.println("----------Set 13 Champions----------");
        for (int cost = 1; cost < 6; cost++) {
            System.out.print(cost + " cost champs:");
            for (Placeable champ: board.getSet().getPlaceableHashMap().values()) {
                if (champ instanceof ChampionTemplate) {
                    ChampionTemplate c = (ChampionTemplate) champ;
                    if (c.getCost() == cost) {
                        System.out.print(c.getName() + " ");
                    }
                }
            }
            System.out.println();
        }
    }


    // EFFECT: display board options on terminal
    public void displayBoardOptions() {
        System.out.println();
        System.out.println("----------|Options|-----------");
        System.out.println("1. Add champion to board");
        System.out.println("2. Remove a champion from board");
        System.out.println("3. Update placement history");
        System.out.println("4. Return to main menu");
    }


    // MODIFIES: this
    // EFFECT: prompts user to give a game placement [0,8]
    //         if invalid input retry.
    public void updateMatchHistory() {
        int i;
        System.out.println("Enter your recent placement 1st-8th");
        i = Integer.parseInt(reader.nextLine());
        while (i < 0 || i > 8) {
            System.out.println("Please try again");
            i = Integer.parseInt(reader.nextLine());
        }
        board.addToHistory(i);
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

    // MODIFIES: this
    // EFFECT: prompts user to give coordinates of the
    //         champion they wish to remove.
    public void removeChampion() {
        int x;
        int y;
        System.out.println("Input x coordinate");
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
        board.removeChampionFromRoster(x, y);
    }
}
