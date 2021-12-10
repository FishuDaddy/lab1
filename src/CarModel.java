import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CarModel {

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private final Timer timer;

    // The frame that represents this instance View of the MVC pattern
    private final CarView frame;
    private final CarController cc;
    // A list of cars, modify if needed
    protected ArrayList<MotorVehicle> cars = new ArrayList<>();
    protected CarFactory carFactory = new CarFactory();

    public CarModel() {

        try {
            initializeCars();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Start a new view and send a reference of self
        cc = new CarController(cars);
        frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        timer = new Timer(delay, new TimerListener());
        timer.start();
    }

    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (MotorVehicle car : cars) {
                outOfBoundsCheck(car);
                attemptToMove(car);
                double x = Math.round(car.getX());
                double y = Math.round(car.getY());
                frame.drawPanel.moveit(car, x, y);
                frame.drawPanel.repaint();
            }
        }
    }

    protected void initializeCars() throws Exception {
        addCar(CarFactory.CarType.Volvo240);
        addCar(CarFactory.CarType.Scania);
        addCar(CarFactory.CarType.Saab95);
        for (int i=0; i<cars.size(); i++) {
            cars.get(i).setCoordinates(0, i*100);
            cars.get(i).setDirection(20 + (i * 20));
        }
    }

    private void addCar(CarFactory.CarType car) throws Exception {
        cars.add(carFactory.getCar(car));
    }


    private void attemptToMove(MotorVehicle car) {
        try {
            moveCar(car);
        } catch (Exception ex) {
            System.out.println("Move error on " + car);
        }
    }

    private void moveCar(MotorVehicle car) throws Exception {
        car.move();
        //double x = car.getX();
        //double y = car.getY();
    }

    private void outOfBoundsCheck(MotorVehicle car) {
        if (car.getX() < 0) {
            car.incDirection(180);
        } else if (car.getY() < 0) {
            car.incDirection(180);
        } else if (car.getX() > frame.getWidth() - 100) { // Image width
            car.incDirection(180);
        } else if (car.getY() > frame.getHeight() - 300) { // Image height + control bar height
            car.incDirection(180);
        }
    }
}
