import java.util.ArrayList;
import java.util.Objects;

// The ElevensBoard class represents the board in a game of Elevens.
// This class will contain all the code that implements the rules of the game
public class ElevensBoard extends Board {

    // The size (number of cards) on the board is fixed at 9
    private static final int BOARD_SIZE = 9;

    // These three arrays will be used in the constructor:
    // The ranks of the cards for this game to be sent to the deck
    private static final String[] RANKS = { "ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king" };
    // The suits of the cards for this game to be sent to the deck.
    private static final String[] SUITS = { "spades", "hearts", "diamonds", "clubs" };
    // The values of the cards for this game to be sent to the deck.
    private static final int[] POINT_VALUES = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 0, 0 };

    // Creates a new ElevensBoard instance with a standard 52 card deck
    public ElevensBoard() {
        super(BOARD_SIZE, RANKS, SUITS, POINT_VALUES);
    }

    // Given a list of the **indexes** of some cards on the board,
    // returns true if **any** two of the given cards adds up to exactly 11
    // and false otherwise
    public boolean containsPairSum11(ArrayList<Integer> cardIndexes) {
        for (int currentCard = 0; currentCard<cardIndexes.size();currentCard++){
            for(int nextCard = 0; nextCard<cardIndexes.size();nextCard ++){
                if (cardAt(cardIndexes.get(currentCard)).getPointValue() + cardAt(cardIndexes.get(nextCard)).getPointValue() == 11 && currentCard != nextCard){
                    return true;
                }
            }
        }
        return false;
    }

    // Given a list of the **indexes** of some cards on the board,
    // returns true if there is at least 1 Jack, at least 1 Queen, and at least 1 King
    // amongst the selected cards, and false otherwise
    public boolean containsJQK(ArrayList<Integer> cardIndexes) {
        // YOUR CODE HERE
        int numJacks = 0,numQueens = 0, numKings = 0;
        for (int i = 0; i < cardIndexes.size();i++){
            if(Objects.equals(cardAt(cardIndexes.get(i)).getRank(), "jack")){
                numJacks ++;
            }
            else if(Objects.equals(cardAt(cardIndexes.get(i)).getRank(), "queen")){
                numQueens ++;
            }
            else if(Objects.equals(cardAt(cardIndexes.get(i)).getRank(), "king")){
                numKings ++;
            }
        }
        if(numKings > 0 && numQueens > 0 && numJacks > 0){
            return true;
        }
        return false;
    }

    // Determine if there are any legal plays left on the board.
    // A legal play is either a pair of non-face cards whose values
    // add to 11, or (2) a group of three cards consisting of a jack, a
    // queen, and a king in some order.
    @Override
    public boolean anotherPlayIsPossible() {
        // allCards is a list of the indexes of all cards on the board
        ArrayList<Integer> allCards = getAllCardIndexes();
        if(containsPairSum11(allCards) || containsJQK(allCards)){
            return true;
        }
        // YOUR CODE HERE
        // Just 1-2 lines of code needed
        return false;
    }

    // Determines if the selected cards form a valid group for removal. In Elevens,
    // the legal groups are (1) a pair of non-face cards whose values add to 11, and
    // (2) a group of three cards consisting of a jack, a queen, and a king in some order
    @Override
    public boolean isLegal(ArrayList<Integer> selectedCards) {
        // YOUR CODE HERE
        if(containsPairSum11(selectedCards) && selectedCards.size() == 2){
            return true;
        }
        else if (containsJQK(selectedCards) && selectedCards.size() == 3){
            return true;
        }
        return false;
    }
}
