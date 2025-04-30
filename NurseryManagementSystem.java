import java.util.Scanner;

// User-Defined Exception for Invalid Plant Type
class InvalidPlantTypeException extends Exception {
    public InvalidPlantTypeException(String message) {
        super(message);
    }
}

// User-Defined Exception for Negative Values (Growth Period, Price, and Quantity)
class NegativeValueException extends Exception {
    public NegativeValueException(String message) {
        super(message);
    }
}

public class NurseryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String plantName = "";
        String plantType = "";
        int growthPeriod = 0;
        double plantPrice = 0.0;
        int totalPlants = 0;

        // Enter Plant Name
        while (true) {
            try {
                System.out.print("Enter Plant Name: ");
                plantName = scanner.nextLine().trim();
                if (plantName.isEmpty()) {
                    throw new IllegalArgumentException("Plant name cannot be empty!");
                }
                break;
            } catch (IllegalArgumentException e) {
                printExceptionDetails(e);
            }
        }

        // Enter Plant Type
        while (true) {
            try {
                System.out.print("Enter Plant Type (Flowering/Fruiting): ");
                plantType = scanner.nextLine().toLowerCase();
                if (!plantType.equals("flowering") && !plantType.equals("fruiting")) {
                    throw new InvalidPlantTypeException("Invalid Plant Type! Must be 'Flowering' or 'Fruiting'.");
                }
                break;
            } catch (InvalidPlantTypeException e) {
                printExceptionDetails(e);
            }
        }

        // Enter Growth Period
        while (true) {
            try {
                System.out.print("Enter Growth Period (in years): ");
                growthPeriod = Integer.parseInt(scanner.nextLine());
                if (growthPeriod <= 0) {
                    throw new NegativeValueException("Growth period must be a positive integer!");
                }
                break;
            } catch (NumberFormatException e) {
                printExceptionDetails(e);
            } catch (NegativeValueException e) {
                printExceptionDetails(e);
            }
        }

        // Enter Plant Price
        while (true) {
            try {
                System.out.print("Enter Plant Price (in rupees): ");
                plantPrice = Double.parseDouble(scanner.nextLine());
                if (plantPrice <= 0) {
                    throw new NegativeValueException("Plant price must be a positive value!");
                }
                break;
            } catch (NumberFormatException e) {
                printExceptionDetails(e);
            } catch (NegativeValueException e) {
                printExceptionDetails(e);
            }
        }

        // Enter Total Number of Plants
        while (true) {
            try {
                System.out.print("Enter Total Number of Plants: ");
                totalPlants = Integer.parseInt(scanner.nextLine());
                if (totalPlants <= 0) {
                    throw new NegativeValueException("Total number of plants must be a positive integer!");
                }
                break;
            } catch (NumberFormatException e) {
                printExceptionDetails(e);
            } catch (NegativeValueException e) {
                printExceptionDetails(e);
            }
        }

        // Calculate Total Cost
        double totalCost = plantPrice * totalPlants;

        // Display Plant Details
        System.out.println("\n***** Nursery Order Details ***** ");
        System.out.println("Plant Name: " + plantName);
        System.out.println("Plant Type: " + (plantType.equals("flowering") ? "Flowering" : "Fruiting"));
        System.out.println("Growth Period: " + growthPeriod + " years");
        System.out.println("Plant Price: Rupees " + plantPrice);
        System.out.println("Total Plants: " + totalPlants);
        System.out.println("Total Cost to be Paid: Rupees " + totalCost);

        // Handling Built-in Exception without a catch (try-finally only)
        try {
            System.out.println("\nTesting Built-in Exception (Array Index Out of Bounds):");
            int[] numbers = {1, 2, 3};
            System.out.println(numbers[5]); // Invalid index access (throws ArrayIndexOutOfBoundsException)
        } finally {
            System.out.println("\nFinally block executed. Cleaning up resources.");
        }

        // Handling Another Default Exception (NullPointerException)
        try {
            System.out.println("\nTesting Default Exception (Null Pointer Exception):");
            String testString = null;
            System.out.println(testString.length()); // Will throw NullPointerException
        } catch (NullPointerException e) {
            printExceptionDetails(e);
        }

        // Finally block ensures scanner is closed
        try {
            System.out.println("\nThank you for visiting the Nursery!");
        } finally {
            scanner.close();
        }
    }

    // Method to print exception details
    public static void printExceptionDetails(Exception e) {
        System.out.println("\nException Occurred: " + e.getClass().getName());
        System.out.println("Message: " + e.getMessage());
        StackTraceElement element = e.getStackTrace()[0];
        System.out.println("Exception at line: " + element.getLineNumber() + " in " + element.getClassName());
    }
}
