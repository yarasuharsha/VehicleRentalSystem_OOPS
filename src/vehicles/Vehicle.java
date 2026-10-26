package vehicles;

/**
 * Vehicle.java - Parent class (Base class)
 * 
 * OOPS Concept: INHERITANCE
 * This is the parent class. Car, Bike, and Van inherit from this class.
 * Common properties like vehicleId, brand, model, etc. are defined here.
 * 
 * OOPS Concept: METHOD OVERRIDING (base method)
 * The calculateRent() method is defined here and overridden by child classes.
 */
public class Vehicle {

    // Encapsulation: private fields
    private String vehicleId;
    private String brand;
    private String model;
    private String registrationNumber;
    private boolean available;
    private boolean underMaintenance;
    private String maintenanceReason;

    // Constructor
    public Vehicle(String vehicleId, String brand, String model, String registrationNumber) {
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        this.registrationNumber = registrationNumber;
        this.available = true; // new vehicle is available by default
        this.underMaintenance = false;
        this.maintenanceReason = "";
    }

    // ----- Getters and Setters -----

    public String getVehicleId() {
        return vehicleId;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isUnderMaintenance() {
        return underMaintenance;
    }

    public void setUnderMaintenance(boolean underMaintenance) {
        this.underMaintenance = underMaintenance;
    }

    public String getMaintenanceReason() {
        return maintenanceReason;
    }

    public void setMaintenanceReason(String maintenanceReason) {
        this.maintenanceReason = maintenanceReason;
    }

    public String getType() {
        return "Vehicle";
    }

    public String getStatus() {
        if (underMaintenance) {
            return "Under Maintenance";
        } else if (available) {
            return "Available";
        } else {
            return "Rented";
        }
    }

    /**
     * calculateRent() - calculates rental amount.
     * 
     * METHOD OVERRIDING: This is the base method.
     * Child classes (Car, Bike, Van, Scooter) override this method
     * to provide their own rental rate calculation.
     */
    public double calculateRent(int days) {
        return 0; // base implementation, overridden by child classes
    }

    /**
     * displayVehicleDetails() - prints vehicle information to console.
     */
    public void displayVehicleDetails() {
        System.out.println("  Vehicle ID   : " + vehicleId);
        System.out.println("  Brand        : " + brand);
        System.out.println("  Model        : " + model);
        System.out.println("  Reg. Number  : " + registrationNumber);
        System.out.println("  Status       : " + getStatus());
        if (underMaintenance && maintenanceReason != null && !maintenanceReason.trim().isEmpty()) {
            System.out.println("  Maint Reason : " + maintenanceReason);
        }
    }
}
