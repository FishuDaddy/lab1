public class CarApp {
    public static void main(String[] args) throws Exception {
        CarController cc = new CarController();
        cc.start(cc);
    }
}
