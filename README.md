# Smart Vehicle Rental Management System

A beginner-friendly console-based Java application developed for an Object-Oriented Programming (OOP) college micro-project. It automates vehicle management, customer registration, vehicle rental transactions, returns, searching, and maintenance tracking.

---

## Student & Repository Information

- **Student Name:** YARASU HARSHAVARDHAN REDDY
- **Email ID:** yarasuharshavardhan.reddy.s.113@kalvium.community
- **Squad:** 113
- **GitHub Repository:** [https://github.com/yarasuharsha/VehicleRentalSystem_OOPS](https://github.com/yarasuharsha/VehicleRentalSystem_OOPS)


---

## Table of Contents

1. [Project Description](#project-description)
2. [Objectives](#objectives)
3. [Technologies Used](#technologies-used)
4. [OOP Concepts Implemented](#oop-concepts-implemented)
   - [Proposal Concepts (Inheritance & Method Overriding)](#proposal-concepts)
   - [Additional OOP Concepts](#additional-oop-concepts)
5. [Project Structure](#project-structure)
6. [Application Features](#application-features)
7. [Rental Calculation & Pricing](#rental-calculation--pricing)
8. [How to Compile and Run](#how-to-compile-and-run)
9. [Example Usage Workflow](#example-usage-workflow)
10. [Testing and Verification](#testing-and-verification)
11. [Limitations](#limitations)
12. [Future Improvements](#future-improvements)

---

## Project Description

The **Smart Vehicle Rental Management System** is a standalone, terminal-driven application designed to model a vehicle rental agency. It allows users to register various types of vehicles (Cars, Bikes, Vans, Scooters) and customers, rent vehicles with automatic rate calculations, return rented vehicles, perform case-insensitive multi-attribute vehicle searches, and manage maintenance workflows with repair status tracking.

All business operations and transaction records are managed through object-oriented design principles in Java, demonstrating clean class hierarchies, encapsulation, and dynamic method dispatch.

---

## Objectives

The system fulfills the following functional objectives implemented in the source code:

- **Vehicle Inventory Management:** Add new vehicles of specific types (Car, Bike, Van, Scooter) and display complete fleet details.
- **Availability Monitoring:** Filter and display only vehicles currently available for rent (excluding rented vehicles and those under maintenance).
- **Customer Registration & Listing:** Register customers with unique IDs, names, and contact details, and view all registered customers.
- **Rental Processing:** Rent available vehicles to registered customers for a specified duration, calculating rental costs automatically based on vehicle type.
- **Vehicle Returns:** Process returns of rented vehicles, updating vehicle availability status and referencing rental receipts.
- **Rental Records Tracking:** Maintain and view historical rental transaction logs with customer, vehicle, duration, and billing details.
- **Search Capabilities:** Search vehicles case-insensitively by Vehicle ID, Brand (displaying all matches), or Vehicle Type.
- **Maintenance & Repair Management:** Flag damaged vehicles under maintenance with documented reasons (preventing them from being rented or marked while rented), and restore them to available status once repaired.

---

## Technologies Used

- **Programming Language:** Java (JDK 8 or higher / Java 11+)
- **Architecture:** Object-Oriented Programming (OOP) layered design separating console I/O (`Main.java`) from core business logic (`RentalService.java`)
- **Data Structures / Collections:** `java.util.ArrayList` (in-memory dynamic storage for vehicles, customers, and rental transactions)
- **User Input Handling:** `java.util.Scanner` with input validation for integers, doubles, and strings to prevent runtime crashes
- **Operating Environment:** Windows PowerShell / Command Prompt / Linux / macOS Terminal

---

## OOP Concepts Implemented

### Proposal Concepts

These two primary concepts were specified in the college project proposal form and are verified to be **fully and correctly implemented**:

#### 1. Inheritance
- **Meaning:** Inheritance is an OOP mechanism where a new class (subclass/child class) inherits the fields and methods of an existing class (superclass/parent class) using the `extends` keyword, promoting code reusability.
- **Implementation in Project:**
  - **Parent Class:** `Vehicle`
  - **Child Classes:** `Car`, `Bike`, `Van`, and `Scooter`
- **Code Example:**
  ```java
  // In Car.java
  public class Car extends Vehicle {
      private int numberOfSeats;

      public Car(String vehicleId, String brand, String model, String registrationNumber, int numberOfSeats) {
          super(vehicleId, brand, model, registrationNumber); // Calls parent constructor
          this.numberOfSeats = numberOfSeats;
      }
  }
  ```
- **Viva Explanation:** Common vehicle attributes (`vehicleId`, `brand`, `model`, `registrationNumber`, `available`, `underMaintenance`, `maintenanceReason`) and base behaviors are defined once in `Vehicle`. Subclasses like `Car`, `Bike`, `Van`, and `Scooter` extend `Vehicle` and reuse these fields via `super(...)`, while adding their own specialized attributes (such as `numberOfSeats`, `engineCC`, or `cargoCapacity`).

---

#### 2. Method Overriding
- **Meaning:** Method Overriding occurs when a subclass provides its own specific implementation of a method that is already defined in its superclass, using the exact same method signature (name, parameter list, and return type).
- **Implementation in Project:**
  - Base method: `public double calculateRent(int days)` defined in `Vehicle.java`.
  - Overridden in child classes:
    - `Car.java`: `1500 * days`
    - `Bike.java`: `500 * days`
    - `Van.java`: `2000 * days`
    - `Scooter.java`: `300 * days`
  - Also demonstrated in `displayVehicleDetails()` and `getType()` across all child classes.
- **Code Example:**
  ```java
  // In Bike.java
  @Override
  public double calculateRent(int days) {
      return 500 * days; // Overrides base method with Bike-specific pricing
  }
  ```
- **Viva Explanation:** In `Vehicle.java`, `calculateRent(int days)` provides a default base calculation (`return 0;`). Each vehicle subclass overrides this method with its own rate. When `vehicle.calculateRent(days)` is called in `Rental.java`, Java dynamically invokes the child class's overridden version based on the actual object created at runtime.

---

### Additional OOP Concepts

The codebase also demonstrates several additional core OOP principles:

#### 3. Encapsulation
- **Meaning:** Bundling state (data fields) and behavior (methods) together, and restricting direct access to object components using access modifiers (`private`) with public getter and setter methods.
- **Implementation:**
  - All attributes in `Customer`, `Vehicle`, `Rental`, and `RentalService` are declared `private`.
  - Access is controlled via getters (e.g., `getVehicleId()`, `getCustomerId()`) and mutators (e.g., `setAvailable()`, `setUnderMaintenance()`).

#### 4. Classes and Objects
- **Meaning:** A class serves as a blueprint or template, while an object is a concrete, stateful runtime instance of that class.
- **Implementation:** The system defines real-world entities through classes (`Vehicle`, `Customer`, `Rental`, `RentalService`, `Main`) and instantiates objects dynamically (e.g., `new Customer(customerId, name, phone)`, `new Car(...)`).

#### 5. Constructor and Constructor Chaining (`super`)
- **Meaning:** Constructors initialize newly created objects. Subclasses use `super(...)` to invoke the parent constructor, ensuring inherited fields are properly initialized.
- **Implementation:** Subclasses `Car`, `Bike`, `Van`, and `Scooter` call `super(vehicleId, brand, model, registrationNumber)` before initializing subclass-specific fields.

#### 6. Runtime Polymorphism (Dynamic Method Dispatch)
- **Meaning:** The ability of a parent class reference variable to hold a child class object, resolving method calls at runtime rather than compile-time.
- **Implementation:**
  ```java
  // In RentalService.java
  ArrayList<Vehicle> vehicleList; // Stores Car, Bike, Van, Scooter under Vehicle reference
  Vehicle vehicle = vehicleList.get(i);
  vehicle.calculateRent(days);    // Calls overridden method of actual child object
  vehicle.displayVehicleDetails();// Calls overridden display of actual child object
  ```

> **Note on Abstraction:** `Vehicle` is a concrete base class with concrete implementations rather than an `abstract` class or interface. Abstraction is achieved conceptually through service-level decoupling (`RentalService` hides business logic from `Main`), but Java's `abstract` keyword and `interface` constructs are not used.

---

## Project Structure

The project is organized into modular packages separating concerns across entities, services, transactions, and UI:

```text
VehicleRentalSystem/
│
├── src/
│   ├── Main.java                          # Main console UI and menu controller
│   │
│   ├── vehicles/                          # Vehicle hierarchy package
│   │   ├── Vehicle.java                   # Base parent class with shared attributes
│   │   ├── Car.java                       # Subclass with seat capacity (Rs. 1500/day)
│   │   ├── Bike.java                      # Subclass with engine displacement (Rs. 500/day)
│   │   ├── Van.java                       # Subclass with cargo capacity (Rs. 2000/day)
│   │   └── Scooter.java                   # Subclass with engine CC (Rs. 300/day)
│   │
│   ├── customers/                         # Customer management package
│   │   └── Customer.java                  # Customer model (ID, Name, Phone Number)
│   │
│   ├── rentals/                           # Rental transaction package
│   │   └── Rental.java                    # Rental transaction linking Customer and Vehicle
│   │
│   └── services/                          # Business logic service package
│       └── RentalService.java             # Core controller managing lists, rentals, and maintenance
│
├── screenshots/                           # Folder for genuine application execution screenshots
│   └── README.md                          # Screenshot capture guidelines (01_main_menu to 09_test_results)
│
├── report/                                # Designated folder for college submission report
│   └── README.md                          # Project report placeholder
│
├── out/                                   # Compiled bytecode (.class) output directory (git-ignored)
├── README.md                              # Main project documentation and viva preparation guide
└── .gitignore                             # Ignores build artifacts (*.class, out/, build/)
```

### Source File Breakdown

| Package / Folder | File Name | Responsibility | Key OOP Features |
|---|---|---|---|
| *(default)* `src/` | **`Main.java`** | Console UI; handles user choices (0-11) and calls `RentalService`. | UI Layer, Input Validation |
| `vehicles` | **`Vehicle.java`** | Base class with attributes (`vehicleId`, `brand`, `model`, `regNumber`, `available`, `underMaintenance`, `maintenanceReason`). | Superclass, Encapsulation, Base methods |
| `vehicles` | **`Car.java`** | Extends `Vehicle`; adds `numberOfSeats`, overrides `calculateRent(int days)` (Rs. 1500/day). | Inheritance, Method Overriding |
| `vehicles` | **`Bike.java`** | Extends `Vehicle`; adds `engineCC`, overrides `calculateRent(int days)` (Rs. 500/day). | Inheritance, Method Overriding |
| `vehicles` | **`Van.java`** | Extends `Vehicle`; adds `cargoCapacity`, overrides `calculateRent(int days)` (Rs. 2000/day). | Inheritance, Method Overriding |
| `vehicles` | **`Scooter.java`** | Extends `Vehicle`; adds `engineCC`, overrides `calculateRent(int days)` (Rs. 300/day). | Inheritance, Method Overriding |
| `customers` | **`Customer.java`** | Entity class representing customer information (`customerId`, `name`, `phoneNumber`). | Encapsulation, Data Modeling |
| `rentals` | **`Rental.java`** | Transaction record linking a `Customer`, a `Vehicle`, duration, and total amount. | Composition, Polymorphic Calculation |
| `services` | **`RentalService.java`** | Controller managing dynamic `ArrayList` collections of vehicles, customers, and rentals. | Business Logic, Separation of Concerns |

---

## Application Features

The main console menu provides 11 operational options and an exit option:

1. **Add Vehicle:** Interactively register a new vehicle by specifying type (1: Car, 2: Bike, 3: Van, 4: Scooter), unique Vehicle ID, Brand, Model, Registration Number, and type-specific attributes.
2. **Display All Vehicles:** View all vehicles currently loaded in the system along with their type, specs, rates, and current status (`Available`, `Rented`, or `Under Maintenance`).
3. **Display Available Vehicles:** View only vehicles ready for rent. Excludes rented vehicles and vehicles under maintenance.
4. **Register Customer:** Register new customers with a unique Customer ID, full name, and phone number.
5. **Rent a Vehicle:** Select a registered customer, an available vehicle, and rental duration in days. Computes total rent automatically and marks the vehicle as rented.
6. **Return a Vehicle:** Process a vehicle return by Vehicle ID. Updates the vehicle status back to `Available` and displays the return receipt.
7. **View Rental Details:** Review all historical rental transactions including Rental ID, Customer Name, Vehicle Brand/Model, Vehicle ID, rental duration, and total amount billed.
8. **View All Customers:** Display all registered customer records (Customer ID, Name, Phone Number).
9. **Search Vehicles:** Search inventory case-insensitively using:
   - **Vehicle ID:** Returns exact matching vehicle.
   - **Brand:** Displays all matching vehicles from that brand (e.g., `Toyota`, `Honda`).
   - **Vehicle Type:** Displays all vehicles of type `Car`, `Bike`, `Scooter`, or `Van`.
10. **Mark Vehicle Under Maintenance:** Tag an available vehicle under maintenance by entering its Vehicle ID and a specific reason (e.g., `"Brake repair"`, `"Engine overhaul"`). Actively rented vehicles are protected and cannot be placed under maintenance.
11. **Mark Vehicle Repaired:** Remove maintenance status and clear the maintenance reason for a vehicle, returning it to `Available` status.
0. **Exit:** Terminate the program with a friendly goodbye message.

---

## Rental Calculation & Pricing

The total rental price is calculated dynamically using:

$$\text{Total Amount} = \text{Daily Rental Rate} \times \text{Number of Days}$$

The rates defined in the respective child classes are:

| Vehicle Type | Daily Rate (INR) | Class Implementation | Calculation Formula in Code |
|---|---|---|---|
| **Car** | Rs. 1,500 / day | `Car.java` | `1500 * days` |
| **Bike** | Rs. 500 / day | `Bike.java` | `500 * days` |
| **Van** | Rs. 2,000 / day | `Van.java` | `2000 * days` |
| **Scooter** | Rs. 300 / day | `Scooter.java` | `300 * days` |

Pre-loaded sample data available at startup:
- **Cars:** `V101` (Toyota Innova, 7 seats), `V102` (Hyundai Creta, 5 seats)
- **Bikes:** `V201` (Royal Enfield Classic 350, 350 CC), `V202` (Bajaj Pulsar 150, 150 CC)
- **Vans:** `V301` (Tata Ace, 1.5 tons), `V302` (Mahindra Bolero Pickup, 2.0 tons)
- **Scooters:** `V401` (Honda Activa 6G, 110 CC), `V402` (TVS Jupiter, 110 CC)

---

## How to Compile and Run

### Quick Start Commands
Run these commands from the `VehicleRentalSystem` directory in PowerShell:

```powershell
# 1. Compile the project
javac -sourcepath src -d out src/Main.java

# 2. Run the application
java -cp out Main
```

> **Tip (One-Liner in PowerShell):**
> Use a semicolon (`;`) to compile and run together:
> ```powershell
> javac -sourcepath src -d out src/Main.java; java -cp out Main
> ```

---

### Prerequisites
- **Java Development Kit (JDK):** Version 8 or higher installed on your computer.
- Verify installation in PowerShell:
  ```powershell
  javac -version
  java -version
  ```

### Step-by-Step Instructions

1. Open PowerShell and clone or navigate to the project directory:
   ```powershell
   git clone https://github.com/yarasuharsha/VehicleRentalSystem_OOPS.git
   cd VehicleRentalSystem
   ```

2. Compile all source files into the `out` directory:
   ```powershell
   javac -sourcepath src -d out src/Main.java
   ```
   *Alternative command (compiles all files recursively):*
   ```powershell
   javac -d out (Get-ChildItem -Path src -Filter *.java -Recurse).FullName
   ```

3. Run the application:
   ```powershell
   java -cp out Main
   ```

---

## Example Usage Workflow

1. **Start Application:** Run `java -cp out Main`. The banner and Main Menu appear.
2. **Register a Customer (Option 4):**
   - Enter Customer ID: `C101`
   - Enter Name: `John Doe`
   - Enter Phone: `9876543210`
3. **Search for a Vehicle (Option 9):**
   - Choose search type `2` (By Brand).
   - Enter Brand: `Toyota`.
   - The system displays `V101` (Toyota Innova).
4. **Rent the Vehicle (Option 5):**
   - Enter Customer ID: `C101`
   - Enter Vehicle ID: `V101`
   - Enter Days: `3`
   - Total amount is computed: `Rs. 4500.0` (3 days × Rs. 1500/day).
5. **View Rental Records (Option 7):**
   - Transaction `R1` displays customer `John Doe`, vehicle `Toyota Innova`, and total amount paid.
6. **Return the Vehicle (Option 6):**
   - Enter Vehicle ID: `V101`. Vehicle status reverts to `Available`.
7. **Maintenance Workflow (Option 10 & 11):**
   - Flag `V201` under maintenance with reason `"Oil leak"`.
   - Attempting to rent `V201` shows an error preventing rental.
   - Select Option 11 with ID `V201` to complete repairs and restore availability.
8. **Exit (Option 0):** Cleanly exit the system.

---

## Testing and Verification

### Executed Tests (Verified Working)

The following test scenarios have been compiled and verified using PowerShell test automation:

| Test Case | Steps Taken | Expected Output | Actual Result |
|---|---|---|---|
| **Empty Customers State** | Selected Option 8 before registering customers | `"No customers registered yet."` | **PASS** |
| **Search by Vehicle ID** | Searched `v101` (lowercase) | Displayed Toyota Innova details and Rs. 1500 rate | **PASS** |
| **Search by Brand** | Searched `toyota` | Displayed all matching Toyota vehicles | **PASS** |
| **Search by Type** | Searched `scooter` | Displayed Activa 6G and TVS Jupiter | **PASS** |
| **Search Nonexistent** | Searched `V999` and brand `Ferrari` | Displayed clear `"No vehicle found..."` message | **PASS** |
| **Mark Under Maintenance** | Marked `V101` with reason `"Brake repair"` | Status set to `Under Maintenance`, excluded from available list | **PASS** |
| **Block Renting Damaged** | Attempted to rent `V101` while under maintenance | Blocked with maintenance error message | **PASS** |
| **Block Maintenance on Rented** | Rented `V201` then tried to mark it under maintenance | Blocked with `"Vehicle is currently rented"` error | **PASS** |
| **Repairs Restoration** | Marked `V101` repaired | Reason cleared, status restored to `Available`, re-rental succeeded | **PASS** |
| **Input Validation** | Entered non-numeric characters (`abc`) and out-of-range (`99`) | Caught gracefully without crashing program | **PASS** |

### Suggested Additional Tests (For Viva Demonstration)
- Add a new Scooter through Option 1 and verify its daily rate in rentals (Rs. 300/day).
- Add two vehicles with the same brand and test brand search matching.
- Try returning a vehicle that is not currently rented to verify error handling.

---

## Limitations

- **In-Memory Storage:** Vehicles, customers, and rental logs are stored in Java `ArrayList` objects in RAM. Data is reset to default sample values upon application exit.
- **Console Interface:** Interaction is terminal-based; no graphical user interface (GUI) or web interface is provided.
- **Single-User Session:** Designed for a single operator at a time (no multi-threading or concurrent access control).
- **Date Handling:** Rental durations are recorded as integer days rather than calendar timestamp ranges.

---

## Future Improvements

- **Persistent Storage:** Integrate file persistence (JSON/CSV) or an SQL database (MySQL / SQLite / JDBC) to retain records across sessions.
- **Graphical User Interface (GUI):** Implement a modern JavaFX or Swing desktop interface or a Spring Boot web portal.
- **Date Picker and Billing Invoice:** Incorporate `java.time.LocalDate` for exact pickup/drop-off dates, penalty fines for late returns, and downloadable PDF invoices.
- **Customer Discounts:** Add promo code support and loyalty reward discounts for repeat customers.
