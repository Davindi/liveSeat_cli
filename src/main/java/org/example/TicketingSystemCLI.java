package org.example;

import java.util.Scanner;

public class TicketingSystemCLI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Real-time Ticketing System Configuration CLI!");
        System.out.println("Would you like to continue with existing configuration? (yes/no): ");
        String command = scanner.nextLine();

        Configuration config;
        if (command.equalsIgnoreCase("yes")) {
            config = ConfigurationManager.loadConfiguration();
            if (config != null) {
                System.out.println("Configuration successfully loaded!");
                System.out.println("loaded configurattion:" + config.toString());
            }else {
                System.out.println("No existing Configuration ");
                config = createConfiguration(scanner);

            }
        }else {
            config = createConfiguration(scanner);
        }
        ConfigurationManager.saveConfiguration(config);
        System.out.println("Configuration complete. Exiting CLI.");
    }
    private static Configuration createConfiguration(Scanner scanner) {
        System.out.print("Enter Total Number of Tickets: ");
        int totalTickets = getIntInput(scanner);

        System.out.print("Enter Ticket Release Rate (tickets per interval): ");
        int ticketReleaseRate = getIntInput(scanner);

        System.out.print("Enter Customer Retrieval Rate (tickets per interval): ");
        int customerRetrievalRate = getIntInput(scanner);

        System.out.print("Enter Maximum Ticket Capacity: ");
        int maxTicketCapacity = getIntInput(scanner);

        return new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);
    }
    private static int getIntInput(Scanner scanner) {
        int input;
        while (true) {
            try {
                input = Integer.parseInt(scanner.nextLine());
                if (input <= 0) {
                    System.out.println("Please enter a positive number.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        return input;
    }
}
