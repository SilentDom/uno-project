import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.*;

public class UnoController {
	
	Card[] cards = new Card[108];
	Deck deck = new Deck(108);
	Game game = new Game();
	ArrayList<Button> cardButtons = new ArrayList<Button>();
	int players = 1;
	int startingHandSize = 7;
	
	@FXML
	private Button playerCardButton3;

	@FXML
	private Button playerCardButton4;

	@FXML
	private Button playerCardButton5;

	@FXML
	private Button playerCardButton6;

	@FXML
	private Button playerCardButton7;

	@FXML
	private Button playerCardButton8;

	@FXML
	private Button playerCardButton9;

	@FXML
	private Button playerCardButton1;

	@FXML
	private Button playerCardButton2;
	
	@FXML
	private Button callUnoButton;

	@FXML
	private ImageView playedCardImage;

	@FXML
	private ImageView deckImage;
	
    @FXML
    private Label player1Label;
	
	@FXML
	void drawCardButtonPressed(ActionEvent event) {
		if (game.getPlayerHand().size() >= 9) {
			
		}
		Card c = deck.drawCard();
		game.getPlayerHand().add(c);
		drawPlayerHand();
	}

	@FXML
	void callUnoButtonPressed(ActionEvent event) {
		
	}
	
    @FXML
    void playerCard1ButtonPressed(ActionEvent event) {

    }

    @FXML
    void playerCard2ButtonPressed(ActionEvent event) {

    }

    @FXML
    void playerCard3ButtonPressed(ActionEvent event) {

    }

    @FXML
    void playerCard4ButtonPressed(ActionEvent event) {

    }

    @FXML
    void playerCard5ButtonPressed(ActionEvent event) {

    }

    @FXML
    void playerCard6ButtonPressed(ActionEvent event) {

    }

    @FXML
    void playerCard7ButtonPressed(ActionEvent event) {

    }

    @FXML
    void playerCard8ButtonPressed(ActionEvent event) {

    }

    @FXML
    void playerCard9ButtonPressed(ActionEvent event) {

    }
    
    public void buttonList() {
    	cardButtons.add(playerCardButton1);
    	cardButtons.add(playerCardButton2);
    	cardButtons.add(playerCardButton3);
    	cardButtons.add(playerCardButton4);
    	cardButtons.add(playerCardButton5);
    	cardButtons.add(playerCardButton6);
    	cardButtons.add(playerCardButton7);
    	cardButtons.add(playerCardButton8);
    	cardButtons.add(playerCardButton9);
    }
    
    public void setPlayerName() {
    	String currentPlayer = game.getCurrentPlayer();
    	player1Label.setText(currentPlayer + "'s cards");
    }
    
    public void drawPlayerHand() {
        for (int i = 0; i < game.getPlayerHand().size(); i++) {
            Card currentPlayerCard = game.getPlayerCard(i);
            cardButtons.get(i).setGraphic(new ImageView(currentPlayerCard.getImage(currentPlayerCard.getColor(), currentPlayerCard.getType())));
        }
    }
    
    public void dealCards() {
        for (int i = 0; i < startingHandSize; i++) {
            for (int j = 0; j < players ; j++) {
                Card c1 = deck.drawCard();
                game.getPlayerHand().add(c1);
            }
        }
    }
    
    public void playFirstCard() {
		Card c = deck.drawCard();
		while (c.getType() == Card.Type.Wild || c.getType() == Card.Type.WildDrawFour || c.getType() == Card.Type.DrawTwo || c.getColor() == Card.Color.Wild
				|| c.getType() == Card.Type.Skip || c.getType() == Card.Type.Reverse) {
			c = deck.drawCard();
		}
		game.getDiscardPile().add(c);
		Image firstCard = new Image(c.getImage(c.getColor(), c.getType()));
		playedCardImage.setImage(firstCard);
    }

	public void initialize() {
		deck.createDeck();
		deck.shuffleDeck();
		game.startGame(game);	
		playFirstCard();
		buttonList();
		dealCards();
        drawPlayerHand();
	}
}
