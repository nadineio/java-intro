import java.io.*;
import java.util.Scanner;
import java.util.logging.*;
import javafx.beans.binding.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class FileMgmt {
    public static TextField newFileName;
    public static String FILENAME = "inventory.txt";
    public static File file       = new File(FILENAME);
    
    public static void readInventory(String fileName) throws Exception {
        if (file.exists()) {
            Scanner fileIn = new Scanner(file);
            int i;
            i = 0;
            while (fileIn.hasNext()) {
                Entry.entryList[i]          = new Entry();
                Entry.entryList[i].name     = fileIn.next();
                Entry.entryList[i].quantity = fileIn.next();
                Entry.entryList[i].notes    = fileIn.nextLine().trim();
                i++;
            }
            fileIn.close();
            Entry.numberOfEntries = i;
        } else 
            AlertBox.display("No file", "Specified file does not exist. \nChanges you make will not be saved.");    
    }
    
    public static void storeInventory(String fileName) throws Exception {
        Entry.sortArray();
        PrintStream P = new PrintStream(fileName);
        for (int i = 0; i < Entry.numberOfEntries; i++) {
            P.println(Entry.entryList[i].name       + "\t"
                    + Entry.entryList[i].quantity   + "\t"
                    + Entry.entryList[i].notes);
        }
        P.close();
    }
    
    public static void renameFile(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Rename File");
        
        HBox renameLayout = new HBox();
        renameLayout.setPadding(new Insets(20, 10, 10, 10));
        renameLayout.setSpacing(20);
        renameLayout.setAlignment(Pos.CENTER);
        
        Button renameBtn = new Button("Rename");
        renameBtn.setOnAction(e -> renameBtnClicked());
        
        Button cancelBtn = new Button("Cancel");
        cancelBtn.setOnAction(e -> {
            try {
                MenuOptions.fileInfo(primaryStage);
            } catch (Exception ex) {
                Logger.getLogger(FileMgmt.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        newFileName = new TextField();
        newFileName.setPromptText("example.txt");
        newFileName.setMinWidth(200);
        
        renameLayout.getChildren().addAll(newFileName, renameBtn, cancelBtn);
        
        Scene renameFilePage = new Scene(renameLayout);
        renameFilePage.getStylesheets().add(programSkin.class.getResource("programSkin.css").toExternalForm());
        
        primaryStage.setScene(renameFilePage);
        primaryStage.show();
        
        renameFilePage.setOnKeyPressed((final KeyEvent keyEvent) -> {
            if ((keyEvent.getCode() == KeyCode.ENTER) && !(renameBtn.isDisabled())) {
                renameBtnClicked();
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
        BooleanBinding renameBindName = Bindings.isEmpty(newFileName.textProperty());
        renameBtn.disableProperty().bind(renameBindName);
    }
    
    public static void renameBtnClicked() {
        String newName;
        newName = newFileName.getText();
        
        File newFile = new File(newName);
        file.renameTo(newFile);
        
        FILENAME = newName;
        AlertBox.display("Rename Successful", "File has been renamed");
        
        newFileName.clear();
        file = new File(FILENAME);
    }
    
    public static void deleteFile() {
        file.delete();
        AlertBox.display("File Deleted", "The specified file has been deleted.");
    }
}