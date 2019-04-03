import java.util.Scanner;
public class CountingChange {
    // Written by: Nadine Mansour (Project 4 Part 3)
    // Determines whether the monetary value of coins provided by the user equals sum; if not, allows user one more try
    
public static void main(String[ ] args) {
    Scanner stdin = new Scanner(System.in);
    int sum, dimes, nickels, pennies;
    double total;
    total = 0;
        
    System.out.print("Enter the value of the sum: ");
    sum = stdin.nextInt();
        
    if ((sum < 0) || (sum > 99))
    System.out.println("ERROR: Sum must be in the interval 0 - 99");
        
    else {
        for (int i = 0; (i < 2) && (sum != total); i++) {
            System.out.print("Number of dimes: ");
            dimes = stdin.nextInt();
            System.out.print("Number of nickels: ");
            nickels = stdin.nextInt();
            System.out.print("Number of pennies: ");
            pennies = stdin.nextInt();
            
            total = (dimes * 10) + (nickels * 5) + (pennies);
            
            if (sum != total)       System.out.println("No");
            else                    System.out.println("Yes");
        }
    }
}}