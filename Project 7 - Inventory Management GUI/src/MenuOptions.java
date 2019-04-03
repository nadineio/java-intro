import java.util.Date;
import java.util.logging.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class MenuOptions {
    public static Inventory instance = new Inventory();
    public static void aboutMenu(Stage primaryStage) {
        primaryStage.setTitle("ABOUT");
        
        Label aboutLbl = new Label("This application serves as an \ninventory manager for \n200 items or fewer.");
        aboutLbl.setFont(Font.font("Bookman Old Style", FontWeight.BOLD, FontPosture.ITALIC, 15));
        aboutLbl.setTextAlignment(TextAlignment.CENTER);
        
        Button backBtn = new Button("Back");
        backBtn.setOnAction(e -> {
            try {
                toWelcomeScreen(primaryStage);
            } catch (Exception ex) {
                Logger.getLogger(Inventory.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        BorderPane buttonLayout = new BorderPane();
        buttonLayout.setCenter(backBtn);
        
        VBox pageLayout = new VBox();
        pageLayout.setPadding(new Insets(90, 30, 10, 30));
        pageLayout.setSpacing(70);
        pageLayout.getChildren().addAll(aboutLbl, buttonLayout);
        
        Scene menuPage = new Scene(pageLayout, 300, 300);
        menuPage.getStylesheets().add(programSkin.class.getResource("programSkin.css").toExternalForm());
        menuPage.setOnKeyPressed((final KeyEvent keyEvent) -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                try {
                    toWelcomeScreen(primaryStage);
                } catch (Exception ex) {
                    Logger.getLogger(AddDel.class.getName()).log(Level.SEVERE, null, ex);
                }
                keyEvent.consume();
            }
        });
        
        primaryStage.setScene(menuPage);
        primaryStage.show();
    }

    public static void logout(Stage primaryStage) {
        try {
            instance.start(primaryStage);
        } catch (Exception ex) {
            Logger.getLogger(List.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void helpMenu(Stage primaryStage) {
        primaryStage.setTitle("HELP");
        Label helpLbl = new Label("To add an item, use CTRL+A or click on the \"Add or Delete Item\" button on the welcome screen. \n"
                + "After that, enter the item's name, quantity, and notes (optional) and then click "
                + "\non the \"Add Item\" button.\n"
                + "\n"
                + "To edit an item, use CTRL+E or click on the \"Edit Item\" button on the welcome screen. \n"
                + "After that, enter the item's name and then click on the \"Edit Item\" button. \n"
                + "You will then be redirected to a page in which you can update the item's information.\n"
                + "\n"
                + "To delete an item, use CTRL+D or click on the \"Add or Delete Item\" button on the welcome "
                + "screen. \n"
                + "After that, enter the item's name and then click on the \"Delete Item\" button.\n"
                + "\n"
                + "To delete all items, use CTRL+D or click on the \"Add or Delete Item\" button on the welcome "
                + "screen. \n"
                + "After that, click on the \"Delete All Items\" button.\n"
                + "\n"
                + "To find an item, use CTRL+F or click on the \"Find Item\" button on the welcome screen. \n"
                + "After that, enter the item's name (or part of it) and then click on the \"Find Item\" button.\n"
                + "\n"
                + "To list all items, use CTRL+L or click on the \"List Items\" button on the welcome screen.\n"
                + "\n"
                + "To go back to the welcome screen after performing any of the above tasks, \n"
                + "use ESCAPE or simply click on the \"Done\" button on each screen.\n"
                + "\n"
                + "To view file details, use CTRL+I from the welcome screen.");
        helpLbl.setTextAlignment(TextAlignment.CENTER);
        helpLbl.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        
        Button backBtn = new Button("Back");
        backBtn.setOnAction(e -> {
            try {
                toWelcomeScreen(primaryStage);
            } catch (Exception ex) {
                Logger.getLogger(Inventory.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        VBox pageLayout = new VBox();
        pageLayout.setPadding(new Insets(20, 10, 10, 10));
        pageLayout.setSpacing(30);
        pageLayout.getChildren().addAll(helpLbl, backBtn);
        pageLayout.setAlignment(Pos.CENTER);
        
        Scene helpPage = new Scene(pageLayout, 650, 500);
        helpPage.getStylesheets().add(programSkin.class.getResource("programSkin.css").toExternalForm());
        
        helpPage.setOnKeyPressed((final KeyEvent keyEvent) -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                try {
                    toWelcomeScreen(primaryStage);
                } catch (Exception ex) {
                    Logger.getLogger(AddDel.class.getName()).log(Level.SEVERE, null, ex);
                }
                keyEvent.consume();
            }
        });
        
        primaryStage.setScene(helpPage);
        primaryStage.show();
    }
    
    public static void toWelcomeScreen(Stage primaryStage) throws Exception {
        Inventory.welcomeScreen(primaryStage);
    }
    
    public static void fileInfo(Stage primaryStage) throws Exception {
        primaryStage.setTitle("FILE INFORMATION");
        Date fileDate = new Date(FileMgmt.file.lastModified());
        
        Label fileLbl = new Label("File Name: " + FileMgmt.file.getName() +
                               "\nFile Path: " + FileMgmt.file.getCanonicalPath() +
                               "\nFile Last Modified on " + fileDate);
        fileLbl.setTextAlignment(TextAlignment.JUSTIFY);
        fileLbl.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        
        Button deleteFile = new Button("Delete File");
        deleteFile.setOnAction(e -> FileMgmt.deleteFile());
        
        Button renameFile = new Button("Rename File");
        renameFile.setOnAction(e -> {
            try {
                FileMgmt.renameFile(primaryStage);
            } catch (Exception ex) {
                Logger.getLogger(MenuOptions.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        Button backBtn = new Button("Back");
        backBtn.setOnAction(e -> {
            try {
                toWelcomeScreen(primaryStage);

            } catch (Exception ex) {
                Logger.getLogger(Inventory.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        HBox buttonsLayout = new HBox();
        buttonsLayout.setPadding(new Insets(20, 10, 10, 10));
        buttonsLayout.setSpacing(20);
        buttonsLayout.getChildren().addAll(renameFile, deleteFile, backBtn);
        buttonsLayout.setAlignment(Pos.CENTER);
        
        VBox pageLayout = new VBox();
        pageLayout.setPadding(new Insets(20, 10, 10, 10));
        pageLayout.setSpacing(20);
        pageLayout.getChildren().addAll(fileLbl, buttonsLayout);
        pageLayout.setAlignment(Pos.CENTER);
        
        Scene fileInfoPage = new Scene(pageLayout, 750, 300);
        fileInfoPage.getStylesheets().add(programSkin.class.getResource("programSkin.css").toExternalForm());
        
        fileInfoPage.setOnKeyPressed((final KeyEvent keyEvent) -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                try {
                    toWelcomeScreen(primaryStage);
                } catch (Exception ex) {
                    Logger.getLogger(AddDel.class.getName()).log(Level.SEVERE, null, ex);
                }
                keyEvent.consume();
            }
        });
        
        primaryStage.setScene(fileInfoPage);
        primaryStage.show();       
    }
}