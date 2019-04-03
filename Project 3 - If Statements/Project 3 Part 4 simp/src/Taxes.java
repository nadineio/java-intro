import java.text.DecimalFormat;
import java.util.Scanner;
public class Taxes {
    // Written by: Nadine Mansour
    // Reads in a user's net taxable income, and prints the amount of 
    // tax using the 2014 table. 
    // Program only includes incomes below $190,000
    
    public static void main(String[ ] args) {
        Scanner stdin = new Scanner(System.in);
        double income, tax;
        DecimalFormat df = new DecimalFormat("$###,###.##");
        
        System.out.print("Enter your net taxable income: $");
        income = stdin.nextDouble();

        if (income <= 9275)
            tax = (income * 0.10);
        else if (income <= 37650)
            tax = (9275 * 0.10) + (income - 9275) * 0.15;
        else if (income <= 91150)
            tax = (9275 * 0.10) + ((37650 - 9275) * 0.15) + 
                    ((income - 37650) * 0.25);
        else if (income <= 190000)
            tax = (9275 * 0.10) + ((37650 - 9275) * 0.15) + 
                    ((91150 - 37650) * 0.25) + ((income - 91150) * 0.28);  
        else {
            System.out.println("ERROR: Income must be below $190,000");
            return;
        }
        
        System.out.println("Your 2014 tax is " + (df.format(tax)));
    }
}