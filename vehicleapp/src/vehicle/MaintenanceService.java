package vehicle;

import java.util.ArrayList;

public class MaintenanceService {

    private ArrayList<Vehicle> vehicles = new ArrayList<>();
    private ArrayList<MaintenanceRecord> records = new ArrayList<>();

    public void addVehicle(String vehicleName, String vehicleNumber) {
        int id = vehicles.size() + 1;

        Vehicle vehicle = new Vehicle(id, vehicleName, vehicleNumber);

        vehicles.add(vehicle);
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public void addRecord(int vehicleId, String maintenanceDate,
            int mileage, String maintenanceContent, int cost) {

        int id = records.size() + 1;

        MaintenanceRecord record = new MaintenanceRecord(
                id,
                vehicleId,
                maintenanceDate,
                mileage,
                maintenanceContent,
                cost
        );

        records.add(record);
    }

    public ArrayList<MaintenanceRecord> getRecords() {
        return records;
    }
    
    public ArrayList<MaintenanceRecord> getRecordsByVehicleId(int vehicleId) {

        ArrayList<MaintenanceRecord> vehicleRecords = new ArrayList<>();

        for (MaintenanceRecord record : records) {

            if (record.getVehicleId() == vehicleId) {
                vehicleRecords.add(record);
            }
        }

        return vehicleRecords;
    }
    
    public ArrayList<Vehicle> searchVehiclesByName(String keyword) {

        ArrayList<Vehicle> result = new ArrayList<>();

        for (Vehicle vehicle : vehicles) {

            if (vehicle.getVehicleName().contains(keyword)) {
                result.add(vehicle);
            }
        }

        return result;
    }

    public ArrayList<Vehicle> searchVehiclesByNumber(String keyword) {

        ArrayList<Vehicle> result = new ArrayList<>();

        for (Vehicle vehicle : vehicles) {

            if (vehicle.getVehicleNumber().contains(keyword)) {
                result.add(vehicle);
            }
        }

        return result;
    }

    public ArrayList<MaintenanceRecord> searchRecordsByContent(
            int vehicleId, String keyword) {

        ArrayList<MaintenanceRecord> result = new ArrayList<>();

        for (MaintenanceRecord record : records) {

            if (record.getVehicleId() == vehicleId
                    && record.getMaintenanceContent().contains(keyword)) {

                result.add(record);
            }
        }

        return result;
    }
    
    
    
}