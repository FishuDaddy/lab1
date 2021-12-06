
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel{

    // Just a single image, TODO: Generalize
    BufferedImage volvoImage, saabImage, scaniaImage;
    // To keep track of a singles cars position
    Point volvoPoint = new Point();
    Point saabPoint = new Point();
    Point scaniaPoint = new Point();

    void moveit(MotorVehicle car, int x, int y){
        if (car instanceof Volvo240) {
            volvoPoint.setLocation(x, y);
        } else if (car instanceof Saab95) {
            saabPoint.setLocation(x, y);
        } else if (car instanceof Scania) {
            scaniaPoint.setLocation(x, y);
        }
    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.darkGray);
        // Print an error message in case file is not found with a try/catch block
        try {
            // You can remove the "pics" part if running outside of IntelliJ and
            // everything is in the same main folder.
            // volvoImage = ImageIO.read(new File("Volvo240.jpg"));

            // Remember to right-click src New -> Package -> name: pics -> MOVE *.jpg to pics.
            // if you are starting in IntelliJ.
            volvoImage = ImageIO.read(Objects.requireNonNull(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg")));
            saabImage = ImageIO.read(Objects.requireNonNull(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg")));
            scaniaImage = ImageIO.read(Objects.requireNonNull(DrawPanel.class.getResourceAsStream("pics/Scania.jpg")));
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(volvoImage, volvoPoint.x, volvoPoint.y, null); // see javadoc for more info on the parameters
        g.drawImage(saabImage, saabPoint.x, saabPoint.y, null); // see javadoc for more info on the parameters
        g.drawImage(scaniaImage, scaniaPoint.x, scaniaPoint.y, null); // see javadoc for more info on the parameters
    }
}
