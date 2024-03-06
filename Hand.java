package bsu.comp152;

/**
 * Hand - A class to create Hand objects and get to know them
 *
 * Starter code from Computer Science 111, Boston University
 *
 * Modified by Laura K. Gross, COMP 152, Bridgewater State University
 *
 * Completed by: Charlie Dakai cdakai@student.bridgew.edu
 * date: 3/2022
 */
public class Hand {
    /* Constants for types of hands
     * The numbers used for the hand types increase
     * with the value of the hand type.
     * For example, four-of-a-kind is the highest-valued
     * hand type that we support, and it has the highest
     * numeric value.
     */
    private static final int HIGH_CARD = 0;
    private static final int PAIR = 1;
    private static final int TWO_PAIRS = 2;
    private static final int THREE_OF_A_KIND = 3;
    private static final int FLUSH = 4;
    private static final int FOUR_OF_A_KIND = 5;

    // The instance fields for a Hand object
    // The Card objects in the Hand object are stored in array called cards.
    // Declare the array of Card objects.
    private Card[] cards;
    // The number of cards in the hand is called numCards.
    // Declare it as an integer.
    private int numCards;

    /*
     * getTotalValue - computes and returns the sum of the values
     * of the cards in the cards array.
     * If the Hand is currently empty (i.e., has no cards),
     * the method returns 0.
     */
    public int getTotalValue() {
        int value = 0;
        for (int i = 0; i < numCards; i++) {
            value += cards[i].getValue();
        }
        return value;
    }

    // constructor
    public Hand(int maxCards) {
        cards = new Card[maxCards];
        numCards = 0;
    }

    public int getNumCards() {
        return numCards;
    }

    public void addCard(Card c1) {
        if (c1 == null) {
            throw new IllegalArgumentException("Parameter is null");
        }
        if (cards[cards.length-1] != null) {
            throw new IllegalStateException("Array is full");
        }
        cards[numCards] = c1;
        numCards += 1;
    }

    public String toString() {
        if (numCards == 0){
            return "[]";
        }
        String str = "[";
        for (int i = 0; i < numCards - 1; i++) {
            if (cards[i] != null){
                str += cards[i].getAbbrev() + ", ";
            }
        }
        str += cards[numCards-1].getAbbrev();
        return str + "]";
    }

    public Card getCard(int i) {
        if (i < 0){
            throw new IllegalArgumentException("Index is negative");
        }
        if (i > cards.length - 1) {
            throw new IllegalArgumentException("Index is too large");
        }
        if (cards[i] == null){
            throw new IllegalArgumentException("There is no card in that place");
        }
        if (numCards == 0){
            throw new IllegalArgumentException("There are no cards to get");
        }
        return cards[i];
    }

    public Card playCard(int index) {
        if ((index > numCards - 1) || (index < 0)){
            throw new IllegalArgumentException("Index too large or too small");
        }
        Card temp = cards[index];
        cards[index] = null;
        numCards -= 1;
        for(int i = index; i < numCards; i++){
            cards[i] = cards[i + 1];
        }
        cards[cards.length-1] = null;
        return temp;
    }

    public Card highCard() {
        if (numCards == 1){
            return cards[0];
        }
        Card max = cards[0];
        for (int i = 0; i < numCards; i++){
            if (max.getValue() < cards[i].getValue()){
                max = cards[i];
            }
        }
        return max;
    }

    public int numCardsOfRank(int rank) {
        int count = 0;
        for(int i = 0; i < numCards; i++){
            if (cards[i].getRank() == rank){
                count += 1;
            }
        }
        return count;
    }

    public boolean hasFlush() {
        if (numCards == 1){
            return true;
        }
        int repeat = 0;
        for(int i = 0; i < numCards - 1; i++){
            if(cards[i].getSuit() == cards[i+1].getSuit()){
               repeat += 1;
            }
        }
        if (repeat == numCards - 1) {
            return true;
        }
       else return false;
    }
}

