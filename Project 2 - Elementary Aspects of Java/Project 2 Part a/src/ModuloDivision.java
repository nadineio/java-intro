import java.util.Scanner;
public class ModuloDivision {
    // Written by: Nadine Mansour
    // Reads in a 3-digit integer 
    // Prints out the sum of the digits of the integer
    
    public static void main(String[ ] args) {
        Scanner stdin = new Scanner(System.in);
        
        int n, sum = 0;
        
        System.out.print("Enter an integer: ");
        n = stdin.nextInt();
                
        while (n != 0) {
            sum = sum + (n % 10);
            n = n / 10;
        }
        
        System.out.println("The sum of the digits is: " + sum);
    }
}