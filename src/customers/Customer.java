package customers;

/**
 * Customer.java - Represents a customer who rents vehicles.
 * 
 * This is a simple class with encapsulated fields.
 * It is a supporting class used by Rental and RentalService.
 */
public class Customer {

    // Encapsulation: private fields
    private String customerId;
    private String name;
    private String phoneNumber;

    // Constructor
    public Customer(String customerId, String name, String phoneNumber) {
        this.customerId = customerId;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    // ----- Getters -----

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * displayCustomerDetails() - prints customer information to console.
     */
    public void displayCustomerDetails() {
        System.out.println("  Customer ID : " + customerId);
        System.out.println("  Name        : " + name);
        System.out.println("  Phone       : " + phoneNumber);
    }
}
