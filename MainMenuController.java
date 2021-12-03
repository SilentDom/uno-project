import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainMenuController {

    @FXML
    private Button exitButton;

    @FXML
    private Button rulesButton;

    @FXML
    private Button startGameButton;

    @FXML
    void startGameButtonPressed(ActionEvent event) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("UnoGame.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("UNO");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("Exception caught in starting the game from the main menu: " + e.toString());
            e.printStackTrace();
        }
    }

    @FXML
    void rulesButtonPressed(ActionEvent event) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("Rules.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("UNO Rules");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("Exception caught in starting the game from the main menu: " + e.toString());
        }
    }

    @FXML
    void exitButtonPressed(ActionEvent event) {
        System.exit(0);
    }
}
