/**
 * Card class represents a single card used in the game.
 * Each card is either a murder card or belongs to a player.
 */
public class Card{

    /**
     * Type of card. Referenced from the CardType enum.
     */
    private CardType cardType;

    /**
     * Name of card.
     */
    private String name;

    /**
     * Card constructor method.
     * 
     * @param t type of card
     * @param n name of card
     */
    public Card(CardType t, String n){
        cardType = t;
        name = n;
    }

    /**
     * Getter method for card type.
     * 
     * @return Card Type enum
     */
    public CardType getCartType(){
        return cardType;
    }
    
    /**
     * Getter method for card name
     * 
     * @return name of card
     */
    public String getCard(){
        return name;
    }
}