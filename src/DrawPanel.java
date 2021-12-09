
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

    void moveit(MotorVehicle car, double x, double y){
        if (isVolvo(car)) {
            volvoPoint.setLocation(x, y);
        } else if (isSaab(car)) {
            saabPoint.setLocation(x, y);
        } else if (isScania(car)) {
            scaniaPoint.setLocation(x, y);
        }
    }

    private boolean isScania(MotorVehicle car) {
        return car instanceof Scania;
    }

    private boolean isSaab(MotorVehicle car) {
        return car instanceof Saab95;
    }

    private boolean isVolvo(MotorVehicle car) {
        return car instanceof Volvo240;
    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        initializeWindow(x, y);
        try {
            initializeImages();
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }

    private void initializeImages() throws IOException {
        volvoImage = ImageIO.read(Objects.requireNonNull(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg")));
        saabImage = ImageIO.read(Objects.requireNonNull(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg")));
        scaniaImage = ImageIO.read(Objects.requireNonNull(DrawPanel.class.getResourceAsStream("pics/Scania.jpg")));
    }

    private void initializeWindow(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.darkGray);
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawImages(g);
    }

    private void drawImages(Graphics g) {
        g.drawImage(volvoImage, volvoPoint.x, volvoPoint.y, null); // see javadoc for more info on the parameters
        g.drawImage(saabImage, saabPoint.x, saabPoint.y, null); // see javadoc for more info on the parameters
        g.drawImage(scaniaImage, scaniaPoint.x, scaniaPoint.y, null); // see javadoc for more info on the parameters
    }
}
