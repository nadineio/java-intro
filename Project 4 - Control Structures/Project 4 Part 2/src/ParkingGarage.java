import java.util.Scanner;
public class ParkingGarage {
    // Written by: Nadine Mansour (Project 4 Part 2)
    // Calculates and prints a summary of the hours and charges incurred in a 
    // day at a parking garage with 5 customers
    
public static void main(String[ ] args) {
    Scanner stdin = new Scanner(System.in);
    double hours, charge;
    float totalHours, totalCharge;
    totalHours = totalCharge = 0;
    
    for (int carNumber = 1; carNumber <= 5; carNumber++) {
        System.out.print("Enter the hours parked for car " +carNumber+ ":   ");
        hours = stdin.nextDouble();
        
        if (hours <= 2.0)            charge = 5.00;
        else if (hours <= 9.0)       charge = 5.00 + (Math.ceil(hours) - 2.00);
        else                         charge = 12.00;
        
        totalHours += hours; 
        totalCharge += charge;
    }

    System.out.println("\nTotal Hours " + totalHours);
    System.out.printf("Total Charge $%.2f", totalCharge);         
    System.out.println();
    
}}