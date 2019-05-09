import java.util.Random;

/**
 * ComputerPlayer class implements methods needed to simulate a computer player.
 */
public class ComputerPlayer extends Player{

    /**
     * Random object initialized as a lot of the computer's decision will be random.
     */
    private Random random = new Random();

    /**
     * A counter to track the number of turns this player has made.
     * This is counter is used to give the computer player a chance to make an accusation
     * and thus a chance to win the game.
     * 
     * Computer player will make an accusation after the first 10 turns.
     */
    private int turns = 0;

    /**
     * Constructor for ComputerPlayer class.
     * 
     * @param r role of player
     */
    public ComputerPlayer(Role r){
        super(r);
    }

    /**
     * Move player across the board when it is their turn.
     * Always assume computer player wants to move and determine movements randomly.
     * 
     * @param m number of moves from dice roll
     */
    public void move(int m){
        int move;
        int nextBlock;

        int locationId = this.getLocation();
        Block location = Board.blockSearch(locationId);
        location.setHasPlayer(false);

        while(m>0){
            move = random.nextInt(4);
            switch(move){
                case 0: nextBlock = locationId - 1;
                        break;
                case 1: nextBlock = locationId + 1;
                        break;
                case 2: nextBlock = locationId - 25;
                        break;
                case 3: nextBlock = locationId + 25;
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
     * Simulate computer player looking at their cards.
     */
    public void showCard(){
        this.printRole();
        System.out.println(" has seen their cards...");
    }

    /**
     * Keep track of when computer player will be simulated to want to make an accusation.
     * Increment counter each turn and make computer want to make an accusation after the first 10 turns.
     * 
     * @return true after first 10 turns that computer player is in a room, false otherwise
     */
    public boolean wantMakeAccusation(){
        turns ++;
        if(turns>10){
            int j = random.nextInt(1);
            if(j == 1)
                return true;
        }
        return false;
    }

    /**
     * Simulate computer player making an accusation.
     * Assuming by taking a random guess, the computer has an equal chance of guessing each card.
     * Since there are 6 cards of each category to guess from (i.e player and weapon),
     * by randomly picking guessing a card from each category, the computer has the probability of guessing
     * the correct pair of (1/6 x 1/6 =) 1/36.
     * This is the same probability as picking a specific number from 0 to 35.
     * 
     * Therefore, given the computer is in the correct room already. It has a 1 in 36 chance of winning.
     * 
     * @param muderCards list of murder Cards, irrelevant to this simulation
     * @return true if computer is in the correct room and correct random number is chosen, false otherwise
     */
    public boolean makeAccusation(Card[] murderCards){
        String room = Board.blockSearch(this.getLocation()).getRoomName();
        if(murderCards[2].getCard().equalsIgnoreCase(room)){
            int j = random.nextInt(36);

            // 24 is a random number chosen. Any other positive integer below 36 will work just as fine.
            if(j == 24)
                return true;
        }

        this.printRole();
        System.out.println(" has finished their turn!");
        return false;
    }

    /**
     * Simulate computer player making a suggestion.
     * Always assume computer player does not make a suggestion as no AI is implemented.
     * 
     * @return null always
     */
    public String[] makeSuggestion(){
        return null;
    }

}