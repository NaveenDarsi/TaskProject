package com.marolix.controller;

import com.marolix.model.Ticket;
import com.marolix.service.TicketService;

import java.util.List;
import java.util.Scanner;

public class TicketController {
    private final TicketService ticketService = new TicketService();
    private final Scanner scanner = new Scanner(System.in);

    public void displayAllTickets() {
        List<Ticket> tickets = ticketService.getAllTickets();

        for (Ticket ticket : tickets) {
            System.out.println(ticket);
        }
    }

    public void bookTicket() {
        System.out.println("Enter passenger name:");
        String passengerName = scanner.nextLine();

        System.out.println("Enter source:");
        String source = scanner.nextLine();

        System.out.println("Enter destination:");
        String destination = scanner.nextLine();

        System.out.println("Enter pickup point:");
        String pickupPoint = scanner.nextLine();

        System.out.println("Enter ticket price:");
        double price = scanner.nextDouble();
        scanner.nextLine();

        Ticket newTicket = new Ticket(0, passengerName, source, pickupPoint, destination, price);
        ticketService.bookTicket(newTicket);
    }

    public void viewTicketDetails() {
        System.out.println("Enter ticket ID:");
        int ticketId = scanner.nextInt();
        scanner.nextLine();

        Ticket ticket = ticketService.getTicketById(ticketId);

        if (ticket != null) {
            System.out.println(ticket);
        } else {
            System.out.println("Ticket not found.");
        }
    }

    public void updateTicketDetails() {
        System.out.println("Enter ticket ID:");
        int ticketId = scanner.nextInt();
        scanner.nextLine();

        Ticket existingTicket = ticketService.getTicketById(ticketId);

        if (existingTicket != null) {
            System.out.println("Enter new passenger name:");
            String newPassengerName = scanner.nextLine();

            System.out.println("Enter new source:");
            String newSource = scanner.nextLine();

            System.out.println("Enter new destination:");
            String newDestination = scanner.nextLine();

            System.out.println("Enter new ticket price:");
            double newPrice = scanner.nextDouble();
            scanner.nextLine();

            existingTicket.setPassengerName(newPassengerName);
            existingTicket.setSource(newSource);
            existingTicket.setDestination(newDestination);
            existingTicket.setPrice(newPrice);

            ticketService.updateTicketDetails(existingTicket);
        } else {
            System.out.println("Ticket not found. Unable to update details.");
        }
    }

    public void cancelTicket() {
        System.out.println("Enter ticket ID:");
        int ticketId = scanner.nextInt();
        scanner.nextLine();

        ticketService.cancelTicket(ticketId);
    }
}
