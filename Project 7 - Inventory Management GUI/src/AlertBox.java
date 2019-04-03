import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;

public class AlertBox {
    public static void display(String title, String message) {
        Stage primaryStage = new Stage();
        primaryStage.setTitle(title);
        primaryStage.setMinWidth(300);
        primaryStage.setMinHeight(150);

        Label alert = new Label();
        alert.setText(message);
        alert.setFont(Font.font("Verdana", 15));
        alert.setAlignment(Pos.CENTER);
        
        Button closeBtn = new Button("OK");
        closeBtn.setOnAction(e -> primaryStage.close());

        VBox alertLayout = new VBox(20);
        alertLayout.setPadding(new Insets(10, 10, 10, 10));
        alertLayout.getChildren().addAll(alert, closeBtn);
        alertLayout.setAlignment(Pos.CENTER);

        Scene showAlert = new Scene(alertLayout);
        showAlert.getStylesheets().add(programSkin.class.getResource("programSkin.css").toExternalForm());
        showAlert.setOnKeyPressed((final KeyEvent keyEvent) -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                primaryStage.close();
                keyEvent.consume();
        }});
        
        primaryStage.setScene(showAlert);
        primaryStage.showAndWait();
    }
}