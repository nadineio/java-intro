import javax.swing.JOptionPane;
public class Circle {
    // Written by: Nadine Mansour
    // Determines whether a point lies within a circle of radius 10.0 centered
    // at the origin if (x-originx)^2 +(y-originy)^2 is less than r^2
    
    public static void main(String[ ] args) {
        int x, y;
        double radius, centerx, centery;
        
        radius = 10.0;
        centerx = centery = 0;
        
        x = Integer.parseInt(JOptionPane.showInputDialog(null, 
            "Enter x coordinate of point"));
        y = Integer.parseInt(JOptionPane.showInputDialog(null, 
            "Enter y coordinate of point"));
        
        if ((Math.pow((x - centerx), 2) + Math.pow((x - centery), 2)) < 
                    (Math.pow(radius, 2))) {
            JOptionPane.showMessageDialog(null, "Point (" + x + ", " + y + ")"
                + " is in the circle.");
        } else 
            JOptionPane.showMessageDialog(null, "Point (" + x + ", " + y + ")"
                + " is not in the circle.");
    }
}