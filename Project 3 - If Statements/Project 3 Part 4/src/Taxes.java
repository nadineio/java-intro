import java.text.DecimalFormat;
import java.util.Scanner;
public class Taxes {
    // Written by: Nadine Mansour
    // Reads in a user's net taxable income, and prints the amount of tax using the 2014 table. 
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
            tax = (9275 * 0.10) + ((37650 - 9275) * 0.15) + ((income - 37650) * 0.25);
        else if (income <= 190150)
            tax = (9275 * 0.10) + ((37650 - 9275) * 0.15) + ((91150 - 37650) * 0.25) + ((income - 91150) * 0.28);
        else if (income <= 413350)
            tax = (9275 * 0.10) + ((37650 - 9275) * 0.15) + ((91150 - 37650) * 0.25) + ((190150 - 91150) * 0.28) + ((income - 190150) * 0.33);
        else if (income <= 415050)
            tax = (9275 * 0.10) + ((37650 - 9275) * 0.15) + ((91150 - 37650) * 0.25) + ((190150 - 91150) * 0.28) + ((413350 - 190150) * 0.33) + ((income - 413350) * 0.35);
        else
            tax = (9275 * 0.10) + ((37650 - 9275) * 0.15) + ((91150 - 37650) * 0.25) + ((190150 - 91150) * 0.28) + ((413350 - 190150) * 0.33) + ((415050 - 413350) * 0.35) + ((income - 415050) * 0.396);
            

        System.out.println("Your 2014 tax is " + (df.format(tax)));

    }
}