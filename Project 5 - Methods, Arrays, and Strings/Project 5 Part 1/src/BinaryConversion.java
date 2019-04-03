import java.util.Scanner;
public class BinaryConversion {
    // Written by: Nadine Mansour (Project 5 Part 1)
    // Reads a sequence of binary strings and converts each to a 
    // decimal integer. The program terminates when the string -1 is given
 
public static final String SENTINEL = "-1";
public static void main(String[ ] args) {
    Scanner stdin = new Scanner(System.in);
    String enterBinaryString;
    int binaryDecimal;
    
    System.out.print("Enter a binary number:  ");
    enterBinaryString = stdin.nextLine();    
    
    while (!enterBinaryString.equals(SENTINEL)) {
        binaryDecimal = binaryToDecimal(enterBinaryString);
        System.out.println("Conversion to decimal:  " + binaryDecimal);
        System.out.print("Enter a binary number:  ");
        enterBinaryString = stdin.nextLine();
    }
    if (enterBinaryString.equals(SENTINEL))  System.out.println("All set!");
}
public static int binaryToDecimal(String binaryString) {
    int binaryInteger, digit, decimal, exponent;
    exponent = decimal = 0;

    binaryInteger = Integer.parseInt(binaryString);
    while (binaryInteger != 0) {
        digit = binaryInteger % 10;
        decimal += digit * Math.pow(2, exponent);
        binaryInteger = binaryInteger / 10;
        exponent++;
    }
    
    return decimal;
}}