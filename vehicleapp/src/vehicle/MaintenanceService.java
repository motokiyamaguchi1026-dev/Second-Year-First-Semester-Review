package vehicle;

import java.util.ArrayList;

public class MaintenanceService {

    private ArrayList<Vehicle> vehicles = new ArrayList<>();

    public void addVehicle(String vehicleName, String vehicleNumber) {
        int id = vehicles.size() + 1;

        Vehicle vehicle = new Vehicle(id, vehicleName, vehicleNumber);

        vehicles.add(vehicle);
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }
}