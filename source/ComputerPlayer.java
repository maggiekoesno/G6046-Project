import java.util.Scanner;
import java.util.Random;

public class ComputerPlayer extends Player{

    private Scanner sc = new Scanner(System.in);

    public ComputerPlayer(Role r){
        super(r);
    }

    public void move(int m){}

    public void seeCards(){}

    public void showCard(){}

    public boolean makeAccusation(){return true;}

    public void makeSuggestion(){}

    public int roleDice(){
        String choice;
        System.out.println("Do you want to role the dice (y/n)? ");
        choice = sc.nextLine();
        
        if(choice.equalsIgnoreCase("y")){
            Random r = new Random();
            return r.nextInt(12) + 1;
        }

        return 0;
    }
}