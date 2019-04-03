public class Marathon {
    // Written by: Nadine Mansour (Project 5 Part 3)
    // Prints the list of runners and times given, and uses two static methods 
    // to find the fastest and second fastest runners (in hours and minutes)
    
public static final int NUMBER_OF_RUNNERS = 16;
public static void main (String[] arguments){
    String fastestName, secondFastestName;
    int fastestTime, fastestTimeHours, fastestTimeMinutes,
        secondFastestTime, secondFastestTimeHours, secondFastestTimeMinutes;
    
    String[] name = {"Elena", "Thomas", "Hamilton", "Suzie", "Phil", "Matt",
      "Alex", "Emma", "John", "James", "Jane", "Emily", "Daniel", "Neda", 
      "Aaron", "Kate"}; 

    int[] time = {341, 273, 278, 329, 445, 402, 388, 275, 243, 334, 412, 393, 
       299, 343, 317, 265};    

    System.out.println("Students who ran in the Columbus Marathon:");
    System.out.println("Name        Time (minutes)");
    for (int i = 0; i < NUMBER_OF_RUNNERS; i++) {
      System.out.printf("%-16s", name[i]);
      System.out.println(time[i]);
    }
    
    fastestName = name[fastestRunner(time)];
    fastestTime = time[fastestRunner(time)];
    fastestTimeHours = (fastestTime / 60);
    fastestTimeMinutes = (fastestTime - (fastestTimeHours * 60));
    
    System.out.println();
    System.out.println("The fastest runner was " + fastestName + ".");
    System.out.print(fastestName + "'s time was " + fastestTimeHours);
    System.out.println(" hours and " + fastestTimeMinutes + " minutes.");
    
    secondFastestName = name[secondFastestRunner(time)];
    secondFastestTime = time[secondFastestRunner(time)];
    secondFastestTimeHours = (secondFastestTime / 60);
    secondFastestTimeMinutes = secondFastestTime - (secondFastestTimeHours * 60);
   
    System.out.println();
    System.out.println("The second fastest runner was " + secondFastestName + ".");
    System.out.print(secondFastestName + "'s time was " + secondFastestTimeHours);
    System.out.println(" hours and " + secondFastestTimeMinutes + " minutes.");
}
public static int fastestRunner(int[] listOfTimes) {
    int fastestTime, fastestNameIndex;
    
    fastestTime = fastestNameIndex = 1000;
    
    for (int j = 0; j < NUMBER_OF_RUNNERS; j++) {
       if (listOfTimes[j] < fastestTime) {
            fastestTime = listOfTimes[j];
            fastestNameIndex = j;
       }
    }
  return fastestNameIndex;
}
public static int secondFastestRunner(int[] listOfTimes) {
    int fastestTime, secondFastestTime, secondFastestNameIndex;
    
    fastestTime = fastestRunner(listOfTimes);
    secondFastestTime = secondFastestNameIndex = 1000;
    
    for (int k = 0; k < NUMBER_OF_RUNNERS; k++) {
       if ((listOfTimes[k] < secondFastestTime) && (k != fastestTime)) {
           secondFastestTime = listOfTimes[k];
           secondFastestNameIndex = k;
       }
    }
  return secondFastestNameIndex;
}}