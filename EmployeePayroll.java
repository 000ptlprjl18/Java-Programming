import java.util.Scanner;

class Employee {
    String name;
    int id;
    double baseSalary;

    Employee(String name, int id, double baseSalary) {
        this.name = name;
        this.id = id;
        this.baseSalary = baseSalary;
    }

    double calculateSalary() {
        return baseSalary;
    }

    final void displaySalary(double taxPercentage) {
        double salaryAfterTax = calculateSalary() - (calculateSalary() * taxPercentage / 100);
        System.out.printf("Employee: %s\n | ID: %d\n | Salary Before Tax: Rupee %.2f\n | Salary After %.2f%% Tax: Rupee %.2f%n\n",
                name, id, calculateSalary(), taxPercentage, salaryAfterTax);
    }
}

class FullTimeEmployee extends Employee {
    double bonus;

    FullTimeEmployee(String name, int id, double baseSalary, double bonus) {
        super(name, id, baseSalary);
        this.bonus = bonus;
    }

    @Override
    double calculateSalary() {
        return baseSalary + bonus;
    }
}

class PartTimeEmployee extends Employee {
    int hoursWorked;
    double hourlyRate;

    PartTimeEmployee(String name, int id, int hoursWorked, double hourlyRate) {
        super(name, id, 0);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    double calculateSalary() {
        return hoursWorked * hourlyRate;
    }
}

final class ContractEmployee extends Employee {
    double contractAmount;

    ContractEmployee(String name, int id, double contractAmount) {
        super(name, id, contractAmount);
        this.contractAmount = contractAmount;
    }

    @Override
    double calculateSalary() {
        return contractAmount;
    }
}

public class EmployeePayroll {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numEmployees = getValidIntInput(scanner, "Enter number of employees:", 1);
        Employee[] employees = new Employee[numEmployees];

        for (int i = 0; i < numEmployees; i++) {
            System.out.println("\nEnter details for Employee " + (i + 1) + ":");

            String name = getValidName(scanner);
            int id = getValidIntInput(scanner, "Enter Employee ID:", 1);
            int type = getValidIntInput(scanner, "Select Employee Type:\n1. Full-Time\n2. Part-Time\n3. Contract", 1, 3);

            if (type == 1) {
                double baseSalary = getValidDoubleInput(scanner, "Enter Base Salary:", 0);
                double bonus = getValidDoubleInput(scanner, "Enter Bonus:", 0);
                employees[i] = new FullTimeEmployee(name, id, baseSalary, bonus);
            } else if (type == 2) {
                int hoursWorked = getValidIntInput(scanner, "Enter Hours Worked:", 0);
                double hourlyRate = getValidDoubleInput(scanner, "Enter Hourly Rate:", 0);
                employees[i] = new PartTimeEmployee(name, id, hoursWorked, hourlyRate);
            } else {
                double contractAmount = getValidDoubleInput(scanner, "Enter Contract Amount:", 0);
                employees[i] = new ContractEmployee(name, id, contractAmount);
            }
        }

        double taxPercentage = getValidDoubleInput(scanner, "\nEnter a tax percentage to apply to all employees:", 0);
        displayAllEmployees(employees, taxPercentage);

        scanner.close();
    }

    private static void displayAllEmployees(Employee[] employees, double taxPercentage) {
        System.out.println("\nEmployee Salary Details:");
        System.out.println("-------------------------------------------------------------");
        for (Employee emp : employees) {
            emp.displaySalary(taxPercentage);
        }
        System.out.println("-------------------------------------------------------------");
    }

    private static int getValidIntInput(Scanner scanner, String message, int minValue) {
        while (true) {
            try {
                System.out.println(message);
                int value = Integer.parseInt(scanner.nextLine());
                if (value < minValue) throw new IllegalArgumentException("Value must be at least " + minValue + ".");
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid integer.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static int getValidIntInput(Scanner scanner, String message, int minValue, int maxValue) {
        while (true) {
            try {
                System.out.println(message);
                int value = Integer.parseInt(scanner.nextLine());
                if (value < minValue || value > maxValue)
                    throw new IllegalArgumentException("Value must be between " + minValue + " and " + maxValue + ".");
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid integer.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static double getValidDoubleInput(Scanner scanner, String message, double minValue) {
        while (true) {
            try {
                System.out.println(message);
                double value = Double.parseDouble(scanner.nextLine());
                if (value < minValue) throw new IllegalArgumentException("Value must be at least " + minValue + ".");
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static String getValidName(Scanner scanner) {
        while (true) {
            System.out.println("Enter Employee Name:");
            String name = scanner.nextLine();
            if (!name.matches(".*\\d.*")) return name;
            System.out.println("Invalid name! Name should not contain numbers.");
        }
    }
}
