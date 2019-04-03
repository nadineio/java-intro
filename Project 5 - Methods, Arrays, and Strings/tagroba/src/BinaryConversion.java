public static boolean checkIfBinary(String binaryString) {
    boolean result;
    for (int i = 0; i < binaryString.length(); i++) {
        result = binaryString.charAt(i).matches(".[2-9].");
    }
    return result;
}


public static boolean checkIfBinary(String binaryString) {
    boolean result; 
    result = true;
    
    int i;
    i = 0;
    while ((i < binaryString.length()) && (result == true)) {
        result = (binaryString.charAt(i) != 0) || (binaryString.charAt(i) != 1);
        i++;
    }
    return result;
}
}


import java.util.Scanner;
public class Marathon {
    //

if (phoneNumber.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}"))
        System.out.println("Valid phone number");
    else
        System.out.println("Invalid phone number");
        
public static final int NUMBER_OF_RUNNERS = 16;
public static void main (String[] arguments){
    String fastestName, secondFastestName;
    int fastestTime, secondFastestTime;
    double fastestTimeHours, secondFastestTimeHours;
    
    fastestName = secondFastestName = null;
    fastestTime = secondFastestTime = 500;
    
    
    String[] names = {"Elena", "Thomas", "Hamilton", "Suzie", "Phil", "Matt",
      "Alex", "Emma", "John", "James", "Jane", "Emily", "Daniel", "Neda", 
      "Aaron", "Kate"}; 

    int[] times = {341, 273, 278, 329, 445, 402, 388, 275, 243, 334, 412, 393, 
       299,343, 317, 265};    

    System.out.println("Students who ran in the Columbus Marathon:");
    for (int i = 0; i < NUMBER_OF_RUNNERS; i++) {
      System.out.println(names[i]+ "   " + times[i]);
    } 
   
    for (int j = 0; j < NUMBER_OF_RUNNERS; j++) {
       if (times[j] < fastestTime) {
            fastestTime = times[j];
            fastestName = names[j];
       }
    }
    fastestTimeHours = fastestTime / 60;
    System.out.println();
    System.out.println("The fastest runner was " + fastestName + ".");
    System.out.print(fastestName + "'s time was " + fastestTimeHours + " hours");
    System.out.println(" (" + fastestTime + " minutes).");
    
    for (int k = 0; k < NUMBER_OF_RUNNERS; k++) {
       if ((times[k] < secondFastestTime) && (times[k] != fastestTime)) {
            secondFastestTime = times[k];
            secondFastestName = names[k];
       }
    }
    secondFastestTimeHours = secondFastestTime / 60;
    System.out.println();
    System.out.println("The second fastest runner was " + secondFastestName + ".");
    System.out.print(secondFastestName + "'s time was " + secondFastestTimeHours + " hours");
    System.out.println(" (" + secondFastestTime + " minutes).");
}}




import java.util.Scanner;
public class BinaryConversion {
    // Written by: Nadine Mansour (Project 5 Part 1)
    // Reads a sequence of binary strings and converts each to a decimal integer
    // Terminates when the string -1 is given

public static int binaryToDecimal(String binaryString) {
    int digit, total;
    double decimal, exponent;
    exponent = total = 0;
        
    for (int i = binaryString.length(); i < 0; i--) {
        digit = binaryString.charAt(i);
        
        if (digit == 1)         decimal = Math.pow(2, exponent);
        else                    decimal = 0;
        
        exponent++;
        total += decimal;
    }
    
    return total;
}
      
public static final String SENTINEL = "-1";
public static void main(String[ ] args) {
    Scanner stdin = new Scanner(System.in);
    String binaryString;
    int decimal;
    
    System.out.print("Enter a binary number:  ");
    binaryString = stdin.nextLine();
    while (!binaryString.equals(SENTINEL)) {
        decimal = binaryToDecimal(binaryString);
        System.out.println("Conversion to decimal:  " + decimal);
        System.out.print("Enter a binary number:  ");
        binaryString = stdin.nextLine();
    }
    if (binaryString.equals(SENTINEL))    System.out.println("All set!");
    
}}


import java.util.Scanner;
public class BinaryConversion {


public static void main(String[ ] args) {
    Scanner stdin = new Scanner(System.in);
    String binaryString;
    int digit, total;
    double decimal, exponent;
    exponent = total = 0;
    
    System.out.print("Enter a binary number:  ");
    binaryString = stdin.next();
    for (int i = binaryString.length(); i < 0; i--) {
        digit = binaryString.charAt(i);
        
        if (digit == 1)         decimal = Math.pow(2, exponent);
        else                    decimal = 0;
        
        exponent++;
        total += decimal;
    }
    System.out.println("Conversion to decimal:  " + total);
}}



import java.util.Scanner;
public class BinaryConversion {
    // Written by: Nadine Mansour (Project 5 Part 1)
    // Reads a sequence of binary strings and converts each to a decimal integer
    // Terminates when the string -1 is given

public static final String SENTINEL = "-1";
public static void main(String[ ] args) {
    Scanner stdin = new Scanner(System.in);
    String binaryString;
    int digit, total;
    double decimal, exponent;
    exponent = total = 0;
    
    System.out.print("Enter a binary number:  ");
    binaryString = stdin.next();    
        while (!binaryString.equals(SENTINEL)) {
        for (int i = binaryString.length(); i < 0; i--) {
        digit = binaryString.charAt(i);
        
        if (digit == 1)         decimal = Math.pow(2, exponent);
        else                    decimal = 0;
        
        exponent++;
        total += decimal;
    }
        System.out.println("Conversion to decimal:  " + total);
        System.out.print("Enter a binary number:  ");
        binaryString = stdin.next();
        
        }
        if (binaryString.equals(SENTINEL))    System.out.println("All set!");
}}
    
    {"Elena", "341"},
        {"Thomas", "273"},
        {"Hamilton", "278"},
        {"Suzie", "329"},
        {"Phil", "445"},
        {"Matt", "402"},
        {"Alex", "388"},
        {"Emma", "275"},
        {"John", "243"},
        {"James", "334"},
        {"Jane", "412"},
        {"Emily", "393"},
        {"Daniel", "299"},
        {"Neda", "343"},
        {"Aaron", "317"},
        {"Kate", "265"}