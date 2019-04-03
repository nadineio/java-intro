import java.util.*;
import javax.swing.JOptionPane;
public class Oldest {
    // Written by: Nadine Mansour (Project 4 Part 1)
    // Reads in a list of names and ages until the user enters the sentinel
    // The program displays the name of the oldest person and then terminates
    
public static final String SENTINEL = "quit";    
public static void main(String[ ] args) {
    Scanner stdin = new Scanner(System.in);
    String name, oldestName;
    int age, oldestAge;
    oldestName = null;
    oldestAge = 0;
    
    name = JOptionPane.showInputDialog(null, "Enter name of family member (or \"" + SENTINEL + "\"): ");
    while (!name.equals(SENTINEL)) {
        age = Integer.parseInt(JOptionPane.showInputDialog("What is " + name + "'s age?"));
        if (age > oldestAge) {
            oldestAge = age;
            oldestName = name;
        } 
        name = JOptionPane.showInputDialog(null, "Enter name of family member (or \"" + SENTINEL + "\"): ");
    }
    JOptionPane.showMessageDialog(null, "The oldest person is " + oldestName + ".");
}}