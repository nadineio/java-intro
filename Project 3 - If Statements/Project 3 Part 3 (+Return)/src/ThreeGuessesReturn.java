import java.util.Scanner;
public class ThreeGuesses {
    // Written by: Nadine Mansour
    // Determines whether user has entered correct secret code with a maximum of three tries.
    
    public static void main(String[ ] args) {
        Scanner stdin = new Scanner(System.in);
        
        int code, attempt1, attempt2, attempt3;
        code = 51;
        
        System.out.println("Welcome to People's Bank.");
        System.out.print("Please enter your secret code:    ");
        
        attempt1 = stdin.nextInt();
        if (attempt1 == code) {
            System.out.println("Fine, go ahead.");
            return;
        } else 
            System.out.print("Sorry, that is not it. Try again: ");
            
        attempt2 = stdin.nextInt();
        if (attempt2 == code) {
            System.out.println("Fine, go ahead.");
            return;
        } else 
            System.out.print("Sorry, last chance. Try again:    ");
            
        attempt3 = stdin.nextInt();
        if (attempt3 == code) {
            System.out.println("Fine, go ahead.");
        } else 
            System.out.println("\nYou have entered the incorrect secret code three times. \nPlease contact People's Bank to reset your account.");

    }
}