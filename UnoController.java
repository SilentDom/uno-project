import java.awt.Font;

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

	@FXML
	void drawCardButtonPressed(ActionEvent event) {
//		deck.drawCard();
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
		playerCardButton1.setGraphic(new ImageView("/images/uno_g0.png"));
		playerCardButton2.setGraphic(new ImageView("/images/uno_r1.png"));
		playerCardButton3.setGraphic(new ImageView("/images/uno_b2.png"));
		playerCardButton4.setGraphic(new ImageView("/images/uno_y3.png"));
		playerCardButton5.setGraphic(new ImageView("/images/uno_gReverse.png"));
		playerCardButton6.setGraphic(new ImageView("/images/uno_rSkip.png"));
		playerCardButton7.setGraphic(new ImageView("/images/uno_bDrawTwo.png"));
		playerCardButton8.setGraphic(new ImageView("/images/uno_wildDrawFour.png"));
		playerCardButton9.setGraphic(new ImageView("/images/uno_g1.png"));
	}

}
