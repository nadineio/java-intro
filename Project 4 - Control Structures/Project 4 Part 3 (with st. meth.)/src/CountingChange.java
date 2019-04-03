import java.util.Scanner;
public class CountingChange {
    // Written by: Nadine Mansour (Project 4 Part 3)
    // Determines whether the monetary value of coins provided by the user equals sum; if not, allows user one more try
    // Uses a static method to prompt the user for the coins and return the monetary value of the coins

public static double dimes( ) {
    Scanner stdin = new Scanner(System.in);
    double dimes, d;
    
    System.out.print("Number of dimes:   ");
    dimes = stdin.nextInt();
    d = dimes * 0.10;
    
    return d;
}
public static double nickels( ) {
    Scanner stdin = new Scanner(System.in);
    double nickels, n;
    
    System.out.print("Number of nickels: ");
    nickels = stdin.nextInt();
    n = nickels * 0.05;
    
    return n;
}
public static double pennies( ) {
    Scanner stdin = new Scanner(System.in);
    double pennies, p;
    
    System.out.print("Number of pennies: ");
    pennies = stdin.nextInt();
    p = pennies * 0.01;
    
    return p;
}
public static void main(String[ ] args) {
    Scanner stdin = new Scanner(System.in);
    double sum, d, n, p, total;
    float value;
    total = 0;
    
    System.out.print("Enter the value of the sum: ");
    sum = stdin.nextInt();
    
    if ((sum < 0) || (sum > 99))
    System.out.println("ERROR: Sum must be in the interval 0 - 99");
        
    else {
        for (int i = 0; (i < 2) && (sum != total); i++) {
            d = dimes( );
            n = nickels( );
            p = pennies( );
            
            total = (100 * (d + n + p));
            value = (float) (total / 100);
            
            if (sum != total){
                System.out.printf("Incorrect sum, the actual value is $%.2f", value);
                System.out.println(".");
            } else
                System.out.println("Yes");
        }
    }
}}