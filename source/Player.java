import java.util.ArrayList;
import java.util.Random;

public abstract class Player {
    private Role role;
    private ArrayList<Card> cards;
    private int location;

    public Player(Role r){
        role = r;
        
        Random rand = new Random();
        int i = rand.nextInt(Board.getStartBlockSize());
        location = Board.getStartBlock(i);
    }

    public Role getRole(){
        return role;
    }

    public boolean hasCard(String card){
        for (Card c : cards){
            card.equals(c.getCard());
        }
        return false;
    }

    public void addCard(Card c){
        cards.add(c);
    }

    public int getLocation(){
        return location;
    }

    public int roleDice(){
        Random r = new Random();
        return r.nextInt(12) + 1;
    }

    public void printRole(){
        switch(role){
            case REV_GREEN: System.out.print("Rev Green");
            case COL_MUSTARD: System.out.print("Col Mustards");
            case MISS_SCARLET: System.out.print("Miss Scarlet");
            case MRS_PEACOCK: System.out.print("Mrs Peacock");
            case MRS_WHITE: System.out.print("Mrs White");
            case PROF_PLUM: System.out.print("Prof Plum");
        }
    }

    public abstract void move(int m);

    public abstract void seeCards();

    public abstract void showCard();

    public abstract boolean makeAccusation();

    public abstract void makeSuggestion();
}