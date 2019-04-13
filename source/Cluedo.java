import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Cluedo{

    private static int numPlayers;
    private static ArrayList<Player> player;
    private static Card[] murderCards = new Card[3];

    private static void setup(){
        Scanner sc = new Scanner(System.in);
        Random r = new Random();
        ArrayList<Card> deck = new ArrayList<Card>();
        Card c;
        Role role = Role.MISS_SCARLET;
        int choice;
        int i;
        int j;
        int k;

        c = new Card(CardType.PERSON, "Col Mustard");
        deck.set(0,c);
        c = new Card(CardType.PERSON, "Prof Plum");
        deck.set(1,c);
        c = new Card(CardType.PERSON, "Rev Green");
        deck.set(2,c);
        c = new Card(CardType.PERSON, "Mrs Peacock");
        deck.set(3,c);
        c = new Card(CardType.PERSON, "Miss Scarlett");
        deck.set(4,c);
        c = new Card(CardType.PERSON, "Mrs White");
        deck.set(5,c);

        i = r.nextInt(6);
        murderCards[0] = deck.get(i);
        deck.remove(i);

        c = new Card(CardType.WEAPON, "Dagger");
        deck.set(5, c);
        c = new Card(CardType.WEAPON, "Candlestick");
        deck.set(6, c);
        c = new Card(CardType.WEAPON, "Revolver");
        deck.set(7, c);
        c = new Card(CardType.WEAPON, "Rope");
        deck.set(8, c);
        c = new Card(CardType.WEAPON, "Lead Piping");
        deck.set(9, c);
        c = new Card(CardType.WEAPON, "Spanner");
        deck.set(10, c);

        i = r.nextInt(6) + 5;
        murderCards[1] = deck.get(i);
        deck.remove(i);

        c = new Card(CardType.ROOM, "Study");
        deck.set(10, c);
        c = new Card(CardType.ROOM, "Hall");
        deck.set(11, c);
        c = new Card(CardType.ROOM, "Lounge");
        deck.set(12, c);
        c = new Card(CardType.ROOM, "Dining Room");
        deck.set(13, c);
        c = new Card(CardType.ROOM, "Kitchen");
        deck.set(14, c);
        c = new Card(CardType.ROOM, "Ball Room");
        deck.set(15, c);
        c = new Card(CardType.ROOM, "Conservatory");
        deck.set(16, c);
        c = new Card(CardType.ROOM, "Billiard Room");
        deck.set(17, c);
        c = new Card(CardType.ROOM, "Library");
        deck.set(18, c);
        
        i = r.nextInt(9) + 10;
        murderCards[2] = deck.get(i);
        deck.remove(i);

        Board.makeBoard();

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
            player.set(0, new HumanPlayer(Role.MISS_SCARLET));
        }
        else{
            player.set(0, new ComputerPlayer(Role.MISS_SCARLET));
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
                case 2: role = Role.PROF_PLUM;
                case 3: role = Role.REV_GREEN;
                case 4: role = Role.MRS_PEACOCK;
                case 5: role = Role.MRS_WHITE;
            }

            System.out.println("Enter 1 if someone wants to play this role");
            System.out.println("Enter 2 if this player is a bot");
            choice = sc.nextInt();
            sc.nextLine();
    
            if(choice == 1){
                player.set(i, new HumanPlayer(role));
            }
            else{
                player.set(i, new ComputerPlayer(role));
            }
        }

        System.out.println("Dealing cards...");

        j = 0;
        for(i=18;i>0;i--){
            k = r.nextInt(i);
            player.get(j).addCard(deck.get(k));
            deck.remove(k);
            j = (j+1)%numPlayers;
        }

        //TODO show cards first before game?

        sc.close(); 
    }

    public static void main(String[] args){
        setup();

        int i = 0;
        int moves;
        Player p;

        while(true){
            p = player.get(i);
            System.out.print("It is ");
            p.printRole();
            System.out.println("'s turn!'");

            moves = p.roleDice();
            p.move(moves);
            p.makeSuggestion();
            if(p.makeAccusation())
                break;
        }
    }
}