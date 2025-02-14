package ui;

import model.Planner;
import java.util.Scanner;
import model.*;


// Represents the menu system for the main board planner application.
public class MainMenu {
    private Scanner reader = new Scanner(System.in);
    private Planner planner;

    public MainMenu() {
        planner = new Planner();
        mainMenuPage();
    }

    // MODIFIES: Planner
    // EFFECT: Prints boards that the user has made
    //         Prints option to add new board or edit a previous board.
    //         Recieves a option.
    public void mainMenuPage() {
        printBoardDeck();
        displaySurfaceOptions();
        recieveSurfaceOption();
    }
    // EFFECT: Prints the catalogue of boards within the planner
    //         if no boards, prompt to make a board
    private void printBoardDeck() {
        System.out.println("-----------| Your boards | -----------");
        if (!planner.getBoardDeck().isEmpty()) {
            for (int x = 0; x < planner.getBoardDeck().size(); x++) {
                Board b = planner.getBoardDeck().get(x);
                System.out.println((x+1) + ". " + b.getName());
            }
        } else {
            System.err.println("You have no boards!");
        }
    }

    // EFFECT: print out the options available.
    private void displaySurfaceOptions() {
        System.out.println("-------------| Options | -------------");
        System.out.println("1. Create a new board");
        System.out.println("2. Edit a board");

    }

    // MODIFIES: Planner
    // EFFECT: recieves a option for the above selections.
    //         else prompts user to try again
    private void recieveSurfaceOption () {
        int option = reader.nextInt();
        reader.nextLine();
        
        if (option == 1) {
            System.out.println("Give your board a name:");
            String name = reader.nextLine();
            new BoardMenu(new Board(new Set("Set Thirteen"), name));
        } else if (option == 2) {

        } else {
            System.err.println("Invalid option. Please enter again.");
            recieveSurfaceOption();
        }
    }

    public static void main(String[] args) throws Exception {
        new MainMenu();
    }
}
