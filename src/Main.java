import java.util.Scanner;
import services.RentalService;
import vehicles.Vehicle;
import vehicles.Car;
import vehicles.Bike;
import vehicles.Van;
import vehicles.Scooter;
import customers.Customer;

/**
 * Main.java - Entry point of the application.
 * 
 * This class only handles:
 * - Displaying the menu
 * - Taking user input
 * - Calling methods from RentalService
 * 
 * All business logic is in RentalService.java.
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        RentalService service = new RentalService();

        int choice = -1;

        System.out.println("\n**********************************************");
        System.out.println("*   SMART VEHICLE RENTAL MANAGEMENT SYSTEM   *");
        System.out.println("**********************************************");

        do {
            // Display menu
            System.out.println("\n=============== MAIN MENU ===============");
            System.out.println("  1. Add Vehicle");
            System.out.println("  2. Display All Vehicles");
            System.out.println("  3. Display Available Vehicles");
            System.out.println("  4. Register Customer");
            System.out.println("  5. Rent a Vehicle");
            System.out.println("  6. Return a Vehicle");
            System.out.println("  7. View Rental Details");
            System.out.println("  8. View All Customers");
            System.out.println("  9. Search Vehicles");
            System.out.println(" 10. Mark Vehicle Under Maintenance");
            System.out.println(" 11. Mark Vehicle Repaired");
            System.out.println("  0. Exit");
            System.out.println("==========================================");
            System.out.print("  Enter your choice: ");

            // Input validation for menu choice
            if (!scanner.hasNextInt()) {
                System.out.println("\n  Error: Please enter a valid number.");
                scanner.next();
                continue;
            }
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    addNewVehicle(scanner, service);
                    break;

                case 2:
                    service.displayAllVehicles();
                    break;

                case 3:
                    service.displayAvailableVehicles();
                    break;

                case 4:
                    registerCustomer(scanner, service);
                    break;

                case 5:
                    rentVehicle(scanner, service);
                    break;

                case 6:
                    returnVehicle(scanner, service);
                    break;

                case 7:
                    service.displayRentals();
                    break;

                case 8:
                    service.displayAllCustomers();
                    break;

                case 9:
                    searchVehicles(scanner, service);
                    break;

                case 10:
                    markUnderMaintenance(scanner, service);
                    break;

                case 11:
                    markRepaired(scanner, service);
                    break;

                case 0:
                    System.out.println("\n  Thank you for using the Vehicle Rental System!");
                    System.out.println("  Goodbye!\n");
                    break;

                default:
                    System.out.println("\n  Error: Invalid choice. Please enter 0-11.");
            }

        } while (choice != 0);

        scanner.close();
    }

    /**
     * Handles adding a new vehicle.
     * Takes input and creates the appropriate child class object.
     */
    private static void addNewVehicle(Scanner scanner, RentalService service) {
        System.out.println("\n===== ADD NEW VEHICLE =====");
        System.out.println("  Select vehicle type:");
        System.out.println("    1. Car");
        System.out.println("    2. Bike");
        System.out.println("    3. Van");
        System.out.println("    4. Scooter");
        System.out.print("  Enter type (1/2/3/4): ");

        if (!scanner.hasNextInt()) {
            System.out.println("\n  Error: Invalid input.");
            scanner.next();
            return;
        }
        int type = scanner.nextInt();
        scanner.nextLine();

        if (type < 1 || type > 4) {
            System.out.println("\n  Error: Invalid vehicle type.");
            return;
        }

        System.out.print("  Enter Vehicle ID: ");
        String vehicleId = scanner.nextLine().trim();
        if (vehicleId.isEmpty()) {
            System.out.println("\n  Error: Vehicle ID cannot be empty.");
            return;
        }
        if (service.vehicleIdExists(vehicleId)) {
            System.out.println("\n  Error: Vehicle ID '" + vehicleId + "' already exists.");
            return;
        }

        System.out.print("  Enter Brand: ");
        String brand = scanner.nextLine().trim();

        System.out.print("  Enter Model: ");
        String model = scanner.nextLine().trim();

        System.out.print("  Enter Registration Number: ");
        String regNumber = scanner.nextLine().trim();

        Vehicle vehicle;

        switch (type) {
            case 1:
                System.out.print("  Enter Number of Seats: ");
                int seats = scanner.nextInt();
                scanner.nextLine();
                vehicle = new Car(vehicleId, brand, model, regNumber, seats);
                break;
            case 2:
                System.out.print("  Enter Engine CC: ");
                int cc = scanner.nextInt();
                scanner.nextLine();
                vehicle = new Bike(vehicleId, brand, model, regNumber, cc);
                break;
            case 3:
                System.out.print("  Enter Cargo Capacity (in tons): ");
                double capacity = scanner.nextDouble();
                scanner.nextLine();
                vehicle = new Van(vehicleId, brand, model, regNumber, capacity);
                break;
            case 4:
                System.out.print("  Enter Engine CC: ");
                int scooterCC = scanner.nextInt();
                scanner.nextLine();
                vehicle = new Scooter(vehicleId, brand, model, regNumber, scooterCC);
                break;
            default:
                System.out.println("\n  Error: Invalid type.");
                return;
        }

        service.addVehicle(vehicle);
    }

    /**
     * Handles registering a new customer.
     */
    private static void registerCustomer(Scanner scanner, RentalService service) {
        System.out.println("\n===== REGISTER CUSTOMER =====");

        System.out.print("  Enter Customer ID: ");
        String customerId = scanner.nextLine().trim();
        if (customerId.isEmpty()) {
            System.out.println("\n  Error: Customer ID cannot be empty.");
            return;
        }
        if (service.customerIdExists(customerId)) {
            System.out.println("\n  Error: Customer ID '" + customerId + "' already exists.");
            return;
        }

        System.out.print("  Enter Name: ");
        String name = scanner.nextLine().trim();

        System.out.print("  Enter Phone Number: ");
        String phone = scanner.nextLine().trim();

        Customer customer = new Customer(customerId, name, phone);
        service.addCustomer(customer);
    }

    /**
     * Handles renting a vehicle to a customer.
     */
    private static void rentVehicle(Scanner scanner, RentalService service) {
        System.out.println("\n===== RENT A VEHICLE =====");

        // Show available vehicles first
        service.displayAvailableVehicles();

        System.out.print("\n  Enter Customer ID: ");
        String customerId = scanner.nextLine().trim();

        System.out.print("  Enter Vehicle ID to rent: ");
        String vehicleId = scanner.nextLine().trim();

        System.out.print("  Enter number of days: ");
        if (!scanner.hasNextInt()) {
            System.out.println("\n  Error: Please enter a valid number of days.");
            scanner.next();
            return;
        }
        int days = scanner.nextInt();
        scanner.nextLine();

        service.rentVehicle(customerId, vehicleId, days);
    }

    /**
     * Handles returning a rented vehicle.
     */
    private static void returnVehicle(Scanner scanner, RentalService service) {
        System.out.println("\n===== RETURN A VEHICLE =====");

        System.out.print("  Enter Vehicle ID to return: ");
        String vehicleId = scanner.nextLine().trim();

        service.returnVehicle(vehicleId);
    }

    /**
     * Handles searching for vehicles by ID, Brand, or Type.
     */
    private static void searchVehicles(Scanner scanner, RentalService service) {
        System.out.println("\n===== SEARCH VEHICLES =====");
        System.out.println("  1. Search by Vehicle ID");
        System.out.println("  2. Search by Brand");
        System.out.println("  3. Search by Vehicle Type (Car, Bike, Scooter, Van)");
        System.out.print("  Enter your choice (1/2/3): ");

        if (!scanner.hasNextInt()) {
            System.out.println("\n  Error: Invalid input. Please enter 1, 2, or 3.");
            scanner.next();
            return;
        }
        int searchChoice = scanner.nextInt();
        scanner.nextLine();

        switch (searchChoice) {
            case 1:
                System.out.print("  Enter Vehicle ID to search: ");
                String vId = scanner.nextLine().trim();
                service.searchByVehicleId(vId);
                break;
            case 2:
                System.out.print("  Enter Brand name to search: ");
                String brand = scanner.nextLine().trim();
                service.searchByBrand(brand);
                break;
            case 3:
                System.out.print("  Enter Vehicle Type (Car, Bike, Scooter, Van): ");
                String type = scanner.nextLine().trim();
                service.searchByType(type);
                break;
            default:
                System.out.println("\n  Error: Invalid search choice. Please enter 1-3.");
        }
    }

    /**
     * Handles marking a vehicle under maintenance.
     */
    private static void markUnderMaintenance(Scanner scanner, RentalService service) {
        System.out.println("\n===== MARK VEHICLE UNDER MAINTENANCE =====");
        System.out.print("  Enter Vehicle ID: ");
        String vehicleId = scanner.nextLine().trim();
        if (vehicleId.isEmpty()) {
            System.out.println("\n  Error: Vehicle ID cannot be empty.");
            return;
        }

        System.out.print("  Enter Reason (e.g., engine damage, brake repair, accident damage): ");
        String reason = scanner.nextLine().trim();
        if (reason.isEmpty()) {
            System.out.println("\n  Error: Maintenance reason cannot be empty.");
            return;
        }

        service.markVehicleUnderMaintenance(vehicleId, reason);
    }

    /**
     * Handles marking a vehicle as repaired and available again.
     */
    private static void markRepaired(Scanner scanner, RentalService service) {
        System.out.println("\n===== MARK VEHICLE REPAIRED =====");
        System.out.print("  Enter Vehicle ID: ");
        String vehicleId = scanner.nextLine().trim();
        if (vehicleId.isEmpty()) {
            System.out.println("\n  Error: Vehicle ID cannot be empty.");
            return;
        }

        service.markVehicleRepaired(vehicleId);
    }
}
