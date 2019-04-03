import java.util.Scanner;
public class ApproximatingPi {
    // Written by: Nadine Mansour
    // Displays the result of pi given the formula 4 * (1.0 - 1.0/3 + 1.0/5 - 1.0/7 + 1.0/9 - 1.0/11 + 1.0/13 + ...)
    
    public static void main(String[ ] args) {
        Scanner stdin = new Scanner(System.in);
        
        double pi = 1.0;
        int n = 1;
        
        for (double i = 3.0; i < 1000000000; i = i + 2){
            if (n % 2 == 0)
                pi = pi + (1 / i);
            else
                pi = pi - (1 / i);
            n = n + 1;
        }
        
        System.out.println("\u03C0 = " + (pi * 4));
    }
}