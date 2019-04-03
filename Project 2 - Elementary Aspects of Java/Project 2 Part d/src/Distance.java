import java.util.Scanner;
public class Distance {
    // Written by: Nadine Mansour
    // Prompts user to enter two points 
    // Displays distance between two points, assuming values entered are valid
    
    public static void main(String[ ] args) {
        Scanner stdin = new Scanner(System.in);
        double x1, y1, x2, y2, d;
        
        System.out.print("Enter x1 and y1: ");
        x1 = stdin.nextDouble();
        y1 = stdin.nextDouble();
        
        System.out.print("Enter x2 and y2: ");
        x2 = stdin.nextDouble();
        y2 = stdin.nextDouble();
        
        d = Math.sqrt((Math.pow(x2-x1, 2)) + (Math.pow(y2-y1, 2)));
        
        System.out.println("The distance is " + d);
    }
}