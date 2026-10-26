package vehicles;

/**
 * Bike.java - Child class of Vehicle
 * 
 * OOPS Concept: INHERITANCE
 * Bike extends Vehicle — inherits all fields and methods from Vehicle.
 * 
 * OOPS Concept: METHOD OVERRIDING
 * Bike overrides calculateRent() to apply bike-specific pricing.
 * Bike rate: Rs.500/day
 */
public class Bike extends Vehicle {

    private int engineCC;

    // Constructor: uses super() to call the parent class constructor
    public Bike(String vehicleId, String brand, String model, String registrationNumber, int engineCC) {
        super(vehicleId, brand, model, registrationNumber);
        this.engineCC = engineCC;
    }

    public int getEngineCC() {
        return engineCC;
    }

    @Override
    public String getType() {
        return "Bike";
    }

    /**
     * METHOD OVERRIDING: Bike's own version of calculateRent().
     * Bike charges Rs.500 per day.
     */
    @Override
    public double calculateRent(int days) {
        return 500 * days;
    }

    @Override
    public void displayVehicleDetails() {
        System.out.println("  Type         : Bike");
        super.displayVehicleDetails();
        System.out.println("  Engine       : " + engineCC + " CC");
        System.out.println("  Rent Rate    : Rs.500/day");
    }
}
