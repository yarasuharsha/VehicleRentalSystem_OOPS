package services;

import java.util.ArrayList;
import vehicles.Vehicle;
import vehicles.Car;
import vehicles.Bike;
import vehicles.Van;
import vehicles.Scooter;
import customers.Customer;
import rentals.Rental;

/**
 * RentalService.java - Handles all business operations.
 * 
 * This class manages the lists of vehicles, customers, and rentals.
 * Main.java calls methods of this class to perform operations.
 */
public class RentalService {

    // ArrayList to store all vehicles, customers, and rentals
    private ArrayList<Vehicle> vehicleList;
    private ArrayList<Customer> customerList;
    private ArrayList<Rental> rentalList;
    private int rentalCounter;

    // Constructor: initializes the lists and adds sample data
    public RentalService() {
        vehicleList = new ArrayList<Vehicle>();
        customerList = new ArrayList<Customer>();
        rentalList = new ArrayList<Rental>();
        rentalCounter = 1;

        // Add pre-loaded vehicles for demonstration
        addSampleData();
    }

    /**
     * Adds sample vehicles so the system is not empty when it starts.
     */
    private void addSampleData() {
        vehicleList.add(new Car("V101", "Toyota", "Innova", "MH01AB1234", 7));
        vehicleList.add(new Car("V102", "Hyundai", "Creta", "MH02CD5678", 5));
        vehicleList.add(new Bike("V201", "Royal Enfield", "Classic 350", "MH01EF9012", 350));
        vehicleList.add(new Bike("V202", "Bajaj", "Pulsar 150", "MH02GH3456", 150));
        vehicleList.add(new Van("V301", "Tata", "Ace", "MH01IJ7890", 1.5));
        vehicleList.add(new Van("V302", "Mahindra", "Bolero Pickup", "MH02KL1234", 2.0));
        vehicleList.add(new Scooter("V401", "Honda", "Activa 6G", "MH01MN5678", 110));
        vehicleList.add(new Scooter("V402", "TVS", "Jupiter", "MH02OP9012", 110));
    }

    // ==================== VEHICLE OPERATIONS ====================

    /**
     * Adds a new vehicle to the system.
     */
    public void addVehicle(Vehicle vehicle) {
        vehicleList.add(vehicle);
        System.out.println("\n===== VEHICLE ADDED SUCCESSFULLY =====");
        vehicle.displayVehicleDetails();
    }

    /**
     * Displays all vehicles in the system.
     */
    public void displayAllVehicles() {
        if (vehicleList.isEmpty()) {
            System.out.println("\n  No vehicles in the system.");
            return;
        }
        System.out.println("\n===== ALL VEHICLES =====");
        for (int i = 0; i < vehicleList.size(); i++) {
            System.out.println("\n--- Vehicle " + (i + 1) + " ---");
            vehicleList.get(i).displayVehicleDetails();
        }
    }

    /**
     * Displays only available (not rented and not under maintenance) vehicles.
     */
    public void displayAvailableVehicles() {
        System.out.println("\n===== AVAILABLE VEHICLES =====");
        boolean found = false;
        int count = 0;
        for (int i = 0; i < vehicleList.size(); i++) {
            if (vehicleList.get(i).isAvailable() && !vehicleList.get(i).isUnderMaintenance()) {
                count++;
                System.out.println("\n--- Vehicle " + count + " ---");
                vehicleList.get(i).displayVehicleDetails();
                found = true;
            }
        }
        if (!found) {
            System.out.println("\n  No vehicles available right now.");
        }
    }

    /**
     * Finds a vehicle by its ID. Returns null if not found.
     */
    public Vehicle findVehicleById(String vehicleId) {
        for (int i = 0; i < vehicleList.size(); i++) {
            if (vehicleList.get(i).getVehicleId().equalsIgnoreCase(vehicleId)) {
                return vehicleList.get(i);
            }
        }
        return null;
    }

    /**
     * Checks if a vehicle ID already exists in the system.
     */
    public boolean vehicleIdExists(String vehicleId) {
        return findVehicleById(vehicleId) != null;
    }

    // ==================== CUSTOMER OPERATIONS ====================

    /**
     * Registers a new customer.
     */
    public void addCustomer(Customer customer) {
        customerList.add(customer);
        System.out.println("\n===== CUSTOMER REGISTERED SUCCESSFULLY =====");
        customer.displayCustomerDetails();
    }

    /**
     * Displays all registered customers in the system.
     */
    public void displayAllCustomers() {
        if (customerList.isEmpty()) {
            System.out.println("\n  No customers registered yet.");
            return;
        }
        System.out.println("\n===== ALL REGISTERED CUSTOMERS =====");
        for (int i = 0; i < customerList.size(); i++) {
            System.out.println("\n--- Customer " + (i + 1) + " ---");
            customerList.get(i).displayCustomerDetails();
        }
    }

    /**
     * Finds a customer by their ID. Returns null if not found.
     */
    public Customer findCustomerById(String customerId) {
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getCustomerId().equalsIgnoreCase(customerId)) {
                return customerList.get(i);
            }
        }
        return null;
    }

    /**
     * Checks if a customer ID already exists in the system.
     */
    public boolean customerIdExists(String customerId) {
        return findCustomerById(customerId) != null;
    }

    // ==================== RENTAL OPERATIONS ====================

    /**
     * Rents a vehicle to a customer.
     * The rental amount is calculated using vehicle.calculateRent(days).
     * Since each child class overrides calculateRent(), the correct rate is applied.
     */
    public void rentVehicle(String customerId, String vehicleId, int days) {
        // Find customer
        Customer customer = findCustomerById(customerId);
        if (customer == null) {
            System.out.println("\n  Error: Customer ID '" + customerId + "' not found.");
            System.out.println("  Please register the customer first (Option 4).");
            return;
        }

        // Find vehicle
        Vehicle vehicle = findVehicleById(vehicleId);
        if (vehicle == null) {
            System.out.println("\n  Error: Vehicle ID '" + vehicleId + "' not found.");
            return;
        }

        // Check maintenance status
        if (vehicle.isUnderMaintenance()) {
            System.out.println("\n  Error: Vehicle '" + vehicleId + "' is currently under maintenance ("
                    + vehicle.getMaintenanceReason() + ") and cannot be rented.");
            return;
        }

        // Check availability
        if (!vehicle.isAvailable()) {
            System.out.println("\n  Error: Vehicle '" + vehicleId + "' is already rented.");
            return;
        }

        // Validate days
        if (days <= 0) {
            System.out.println("\n  Error: Number of days must be greater than 0.");
            return;
        }

        // Create rental
        String rentalId = "R" + rentalCounter;
        rentalCounter++;

        Rental rental = new Rental(rentalId, customer, vehicle, days);

        // Mark vehicle as rented
        vehicle.setAvailable(false);

        // Add to rental list
        rentalList.add(rental);

        // Display success message
        System.out.println("\n===== RENTAL SUCCESSFUL =====");
        System.out.println("  Customer   : " + customer.getName());
        System.out.println("  Vehicle    : " + vehicle.getBrand() + " " + vehicle.getModel());
        System.out.println("  Days       : " + days);
        System.out.println("  Total Amt  : Rs." + rental.getTotalAmount());
    }

    /**
     * Returns a rented vehicle.
     */
    public void returnVehicle(String vehicleId) {
        Vehicle vehicle = findVehicleById(vehicleId);
        if (vehicle == null) {
            System.out.println("\n  Error: Vehicle ID '" + vehicleId + "' not found.");
            return;
        }

        if (vehicle.isAvailable()) {
            System.out.println("\n  Error: Vehicle '" + vehicleId + "' is not currently rented.");
            return;
        }

        // Find the latest active rental record for this vehicle
        Rental foundRental = null;
        for (int i = rentalList.size() - 1; i >= 0; i--) {
            Rental r = rentalList.get(i);
            if (r.getVehicle().getVehicleId().equalsIgnoreCase(vehicleId)) {
                foundRental = r;
                break;
            }
        }

        // Mark vehicle as available again
        vehicle.setAvailable(true);

        System.out.println("\n===== VEHICLE RETURNED =====");
        System.out.println("  Vehicle ID : " + vehicleId);
        if (foundRental != null) {
            System.out.println("  Customer   : " + foundRental.getCustomer().getName());
            System.out.println("  Amount Paid: Rs." + foundRental.getTotalAmount());
        }
        System.out.println("  Status     : Available");
    }

    /**
     * Displays all rental records.
     */
    public void displayRentals() {
        if (rentalList.isEmpty()) {
            System.out.println("\n  No rental records found.");
            return;
        }
        System.out.println("\n===== ALL RENTAL RECORDS =====");
        for (int i = 0; i < rentalList.size(); i++) {
            System.out.println("\n--- Rental " + (i + 1) + " ---");
            rentalList.get(i).displayRentalDetails();
        }
    }

    // ==================== SEARCH OPERATIONS ====================

    /**
     * Searches vehicle by Vehicle ID (case-insensitive).
     */
    public void searchByVehicleId(String vehicleId) {
        if (vehicleId == null || vehicleId.trim().isEmpty()) {
            System.out.println("\n  Error: Vehicle ID cannot be empty.");
            return;
        }
        Vehicle vehicle = findVehicleById(vehicleId.trim());
        if (vehicle == null) {
            System.out.println("\n  No vehicle found with Vehicle ID '" + vehicleId.trim() + "'.");
            return;
        }
        System.out.println("\n===== SEARCH RESULTS (VEHICLE ID: " + vehicleId.trim() + ") =====");
        vehicle.displayVehicleDetails();
    }

    /**
     * Searches vehicles by brand (case-insensitive, displays all matching).
     */
    public void searchByBrand(String brand) {
        if (brand == null || brand.trim().isEmpty()) {
            System.out.println("\n  Error: Brand name cannot be empty.");
            return;
        }
        String searchKey = brand.trim().toLowerCase();
        boolean found = false;
        int count = 0;
        System.out.println("\n===== SEARCH RESULTS FOR BRAND: '" + brand.trim() + "' =====");
        for (int i = 0; i < vehicleList.size(); i++) {
            Vehicle v = vehicleList.get(i);
            if (v.getBrand().equalsIgnoreCase(searchKey) || v.getBrand().toLowerCase().contains(searchKey)) {
                count++;
                System.out.println("\n--- Vehicle " + count + " ---");
                v.displayVehicleDetails();
                found = true;
            }
        }
        if (!found) {
            System.out.println("\n  No vehicles found for brand '" + brand.trim() + "'.");
        }
    }

    /**
     * Searches vehicles by type (Car, Bike, Scooter, Van) - case-insensitive.
     */
    public void searchByType(String type) {
        if (type == null || type.trim().isEmpty()) {
            System.out.println("\n  Error: Vehicle type cannot be empty.");
            return;
        }
        String searchKey = type.trim().toLowerCase();
        boolean found = false;
        int count = 0;
        System.out.println("\n===== SEARCH RESULTS FOR TYPE: '" + type.trim() + "' =====");
        for (int i = 0; i < vehicleList.size(); i++) {
            Vehicle v = vehicleList.get(i);
            if (v.getType().equalsIgnoreCase(searchKey) || v.getType().toLowerCase().contains(searchKey)) {
                count++;
                System.out.println("\n--- Vehicle " + count + " ---");
                v.displayVehicleDetails();
                found = true;
            }
        }
        if (!found) {
            System.out.println("\n  No vehicles found of type '" + type.trim() + "'.");
        }
    }

    // ==================== MAINTENANCE OPERATIONS ====================

    /**
     * Marks a vehicle as under maintenance with a specified reason.
     */
    public void markVehicleUnderMaintenance(String vehicleId, String reason) {
        Vehicle vehicle = findVehicleById(vehicleId);
        if (vehicle == null) {
            System.out.println("\n  Error: Vehicle ID '" + vehicleId + "' not found.");
            return;
        }

        // Do not allow an actively rented vehicle to be marked under maintenance
        if (!vehicle.isAvailable()) {
            System.out.println("\n  Error: Vehicle '" + vehicleId + "' is currently rented and cannot be marked under maintenance.");
            return;
        }

        if (vehicle.isUnderMaintenance()) {
            System.out.println("\n  Warning: Vehicle '" + vehicleId + "' is already under maintenance.");
            System.out.println("  Current Reason: " + vehicle.getMaintenanceReason());
            return;
        }

        if (reason == null || reason.trim().isEmpty()) {
            reason = "General maintenance";
        }

        vehicle.setUnderMaintenance(true);
        vehicle.setMaintenanceReason(reason.trim());

        System.out.println("\n===== VEHICLE MARKED UNDER MAINTENANCE =====");
        System.out.println("  Vehicle ID     : " + vehicle.getVehicleId());
        System.out.println("  Vehicle        : " + vehicle.getBrand() + " " + vehicle.getModel());
        System.out.println("  Status         : Under Maintenance");
        System.out.println("  Reason         : " + vehicle.getMaintenanceReason());
    }

    /**
     * Marks a vehicle as repaired and restores it to available status.
     */
    public void markVehicleRepaired(String vehicleId) {
        Vehicle vehicle = findVehicleById(vehicleId);
        if (vehicle == null) {
            System.out.println("\n  Error: Vehicle ID '" + vehicleId + "' not found.");
            return;
        }

        if (!vehicle.isUnderMaintenance()) {
            System.out.println("\n  Error: Vehicle '" + vehicleId + "' is not currently under maintenance.");
            return;
        }

        vehicle.setUnderMaintenance(false);
        vehicle.setMaintenanceReason("");
        // Make available again, provided it is not currently rented
        vehicle.setAvailable(true);

        System.out.println("\n===== VEHICLE REPAIRED SUCCESSFULLY =====");
        System.out.println("  Vehicle ID     : " + vehicle.getVehicleId());
        System.out.println("  Vehicle        : " + vehicle.getBrand() + " " + vehicle.getModel());
        System.out.println("  Status         : Available");
        System.out.println("  Maintenance reason cleared. Vehicle is ready for rent.");
    }
}
