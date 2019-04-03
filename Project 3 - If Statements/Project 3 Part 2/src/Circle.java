import java.util.Scanner;
public class Circle {
    // Written by: Nadine Mansour
    // Determines whether a point lies within a circle of radius 10.0 centered at the origin if (x-originx)^2 +(y-originy)^2 is less than r^2
    
    public static void main(String[ ] args) {
        Scanner stdin = new Scanner(System.in);
        
        int x, y;
        double radius, centerx, centery;
        radius = 10.0; 
        centerx = centery = 0.0;
        
        System.out.print("Enter a point with two coordinates: ");
        x = stdin.nextInt();
        y = stdin.nextInt();
        
        if ((Math.pow((x - centerx), 2) + Math.pow((x - centery), 2)) < (Math.pow(radius, 2))) {
            System.out.println("Point (" + x + ", " + y + ") is in the circle.");
        } else 
            System.out.println("Point (" + x + ", " + y + ") is not in the circle.");
        
    }
}