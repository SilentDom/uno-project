import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;

public class UnoController {

    Game game = new Game();
    private List<Player> players = new ArrayList<Player>();
    Timer timer = new Timer();
    @FXML
    private ListView<Card> cardListView;

    @FXML
    private Button callUnoButton;

    @FXML
    private ImageView playedCardImage;

    @FXML
    private ImageView deckImage;

    @FXML
    private Label player1Label;

    @FXML
    private TextArea cardTotalField;
    
    @FXML
    private TextField playerTextField;

    @FXML
    void cardClicked(MouseEvent event) {
        Player thisPlayer = game.getActingPlayer(game.getTurn());
        int playerCard = cardListView.getSelectionModel().getSelectedIndex();
        try {
            game.playCard(game.getTurn(), playerCard); // TODO: fix hardcode
            drawPlayerHand();
            updateDiscardDraw();
            if (game.isGameOver()) {
                Alert playerWon = new Alert(Alert.AlertType.INFORMATION);
                playerWon.setTitle("UNO");
                playerWon.setHeaderText("Game Over!");
                playerWon.setContentText(thisPlayer.getPlayerName() + " has won!");
                playerWon.showAndWait();
                System.exit(0);
            }else {
            	game.setTurn(game.nextPlayer(game.getTurn()));
                drawPlayerHand();
                playerTextField.setText(thisPlayer.getPlayerName());
            }
        } catch (Exception e) {
            System.out.println("We caught an exception in cardClicked(): " + e.toString());
        }
        
    }

    @FXML
    void drawCardButtonPressed(ActionEvent event) {
        try {
            game.givePlayerCard(game.getActingPlayer(game.getTurn())); // TODO: fix hardcode
            drawPlayerHand();
        } catch (Exception e) {
            System.out.println("We caught an exception in drawCardButtonPressed(): " + e.toString());
        }
    }

    @FXML
    void callUnoButtonPressed(ActionEvent event) {
        Player thisPlayer = game.getActingPlayer(game.getTurn());
        List<Card> playerCards = thisPlayer.getPlayerHand();

        if (playerCards.size() == 1) {
            Alert unoCalled = new Alert(AlertType.INFORMATION);
            unoCalled.setTitle("UNO");
            unoCalled.setHeaderText("UNO has been called!");
            unoCalled.setContentText(thisPlayer.getPlayerName() + " has called UNO!");
            unoCalled.showAndWait();
        } else {
            game.givePlayerCard(game.getActingPlayer(0)); // TODO: fix hardcode
            drawPlayerHand();
        }
    }

    public void updateDiscardDraw() {
        playedCardImage.setImage(game.getLastDiscard());
    }

    public void drawPlayerHand() {
        Player thisPlayer = game.getActingPlayer(game.getTurn());
        List<Card> cardList = thisPlayer.getPlayerHand();
        ObservableList<Card> cards = FXCollections.observableArrayList();

        try {
            for (Card card : cardList) {
                cards.add(card);
            }
            cardListView.setItems(cards);
            cardListView.setCellFactory(listView -> new ListCell<Card>() {
                ImageView imageView = new ImageView();

                @Override
                public void updateItem(Card card, boolean empty) {
                    super.updateItem(card, empty);
                    if (empty || card == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        Image image = new Image(card.getImage(card.getCardColor(), card.getCardType()));
                        imageView.setImage(image);
                        setText(card.toString());
                        setGraphic(imageView);
                    }
                }
            });
        } catch (Exception e) {
            System.out.println("We caught an exception in drawPlayerHand(): " + e.toString());
        }
        cardTotalField.setText("Card Total: " + thisPlayer.getPlayerHand().size());
    }

    public boolean isPlayable(Card currentCard) {
    	Card discardCard = game.getDiscardPile().get(game.getDiscardPile().size());
    	if (currentCard.getCardType() == discardCard.getCardType()
				|| currentCard.getCardColor() == discardCard.getCardColor()
				|| currentCard.getCardType() == CardType.Wild
				|| currentCard.getCardType() == CardType.WildDrawFour
				|| discardCard.getCardType() == CardType.Wild	// TODO: temporary until wild color choosing
				|| discardCard.getCardType() == CardType.WildDrawFour) {
    		return true;
    	}else return false;
    	
    }

    public void initialize() {
        new Game();
        cardListView.setOrientation(Orientation.HORIZONTAL);
        updateDiscardDraw();
        drawPlayerHand();

    }
}