import java.util.logging.*;
import javafx.collections.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class List {
    public static void list(Stage primaryStage) {
        primaryStage.setTitle("List Items");

        TableColumn<Entry, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Entry, String> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(100);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<Entry, String> notesColumn = new TableColumn<>("Notes");
        notesColumn.setMinWidth(200);
        notesColumn.setCellValueFactory(new PropertyValueFactory<>("notes"));

        Inventory.tableOfEntries = new TableView<>();
        Inventory.tableOfEntries.setItems(getEntry());
        Inventory.tableOfEntries.getColumns().addAll(nameColumn, quantityColumn, notesColumn);

        Button doneBtn = new Button("Done");
        doneBtn.setOnAction(e -> {
            try {
                MenuOptions.toWelcomeScreen(primaryStage);
            } catch (Exception ex) {
                Logger.getLogger(List.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        BorderPane buttonLayout = new BorderPane();
        buttonLayout.setCenter(doneBtn);
        
        VBox pageLayout = new VBox();
        pageLayout.getChildren().addAll(Inventory.tableOfEntries, buttonLayout);

        Scene listPage = new Scene(pageLayout);
        listPage.getStylesheets().add(programSkin.class.getResource("programSkin.css").toExternalForm());
        listPage.setOnKeyPressed((final KeyEvent keyEvent) -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                try {
                    MenuOptions.toWelcomeScreen(primaryStage);
                } catch (Exception ex) {
                    Logger.getLogger(AddDel.class.getName()).log(Level.SEVERE, null, ex);
                }
                keyEvent.consume();
            }
        });
        
        primaryStage.setScene(listPage);
        primaryStage.show();
    }
    
    public static ObservableList<Entry> getEntry() {
        ObservableList<Entry> entries = FXCollections.observableArrayList();
        for (int i = 0; i < Entry.numberOfEntries; i++)
            entries.addAll(Entry.entryList[i]);
        return entries;
    }
}