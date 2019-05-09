import java.util.ArrayList;
import java.util.Random;

/**
 * An abstract class that represents each player in the game.
 */
public abstract class Player {

    /**
     * The role of the player. Referenced from the Role enum.
     */
    private Role role;

    /**
     * The hand of cards dealt to the player as an array of Card objects.
     */
    private ArrayList<Card> cards;

    /**
     * Location of player on the board.
     * Int represents the block ID the player is on.
     */
    private int location;

    /**
     * Constructor for Player class.
     * 
     * @param r role of player
     */
    public Player(Role r){
        role = r;
        cards = new ArrayList<Card>();
        
        // Places player at a random available starting block.
        Random rand = new Random();
        int i = rand.nextInt(Board.getStartBlockSize());
        location = Board.getStartBlock(i);

        // Change the location's block's attribute accordingly
        Block b = Board.blockSearch(location);
        b.setHasPlayer(true);
    }

    /**
     * Getter method for role.
     * 
     * @return role 
     */
    public Role getRole(){
        return role;
    }

    /**
     * Getter method for hand of cards.
     * 
     * @return array list of cards
     */
    public ArrayList<Card> getCard(){
        return cards;
    }

    /**
     * Checks if player has a certain card.
     * 
     * @param card name of card as string
     * @return     true if player has card, false otherwise.
     */
    public boolean hasCard(String card){
        for (Card c : cards){
            card.equalsIgnoreCase(c.getCard());
        }
        return false;
    }

    /**
     * Adds card into player's hand.
     * 
     * @param c card to add
     */
    public void addCard(Card c){
        cards.add(c);
    }

    /**
     * Getter method for location.
     * 
     * @return block id of current location
     */
    public int getLocation(){
        return location;
    }

    /**
     * Simulate player rolling the dice.
     * Dice used are two non-biased six-faced dice.
     * 
     * @return random dice results
     */
    public int roleDice(){
        Random r = new Random();
        return r.nextInt(12) + 1;
    }

    /**
     * Print out role of player.
     */
    public void printRole(){
        switch(role){
            case REV_GREEN: System.out.print("Rev Green");
                            break;
            case COL_MUSTARD: System.out.print("Col Mustards");
                              break;
            case MISS_SCARLET: System.out.print("Miss Scarlet");
                               break;
            case MRS_PEACOCK: System.out.print("Mrs Peacock");
                              break;
            case MRS_WHITE: System.out.print("Mrs White");
                            break;
            case PROF_PLUM: System.out.print("Prof Plum");
                            break;
        }
    }

    /**
     * Abstract method to move player across the board and update location accordingly.
     * Called if player wants to move during player's turn.
     * 
     * @param m number of moves it can make based on dice results
     */
    public abstract void move(int m);

    /**
     * Moves player to a room when another player makes a suggestion.
     * 
     * @param m    block id of location of the player making the suggestion
     * @param room name of room the player is called to
     */
    public void move(int m, String room){
        this.location = m;
        this.printRole();
        System.out.println(" has been moved to " + room);
    }

    /**
     * Abstract method to print out player's hand.
     */
    public abstract void showCard();

    /**
     * Abstract method to ask player if he/she wants to make an accusation.
     * 
     * @return true if player wants to, false otherwise
     */
    public abstract boolean wantMakeAccusation();

    /**
     * Abstract method for player to make an accusation.
     * 
     * @param murderCards list of correct murder cards
     * @return            true if player is correct, false otherwise
     */
    public abstract boolean makeAccusation(Card[] murderCards);

    /**
     * Abstract method for player to make a suggestion.
     * 
     * @return list of card names player suggested if suggestion is made
     *         null if player does not want to make a suggestion
     */
    public abstract String[] makeSuggestion();
}