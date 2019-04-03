import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class InventoryManagement {
    //

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("products.txt");
        Scanner input = new Scanner(file);
        Scanner stdin = new Scanner(System.in);

        if (file.exists()) {
            try(FileWriter fw = new FileWriter("products.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter output = new PrintWriter(bw)) {
                System.out.print("Command: ");
                char code = stdin.next().charAt(0);
                if (code != 'q') {
                    String productName = stdin.nextLine();
                    productName = capitalizeFirst(productName);
                    while (code != 'q') {
                        boolean productExists = false;
                        switch (code) {
                            case 'e':
                                System.out.print("Enter quantity: ");
                                String quantity = stdin.nextLine();
                                System.out.print("Enter notes: ");
                                String notes = stdin.nextLine();
                                System.out.println();
                                output.println(productName.trim());
                                output.println(quantity);
                                output.println(notes);
                                output.println();
                                break;
                            case 'f':
                                while ((input.hasNextLine()) && (productExists != true)) {
                                    String line = input.nextLine();
                                    if(line.contains(productName.trim())) {
                                        quantity = input.nextLine();
                                        notes = input.nextLine();
                                        System.out.println("-- " + productName.trim());
                                        System.out.println("-- " + quantity);
                                        System.out.println("-- " + notes);
                                        productExists = true;
                                    }
                                }
                                if (productExists == false)
                                        System.out.println("** No entry with code " + productName);
                                break;
                            case 'l':
                                while(input.hasNext()) {
                                    productName = input.nextLine();
                                    quantity = input.nextLine();
                                    notes = input.nextLine();

                                    System.out.println(productName);
                                    System.out.println(quantity);
                                    System.out.println(notes);
                                }
                                input.close();
                                break;
                            case 'q':
                                break;
                            default:
                                System.out.println("Invalid code.");
                                break;
                            }

                        System.out.print("Command: ");
                        code = stdin.next().charAt(0);
                        if (code != 'q') {
                        productName = stdin.nextLine();
                        productName = capitalizeFirst(productName);           
                        input = new Scanner(file);
                }}}} catch (IOException exc) {
                }

            } else {
                System.out.println("File does not exist.");
            }
    }
    public static String capitalizeFirst(String s) {
        s = s.toLowerCase();
        s = Character.toUpperCase(s.trim().charAt(0)) + s.trim().substring(1);
        return s;
    }
}