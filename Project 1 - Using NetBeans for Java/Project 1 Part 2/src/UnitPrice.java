import java.util.Scanner;
public class UnitPrice {
// Written by: Nadine Mansour
// Reads the unit price of an item and the quantity ordered
// Calculates the total amount of purchase
    
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        
        System.out.print("Please enter the Quantity desired: ");
        double quantity;
        quantity = stdin.nextInt();
        
        System.out.print("Please enter the Unit price: ");
        double price;
        price = stdin.nextInt();
        
        System.out.println();
        
        System.out.print("The Quantity desired is : ");
        System.out.println((int) quantity);
        
        System.out.print("The Unit Price is       : $");
        System.out.println((int) price);
        
        System.out.print("The Total Amount is     : $");
        System.out.println((int) (price*quantity));
        
    }
}