public class CarFactory {

    enum CarType {
        Volvo240,
        Saab95,
        Scania
    }

    protected MotorVehicle getCar(CarType type) throws Exception {
        switch (type) {
            case Volvo240 -> {
                return new Volvo240();
            }
            case Saab95 -> {
                return new Saab95();
            }
            case Scania -> {
                return new Scania();
            }
            default -> throw new Exception("Type not allowed");
        }
    }
}
