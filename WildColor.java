import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WildColor {
    private static CardColor wildCardColor;

    public static CardColor display() {
        Stage window = new Stage();

        // instantiate button names
        Button green = new Button("Green");
        Button blue = new Button("Blue");
        Button red = new Button("Red");
        Button yellow = new Button("Yellow");

        // card will be of type Wild by default, these will change the color
        green.setOnAction(e -> {
            wildCardColor = CardColor.Green;
            window.close();
        });
        blue.setOnAction(e -> {
            wildCardColor = CardColor.Blue;
            window.close();
        });
        red.setOnAction(e -> {
            wildCardColor = CardColor.Red;
            window.close();
        });
        yellow.setOnAction(e -> {
            wildCardColor = CardColor.Yellow;
            window.close();
        });

        // buttons 100x100px in size
        green.setPrefSize(100, 100);
        blue.setPrefSize(100, 100);
        red.setPrefSize(100, 100);
        yellow.setPrefSize(100, 100);

        // background colors of the buttons
        green.setStyle("-fx-background-color: #32e39b;");
        blue.setStyle("-fx-background-color: #00c3e5;");
        red.setStyle("-fx-background-color: #f56462;");
        yellow.setStyle("-fx-background-color: #f7e359;");

        GridPane layout = new GridPane();

        // 10px margin from edges, 5px gap between buttons
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setVgap(5);
        layout.setHgap(5);

        // add colors in order: top left, top right, bottom left, bottom right
        layout.add(green, 0, 0);
        layout.add(blue, 1, 0);
        layout.add(red, 0, 1);
        layout.add(yellow, 1, 1);

        Scene scene = new Scene(layout, 225, 225);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Choose Wild Color");
        window.setResizable(false);
        window.setScene(scene);
        window.showAndWait();
        return wildCardColor;
    }

}
