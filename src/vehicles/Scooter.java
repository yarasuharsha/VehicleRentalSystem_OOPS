package vehicles;

/**
 * Scooter.java - Child class of Vehicle
 * 
 * OOPS Concept: INHERITANCE
 * Scooter extends Vehicle — inherits all fields and methods from Vehicle.
 * 
 * OOPS Concept: METHOD OVERRIDING
 * Scooter overrides calculateRent() to apply scooter-specific pricing.
 * Scooter rate: Rs.300/day
 */
public class Scooter extends Vehicle {

    private int engineCC;

    // Constructor: uses super() to call the parent class constructor
    public Scooter(String vehicleId, String brand, String model, String registrationNumber, int engineCC) {
        super(vehicleId, brand, model, registrationNumber);
        this.engineCC = engineCC;
    }

    public int getEngineCC() {
        return engineCC;
    }

    @Override
    public String getType() {
        return "Scooter";
    }

    /**
     * METHOD OVERRIDING: Scooter's own version of calculateRent().
     * Scooter charges Rs.300 per day.
     */
    @Override
    public double calculateRent(int days) {
        return 300 * days;
    }

    @Override
    public void displayVehicleDetails() {
        System.out.println("  Type         : Scooter");
        super.displayVehicleDetails();
        System.out.println("  Engine       : " + engineCC + " CC");
        System.out.println("  Rent Rate    : Rs.300/day");
    }
}
