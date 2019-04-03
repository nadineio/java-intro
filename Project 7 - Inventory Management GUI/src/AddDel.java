import java.util.logging.*;
import javafx.beans.binding.*;
import javafx.collections.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class AddDel {
    public static void addDel(Stage primaryStage) {
        VBox textFieldsLayout   = new VBox();
        VBox buttonsLayout      = new VBox();
        VBox centeredLayout     = new VBox();
        HBox pageLayout         = new HBox();
        
        primaryStage.setTitle("Add or Delete Item");

        Inventory.nameInput = new TextField();
        Inventory.nameInput.setPromptText("Name");
        Inventory.nameInput.setMinWidth(170);

        Inventory.quantityInput = new TextField();
        Inventory.quantityInput.setPromptText("Quantity");
        Inventory.quantityInput.setMinWidth(170);

        Inventory.notesInput = new TextField();
        Inventory.notesInput.setPromptText("Notes");
        Inventory.notesInput.setMinWidth(170);

        Button addBtn = new Button("Add");
        addBtn.setOnAction(e -> {
            try {
                addBtnClicked(primaryStage);
            } catch (Exception ex) {
            }
        });
        addBtn.setMaxWidth(Double.MAX_VALUE);
        
        Button delBtn = new Button("Delete");
        delBtn.setOnAction(e -> {
            try {
                delBtnClicked();
            } catch (Exception ex) {
            }
        });
        delBtn.setMaxWidth(Double.MAX_VALUE);
        
        Button delAllBtn = new Button("Delete All Items");
        delAllBtn.setOnAction(e -> {
            try {
                delAllBtnClicked();
            } catch (Exception ex) {
                Logger.getLogger(AddDel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        delAllBtn.setMaxWidth(Double.MAX_VALUE);
        
        Button doneBtn = new Button("Done");
        doneBtn.setOnAction(e -> {
            try {
                MenuOptions.toWelcomeScreen(primaryStage);
            } catch (Exception ex) {
                Logger.getLogger(AddDel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        doneBtn.setMaxWidth(100);
        
        BorderPane doneBtnLayout = new BorderPane();
        doneBtnLayout.setCenter(doneBtn);
        
        Label addDelInstructions = new Label("\n\t\tIf you would like to add an "
                + "item, please enter\t\n\t\tits name and quantity before "
                + "clicking \"Add\"\n\n\t\tIf you would like to delete "
                + "an item, please enter\t\n\t\tits code in the Name field "
                + "before clicking \"Delete\"\n  ");
        BorderPane instructionsLayout = new BorderPane();
        instructionsLayout.setBottom(addDelInstructions);

        textFieldsLayout.setPadding(new Insets(10, 10, 10, 10));
        textFieldsLayout.setSpacing(10);
        textFieldsLayout.getChildren().addAll(Inventory.nameInput, Inventory.quantityInput, Inventory.notesInput);
        
        buttonsLayout.setPadding(new Insets(10, 10, 10, 10));
        buttonsLayout.setSpacing(10);
        buttonsLayout.getChildren().addAll(addBtn, delBtn, delAllBtn);
        
        pageLayout.setPadding(new Insets(10, 10, 10, 10));
        pageLayout.setSpacing(20);
        pageLayout.getChildren().addAll(textFieldsLayout, buttonsLayout);
        
        centeredLayout.getChildren().addAll(pageLayout, doneBtnLayout);
        instructionsLayout.setCenter(centeredLayout);
        
        Scene scene = new Scene(instructionsLayout);
        scene.getStylesheets().add(programSkin.class.getResource("programSkin.css").toExternalForm());
        
        scene.setOnKeyPressed((final KeyEvent keyEvent) -> {
            if ((keyEvent.getCode() == KeyCode.ENTER) && !(addBtn.isDisabled())) {
                try {
                    addBtnClicked(primaryStage);
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
        
        primaryStage.setScene(scene);
        primaryStage.show();

        BooleanBinding addDelBindName = Bindings.isEmpty(Inventory.nameInput.textProperty());
        BooleanBinding addBindQuantity = Bindings.isEmpty(Inventory.quantityInput.textProperty());
        
        addBtn.disableProperty().bind(Bindings.or(addDelBindName, addBindQuantity));
        delBtn.disableProperty().bind(addDelBindName);
    }

    public static void addBtnClicked(Stage primaryStage) throws Exception {
        String name, quantity, notes;
        boolean foundItem;
        foundItem = false;
        
        Entry entry = new Entry();
        entry.setName(Inventory.nameInput.getText());
        entry.setQuantity(Inventory.quantityInput.getText());
        
        if (!(Inventory.quantityInput.getText().matches("^[0-9]*$"))) {
            AlertBox.display("Error", "Quantity must be greater than or equal to zero.\n\t\t\tItem not added.");
            addDel(primaryStage);
        } else {
            entry.setNotes(Inventory.notesInput.getText());
            name     = Inventory.nameInput.getText();
            quantity = Inventory.quantityInput.getText();
            notes    = Inventory.notesInput.getText();

            for (int i = 0; i < Entry.numberOfEntries; i++) {
                if (Entry.entryList[i].name.equalsIgnoreCase(name)) {
                    foundItem = true;
                    break;
                }
            }
            if (foundItem == true) {
                AlertBox.display("Alert", "Item with code \"" + name + "\" already exists");
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
                BorderPane buttonsLayout = new BorderPane();
                buttonsLayout.setCenter(okBtn);
                okBtn.setOnAction(e -> {
                    try {
                        MenuOptions.toWelcomeScreen(primaryStage);
                    } catch (Exception ex) {
                        Logger.getLogger(List.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                name = Inventory.nameInput.getText();
                
                foundItem = false;
                for (int i = 0; i < Entry.numberOfEntries; i++) {
                    if (Entry.entryList[i].name.equalsIgnoreCase(name)) {
                        Inventory.tableOfEntries.setItems(getEntry(i));
                        foundItem = true;
                    }
                }
                VBox listLayout = new VBox();
                if (foundItem == false) {
                    AlertBox.display("Item not found",
                            "No item with code \"" + name + "\"");

                } else {
                    listLayout.getChildren().addAll(Inventory.tableOfEntries, buttonsLayout);
                    Scene foundItemPage = new Scene(listLayout, 403, 100);
                    foundItemPage.getStylesheets().add(programSkin.class.getResource("programSkin.css").toExternalForm());
                    primaryStage.setScene(foundItemPage);
                    primaryStage.show();
                }
                Inventory.nameInput.clear();
            } else {
                Entry.entryList[Entry.numberOfEntries] = new Entry(name, quantity, notes);
                AlertBox.display("Alert", "Item added.");
                Entry.numberOfEntries++;
            }
            Inventory.nameInput.clear();
            Inventory.quantityInput.clear();
            Inventory.notesInput.clear();

            FileMgmt.storeInventory(FileMgmt.FILENAME);
        }
    }

    public static void delBtnClicked() throws Exception {
        String name;
        boolean foundItem;
        
        name = Inventory.nameInput.getText();
        foundItem = false;
        
        for (int i = 0; i < Entry.numberOfEntries; i++) {
            if (Entry.entryList[i].name.equalsIgnoreCase(name)) {
                Entry.entryList[i].name = " ".trim();
                Entry.entryList[i].quantity = " ".trim();
                Entry.entryList[i].notes = " ".trim();
                AlertBox.display("Alert", "Item deleted.");
                foundItem = true;
                break;
            }
        }
        if (foundItem == false) {
            AlertBox.display("Item not found", "No item with code " + name);
        }
        Inventory.nameInput.clear();
        Entry.numberOfEntries--;
        FileMgmt.storeInventory(FileMgmt.FILENAME);
    }

    public static void delAllBtnClicked() throws Exception {
        for (int i = 0; i < Entry.numberOfEntries; i++) {
            Entry.entryList[i].name = " ".trim();
            Entry.entryList[i].quantity = " ".trim();
            Entry.entryList[i].notes = " ".trim();
        }
        Entry.numberOfEntries = 0;
        FileMgmt.storeInventory(FileMgmt.FILENAME);
        AlertBox.display("Alert", "All items deleted.");
    }

    public static ObservableList<Entry> getEntry(int index) {
        ObservableList<Entry> entries = FXCollections.observableArrayList();
        entries.addAll(Entry.entryList[index]);

        return entries;
    }
}