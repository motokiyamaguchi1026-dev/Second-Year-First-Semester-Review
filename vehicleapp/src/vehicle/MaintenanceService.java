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
    
    
    public boolean updateRecord(int recordId, String maintenanceDate,
            int mileage, String maintenanceContent, int cost) {

        for (MaintenanceRecord record : records) {

            if (record.getId() == recordId) {

                record.setMaintenanceDate(maintenanceDate);
                record.setMileage(mileage);
                record.setMaintenanceContent(maintenanceContent);
                record.setCost(cost);

                return true;
            }
        }

        return false;
    }
    
    
    public boolean deleteRecord(int recordId) {

        for (int i = 0; i < records.size(); i++) {

            if (records.get(i).getId() == recordId) {
                records.remove(i);
                return true;
            }
        }

        return false;
    }
    
    public boolean deleteVehicle(int vehicleId) {

        for (int i = 0; i < vehicles.size(); i++) {

            if (vehicles.get(i).getId() == vehicleId) {

                vehicles.remove(i);

                for (int j = records.size() - 1; j >= 0; j--) {

                    if (records.get(j).getVehicleId() == vehicleId) {
                        records.remove(j);
                    }
                }

                return true;
            }
        }

        return false;
    }
    
    
    
}