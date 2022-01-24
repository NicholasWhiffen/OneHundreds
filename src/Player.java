import java.util.LinkedList;

public class Player {
    private String name;
    private LinkedList<Card> hand;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<Card> getHand() {
        return hand;
    }

    public void addCard(Card card) {
        this.hand.add(card);
    }

    public void printHand(){
        int cardNumber = 1;
        for (Card card : this.hand) {
            System.out.printf("Card %d: %s %s", cardNumber, card.getValue(), card.getStatus());
        }
    }

    public Player(String name, LinkedList<Card> hand) {
        this.name = name;
        this.hand = hand;
    }
}
