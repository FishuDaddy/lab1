import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with an listener (see below) that executes the statements
    // each step between delays.
    private final Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    protected ArrayList<MotorVehicle> cars = new ArrayList<>();
    protected CarFactory carFactory = new CarFactory();

    //methods:
    public void start(CarController cc) throws Exception {
        initializeCars(cc);

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }
    private static void initializeCars(CarController cc) throws Exception {
        cc.addCar(cc.carFactory.getCar(CarFactory.CarType.Volvo240));
        cc.cars.get(0).setCoordinates(0, 100);
        cc.cars.get(0).setDirection(30);
        cc.addCar(cc.carFactory.getCar(CarFactory.CarType.Scania));
        cc.cars.get(1).setCoordinates(0, 200);
        cc.cars.get(1).setDirection(40);
        cc.addCar(cc.carFactory.getCar(CarFactory.CarType.Saab95));
        cc.cars.get(2).setCoordinates(0, 300);
        cc.cars.get(2).setDirection(60);
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
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
    public void addCar(MotorVehicle car) {
        cars.add(car);
    }

    private void attemptToMove(MotorVehicle car) {
        try {
            moveCar(car);
        } catch (Exception ex) {
            System.out.println("Move error on " + car);
        }
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

    private void moveCar(MotorVehicle car) throws Exception {
        car.move();
        double x = car.getX();
        double y = car.getY();
    }

    void toggleEngineOn() {
        for (MotorVehicle car : cars) {
            car.toggleEngineOn();
        }
    }
    void toggleEngineOff() {
        for (MotorVehicle car : cars) {
            car.toggleEngineOff();
        }
    }
    void brake(int amount) {
        double brakeAmount = ((double) amount) / 100;
        for (MotorVehicle car : cars) {
            car.brake(brakeAmount);
        }
    }
    // Calls the gas method for each car once
    void gas(int amount) throws Exception {
        double gasAmount = ((double) amount) / 100;
        for (MotorVehicle car : cars) {
            car.gas(gasAmount);
        }
    }
    void setTurboOn() {
        for (MotorVehicle car : cars) {
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOn();
            }
        }
    }
    void setTurboOff() {
        for (MotorVehicle car : cars) {
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOff();
            }
        }
    }
    void liftBed() throws Exception {
        for (MotorVehicle car : cars) {
            if (car instanceof Scania && !((Scania) car).isPlatformRaised()) {
                ((Scania) car).togglePlatform();
            }
        }
    }
    void lowerBed() throws Exception {
        for (MotorVehicle car : cars) {
            if (car instanceof Scania && ((Scania) car).isPlatformRaised()) {
                ((Scania) car).togglePlatform();
            }
        }
    }
}
