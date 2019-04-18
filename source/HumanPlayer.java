import java.util.ArrayList;
import java.util.Scanner;

public class HumanPlayer extends Player{
    private Scanner sc = new Scanner(System.in);

    public HumanPlayer(Role r){
        super(r);
    }

    public void move(int m){
        String choice;
        System.out.print("Do you want to role the dice (y/n)? ");
        choice = sc.nextLine();
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


    public void showCard(){
        this.printRole();
        System.out.println(", here are your cards! Enter if you are ready to see them...");
        sc.nextLine();

        System.out.println("Here are your cards: ");
        ArrayList<Card> myCard = getCard();
        for(Card c : myCard){
            System.out.println(c.getCard());
        }  
    }

    public boolean wantMakeAccusation(){
        String choice;
        System.out.print("Do you want to make an accusation (y/n)? ");
        choice = sc.nextLine().strip();
        if(choice.equalsIgnoreCase("y")){
            return true;
        }
        return false;
    }
    
    public boolean makeAccusation(Card[] murderCards){
        String room = Board.blockSearch(this.getLocation()).getRoomName();
        System.out.println("You think the murder happend in the " + room);
        System.out.print("Who is the murderer? ");
        String person = sc.nextLine().strip();
        System.out.print("What is the murder weapon? ");
        String weapon = sc.nextLine().strip();

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