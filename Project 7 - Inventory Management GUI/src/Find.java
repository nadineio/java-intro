import java.util.logging.*;
import javafx.beans.binding.*;
import javafx.collections.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Find {
    public static String name, quantity, notes;
    
    public static void find(Stage primaryStage) {
        primaryStage.setTitle("Find Item");
        
        Inventory.nameInput = new TextField();
        Inventory.nameInput.setPromptText("Name");
        Inventory.nameInput.setMinWidth(100);

        Button findBtn = new Button("Find Item");
        findBtn.setOnAction(e -> findBtnClicked(primaryStage));
        Button doneBtn = new Button("Done");
        doneBtn.setOnAction(e -> {
            try {
                MenuOptions.toWelcomeScreen(primaryStage);
            } catch (Exception ex) {
                Logger.getLogger(Find.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        HBox buttonsLayout = new HBox();
        buttonsLayout.setPadding(new Insets(110, 10, 50, 10));
        buttonsLayout.setSpacing(10);
        buttonsLayout.getChildren().addAll(Inventory.nameInput, findBtn, doneBtn);

        Scene findPage = new Scene(buttonsLayout);
        findPage.getStylesheets().add(programSkin.class.getResource("programSkin.css").toExternalForm());
        findPage.setOnKeyPressed((final KeyEvent keyEvent) -> {
            if ((keyEvent.getCode() == KeyCode.ENTER) && !(findBtn.isDisabled())) {
                findBtnClicked(primaryStage);
                keyEvent.consume();
            } else if (keyEvent.getCode() == KeyCode.ESCAPE) {
                try {
                    MenuOptions.toWelcomeScreen(primaryStage);
                } catch (Exception ex) {
                    Logger.getLogger(AddDel.class.getName()).log(Level.SEVERE, null, ex);
                }
                keyEvent.consume();
            }
        });
        
        primaryStage.setScene(findPage);
        primaryStage.show();
        
        BooleanBinding findBindName = Bindings.isEmpty(Inventory.nameInput.textProperty());
        findBtn.disableProperty().bind(findBindName);
    }

    public static void findBtnClicked(Stage primaryStage) {
        boolean foundItem;
        
        Inventory.tableOfEntries = new TableView<>();
        TableColumn<Entry, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Entry, String> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(100);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<Entry, String> notesColumn = new TableColumn<>("Notes");
        notesColumn.setMinWidth(200);
        notesColumn.setCellValueFactory(new PropertyValueFactory<>("notes"));
        
        Inventory.tableOfEntries.getColumns().addAll(nameColumn, quantityColumn, notesColumn);
        
        Button okBtn = new Button("OK");
        okBtn.setOnAction(e -> {
            try {
                MenuOptions.toWelcomeScreen(primaryStage);
            } catch (Exception ex) {
                Logger.getLogger(List.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        BorderPane buttonsLayout = new BorderPane();
        buttonsLayout.setCenter(okBtn);
        
        ObservableList data = FXCollections.observableArrayList();
        Inventory.tableOfEntries.setItems(data);
        
        name = Inventory.nameInput.getText();
        foundItem = false;
        for (int i = 0; i < Entry.numberOfEntries; i++) {
            Entry entry = new Entry();
            if (Entry.entryList[i].name.contains(name)) {
                entry.name = Entry.entryList[i].name;
                entry.quantity = Entry.entryList[i].quantity;
                entry.notes = Entry.entryList[i].notes;
                data.add(entry);
                foundItem = true;
            }
        }
        if (foundItem == false) {
            AlertBox.display("Item not found", 
                             "No item with code \"" + name + "\"");
        } else {
            VBox listLayout = new VBox();
            listLayout.getChildren().addAll(Inventory.tableOfEntries, buttonsLayout);
            
            Scene foundItemPage = new Scene(listLayout, 403, 100);
            foundItemPage.getStylesheets().add(programSkin.class.getResource("programSkin.css").toExternalForm());
            foundItemPage.setOnKeyPressed((final KeyEvent keyEvent) -> {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    try {
                        MenuOptions.toWelcomeScreen(primaryStage);
                    } catch (Exception ex) {
                        Logger.getLogger(AddDel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    keyEvent.consume();
                }
            });
            primaryStage.setScene(foundItemPage);
            primaryStage.show();
        }
        Inventory.nameInput.clear();
    }
}