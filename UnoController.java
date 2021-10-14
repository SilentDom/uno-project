import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.*;

public class UnoController {

//	Deck deck = new Deck();
//	Game game = new Game(null);
	
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
	private Button callUnoButton;

	@FXML
	private Button playerCardButton8;

	@FXML
	private Button playerCardButton9;

	@FXML
	private Button playerCardButton1;

	@FXML
	private Button playerCardButton2;

	@FXML
	private Button drawCardButton;

	@FXML
	private ImageView playedCardImage;

	@FXML
	private ImageView deckImage;
	
	Card[] cards = new Card[108];
	Deck deck = new Deck(cards, 0);
	@FXML
	void drawCardButtonPressed(ActionEvent event) {
		deck.shuffleDeck();
		Card c = deck.drawCard();
		playerCardButton1.setGraphic( new ImageView(c.getImage(c.getColor(), c.getType())));
	}

	@FXML
	void callUnoButtonPressed(ActionEvent event) {
//		if (game.getPlayerCardAmount(game.getCurrentPlayer()) == 1) {
//			JLabel message = new JLabel(game.getCurrentPlayer()+ " declared UNO!");
//			message.setFont(new Font("Arial", Font.BOLD, 48));
//		} else {
//			throw new IllegalArgumentException("You cannot declare UNO with more than 1 card in your hand.");
//		}
	}

	public void initialize() {
		deck.createDeck();
		/*playerCardButton1.setGraphic(new ImageView("/images/Green_Zero.png"));
		playerCardButton2.setGraphic(new ImageView("/images/Red_One.png"));
		playerCardButton3.setGraphic(new ImageView("/images/Blue_Two.png"));
		playerCardButton4.setGraphic(new ImageView("/images/Yellow_Three.png"));
		playerCardButton5.setGraphic(new ImageView("/images/Green_Reverse.png"));
		playerCardButton6.setGraphic(new ImageView("/images/Red_Skip.png"));
		playerCardButton7.setGraphic(new ImageView("/images/Blue_DrawTwo.png"));*/
	}

}
