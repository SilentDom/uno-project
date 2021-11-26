import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.media.AudioClip;

public class UnoController {

    @FXML
    private ListView<Card> cardListView;

    @FXML
    private TextArea cardTotalField;

    @FXML
    private ListView<Card> opponentCardList;

    @FXML
    private TextArea opponentCardTotal;

    @FXML
    private ImageView playedCardImage;

    @FXML
    private Button callUnoButton;

    @FXML
    private Label player1Label;

    AudioClip playCardSound = new AudioClip(getClass().getResource("/images/playcard.wav").toExternalForm());
    AudioClip drawCardSound = new AudioClip(getClass().getResource("/images/draw.wav").toExternalForm());

    // Instantiates the Game class and passes the UnoController class in for turn updates:
    Game game = new Game(this);

    // MouseEvent that plays the card the player clicked on:
    @FXML
    void cardClicked(MouseEvent event) {
        Player thisPlayer = game.getActingPlayer(0);
        int playerCard = cardListView.getSelectionModel().getSelectedIndex();
        try {
            if (!thisPlayer.getCardPlayed()) {
                if (game.playCard(0, playerCard)) {
                    playCardSound.play();
                    game.endTurn();
                }
            }
        } catch (Exception e) {
            System.out.println("We caught an exception in cardClicked(): " + e.toString());
        }
    }

    // Draws a card when the player clicks on the Draw Card button:
    @FXML
    void drawCardButtonPressed(ActionEvent event) {
        try {
            game.givePlayerCard(game.getActingPlayer(0)); // TODO: fix hardcode
            drawCardSound.play();
            drawPlayerHand();
        } catch (Exception e) {
            System.out.println("We caught an exception in drawCardButtonPressed(): " + e.toString());
        }
    }

    // Displays a message prompt when the user clicks on the Call UNO button.
    // If the player has one card it displays the alert otherwise it adds a card
    // to their hand:
    @FXML
    void callUnoButtonPressed(ActionEvent event) {
        Player thisPlayer = game.getActingPlayer(0);
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

    // Updates the discard pile image to match whatever the last card played was:
    public void updateDiscardDraw() {
        playedCardImage.setImage(game.getLastCardPlayed());
    }

    // "Draws" the player's hand by setting the images in the list view to equal what
    // cards are in the player's hand:
    public void drawPlayerHand() {
        Player thisPlayer = game.getActingPlayer(0);
        ObservableList<Card> cards = FXCollections.observableArrayList();

        try {
            // Adds all the player's cards in their hand to their respective list view:
            cards.addAll(thisPlayer.getPlayerHand());
            cardListView.setItems(cards);
            cardListView.setCellFactory(listView -> new ListCell<Card>() {
                ImageView imageView = new ImageView();

                // Sets the list view to have the images of the cards in the player's hand:
                @Override
                public void updateItem(Card card, boolean empty) {
                    super.updateItem(card, empty);
                    if (empty || card == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        Image image = new Image(card.getImage(card.getCardColor(), card.getCardType()));
                        imageView.setImage(image);
                        setGraphic(imageView);
                    }
                }
            });
        } catch (Exception e) {
            System.out.println("We caught an exception in drawPlayerHand(): " + e.toString());
        }
        // Updates the player's total card # to show how many cards they have:
        cardTotalField.setText("Card Total: " + thisPlayer.getPlayerHand().size());
    }

    // "Draws" the opponent's hand by setting the images in the list view to equal what
    // cards are in the opponent's hand:
    public void drawOpponentHand() {
        Player thisPlayer = game.getActingPlayer(1);
        ObservableList<Card> cards = FXCollections.observableArrayList();

        try {
            // Adds all the opponent's cards in their hand to their respective list view:
            cards.addAll(thisPlayer.getPlayerHand());
            opponentCardList.setItems(cards);
            opponentCardList.setCellFactory(listView -> new ListCell<Card>() {
                ImageView imageView = new ImageView();

                // Sets the list view to have the images of the cards in the opponent's hand:
                @Override
                public void updateItem(Card card, boolean empty) {
                    super.updateItem(card, empty);
                    if (empty || card == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        Image image = new Image("/images/card_back_alt.png");
                        imageView.setImage(image);
                        setGraphic(imageView);
                    }
                }
            });
        } catch (Exception e) {
            System.out.println("We caught an exception in drawOpponentHand(): " + e.toString());
        }
        // Updates the opponent's total card # to show how many cards they have:
        opponentCardTotal.setText("Card Total: " + thisPlayer.getPlayerHand().size());
    }

    // This method performs checks and updates once a player's turn has ended.
    // It re-draws the players' hands, updates the discard pile, and checks to see
    // if either player has won:
    public void endTurnUpdates() {
        drawPlayerHand();
        drawOpponentHand();
        updateDiscardDraw();
        if (game.isGameOver()) {
            int winningPlayer = game.getPlayerTurn();
            Alert playerWon = new Alert(Alert.AlertType.INFORMATION);
            playerWon.setTitle("UNO");
            playerWon.setHeaderText("Game Over!");
            playerWon.setContentText(winningPlayer + " has won!");
            playerWon.showAndWait();
            System.exit(0);
        }
    }

    // Initializes the game, drawing the players' hands and playing one card automatically
    // to start the game:
    public void initialize() {
        updateDiscardDraw();
        drawPlayerHand();
        drawOpponentHand();
    }
}