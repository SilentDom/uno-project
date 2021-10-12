import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.*;

public class UnoController {

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
    private Button deckButton;

    @FXML
    private Button playedCardButton;
    
    @FXML
    void drawCardButtonPressed(ActionEvent event) {

    }
    
    @FXML
    void callUnoButtonPressed(ActionEvent event) {

    }
 
    public void initialize() {
    	playerCardButton1.setGraphic(new ImageView("images/uno_g1.png"));
    }
    
}
