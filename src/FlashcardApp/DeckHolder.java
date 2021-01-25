package FlashcardApp;


//This is a singleton class used to pass the deck to the add cards scene.

import java.util.ArrayList;

public class DeckHolder {

    private static final DeckHolder deckHolderInstance = new DeckHolder();
    private Deck deck;
    private ArrayList<Card> cards;
    private int cardIndex;

    private DeckHolder(){
    }

    public static DeckHolder getDeckHolderInstance() {
        return deckHolderInstance;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public int getCardIndex() {
        return cardIndex;
    }

    public void setCardIndex(int cardIndex) {
        this.cardIndex = cardIndex;
    }
}
