
public class Computer extends Player {
	private Game game;
	private Deck deck;

	public Computer(String playerName) {
		super(playerName);

	}

	public void playComputerCard (int cardNum) {
		Card currentPlayerCard = getPlayerCard(cardNum);
		for(int i = 0; i < getPlayerHand().size(); i++){
			if (isPlayable(currentPlayerCard)){
				getPlayerHand().remove(cardNum); 
			}
		}
		Card newCard = deck.getDeck().remove(0);
		while (!isPlayable(newCard)) {
			newCard = deck.getDeck().remove(0);
			if (isPlayable(newCard)) {
				getPlayerHand().remove(newCard);
				getGame(game).getDiscardPile().add(newCard);
			} else { 
				getPlayerHand().add(newCard);
			}
		}
	}

	//	public Card computerCard (int cardNum) {
	//		Card currentPlayerCard = getPlayerCard(cardNum);
	//		Card discardCard = getGame(game).getDiscardPile().get(getGame(game).getDiscardPile().size()-1);
	//		for(int i = 0; i < getPlayerHand().size(); i++){
	//			if (isPlayable(currentPlayerCard)){
	//				getPlayerHand().remove(cardNum); 
	//				return currentPlayerCard;
	//			}
	//		}
	//		Card newCard;
	//		do {
	//			newCard = deck.getDeck().remove(0);
	//			if(isPlayable(newCard)) return newCard;
	//			else getPlayerHand().add(newCard);
	//		} while(isPlayable(newCard)==false);
	//	}

	public boolean isPlayable (Card currentPlayerCard) {
		Card discardCard = getGame(game).getDiscardPile().get(getGame(game).getDiscardPile().size()-1);
		if (currentPlayerCard.getCardType() == discardCard.getCardType()
				|| currentPlayerCard.getCardColor() == discardCard.getCardColor()
				|| currentPlayerCard.getCardType() == CardType.Wild
				|| currentPlayerCard.getCardType() == CardType.WildDrawFour
				|| discardCard.getCardType() == CardType.Wild	// temporary until wild color choosing
				|| discardCard.getCardType() == CardType.WildDrawFour) {
			return true;
		}
		else {
			return false;
		}
	}
}