import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.*;

public class UnoController {

    Game game = new Game();
    ArrayList<Button> cardButtons = new ArrayList<Button>();
    int playerCount = 1;

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
        if (game.getPlayerHand().size() >= 9) {

        }
        game.givePlayerCard();
        drawPlayerHand();
    }
    
    private void playerCardAction(int cardNum) {
        game.playCard(cardNum);
        drawPlayerHand();
        updateDiscardDraw();
    }
    
    private void updateDiscardDraw() {
        playedCardImage.setImage(game.getLastDiscard());
    }
    
    public void drawPlayerHand() {
        for (int i = 0; i < game.getMaxHandSize(); i++) {
            try {
                Card currentPlayerCard = game.getPlayerCard(i);
                cardButtons.get(i).setGraphic(new ImageView(currentPlayerCard.getImage(currentPlayerCard.getColor(), currentPlayerCard.getType())));
            } catch (IndexOutOfBoundsException e) {
                cardButtons.get(i).setGraphic(new ImageView());
            } catch (Exception e) {
                System.out.println("We botched something");
            }

        }
    }

    public void initialize() {
        game.startGame(game, playerCount);
        updateDiscardDraw();
        buttonList();
        drawPlayerHand();
    }
}