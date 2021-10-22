import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.*;

public class UnoController {

	Game game = new Game();
	private List<Player> players = new ArrayList<Player>();
	ArrayList<Button> cardButtons = new ArrayList<Button>();
	private ObservableList<Card> cards = FXCollections.observableArrayList();

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

	@FXML
	private ListView<Card> cardListView;
	
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
	private Button endTurnButton;

	@FXML
	private ImageView playedCardImage;

	@FXML
	private ImageView deckImage;

	@FXML
	private Label player1Label;

	@FXML
	void callUnoButtonPressed(ActionEvent event) {

	}

	@FXML
	void playerCard1ButtonPressed(ActionEvent event) {
		playerCardAction(0);
	}

	@FXML
	void playerCard2ButtonPressed(ActionEvent event) {
		playerCardAction(1);
	}

	@FXML
	void playerCard3ButtonPressed(ActionEvent event) {
		playerCardAction(2);
	}

	@FXML
	void playerCard4ButtonPressed(ActionEvent event) {
		playerCardAction(3);
	}

	@FXML
	void playerCard5ButtonPressed(ActionEvent event) {
		playerCardAction(4);
	}

	@FXML
	void playerCard6ButtonPressed(ActionEvent event) {
		playerCardAction(5);
	}

	@FXML
	void playerCard7ButtonPressed(ActionEvent event) {
		playerCardAction(6);
	}

	@FXML
	void playerCard8ButtonPressed(ActionEvent event) {
		playerCardAction(7);
	}

	@FXML
	void playerCard9ButtonPressed(ActionEvent event) {
		playerCardAction(8);

	}

	@FXML
	void endTurnButtonPressed(ActionEvent event) {

	}

	@FXML
	void drawCardButtonPressed(ActionEvent event) {
		Card card = new Card(null, null);
		if (game.getActingPlayer(0).getPlayerHand().size() >= 9) { // TODO

		}
		game.givePlayerCard(game.getCurrentPlayer(), card); // TODO
		drawPlayerHand();
	}

	public void playerCardAction(int cardNum) {
		game.playCard(0, cardNum); // TODO: fix hardcode
		drawPlayerHand();
		updateDiscardDraw();
		game.playCard(1, cardNum);
		updateDiscardDraw();
	}

	public void updateDiscardDraw() {
		playedCardImage.setImage(game.getLastDiscard());
	}

    public void drawPlayerHand() {
        Player thisPlayer = game.getActingPlayer(0); // TODO: remove hardcode
        int originalHandSize = thisPlayer.getPlayerHand().size();
        List<Card> playerCards = thisPlayer.getPlayerHand();
        for (int i = 0; i < originalHandSize + 1; i++) {
            try {
                Card currentCard = playerCards.get(i);
                cards.add(currentCard);
                cardButtons.get(i).setGraphic(new ImageView(currentCard.getImage(currentCard.getCardColor(), currentCard.getCardType())));
            } catch (IndexOutOfBoundsException e) {
                cardButtons.get(i).setGraphic(new ImageView());
            } catch (Exception e) {
                System.out.println("We caught exception: " + e.toString());
            }
        }
//        cardListView.setItems(cards);
    }

	public void initialize() {
		new Game();
		updateDiscardDraw();
		buttonList();
		drawPlayerHand();
	}
}