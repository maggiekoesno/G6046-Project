import java.util.Random;

public class ComputerPlayer extends Player{

    private Random random = new Random();
    private int turns = 0;

    public ComputerPlayer(Role r){
        super(r);
    }

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

    public void showCard(){
        this.printRole();
        System.out.println(" has seen their cards...");
    }

    public boolean wantMakeAccusation(){
        turns ++;
        if(turns>10){
            int j = random.nextInt(1);
            if(j == 1)
                return true;
        }
        return false;
    }

    public boolean makeAccusation(Card[] murderCards){
        String room = Board.blockSearch(this.getLocation()).getRoomName();
        if(murderCards[2].getCard().equalsIgnoreCase(room)){
            // Assuming there is a 1/6 chance of getting each other card correct
            int j = random.nextInt(36);
            if(j == 24)
                return true;
        }

        this.printRole();
        System.out.println(" has finished their turn!");
        return false;
    }

    public String[] makeSuggestion(){
        return null;
    }

}