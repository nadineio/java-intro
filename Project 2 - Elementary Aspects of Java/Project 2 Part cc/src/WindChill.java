import java.util.Scanner;
import java.lang.Math;

public class WindChill {
    // Written by: Nadine Mansour
    // Prompts user to enter a temperature and a wind speed in °F and mph, respectively
    // Displays the wind chill index assuming that -58 < temperature < 41 and wind speed > 2 
    
    public static void main(String[ ] args) {
        Scanner stdin = new Scanner(System.in);
        double v, T, w;
                        
        System.out.print("Enter temperature(Fahrenheit): ");
        T = stdin.nextFloat();
        
        System.out.print("Enter wind speed(mph): ");
        v = stdin.nextFloat();
            
        w = 35.74 + (0.6215*T) - 35.75*(Math.pow(v, 0.16)) + 0.4275*T*(Math.pow(v, 0.16));
        System.out.println("The wind chill index is " + (int) w);     
    }
}