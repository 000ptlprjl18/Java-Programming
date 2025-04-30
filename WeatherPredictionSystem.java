import java.util.*;

interface WeatherData {
    double getTemperature();
    double getHumidity();
    double getWindSpeed();
}

interface WeatherOperations {
    void getInfo();
    void displayInfo();
    void specificTemp();
    void specificHumidity();
    void specificWindSpeed();
}

interface WeatherCalculations {
    double calculateHeatIndex();
    double calculateWindChill();
    double calculateDewPoint();
    double calculateWeatherSeverityIndex();
}

interface AdvancedWeather extends WeatherData, WeatherOperations, WeatherCalculations {
    void displayAdvancedInfo();
    void compareWeather(WeatherStation other);
}

class WeatherStation implements AdvancedWeather {
    private final String location;
    private final double temperature;
    private final double humidity;
    private final double windSpeed;

    public WeatherStation(String location, double temperature, double humidity, double windSpeed) {
        this.location = location;
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
    }

    @Override
    public double getTemperature() {
        return temperature;
    }

    @Override
    public double getHumidity() {
        return humidity;
    }

    @Override
    public double getWindSpeed() {
        return windSpeed;
    }

    @Override
    public void getInfo() {
        System.out.println("Weather data is recorded and cannot be modified.");
    }

    @Override
    public void displayInfo() {
        System.out.println("\n--- Weather Information ---");
        System.out.println("Location: " + location);
        System.out.println("Temperature: " + temperature + "°C");
        System.out.println("Humidity: " + humidity + "%");
        System.out.println("Wind Speed: " + windSpeed + " km/h");
    }

    @Override
    public void specificTemp() {
        if (temperature > 35) System.out.println("Alert: High Temperature!");
        else if (temperature < 5) System.out.println("Alert: Cold Weather!");
    }

    @Override
    public void specificHumidity() {
        if (humidity > 80) System.out.println("Alert: High Humidity!");
        else if (humidity < 20) System.out.println("Alert: Dry Weather!");
    }

    @Override
    public void specificWindSpeed() {
        if (windSpeed > 50) System.out.println("Alert: Strong Winds!");
    }

    @Override
    public double calculateHeatIndex() {
        return temperature + (0.5 * humidity);
    }

    @Override
    public double calculateWindChill() {
        return 13.12 + 0.6215 * temperature - 11.37 * Math.pow(windSpeed, 0.16) + 0.3965 * temperature * Math.pow(windSpeed, 0.16);
    }

    @Override
    public double calculateDewPoint() {
        return temperature - ((100 - humidity) / 5);
    }

    @Override
    public double calculateWeatherSeverityIndex() {
        return (temperature * 0.4) + (humidity * 0.3) + (windSpeed * 0.3);
    }

    @Override
    public void displayAdvancedInfo() {
        System.out.println("\n--- Advanced Weather Calculations ---");
        System.out.println("Heat Index: " + calculateHeatIndex() + "°C");
        System.out.println("Wind Chill: " + calculateWindChill() + "°C");
        System.out.println("Dew Point: " + calculateDewPoint() + "°C");
        System.out.println("Weather Severity Index: " + calculateWeatherSeverityIndex());
    }

    @Override
    public void compareWeather(WeatherStation other) {
        System.out.println("\n--- Comparing Weather Between " + this.location + " and " + other.location + " ---");
        System.out.println("Temperature Difference: " + Math.abs(this.temperature - other.temperature) + "°C");
        System.out.println("Humidity Difference: " + Math.abs(this.humidity - other.humidity) + "%");
        System.out.println("Wind Speed Difference: " + Math.abs(this.windSpeed - other.windSpeed) + " km/h");
        
        if (this.calculateWeatherSeverityIndex() > other.calculateWeatherSeverityIndex()) {
            System.out.println(this.location + " has more severe weather conditions.");
        } else if (this.calculateWeatherSeverityIndex() < other.calculateWeatherSeverityIndex()) {
            System.out.println(other.location + " has more severe weather conditions.");
        } else {
            System.out.println("Both locations have similar weather severity.");
        }
    }
}

public class WeatherPredictionSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter details for Location 1:");
        System.out.print("Location: ");
        String location1 = scanner.nextLine();
        System.out.print("Temperature (°C): ");
        double temp1 = scanner.nextDouble();
        System.out.print("Humidity (%): ");
        double hum1 = scanner.nextDouble();
        System.out.print("Wind Speed (km/h): ");
        double wind1 = scanner.nextDouble();
        scanner.nextLine(); 
        System.out.println("\nEnter details for Location 2:");
        System.out.print("Location: ");
        String location2 = scanner.nextLine();
        System.out.print("Temperature (°C): ");
        double temp2 = scanner.nextDouble();
        System.out.print("Humidity (%): ");
        double hum2 = scanner.nextDouble();
        System.out.print("Wind Speed (km/h): ");
        double wind2 = scanner.nextDouble();

        WeatherStation station1 = new WeatherStation(location1, temp1, hum1, wind1);
        WeatherStation station2 = new WeatherStation(location2, temp2, hum2, wind2);

        while (true) {
            System.out.println("\n--- Weather System Menu ---");
            System.out.println("1. Display Weather Data");
            System.out.println("2. Display Specific Alerts");
            System.out.println("3. Display Advanced Calculations");
            System.out.println("4. Compare Weather Data");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    station1.displayInfo();
                    station2.displayInfo();
                    break;
                case 2:
                    station1.specificTemp();
                    station1.specificHumidity();
                    station1.specificWindSpeed();
                    station2.specificTemp();
                    station2.specificHumidity();
                    station2.specificWindSpeed();
                    break;
                case 3:
                    station1.displayAdvancedInfo();
                    station2.displayAdvancedInfo();
                    break;
                case 4:
                    station1.compareWeather(station2);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}