package service;

import java.util.ArrayList;

import model.MaintenanceRecord;
import model.Vehicle;

//車両と整備記録のデータ管理
public class MaintenanceService {

    //車両保管リスト
    private ArrayList<Vehicle> vehicles = new ArrayList<>();

    //整備記録保管リスト
    private ArrayList<MaintenanceRecord> records = new ArrayList<>();

    private int nextVehicleId = 1;

    private int nextRecordId = 1;

    // 車両を登録するやつ
    public void addVehicle(String vehicleName, String vehicleNumber) {

        Vehicle vehicle = new Vehicle(
                nextVehicleId,
                vehicleName,
                vehicleNumber
        );
        vehicles.add(vehicle);
        nextVehicleId++;
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    //整備記録を保存するやつ
    public void addRecord(int vehicleId, String maintenanceDate,
            int mileage, String maintenanceContent, int cost) {

        MaintenanceRecord record = new MaintenanceRecord(
                nextRecordId,
                vehicleId,
                maintenanceDate,
                mileage,
                maintenanceContent,
                cost
        );
        records.add(record);
        nextRecordId++;
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

    public boolean existsVehicle(int vehicleId) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getId() == vehicleId) {
                return true;
            }
        }
        return false;
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

    //検索用
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

    // 整備記録更新
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

    // 整備記録を削除する
    public boolean deleteRecord(int recordId) {

        for (int i = 0; i < records.size(); i++) {
            if (records.get(i).getId() == recordId) {
                records.remove(i);
                return true;
            }
        }
        return false;
    }

    // 車両を削除する
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