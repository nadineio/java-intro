import java.io.IOException;
import java.util.Date;
import java.util.logging.*;
import javafx.application.*;
import javafx.beans.binding.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.*;

public class Inventory extends Application {
    /** Written by: Eyad Elissa and Nadine Mansour (Project 7)
     * This program creates a JavaFX GUI to help users manage inventories
     */
    
    public static String nameOfUser;
    public static TextField username, nameInput, quantityInput, notesInput;
    public static TableView<Entry> tableOfEntries = new TableView<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Login");
        primaryStage.setMinWidth(500);
        primaryStage.setMinHeight(300);
        
        HBox buttonsLayout = new HBox();
        buttonsLayout.setAlignment(Pos.CENTER);
        
        VBox labelsLayout = new VBox();
        labelsLayout.setAlignment(Pos.CENTER);
        
        Date currentDateTime = new Date();
        Label dateTime = new Label(currentDateTime.toString());
        dateTime.setFont(Font.font("Bookman Old Style"));
                
        Label enterNamePass = new Label("Please Login");
        enterNamePass.setFont(Font.font("Bookman Old Style", FontWeight.BLACK, 20));
               
        username = new TextField();
        username.setPromptText("Username");
        username.setMinWidth(100);
        username.setMaxWidth(200);

        PasswordField password = new PasswordField();
        password.setPromptText("Password");
        password.setMinWidth(100);
        password.setMaxWidth(200);
        
        Button loginBtn = new Button("Login");
        loginBtn.setOnAction(e -> {
            try {
                MenuOptions.toWelcomeScreen(primaryStage);
            } catch (Exception ex) {
                Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        Button quitBtn = new Button("Quit");
        quitBtn.setOnAction(e -> Quit.quit(primaryStage));
        
        buttonsLayout.setPadding(new Insets(10, 10, 10, 10));
        buttonsLayout.setSpacing(10);
        buttonsLayout.getChildren().addAll(loginBtn, quitBtn);
        
        labelsLayout.setPadding(new Insets(80, 10, 50, 10));
        labelsLayout.setSpacing(10);
        labelsLayout.getChildren().addAll(dateTime, enterNamePass, username, password, buttonsLayout);
        
        Scene loginPage = new Scene(labelsLayout);
        loginPage.getStylesheets().add(programSkin.class.getResource("programSkin.css").toExternalForm());
        loginPage.setOnKeyPressed((final KeyEvent keyEvent) -> {
            if ((keyEvent.getCode() == KeyCode.ENTER) && !(loginBtn.isDisabled())) {
                try {
                    MenuOptions.toWelcomeScreen(primaryStage);
                } catch (Exception ex) {
                    Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
                }
                keyEvent.consume();
            } else if (keyEvent.getCode() == KeyCode.ESCAPE) {
                Quit.quit(primaryStage);
                keyEvent.consume();
            }
        });
        
        primaryStage.setScene(loginPage);
        primaryStage.show();
        
        BooleanBinding loginBindName = Bindings.isEmpty(username.textProperty());
        BooleanBinding loginBindPassword = Bindings.isEmpty(password.textProperty());
        loginBtn.disableProperty().bind(Bindings.or(loginBindName, loginBindPassword));
    }

    public static void welcomeScreen(Stage primaryStage) throws Exception {
        nameOfUser = username.getText();
        primaryStage.setTitle("Inventory Management");
        primaryStage.setMinWidth(300);
        primaryStage.setMinHeight(300);

        Menu fileMenu = new Menu("File");
        Menu editMenu = new Menu("Edit");
        
        MenuItem about = new MenuItem("About");
        about.setOnAction(e -> MenuOptions.aboutMenu(primaryStage));
        about.setAccelerator(new KeyCodeCombination(KeyCode.B, KeyCombination.CONTROL_DOWN));
        
        MenuItem help = new MenuItem("Help");
        help.setOnAction(e -> MenuOptions.helpMenu(primaryStage));
        help.setAccelerator(new KeyCodeCombination(KeyCode.H, KeyCombination.CONTROL_DOWN));
        
        MenuItem logout = new MenuItem("Logout");
        logout.setOnAction(e -> MenuOptions.logout(primaryStage));
        logout.setAccelerator(new KeyCodeCombination(KeyCode.ESCAPE));
        
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(e -> Quit.quit(primaryStage));
        
        fileMenu.getItems().addAll(help, about, new SeparatorMenuItem(), logout,  new SeparatorMenuItem(), exit);
        
        MenuItem info = new MenuItem("File Information");
        info.setOnAction(e -> {
            try {
                MenuOptions.fileInfo(primaryStage);
            } catch (Exception ex) {
                Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        info.setAccelerator(new KeyCodeCombination(KeyCode.I, KeyCombination.CONTROL_DOWN));
        MenuItem newFile = new MenuItem("Create/Open a New File");
        newFile.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));
        Runtime rs = Runtime.getRuntime();
        newFile.setOnAction(e -> {
            try {
                rs.exec("notepad");
            } catch (IOException ex) {
                Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        MenuItem renameFile = new MenuItem("Rename File");
        renameFile.setOnAction(e -> {
            try {
                FileMgmt.renameFile(primaryStage);
            } catch (Exception ex) {
                Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        renameFile.setAccelerator(new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN));
        
        Menu mangInv = new Menu("Manage Inventory");
        
        MenuItem addItem = new MenuItem("Add Item");
        addItem.setOnAction(e -> AddDel.addDel(primaryStage));
        addItem.setAccelerator(new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN));
        
        MenuItem editItem = new MenuItem("Edit Item");
        editItem.setOnAction(e -> Edit.edit(primaryStage));
        editItem.setAccelerator(new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN));
        
        MenuItem delItem = new MenuItem("Delete Item");
        delItem.setOnAction(e -> AddDel.addDel(primaryStage));
        delItem.setAccelerator(new KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_DOWN));
        
        MenuItem findItem = new MenuItem("Find Item");
        findItem.setOnAction(e -> Find.find(primaryStage));
        findItem.setAccelerator(new KeyCodeCombination(KeyCode.F, KeyCombination.CONTROL_DOWN));
        
        MenuItem listItems = new MenuItem("List Items");
        listItems.setOnAction(e -> List.list(primaryStage));
        listItems.setAccelerator(new KeyCodeCombination(KeyCode.L, KeyCombination.CONTROL_DOWN));
        
        mangInv.getItems().addAll(addItem, editItem, delItem, findItem, listItems);
        editMenu.getItems().addAll(mangInv, new SeparatorMenuItem(), info, renameFile, newFile);
        
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, editMenu);
        
        BorderPane menuLayout = new BorderPane();
        menuLayout.setTop(menuBar);

        Label welcomeLbl = new Label("Welcome, " + nameOfUser + "!");
        welcomeLbl.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
                
        Button addDelBtn = new Button("Add or Delete Item");
        addDelBtn.setOnAction(e -> AddDel.addDel(primaryStage));
        addDelBtn.setMaxWidth(Double.MAX_VALUE);
        addDelBtn.setMaxHeight(Double.MAX_VALUE);
        
        Button listBtn = new Button("List All Items");
        listBtn.setOnAction(e -> List.list(primaryStage));
        listBtn.setMaxWidth(Double.MAX_VALUE);
        listBtn.setMaxHeight(Double.MAX_VALUE);
        
        Button editBtn = new Button("Edit Item");
        editBtn.setOnAction(e -> Edit.edit(primaryStage));
        editBtn.setMaxWidth(Double.MAX_VALUE);
        editBtn.setMaxHeight(Double.MAX_VALUE);
        
        Button findBtn = new Button("Find Item");
        findBtn.setOnAction(e -> Find.find(primaryStage));
        findBtn.setMaxWidth(Double.MAX_VALUE);
        findBtn.setMaxHeight(Double.MAX_VALUE);
        
        Button quitBtn = new Button("Quit");
        quitBtn.setOnAction(e -> Quit.quit(primaryStage));
        quitBtn.setMaxWidth(Double.MAX_VALUE);
        quitBtn.setMaxHeight(Double.MAX_VALUE);
        
        VBox buttonsLayout = new VBox(addDelBtn, editBtn, findBtn, listBtn, quitBtn);
        buttonsLayout.setSpacing(5);
        buttonsLayout.setPadding(new Insets(10, 10, 10, 10));

        VBox buttonsLabelLayout = new VBox(welcomeLbl, buttonsLayout);
        buttonsLabelLayout.setPadding(new Insets(40, 10, 10, 10));
        buttonsLabelLayout.setSpacing(10);
        buttonsLabelLayout.setAlignment(Pos.CENTER);
        
        menuLayout.setCenter(buttonsLabelLayout);
        Scene welcomePage = new Scene(menuLayout, 300, 300);
        welcomePage.getStylesheets().add(programSkin.class.getResource("programSkin.css").toExternalForm());
               
        welcomePage.setOnKeyPressed((final KeyEvent keyEvent) -> {
            final KeyCombination CTRLA = new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN);
            final KeyCombination CTRLD = new KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_DOWN);
            final KeyCombination CTRLE = new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN);
            final KeyCombination CTRLL = new KeyCodeCombination(KeyCode.L, KeyCombination.CONTROL_DOWN);
            final KeyCombination CTRLF = new KeyCodeCombination(KeyCode.F, KeyCombination.CONTROL_DOWN);
            final KeyCombination CTRLI = new KeyCodeCombination(KeyCode.I, KeyCombination.CONTROL_DOWN);
            final KeyCombination CTRLH = new KeyCodeCombination(KeyCode.H, KeyCombination.CONTROL_DOWN);
            final KeyCombination CTRLN = new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN);
            final KeyCombination CTRLR = new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN);
            final KeyCombination CTRLB = new KeyCodeCombination(KeyCode.B, KeyCombination.CONTROL_DOWN);
            
            if ((CTRLA.match(keyEvent)) || (CTRLD.match(keyEvent))) {
                AddDel.addDel(primaryStage);
                keyEvent.consume();
            } else if (CTRLE.match(keyEvent)) {
                Edit.edit(primaryStage);
                keyEvent.consume();
            } else if (CTRLR.match(keyEvent)) {
                try {
                    FileMgmt.renameFile(primaryStage);
                } catch (Exception ex) {
                    Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
                }
                keyEvent.consume();
            } else if (CTRLI.match(keyEvent)) {
                try {
                    MenuOptions.fileInfo(primaryStage);
                } catch (Exception ex) {
                    Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
                }
                keyEvent.consume();
            } else if (CTRLH.match(keyEvent)) {
                MenuOptions.helpMenu(primaryStage);
                keyEvent.consume();
            } else if (CTRLB.match(keyEvent)) {
                MenuOptions.aboutMenu(primaryStage);
                keyEvent.consume();
            } else if (CTRLN.match(keyEvent)) {
                try {
                    rs.exec("notepad");
                } catch (IOException ex) {
                    Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
                }
                keyEvent.consume();
            } else if (CTRLL.match(keyEvent)) {
                List.list(primaryStage);
                keyEvent.consume();
            } else if (CTRLF.match(keyEvent)) {
                Find.find(primaryStage);
                keyEvent.consume();
            } else if (keyEvent.getCode() == KeyCode.ESCAPE) {
                MenuOptions.logout(primaryStage);
                keyEvent.consume();
            }
        });
        
        
        primaryStage.setScene(welcomePage);
        primaryStage.show();
        
        if (FileMgmt.file.exists()) {
            FileMgmt.readInventory(FileMgmt.FILENAME);
            FileMgmt.storeInventory(FileMgmt.FILENAME);
            primaryStage.setOnCloseRequest(e -> Platform.exit());
        } else {
            AlertBox.display("No file", "Specified file does not exist. \nChanges you make will not be saved.");
        }
    }
}