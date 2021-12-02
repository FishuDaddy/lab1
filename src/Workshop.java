import com.sun.jdi.ClassObjectReference;
import com.sun.source.tree.ModifiersTree;

import java.util.ArrayList;

public class Workshop {
    protected ArrayList<MotorVehicle> storage;
    protected int storageLimit;
    protected String carType;
    protected boolean anyVehicle;

    public Workshop(int storageLimit, String carType) {
        this.storageLimit = storageLimit;
        this.storage = new ArrayList<>(storageLimit);
        if (carType == null) {
            this.anyVehicle = true;
        } else {
            this.carType = "class " + carType;
            this.anyVehicle = false;
        }

    }
    public String getStorage() {
        return "Current vehicles in storage > " + storage;
    }
    public void loadVehicle(MotorVehicle vehicle) throws Exception {
        if (storage.size() < storageLimit) {
            if (!anyVehicle) {
                if (vehicle.getClass().toString().equals(carType)) {
                    storage.add(vehicle);
                } else {
                    throw new Exception("Invalid car model");
                }
            } else {
                storage.add(vehicle);
            }
        } else {
            throw new Exception("Not enough storage");
        }
    }

    public void unloadVehicle(MotorVehicle vehicle) throws Exception {
        if (storage.size() > 0) {
            storage.remove(vehicle);
        } else {
            throw new Exception("Nothing in storage");
        }
    }


}
