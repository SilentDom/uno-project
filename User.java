
public class User extends Player{
	Game game;
	Deck deck;

	public User(String playerName) {
		super(playerName);
		// TODO Auto-generated constructor stub
	}

	public Card userCard(int cardNumber) {
		try {
			Card currentPlayerCard = getPlayerCard(cardNumber);
			Card discardCard = getGame(game).getDiscardPile().get(getGame(game).getDiscardPile().size()-1);

			if (currentPlayerCard.getCardType() == discardCard.getCardType()
					|| currentPlayerCard.getCardColor() == discardCard.getCardColor()
					|| currentPlayerCard.getCardType() == CardType.Wild
					|| currentPlayerCard.getCardType() == CardType.WildDrawFour
					|| discardCard.getCardType() == CardType.Wild	// temporary until wild color choosing
					|| discardCard.getCardType() == CardType.WildDrawFour) {

				getPlayerHand().remove(cardNumber); 
				return currentPlayerCard;
			}
			else return null;


		} catch (Exception e) {
			System.out.println("We caught exception: " + e.toString());
			return null;
		}
	}

	public Card playCard(int cardNum) {
		return userCard(cardNum);
	}


}