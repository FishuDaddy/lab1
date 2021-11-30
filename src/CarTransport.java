abstract public class CarTransport extends MotorVehicle {

    protected int maxWeight;
    protected int currentWeight;

    protected void transportAssembler(int maxWeight) {
        this.maxWeight = maxWeight;
        this.currentWeight = 0;
    }


}
