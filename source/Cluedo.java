import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

/**
 * Cluedo is the main class that controls the flow of the game.
 */
public class Cluedo{

    /**
     * Number of players in this game.
     */
    private static int numPlayers;

    /**
     * Array List of player objects that are participating in this game.
     */
    private static ArrayList<Player> player = new ArrayList<Player>();

    /**
     * List of Card objects knows as the muderCards.
     * These are the Cards the players have to figure out to win the game.
     * There are 3 cards exactly, one from each category (i.e room, person, weapon).
     */
    private static Card[] murderCards = new Card[3];

    /**
     * Does all the preparations before a game, which include:
     * <ul>
     * <li> Creation of all the cards</li>
     * <li> Setting aside the murder cards</li>
     * <li> Creation of the board to play on</li>
     * <li> Creation of all the players and player setup</li>
     * <li> Dealing the cards amongst the players</li>
     * </ul>
     */
    private static void setup(){
        Scanner sc = new Scanner(System.in);
        Random r = new Random();
        ArrayList<Card> deck = new ArrayList<Card>();
        Role role = Role.MISS_SCARLET;
        Card c;
        int choice;
        int i;
        int j;
        int k;

        int forFun = 5;

        // Creation of person cards
        c = new Card(CardType.PERSON, "Col Mustard");
        deck.add(c);
        c = new Card(CardType.PERSON, "Prof Plum");
        deck.add(c);
        c = new Card(CardType.PERSON, "Rev Green");
        deck.add(c);
        c = new Card(CardType.PERSON, "Mrs Peacock");
        deck.add(c);
        c = new Card(CardType.PERSON, "Miss Scarlett");
        deck.add(c);
        c = new Card(CardType.PERSON, "Mrs White");
        deck.add(c);

        // Picking the murderer
        i = r.nextInt(6);
        murderCards[0] = deck.get(i);
        deck.remove(i);

        // Creationg of weapon cards
        c = new Card(CardType.WEAPON, "Dagger");
        deck.add(c);
        c = new Card(CardType.WEAPON, "Candlestick");
        deck.add(c);
        c = new Card(CardType.WEAPON, "Revolver");
        deck.add(c);
        c = new Card(CardType.WEAPON, "Rope");
        deck.add(c);
        c = new Card(CardType.WEAPON, "Lead Piping");
        deck.add(c);
        c = new Card(CardType.WEAPON, "Spanner");
        deck.add(c);

        // Picking the murder weapon
        i = r.nextInt(6) + 5;
        murderCards[1] = deck.get(i);
        deck.remove(i);

        // Creation of room cards
        c = new Card(CardType.ROOM, "Study");
        deck.add(c);
        c = new Card(CardType.ROOM, "Hall");
        deck.add(c);
        c = new Card(CardType.ROOM, "Lounge");
        deck.add(c);
        c = new Card(CardType.ROOM, "Dining Room");
        deck.add(c);
        c = new Card(CardType.ROOM, "Kitchen");
        deck.add(c);  
        c = new Card(CardType.ROOM, "Ball Room");
        deck.add(c);
        c = new Card(CardType.ROOM, "Conservatory");
        deck.add(c);
        c = new Card(CardType.ROOM, "Billiard Room");
        deck.add(c);
        c = new Card(CardType.ROOM, "Library");
        deck.add(c);
        
        // Picking the murder location
        i = r.nextInt(9) + 10;
        murderCards[2] = deck.get(i);
        deck.remove(i);

        // Setting up the board
        Board.makeBoard();

        // Player setup
        System.out.println("Welcome to Cluedo!");
        System.out.print("Enter number of player (2-6): ");
        numPlayers = sc.nextInt();
        sc.nextLine();

        System.out.println("Player 1 is always Miss Scarlet!");
        System.out.println("Enter 1 if someone wants to play as Miss Scarlet");
        System.out.println("Enter 2 if Miss Scarlet is a bot");
        choice = sc.nextInt();
        sc.nextLine();

        if(choice == 1){
            player.add(new HumanPlayer(Role.MISS_SCARLET));
        }
        else{
            player.add(new ComputerPlayer(Role.MISS_SCARLET));
        }

        for(i=1;i<numPlayers;i++){
            System.out.println("Select role for player " + (i+1));
            System.out.println("1. Col Mustard");
            System.out.println("2. Prof plum");
            System.out.println("3. Rev Green");
            System.out.println("4. Mrs Peacock");
            System.out.println("5. Mrs White");
            choice = sc.nextInt();
            sc.nextLine();

            switch(choice){
                case 1: role = Role.COL_MUSTARD;
                        break;
                case 2: role = Role.PROF_PLUM;
                        break; 
                case 3: role = Role.REV_GREEN;
                        break;
                case 4: role = Role.MRS_PEACOCK;
                        break;
                case 5: role = Role.MRS_WHITE;
                        break;
            }

            System.out.println("Enter 1 if someone wants to play this role");
            System.out.println("Enter 2 if this player is a bot");
            choice = sc.nextInt();
            sc.nextLine();
    
            if(choice == 1){
                player.add(new HumanPlayer(role));
            }
            else{
                player.add(new ComputerPlayer(role));
            }
        }

        // Dealing the cards
        System.out.println("Dealing cards...");

        j = 0;
        for(i=18;i>0;i--){
            k = r.nextInt(i);
            player.get(j).addCard(deck.get(k));
            deck.remove(k);
            j = (j+1)%numPlayers;
        }

        // Letting each player see their cards before the start of the game
        for(Player p : player){
            p.showCard();
        }

    }

    /**
     * Method to allow player to get an answer to their suggestion.
     * After a player makes a suggestion, we check to see if the next player has one or more of the cards in the suggestion.
     * If they do, show one of the cards to the player and method is done, if not, check the next player.
     * Each player is checked until either at least card is found or all the other players have been checked.
     * 
     * @param suggestion names of cards player suggested
     * @param i          player's turn order
     */
    private static void checkSuggestion(String[] suggestion, int i){
        // Check if player made a suggestion
        if(suggestion == null)
            return;
        
        Player p;
        Boolean flag = false;
        Scanner sc = new Scanner(System.in);
        String cardToShow;
        int j;
        int k;

        // Loops a maximum of the number of player - 1 because the player who made the suggestion is not included
        for(j = 1; j < numPlayers; j++){
            i = (i+j)%numPlayers;
            p = player.get(i);

            for(k=0; k<3; k++){
                if(p.hasCard(suggestion[k])){
                    if(!flag){
                        p.printRole();
                        System.out.print(", please pick a card to show! (Enter)");
                        sc.nextLine();
                    }
                    else{
                        System.out.print(" or ");
                    }
                    flag = true;
                    System.out.print(suggestion[k]);
                }
            }
            
            if(flag){
                System.out.println("");
                System.out.print("Your choice: ");
                cardToShow = sc.nextLine().strip();
                p.printRole();
                System.out.println(" has the card " + cardToShow);
                break;
            }
        }
    }

    /**
     * Helps move suggested player to suggested room.
     * 
     * @param name name of player suggested
     * @param location location block id of player who made the suggestion
     * @param room name of room suggested
     */
    public static void moveSuggestedPlayer(String name, int location, String room){
        Role r;

        switch(name.toLowerCase()){
            case "col mustard": r = Role.COL_MUSTARD;
            case "prof plum": r = Role.PROF_PLUM;
            case "rev green": r = Role.REV_GREEN;
            case "mrs peacock": r = Role.MRS_PEACOCK;
            case "miss scarlet": r = Role.MISS_SCARLET;
            case "mrs white": r = Role.MRS_WHITE;
            default: r = null;
        }

        for(Player p : player){
            if(p.getRole() == r){
                p.move(location,room);
            }
        }
    }

    /**
     * Main function that will be run in the console.
     * 
     * @param args default parameter for main function, unused this time
     */
    public static void main(String[] args){
        setup();

        int i = 0;
        int moves;
        String[] suggestion;
        Player p;
        Boolean flag = false;

        // Keeps game going until somebody wins or only one player left
        while(true){
            if(numPlayers == 1){
                p = player.get(0);
                p.printRole();
                System.out.println(", you are the last player!");
                System.out.println("Congratulation! You win by default");
                System.out.println(murderCards[0].getCard() + " killed the victim using a " + murderCards[1].getCard() + " at the " + murderCards[2].getCard());
                break;
            }

            p = player.get(i);
            System.out.print("It is ");
            p.printRole();
            System.out.println("'s turn!'");

            moves = p.roleDice();
            p.move(moves);

            // Player can only make a suggestion or accusation if they are in a room
            if(Board.blockSearch(p.getLocation()).isRoom()){

                suggestion = p.makeSuggestion();
                checkSuggestion(suggestion, i);
                
                if(p.wantMakeAccusation())
                    flag = p.makeAccusation(murderCards);
                    if(!flag){
                        System.out.print("Sorry ");
                        p.printRole();
                        System.out.println(", your accusation is incorrect!");
                        System.out.println("You are now out of the game :(");
                        player.remove(p);
                        numPlayers--;
                    }
            }

            if(flag){
                System.out.println("Congratulations, you solved the case!");
                p.printRole();
                System.out.println(", you are the winner");
                System.out.println(murderCards[0].getCard() + " killed the victim using a " + murderCards[1].getCard() + " at the " + murderCards[2].getCard());
                break;
            }

            i = (i+1) % numPlayers;
        }

        System.out.println("Thank you for playing!");
    }
}