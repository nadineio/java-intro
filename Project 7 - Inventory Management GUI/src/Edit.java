import java.util.logging.*;
import javafx.beans.binding.*;
import javafx.collections.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Edit {
    public static String name, quantity, notes;
    public static int index;
    
    public static void edit(Stage primaryStage) {
        primaryStage.setTitle("Edit Item");
        
        Inventory.nameInput = new TextField();
        Inventory.nameInput.setPromptText("Name of Item to be Edited");
        Inventory.nameInput.setMinWidth(170);

        Button editBtn = new Button("Edit Item");
        editBtn.setOnAction(e -> editBtnClicked(primaryStage));
        
        Button cancelBtn = new Button("Cancel");
        cancelBtn.setOnAction(e -> {
            try {
                MenuOptions.toWelcomeScreen(primaryStage);
            } catch (Exception ex) {
                Logger.getLogger(Find.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        HBox buttonsLayout = new HBox();
        buttonsLayout.setPadding(new Insets(110, 10, 50, 10));
        buttonsLayout.setSpacing(10);
        buttonsLayout.getChildren().addAll(Inventory.nameInput, editBtn, cancelBtn);

        Scene editPage = new Scene(buttonsLayout);
        editPage.getStylesheets().add(programSkin.class.getResource("programSkin.css").toExternalForm());
        editPage.setOnKeyPressed((final KeyEvent keyEvent) -> {
            if ((keyEvent.getCode() == KeyCode.ENTER) && !(editBtn.isDisabled())) {
                try {
                    editBtnClicked(primaryStage);
                } catch (Exception ex) {
                    Logger.getLogger(AddDel.class.getName()).log(Level.SEVERE, null, ex);
                }
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
        primaryStage.setScene(editPage);
        primaryStage.show();

        BooleanBinding findBindName = Bindings.isEmpty(Inventory.nameInput.textProperty());
        editBtn.disableProperty().bind(findBindName);
    }
    
    public static void editBtnClicked(Stage primaryStage) {
        boolean foundItem;
        name = Inventory.nameInput.getText();
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
        
        Inventory.nameInput = new TextField();
        Inventory.nameInput.setPromptText("New name");
        Inventory.nameInput.setMinWidth(70);
        Inventory.nameInput.setMaxWidth(80);
        
        Inventory.quantityInput = new TextField();
        Inventory.quantityInput.setPromptText("New quantity");
        Inventory.quantityInput.setMinWidth(70);
        Inventory.quantityInput.setMaxWidth(90);
        
        Inventory.notesInput = new TextField();
        Inventory.notesInput.setPromptText("New notes");
        Inventory.notesInput.setMinWidth(70);
        Inventory.notesInput.setMaxWidth(80);
        
        Button updateBtn = new Button("Update");
        updateBtn.setOnAction(e -> {
            try {
                updateBtnClicked(primaryStage);
            } catch (Exception ex) {
                Logger.getLogger(Edit.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        Button cancelBtn = new Button("Cancel");
        cancelBtn.setOnAction(e -> edit(primaryStage));
        
        HBox buttonsLayout = new HBox();
        buttonsLayout.setPadding(new Insets(10, 10, 10, 10));
        buttonsLayout.setSpacing(6);
        buttonsLayout.getChildren().addAll(Inventory.nameInput, Inventory.quantityInput, Inventory.notesInput, updateBtn, cancelBtn);
        
        foundItem = false;
        for (int i = 0; i < Entry.numberOfEntries; i++) {
            if (Entry.entryList[i].name.equalsIgnoreCase(name)) {
                index = i;
                Inventory.tableOfEntries.setItems(getEntry());
                foundItem = true;
                
            }
        }
        if (foundItem == false) {
            AlertBox.display("Item not found",
                    "No item with code \"" + name + "\"");
            Inventory.nameInput.clear();
            edit(primaryStage);
        } else {
            VBox listLayout = new VBox();
            listLayout.getChildren().addAll(Inventory.tableOfEntries, buttonsLayout);
            
            Scene foundItemPage = new Scene(listLayout, 403, 100);
            foundItemPage.getStylesheets().add(programSkin.class.getResource("programSkin.css").toExternalForm());
            foundItemPage.setOnKeyPressed((final KeyEvent keyEvent) -> {
                if ((keyEvent.getCode() == KeyCode.ENTER) && !(updateBtn.isDisabled())) {
                    try {
                        updateBtnClicked(primaryStage);
                    } catch (Exception ex) {
                        Logger.getLogger(AddDel.class.getName()).log(Level.SEVERE, null, ex);
                    }
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
            
            primaryStage.setScene(foundItemPage);
            primaryStage.show();
        }
        Inventory.nameInput.clear();
        
        BooleanBinding updateBindName = Bindings.isEmpty(Inventory.nameInput.textProperty());
        BooleanBinding updateBindQuantity = Bindings.isEmpty(Inventory.quantityInput.textProperty());
        
        updateBtn.disableProperty().bind(Bindings.or(updateBindName, updateBindQuantity));
    }

    public static ObservableList<Entry> getEntry() {
        ObservableList<Entry> entries = FXCollections.observableArrayList();
        entries.addAll(Entry.entryList[index]);

        return entries;
    }
    
    public static void updateBtnClicked(Stage primaryStage) throws Exception {
        boolean foundItem;
        foundItem = false;
        name = Inventory.nameInput.getText();
        quantity = Inventory.quantityInput.getText();
        
        for (int i = 0; i < Entry.numberOfEntries; i++) {
            if ((Entry.entryList[i].name.equalsIgnoreCase(name)) && !(Entry.entryList[i].name.equalsIgnoreCase(Entry.entryList[index].name))) {
                foundItem = true;
                break;
            }
        }
        
        if (!(Inventory.quantityInput.getText().matches("^[0-9]*$"))) {
            AlertBox.display("Error", "Quantity must be greater than or equal to zero.\n\t\t\tItem not edited.");
        } else if (foundItem == true) {
            AlertBox.display("Alert", "Item with code \"" + name + "\" already exists.\n\t\tItem not edited.");
        } else {
            notes = Inventory.notesInput.getText();

            Entry.entryList[index].name = name;
            Entry.entryList[index].quantity = quantity;
            Entry.entryList[index].notes = notes;
            FileMgmt.storeInventory(FileMgmt.FILENAME);

            AlertBox.display("Update Successful", "Item has been edited.");

            Inventory.nameInput.clear();
            Inventory.quantityInput.clear();
            Inventory.notesInput.clear();
            Inventory.welcomeScreen(primaryStage);
        }
        primaryStage.show();
    }
}
