import java.util.Scanner;
public class ValidPhoneNumber {
    // Written by: Nadine Mansour (Project 5 Part 2)
    // Checks if a phone number is valid and in this exact format XXX-XXX-XXXX, where X is a digit
    
public static final int NUMBER_OF_DIGITS = 12;
public static void main(String[ ] args) {
    Scanner stdin = new Scanner(System.in);
    int i, j, k;
    boolean testNumber;
    String phoneNumber;
    char hyphenCheck1, hyphenCheck2;
    
    i = 0;      j = 4;      k = 8;
    testNumber = true;
    
    System.out.print("Please enter a phone number:  ");
    phoneNumber = stdin.nextLine();

    hyphenCheck1 = phoneNumber.charAt(3); hyphenCheck2 = phoneNumber.charAt(7);
    
    if (phoneNumber.length() == NUMBER_OF_DIGITS) {
        if ((hyphenCheck1 != '-') || (hyphenCheck2 != '-')) testNumber = false;
        else {
            while ((i < 3) && (j < 7) && (k < 12) && (testNumber != false)) {
                if (!Character.isDigit(phoneNumber.charAt(i)))
                    testNumber = false;
                else if (!Character.isDigit(phoneNumber.charAt(j)))
                    testNumber = false;
                else if (!Character.isDigit(phoneNumber.charAt(k)))
                    testNumber = false;
                i++;    j++;    k++;
            }
        }
    } else                      testNumber = false;
    if (testNumber == false)    System.out.println("Invalid number.");
    else                        System.out.println("Number is valid!");
}}