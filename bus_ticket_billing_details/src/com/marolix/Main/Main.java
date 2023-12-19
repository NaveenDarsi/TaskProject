package com.marolix.Main;

import com.marolix.controller.TicketController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TicketController ticketController = new TicketController();
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("\n===== Ticket Booking System =====");
            System.out.println("1. Display All Tickets");
            System.out.println("2. Book a New Ticket");
            System.out.println("3. View Ticket Details");
            System.out.println("4. Update Ticket Details");
            System.out.println("5. Cancel a Ticket");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.println("\nAll Tickets:");
                ticketController.displayAllTickets();
            } else if (choice == 2) {
                System.out.println("\nBook a New Ticket:");
                ticketController.bookTicket();
            } else if (choice == 3) {
                System.out.println("\nView Ticket Details:");
                ticketController.viewTicketDetails();
            } else if (choice == 4) {
                System.out.println("\nUpdate Ticket Details:");
                ticketController.updateTicketDetails();
            } else if (choice == 5) {
                System.out.println("\nCancel a Ticket:");
                ticketController.cancelTicket();
            } else if (choice == 0) {
                System.out.println("Exiting the Ticket Booking System. Goodbye!");
            } else {
                System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (choice != 0);

        scanner.close();
    }
}
