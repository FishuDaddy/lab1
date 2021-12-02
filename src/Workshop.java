import java.util.ArrayList;

public class Workshop<T> {

    protected ArrayList<T> storage;
    protected int storageLimit;
    private T type;
    protected boolean anyVehicle;

    public Workshop(int storageLimit) {
        this.storageLimit = storageLimit;
        this.storage = new ArrayList<>(storageLimit);
    }
    public int getStorageSize() {
        return storage.size();
    }

    public void loadVehicle(T vehicle) throws Exception {
        if (storage.size() < storageLimit) {
            storage.add(vehicle);
        } else {
            throw new Exception("Storage Full");
        }
    }
    public void unloadVehicle(T vehicle) throws Exception {
        if (storage.size() > 0) {
            storage.remove(vehicle);
        } else {
            throw new Exception("Nothing in storage");
        }
    }


}
