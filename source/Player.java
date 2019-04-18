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

        Block b = Board.blockSearch(location);
        b.setHasPlayer(true);
    }

    public Role getRole(){
        return role;
    }

    public ArrayList<Card> getCard(){
        return cards;
    }

    public boolean hasCard(String card){
        for (Card c : cards){
            card.equalsIgnoreCase(c.getCard());
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

    public abstract void move(int m);

    public void move(int m, String room){
        this.location = m;
        this.printRole();
        System.out.println(" has been moved to " + room);
    }

    public abstract void showCard();

    public abstract boolean wantMakeAccusation();

    public abstract boolean makeAccusation(Card[] murderCards);

    public abstract String[] makeSuggestion();
}