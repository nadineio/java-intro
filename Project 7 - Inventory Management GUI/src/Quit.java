import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Quit {
    public static void quit(Stage primaryStage) {
        String byeMessage;
        VBox byeLayout = new VBox();
        Scene quitPage = new Scene(byeLayout);
        quitPage.getStylesheets().add(programSkin.class.getResource("programSkin.css").toExternalForm());
        
        primaryStage.setTitle("See you soon!");

        if (Inventory.nameOfUser == null) {
            byeMessage = "Goodbye!";
        } else {
            byeMessage = "Goodbye, " + Inventory.nameOfUser + "!";
        }
        Label goodbye = new Label(byeMessage);
	goodbye.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        
        byeLayout.getChildren().add(goodbye);
        byeLayout.setAlignment(Pos.CENTER);
        
        primaryStage.setScene(quitPage);
        primaryStage.show();
        
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
	delay.setOnFinished(e -> Platform.exit());
        delay.play();
    }
}