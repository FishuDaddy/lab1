import com.sun.jdi.ClassObjectReference;
import com.sun.source.tree.ModifiersTree;

import java.util.ArrayList;

public class Workshop {
    protected ArrayList<Object> storage;
    protected int storageLimit;
    protected Object carType;
    protected boolean anyVehicle;

    public Workshop(int storageLimit, boolean anyVehicle, Object carType) {
        this.storageLimit = storageLimit;
        this.anyVehicle = anyVehicle;
        if (!anyVehicle) {
            this.carType = carType;
        } else {
            this.carType = null;
        }
    }

    public void loadVehicle(MotorVehicle vehicle) {
        if (anyVehicle && storage.size() < storageLimit) {
            storage.add(vehicle);
        } else if (!anyVehicle) {}

    }

    public void unloadVehicle(MotorVehicle vehicle) {
        storage.remove(vehicle);
    }


}
