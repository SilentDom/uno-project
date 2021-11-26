import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WildColor {
    static String choice;

    public static void display() {
        System.out.println("Creating dialog");

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        Button green = new Button("Green");
        Button blue = new Button("Blue");
        Button red = new Button("Red");
        Button yellow = new Button("Yellow");
        green.setOnAction(e -> {
            choice = "green";
            System.out.println("choice: " + choice);
            stage.close();
        });
        blue.setOnAction(e -> {
            choice = "blue";
            System.out.println("choice: " + choice);
            stage.close();
        });
        red.setOnAction(e -> {
            choice = "red";
            System.out.println("choice: " + choice);
            stage.close();
        });
        yellow.setOnAction(e -> {
            choice = "yellow";
            System.out.println("choice: " + choice);
            stage.close();
        });

        green.setStyle("-fx-background-color:#00FF00;");
        green.setPrefSize(100, 100);
        red.setPrefSize(100, 100);
        blue.setPrefSize(100, 100);
        yellow.setPrefSize(100, 100);
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setVgap(5);
        layout.setHgap(5);
        layout.add(green, 0, 0);
        layout.add(blue, 1, 0);
        layout.add(red, 0, 1);
        layout.add(yellow, 1, 1);

        Scene scene = new Scene(layout, 200, 200);
        stage.setTitle("UNO");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.showAndWait();
    }
}