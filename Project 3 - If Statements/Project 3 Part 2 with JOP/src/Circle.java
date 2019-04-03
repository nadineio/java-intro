import javax.swing.JOptionPane;
public class Circle {
    // Written by: Nadine Mansour
    // Determines whether a point lies within a circle of radius 10.0 centered at the origin if (x-originx)^2 +(y-originy)^2 is less than r^2
    
    public static void main(String[ ] args) {
        int x, y;
        String point, xstr, ystr;
        double radius, centerx, centery;
        
        radius = 10.0;
        centerx = centery = 0;
        
        point = JOptionPane.showInputDialog(null, "Enter a point with two coordinates");
        xstr = point.substring(0,1);
        ystr = point.substring(2,3);
        
        x = Integer.parseInt(xstr);
        y = Integer.parseInt(ystr);
        
        if ((Math.pow((x - centerx), 2) + Math.pow((x - centery), 2)) < (Math.pow(radius, 2))) {
            JOptionPane.showMessageDialog(null, "Point (" + x + ", " + y + ") is in the circle.");
        } else 
            JOptionPane.showMessageDialog(null, "Point (" + x + ", " + y + ") is not in the circle.");
    }
}