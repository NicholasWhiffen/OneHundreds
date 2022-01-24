import com.sun.tools.attach.AgentInitializationException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;
import java.util.Random;

public class CardDeck {
    private ArrayList<Card> deck;

    public ArrayList<Card> getDeck() {
        return this.deck;
    }

    public void generateDeck(){
        this.deck = new ArrayList<>();
        for (int i = 1; i <= 100; ++i) {
            Card card = new Card("n", i);
            this.deck.add(card);
        }
        for (int i = 0; i < 4; ++i) {
            Random random = new Random();
            int randomCard = random.nextInt(100);
            this.deck.get(randomCard).setStatus("w");
        }
    }

    public void shuffleDeck(ArrayList<Card> deck, int timesShuffled){
        for(int i = 0; i < timesShuffled; ++i){
            Collections.shuffle(deck);
        }
    }

    public void printDeck(){
        ListIterator<Card> it = deck.listIterator();
        while (it.hasNext()){
            Card card = it.next();
            System.out.println(card.getValue() + " - " + card.getStatus());
        }
        if(deck.size() == 0){
            System.out.println("No cards remaining\n");
        }else{
            System.out.println();
        }
    }

    public int cardsRemaining(){
        ListIterator<Card> it = deck.listIterator();
        int cardsRemaining = 0;
        while (it.hasNext()){
            it.next();
            cardsRemaining += 1;
        }
        return  cardsRemaining;
    }
}
