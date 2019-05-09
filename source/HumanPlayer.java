import java.util.ArrayList;
import java.util.Scanner;

/**
 * HumanPlayer class implements the methods needed to facilitate a human player.
 */
public class HumanPlayer extends Player{

    /**
     * Scanner initialized as it will be used very often in this class to interact with user.
     */
    private Scanner sc = new Scanner(System.in);

    /**
     * Constructor for HumanPlayer class.
     * 
     * @param r role of player
     */
    public HumanPlayer(Role r){
        super(r);
    }

    /**
     * Asks user if they want to role the dice in order to move.
     * If they do, moves player across the board and updates location accordingly.
     * User can move the player using the standard "WASD" keyboard controls, ie.
     * <ul>
     * <li> 'A' to move left </li>
     * <li> 'D' to move right </li>
     * <li> 'W' to move up </li>
     * <li> 'S' to move down </li>
     * </ul>
     */
    public void move(int m){
        String choice;
        System.out.println("Do you want to role the dice (y/n)? ");
        choice = sc.next();
        if(choice.equalsIgnoreCase("n")){
            return;
        }

        String move;
        int nextBlock;

        int locationId = this.getLocation();
        Block location = Board.blockSearch(locationId);
        location.setHasPlayer(false);

        while(m>0){
            System.out.println("Enter your next move (WASD): ");
            move = sc.nextLine();
            switch(move.strip().toUpperCase()){
                case "A": nextBlock = locationId - 1;
                          break;
                case "D": nextBlock = locationId + 1;
                          break;
                case "W": nextBlock = locationId - 25;
                          break;
                case "S": nextBlock = locationId + 25;
                          break;
                default: nextBlock = locationId;
            }
            if(location.canMove(nextBlock)){
                locationId = nextBlock;
                location = Board.blockSearch(locationId);
                m--;
            }
        }

        location.setHasPlayer(true);
    }

    /**
     * Shows user their cards.
     */
    public void showCard(){
        //Added precaution to make user only the intended user can see their cards
        this.printRole();
        System.out.println(", here are your cards! Enter '1' if you are ready to see them...");
        sc.nextInt();
        sc.nextLine();

        System.out.println("Here are your cards: ");
        ArrayList<Card> myCard = getCard();
        for(Card c : myCard){
            System.out.println(c.getCard());
        }  
        System.out.println("");
    }

    /**
     * Asks user if they want to make an accusation.
     * 
     * @return true if they want to, false otherwise
     */
    public boolean wantMakeAccusation(){
        String choice;
        System.out.print("Do you want to make an accusation (y/n)? ");
        choice = sc.nextLine().strip();
        if(choice.equalsIgnoreCase("y")){
            return true;
        }
        return false;
    }
    
    /**
     * Asks user for their accusation and determine wether or not the given accusation is correct.
     * 
     * @param murderCards list of Card objects that contain the correct answers
     * @return            true if accusation made is correct, false otherwise
     */
    public boolean makeAccusation(Card[] murderCards){
        String room = Board.blockSearch(this.getLocation()).getRoomName();
        System.out.println("You think the murder happend in the " + room);
        System.out.print("Who is the murderer? ");
        String person = sc.nextLine().strip();
        System.out.print("What is the murder weapon? ");
        String weapon = sc.nextLine().strip();

        // immediately return false if any one of the accusations are wrong
        if(!(murderCards[0].getCard().equalsIgnoreCase(person))){
            return false;
        }
        if(!(murderCards[1].getCard().equalsIgnoreCase(weapon))){
            return false;
        }
        if(!(murderCards[2].getCard().equalsIgnoreCase(room))){
            return false;
        }

        return true;
    }

    /**
     * Ask user if they want to make a suggestion.
     * If they do, ask for their suggestion.
     * 
     * @return string array of suggested card names if a suggestion is made
     *         null if user does not want to make a suggestion
     */
    public String[] makeSuggestion(){
        String choice;
        System.out.print("Do you want to make a suggestion (y/n)? ");
        choice = sc.nextLine().strip();
        if(choice.equalsIgnoreCase("n")){
            return null;
        }

        String[] suggestion = new String[3];

        suggestion[0] = Board.blockSearch(this.getLocation()).getRoomName();

        System.out.println("You are suggesting the murder happened in the " + suggestion[0]);
        System.out.print("Who do you think is the murderer? ");
        suggestion[1] = sc.nextLine().strip();
        Cluedo.moveSuggestedPlayer(suggestion[1], this.getLocation(), suggestion[0]);

        System.out.print("What do you think is the murder weapon? ");
        suggestion[2] = sc.nextLine().strip();

        return suggestion;
    }

}