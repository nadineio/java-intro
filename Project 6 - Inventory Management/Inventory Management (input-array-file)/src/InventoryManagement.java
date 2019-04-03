import java.io.*;
import java.util.*;
class Entry {
    public String name, quantity, notes;
    public static int numberOfEntries;
    public static final String FILENAME = "inventory.txt";
    public static File file             = new File(FILENAME);
    public static Scanner stdin         = new Scanner(System.in);
    public static Entry[] entryList     = new Entry[200]; 
}
public class InventoryManagement {
    /*  Written by: Nadine Mansour (Project 6)
        This program reads codes of 1-8 characters, representing an item 
        or product name, along with an associated quantity and associated 
        notes, and stores them into a designated text file.            
    */
    
    public static void main(String[] args) throws Exception {
        if (Entry.file.exists()) {
            String userIn;
            char action;

            readInventory(Entry.FILENAME);
            if (Entry.numberOfEntries == 200) {
                System.out.println("Sorry, you have reached 200 entries.");
                System.exit(0);
            }            
            System.out.println("Codes are entered as 1 to 8 characters.");
            printCommandOptions();
            
            System.out.print("Command: ");
            userIn = Entry.stdin.nextLine();
            action = userIn.toLowerCase().charAt(0);
            while (action != 'q') {
                switch (action) {
                    case 'd':
                        deleteItem(userIn);
                        break;
                    case 'e':
                        newItem(userIn);
                        break;
                    case 'f':
                        findItem(Entry.FILENAME, userIn);
                        break;
                    case 'l':
                        listAllItems(Entry.FILENAME);
                        break;
                    default:
                        printCommandOptions();
                        break;
                }
                System.out.print("Command: ");
                userIn = Entry.stdin.nextLine();
                action = userIn.toLowerCase().charAt(0);
            }
            storeInventory(Entry.FILENAME);
        } else 
            System.out.println("The specified file does not exist.");
    }
    public static String capitalizeFirst(String s) {
        s = s.toLowerCase();
        s = Character.toUpperCase(s.trim().charAt(0)) + s.trim().substring(1);
        return s;
    }
    public static String checkLengthGetName(String userIn) {
        String correctName;
        correctName = userIn.substring(2);
        while ((correctName.length() > 8) || (correctName.length() < 1)) {
            System.out.println("Code must be between 1 and 8 characters. ");
            System.out.println();
            System.out.print("Command: ");
            userIn      = Entry.stdin.nextLine();
            correctName = userIn.substring(2);
        }
        return correctName;
    }
    public static void readInventory(String fileName) throws Exception {
        Scanner fileIn = new Scanner(Entry.file);
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
    } 
    public static void printCommandOptions() {
        System.out.println("Use \"e\" for enter, \"f\" for find,"
                         + " \"l\" for list, \"d\" for delete, and \"q\" for quit.");
        System.out.println();
    }
    public static void newItem(String userIn) throws Exception {
        int i;
        i = Entry.numberOfEntries;
        Entry.entryList[i] = new Entry();
        Entry.entryList[i].name = capitalizeFirst(checkLengthGetName(userIn));
        System.out.print("Enter quantity: ");
        Entry.entryList[i].quantity = Entry.stdin.nextLine();
        System.out.print("Enter notes: ");
        Entry.entryList[i].notes    = Entry.stdin.nextLine().trim();
        System.out.println();
        Entry.numberOfEntries++;
    }
    public static void findItem(String fileName, String n) throws Exception {
        Scanner fileIn = new Scanner(Entry.file);
        String name, quantity, notes, nextWord;
        boolean itemExists;
        
        itemExists = false;
        name = capitalizeFirst(checkLengthGetName(n));
        while ((fileIn.hasNext()) && (itemExists != true)) {
            nextWord = fileIn.next();
            if (nextWord.contains(name.trim())) {
                quantity = fileIn.next();
                notes = fileIn.nextLine().trim();
                System.out.println("-- " + name.trim());
                System.out.println("-- " + quantity);
                System.out.println("-- " + notes);
                System.out.println();
                itemExists = true;
            }
        }
        fileIn.close();
        if (itemExists == false)
            System.out.println("** No entry with code " + name + ".\n");
    }  
    public static void listAllItems(String fileName) throws Exception {
        for (int i = 0; i < Entry.numberOfEntries; i++) {
            System.out.println("-- " + Entry.entryList[i].name);
            System.out.println("-- " + Entry.entryList[i].quantity);
            System.out.println("-- " + Entry.entryList[i].notes);
            System.out.println();
        }
    }
    public static void storeInventory(String fileName) throws Exception {
        PrintStream P = new PrintStream(fileName);
        for (int i = 0; i < Entry.numberOfEntries; i++) {
            P.println(Entry.entryList[i].name     + "\t" + 
                      Entry.entryList[i].quantity + "\t" +
                      Entry.entryList[i].notes);
        }
        P.close();
        System.out.println("Inventory stored.");
    }
    public static void deleteItem(String userIn) throws Exception {
        for (int i = 0; i < Entry.numberOfEntries; i++) {
            if (Entry.entryList[i].name.equals(capitalizeFirst(checkLengthGetName(userIn)))) {
                  Entry.entryList[i].name = " ".trim();
                  Entry.entryList[i].quantity = " ".trim();
                  Entry.entryList[i].notes = " ".trim();
            }
        }
        System.out.println("Item deleted.");
    }
}