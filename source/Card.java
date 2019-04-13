public class Card{
    private CardType cardType;
    private String name;

    public Card(CardType t, String n){
        cardType = t;
        name = n;
    }

    public CardType getCartType(){
        return cardType;
    }
    
    public String getCard(){
        return name;
    }
}