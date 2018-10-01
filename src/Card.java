
public class Card {
	public static enum ranks {
		ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), 
		JACK(10), QUEEN(10), KING(10), ACE(1);
		
	  
	    // declaring private variable for getting values 
	    private int action; 
	  
	    // getter method 
	    public int getAction() 
	    { 
	        return this.action; 
	    } 
	  
	    // enum constructor - cannot be public or protected 
	    private ranks(int action) 
	    { 
	        this.action = action; 
	    } 
	    
	}
	
	public static enum suits {
		CLUBS, DIAMONDS, HEARTS, SPADES
	}
	
	public final Card.ranks rank;
	public final Card.suits suit;
	
	public Card(Card.ranks rank, Card.suits suit) {
		this.rank = rank;
		this.suit = suit;
	}
	
	public boolean equals(Object o) {
	    if (o == this) {
	      return true;
	    }
	    if (!(o instanceof Card)) {
	      return false;
	    }
	    Card c = (Card)o;
	    return c.rank == rank && c.suit == suit;
	}
}
