package vehicle;

public class MaintenanceRecord {

    private int id;
    private int vehicleId;
    private String maintenanceDate;
    private int mileage;
    private String maintenanceContent;
    private int cost;

    public MaintenanceRecord(int id, int vehicleId, String maintenanceDate,
            int mileage, String maintenanceContent, int cost) {

        this.id = id;
        this.vehicleId = vehicleId;
        this.maintenanceDate = maintenanceDate;
        this.mileage = mileage;
        this.maintenanceContent = maintenanceContent;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public String getMaintenanceDate() {
        return maintenanceDate;
    }

    public int getMileage() {
        return mileage;
    }

    public String getMaintenanceContent() {
        return maintenanceContent;
    }

    public int getCost() {
        return cost;
    }

    public void setMaintenanceDate(String maintenanceDate) {
        this.maintenanceDate = maintenanceDate;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public void setMaintenanceContent(String maintenanceContent) {
        this.maintenanceContent = maintenanceContent;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}