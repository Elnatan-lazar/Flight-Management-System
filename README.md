# Flight Management System

## Overview

This project demonstrates the Composite design pattern to manage flights and flight companies. The system allows for the creation of individual flights and organizations of flights into flight companies. Larger flight companies can also contain smaller flight companies, allowing for a hierarchical structure.

## Components

1. **Flight**: Represents an individual flight with a flight number.
2. **FlightCompany**: Represents a flight company that can hold both individual flights and other flight companies. It implements the Composite pattern to manage a hierarchy of flight components.

## Design Pattern

The Composite design pattern is used to allow a single entity (`Flight`) and a collection of entities (`FlightCompany`) to be treated uniformly. This pattern is useful for representing part-whole hierarchies.

### Classes

- **Flight**
  - **Attributes**:
    - `flightNumber` (String): The unique identifier for the flight.
  - **Methods**:
    - `display()`: Prints the flight number.

- **FlightCompany**
  - **Attributes**:
    - `companyName` (String): The name of the flight company.
    - `components` (List<FlightComponent>): A list of `FlightComponent` objects (both `Flight` and `FlightCompany` instances).
  - **Methods**:
    - `addComponent(FlightComponent component)`: Adds a flight or flight company to the list of components.
    - `removeComponent(FlightComponent component)`: Removes a flight or flight company from the list of components.
    - `display()`: Prints the company name and displays all contained flights and companies.

## Usage

1. **Create Individual Flights**:
   ```java
   Flight flight1 = new Flight("ABC123");
   Flight flight2 = new Flight("XYZ789");

2. **Create a Smaller Flight Company:**:
   ```java
   FlightCompany smallerCompany = new FlightCompany("Smaller Company");
   smallerCompany.addComponent(flight1);
   smallerCompany.addComponent(flight2);

3. **Create a Larger Flight Company and Add the Smaller Company:**:
   ```java
   FlightCompany largerCompany = new FlightCompany("Larger Company");
   largerCompany.addComponent(smallerCompany);

4. **Display Details:**:
   ```java
   largerCompany.display();

**Example**
Here is a simple example demonstrating the usage of the Flight and FlightCompany classes:
  ```java
  public class Main {
    public static void main(String[] args) {
        // Create individual flights
        Flight flight1 = new Flight("ABC123");
        Flight flight2 = new Flight("XYZ789");

        // Create a smaller flight company
        FlightCompany smallerCompany = new FlightCompany("Smaller Company");
        smallerCompany.addComponent(flight1);
        smallerCompany.addComponent(flight2);

        // Create a larger flight company
        FlightCompany largerCompany = new FlightCompany("Larger Company");
        largerCompany.addComponent(smallerCompany);

        // Display larger company details
        largerCompany.display();
    }
}

   


