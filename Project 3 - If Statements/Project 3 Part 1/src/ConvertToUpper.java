import java.util.Scanner;
public class ConvertToUpper {
    // Written by: Nadine Mansour
    // Reads in a character, presumably a letter, and prints the uppercase 
    // equivalent of the letter. If the character is not a letter, the program
    // simply prints a message indicating this.
    
    public static void main(String[ ] args) {
        Scanner stdin = new Scanner(System.in);
        char lower;
        
        System.out.print("Enter a letter: ");
        lower = stdin.next().charAt(0);
        
        if (Character.isLetter(lower)) {
            System.out.println("The uppercase equivalent of " + lower + " is");
            System.out.println("     " + Character.toUpperCase(lower));
        } else 
            System.out.println("Not a letter.");
    }
}

