import java.security.Key;
import java.util.*;

public class OneHundreds {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        //Add players
        System.out.println("How many players (2-4):");
        int playerAmount;
        while (true) {
            try {
                playerAmount = Integer.parseInt(input.nextLine());
                if(playerAmount > 4 || playerAmount < 2){
                    System.err.println("Must enter 2, 3, or 4");
                }else{
                    break;
                }
            } catch (NumberFormatException e) {
                System.err.println("Must enter integer");
            }
        }
        //Add scoring
        ArrayList<Player> players = new ArrayList<>();
        Map<String, Integer> playerScores = new HashMap<>();
        for(int i = 0; i < playerAmount; ++i){
            String playerName = "";
            LinkedList<Card> playerHand = new LinkedList<>();
            while (true) {
                try {
                    System.out.println("Enter player name:");
                    playerName = input.nextLine();
                    break;
                } catch (InputMismatchException e) {
                    System.err.println("Name must be a string");
                }
            }
            playerScores.put(playerName, 0);
            Player player = new Player(playerName, playerHand);
            players.add(player);
        }
        //Generate and deal deck
        CardDeck deck = new CardDeck();
        deck.generateDeck();
        deck.shuffleDeck(deck.getDeck(), 2);

        try {
            while(deck.getDeck().size() - players.size() >= 0){
                for(Player player : players){
                    player.addCard(deck.getDeck().get(0));
                    deck.getDeck().remove(0);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("cards dealt\n");
        }

        //Game starts
        while(players.get(0).getHand().size() > 0){
            Map<String, Card> playedCards = new HashMap<>();
            //Play cards
            for(Player player : players){
                Card playedCard = player.getHand().getFirst();
                System.out.println(player.getName() + " played: " + playedCard.getValue() + " - " + playedCard.getStatus());
                playedCards.put(player.getName(), playedCard);
                player.getHand().remove(0);
            }
            //Compare cards
            String winner = "";
            Card highestCard = new Card("n", 0);
            for(Map.Entry<String, Card> entry : playedCards.entrySet()){
                if(entry.getValue().getValue() < highestCard.getValue() && entry.getValue().getStatus().equals("w")){
                    highestCard = entry.getValue();
                    winner = entry.getKey();
                }else if(entry.getValue().getStatus().equals("w") && highestCard.getStatus().equals("n")){
                    highestCard = entry.getValue();
                    winner = entry.getKey();
                }else if(entry.getValue().getValue() > highestCard.getValue() && highestCard.getStatus().equals("n")){
                    highestCard = entry.getValue();
                    winner = entry.getKey();
                }
            }
            //Update score
            System.out.println(winner + " has won this round!\n");
            playerScores.put(winner, playerScores.get(winner) + 1);
        }
        System.out.println("Remaining cards:");
        deck.printDeck();
        System.out.println("Final scores:");
        String winner = "";
        int highestScore = 0;
        for(Map.Entry<String, Integer> player : playerScores.entrySet()) {
            System.out.println(player.getKey() + ": " + player.getValue());
            if(player.getValue() > highestScore){
                winner = player.getKey();
                highestScore = player.getValue();
            }else if(player.getValue() == highestScore){
                winner = "Its a tie!";
            }
        }
        System.out.println("The winner is:\n" + winner);
    }
}
